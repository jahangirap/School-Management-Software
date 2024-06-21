/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.timetable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import school.util.DBConnect;

/**
 *
 * @author Enamul
 */
@ManagedBean
@SessionScoped
public class TeacherTimeTableBean {

    private int teacherid, subjectid, timeid;
    private String teachername;
    private String subjectname;
    private String time;
    private String day;
    private String[] days;

    /**
     * Creates a new instance of TeacherTimeTableBean
     */
    public TeacherTimeTableBean() {
//        this.days = new String[]{"Sunday","Monday","Tuseday","Wednesday","Thusday","Friday","Satureday"};
    }

    public String[] getDays() {
        return days = new String[]{"Sunday", "Monday", "Tuseday", "Wednesday", "Thusday", "Friday", "Satureday"};
    }

//    public void setDays(String[] days) {
//        this.days = days;
//    }
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

    public List<TeacherTimeTableBean> getAllTeacher() {
        List<TeacherTimeTableBean> list = new ArrayList<TeacherTimeTableBean>();
        try {
            Connection con = DBConnect.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sch_teacher_info");
            while (rs.next()) {
                //System.out.println("gggg");
                TeacherTimeTableBean t = new TeacherTimeTableBean();
                t.setTeachername(rs.getString("teacher_name"));
                t.setTeacherid(rs.getInt("teacher_id"));
                list.add(t);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public List<TeacherTimeTableBean> getAllSubject() {
        List<TeacherTimeTableBean> list = new ArrayList<TeacherTimeTableBean>();
        try {
            Connection con = DBConnect.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sch_subject");
            while (rs.next()) {
                // System.out.println("gggg");
                TeacherTimeTableBean t = new TeacherTimeTableBean();
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

    public List<TeacherTimeTableBean> getAllTime() {
        List<TeacherTimeTableBean> list = new ArrayList<TeacherTimeTableBean>();
        try {
            Connection con = DBConnect.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sch_day_period");
            while (rs.next()) {
                // System.out.println("gggg");
                TeacherTimeTableBean t = new TeacherTimeTableBean();
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

//    public void dayTime() {
//        try {
//            Connection con = DBConnect.getConnection();
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("SELECT dpid FROM sch_day_period where dptime='" + time + "'");
//            while (rs.next()) {
//                timeid = rs.getInt("dpid");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
    public List<TeacherTimeTableBean> sevenDay() {
        List<TeacherTimeTableBean> list = new ArrayList<TeacherTimeTableBean>();

        return list;

    }

    public void save() {
        try {
            String sql = "insert into sch_class_time_table(teacher_id, subject_id, dpid, ttday) values(?,?,?,?)";

            Connection con = DBConnect.getConnection();
            Statement st = con.createStatement();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.getTeacherid());
            ps.setInt(2, this.getSubjectid());
            ps.setInt(3, this.getTimeid());
            ps.setString(4, this.day);
            int i = ps.executeUpdate();

            if (i > 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Data is Saved!", "Add New "));
                System.out.println("Data is saved");
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
