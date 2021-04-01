package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
public class graphmap {
    public HashMap<Integer,nodes> nodesmap;//图中所有节点及其值的映射，便于通过值来访问。
    public HashSet<edges> edgesmap;//图中所有的边，与nodes类属性有冗余，但是可以便于操作边
    public graphmap() {
        this.edgesmap=new HashSet<edges>();
        this.nodesmap=new HashMap<Integer,nodes>();
    }
}
