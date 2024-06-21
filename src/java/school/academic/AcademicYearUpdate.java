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
 * @author TCLD
 */
public class AcademicYearUpdate implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private AcademicYearBean selectedRow;
  private int acyid;
  private int acyear;
  private int newAcyear;
  private int numberOfExam;
  private boolean closed;

  /** Creates a new instance of AcademicYearUpdate */
  public AcademicYearUpdate() {
  }

  public int getAcyid() {
    return acyid;
  }

  public void setAcyid(int acyid) {
    this.acyid = acyid;
  }

  public int getNewAcyear() {
    return newAcyear;
  }

  public void setNewAcyear(int newAcyear) {
    this.newAcyear = newAcyear;
  }

  public int getAcyear() {
    return acyear;
  }

  public void setAcyear(int acyear) {
    this.acyear = acyear;
  }

  public boolean isClosed() {
    return closed;
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  public int getNumberOfExam() {
    return numberOfExam;
  }

  public void setNumberOfExam(int numberOfExam) {
    this.numberOfExam = numberOfExam;
  }

  public AcademicYearBean getSelectedRow() {
    return selectedRow;
  }

  public void setSelectedRow(AcademicYearBean selectedRow) {
    this.selectedRow = selectedRow;
  }

  public List<Integer> getAllAcyears() {
    List<Integer> list = new ArrayList<Integer>();
    String sql = "SELECT acyear FROM sch_academic_year;";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        int i = rs.getInt("acyear");
        list.add(i);
      }
    } catch (SQLException ex) {
      Logger.getLogger(AcademicYearBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public void doSetSelectedRow() {
    setAcyid(getSelectedRow().getAcyid());
    setAcyear(getSelectedRow().getAcyear());
    setNewAcyear(getSelectedRow().getAcyear());
    setNumberOfExam(getSelectedRow().getNumberOfExam());
    setClosed(getSelectedRow().isClosed());
  }

  public void updateData() {
    String sql = "UPDATE sch_academic_year SET acyear=?, no_of_exam=?, isclosed=? where acyid=?;";
    boolean isDuplicate = false;   
    if (getNewAcyear() != getAcyear()) {
     isDuplicate  = new Duplicate<Integer>().isDuplicate(getNewAcyear(), getAllAcyears());
    }     
    if (isDuplicate) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate Academic Year not allowed here");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    } else {
      try {        
        PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
        ps.setInt(1, getNewAcyear());
        ps.setInt(2, getNumberOfExam());
        ps.setBoolean(3, isClosed());
        ps.setInt(4, getAcyid());
        int i = ps.executeUpdate();
        if (i > 0) {
          clear();
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data update successfully");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to update data");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        }
      } catch (SQLException ex) {
        Logger.getLogger(AcademicYearBean.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

  }

  private void clear() {    
    setNewAcyear(0);
  }
}
