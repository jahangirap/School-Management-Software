/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.academic;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.primefaces.event.RowEditEvent;
import school.util.DBConnect;
import school.util.Duplicate;

/**
 *
 * @author MASHUK
 */
public class AcademicYearBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private int acyid;
  private int acyear;
  private int newAcyear;
  private int numberOfExam;
  private boolean closed;
  private int acyearNo;
  private AcademicYearBean selectedRow;

  /** Creates a new instance of AcademicYearBean */
  public AcademicYearBean() {
  }
// <editor-fold defaultstate="collapsed" desc="Getter and Setter">

  public int getAcyear() {
    return acyear;
  }

  public void setAcyear(int acyear) {
    this.acyear = acyear;
  }

  public int getAcyid() {
    return acyid;
  }

  public void setAcyid(int acyid) {
    this.acyid = acyid;
  }

  public boolean isClosed() {
    return closed;
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  public int getNewAcyear() {
    return newAcyear;
  }

  public void setNewAcyear(int newAcyear) {
    this.newAcyear = newAcyear;
  }

  public int getNumberOfExam() {
    return numberOfExam;
  }

  public void setNumberOfExam(int numberOfExam) {
    this.numberOfExam = numberOfExam;
  }

  public int getAcyearNo() {
    return acyearNo;
  }

  public void setAcyearNo(int acyearNo) {
    this.acyearNo = acyearNo;
  }

  public AcademicYearBean getSelectedRow() {
    return selectedRow;
  }

  public void setSelectedRow(AcademicYearBean selectedRow) {
    this.selectedRow = selectedRow;
  }
// </editor-fold>

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

  public void insertData() {
    String sql = "INSERT INTO sch_academic_year (acyear, no_of_exam, isclosed) VALUES (?,?,?)";
    try {
      PreparedStatement pts = DBConnect.getConnection().prepareStatement(sql);
      pts.setInt(1, this.getAcyear());
      pts.setInt(2, this.getNumberOfExam());
      pts.setBoolean(3, this.isClosed());
      int i = pts.executeUpdate();
      if (i > 0) {
        clear();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data Saved Successfully");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save data");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (SQLException ex) {
      Logger.getLogger(AcademicYearBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public List<AcademicYearBean> getAllAcademicYear() {
    List<AcademicYearBean> data = new ArrayList<AcademicYearBean>();

    String sql = "SELECT acyid, acyear, no_of_exam, isclosed FROM sch_academic_year ORDER BY acyear DESC;";
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        AcademicYearBean acb = new AcademicYearBean();
        acb.setAcyearNo(rs.getRow());
        acb.setAcyid(rs.getInt("acyid"));
        acb.setAcyear(rs.getInt("acyear"));
        acb.setNumberOfExam(rs.getInt("no_of_exam"));
        acb.setClosed(rs.getBoolean("isclosed"));
        data.add(acb);
      }
    } catch (SQLException ex) {
      ex.getMessage();
    }
    return data;
  }

  public void validateAcyear(FacesContext context, UIComponent component, Object value) throws ValidatorException {

    int v = (Integer) value;
    if (v < 1950 || v > 2100) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid Year");
      throw new ValidatorException(msg);
    }
    boolean isDuplicate = new Duplicate<Integer>().isDuplicate(v, getAllAcyears());
    if (isDuplicate) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate Academic Year not allowed here");
      throw new ValidatorException(msg);
    }
  }

  public void validateNumberOfExam(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    boolean isWrong = (Integer) value < 1;
    if (isWrong) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid Number");
      throw new ValidatorException(msg);
    }
  }

  public void deleteData() {
    String sql = "DELETE FROM sch_academic_year WHERE acyear=?;";
    try {
      PreparedStatement pst = DBConnect.getConnection().prepareStatement(sql);
      pst.setInt(1, this.getAcyear());
      int i = pst.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "One row deleted");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to delete data");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (SQLException ex) {
      Logger.getLogger(AcademicYearBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  void clear() {
    setAcyear(0);
    setNewAcyear(0);
    setNumberOfExam(0);
  }
}
