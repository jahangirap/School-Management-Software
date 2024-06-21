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
public class GroupUpdate implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private int groupId;
  private String groupName;
  private String newGroupName;
  private boolean groupStatus;
  private GroupBean selectedRow;

  /** Creates a new instance of GroupUpdate */
  public GroupUpdate() {
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

  public boolean isGroupStatus() {
    return groupStatus;
  }

  public void setGroupStatus(boolean groupStatus) {
    this.groupStatus = groupStatus;
  }

  public String getNewGroupName() {
    return newGroupName;
  }

  public void setNewGroupName(String newGroupName) {
    this.newGroupName = newGroupName;
  }

  public GroupBean getSelectedRow() {
    return selectedRow;
  }

  public void setSelectedRow(GroupBean selectedRow) {
    this.selectedRow = selectedRow;
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
      Logger.getLogger(GroupUpdate.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
  }

  public void doSetSelectedRow() {
    setGroupName(getSelectedRow().getGroupName());
    setNewGroupName(getSelectedRow().getNewGroupName());
    setGroupStatus(getSelectedRow().isGroupStatus());
  }

  public void groupUpdateData() {
    String sql = "UPDATE sch_std_group SET group_name=?,group_status=? WHERE group_name=?;";
    //update school.sch_std_group set group_name='rahim' where group_name='rahim1';
    boolean isDuplicate = false;
    if (!getNewGroupName().equals(getGroupName())) {
      isDuplicate = new Duplicate<String>().isDuplicate(getNewGroupName(), getAllGroups());
    }     
    if (isDuplicate) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate Group Name not allowed here");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    } else {
      try {
        PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
        ps.setString(1, this.getNewGroupName());
        ps.setBoolean(2, this.isGroupStatus());
        ps.setString(3, this.getGroupName());
        int i = ps.executeUpdate();
        if (i > 0) {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data update successfully");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to update data");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        clear();
      } catch (SQLException ex) {
        Logger.getLogger(GroupUpdate.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  void clear() {
    setGroupName(null);
    setNewGroupName(null);
  }
}
