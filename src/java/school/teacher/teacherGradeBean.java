/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.teacher;

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

/**
 *
 * @author MASHUK
 */
public class teacherGradeBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int teacherGradeId;
    private String teacherGrade;
    private String newTeacherGrade;

    /** Creates a new instance of teacherGradeBean */
    public teacherGradeBean() {
    }

    public String getNewTeacherGrade() {
        return newTeacherGrade;
    }

    public void setNewTeacherGrade(String newTeacherGrade) {
        this.newTeacherGrade = newTeacherGrade;
    }

    public String getTeacherGrade() {
        return teacherGrade;
    }

    public void setTeacherGrade(String teacherGrade) {
        this.teacherGrade = teacherGrade;
    }

    public int getTeacherGradeId() {
        return teacherGradeId;
    }

    public void setTeacherGradeId(int teacherGradeId) {
        this.teacherGradeId = teacherGradeId;
    }

    public void insertGrade() {
        String sql = "INSERT INTO sch_teacher_grade (teacher_grade) VALUES(?);";
        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setString(1, this.getTeacherGrade());
            int i = ps.executeUpdate();
            if (i > 0) {
                setTeacherGrade("");
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data Saved Successfully");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save data");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
        }
    }

    public List<teacherGradeBean> getAllGradeName() {
        List<teacherGradeBean> data = new ArrayList<teacherGradeBean>();
        String sql = "SELECT * FROM sch_teacher_grade;";
        try {
            Statement st = DBConnect.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                teacherGradeBean tg = new teacherGradeBean();
                tg.setTeacherGrade(rs.getString("teacher_grade"));
                data.add(tg);
            }
        } catch (Exception e) {
        }
        return data;
    }

    public void updateTGrade() {
        String sql = "UPDATE sch_teacher_grade SET teacher_grade=? WHERE teacher_grade=?;";
        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setString(1, this.getNewTeacherGrade());
            ps.setString(2, this.getTeacherGrade());
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data Update Successfully");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to update data");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
        }
    }

    public void deleteTeacherGrade() {
        String sql = "DELETE FROM sch_teacher_grade WHERE teacher_grade=?";
        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setString(1, this.getTeacherGrade());
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "One row deleted");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to delete data");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(teacherGradeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
