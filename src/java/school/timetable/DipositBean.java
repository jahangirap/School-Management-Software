/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.timetable;

/**
 *
 * @author Enamul
 */
public class DipositBean implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
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

  /**
   * Creates a new instance of DipositBean
   */
  public DipositBean(String groupName, String className, String sectionName, String teacherName, String subjectName, String dptime, String day) {
    this.groupName = groupName;
    this.className = className;
    this.sectionName = sectionName;
    this.teacherName = teacherName;
    this.subjectName = subjectName;
    this.dptime = dptime;
    this.day = day;

  }

  /**
   * @return the timTableId
   */
  public int getTimTableId() {
    return timTableId;
  }

  /**
   * @param timTableId the timTableId to set
   */
  public void setTimTableId(int timTableId) {
    this.timTableId = timTableId;
  }

  /**
   * @return the teacherId
   */
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
   * @return the subjectId
   */
  public int getSubjectId() {
    return subjectId;
  }

  /**
   * @param subjectId the subjectId to set
   */
  public void setSubjectId(int subjectId) {
    this.subjectId = subjectId;
  }

  /**
   * @return the periodId
   */
  public int getPeriodId() {
    return periodId;
  }

  /**
   * @param periodId the periodId to set
   */
  public void setPeriodId(int periodId) {
    this.periodId = periodId;
  }

  /**
   * @return the teacherName
   */
  public String getTeacherName() {
    return teacherName;
  }

  /**
   * @param teacherName the teacherName to set
   */
  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  /**
   * @return the subjectName
   */
  public String getSubjectName() {
    return subjectName;
  }

  /**
   * @param subjectName the subjectName to set
   */
  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  /**
   * @return the period
   */
  public String getPeriod() {
    return period;
  }

  /**
   * @param period the period to set
   */
  public void setPeriod(String period) {
    this.period = period;
  }

  /**
   * @return the day
   */
  public String getDay() {
    return day;
  }

  /**
   * @param day the day to set
   */
  public void setDay(String day) {
    this.day = day;
  }

  /**
   * @return the days
   */
  public String[] getDays() {
    return days;
  }

  /**
   * @param days the days to set
   */
  public void setDays(String[] days) {
    this.days = days;
  }

  /**
   * @return the groupName
   */
  public String getGroupName() {
    return groupName;
  }

  /**
   * @param groupName the groupName to set
   */
  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  /**
   * @return the groupId
   */
  public int getGroupId() {
    return groupId;
  }

  /**
   * @param groupId the groupId to set
   */
  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  /**
   * @return the className
   */
  public String getClassName() {
    return className;
  }

  /**
   * @param className the className to set
   */
  public void setClassName(String className) {
    this.className = className;
  }

  /**
   * @return the classId
   */
  public int getClassId() {
    return classId;
  }

  /**
   * @param classId the classId to set
   */
  public void setClassId(int classId) {
    this.classId = classId;
  }

  /**
   * @return the sectionName
   */
  public String getSectionName() {
    return sectionName;
  }

  /**
   * @param sectionName the sectionName to set
   */
  public void setSectionName(String sectionName) {
    this.sectionName = sectionName;
  }

  /**
   * @return the sectionId
   */
  public int getSectionId() {
    return sectionId;
  }

  /**
   * @param sectionId the sectionId to set
   */
  public void setSectionId(int sectionId) {
    this.sectionId = sectionId;
  }

  /**
   * @return the dptime
   */
  public String getDptime() {
    return dptime;
  }

  /**
   * @param dptime the dptime to set
   */
  public void setDptime(String dptime) {
    this.dptime = dptime;
  }

  /**
   * @return the dpid
   */
  public int getDpid() {
    return dpid;
  }

  /**
   * @param dpid the dpid to set
   */
  public void setDpid(int dpid) {
    this.dpid = dpid;
  }
}
