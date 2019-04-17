package gridtree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Zihao.Liu on 2019/4/3.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point {

  private double x;
  private double y;
  
  public Point(Point p) {
    x = p.getX();
    y = p.getY();
  }
  
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public void adjustTopRight(Point p) {
    if (p.getX() > x) {
      x = p.getX();
    }
    if (p.getY() > y) {
      y = p.getY();
    }
  }
  
  public void adjustBottomLeft(Point p) {
    if (p.getX() < x ) {
      x = p.getX();
    }
    if (p.getY() < y) {
      y = p.getY();
    }
  }
  
  public boolean equals(Point p) {
    if (p == null) {
      return false;
    }
    return x == p.getX() && y == p.getY();
  }
  
}
