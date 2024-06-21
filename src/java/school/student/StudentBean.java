/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.student;

import java.io.IOException;
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
import school.academic.AcademicYearBean;
import school.util.DBConnect;
import school.util.Duplicate;

/**
 *
 * @author MASHUK
 */
public class StudentBean implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private int studentId;
  private String studentName;
  private String FatherName;
  private String motherName;
  private String gender;
  private int age;
  private Date dateOfBirth;
  private boolean stdStatus = true;
  private UploadedFile photo;
  private int userId;
  private String userName;
  private String oldPassword;
  private String newPassword;
  private String confirmPassword;

  /** Creates a new instance of StudentBean */
  public StudentBean() {
  }

  public String getFatherName() {
    return FatherName;
  }

  public void setFatherName(String FatherName) {
    this.FatherName = FatherName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getMotherName() {
    return motherName;
  }

  public void setMotherName(String motherName) {
    this.motherName = motherName;
  }

  public UploadedFile getPhoto() {
    return photo;
  }

  public void setPhoto(UploadedFile photo) {
    this.photo = photo;
  }

  public boolean isStdStatus() {
    return stdStatus;
  }

  public void setStdStatus(boolean stdStatus) {
    this.stdStatus = stdStatus;
  }

  public int getStudentId() {
    return studentId;
  }

  public void setStudentId(int studentId) {
    this.studentId = studentId;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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

  //To get Student Id
  public void doSetStudentId() {
    try {
      Connection con = DBConnect.getConnection();
      Statement st = con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT MAX(last_insert_id(std_id)) as std_id FROM sch_student_info;");
      while (rs.next()) {
        setStudentId(rs.getInt("std_id"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private boolean isConfirm() {
    boolean b = false;
    if (getNewPassword().equals(getConfirmPassword())) {
      b = true;
    }
    return b;
  }

  void clear() {
    setAge(0);
    setConfirmPassword(null);
    setDateOfBirth(null);
    setFatherName(null);
    setGender(null);
    setMotherName(null);
    setNewPassword(null);
    setStudentId(0);
    setStudentName(null);
    setUserId(0);
    setUserName(null);
  }
}
