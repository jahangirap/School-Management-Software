/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.academic;

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
public class GroupBean implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private int groupId;
  private String groupName;
  private String newGroupName;
  private boolean groupStatus;

  /** Creates a new instance of GroupBean */
  public GroupBean() {
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

  public String getNewGroupName() {
    return newGroupName;
  }

  public void setNewGroupName(String newGroupName) {
    this.newGroupName = newGroupName;
  }

  public boolean isGroupStatus() {
    return groupStatus;
  }

  public void setGroupStatus(boolean groupStatus) {
    this.groupStatus = groupStatus;
  }

  public void insertGroupName() {
    String sql = "insert into sch_std_group (group_name, group_status) values(?,?);";
    boolean isDuplicate = new Duplicate<String>().isDuplicate(getGroupName(), getAllGroups());
    if (isDuplicate) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate Group Name not allowed here");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    } else {
      try {
        PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
        ps.setString(1, this.getGroupName());
        ps.setBoolean(2, true);
        int i = ps.executeUpdate();
        if (i > 0) {          
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "New Group add Successfully");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save data");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        clear();
      } catch (SQLException ex) {
        Logger.getLogger(GroupBean.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

  }

  public List<String> getAllGroups() {
    List<String> data = new ArrayList<String>();
    String sql = "SELECT group_id, group_name, group_status FROM sch_std_group;";
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        data.add(rs.getString("group_name"));
      }
    } catch (SQLException ex) {
      Logger.getLogger(GroupBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
  }

  public List<GroupBean> getAllGroupName() {
    List<GroupBean> data = new ArrayList<GroupBean>();
    String sql = "SELECT group_id, group_name, group_status FROM sch_std_group;";
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        GroupBean cb = new GroupBean();
        cb.setGroupId(rs.getInt("group_id"));
        cb.setGroupName(rs.getString("group_name"));
        cb.setGroupStatus(rs.getBoolean("group_status"));
        data.add(cb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(GroupBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
  }


  public void groupDeleteData() {
    String sql = "delete from sch_std_group where group_name=?;";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setString(1, this.getGroupName());
      int i = ps.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Group : " + this.getGroupName() + " has deleted");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to delete data");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (SQLException ex) {
      Logger.getLogger(GroupBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
 void clear(){
   setGroupName(null);
   setNewGroupName(null);
 }
}
