/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.teacher;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import school.student.DisplayAllStudentInfoBean;
import school.student.StudentBean;
import school.util.DBConnect;
import school.util.Duplicate;

/**
 *
 * @author MASHUK
 */
public class TeacherBean implements java.io.Serializable{

  private static final long serialVersionUID = 1L;
  private int teacherId;
  private int teacherGradeId;
  private int subjectId;
  private String teacherName;
  private String teacherMobile;
  private String teacherAddress;
  private Date teacherJoinDate;
  private String teacherEmail;
  private String departDate;
  private String subject;
  private String teacherGrade;
  private boolean present = true;
  private UploadedFile teacherPhoto;
  private String roleName;
  private int roleId;
  private String userName;
  private String oldPassword;
  private String newPassword;
  private String confirmPassword;
  private boolean active;
  private int userId;

  /**
   * Creates a new instance of TeacherBean
   */
  public TeacherBean() {
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
   * @return the teacherGradeId
   */
  public int getTeacherGradeId() {
    return teacherGradeId;
  }

  /**
   * @param teacherGradeId the teacherGradeId to set
   */
  public void setTeacherGradeId(int teacherGradeId) {
    this.teacherGradeId = teacherGradeId;
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
   * @return the teacherMobile
   */
  public String getTeacherMobile() {
    return teacherMobile;
  }

  /**
   * @param teacherMobile the teacherMobile to set
   */
  public void setTeacherMobile(String teacherMobile) {
    this.teacherMobile = teacherMobile;
  }

  /**
   * @return the teacherAddress
   */
  public String getTeacherAddress() {
    return teacherAddress;
  }

  /**
   * @param teacherAddress the teacherAddress to set
   */
  public void setTeacherAddress(String teacherAddress) {
    this.teacherAddress = teacherAddress;
  }

  /**
   * @return the teacherJoinDate
   */
  public Date getTeacherJoinDate() {
    return teacherJoinDate;
  }

  /**
   * @param teacherJoinDate the teacherJoinDate to set
   */
  public void setTeacherJoinDate(Date teacherJoinDate) {
    this.teacherJoinDate = teacherJoinDate;
  }

  /**
   * @return the teacherEmail
   */
  public String getTeacherEmail() {
    return teacherEmail;
  }

  /**
   * @param teacherEmail the teacherEmail to set
   */
  public void setTeacherEmail(String teacherEmail) {
    this.teacherEmail = teacherEmail;
  }

  /**
   * @return the departDate
   */
  public String getDepartDate() {
    return departDate;
  }

  /**
   * @param departDate the departDate to set
   */
  public void setDepartDate(String departDate) {
    this.departDate = departDate;
  }

  /**
   * @return the present
   */
  public boolean isPresent() {
    return present;
  }

  /**
   * @param present the present to set
   */
  public void setPresent(boolean present) {
    this.present = present;
  }

  /**
   * @return the teacherPhoto
   */
  public UploadedFile getTeacherPhoto() {
    return teacherPhoto;
  }

  /**
   * @param teacherPhoto the teacherPhoto to set
   */
  public void setTeacherPhoto(UploadedFile teacherPhoto) {
    this.teacherPhoto = teacherPhoto;
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
   * @return the teacherGrade
   */
  public String getTeacherGrade() {
    return teacherGrade;
  }

  /**
   * @param teacherGrade the teacherGrade to set
   */
  public void setTeacherGrade(String teacherGrade) {
    this.teacherGrade = teacherGrade;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public int getRoleId() {
    return roleId;
  }

  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public List<TeacherBean> getAllSubject() {
    String sql = "SELECT * from sch_subject;";
    List<TeacherBean> data = new ArrayList<TeacherBean>();
    Statement st;
    try {
      Connection con = DBConnect.getConnection();
      st = con.createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        TeacherBean tb = new TeacherBean();
        tb.setSubject(rs.getString("subject_name"));
        tb.setSubjectId(rs.getInt("subject_id"));
        data.add(tb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(TeacherBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
  }

  public List<TeacherBean> getAllTeacherGrade() {
    String sql = "SELECT * from sch_teacher_grade;";
    List<TeacherBean> data = new ArrayList<TeacherBean>();
    Statement st;
    try {
      Connection con = DBConnect.getConnection();
      st = con.createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        TeacherBean tb = new TeacherBean();
        tb.setTeacherGrade(rs.getString("teacher_grade"));
        tb.setTeacherGradeId(rs.getInt("teacher_grade_id"));
        //teacher_grade_id, teacher_grade
        data.add(tb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(TeacherBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
  }

  public List<TeacherBean> getAllUserType() {
    String query = "SELECT role_id, role_name FROM sch_user_role;";
    List<TeacherBean> list = new ArrayList<TeacherBean>();
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        TeacherBean urb = new TeacherBean();
        urb.setRoleId(rs.getInt("role_id"));
        urb.setRoleName(rs.getString("role_name"));
        list.add(urb);
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return list;
  }

  public List<TeacherBean> getAllTeacherInfo() {
    List<TeacherBean> data = new ArrayList<TeacherBean>();
    String sql = "SELECT t.teacher_id, t.subject_id, t.teacher_name, "
            + "t.teacher_mobile, t.teacher_addr, t.teacher_join_date, "
            + "t.teacher_grade_id, t.teacher_email, t.ispresent, "
            + "t.depart_date, g.teacher_grade, s.subject_name FROM sch_teacher_info as t "
            + "JOIN sch_teacher_grade as g JOIN sch_subject as s "
            + "WHERE t.subject_id=s.subject_id AND t.teacher_grade_id=g.teacher_grade_id;";
    Statement st;
    try {
      Connection con = DBConnect.getConnection();
      st = con.createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        TeacherBean tb = new TeacherBean();
        tb.setTeacherId(rs.getInt("teacher_id"));
        tb.setTeacherName(rs.getString("teacher_name"));
        tb.setTeacherMobile(rs.getString("teacher_mobile"));
        tb.setTeacherAddress(rs.getString("teacher_addr"));
        tb.setTeacherJoinDate(rs.getDate("teacher_join_date"));
        tb.setTeacherEmail(rs.getString("teacher_email"));
        tb.setPresent(rs.getBoolean("ispresent"));
        tb.setDepartDate(rs.getString("depart_date"));
        tb.setTeacherGrade(rs.getString("teacher_grade"));
        tb.setSubject(rs.getString("subject_name"));
        data.add(tb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(TeacherBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
  }

  public void insertTeacher() {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String joinDate = df.format(this.getTeacherJoinDate());

    String sql = "INSERT INTO sch_teacher_info (subject_id, teacher_name, "
            + "teacher_mobile, teacher_addr, teacher_join_date, teacher_grade_id, "
            + "teacher_email, ispresent, depart_date, teacher_photo) "
            + "VALUES((SELECT subject_id FROM sch_subject WHERE subject_name=?),?,?,?,?,"
            + "(SELECT teacher_grade_id FROM sch_teacher_grade WHERE teacher_grade=?),?,?,?,?);";
    try {
      InputStream streamFile = getTeacherPhoto().getInputstream();
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setString(1, this.getSubject());
      ps.setString(2, this.getTeacherName());
      ps.setString(3, this.getTeacherMobile());
      ps.setString(4, this.getTeacherAddress());
      ps.setString(5, joinDate);
      ps.setString(6, this.getTeacherGrade());
      ps.setString(7, this.getTeacherEmail());
      ps.setBoolean(8, this.isPresent());
      ps.setString(9, "N/A");
      ps.setBinaryStream(10, streamFile, this.getTeacherPhoto().getSize());
      int i = ps.executeUpdate();
      if (i > 0) {
        System.out.println("Ok");
      }
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Data is Saved", "Add New");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void updateTeacherInfo() {
//teacher_id, subject_id, teacher_name, teacher_mobile, teacher_addr, teacher_join_date, teacher_grade_id, teacher_email, ispresent, depart_date, teacher_photo
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String joinDate = df.format(this.getTeacherJoinDate());
    String dpDate = df.format(this.getDepartDate());
    String sql = "UPDATE sch_teacher_info SET "
            + "subject_id=(SELECT subject_id FROM sch_subject WHERE subject_name=?), "
            + "teacher_name=?, teacher_mobile=?, teacher_addr=?, teacher_join_date=?, "
            + "teacher_grade_id=(SELECT teacher_grade_id FROM sch_teacher_grade WHERE teacher_grade=?), "
            + "teacher_email=?, ispresent=?, depart_date=? WHERE teacher_id=?;";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setString(1, this.getSubject());
      ps.setString(2, this.getTeacherName());
      ps.setString(3, this.getTeacherMobile());
      ps.setString(4, this.getTeacherAddress());
      ps.setString(5, joinDate);
      ps.setString(6, this.getTeacherGrade());
      ps.setString(7, this.getTeacherEmail());
      ps.setBoolean(8, this.isPresent());
      ps.setString(9, dpDate);
      ps.setInt(10, this.getTeacherId());
      int i = ps.executeUpdate();
      if (i > 0) {
      }
    } catch (SQLException ex) {
      Logger.getLogger(TeacherBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public List<String> getAllUserName() {
    List<String> data = new ArrayList<String>();
    String sql = "SELECT uname FROM sch_user;";
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        data.add(rs.getString("uname"));
      }
    } catch (SQLException ex) {
      Logger.getLogger(TeacherBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
  }

  private boolean isConfirm() {
    boolean b = false;
    if (getNewPassword().equals(getConfirmPassword())) {
      b = true;
    }
    return b;
  }

  public boolean saveUserInfo() {
    boolean returnValue = false;
    String sql = "insert into sch_user (role_id, uname, password, isactive) values (?,?,?,?);";
    boolean isDuplicate = new Duplicate<String>().isDuplicate(getUserName(), getAllUserName());
    if (isDuplicate) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate User Name not allowed here");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    } else {
      if (isConfirm()) {
        try {
          PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
          ps.setInt(1, this.getRoleId());
          ps.setString(2, this.getUserName());
          ps.setString(3, this.getNewPassword());
          ps.setBoolean(4, this.isActive());
          int i = ps.executeUpdate();
          if (i > 0) {
            returnValue = true;
          } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save user data");
            FacesContext.getCurrentInstance().addMessage(null, msg);
          }
//          clear();
        } catch (SQLException ex) {
          Logger.getLogger(TeacherBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Password does match with confirm password");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    }
    return returnValue;
  }

  public void doSetUserId() {
    try {
      Connection con = DBConnect.getConnection();
      Statement st = con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT MAX(last_insert_id(user_id)) as user_id FROM sch_user;");
      while (rs.next()) {
        setUserId(rs.getInt("user_id"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void doInsert() {

    if (this.teacherPhoto != null) {
      boolean isSuccess = saveUserInfo();
      if (isSuccess) {
        doSetUserId();
        saveTeacherInfo();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Data is Saved", "Add New");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        clear();
      }
    } else {
      FacesMessage msg = new FacesMessage("Please select image!!");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
  }

  public void saveTeacherInfo() {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String joinDate = df.format(this.getTeacherJoinDate());
    String sql = "INSERT INTO sch_teacher_info(subject_id, teacher_name, teacher_mobile, teacher_addr, teacher_join_date, teacher_grade_id, teacher_email, ispresent, depart_date, teacher_photo, user_id) values(?,?,?,?,?,?,?,?,?,?,?)";
    try {
      InputStream streamFile = this.getTeacherPhoto().getInputstream();
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setInt(1, this.getSubjectId());
      ps.setString(2, this.getTeacherName());
      ps.setString(3, this.teacherMobile);
      ps.setString(4, this.getTeacherAddress());
      ps.setString(5, joinDate);
      ps.setInt(6, this.getTeacherGradeId());
      ps.setString(7, this.getTeacherEmail());
      ps.setBoolean(8, active);
      ps.setString(9, this.getDepartDate());
      ps.setBinaryStream(10, streamFile, this.getTeacherPhoto().getSize());
      ps.setInt(11, this.getUserId());
      ps.executeUpdate();


    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void clear() {
    teacherName = "";
    subject = "";
    subjectId = 0;
    teacherMobile = "";
    teacherAddress = "";
    teacherEmail = "";
    teacherGrade = "";
    teacherGradeId = 0;
    teacherJoinDate = null;
    departDate = "";
    userName = "";
    newPassword = "";
    confirmPassword = "";
    roleName = "";
  }
}
