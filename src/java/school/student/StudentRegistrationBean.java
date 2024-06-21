/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import school.academic.ClassBean;
import school.academic.GroupBean;
import school.academic.SectionBean;
import school.util.DBConnect;

/**
 *
 * @author MASHUK
 */
public class StudentRegistrationBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int studentRegistrationId;
    private int studentId;
    private int academicYearId;
    private int classId;
    private int sectionId;
    private int groupId;
    private int academicYear;
    private int studentRollNo;
    private String className;
    private String sectionName;
    private Date registrationDate;
    private StudentRagistrationFeeBean registrationFee;

    /** Creates a new instance of StudentRegistrationBean */
    public StudentRegistrationBean() {
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    public int getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(int academicYearId) {
        this.academicYearId = academicYearId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public StudentRagistrationFeeBean getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(StudentRagistrationFeeBean registrationFee) {
        this.registrationFee = registrationFee;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentRegistrationId() {
        return studentRegistrationId;
    }

    public void setStudentRegistrationId(int studentRegistrationId) {
        this.studentRegistrationId = studentRegistrationId;
    }

    public int getStudentRollNo() {
        return studentRollNo;
    }

    public void setStudentRollNo(int studentRollNo) {
        this.studentRollNo = studentRollNo;
    }
   
}
