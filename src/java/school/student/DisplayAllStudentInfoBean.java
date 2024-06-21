/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.student;

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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.UploadedFile;
import school.util.DBConnect;
import school.util.LoginUtil;

/**
 *
 * @author MASHUK
 */
@ManagedBean
@SessionScoped
public class DisplayAllStudentInfoBean implements java.io.Serializable {

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
    private String academicYear;
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
    private int contactId;
    private int registrationId;
    private int feeId;
    private int uId;

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String FatherName) {
        this.FatherName = FatherName;
    }

//    public int getAcademicYear() {
//        return academicYear;
//    }
//
//    public void setAcademicYear(int academicYear) {
//        this.academicYear = academicYear;
//    }
    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
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

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
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

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
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

    public int getFeeId() {
        return feeId;
    }

    public void setFeeId(int feeId) {
        this.feeId = feeId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String goEditPage() {
        return "editStudent";
    }

    public List<DisplayAllStudentInfoBean> getAllStudentData() {
        String sql = "SELECT sch_student_info.std_id,\n"
                + "       sch_student_info.std_name,\n"
                + "       sch_student_info.std_father_name,\n"
                + "       sch_student_info.std_mother_name,\n"
                + "       sch_student_info.std_age,\n"
                + "       sch_student_info.std_gender,\n"
                + "       sch_student_info.std_dob,\n"
                + "       sch_student_info.std_photo,\n"
                + "       sch_std_contact_info.contact_id,\n"
                + "       sch_std_contact_info.tel_no,\n"
                + "       sch_std_contact_info.mobile_no,\n"
                + "       sch_std_contact_info.present_addr,\n"
                + "       sch_std_contact_info.parmanent_addr,\n"
                + "       sch_std_registration.reg_id,\n"
                + "       sch_academic_year.acyear,\n"
                + "       sch_section_name.section_name,\n"
                + "       sch_std_group.group_name,\n"
                + "       sch_class_name.class_name,\n"
                + "       sch_std_registration.reg_date,\n"
                + "       sch_std_registration.std_roll_no,\n"
                + "       sch_reg_fee.fee_amount,\n"
                + "       sch_reg_fee.pay_date,\n"
                + "       sch_user.user_id,\n"
                + "       sch_user.uname,\n"
                + "       sch_user.password,\n"
                + "       sch_reg_fee.fee_id\n"
                + "  FROM    (   (   (   (   (   (   (   school.sch_section_name sch_section_name\n"
                + "                                   INNER JOIN\n"
                + "                                      school.sch_academic_year sch_academic_year\n"
                + "                                   ON (sch_section_name.acyid =\n"
                + "                                          sch_academic_year.acyid))\n"
                + "                               INNER JOIN\n"
                + "                                  school.sch_std_registration sch_std_registration\n"
                + "                               ON (sch_std_registration.acyid =\n"
                + "                                      sch_academic_year.acyid)\n"
                + "                                  AND(sch_std_registration.section_id =\n"
                + "                                         sch_section_name.section_id))\n"
                + "                           INNER JOIN\n"
                + "                              school.sch_student_info sch_student_info\n"
                + "                           ON (sch_std_registration.std_id =\n"
                + "                                  sch_student_info.std_id))\n"
                + "                       INNER JOIN\n"
                + "                          school.sch_std_contact_info sch_std_contact_info\n"
                + "                       ON (sch_std_contact_info.std_id =\n"
                + "                              sch_student_info.std_id))\n"
                + "                   INNER JOIN\n"
                + "                      school.sch_user sch_user\n"
                + "                   ON (sch_student_info.user_id = sch_user.user_id))\n"
                + "               INNER JOIN\n"
                + "                  school.sch_reg_fee sch_reg_fee\n"
                + "               ON (sch_reg_fee.reg_id = sch_std_registration.reg_id))\n"
                + "           INNER JOIN\n"
                + "              school.sch_class_name sch_class_name\n"
                + "           ON (sch_section_name.class_id = sch_class_name.class_id))\n"
                + "       INNER JOIN\n"
                + "          school.sch_std_group sch_std_group\n"
                + "       ON (sch_std_registration.group_id = sch_std_group.group_id) order by std_name";
        List<DisplayAllStudentInfoBean> list = new ArrayList<DisplayAllStudentInfoBean>();
        try {
            Connection con = DBConnect.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                DisplayAllStudentInfoBean d = new DisplayAllStudentInfoBean();
                d.setStudentId(rs.getInt("std_id"));
                d.setStudentName(rs.getString("std_name"));
                d.setFatherName(rs.getString("std_father_name"));
                d.setMotherName(rs.getString("std_mother_name"));
                d.setGender(rs.getString("std_gender"));
                d.setDateOfBirth(rs.getDate("std_dob"));
                d.setAge(rs.getInt("std_age"));
                /*start contact information*/
                d.setContactId(rs.getInt("contact_id"));
                d.setTelephoneNo(rs.getString("tel_no"));
                d.setMobileNo(rs.getString("mobile_no"));
                d.setPresentAddress(rs.getString("present_addr"));
                d.setParmanetAddress(rs.getString("parmanent_addr"));
                /* start Student  Academic infomation */
                d.setRegistrationId(rs.getInt("reg_id"));
                d.setAcademicYear(rs.getString("acyear"));
                d.setClassName(rs.getString("class_name"));
                d.setSectionName(rs.getString("section_name"));
                d.setGroupName(rs.getString("group_name"));
                d.setStudentRollNo(rs.getInt("std_roll_no"));
                /* start Other Information */
                d.setFeeId(rs.getInt("fee_id"));
                d.setuId(rs.getInt("user_id"));
                d.setRegistrationDate(rs.getDate("reg_date"));
                d.setFeeAmount(rs.getInt("fee_amount"));
                d.setPayDate(rs.getDate("pay_date"));
                d.setUserName(rs.getString("uname"));
                list.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;


    }
    // student-std_id, std_name, std_father_name, std_mother_name, std_age, std_gender, std_dob, std_photo, std_status, user_id

    /* To get All Academic year and academic year id*/
    public List<DisplayAllStudentInfoBean> getAllAcademicYear() {
        String query = "SELECT * FROM sch_academic_year";
        List<DisplayAllStudentInfoBean> list = new ArrayList<DisplayAllStudentInfoBean>();
        PreparedStatement ps;
        try {
            ps = DBConnect.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DisplayAllStudentInfoBean ayb = new DisplayAllStudentInfoBean();
                ayb.setAcademicYearId(rs.getInt("acyid"));
                ayb.setAcademicYear(rs.getString("acyear"));
                list.add(ayb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisplayAllStudentInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /* To get all class and Class id*/
    public List<DisplayAllStudentInfoBean> getAllClass() {
        String query = "SELECT class_id, class_name FROM sch_class_name;";
        List<DisplayAllStudentInfoBean> list = new ArrayList<DisplayAllStudentInfoBean>();
        PreparedStatement ps;
        try {
            ps = DBConnect.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DisplayAllStudentInfoBean cb = new DisplayAllStudentInfoBean();
                cb.setClassId(rs.getInt("class_id"));
                cb.setClassName(rs.getString("class_name"));
                list.add(cb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisplayAllStudentInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /*To get All section and section id*/
    public List<DisplayAllStudentInfoBean> getAllSection() {
        String query = "SELECT section_id, section_name FROM sch_section_name WHERE acyid=? AND class_id=?;";
        List<DisplayAllStudentInfoBean> list = new ArrayList<DisplayAllStudentInfoBean>();
        PreparedStatement ps;
        try {
            ps = DBConnect.getConnection().prepareStatement(query);
            ps.setInt(1, getAcademicYearId());
            ps.setInt(2, getClassId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DisplayAllStudentInfoBean sb = new DisplayAllStudentInfoBean();
                sb.setSectionId(rs.getInt("section_id"));
                sb.setSectionName(rs.getString("section_name"));
                list.add(sb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisplayAllStudentInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    /* To get all Grpoup    and Gtpoup id*/

    public List<DisplayAllStudentInfoBean> getAllGroup() {
        String query = "SELECT group_id, group_name FROM sch_std_group;";
        List<DisplayAllStudentInfoBean> list = new ArrayList<DisplayAllStudentInfoBean>();
        PreparedStatement ps;
        try {
            ps = DBConnect.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DisplayAllStudentInfoBean gb = new DisplayAllStudentInfoBean();
                gb.setGroupId(rs.getInt("group_id"));
                gb.setGroupName(rs.getString("group_name"));
                list.add(gb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisplayAllStudentInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /* Update basic Information*/
    public void updateBasicInfo() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dob = df.format(this.getDateOfBirth());

        String sql = "update sch_student_info set std_name=?, std_father_name=?, std_mother_name=?, std_age=?, std_gender=?, std_dob=? where std_id=? ";
        try {

            PreparedStatement ps = ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setString(1, this.getStudentName());
            ps.setString(2, this.getFatherName());
            ps.setString(3, this.getMotherName());
            ps.setInt(4, this.getAge());
            ps.setString(5, this.getGender());
            ps.setString(6, dob);
            ps.setInt(7, this.getStudentId());
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("BasicInfo is Updated!", "click Go Back"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*To Update Contact Information*/
    public void updateContactInfo() {

        String sql = "update sch_std_contact_info set tel_no=?, mobile_no=?, present_addr=?, parmanent_addr=? where contact_id=?";
        try {
            PreparedStatement ps = ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setString(1, this.getTelephoneNo());
            ps.setString(2, this.getMobileNo());
            ps.setString(3, this.getPresentAddress());
            ps.setString(4, this.getParmanetAddress());
            ps.setInt(5, this.getContactId());
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("ContactInfo is Updated!", "click Go Back"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*To update Academic Information*/
    public void updateAcademicInfo() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String reg = df.format(this.getRegistrationDate());
        String sql = "update sch_std_registration set acyid=?, section_id=?, group_id=?, reg_date=?, std_roll_no=? where reg_id=?";

        try {
            PreparedStatement ps = ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setInt(1, this.getAcademicYearId());
            ps.setInt(2, this.getSectionId());
            ps.setInt(3, this.getGroupId());
            ps.setString(4, reg);
            ps.setInt(5, this.getStudentRollNo());
            ps.setInt(6, this.getRegistrationId());
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Academic Year is Updated!", "click Go Back"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* To Update Fee Information*/

    public void updateFeeInfo() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String pay = df.format(this.getPayDate());

        String sql = "update sch_reg_fee set fee_amount=?, pay_date=? where fee_id=?";

        try {
            PreparedStatement ps = ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setDouble(1, this.getFeeAmount());
            ps.setString(2, pay);
            ps.setInt(3, this.getFeeId());
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Feee Infomation is Updated!", "click Go Back"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*To Update user InFormation*/

    public void updateUserInfo() {
        String sql = "update sch_user set uname=?where user_id=?";
        try {
            PreparedStatement ps = ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setString(1, this.getUserName());
            //ps.setString(2, this.getNewPassword());
            ps.setInt(2, this.getuId());
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("User Information is Updated", "click Go Back"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<DisplayAllStudentInfoBean> getStudentProfile() {

        //String sql = "SELECT s.std_id, s.std_name, s.std_father_name, s.std_mother_name, s.std_age, s.std_gender, s.std_dob, s.std_status, s.user_id, c.contact_id, c.std_id, c.tel_no, c.mobile_no, c.present_addr, c.parmanent_addr FROM sch_student_info s inner join sch_std_contact_info c on (s.std_id=c.std_id) where s.user_id=(select u.user_id from sch_user u where u.uname=?);";
      String sql = "SELECT sch_student_info.std_id,\n"
                + "       sch_student_info.std_name,\n"
                + "       sch_student_info.std_father_name,\n"
                + "       sch_student_info.std_mother_name,\n"
                + "       sch_student_info.std_age,\n"
                + "       sch_student_info.std_gender,\n"
                + "       sch_student_info.std_dob,\n"
                + "       sch_student_info.std_photo,\n"
                + "       sch_std_contact_info.contact_id,\n"
                + "       sch_std_contact_info.tel_no,\n"
                + "       sch_std_contact_info.mobile_no,\n"
                + "       sch_std_contact_info.present_addr,\n"
                + "       sch_std_contact_info.parmanent_addr,\n"
                + "       sch_std_registration.reg_id,\n"
                + "       sch_academic_year.acyear,\n"
                + "       sch_section_name.section_name,\n"
                + "       sch_std_group.group_name,\n"
                + "       sch_class_name.class_name,\n"
                + "       sch_std_registration.reg_date,\n"
                + "       sch_std_registration.std_roll_no,\n"
                + "       sch_reg_fee.fee_amount,\n"
                + "       sch_reg_fee.pay_date,\n"
                + "       sch_user.user_id,\n"
                + "       sch_user.uname,\n"
                + "       sch_user.password,\n"
                + "       sch_reg_fee.fee_id\n"
                + "  FROM    (   (   (   (   (   (   (   school.sch_section_name sch_section_name\n"
                + "                                   INNER JOIN\n"
                + "                                      school.sch_academic_year sch_academic_year\n"
                + "                                   ON (sch_section_name.acyid =\n"
                + "                                          sch_academic_year.acyid))\n"
                + "                               INNER JOIN\n"
                + "                                  school.sch_std_registration sch_std_registration\n"
                + "                               ON (sch_std_registration.acyid =\n"
                + "                                      sch_academic_year.acyid)\n"
                + "                                  AND(sch_std_registration.section_id =\n"
                + "                                         sch_section_name.section_id))\n"
                + "                           INNER JOIN\n"
                + "                              school.sch_student_info sch_student_info\n"
                + "                           ON (sch_std_registration.std_id =\n"
                + "                                  sch_student_info.std_id))\n"
                + "                       INNER JOIN\n"
                + "                          school.sch_std_contact_info sch_std_contact_info\n"
                + "                       ON (sch_std_contact_info.std_id =\n"
                + "                              sch_student_info.std_id))\n"
                + "                   INNER JOIN\n"
                + "                      school.sch_user sch_user\n"
                + "                   ON (sch_student_info.user_id = sch_user.user_id))\n"
                + "               INNER JOIN\n"
                + "                  school.sch_reg_fee sch_reg_fee\n"
                + "               ON (sch_reg_fee.reg_id = sch_std_registration.reg_id))\n"
                + "           INNER JOIN\n"
                + "              school.sch_class_name sch_class_name\n"
                + "           ON (sch_section_name.class_id = sch_class_name.class_id))\n"
                + "       INNER JOIN\n"
                + "          school.sch_std_group sch_std_group\n"
                + "       ON (sch_std_registration.group_id = sch_std_group.group_id) where uname=?;"; 
      List<DisplayAllStudentInfoBean> list = new ArrayList<DisplayAllStudentInfoBean>();
        PreparedStatement ps;
        HttpSession session = LoginUtil.getSession();
        String un = session.getAttribute("username").toString();
        try {
            ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setString(1, un);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DisplayAllStudentInfoBean d = new DisplayAllStudentInfoBean();
                d.setStudentId(rs.getInt("std_id"));
                d.setStudentName(rs.getString("std_name"));
                d.setFatherName(rs.getString("std_father_name"));
                d.setMotherName(rs.getString("std_mother_name"));
                d.setGender(rs.getString("std_gender"));
                d.setDateOfBirth(rs.getDate("std_dob"));
                d.setAge(rs.getInt("std_age"));
                /*start contact information*/
                d.setContactId(rs.getInt("contact_id"));
                d.setTelephoneNo(rs.getString("tel_no"));
                d.setMobileNo(rs.getString("mobile_no"));
                d.setPresentAddress(rs.getString("present_addr"));
                d.setParmanetAddress(rs.getString("parmanent_addr"));
                /* start Student  Academic infomation */
                d.setRegistrationId(rs.getInt("reg_id"));
                d.setAcademicYear(rs.getString("acyear"));
                d.setClassName(rs.getString("class_name"));
                d.setSectionName(rs.getString("section_name"));
                d.setGroupName(rs.getString("group_name"));
                d.setStudentRollNo(rs.getInt("std_roll_no"));
                /* start Other Information */
                d.setFeeId(rs.getInt("fee_id"));
                d.setuId(rs.getInt("user_id"));
                d.setRegistrationDate(rs.getDate("reg_date"));
                d.setFeeAmount(rs.getInt("fee_amount"));
                d.setPayDate(rs.getDate("pay_date"));
                d.setUserName(rs.getString("uname"));
                list.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }
}
