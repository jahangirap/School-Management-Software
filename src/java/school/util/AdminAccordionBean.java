/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.util;

import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author MASHUK
 */
public class AdminAccordionBean implements java.io.Serializable {
  
  private String activeIndex = "0";

  /** Creates a new instance of AdminAccordionBean */
  public AdminAccordionBean() {
  }

  public String getActiveIndex() {
    return activeIndex;
  }

  public void setActiveIndex(String activeIndex) {
    this.activeIndex = activeIndex;
  }

  public void onTabChange(TabChangeEvent te) {
//    String currentIndex = ((AccordionPanel) te.getComponent()).getActiveIndex();
//    System.out.println(currentIndex+1);
//    setActiveIndex(currentIndex);
  String tt =  te.getTab().getTitle();
    if (tt.equalsIgnoreCase("Academic")) {
      setActiveIndex("0");
    }
    if (tt.equalsIgnoreCase("Class Routine")) {
      setActiveIndex("1");
    }
    if (tt.equalsIgnoreCase("Student")) {
      setActiveIndex("2");
    }
    if (tt.equalsIgnoreCase("Teacher")) {
      setActiveIndex("3");
    }
    if (tt.equalsIgnoreCase("Notice")) {
      setActiveIndex("4");
    }
    if (tt.equalsIgnoreCase("Others")) {
      setActiveIndex("5");
    }
  }
}
