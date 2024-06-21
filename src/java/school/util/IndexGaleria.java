/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.util;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author MASHUK
 */
public class IndexGaleria implements java.io.Serializable {

  private List<IndexGaleria> images = new ArrayList<IndexGaleria>();
  private String image;
  private String caption;

  /** Creates a new instance of IndexGaleria */
  public IndexGaleria() {
  }

  @PostConstruct
  public void init() {
    for (int i = 1; i < 7; i++) {
      IndexGaleria ig = new IndexGaleria();
      ig.setImage("slide" + i + ".jpg");
      ig.setCaption("Nature " + i);
      images.add(ig);
    }

  }

  public List<IndexGaleria> getImages() {
    return images;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
