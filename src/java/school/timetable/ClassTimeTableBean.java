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
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import school.util.DBConnect;

/**
 *
 * @author MASHUK
 */
public class ClassTimeTableBean implements java.io.Serializable{

  Integer teacherId2, subjectId2, dpid2, ttid;
  String teacherName2, subjectName2, dpName2;
  String day2;
  private int timTableId;
  private int teacherId;
  private int subjectId;
  private int periodId;
  private String teacherName;
  private String subjectName;
  private String period;
  private String day;
  private String[] days;
  private String groupName;
  private int groupId;
  private String className;
  private int classId;
  private String sectionName;
  private int sectionId;
  private String dptime;
  private int dpid;
  private int acyId;
  private int acyear;
  private List<DailyRoutine> rowdata;
   

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String[] getDays() {
    return days = new String[]{"Sunday", "Monday", "Tuseday", "Wednesday", "Thusday", "Friday", "Satureday"};
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

  public int getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(int subjectId) {
    this.subjectId = subjectId;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public int getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(int teacherId) {
    this.teacherId = teacherId;
  }

  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public int getTimTableId() {
    return timTableId;
  }

  public void setTimTableId(int timTableId) {
    this.timTableId = timTableId;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public int getClassId() {
    return classId;
  }

  public void setClassId(int classId) {
    this.classId = classId;
  }

  public String getSectionName() {
    return sectionName;
  }

  public void setSectionName(String sectionName) {
    this.sectionName = sectionName;
  }

  public int getSectionId() {
    return sectionId;
  }

  public void setSectionId(int sectionId) {
    this.sectionId = sectionId;
  }

  public String getDptime() {
    return dptime;
  }

  public void setDptime(String dptime) {
    this.dptime = dptime;
  }

  public int getDpid() {
    return dpid;
  }

  public void setDpid(int dpid) {
    this.dpid = dpid;
  }

  public int getAcyId() {
    return acyId;
  }

  public void setAcyId(int acyId) {
    this.acyId = acyId;
  }

  public int getAcyear() {
    return acyear;
  }

  public void setAcyear(int acyear) {
    this.acyear = acyear;
  }

  public List<DailyRoutine> getRowdata() {
    return rowdata;
  }

  public void setRowdata(List<DailyRoutine> rowdata) {
    this.rowdata = rowdata;
  }
  private static final ArrayList<DipositBean> DepositList = new ArrayList<DipositBean>();
//    private ArrayList list = new ArrayList();

  public ArrayList<DipositBean> getData() {
    return DepositList;
  }

  public String addAction() {
    DipositBean d = new DipositBean(this.groupName, this.className, this.sectionName, this.teacherName, this.subjectName, this.dptime, this.day);
    DepositList.add(d);

    return null;
  }

  public void onEdit(RowEditEvent event) {
    FacesMessage msg = new FacesMessage("Row Edited", ((DipositBean) event.getObject()).getGroupName());
    FacesContext.getCurrentInstance().addMessage(null, msg);

  }

  public void onCancel(RowEditEvent event) {
    FacesMessage msg = new FacesMessage("Row Cancelled");
    FacesContext.getCurrentInstance().addMessage(null, msg);
    DepositList.remove((DipositBean) event.getObject());

  }

  public List<ClassTimeTableBean> getAllTeacher() {
    List<ClassTimeTableBean> list = new ArrayList<ClassTimeTableBean>();
    try {
      Connection con = DBConnect.getConnection();
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery("SELECT * FROM sch_teacher_info");
      while (rs.next()) {
        //System.out.println("gggg");
        ClassTimeTableBean t = new ClassTimeTableBean();
        t.setTeacherName(rs.getString("teacher_name"));
        t.setTeacherId(rs.getInt("teacher_id"));
        list.add(t);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List<ClassTimeTableBean> getAllGroup() {
    List<ClassTimeTableBean> list = new ArrayList<ClassTimeTableBean>();
    try {
      Connection con = DBConnect.getConnection();
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery("SELECT * FROM sch_std_group");
      while (rs.next()) {
        //System.out.println("gggg");
        ClassTimeTableBean t = new ClassTimeTableBean();
        t.setGroupName(rs.getString("group_name"));
        t.setGroupId(rs.getInt("group_id"));
        list.add(t);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List<ClassTimeTableBean> getAllAcademicYear() {
    String query = "SELECT * FROM sch_academic_year";
    List<ClassTimeTableBean> list = new ArrayList<ClassTimeTableBean>();
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        ClassTimeTableBean ayb = new ClassTimeTableBean();
        ayb.setAcyId(rs.getInt("acyid"));
        ayb.setAcyear(rs.getInt("acyear"));
        list.add(ayb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ClassTimeTableBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<ClassTimeTableBean> getAllClass() {
    List<ClassTimeTableBean> list = new ArrayList<ClassTimeTableBean>();
    try {
      Connection con = DBConnect.getConnection();
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery("SELECT * FROM sch_class_name");
      while (rs.next()) {
        //System.out.println("gggg");
        ClassTimeTableBean t = new ClassTimeTableBean();
        t.setClassName(rs.getString("class_name"));
        t.setClassId(rs.getInt("class_id"));
        list.add(t);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List<ClassTimeTableBean> getAllSection() {
    List<ClassTimeTableBean> list = new ArrayList<ClassTimeTableBean>();
    try {
      Connection con = DBConnect.getConnection();
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery("SELECT * FROM sch_section_name");
      while (rs.next()) {
        //System.out.println("gggg");
        ClassTimeTableBean t = new ClassTimeTableBean();
        t.setSectionName(rs.getString("section_name"));
        t.setSectionId(rs.getInt("section_id"));
        list.add(t);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List<ClassTimeTableBean> getAllSubject() {
    List<ClassTimeTableBean> list = new ArrayList<ClassTimeTableBean>();
    try {
      Connection con = DBConnect.getConnection();
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery("SELECT * FROM sch_subject");
      while (rs.next()) {
        // System.out.println("gggg");
        ClassTimeTableBean t = new ClassTimeTableBean();
        t.setSubjectName(rs.getString("subject_name"));
        t.setSubjectId(rs.getInt("subject_id"));
        list.add(t);
        // subject_id, subject_code, subject_name
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public List<ClassTimeTableBean> getAllDpTime() {
    List<ClassTimeTableBean> list = new ArrayList<ClassTimeTableBean>();
    try {
      Connection con = DBConnect.getConnection();
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery("SELECT * FROM sch_day_period;");
      while (rs.next()) {
        // System.out.println("gggg");
        ClassTimeTableBean t = new ClassTimeTableBean();
        t.setDptime(rs.getString("dptime"));
        t.setDpid(rs.getInt("dpid"));
        list.add(t);
        // dpid, dptime
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public void insertTimeTable() {
    try {
      String sql = "insert into sch_class_time_table(teacher_id, subject_id, dpid, ttday) values(?,?,?,?)";
      Connection con = DBConnect.getConnection();
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setInt(1, this.getTeacherId());
      ps.setInt(2, this.getSubjectId());
      ps.setInt(3, this.getPeriodId());
      ps.setString(4, this.getDay());
      int i = ps.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data Saved Successfully");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save data");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
      clear();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<ClassTimeTableBean> getAllTimeTable() {
    String sql = "SELECT ttid, "
            + "(select teacher_name from sch_teacher_info t where t.teacher_id=ct.teacher_id) as teacher_name, "
            + "(select subject_name from sch_subject sub where sub.subject_id=ct.subject_id) as subject_name, "
            + "(select  dptime from sch_day_period dp where dp.dpid=ct.dpid) as dptime, ttday "
            + "FROM sch_class_time_table ct";
    List<ClassTimeTableBean> list = new ArrayList<ClassTimeTableBean>();
    try {
      Connection con = DBConnect.getConnection();
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        ClassTimeTableBean d = new ClassTimeTableBean();
        d.setTeacherName(rs.getString("teacher_name"));
        d.setSubjectName(rs.getString("subject_name"));
        d.setPeriod(rs.getString("dptime"));
        d.setDay(rs.getString("ttday"));
        d.setTimTableId(rs.getInt("ttid"));
        list.add(d);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public void updateTimeTable() {
    String sql = "update sch_class_time_table set teacher_id=?, subject_id=?, dpid=?,ttday=? where ttid=? ";
    try {
      Connection con = DBConnect.getConnection();
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setInt(1, this.getTeacherId());
      ps.setInt(2, this.getSubjectId());
      ps.setInt(3, this.getPeriodId());
      ps.setString(4, this.getDay());
      ps.setInt(5, this.getTimTableId());
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
      e.printStackTrace();
    }

  }

  public void deleteTimeTable() {
    String sql = "DELETE FROM sch_class_time_table WHERE ttid=?";
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setInt(1, this.getTimTableId());
      int i = ps.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "One row deleted");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to delete data");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ClassTimeTableBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void clear() {
    setTeacherId(0);
    setTeacherName("");
    setSubjectId(0);
    setSubjectName("");
    setPeriodId(0);
    setPeriod("");
    setDay("");
  }

  public void saveItem() {
//        String sql = "insert into sch_time_table values  group_id=?, class_id=?, section_id=? "
//                + "where group_id=(select group_id FROM sch_std_group where group_name=?)"
//                + " and class_id=(select class_id from sch_class_name where class_name=?) "
//                + "and section_id=(select section_id FROM sch_section_name where section_name=?)";
//        String sql1="insert into sch_time_table values(  "
//                + "group_id=(select group_id FROM sch_std_group where group_name=?), "
//                + "class_id=(select class_id from sch_class_name where class_name=?),"
//                + " section_id=(select section_id FROM sch_section_name where section_name=?))";

    String sql = "insert into sch_time_table (group_id, class_id, section_id) values("
            + " (select group_id FROM sch_std_group where group_name=?),"
            + "(select class_id from sch_class_name where class_name=?),"
            + "(select section_id FROM sch_section_name where section_name=?))";
    try {
      PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
      ps.setString(1, this.getGroupName());
      ps.setString(2, this.getClassName());
      ps.setString(3, this.getSectionName());
      int c = ps.executeUpdate();
      if (c > 0) {
        //doTtId();
        Statement st = DBConnect.getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX(last_insert_id(ttid)) as ttid FROM sch_time_table");
        if (rs.next()) {
          //setTimTableId(rs.getInt("ttid"));
          ttid = rs.getInt("ttid");
          for (int i = 0; i < DepositList.size(); i++) {
            teacherName2 = DepositList.get(i).getTeacherName();
            subjectName = DepositList.get(i).getSubjectName();
            dpName2 = DepositList.get(i).getDptime();
            day2 = DepositList.get(i).getDay();

//                        st.executeUpdate("insert into sch_class_time_table_line ( teacher_id, subject_id, dpid, ttday, ttid) "
//                                + "values('"+ttid+"',(SELECT teacher_id FROM sch_teacher_info "
//                                + "where teacher_name='"+this.getTeacherName()+"'),(SELECT subject_id  FROM sch_subject "
//                                + "where subject_name='"+this.getSubjectName()+"'),(SELECT dpid FROM sch_day_period "
//                                + "where dptime='"+this.getDptime()+"'),())" );

            st.executeUpdate("insert into sch_class_time_table_line (teacher_id, subject_id, dpid, ttday, ttid) "
                    + "values((SELECT teacher_id FROM sch_teacher_info "
                    + "where teacher_name='" + teacherName2 + "'),(SELECT subject_id FROM sch_subject "
                    + "where subject_name='" + subjectName + "'),(SELECT dpid FROM sch_day_period "
                    + "where dptime='" + dpName2 + "'),'" + day2 + "','" + ttid + "')");
            System.out.println("Saved");
          }
        }
        clear();
        DepositList.removeAll(DepositList);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void doTtId() {
    try {
      Connection con = DBConnect.getConnection();
      Statement st = con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT MAX(ttid) as ttid FROM sch_time_table");
      while (rs.next()) {
        setTimTableId(rs.getInt("ttid"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<ClassTimeTableBean> getRoutine() {
    //String sql = " SELECT l.tt_line_id, (select teacher_name from sch_teacher_info t where t.teacher_id=l.teacher_id) as teacher_name, (select subject_name FROM sch_subject sub where l.subject_id=sub.subject_id) as subject_name , (select dptime FROM sch_day_period p where l.dpid=p.dpid ) as dptime , l.ttday, (select ttid from sch_time_table ti where l.ttid=ti.ttid) FROM sch_class_time_table_line l;";
    //String sql=" SELECT l.tt_line_id, (select teacher_name from sch_teacher_info t where t.teacher_id=l.teacher_id) as teacher_name, (select subject_name FROM sch_subject sub where l.subject_id=sub.subject_id) as subject_name,(select dptime FROM sch_day_period p where l.dpid=p.dpid )  as dptime, l.ttday FROM sch_class_time_table_line l where l.ttid=(select ti.ttid from sch_time_table ti  where ti.group_id='"+this.getGroupId()+"' and ti.class_id='"+this.getClassId()+"' and ti.section_id='"+this.getSectionId()+"');";
    String sql = " SELECT l.tt_line_id, (select teacher_name from sch_teacher_info t where t.teacher_id=l.teacher_id) as teacher_name, (select subject_name FROM sch_subject sub where l.subject_id=sub.subject_id) as subject_name,(select dptime FROM sch_day_period p where l.dpid=p.dpid )  as dptime, l.ttday, l.ttid FROM sch_class_time_table_line l ";
    List<ClassTimeTableBean> list = new ArrayList<ClassTimeTableBean>();
    try {
      Connection con = DBConnect.getConnection();
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        ClassTimeTableBean c = new ClassTimeTableBean();
        c.setTeacherName(rs.getString("teacher_name"));
        list.add(c);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;

  }

  public void doSet() {
    System.out.println(getGroupId());
    System.out.println(getClassId());
    System.out.println(getSectionId());
    System.out.println(getDay());
  }

  public List<ClassTimeTableBean> getClassRoutine() {
    List<ClassTimeTableBean> list = new ArrayList<ClassTimeTableBean>();
    String query = "SELECT t.teacher_name, sb.subject_name, p.dptime, s.ttday "
            + "FROM sch_class_time_table_line s "
            + "INNER JOIN sch_teacher_info t ON (t.teacher_id=s.teacher_id) "
            + "INNER JOIN sch_subject sb ON (sb.subject_id=s.subject_id) "
            + "INNER JOIN sch_day_period p ON (p.dpid=s.dpid) "
            + "WHERE ttid=(SELECT ttid FROM sch_time_table WHERE group_id=? AND class_id=? AND section_id=?) "
            + "AND ttday=?;";
    try {
        PreparedStatement ps = DBConnect.getConnection().prepareStatement(query);
        ps.setInt(1, getGroupId());
        ps.setInt(2, getClassId());
        ps.setInt(3, getSectionId());
        ps.setString(4, getDay());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
          ClassTimeTableBean cttb = new ClassTimeTableBean();
          cttb.setPeriod(rs.getString("dptime"));
          cttb.setSubjectName(rs.getString("subject_name"));
          cttb.setTeacherName(rs.getString("teacher_name"));          
          list.add(cttb);
        }
      } catch (SQLException ex) {
        Logger.getLogger(ClassTimeTableBean.class.getName()).log(Level.SEVERE, null, ex);
      }    
    return list;
  }
  public static void main(String[] args) {
    ClassTimeTableBean cttb = new ClassTimeTableBean();    
    cttb.setGroupId(1);
    cttb.setClassId(1);
    cttb.setSectionId(1);
    cttb.setDay("Sunday");
    List<ClassTimeTableBean> list=cttb.getClassRoutine();
    for (Iterator<ClassTimeTableBean> it = list.iterator(); it.hasNext();) {
      ClassTimeTableBean ctb = it.next();
      System.out.println(ctb.getPeriod());
    }
  }
}
