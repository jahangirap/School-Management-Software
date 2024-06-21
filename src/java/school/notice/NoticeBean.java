/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.notice;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import school.util.DBConnect;

/**
 *
 * @author MASHUK
 */
public class NoticeBean implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private int noticeId;
  private String noticeTitle;
  private String notice;
  private Date publishDate;
  private Date validateDate;
  private UploadedFile noticeIamge;

  /** Creates a new instance of NoticeBean */
  public NoticeBean() {
  }

  public String getNotice() {
    return notice;
  }

  public void setNotice(String notice) {
    this.notice = notice;
  }

  public UploadedFile getNoticeIamge() {
    return noticeIamge;
  }

  public void setNoticeIamge(UploadedFile noticeIamge) {
    this.noticeIamge = noticeIamge;
  }

  public int getNoticeId() {
    return noticeId;
  }

  public void setNoticeId(int noticeId) {
    this.noticeId = noticeId;
  }

  public String getNoticeTitle() {
    return noticeTitle;
  }

  public void setNoticeTitle(String noticeTitle) {
    this.noticeTitle = noticeTitle;
  }

  public Date getValidateDate() {
    return validateDate;
  }

  public void setValidateDate(Date validateDate) {
    this.validateDate = validateDate;
  }

  public Date getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(Date publishDate) {
    this.publishDate = publishDate;
  }

  public void insertNotice() {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String pd = df.format(getPublishDate());
    String vd = df.format(getValidateDate());
    if (noticeIamge != null) {
      String sql = "INSERT INTO sch_school_notice (notice_title, notice, notice_img, publish_date, expire_date)"
              + "VALUES (?,?,?,?,?)";
      try {
        InputStream streamfile = getNoticeIamge().getInputstream();
        PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
        ps.setString(1, getNoticeTitle());
        ps.setString(2, getNotice());
        ps.setBinaryStream(3, streamfile, getNoticeIamge().getSize());
        ps.setString(4, pd);
        ps.setString(5, vd);
        int i = ps.executeUpdate();
        if (i > 0) {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "New Notice add Successfully");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save notice");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        }
      } catch (SQLException ex) {
        System.out.println("Error from insert----------->" + ex.getMessage());
      } catch (IOException ex) {
        System.out.println("Error from insert----------->" + ex.getMessage());
      }
    } else {
      String sql = "INSERT INTO sch_school_notice (notice_title, notice, publish_date, expire_date)"
              + "VALUES (?,?,?,?)";
      try {
        PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
        ps.setString(1, getNoticeTitle());
        ps.setString(2, getNotice());
        ps.setString(3, pd);
        ps.setString(4, vd);
        int i = ps.executeUpdate();
        if (i > 0) {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "New Notice add Successfully");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save notice");
          FacesContext.getCurrentInstance().addMessage(null, msg);
        }
      } catch (SQLException ex) {
        System.out.println("Error from insert----------->" + ex.getMessage());
      }
    }

  }

  public List<NoticeBean> getAllNotice() {
    List<NoticeBean> data = new ArrayList<NoticeBean>();
    String sql = "SELECT * FROM sch_school_notice ORDER BY notice_id DESC LIMIT 5;";
    try {
      Statement st = DBConnect.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        NoticeBean nb = new NoticeBean();
        nb.setNoticeId(rs.getInt("notice_id"));
        nb.setNoticeTitle(rs.getString("notice_title"));
        nb.setNotice(rs.getString("notice"));
        //nb.setNoticeImage(file);
        nb.setPublishDate(rs.getDate("publish_date"));
        nb.setValidateDate(rs.getDate("expire_date"));

        data.add(nb);
      }
    } catch (Exception e) {
    }
    return data;
  }
//notice_id, notice_title, notice, notice_img, publish_date, expire_date

  public void updateNotice() {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String pd = df.format(getPublishDate());
    String vd = df.format(getValidateDate());
    String sql = "UPDATE sch_school_notice SET notice_title=?, notice=?, publish_date=?,expire_date=? WHERE notice_id=?";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setString(1, getNoticeTitle());
      ps.setString(2, getNotice());
      ps.setString(3, pd);
      ps.setString(4, vd);
      ps.setInt(5, getNoticeId());
      int i = ps.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data Update Successfully");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to update data");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (SQLException ex) {
      Logger.getLogger(NoticeBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void updateNoticeImage() {
    String sql = "UPDATE sch_school_notice SET notice_img=? WHERE notice_id=?";
    InputStream streamfile = null;
    try {
      streamfile = getNoticeIamge().getInputstream();
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setBinaryStream(1, streamfile, getNoticeIamge().getSize());
      int i = ps.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Image Changed Successfully");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to change Image");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (SQLException ex) {
      Logger.getLogger(NoticeBean.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(NoticeBean.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        streamfile.close();
      } catch (IOException ex) {
        Logger.getLogger(NoticeBean.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public void deleteNotice() {
    String sql = "DELETE FROM sch_school_notice WHERE notice_id=?";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setInt(1, getNoticeId());
      int i = ps.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "One row deleted");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to delete data");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (SQLException ex) {
      Logger.getLogger(NoticeBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
