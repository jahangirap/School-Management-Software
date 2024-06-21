/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.student;

/**
 *
 * @author MASHUK
 */
public class StudentContactBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int studentId;
    private String telephoneNo;
    private String mobileNo;
    private String presentAddress;
    private String parmanetAddress;

    /** Creates a new instance of StudentContactBean */
    public StudentContactBean() {
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getParmanetAddress() {
        return parmanetAddress;
    }

    public void setParmanetAddress(String parmanetAddress) {
        this.parmanetAddress = parmanetAddress;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }
}
