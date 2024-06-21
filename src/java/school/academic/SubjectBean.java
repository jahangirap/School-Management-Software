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
public class SubjectBean implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private int subjectId;
  private int subjectCode;
  private int newSubjectCode;
  private String subjectName;

  /** Creates a new instance of SubjectBean */
  public SubjectBean() {
  }

  public int getSubjectCode() {
    return subjectCode;
  }

  public void setSubjectCode(int subjectCode) {
    this.subjectCode = subjectCode;
  }

  public int getNewSubjectCode() {
    return newSubjectCode;
  }

  public void setNewSubjectCode(int newSubjectCode) {
    this.newSubjectCode = newSubjectCode;
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
      Logger.getLogger(SubjectBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return codeList;
  }

  public void insertSubject() {
    boolean isDuplicate = new Duplicate<Integer>().isDuplicate(getSubjectCode(), getAllSubjectCodes());
    if (isDuplicate) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate Subject Code not allowed here");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    } else {
      try {
        String sql = "INSERT INTO sch_subject (subject_code, subject_name) values(?,?);";
        PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
        ps.setInt(1, this.getSubjectCode());
        ps.setString(2, this.getSubjectName());
        int i = ps.executeUpdate();
        if (i > 0) {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "New Subject add Successfully");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save data");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        clear();
      } catch (SQLException ex) {
        Logger.getLogger(SubjectBean.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public List<SubjectBean> getAllSubjectName() {
    List<SubjectBean> data = new ArrayList<SubjectBean>();
    String sql = "select * from school.sch_subject;";
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        SubjectBean cb = new SubjectBean();
        cb.setSubjectCode(rs.getInt("subject_code"));
        cb.setSubjectName(rs.getString("subject_name"));
        data.add(cb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(SubjectBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
  }

  public void deleteSubjectName() {
    String sql = "DELETE FROM sch_subject WHERE subject_code=? AND subject_name=?;";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setInt(1, this.getSubjectCode());
      ps.setString(2, this.getSubjectName());
      int i = ps.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "One row deleted");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to delete data");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (SQLException ex) {
      Logger.getLogger(SubjectBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  void clear() {
    setSubjectCode(0);
    setSubjectName(null);
  }
}
