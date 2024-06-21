/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.teacher;

import java.sql.PreparedStatement;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import school.util.DBConnect;

/**
 *
 * @author Enamul
 */
public class UpdateTeacherProfile {

    private DisplayTeacherInfoBean data;
    private String teacherMobile;
    private String teacherAddress;
    private String teacherEmail;
    private int teacherId;

    /**
     * Creates a new instance of UpdateTeacherProfile
     */
    public UpdateTeacherProfile() {
    }

    public DisplayTeacherInfoBean getData() {
        return data;
    }

    public void setData(DisplayTeacherInfoBean data) {
        this.data = data;
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

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public void doSetData() {
        setTeacherAddress(getData().getTeacherAddress());
        setTeacherEmail(getData().getTeacherEmail());
        setTeacherMobile(getData().getTeacherMobile());
        setTeacherId(getData().getTeacherId());

    }

    public void upadetTeacherProfile() {
        String sql = "update sch_teacher_info set teacher_mobile=?, teacher_addr=?, teacher_email=? where teacher_id=?";

        try {
            PreparedStatement ps = ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setString(1, this.getTeacherMobile());
            ps.setString(2, this.getTeacherAddress());
            ps.setString(3, this.getTeacherEmail());
            ps.setInt(4, this.getTeacherId());
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Profile is updated", "Click Go Back");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
