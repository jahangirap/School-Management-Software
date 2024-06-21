/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.exam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import school.util.DBConnect;
import school.util.Duplicate;
import school.util.LoginUtil;

/**
 *
 * @author MASHUK
 */
public class MarksBean implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  public static final ArrayList<MarksBean> marksList = new ArrayList<MarksBean>();
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
  private String studentName;
  private boolean renderClass;
  private boolean renderSection;
  private boolean renderExam;
  private boolean renderDatatable;

  /** Creates a new instance of MarksBean */
  public MarksBean() {
  }
  //marks_id, acyid, section_id, group_id, std_roll_no, subject_id, marks_obtain, sub_grade
// <editor-fold defaultstate="collapsed" desc="Getter and Setter">

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

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public boolean isRenderClass() {
    return renderClass;
  }

  public void setRenderClass(boolean renderClass) {
    this.renderClass = renderClass;
  }

  public boolean isRenderDatatable() {
    return renderDatatable;
  }

  public void setRenderDatatable(boolean renderDatatable) {
    this.renderDatatable = renderDatatable;
  }

  public boolean isRenderExam() {
    return renderExam;
  }

  public void setRenderExam(boolean renderExam) {
    this.renderExam = renderExam;
  }

  public boolean isRenderSection() {
    return renderSection;
  }

  public void setRenderSection(boolean renderSection) {
    this.renderSection = renderSection;
  }

  public ArrayList<MarksBean> getMarksList() {
    return marksList;
  }

  public void doClassRender() {
    if (acyear != 0) {
      setRenderClass(true);
//      setClassName(null);
    } else {      
      setRenderClass(false);
      setRenderSection(false);
      setRenderExam(false);
      setRenderDatatable(false);
    }

  }

  public void doSectionRender() {
    if (!getClassName().isEmpty()) {
      setRenderSection(true);
//      setSectionName(null);
    } else {
      
      setRenderSection(false);
      setRenderExam(false);
      setRenderDatatable(false);
    }
  }

  public void doExamRender() {
    if (!getSectionName().isEmpty()) {
      setRenderExam(true);
//      setExamTitle(null);
    } else {
      setRenderExam(false);
      setRenderDatatable(false);
    }
  }

  public void doDatatableRender() {
    if (!getExamTitle().isEmpty()) {
      setRenderDatatable(true);
    } else {
      setRenderDatatable(false);
    }
  }
