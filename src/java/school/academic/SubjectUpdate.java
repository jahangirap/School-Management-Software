/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.academic;

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
import school.util.Duplicate;

/**
 *
 * @author MASHUK
 */
public class SubjectUpdate implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private int subjectId;
  private int subjectCode;
  private int newSubjectCode;
  private String subjectName;
  private SubjectBean selectedRow;

  /** Creates a new instance of SubjectUpdate */
  public SubjectUpdate() {
  }

  public int getNewSubjectCode() {
    return newSubjectCode;
  }

  public void setNewSubjectCode(int newSubjectCode) {
    this.newSubjectCode = newSubjectCode;
  }

  public SubjectBean getSelectedRow() {
    return selectedRow;
  }

  public void setSelectedRow(SubjectBean selectedRow) {
    this.selectedRow = selectedRow;
  }

  public int getSubjectCode() {
    return subjectCode;
  }

  public void setSubjectCode(int subjectCode) {
    this.subjectCode = subjectCode;
  }

  public int getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(int subjectId) {
    this.subjectId = subjectId;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public void doSetSelectedRow() {
    setNewSubjectCode(getSelectedRow().getNewSubjectCode());
    setSubjectName(getSelectedRow().getSubjectName());
    setSubjectCode(getSelectedRow().getSubjectCode());
  }

  List<Integer> getAllSubjectCodes() {
    List<Integer> codeList = new ArrayList<Integer>();
    String query = "SELECT subject_code FROM sch_subject;";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        codeList.add(rs.getInt("subject_code"));
      }
    } catch (SQLException ex) {
      Logger.getLogger(SubjectUpdate.class.getName()).log(Level.SEVERE, null, ex);
    }
    return codeList;
  }

  public void updateSubjectName() {
    String sql = "UPDATE sch_subject SET subject_code=?, subject_name=? WHERE subject_code=?;";
    //update school.sch_std_group set group_name='rahim' where group_name='rahim1';
    boolean isDuplicate = false;
    if (getNewSubjectCode() != getSubjectCode()) {
      isDuplicate = new Duplicate<Integer>().isDuplicate(getNewSubjectCode(), getAllSubjectCodes());
    }
    if (isDuplicate) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate Academic Year not allowed here");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    } else {
      try {
        PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
        ps.setInt(1, getNewSubjectCode());
        ps.setString(2, getSubjectName());
        ps.setInt(3, getSubjectCode());
        int i = ps.executeUpdate();
        if (i > 0) {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data Update Successfully");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to update data");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        }
      } catch (SQLException ex) {
        Logger.getLogger(SubjectUpdate.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
