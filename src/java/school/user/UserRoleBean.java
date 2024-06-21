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
import school.util.DBConnect;
import school.util.Duplicate;

/**
 *
 * @author MASHUK
 */
public class UserRoleBean implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private int roleId;
  private String roleName;
  private String newRoleName;

  /** Creates a new instance of UserRoleBean */
  public UserRoleBean() {
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

  public String getNewRoleName() {
    return newRoleName;
  }

  public void setNewRoleName(String newRoleName) {
    this.newRoleName = newRoleName;
  }

  public void insertUserRole() {
    String sql = "INSERT INTO sch_user_role (role_name) VALUES(?);";
    boolean isDuplicate = new Duplicate<String>().isDuplicate(getRoleName(), getAllUserRoles());
    if (isDuplicate) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate Role Name not allowed here");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    } else {
      try {
        PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
        ps.setString(1, this.getRoleName());
        int i = ps.executeUpdate();
        if (i > 0) {          
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "New User Role add Successfully");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save data");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        clear();
      } catch (SQLException ex) {
        Logger.getLogger(UserRoleBean.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public List<String> getAllUserRoles() {
    List<String> list = new ArrayList<String>();
    String sql = "SELECT * FROM sch_user_role;";
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        list.add(rs.getString("role_name"));
      }
    } catch (SQLException ex) {
      Logger.getLogger(UserRoleBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<UserRoleBean> getAllUserRoleName() {
    List<UserRoleBean> data = new ArrayList<UserRoleBean>();
    String sql = "SELECT * FROM sch_user_role;";
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        UserRoleBean ub = new UserRoleBean();
        ub.setRoleName(rs.getString("role_name"));
        data.add(ub);
      }
    } catch (SQLException ex) {
      Logger.getLogger(UserRoleBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
  }

  public void updateUserRole() {
    String sql = "UPDATE sch_user_role SET role_name=? WHERE role_name=?;";
    boolean isDuplicate = new Duplicate<String>().isDuplicate(getNewRoleName(), getAllUserRoles());
    if (isDuplicate) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate Role Name not allowed here");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    } else {
      try {
        PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
        ps.setString(1, this.getNewRoleName());
        ps.setString(2, this.getRoleName());
        int i = ps.executeUpdate();
        if (i > 0) {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data Update Successfully");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to update data");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        clear();
      } catch (SQLException ex) {
        Logger.getLogger(UserRoleBean.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public void deleteUserRole() {
    String sql = "DELETE FROM sch_user_role WHERE role_name=?;";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setString(1, this.getRoleName());
      int i = ps.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "One row deleted");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to delete data");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (SQLException ex) {
      Logger.getLogger(UserRoleBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  void clear() {
    setNewRoleName(null);
    setRoleName(null);
  }
}
