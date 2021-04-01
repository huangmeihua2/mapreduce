package graph;

public class edges {
    public int weight;//对应边的权重
    public nodes from;//from节点
    public nodes to;//to节点
    public edges(int wei,nodes from,nodes to) {
        this.weight=wei;
        this.from=from;
        this.to=to;
    }
}
