/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.timetable;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author MASHUK
 */
@ManagedBean
public class DailyRoutine {
  private String period;
  private String subject;
  private String teacher;

  public DailyRoutine() {
  }

  public String getPeriod() {
    return period;
  }

  public void setPeriod(String period) {
    this.period = period;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getTeacher() {
    return teacher;
  }

  public void setTeacher(String teacher) {
    this.teacher = teacher;
  }
  
}
