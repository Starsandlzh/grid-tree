package gridtree;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data block for storing secondary index items.
 * Created by Zihao.Liu on 2019/4/3.
 */
@Data
public class DataBlock {

  private Integer length = 0;
  private List<DataElement> elements = new ArrayList<DataElement>();

  public void add(DataElement element) {
    elements.add(element);
    length++;
  }
  
  public void remove(DataElement element) {
    elements.remove(element);
    length--;
  }
  
}

@Data
@NoArgsConstructor
class DataElement {
  
  private Point value;
  private String primaryKey;
  private Long timestamp;
  
  public DataElement(Point value, String primaryKey) {
    this.value = value;
    this.primaryKey = primaryKey;
    timestamp = System.currentTimeMillis();
  }
  
}