/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.academic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import school.util.DBConnect;

/**
 *
 * @author MASHUK
 */
public class SectionUpdate implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private int sectionId;
  private int classId;
  private int acyearId;
  private int acyear;
  private String className;
  private String sectionName;
  private SectionBean selectedRow;

  /** Creates a new instance of SectionUpdate */
  public SectionUpdate() {
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

  public SectionBean getSelectedRow() {
    return selectedRow;
  }

  public void setSelectedRow(SectionBean selectedRow) {
    this.selectedRow = selectedRow;
  }
  
  
  
  public List<SectionUpdate> getAllAcyears(){
  List<SectionUpdate> list = new ArrayList<SectionUpdate>();
  String sql = "SELECT acyid, acyear FROM sch_academic_year;";
  try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        SectionUpdate acyb = new SectionUpdate();
        acyb.setAcyearId(rs.getInt("acyid"));
        acyb.setAcyear(rs.getInt("acyear"));
        list.add(acyb);
      }
    } catch (Exception e) {
    }
  return list;
  }
  public List<SectionUpdate> getAllClasses() {
    String sql = "SELECT class_id, class_name FROM sch_class_name;";
    List<SectionUpdate> data = new ArrayList<SectionUpdate>();
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        SectionUpdate cb = new SectionUpdate();
        cb.setClassId(rs.getInt("class_id"));
        cb.setClassName(rs.getString("class_name"));
        data.add(cb);
      }
    } catch (Exception e) {
    }
    return data;
  }
  public void prepareUpdate(){
    setSectionId(getSelectedRow().getSectionId());
    setAcyear(getSelectedRow().getAcyear());
    setClassName(getSelectedRow().getClassName());
    setSectionName(getSelectedRow().getSectionName());
    System.out.println("OK");
  }
  public void updateSection() {
    String sql = "UPDATE sch_section_name "
            + "SET class_id=(SELECT class_id FROM sch_class_name WHERE class_name=?), "
            + "section_name=?, "
            + "acyid=(SELECT acyid FROM sch_academic_year WHERE acyear=?) WHERE section_id=?";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareCall(sql);
      ps.setString(1, this.getClassName());
      ps.setString(2, this.getSectionName());
      ps.setInt(3, this.getAcyear());
      ps.setInt(4, this.getSectionId());
      int i = ps.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data Update Successfully");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to update data");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
      clear();
    } catch (Exception e) {
    }
  }

  void clear() {
    setAcyear(0);
    setClassName("");
    setSectionName("");
  }
}
