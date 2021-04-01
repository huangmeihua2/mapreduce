package graph;

import java.util.ArrayList;
public class nodes {
    public int id;//图中对应节点的值
    public ArrayList<nodes> nexts;//该节点对应的所有的next节点
    public ArrayList<edges> fromedges;//该节点对应所有能够连通的边
    public nodes(int value) {
        this.id=value;
        this.nexts=new ArrayList<nodes>();
        this.fromedges=new ArrayList<edges>();
    }
}

