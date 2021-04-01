package graph;

public class GraphGenerator {

    // matrix 所有的边
    // N*3 的矩阵
    // [weight, from节点上面的值，to节点上面的值]
    public  graphmap createGraph(Integer[][] matrix) {
        graphmap graph = new graphmap();
        for (int i = 0; i < matrix.length; i++) { // matrix[0][0], matrix[0][1]  matrix[0][2]
            Integer from = matrix[i][0];
            Integer to = matrix[i][1];
            Integer weight = matrix[i][2];
            if (!graph.nodesmap.containsKey(from)) {
                graph.nodesmap.put(from, new nodes(from));
            }
            if (!graph.nodesmap.containsKey(to)) {
                graph.nodesmap.put(to, new nodes(to));
            }
            nodes fromNode = graph.nodesmap.get(from);
            nodes toNode = graph.nodesmap.get(to);
            edges newEdge = new edges(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.fromedges.add(newEdge);
            graph.edgesmap.add(newEdge);
        }
        return graph;
    }
    //matri[][]=0或者1，二维矩阵的情况，是否相连接的两个点。
    public  graphmap createGraph1(int[][] matrix) {
        graphmap graph = new graphmap();
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                int conne=matrix[i][j];
                if (!graph.nodesmap.containsKey(i)) {
                    graph.nodesmap.put(i, new nodes(i));
                }
                if (!graph.nodesmap.containsKey(j)) {
                    graph.nodesmap.put(j, new nodes(j));
                }
                if(conne==1){
                    //两个节点是相连的，那么取出来进行映射
                    nodes fromNode = graph.nodesmap.get(i);
                    nodes toNode = graph.nodesmap.get(j);
                    edges newEdge = new edges(10,fromNode,toNode);
                    fromNode.nexts.add(toNode);
                    fromNode.fromedges.add(newEdge);
                    graph.edgesmap.add(newEdge);
                }
            }
        }
        return graph;
    }
}

