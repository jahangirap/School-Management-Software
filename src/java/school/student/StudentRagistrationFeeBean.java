/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.student;

import java.util.Date;

/**
 *
 * @author MASHUK
 */
public class StudentRagistrationFeeBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int registrationFeeId;
    private int registrationId;
    private double feeAmount;
    private Date payDate;

    /** Creates a new instance of StudentRagistrationFeeBean */
    public StudentRagistrationFeeBean() {
    }

    public double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public int getRegistrationFeeId() {
        return registrationFeeId;
    }

    public void setRegistrationFeeId(int registrationFeeId) {
        this.registrationFeeId = registrationFeeId;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }
}
