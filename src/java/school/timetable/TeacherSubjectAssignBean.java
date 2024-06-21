/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.timetable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import school.util.DBConnect;

/**
 *
 * @author MASHUK
 */
public class TeacherSubjectAssignBean implements java.io.Serializable{

    
    private static final long serialVersionUID = 1L;
    private int teachSubAssignId;
    private int teacherId;
    private int subjectId;
    private int sectionId;
    private int classId;
    private String teacherName;
    private String subject;
    private String sectionName;
    private String className;

    /** Creates a new instance of TeacherTeacherSubjectAssignBean */
    public TeacherSubjectAssignBean() {
    }

    /**
     * @return the teachSubAssignId
     */
    public int getTeachSubAssignId() {
        return teachSubAssignId;
    }

    /**
     * @param teachSubAssignId the teachSubAssignId to set
     */
    public void setTeachSubAssignId(int teachSubAssignId) {
        this.teachSubAssignId = teachSubAssignId;
    }

    /**
     * @return the teacherId
     */
    public int getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId the teacherId to set
     */
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return the subjectId
     */
    public int getSubjectId() {
        return subjectId;
    }

    /**
     * @param subjectId the subjectId to set
     */
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * @return the sectionId
     */
    public int getSectionId() {
        return sectionId;
    }

    /**
     * @param sectionId the sectionId to set
     */
    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * @return the classId
     */
    public int getClassId() {
        return classId;
    }

    /**
     * @param classId the classId to set
     */
    public void setClassId(int classId) {
        this.classId = classId;
    }

    /**
     * @return the teacherName
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * @param teacherName the teacherName to set
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the sectionName
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * @param sectionName the sectionName to set
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    public List<TeacherSubjectAssignBean> getAllTeacherName() {
        List<TeacherSubjectAssignBean> data = new ArrayList<TeacherSubjectAssignBean>();
        String sql = "SELECT teacher_id, teacher_name FROM sch_teacher_info;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DBConnect.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TeacherSubjectAssignBean sab = new TeacherSubjectAssignBean();
                sab.setTeacherId(rs.getInt("teacher_id"));
                sab.setTeacherName(rs.getString("teacher_name"));
                data.add(sab);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    public List<TeacherSubjectAssignBean> getAllClassName() {
        List<TeacherSubjectAssignBean> data = new ArrayList<TeacherSubjectAssignBean>();
        String sql = "SELECT * FROM sch_class_name";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DBConnect.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TeacherSubjectAssignBean sab = new TeacherSubjectAssignBean();
                sab.setClassId(rs.getInt("class_id"));
                sab.setClassName(rs.getString("class_name"));
                data.add(sab);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    public List<TeacherSubjectAssignBean> getAllSectionName() {
        List<TeacherSubjectAssignBean> data = new ArrayList<TeacherSubjectAssignBean>();
        String sql = "SELECT section_id, section_name FROM sch_section_name WHERE class_id=?;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setInt(1, getClassId());
            rs = ps.executeQuery();
            while (rs.next()) {
                TeacherSubjectAssignBean sab = new TeacherSubjectAssignBean();
                sab.setSectionId(rs.getInt("section_id"));
                sab.setSectionName(rs.getString("section_name"));
                data.add(sab);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    public List<TeacherSubjectAssignBean> getAllSubjectName() {
        List<TeacherSubjectAssignBean> data = new ArrayList<TeacherSubjectAssignBean>();
        String sql = "SELECT subject_id, subject_name FROM sch_subject;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DBConnect.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TeacherSubjectAssignBean sab = new TeacherSubjectAssignBean();
                sab.setSubjectId(rs.getInt("subject_id"));
                sab.setSubject(rs.getString("subject_name"));
                data.add(sab);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    public void insertData() {
        String sql = "INSERT INTO sch_teacher_sub_assign (teacher_id, section_id, subject_id) VALUES (?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setInt(1, getTeacherId());
            ps.setInt(2, getSectionId());
            ps.setInt(3, getSubjectId());
            int i = ps.executeUpdate();
            if (i > 0) {          
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "New data save Successfully");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save data");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        clear();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateData() {
    }

    public void deleteData() {
    }
//SELECT t.teacher_name, c.class_name, sec.section_name, sub.subject_name FROM sch_teacher_info t JOIN sch_class_name c
//JOIN sch_section_name sec JOIN sch_subject sub WHERE t.teacher_id=1 and c.class_id=2 and sec.section_id=4 and sub.subject_id=1;

    //
    //  
    //SELECT t.teacher_name, c.class_name, sec.section_name, sub.subject_name FROM sch_teacher_info t JOIN sch_class_name c
//JOIN sch_section_name sec JOIN sch_subject sub JOIN sch_teacher_sub_assign tsa
//WHERE t.teacher_id=tsa.teacher_id AND c.class_id=(SELECT class_id FROM sch_section_name WHERE section_id=tsa.section_id)
//and sec.section_id=tsa.section_id and sub.subject_id=tsa.subject_id;
    public List<TeacherSubjectAssignBean> getAllData() {
        List<TeacherSubjectAssignBean> data = new ArrayList<TeacherSubjectAssignBean>();
        String sql = "SELECT t.teacher_name, c.class_name, sec.section_name, sub.subject_name "
                + "FROM sch_teacher_info t "
                + "JOIN sch_class_name c "
                + "JOIN sch_section_name sec "
                + "JOIN sch_subject sub "
                + "JOIN sch_teacher_sub_assign tsa "
                + "WHERE t.teacher_id=tsa.teacher_id "
                + "AND c.class_id=(SELECT class_id FROM sch_section_name WHERE section_id=tsa.section_id) "
                + "AND sec.section_id=tsa.section_id "
                + "AND sub.subject_id=tsa.subject_id;";
        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);           
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TeacherSubjectAssignBean sab = new TeacherSubjectAssignBean();
                sab.setTeacherName(rs.getString("teacher_name"));
                sab.setClassName(rs.getString("class_name"));
                sab.setSectionName(rs.getString("section_name"));
                sab.setSubject(rs.getString("subject_name"));
                data.add(sab);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherSubjectAssignBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

  private void clear() {
    setClassName(null);
    setSectionName(null);
    setSubject(null);
    setTeacherName(null);
  }
    
}