// </editor-fold>

  public void validateRoll(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    boolean isWrong = (Integer) value < 1;
    if (isWrong) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid Number");
      throw new ValidatorException(msg);
    }
  }

  public List<SelectItem> getAllRollNos() {
    String query = "SELECT std_roll_no FROM sch_std_registration "
            + "WHERE acyid=(SELECT acyid FROM sch_academic_year WHERE acyear=?) "
            + "AND section_id=(SELECT section_id FROM sch_section_name WHERE section_name=?) "
            + "AND group_id=(SELECT group_id FROM sch_std_group WHERE group_name=?);";
    List<SelectItem> list = new ArrayList<SelectItem>();
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(query);
      ps.setInt(1, getAcyear());
      ps.setString(2, getSectionName());
      ps.setString(3, getGroupName());
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Integer value = rs.getInt("std_roll_no");
        String label = value.toString();
        SelectItem item = new SelectItem(value, label);
        list.add(item);
        System.out.println("OK");
      }
    } catch (SQLException ex) {
      Logger.getLogger(MarksBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<MarksBean> getAllAcademicYear() {
    String query = "SELECT * FROM sch_academic_year";
    List<MarksBean> list = new ArrayList<MarksBean>();
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        MarksBean ayb = new MarksBean();
        ayb.setAcyId(rs.getInt("acyid"));
        ayb.setAcyear(rs.getInt("acyear"));
        list.add(ayb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MarksBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<MarksBean> getAllClasses() {
    List<MarksBean> list = new ArrayList<MarksBean>();
    String query = "SELECT class_id, class_name FROM sch_class_name;";
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        MarksBean cb = new MarksBean();
        cb.setClassId(rs.getInt("class_id"));
        cb.setClassName(rs.getString("class_name"));
        list.add(cb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MarksBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<MarksBean> getAllSections() {
    List<MarksBean> list = new ArrayList<MarksBean>();
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
        MarksBean sb = new MarksBean();
        sb.setSectionId(rs.getInt("section_id"));
        sb.setSectionName(rs.getString("section_name"));
        list.add(sb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MarksBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<MarksBean> getAllGroups() {
    List<MarksBean> list = new ArrayList<MarksBean>();
    String query = "SELECT group_id, group_name FROM sch_std_group;";
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        MarksBean gb = new MarksBean();
        gb.setGroupId(rs.getInt("group_id"));
        gb.setGroupName(rs.getString("group_name"));
        list.add(gb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MarksBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<MarksBean> getAllSubjects() {
    List<MarksBean> list = new ArrayList<MarksBean>();
    String query = "SELECT subject_id, subject_name FROM sch_subject;";
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        MarksBean gb = new MarksBean();
        gb.setSubjectId(rs.getInt("subject_id"));
        gb.setSubjectName(rs.getString("subject_name"));
        list.add(gb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MarksBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<MarksBean> getAllExams() {
    List<MarksBean> list = new ArrayList<MarksBean>();
    String query = "SELECT ex_tt_id, exam_title FROM sch_exam_title;";
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        MarksBean gb = new MarksBean();
        gb.setExamId(rs.getInt("ex_tt_id"));
        gb.setExamTitle(rs.getString("exam_title"));
        list.add(gb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MarksBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<Integer> getRolls() {
    List<Integer> rollList = new ArrayList<Integer>();
    String query = "SELECT std_roll_no FROM sch_std_marks "
            + "WHERE acyid=(SELECT acyid FROM sch_academic_year WHERE acyear=?) "
            + "AND section_id=(SELECT section_id FROM sch_section_name WHERE section_name=?) "
            + "AND group_id=(SELECT group_id FROM sch_std_group WHERE group_name=?) "
            + "AND subject_id=(SELECT subject_id FROM sch_subject WHERE subject_name=?) "
            + "AND ex_tt_id=(SELECT ex_tt_id FROM sch_exam_title WHERE exam_title=?);";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(query);
      ps.setInt(1, getAcyear());
      ps.setString(2, getSectionName());
      ps.setString(3, getGroupName());
      ps.setString(4, getSubjectName());
      ps.setString(5, getExamTitle());
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        rollList.add(rs.getInt("std_roll_no"));
      }
    } catch (SQLException ex) {
      Logger.getLogger(MarksBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return rollList;
  }

  public List<Integer> getNumberList() {
    List<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i <= 100; i++) {
      list.add(i);
    }
    return list;
  }

  public void doSetGrade() {
    int num = getMarksObtain();
    String grade = null;
    if (num >= 80 && num <= 100) {
      grade = "A+";
    } else if (num >= 70 && num <= 79) {
      grade = "A";
    } else if (num >= 60 && num <= 69) {
      grade = "A-";
    } else if (num >= 50 && num <= 59) {
      grade = "B";
    } else if (num >= 40 && num <= 49) {
      grade = "C";
    } else if (num >= 33 && num <= 39) {
      grade = "D";
    } else if (num >= 0 && num <= 32) {
      grade = "F";
    }
    setSubjectGrade(grade);
  }

  public void addMarks() {
    String qurey = "INSERT INTO sch_std_marks "
            + "(acyid, section_id, group_id, std_roll_no, subject_id, marks_obtain, sub_grade, ex_tt_id) "
            + "VALUES ((SELECT acyid FROM sch_academic_year WHERE acyear=?),"
            + "(SELECT section_id FROM sch_section_name WHERE section_name=?),"
            + "(SELECT group_id FROM sch_std_group WHERE group_name=?),?,"
            + "(SELECT subject_id FROM sch_subject WHERE subject_name=?),?,?,"
            + "(SELECT ex_tt_id FROM sch_exam_title WHERE exam_title=?))";
    boolean isDuplicate = new Duplicate<Integer>().isDuplicate(getStudentRollNo(), getRolls());
    if (isDuplicate) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate Roll not allowed");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    } else {
      try {
        PreparedStatement ps = DBConnect.getConnection().prepareStatement(qurey);
        ps.setInt(1, getAcyear());
        ps.setString(2, getSectionName());
        ps.setString(3, getGroupName());
        ps.setInt(4, getStudentRollNo());
        ps.setString(5, getSubjectName());
        ps.setInt(6, getMarksObtain());
        ps.setString(7, getSubjectGrade());
        ps.setString(8, getExamTitle());
        int i = ps.executeUpdate();
        if (i > 0) {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data Saved Successfully");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save data");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        }
      } catch (SQLException ex) {
        Logger.getLogger(MarksBean.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
//    setStudentRollNo(0);
//    setMarksObtain(0);
//    setSubjectGrade(null);
  }

  public List<MarksBean> getAllMarksInfo() {
    List<MarksBean> markList = new ArrayList<MarksBean>();
    String query = "SELECT m.marks_id, a.acyear, c.class_name, s.section_name, "
            + "g.group_name, m.std_roll_no, sub.subject_name, m.marks_obtain, "
            + "m.sub_grade, e.exam_title "
            + "FROM sch_std_marks m "
            + "JOIN sch_academic_year a "
            + "JOIN sch_class_name c "
            + "JOIN sch_section_name s "
            + "JOIN sch_std_group g "
            + "JOIN sch_subject sub "
            + "JOIN sch_exam_title e "
            + "WHERE m.acyid=a.acyid "
            + "AND c.class_id=(SELECT class_id FROM sch_section_name se WHERE se.section_id=m.section_id) "
            + "AND s.section_id=m.section_id AND g.group_id=m.group_id "
            + "AND sub.subject_id=m.subject_id "
            + "AND e.ex_tt_id=m.ex_tt_id;";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(query);
      //marks_id, acyear, class_name, section_name, group_name, std_roll_no, subject_name, marks_obtain, sub_grade, exam_title
      ResultSet rs = ps.executeQuery();
//      marksList.removeAll(marksList);
      while (rs.next()) {
        MarksBean mb = new MarksBean();
        mb.setAcyear(rs.getInt("acyear"));
        mb.setClassName(rs.getString("class_name"));
        mb.setSectionName(rs.getString("section_name"));
        mb.setGroupName(rs.getString("group_name"));
        mb.setSubjectName(rs.getString("subject_name"));
        mb.setExamTitle(rs.getString("exam_title"));
        mb.setStudentRollNo(rs.getInt("std_roll_no"));
        mb.setMarksObtain(rs.getInt("marks_obtain"));
        mb.setSubjectGrade(rs.getString("sub_grade"));
//        marksList.add(mb);
        markList.add(mb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MarksBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return markList;
  }

  /**
   * This is used to search result though it is empty. don't delete it.
   */
  public void doSearch() {
  }

  public List<MarksBean> getAllMarksForPublic() {
    List<MarksBean> markList = new ArrayList<MarksBean>();
    String query = "SELECT m.marks_id, a.acyear, c.class_name, s.section_name, "
            + "g.group_name, m.std_roll_no, sub.subject_name, m.marks_obtain, "
            + "m.sub_grade, e.exam_title "
            + "FROM sch_std_marks m "
            + "JOIN sch_academic_year a "
            + "JOIN sch_class_name c "
            + "JOIN sch_section_name s "
            + "JOIN sch_std_group g "
            + "JOIN sch_subject sub "
            + "JOIN sch_exam_title e "
            + "WHERE m.acyid=a.acyid "
            + "AND c.class_id=(SELECT class_id FROM sch_section_name se WHERE se.section_id=m.section_id) "
            + "AND s.section_id=m.section_id AND g.group_id=m.group_id "
            + "AND sub.subject_id=m.subject_id "
            + "AND e.ex_tt_id=m.ex_tt_id "
            + "AND a.acyear=? "
            + "AND e.exam_title=?;";
    String q = "SELECT  m.std_roll_no, "
            + "(SELECT s.std_name FROM sch_student_info s WHERE s.std_id=(SELECT r.std_id FROM sch_std_registration r WHERE r.acyid=m.acyid and r.section_id=m.section_id and r.group_id=m.group_id and r.std_roll_no=m.std_roll_no)) as std_name, "
            + "SUM(m.marks_obtain) as marks_obtain "
            + "FROM sch_std_marks m "
            + "JOIN sch_student_info s "
            + "WHERE m.acyid=(SELECT acyid FROM sch_academic_year WHERE acyear=?) "
            + "AND m.section_id=(SELECT section_id FROM sch_section_name WHERE section_name=?) "
            + "AND m.ex_tt_id=(SELECT ex_tt_id FROM sch_exam_title WHERE exam_title=?) "
            + "GROUP BY std_roll_no;";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(q);
      //marks_id, acyear, class_name, section_name, group_name, std_roll_no, subject_name, marks_obtain, sub_grade, exam_title
      ps.setInt(1, getAcyear());
      ps.setString(2, getSectionName());
      ps.setString(3, getExamTitle());
      ResultSet rs = ps.executeQuery();
//      marksList.removeAll(marksList);
      while (rs.next()) {
        MarksBean mb = new MarksBean();

        mb.setStudentRollNo(rs.getInt("std_roll_no"));
        mb.setStudentName(rs.getString("std_name"));
        mb.setMarksObtain(rs.getInt("marks_obtain"));

//        marksList.add(mb);
        markList.add(mb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(MarksBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return markList;
  }

  public List<MarksBean> getMyMarks() {
    List<MarksBean> markList = new ArrayList<MarksBean>();
    String sql = "SELECT acyid, section_id, group_id, std_roll_no FROM sch_std_registration where std_id=(select std_id from sch_student_info where user_id=(select user_id from sch_user where uname=?));";
    String sql2 = "SELECT s.subject_name, m.marks_obtain,m.sub_grade FROM sch_std_marks m JOIN sch_subject s "
            + "WHERE acyid=? AND section_id=? AND group_id=? AND std_roll_no=? AND ex_tt_id=? AND s.subject_id = m.subject_id;";

    PreparedStatement ps;
    HttpSession session = LoginUtil.getSession();
    String un = session.getAttribute("username").toString();
    try {
      ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setString(1, un);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        int acyid = rs.getInt("acyid");
        int section_id = rs.getInt("section_id");
        int gtoup_id = rs.getInt("group_id");
        int stdroll = rs.getInt("std_roll_no");
        PreparedStatement ps2 = DBConnect.getConnection().prepareStatement(sql2);
        ps2.setInt(1, acyid);
        ps2.setInt(2, section_id);
        ps2.setInt(3, gtoup_id);
        ps2.setInt(4, stdroll);
        ps2.setInt(5, getExamId());
        ResultSet rs1 = ps2.executeQuery();
        while (rs1.next()) {
          MarksBean m = new MarksBean();
          m.setSubjectName(rs1.getString("subject_name"));
          m.setMarksObtain(rs1.getInt("marks_obtain"));
          m.setSubjectGrade(rs1.getString("sub_grade"));
          markList.add(m);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return markList;
  }

  public String goUpdatePage() {
    return "marksUpdate?faces-redirect=true";
  }

  public void deleteData() {
    System.out.println("Ok");
  }

  public void updateData() {
  }

  public static void main(String[] args) {
    MarksBean mb = new MarksBean();
    mb.setAcyId(1);
    mb.setSectionId(1);
    mb.setGroupId(1);
    List<SelectItem> list = mb.getAllRollNos();
    for (Iterator<SelectItem> it = list.iterator(); it.hasNext();) {
      SelectItem s = it.next();
      System.out.println(s.getLabel());
    }
  }
}
