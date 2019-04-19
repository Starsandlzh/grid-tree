package gridtree;

import java.util.Random;

/**
 * Created by Zihao.Liu on 2019/4/17.
 */
public class Test {

  public static void main(String[] args) {

    GridTree tree = new GridTree();
    tree.init();
    for (int i = 0; i < 100; i++) {
      tree.insert(generatePoint(), "key" + i);
    }
    System.out.println(tree.getRoot().getLength());
  }
  
  public static Point generatePoint() {
    Random rnd = new Random();
    int x = rnd.nextInt(100);
    int y = rnd.nextInt(100);
    return new Point(x, y);
  }
  
}
