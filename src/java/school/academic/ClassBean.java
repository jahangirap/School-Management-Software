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
public class ClassBean implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private int classId;
  private String className;
  private String newClassName;
  private int serialNo;

  /** Creates a new instance of ClassBean */
  public ClassBean() {
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

  public String getNewClassName() {
    return newClassName;
  }

  public void setNewClassName(String newClassName) {
    this.newClassName = newClassName;
  }

  public int getSerialNo() {
    return serialNo;
  }

  public void setSerialNo(int serialNo) {
    this.serialNo = serialNo;
  }

  public void insertClassName() {
    String sql = "insert into sch_class_name (class_name) values(?);";
    boolean isDuplicate = new Duplicate<String>().isDuplicate(getClassName(), getAllClasses());
    if (isDuplicate) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate Clas Name not allowed here");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    } else {
      try {
        PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
        ps.setString(1, this.getClassName());
        int i = ps.executeUpdate();
        if (i > 0) {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "New Class add successfully");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save data");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        clear();
      } catch (SQLException ex) {
        Logger.getLogger(ClassBean.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

  }

  public List<String> getAllClasses() {
    List<String> list = new ArrayList<String>();
    String sql = "select * from sch_class_name;";
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        list.add(rs.getString("class_name"));
      }
    } catch (SQLException ex) {
      Logger.getLogger(ClassBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<ClassBean> getAllClassName() {
    List<ClassBean> data = new ArrayList<ClassBean>();
    String sql = "select * from sch_class_name;";
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        ClassBean cb = new ClassBean();
        cb.setSerialNo(rs.getRow());
        cb.setClassName(rs.getString("class_name"));
        data.add(cb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ClassBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
  }

  public void updateData() {
    String sql = "UPDATE sch_class_name SET class_name=? WHERE class_name=?;";
    boolean isDuplicate = new Duplicate<String>().isDuplicate(getNewClassName(), getAllClasses());
    if (isDuplicate) {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate Clas Name not allowed here");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    } else {
      try {
        PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
        ps.setString(1, this.getNewClassName());
        ps.setString(2, this.getClassName());
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
        Logger.getLogger(ClassBean.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

  }

  public void deleteData() {
    String sql = "DELETE FROM sch_class_name WHERE class_name=?;";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setString(1, this.getClassName());
      int i = ps.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "One row deleted");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to delete data");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ClassBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  void clear() {
    setClassName("");
    setNewClassName("");
  }
}
