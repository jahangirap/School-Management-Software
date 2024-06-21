/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.timetable;

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
public class PeriodBean implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private int periodId;
  private String period;
  private String newPeriod;
  private int periodNo;

  /** Creates a new instance of PeriodBean */
  public PeriodBean() {
  }

  public String getNewPeriod() {
    return newPeriod;
  }

  public void setNewPeriod(String newPeriod) {
    this.newPeriod = newPeriod;
  }

  public String getPeriod() {
    return period;
  }

  public void setPeriod(String period) {
    this.period = period;
  }

  public int getPeriodId() {
    return periodId;
  }

  public void setPeriodId(int periodId) {
    this.periodId = periodId;
  }

  public int getPeriodNo() {
    return periodNo;
  }

  public void setPeriodNo(int periodNo) {
    this.periodNo = periodNo;
  }

  public void insertPeriod() {
    String sql = "INSERT INTO sch_day_period (dptime) VALUES(?);";
    boolean isDuplicate = new Duplicate<String>().isDuplicate(getPeriod(), getAllPeriods());
    if (isDuplicate) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate Group Name not allowed here");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    } else {
      try {
        PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
        ps.setString(1, this.getPeriod());
        int i = ps.executeUpdate();
        if (i > 0) {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data Saved Successfully");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save data");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        clear();
      } catch (SQLException ex) {
        Logger.getLogger(PeriodBean.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  private List<String> getAllPeriods() {
    List<String> data = new ArrayList<String>();
    String sql = "SELECT * FROM sch_day_period;";
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        data.add(rs.getString("dptime"));
      }
    } catch (SQLException ex) {
      Logger.getLogger(PeriodBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
  }

  public List<PeriodBean> getAllPeriod() {
    List<PeriodBean> data = new ArrayList<PeriodBean>();
    String sql = "SELECT * FROM sch_day_period;";
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        PeriodBean cb = new PeriodBean();
        cb.setPeriodNo(rs.getRow());
        cb.setPeriod(rs.getString("dptime"));
        data.add(cb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(PeriodBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
  }

  public void updatePeriod() {
    String sql = "UPDATE sch_day_period SET dptime=? WHERE dptime=?;";
    //update school.sch_std_group set group_name='rahim' where group_name='rahim1';
    boolean isDuplicate = new Duplicate<String>().isDuplicate(getNewPeriod(), getAllPeriods());
    if (isDuplicate) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate Period not allowed here");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    } else {
      try {
        PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
        ps.setString(1, this.getNewPeriod());
        ps.setString(2, this.getPeriod());
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
        Logger.getLogger(PeriodBean.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public void deletePeriod() {
    String sql = "delete from sch_day_period where dptime=? ;";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setString(1, this.getPeriod());
      int i = ps.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "One row deleted");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to delete data");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (SQLException ex) {
      Logger.getLogger(PeriodBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  void clear() {
    setNewPeriod(null);
    setPeriod(null);
  }
}
