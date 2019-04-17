package gridtree;

import com.sun.org.apache.xpath.internal.operations.Bool;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * Grid tree node.
 * Created by Zihao.Liu on 2019/4/3.
 */
@Data
public class Node {

  private Boolean isLeaf = false;
  private Node parent;
  private Rectangle range;
  private Integer length = 0;
  List<NodeElement> elements = new ArrayList<NodeElement>();
  
  public void add(NodeElement element) {
    length++;
    elements.add(element);
  }
  
  public void remove(NodeElement element) {
    length--;
    elements.remove(element);
  } 
  
  public NodeElement remove(Point p) {
    NodeElement flag = null;
    for (NodeElement e : elements) {
      if (e.getRange().contains(p)) {
        flag = e;
        break;
      }
    }
    if (flag != null) {
      remove(flag);
      return flag;
    } else {
      return null;
    }
  }
  
}

@Data
class NodeElement {
  
  private Rectangle range;
  private Object pointer;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    NodeElement that = (NodeElement) o;

    return range != null ? range.equals(that.range) : that.range == null;
  }
  
}
