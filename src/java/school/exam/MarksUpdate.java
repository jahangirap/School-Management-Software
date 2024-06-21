/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.exam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import school.util.DBConnect;

/**
 *
 * @author MASHUK
 */
public class MarksUpdate implements java.io.Serializable{

  private static final long serialVersionUID = 1L;
 
  private int marksId;
  private int acyId;
  private int sectionId;
  private int groupId;
  private int studentRollNo;
  private int subjectId;
  private int marksObtain;
  private String subjectGrade;
  private int acyear;
  private int classId;
  private String className;
  private String sectionName;
  private String groupName;
  private String subjectName;
  private int examId;
  private String examTitle;
  private MarksBean selectedRow;

  /** Creates a new instance of MarksUpdate */
  public MarksUpdate() {
  }

  public int getAcyId() {
    return acyId;
  }

  public void setAcyId(int acyId) {
    this.acyId = acyId;
  }

  public int getAcyear() {
    return acyear;
  }

  public void setAcyear(int acyear) {
    this.acyear = acyear;
  }

  public int getClassId() {
    return classId;
  }

  public void setClassId(int classId) {
    this.classId = classId;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public int getExamId() {
    return examId;
  }

  public void setExamId(int examId) {
    this.examId = examId;
  }

  public String getExamTitle() {
    return examTitle;
  }

  public void setExamTitle(String examTitle) {
    this.examTitle = examTitle;
  }

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public int getMarksId() {
    return marksId;
  }

  public void setMarksId(int marksId) {
    this.marksId = marksId;
  }

  public int getMarksObtain() {
    return marksObtain;
  }

  public void setMarksObtain(int marksObtain) {
    this.marksObtain = marksObtain;
  }

  public int getSectionId() {
    return sectionId;
  }

  public void setSectionId(int sectionId) {
    this.sectionId = sectionId;
  }

  public String getSectionName() {
    return sectionName;
  }

  public void setSectionName(String sectionName) {
    this.sectionName = sectionName;
  }

  public int getStudentRollNo() {
    return studentRollNo;
  }

  public void setStudentRollNo(int studentRollNo) {
    this.studentRollNo = studentRollNo;
  }

  public String getSubjectGrade() {
    return subjectGrade;
  }

  public void setSubjectGrade(String subjectGrade) {
    this.subjectGrade = subjectGrade;
  }

  public int getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(int subjectId) {
    this.subjectId = subjectId;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public MarksBean getSelectedRow() {
    return selectedRow;
  }

  public void setSelectedRow(MarksBean selectedRow) {
    this.selectedRow = selectedRow;
  }
  
  public List<MarksUpdate> getAllAcademicYear() {
    String query = "SELECT * FROM sch_academic_year";
    List<MarksUpdate> list = new ArrayList<MarksUpdate>();
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        MarksUpdate ayb = new MarksUpdate();
        ayb.setAcyId(rs.getInt("acyid"));
        ayb.setAcyear(rs.getInt("acyear"));
        list.add(ayb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MarksUpdate.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<MarksUpdate> getAllClasses() {
    List<MarksUpdate> list = new ArrayList<MarksUpdate>();
    String query = "SELECT class_id, class_name FROM sch_class_name;";
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        MarksUpdate cb = new MarksUpdate();
        cb.setClassId(rs.getInt("class_id"));
        cb.setClassName(rs.getString("class_name"));
        list.add(cb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MarksUpdate.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<MarksUpdate> getAllSections() {
    List<MarksUpdate> list = new ArrayList<MarksUpdate>();
    String query = "SELECT section_id, section_name FROM sch_section_name "
            + "WHERE acyid=(SELECT acyid FROM sch_academic_year WHERE acyear=?) "
            + "AND class_id=(SELECT class_id FROM sch_class_name WHERE class_name=?);";
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ps.setInt(1, getAcyear());
      ps.setString(2, getClassName());
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        MarksUpdate sb = new MarksUpdate();
        sb.setSectionId(rs.getInt("section_id"));
        sb.setSectionName(rs.getString("section_name"));
        list.add(sb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MarksUpdate.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<MarksUpdate> getAllGroups() {
    List<MarksUpdate> list = new ArrayList<MarksUpdate>();
    String query = "SELECT group_id, group_name FROM sch_std_group;";
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        MarksUpdate gb = new MarksUpdate();
        gb.setGroupId(rs.getInt("group_id"));
        gb.setGroupName(rs.getString("group_name"));
        list.add(gb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MarksUpdate.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<MarksUpdate> getAllSubjects() {
    List<MarksUpdate> list = new ArrayList<MarksUpdate>();
    String query = "SELECT subject_id, subject_name FROM sch_subject;";
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        MarksUpdate gb = new MarksUpdate();
        gb.setSubjectId(rs.getInt("subject_id"));
        gb.setSubjectName(rs.getString("subject_name"));
        list.add(gb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MarksUpdate.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<MarksUpdate> getAllExams() {
    List<MarksUpdate> list = new ArrayList<MarksUpdate>();
    String query = "SELECT ex_tt_id, exam_title FROM sch_exam_title;";
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        MarksUpdate gb = new MarksUpdate();
        gb.setExamId(rs.getInt("ex_tt_id"));
        gb.setExamTitle(rs.getString("exam_title"));
        list.add(gb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MarksUpdate.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public void doSetSelectedRow(){
    setAcyear(getSelectedRow().getAcyear());
    setClassName(getSelectedRow().getClassName());
    setSectionName(getSelectedRow().getSectionName());
    setGroupName(getSelectedRow().getGroupName());
    setSubjectName(getSelectedRow().getSubjectName());
    setExamTitle(getSelectedRow().getExamTitle());
    setStudentRollNo(getSelectedRow().getStudentRollNo());
    setMarksObtain(getSelectedRow().getMarksObtain());
    setSubjectGrade(getSelectedRow().getSubjectGrade());
    System.out.println(getSelectedRow().getAcyear());
//    return "marksUpdate";
  }
  public void updateData(){}
}
