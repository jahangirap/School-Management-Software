/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import school.util.DBConnect;
import school.util.Duplicate;
import school.util.LoginUtil;

/**
 *
 * @author MASHUK
 */
public class UserBean implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private int userId;
  private int roleId;
  private String userName;
  private String roleName;
  private String oldPassword;
  private String newPassword;
  private String confirmPassword;
  private boolean active;

  /** Creates a new instance of UserBean */
  public UserBean() {
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
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

  public List<UserBean> getAllRoleName() {
    List<UserBean> data = new ArrayList<UserBean>();
    String sql = "SELECT role_name FROM sch_user_role;";
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        UserBean ub = new UserBean();
        ub.setRoleName(rs.getString("role_name"));
        data.add(ub);
      }
    } catch (SQLException ex) {
      Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
  }

  public void insertData() {
    String sql = "insert into sch_user (role_id, uname, password, isactive) values ((select role_id from sch_user_role where role_name=?),?,?,?);";
    boolean isDuplicate = new Duplicate<String>().isDuplicate(getUserName(), getAllUserName());
    if (isDuplicate) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate User Name not allowed here");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    } else {
      if (isConfirm()) {
        try {
          PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
          ps.setString(1, this.getRoleName());
          ps.setString(2, this.getUserName());
          ps.setString(3, this.getNewPassword());
          ps.setBoolean(4, this.isActive());
          int i = ps.executeUpdate();
          if (i > 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "New User add Successfully");
            FacesContext.getCurrentInstance().addMessage(null, msg);
          } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save user data");
            FacesContext.getCurrentInstance().addMessage(null, msg);
          }
          clear();
        } catch (SQLException ex) {
          Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Password does match with confirm password");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    }
  }

  public void doSetUname() {
    HttpSession session = LoginUtil.getSession();
    String un = session.getAttribute("username").toString();
    setUserName(un);
  }

  public void doSetSUname() {
  }

  public void updatePassword() {

    if (isOldPasswordMatch()) {
      if (isConfirm()) {
        String sql = "update sch_user set password=? where uname=? and password=?;";
        HttpSession session = LoginUtil.getSession();
        String un = session.getAttribute("username").toString();
        setUserName(un);
        try {
          //System.out.println("before");
          PreparedStatement pst = DBConnect.getConnection().prepareStatement(sql);
          pst.setString(1, this.getNewPassword());
          pst.setString(2, this.getUserName());
          pst.setString(3, this.getOldPassword());
          int i = pst.executeUpdate();
          if (i > 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data Update Successfully");
            FacesContext.getCurrentInstance().addMessage(null, msg);
          } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to update data");
            FacesContext.getCurrentInstance().addMessage(null, msg);
          }
          clear();
        } catch (SQLException ex) {
          System.out.println("updateError--------" + ex.getMessage());
        }
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Password does match with confirm password");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } else {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Enter your correct old password");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
  }

  public void deleteUserData() {
    String sql = "delete from sch_user where uname=?;";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setString(1, this.getUserName());
      int i = ps.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "One row deleted");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to delete data");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (SQLException ex) {
      Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
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
      Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
  }

  public List<UserBean> getAllData() {
    List<UserBean> data = new ArrayList<UserBean>();
    String sql = "SELECT s.uname, s.password, s.isactive, r.role_name FROM sch_user s join sch_user_role r where s.role_id=r.role_id;";
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        UserBean ub = new UserBean();
        ub.setUserName(rs.getString("uname"));
        ub.setNewPassword(rs.getString("password"));
        ub.setRoleName(rs.getString("role_name"));
        ub.setActive(rs.getBoolean("isactive"));
        data.add(ub);
      }
    } catch (SQLException ex) {
      Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
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

  private boolean isOldPasswordMatch() {
    boolean b = false;
    String sql = "SELECT password FROM sch_user WHERE uname=? AND password=?;";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setString(1, getUserName());
      ps.setString(2, getOldPassword());
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        b = true;
      }
    } catch (SQLException ex) {
      Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return b;
  }

  void clear() {
    setNewPassword(null);
    setOldPassword(null);
    setConfirmPassword(null);
  }
}