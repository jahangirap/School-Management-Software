/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.academic;

import com.sun.faces.config.DbfFactory.FacesSchema;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import school.util.DBConnect;

/**
 *
 * @author MASHUK
 */
public class SectionBean implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private int sectionId;
  private int classId;
  private int acyearId;
  private int acyear;
  private String className;
  private String sectionName;
  

  /** Creates a new instance of SectionBean */
  public SectionBean() {
  }

  public int getAcyear() {
    return acyear;
  }

  public void setAcyear(int acyear) {
    this.acyear = acyear;
  }

  public int getAcyearId() {
    return acyearId;
  }

  public void setAcyearId(int acyearId) {
    this.acyearId = acyearId;
  }

  public int getClassId() {
    return classId;
  }

  public void setClassId(int classId) {
    this.classId = classId;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public int getSectionId() {
    return sectionId;
  }

  public void setSectionId(int sectionId) {
    this.sectionId = sectionId;
  }

  public String getSectionName() {
    return sectionName;
  }

  public void setSectionName(String sectionName) {
    this.sectionName = sectionName;
  }

  

  public List<SectionBean> getAllSectionName() {
    List<SectionBean> data = new ArrayList<SectionBean>();
    String sql = "SELECT acyear, class_name, section_id, section_name "
            + "FROM sch_academic_year a "
            + "JOIN sch_class_name as c "
            + "JOIN sch_section_name as s "
            + "WHERE a.acyid=s.acyid "
            + "AND c.class_id=s.class_id;";
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        SectionBean sb = new SectionBean();
        sb.setSectionId(rs.getInt("section_id"));
        sb.setAcyear(rs.getInt("acyear"));
        sb.setClassName(rs.getString("class_name"));
        sb.setSectionName(rs.getString("section_name"));
        data.add(sb);
      }
    } catch (Exception e) {
    }
    return data;
  }

  public List<SectionBean> getAllClasses() {
    String sql = "SELECT class_id, class_name FROM sch_class_name;";
    List<SectionBean> data = new ArrayList<SectionBean>();
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        SectionBean cb = new SectionBean();
        cb.setClassId(rs.getInt("class_id"));
        cb.setClassName(rs.getString("class_name"));
        data.add(cb);
      }
    } catch (Exception e) {
    }

    return data;
  }

  public List<SectionBean> getAllAcyears() {
    String sql = "SELECT acyid, acyear FROM sch_academic_year;";
    List<SectionBean> data = new ArrayList<SectionBean>();
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        SectionBean acyb = new SectionBean();
        acyb.setAcyearId(rs.getInt("acyid"));
        acyb.setAcyear(rs.getInt("acyear"));
        data.add(acyb);
      }
    } catch (Exception e) {
    }

    return data;
  }

  public void insertSection() {
    String sql = "INSERT INTO sch_section_name (class_id ,section_name,acyid) VALUES(?,?,?);";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setInt(1, this.getClassId());
      ps.setString(2, this.getSectionName());
      ps.setInt(3, this.getAcyearId());
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
      Logger.getLogger(SectionBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }


  public void deleteSection() {
    String sql = "DELETE FROM sch_section_name WHERE section_name=? "
            + "AND class_id=(SELECT class_id FROM sch_class_name WHERE class_name=?) "
            + "AND acyid=(SELECT acyid FROM sch_academic_year WHERE acyear=?);";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareCall(sql);
      ps.setString(1, this.getSectionName());
      ps.setString(2, this.getClassName());
      ps.setInt(3, this.getAcyear());
      int i = ps.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "One row deleted");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to delete data");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (SQLException ex) {
      Logger.getLogger(SectionBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  void clear() {
    setAcyear(0);
    setClassName("");
    setSectionName("");
  }
}
