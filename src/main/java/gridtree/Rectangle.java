package gridtree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Range rectangle.
 *
 * Created by Zihao.Liu on 2019/4/3.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rectangle {

  private Point bottomLeft;
  private Point topRight;

  public Rectangle(Rectangle rect) {
    bottomLeft = new Point(rect.getBottomLeft());
    topRight = new Point(rect.getTopRight());
  }
  
  /**
   * Compare whether a point is inside this rectangle, bottom and left side determined by >,
   * top and right side determined by <=. If bottom left point equals top right point, compare
   * the point directly.
   */
  public boolean contains(Point point) {
    if (bottomLeft.equals(topRight)) {
      return topRight.equals(point);
    } else {
      return !(point.getX() <= bottomLeft.getX() || point.getY() <= bottomLeft.getY()
          || point.getX() > topRight.getX() || point.getY() > topRight.getY());
    }
  }

  public boolean contains(Rectangle rect) {
    return !(rect.getBottomLeft().getX() <= bottomLeft.getX() 
        || rect.getBottomLeft().getY() <= bottomLeft.getY()
        || rect.getTopRight().getX() > topRight.getX()
        || rect.getTopRight().getY() > topRight.getY());
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Rectangle rectangle = (Rectangle) o;

    return (bottomLeft != null ? bottomLeft.equals(rectangle.bottomLeft)
        : rectangle.bottomLeft == null) && (topRight != null ? topRight.equals(rectangle.topRight)
        : rectangle.topRight == null);
  }

}
