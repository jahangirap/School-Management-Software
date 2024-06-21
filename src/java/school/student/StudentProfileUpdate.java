/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.student;

import java.sql.PreparedStatement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import school.util.DBConnect;

/**
 *
 * @author Enamul
 */
public class StudentProfileUpdate {

    private DisplayAllStudentInfoBean data;
    private String parmanetAddress;
    private String telephoneNo;
    private String presentAddress;
    private String mobileNo;
    private int contactId;

    /**
     * Creates a new instance of StudentProfileUpdate
     */
    public StudentProfileUpdate() {
    }

    public DisplayAllStudentInfoBean getData() {
        return data;
    }

    public void setData(DisplayAllStudentInfoBean data) {
        this.data = data;
    }

    public String getParmanetAddress() {
        return parmanetAddress;
    }

    public void setParmanetAddress(String parmanetAddress) {
        this.parmanetAddress = parmanetAddress;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public void doSetData() {
        setPresentAddress(getData().getPresentAddress());
        setParmanetAddress(getData().getParmanetAddress());
        setMobileNo(getData().getMobileNo());
        setTelephoneNo(getData().getTelephoneNo());
        setContactId(getData().getContactId());
    }

    public void updateContact() {
        String sql = "update sch_std_contact_info set tel_no=?, mobile_no=?, present_addr=?, parmanent_addr=? where contact_id=?";
        try {
            PreparedStatement ps = ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setString(1, this.getTelephoneNo());
            ps.setString(2, this.getMobileNo());
            ps.setString(3, this.getPresentAddress());
            ps.setString(4, this.getParmanetAddress());
            ps.setInt(5, this.getContactId());
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
