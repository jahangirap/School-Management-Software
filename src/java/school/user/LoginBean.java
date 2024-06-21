/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import school.util.DBConnect;
import school.util.LoginUtil;

/**
 *
 * @author MASHUK
 */
public class LoginBean implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private String userName;
  private String password;
  private String roleName;
  private int roleId;
  private boolean showLogin = true;
  private boolean showLogout;
  private String name;

  /** Creates a new instance of LoginBean */
  public LoginBean() {
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isShowLogin() {
    return showLogin;
  }

  public void setShowLogin(boolean showLogin) {
    this.showLogin = showLogin;
  }

  public boolean isShowLogout() {
    return showLogout;
  }

  public void setShowLogout(boolean showLogout) {
    this.showLogout = showLogout;
  }

  public String doLogin() {
    String returnPage = "index";
    String query = "SELECT u.user_id, r.role_name, u.isactive "
            + "FROM sch_user u "
            + "JOIN sch_user_role r "
            + "WHERE uname=? AND password=? AND u.role_id = r.role_id;";
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ps.setString(1, getUserName());
      ps.setString(2, getPassword());
      rs = ps.executeQuery();
      if (rs.next()) {
        boolean isActive = rs.getBoolean("isactive");
        String userType = rs.getString("role_name");
        int userid = rs.getInt("user_id");
        if (isActive) {
          if (userType.equalsIgnoreCase("Admin")) {
            HttpSession session = LoginUtil.getSession();
            session.setAttribute("username", getUserName());
            session.setAttribute("roleName", userType);
            setRoleName(userType);
            setShowLogout(true);
            returnPage = "admin/adminPanel?faces-redirect=true";
          }
          if (userType.equalsIgnoreCase("Teacher")) {
            HttpSession session = LoginUtil.getSession();
            session.setAttribute("username", getUserName());
            session.setAttribute("roleName", userType);
            setRoleName(userType);
            String q = "SELECT teacher_name FROM sch_teacher_info WHERE user_id=?;";
            PreparedStatement ps2 = DBConnect.getConnection().prepareStatement(q);
            ps2.setInt(1, userid);
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
              setName(rs2.getString("teacher_name"));
            }
            setShowLogout(true);
            returnPage = "teacher/teacherHome?faces-redirect=true";
          }
          if (userType.equalsIgnoreCase("Student")) {
            HttpSession session = LoginUtil.getSession();
            session.setAttribute("username", getUserName());
            session.setAttribute("roleName", userType);
            setRoleName(userType);
            String q1 = "SELECT std_name FROM sch_student_info WHERE user_id=?;";
            PreparedStatement ps3 = DBConnect.getConnection().prepareStatement(q1);
            ps3.setInt(1, userid);
            ResultSet rs3 = ps3.executeQuery();
            if (rs3.next()) {
              setName(rs3.getString("std_name"));
            }
            setShowLogout(true);
            returnPage = "student/studentHome?faces-redirect=true";
          }
        } else {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "User is not active");
          FacesContext.getCurrentInstance().addMessage("warn", msg);
        }
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Invalid Username or password");
        FacesContext.getCurrentInstance().addMessage("warn", msg);
      }
    } catch (SQLException ex) {
      Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        ps.close();
        rs.close();
        clear();
      } catch (SQLException ex) {
        Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return returnPage;
  }

  public String goMyHome() {
    String returnPage = null;
    String usertype = getRoleName();
    if (usertype.equalsIgnoreCase("admin")) {
      returnPage = "adminhome";
    } else if (usertype.equalsIgnoreCase("teacher")) {
      returnPage = "teacherhome";   
    } else if (usertype.equalsIgnoreCase("student")) {
      returnPage = "studenthome";
    } else {
    }
    return returnPage;
  }

  public String logout() {
    HttpSession session = LoginUtil.getSession();
    session.invalidate();
    setShowLogin(true);
    setShowLogout(false);
    return "logout";
  }

  void clear() {
    setUserName(null);
    setPassword(null);
  }
}
