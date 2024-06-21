/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.timetable;

import java.sql.Connection;
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
import javax.naming.spi.DirStateFactory;
import school.util.DBConnect;

/**
 *
 * @author Enamul
 */
@ManagedBean
@SessionScoped
public class DisplayTimeTable implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int teacherid, subjectid, timeid;
    private String teachername;
    private String subjectname;
    private String time;
    private String day;
    private int ttid;
    private String[] days;

    /**
     * Creates a new instance of DisplayTimeTable
     */
    public DisplayTimeTable() {
    }

//    public String[] getDays() {
//        return days = new String[]{"Sunday", "Monday", "Tuseday", "Wednesday", "Thusday", "Friday", "Satureday"};
//    }
    public String[] getDays() {
        return days = new String[]{"Sunday", "Monday", "Tuseday", "Wednesday", "Thusday", "Friday", "Satureday"};
    }

    public void setDays(String[] days) {
        this.days = days;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getTtid() {
        return ttid;
    }

    public void setTtid(int ttid) {
        this.ttid = ttid;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public int getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(int subjectid) {
        this.subjectid = subjectid;
    }

    public int getTimeid() {
        return timeid;
    }

    public void setTimeid(int timeid) {
        this.timeid = timeid;
    }

    public String edit() {
        teachername = "";
        subjectname = "";
        time = "";
        //day="";
        return "edit";
    }

    public List<DisplayTimeTable> getAllTimeTable() {
        String sql = "SELECT ttid, (select teacher_name from sch_teacher_info t where t.teacher_id=ct.teacher_id) as teacher_name, (select subject_name from sch_subject sub where sub.subject_id=ct.subject_id) as subject_name, (select  dptime from sch_day_period dp where dp.dpid=ct.dpid) as dptime, ttday FROM sch_class_time_table ct";
        List<DisplayTimeTable> list = new ArrayList<DisplayTimeTable>();
        try {
            Connection con = DBConnect.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                DisplayTimeTable d = new DisplayTimeTable();
                d.setTeachername(rs.getString("teacher_name"));
                d.setSubjectname(rs.getString("subject_name"));
                d.setTime(rs.getString("dptime"));
                d.setDay(rs.getString("ttday"));
                d.setTtid(rs.getInt("ttid"));
                list.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void delete() {
        String sql = "DELETE FROM sch_class_time_table WHERE ttid=?";
        PreparedStatement ps;
        try {
            ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setInt(1, this.getTtid());
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "One row deleted");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to delete data");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisplayTimeTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<DisplayTimeTable> getAllTeacher() {
        List<DisplayTimeTable> list = new ArrayList<DisplayTimeTable>();
        try {
            Connection con = DBConnect.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sch_teacher_info");
            while (rs.next()) {
                //System.out.println("gggg");
                DisplayTimeTable t = new DisplayTimeTable();
                t.setTeachername(rs.getString("teacher_name"));
                t.setTeacherid(rs.getInt("teacher_id"));
                list.add(t);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public List<DisplayTimeTable> getAllSubject() {
        List<DisplayTimeTable> list = new ArrayList<DisplayTimeTable>();
        try {
            Connection con = DBConnect.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sch_subject");
            while (rs.next()) {
                // System.out.println("gggg");
                DisplayTimeTable t = new DisplayTimeTable();
                t.setSubjectname(rs.getString("subject_name"));
                t.setSubjectid(rs.getInt("subject_id"));
                list.add(t);
                // subject_id, subject_code, subject_name

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public List<DisplayTimeTable> getAllTime() {
        List<DisplayTimeTable> list = new ArrayList<DisplayTimeTable>();
        try {
            Connection con = DBConnect.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sch_day_period;");
            while (rs.next()) {
                // System.out.println("gggg");
                DisplayTimeTable t = new DisplayTimeTable();
                t.setTime(rs.getString("dptime"));
                t.setTimeid(rs.getInt("dpid"));
                list.add(t);
                // dpid, dptime

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public void updat() {
        String sql = "update sch_class_time_table set teacher_id=?, subject_id=?, dpid=?,ttday=? where ttid=? ";
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.getTeacherid());
            ps.setInt(2, this.getSubjectid());
            ps.setInt(3, this.getTimeid());
            ps.setString(4, this.getDay());
            ps.setInt(5, this.getTtid());
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Data is Updated!", "Click Go to Return Home "));
                System.out.println("Data is updated");

            }
            clear();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clear() {
        teachername = "";
        subjectname = "";
        time = "";
        day = "";
        teacherid = 0;
        subjectid = 0;
        timeid = 0;
    }
}
