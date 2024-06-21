package school.teacher;

import java.sql.Connection;
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import school.util.DBConnect;
import school.util.LoginUtil;

@ManagedBean
@SessionScoped
public class DisplayTeacherInfoBean implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private int teacherId;
    private int teacherGradeId;
    private int subjectId;
    private String teacherName;
    private String teacherMobile;
    private String teacherAddress;
    private Date teacherJoinDate;
    private String teacherEmail;
    private String departDate;
    private String subject;
    private String teacherGrade;
    private boolean present = true;
    private String roleName;
    private int roleId;
    private String userName;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    private boolean active;
    private int userId;

    public int getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId the teacherId to set
     */
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return the teacherGradeId
     */
    public int getTeacherGradeId() {
        return teacherGradeId;
    }

    /**
     * @param teacherGradeId the teacherGradeId to set
     */
    public void setTeacherGradeId(int teacherGradeId) {
        this.teacherGradeId = teacherGradeId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherMobile() {
        return teacherMobile;
    }

    public void setTeacherMobile(String teacherMobile) {
        this.teacherMobile = teacherMobile;
    }

    public String getTeacherAddress() {
        return teacherAddress;
    }

    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public Date getTeacherJoinDate() {
        return teacherJoinDate;
    }

    public void setTeacherJoinDate(Date teacherJoinDate) {
        this.teacherJoinDate = teacherJoinDate;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacherGrade() {
        return teacherGrade;
    }

    public void setTeacherGrade(String teacherGrade) {
        this.teacherGrade = teacherGrade;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String goEditPage() {

        return "updateTeacherInfo?faces-redirect=true";
    }

    public List<DisplayTeacherInfoBean> getAllTeacherInfo() {
        String sql = "SELECT t.teacher_id, sub.subject_name, t.teacher_name, "
                + "t.teacher_mobile, t.teacher_addr, t.teacher_join_date, "
                + "g.teacher_grade, t.teacher_email, t.ispresent, t.depart_date, "
                + "t.teacher_photo, u.user_id, u.uname "
                + "FROM (sch_teacher_info t inner join sch_teacher_grade g "
                + "on (t.teacher_grade_id=g.teacher_grade_id) "
                + "inner join sch_user u "
                + "on (t.user_id=u.user_id) "
                + "inner join sch_subject sub "
                + "on(t.subject_id=sub.subject_id)) order by t.teacher_name;";
        List<DisplayTeacherInfoBean> list = new ArrayList<DisplayTeacherInfoBean>();
        try {
            Connection con = DBConnect.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                DisplayTeacherInfoBean d = new DisplayTeacherInfoBean();
                d.setTeacherId(rs.getInt("teacher_id"));
                d.setTeacherName(rs.getString("teacher_name"));
                d.setSubject(rs.getString("subject_name"));
                d.setTeacherMobile(rs.getString("teacher_mobile"));
                d.setTeacherAddress(rs.getString("teacher_addr"));
                d.setTeacherJoinDate(rs.getDate("teacher_join_date"));
                d.setTeacherGrade(rs.getString("teacher_grade"));
                d.setTeacherEmail(rs.getString("teacher_email"));
                d.setDepartDate(rs.getString("depart_date"));
                d.setUserId(rs.getInt("user_id"));
                d.setUserName(rs.getString("uname"));
                list.add(d);
            }
            //teacher_id, subject_id, teacher_name, teacher_mobile, teacher_addr, teacher_join_date, teacher_grade_id, teacher_email, ispresent, depart_date, teacher_photo, user_id
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public List<DisplayTeacherInfoBean> getAllTeacherForPublic() {
        String sql = "SELECT t.teacher_id, sub.subject_name, t.teacher_name, "                
                + "g.teacher_grade, t.teacher_email, "
                + "t.teacher_photo "
                + "FROM (sch_teacher_info t inner join sch_teacher_grade g "
                + "on (t.teacher_grade_id=g.teacher_grade_id) "                
                + "inner join sch_subject sub "
                + "on(t.subject_id=sub.subject_id)) order by t.teacher_name;";
        List<DisplayTeacherInfoBean> list = new ArrayList<DisplayTeacherInfoBean>();
        try {
            Connection con = DBConnect.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                DisplayTeacherInfoBean d = new DisplayTeacherInfoBean();
                d.setTeacherId(rs.getInt("teacher_id"));
                d.setTeacherName(rs.getString("teacher_name"));
                d.setSubject(rs.getString("subject_name"));                
                d.setTeacherGrade(rs.getString("teacher_grade"));
                d.setTeacherEmail(rs.getString("teacher_email"));               
                list.add(d);
            }
            //teacher_id, subject_id, teacher_name, teacher_mobile, teacher_addr, teacher_join_date, teacher_grade_id, teacher_email, ispresent, depart_date, teacher_photo, user_id
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public List<TeacherBean> getAllSubject() {
        String sql = "SELECT * from sch_subject;";
        List<TeacherBean> data = new ArrayList<TeacherBean>();
        Statement st;
        try {
            Connection con = DBConnect.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TeacherBean tb = new TeacherBean();
                tb.setSubject(rs.getString("subject_name"));
                tb.setSubjectId(rs.getInt("subject_id"));
                data.add(tb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<TeacherBean> getAllTeacherGrade() {
        String sql = "SELECT * from sch_teacher_grade;";
        List<TeacherBean> data = new ArrayList<TeacherBean>();
        Statement st;
        try {
            Connection con = DBConnect.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TeacherBean tb = new TeacherBean();
                tb.setTeacherGrade(rs.getString("teacher_grade"));
                tb.setTeacherGradeId(rs.getInt("teacher_grade_id"));
                //teacher_grade_id, teacher_grade
                data.add(tb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public void updateTeacherInfo() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String join = df.format(this.getTeacherJoinDate());

        String sql = "UPDATE sch_teacher_info "
                + "SET subject_id=(SELECT subject_id FROM sch_subject WHERE subject_name=?), "
                + "teacher_name=?, teacher_mobile=?, teacher_addr=?, teacher_join_date=?, "
                + "teacher_grade_id=(SELECT teacher_grade_id FROM sch_teacher_grade WHERE teacher_grade=?), "
                + "teacher_email=?, depart_date=? where teacher_id=?";
        try {
            PreparedStatement ps = ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setInt(1, this.getSubjectId());
            ps.setString(2, this.getTeacherName());
            ps.setString(3, this.getTeacherMobile());
            ps.setString(4, this.getTeacherAddress());
            ps.setString(5, join);
            ps.setInt(6, this.getTeacherGradeId());
            ps.setString(7, this.getTeacherEmail());
            ps.setString(8, this.getDepartDate());
            ps.setInt(9, this.getTeacherId());
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Teacher Basic Information is Updated", "click Go Back"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }       
        
    }
    
    
     public void updateUserInfo() {
        String sql = "update sch_user set uname=?where user_id=?";
        try {
            PreparedStatement ps = ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setString(1, this.getUserName());
            //ps.setString(2, this.getNewPassword());
            ps.setInt(2, this.getUserId());
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("User Information is Updated", "click Go Back"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
     
    public List<DisplayTeacherInfoBean> getProfileInfo() {
        String sql = "SELECT t.teacher_id, (select s.subject_name from sch_subject s "
                + "where t.subject_id=s.subject_id ) as subject_name, t.teacher_name, "
                + "t.teacher_mobile, t.teacher_addr, t.teacher_join_date, "
                + "(select teacher_grade FROM sch_teacher_grade g where t.teacher_grade_id=g.teacher_grade_id)"
                + " as teacher_grade, t.teacher_email, t.ispresent, t.depart_date, t.teacher_photo FROM sch_teacher_info t "
                + "where t.user_id=(select u.user_id FROM sch_user u where u.uname=?);";
        List<DisplayTeacherInfoBean> list = new ArrayList<DisplayTeacherInfoBean>();
        PreparedStatement ps;
        HttpSession session = LoginUtil.getSession();
        String un = session.getAttribute("username").toString();
        try {
            ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setString(1, un);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DisplayTeacherInfoBean d = new DisplayTeacherInfoBean();
                d.setTeacherId(rs.getInt("teacher_id"));
                d.setTeacherName(rs.getString("teacher_name"));
                d.setSubject(rs.getString("subject_name"));
                d.setTeacherMobile(rs.getString("teacher_mobile"));
                d.setTeacherAddress(rs.getString("teacher_addr"));
                d.setTeacherJoinDate(rs.getDate("teacher_join_date"));
                d.setTeacherGrade(rs.getString("teacher_grade"));
                d.setTeacherEmail(rs.getString("teacher_email"));
                d.setDepartDate(rs.getString("depart_date"));
                d.setUserName(un);
               
                list.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    
}
