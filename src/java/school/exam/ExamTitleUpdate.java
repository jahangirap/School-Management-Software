/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.exam;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import school.util.DBConnect;

/**
 *
 * @author MASHUK
 */
public class ExamTitleUpdate implements java.io.Serializable{
private static final long serialVersionUID = 1L;
    private int examTitleId;
    private String examTitle;
    private String examDescription;
    private ExamTitleBean selectedRow;
  /** Creates a new instance of ExamTitleUpdate */
  public ExamTitleUpdate() {
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

  public ExamTitleBean getSelectedRow() {
    return selectedRow;
  }

  public void setSelectedRow(ExamTitleBean selectedRow) {
    this.selectedRow = selectedRow;
  }
  public void doSetSelectedRow() {
    setExamTitleId(getSelectedRow().getExamTitleId());
    setExamTitle(getSelectedRow().getExamTitle());
    setExamDescription(getSelectedRow().getExamDescription());
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

}
