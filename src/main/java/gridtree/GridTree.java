package gridtree;

import java.util.Iterator;
import lombok.Data;

/**
 * Created by Zihao.Liu on 2019/4/4.
 */
@Data
public class GridTree {

  public static Integer M = 5;
  public static Integer MIN = Integer.MIN_VALUE;
  public static Integer MAX = Integer.MAX_VALUE;

  private Node root;
  private DataBlock dataBlock;

  /**
   * Init GirdTree with min bottom left and max top right, insert a init
   */
  public void init() {
    root = new Node();
    root.setIsLeaf(true);
    root.setRange(new Rectangle(new Point(MIN, MIN), new Point(MAX, MAX)));
    dataBlock = new DataBlock();
  }

  /**
   * Insert a point as a MBB with bottom left and top right itself.
   */
  public void insert(Point point, String primaryKey) {
    Node leafNode = findLeaf(root, point);
    if (leafNode == null) {
      System.out.println("Could not find proper node.");
      return;
    }
    DataElement dataElem = new DataElement(point, primaryKey);
    dataBlock.add(dataElem);
    NodeElement nodeElem = new NodeElement();
    nodeElem.setRange(new Rectangle(point, point));
    nodeElem.setPointer(dataElem);
    leafNode.add(nodeElem);
    adjustTree(leafNode);  
  }

  /**
   * Delete a point with data element.
   */
  public void delete(Point point) {
    Node leafNode = findLeaf(root, point);
    if (leafNode == null) {
      System.out.println("Could not find proper node.");
      return;
    }
    NodeElement nodeElem = leafNode.remove(point);
    DataElement dataElem = (DataElement) nodeElem.getPointer();
    dataBlock.remove(dataElem);
    adjustTree(leafNode);
  }
  
  /**
   * Find a proper leaf node to store new point. 
   * @param current
   * @param point
   * @return
   */
  public Node findLeaf(Node current, Point point) {
    if (current.getRange().contains(point)) {
      if (current.getIsLeaf()) {
        return current;
      } else {
        for (NodeElement e : current.getElements()) {
          if (e.getRange().contains(point)) {
            return findLeaf((Node) e.getPointer(), point);
          }
        }
        return null;
      }
    } else {
      return null;
    }
  }
  
  /**
   * Adjust grid tree when a node has more than M-1 elements or less than M/2 elements. 
   */
  public void adjustTree(Node current) {
    if (current.getLength() > M - 1) {
      // Node is full, need split
      Rectangle curRange = current.getRange();
      
      // create new node
      Node newer = new Node();
      newer.setIsLeaf(true);
      newer.setRange(new Rectangle(curRange));
      newer.setParent(current.getParent());
      
      // split space
      Point bottomLeft = new Point(MAX, MAX); 
      Point topRight = new Point(MIN, MIN);
      for (NodeElement e : current.getElements()) {
        bottomLeft.adjustBottomLeft(e.getRange().getBottomLeft());
        topRight.adjustTopRight(e.getRange().getTopRight());
      }
      double deltaY = topRight.getY() - bottomLeft.getY();
      double deltaX = topRight.getX() - bottomLeft.getX();
      
      if (deltaX > deltaY) {
        double mid = deltaX / 2;
        current.getRange().getTopRight().setX(mid);
        newer.getRange().getBottomLeft().setX(mid);
      } else {
        double mid = deltaY / 2;
        current.getRange().getTopRight().setY(mid);
        newer.getRange().getBottomLeft().setY(mid);
      }
      
      // re-order elements
      for (Iterator<NodeElement> iterator = current.getElements().iterator(); iterator.hasNext();) {
        NodeElement e = iterator.next();
        if (!current.getRange().contains(e.getRange())) {
          e.setParent(newer);
          newer.add(e);
          iterator.remove();
        }
      }
      
      // adjust parent
      if (current.getParent() == null) {
        // current is root, create new root
        Node newRoot = new Node();
        newRoot.setRange(new Rectangle(new Point(MIN, MIN), new Point(MAX, MAX)));
        // put current and newer node as elements of new root
        NodeElement curElem = new NodeElement();
        curElem.setRange(current.getRange());
        curElem.setPointer(current);
        NodeElement newElem = new NodeElement();
        newElem.setRange(newer.getRange());
        newElem.setPointer(newer);
        newRoot.add(curElem);
        newRoot.add(newElem);
        root = newRoot;
        current.setParent(root);
        newer.setParent(root);
      } else {
        // current is not root
        Node parent = current.getParent();
        
      }
      
    } else if (current.getLength() < M / 2) {
      // Node is not enough, need merge.
    }
    
  }
  
}
