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
import school.util.DBConnect;
import school.util.Duplicate;
/**
 *
 * @author MASHUK
 */
public class NewStudentBean implements java.io.Serializable{

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
  private String telephoneNo;
  private String mobileNo;
  private String presentAddress;
  private String parmanetAddress;
  private int studentRegistrationId;
  private int academicYearId;
  private int classId;
  private int sectionId;
  private int groupId;
  private int academicYear;
  private int studentRollNo;
  private String className;
  private String sectionName;
  private String groupName;
  private Date registrationDate;
  private int registrationFeeId;
  private double feeAmount;
  private Date payDate;
  private int userId;
  private int roleId;
  private String userName;
  private String roleName;
  private String oldPassword;
  private String newPassword;
  private String confirmPassword;
  private boolean active;

  /** Creates a new instance of StudentBean */
  public NewStudentBean() {
  }

  public String getFatherName() {
    return FatherName;
  }

  public void setFatherName(String FatherName) {
    this.FatherName = FatherName;
  }

  public int getAcademicYear() {
    return academicYear;
  }

  public void setAcademicYear(int academicYear) {
    this.academicYear = academicYear;
  }

  public int getAcademicYearId() {
    return academicYearId;
  }

  public void setAcademicYearId(int academicYearId) {
    this.academicYearId = academicYearId;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
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

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public double getFeeAmount() {
    return feeAmount;
  }

  public void setFeeAmount(double feeAmount) {
    this.feeAmount = feeAmount;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  public String getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
  }

  public String getMotherName() {
    return motherName;
  }

  public void setMotherName(String motherName) {
    this.motherName = motherName;
  }

  public String getParmanetAddress() {
    return parmanetAddress;
  }

  public void setParmanetAddress(String parmanetAddress) {
    this.parmanetAddress = parmanetAddress;
  }

  public Date getPayDate() {
    return payDate;
  }

  public void setPayDate(Date payDate) {
    this.payDate = payDate;
  }

  public UploadedFile getPhoto() {
    return photo;
  }

  public void setPhoto(UploadedFile photo) {
    this.photo = photo;
  }

  public String getPresentAddress() {
    return presentAddress;
  }

  public void setPresentAddress(String presentAddress) {
    this.presentAddress = presentAddress;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public int getRegistrationFeeId() {
    return registrationFeeId;
  }

  public void setRegistrationFeeId(int registrationFeeId) {
    this.registrationFeeId = registrationFeeId;
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

  public int getStudentRegistrationId() {
    return studentRegistrationId;
  }

  public void setStudentRegistrationId(int studentRegistrationId) {
    this.studentRegistrationId = studentRegistrationId;
  }

  public int getStudentRollNo() {
    return studentRollNo;
  }

  public void setStudentRollNo(int studentRollNo) {
    this.studentRollNo = studentRollNo;
  }

  public String getTelephoneNo() {
    return telephoneNo;
  }

  public void setTelephoneNo(String telephoneNo) {
    this.telephoneNo = telephoneNo;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
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

  public int getRoleId() {
    return roleId;
  }

  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
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

  public List<NewStudentBean> getAllUserType() {
    String query = "SELECT role_id, role_name FROM sch_user_role WHERE role_name NOT LIKE 'Admin';";
    List<NewStudentBean> list = new ArrayList<NewStudentBean>();
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        NewStudentBean urb = new NewStudentBean();
        urb.setRoleId(rs.getInt("role_id"));
        urb.setRoleName(rs.getString("role_name"));
        list.add(urb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(NewStudentBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<NewStudentBean> getAllAcademicYear() {
    String query = "SELECT * FROM sch_academic_year";
    List<NewStudentBean> list = new ArrayList<NewStudentBean>();
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        NewStudentBean ayb = new NewStudentBean();
        ayb.setAcademicYearId(rs.getInt("acyid"));
        ayb.setAcademicYear(rs.getInt("acyear"));
        list.add(ayb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(NewStudentBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<NewStudentBean> getAllClass() {
    String query = "SELECT class_id, class_name FROM sch_class_name;";
    List<NewStudentBean> list = new ArrayList<NewStudentBean>();
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        NewStudentBean cb = new NewStudentBean();
        cb.setClassId(rs.getInt("class_id"));
        cb.setClassName(rs.getString("class_name"));
        list.add(cb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(NewStudentBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<NewStudentBean> getAllSection() {
    String query = "SELECT section_id, section_name FROM sch_section_name WHERE acyid=? AND class_id=?;";
    List<NewStudentBean> list = new ArrayList<NewStudentBean>();
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ps.setInt(1, getAcademicYearId());
      ps.setInt(2, getClassId());
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        NewStudentBean sb = new NewStudentBean();
        sb.setSectionId(rs.getInt("section_id"));
        sb.setSectionName(rs.getString("section_name"));
        list.add(sb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(NewStudentBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<NewStudentBean> getAllGroup() {
    String query = "SELECT group_id, group_name FROM sch_std_group;";
    List<NewStudentBean> list = new ArrayList<NewStudentBean>();
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        NewStudentBean gb = new NewStudentBean();
        gb.setGroupId(rs.getInt("group_id"));
        gb.setGroupName(rs.getString("group_name"));
        list.add(gb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(NewStudentBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
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
      Logger.getLogger(NewStudentBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
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

  //To get Registraion Id
  public void doSetRegistrationId() {
    String query = "SELECT MAX(last_insert_id(reg_id)) as reg_id FROM sch_std_registration";
    try {
      Connection con = DBConnect.getConnection();
      Statement st = con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery(query);
      while (rs.next()) {
        setStudentRegistrationId(rs.getInt("reg_id"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // To save contact Information
  public void saveContactInfo() {
    String query = "INSERT INTO school.sch_std_contact_info"
            + " (std_id, tel_no, mobile_no, present_addr, parmanent_addr)"
            + " VALUES(?,?,?,?,?);";
    try {
      Connection con = DBConnect.getConnection();
      PreparedStatement ps = con.prepareStatement(query);
      ps.setInt(1, this.getStudentId());
      ps.setString(2, this.getTelephoneNo());
      ps.setString(3, this.getMobileNo());
      ps.setString(4, this.getPresentAddress());
      ps.setString(5, this.getParmanetAddress());
      int i = ps.executeUpdate();
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }

  //To Save Student Registration Information
  public boolean saveRegistrationInfo() {
    boolean returnValue = false;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String d1 = df.format(this.getRegistrationDate());

    String sql = "insert into sch_std_registration (std_id, acyid, section_id, group_id, reg_date, std_roll_no) values(?,?,?,?,?,?)";
    try {
      Connection con = DBConnect.getConnection();
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setInt(1, this.getStudentId());
      ps.setInt(2, this.getAcademicYearId());
      ps.setInt(3, this.getSectionId());
      ps.setInt(4, this.getGroupId());
      ps.setString(5, d1);
      ps.setInt(6, this.getStudentRollNo());
      int i = ps.executeUpdate();
      if (i > 0) {
        returnValue = true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return returnValue;
  }

///To Save Registration Free 
  public void saveRegistrationFree() {

//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        String d1 = df.format(this.getFreepaydate());
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String d1 = df.format(this.getRegistrationDate());
    String sql = "insert into sch_reg_fee (reg_id, fee_amount, pay_date) values(?,?,?)";
    try {
      Connection con = DBConnect.getConnection();
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setInt(1, this.getStudentRegistrationId());
      ps.setDouble(2, this.getFeeAmount());
      ps.setString(3, d1);
      // ps.setInt(4, userid);
      int i = ps.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "All data save successfully");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public boolean saveStudentInfo() {
    boolean returnValue = false;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String dob = df.format(this.getDateOfBirth());

    String sql = "INSERT INTO sch_student_info(std_name, std_father_name, "
            + "std_mother_name, std_age, std_gender, std_dob, std_photo, std_status,user_id) "
            + "VALUES(?,?,?,?,?,?,?,?,?);";
    PreparedStatement ps;
    if (this.getPhoto() != null) {
      try {
        InputStream streamFile = this.getPhoto().getInputstream();
        ps = DBConnect.getConnection().prepareStatement(sql);
        ps.setString(1, this.getStudentName());
        ps.setString(2, this.getFatherName());
        ps.setString(3, this.getMotherName());
        ps.setInt(4, this.getAge());
        ps.setString(5, this.getGender());
        ps.setString(6, dob);
        ps.setBinaryStream(7, streamFile, this.getPhoto().getSize());
        ps.setBoolean(8, this.isStdStatus());
        ps.setInt(9, getUserId());
        int i = ps.executeUpdate();
        if (i > 0) {
          returnValue = true;
        }
      } catch (IOException ex) {
        Logger.getLogger(NewStudentBean.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SQLException ex) {
        Logger.getLogger(NewStudentBean.class.getName()).log(Level.SEVERE, null, ex);
      }
    } else {
      FacesMessage msg = new FacesMessage("Please select image!!");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    return returnValue;
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
          Logger.getLogger(NewStudentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Password does match with confirm password");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    }
    return returnValue;
  }

  public void doInsert() {
    
    if (this.getPhoto() != null) {
  boolean isSuccess = saveUserInfo();
     if (isSuccess) {
      doSetUserId();
    isSuccess = saveStudentInfo();
      if (isSuccess) {
        doSetStudentId();
        saveContactInfo();
        isSuccess = saveRegistrationInfo();
        if (isSuccess) {
          doSetRegistrationId();
          saveRegistrationFree();
          clear();
        }
      }
    }
    } else {
      FacesMessage msg = new FacesMessage("Please select image!!");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }    
  }

  private boolean isConfirm() {
    boolean b = false;
    if (getNewPassword().equals(getConfirmPassword())) {
      b = true;
    }
    return b;
  }
  void clear(){
    setAcademicYear(0);
    setAcademicYearId(0);
    setAge(0);
    setClassId(0);
    setClassName(null);
    setConfirmPassword(null);
    setDateOfBirth(null);
    setFatherName(null);
    setFeeAmount(0);
    setGender(null);
    setGroupId(0);
    setGroupName(null);
    setMobileNo(null);
    setMotherName(null);
    setNewPassword(null);
    setParmanetAddress(null);
    setPayDate(null);
    setPresentAddress(null);
    setRegistrationDate(null);
    setRegistrationFeeId(0);
    setRoleId(0);
    setRoleName(null);
    setSectionId(0);
    setSectionName(null);
    setStudentId(0);
    setStudentName(null);
    setStudentRegistrationId(0);
    setStudentRollNo(0);
    setTelephoneNo(null);
    setUserId(0);
    setUserName(null);
  }
}
