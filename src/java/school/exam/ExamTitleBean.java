/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.exam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import school.util.DBConnect;

/**
 *
 * @author MASHUK
 */
public class ExamTitleBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int examTitleId;
    private String examTitle;
    private String examDescription;

    /** Creates a new instance of ExamTitleBean */
    public ExamTitleBean() {
    }

    public String getExamDescription() {
        return examDescription;
    }

    public void setExamDescription(String examDescription) {
        this.examDescription = examDescription;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public int getExamTitleId() {
        return examTitleId;
    }

    public void setExamTitleId(int examTitleId) {
        this.examTitleId = examTitleId;
    }
    
    public void insertExam() {
        String sql = "INSERT INTO sch_exam_title (exam_title,description) VALUES(?,?);";

        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setString(1, this.getExamTitle());
            ps.setString(2, this.getExamDescription());
            int i = ps.executeUpdate();
            if (i > 0) {
                setExamTitle("");
                setExamDescription("");
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data Saved Successfully");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save data");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "------>insert Error");
        }
        //System.out.println("insert successfully");
    }

    public List<ExamTitleBean> getAllExamTitle() {
        List<ExamTitleBean> data = new ArrayList<ExamTitleBean>();
        String sql = "SELECT  ex_tt_id, exam_title, description  FROM sch_exam_title;";
        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExamTitleBean et = new ExamTitleBean();
                et.setExamTitleId(rs.getInt("ex_tt_id"));
                et.setExamTitle(rs.getString("exam_title"));
                et.setExamDescription(rs.getString("description"));
                data.add(et);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "------>get Exam title Error");
        }
        return data;
    }

    public void updateExam() {
        String sql = "UPDATE sch_exam_title SET exam_title=?, description=? WHERE ex_tt_id=?; ";
        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setString(1, this.getExamTitle());
            ps.setString(2, this.getExamDescription());
            ps.setInt(3, this.getExamTitleId());
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data Update Successfully");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to update data");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "------>updateExam Error");
        }
    }

    public void deleteExam() {
        String sql = "DELETE FROM sch_exam_title WHERE ex_tt_id=?;";
        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setInt(1, this.getExamTitleId());
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "One row deleted");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to delete data");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "------>deleteExam Error");
        }
    }
}
