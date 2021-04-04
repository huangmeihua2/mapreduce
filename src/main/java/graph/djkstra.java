package graph;
import java.util.*;

public class djkstra {
    //得到最短距离表及图中任意两个节点之间的最短路径节点
   public ArrayList<nodes>[][] helpreturn(graphmap cur){
        int size=cur.nodesmap.size();
       ArrayList<nodes>[][] all_short_path=new ArrayList[size][size];
       int i=0;
       int j=0;
        for(Map.Entry<Integer,nodes> from:cur.nodesmap.entrySet()){
            parentmap=new HashMap<nodes,nodes>();
            HashMap<nodes, Integer> res=dikstrafind(from.getValue());
            ArrayList<nodes> one=new ArrayList<>();
            for(Map.Entry<Integer,nodes> end:cur.nodesmap.entrySet()){
                if(end.getValue().equals(from.getValue())) one.add(from.getValue());
                else{
                    one=helpreturn(end.getValue());
                }
                all_short_path[i][j++]=one;
            }
            i++;
        }
        return all_short_path;
   }
    //由前驱节点表进行最短路径还原
   private ArrayList<nodes> helpreturn(nodes end){
       ArrayList<nodes> res=new ArrayList<nodes>();
       while(!end.equals(parentmap.get(end))){
           res.add(end);
           end=parentmap.get(end);
       }
       return res;
   }
    //给定一个图中的节点，可以找到从该节点出发到图中所有其它节点之间的最短路径map表
    //及前驱节点表
    HashMap<nodes,nodes> parentmap1;
    public  HashMap<nodes, Integer> dikstrafind(nodes from) {
        HashMap<nodes, Integer> mindistancemap = new HashMap<nodes, Integer>();
        //记录from节点到图中所有节点的最短的距离。
        parentmap=new HashMap<nodes,nodes>();
        mindistancemap.put(from, 0);//from到自己节点的距离是为0的。
        parentmap.put(from,from);
        HashSet<nodes> lockednode = new HashSet<nodes>();//存储已经作为跳跃点的节点，进行锁定。
        nodes minnode = getmin_and_unlockednode(mindistancemap, lockednode);
        //得到map表中没有被锁定的节点，并且当前距离最小的值的节点作为跳点，跳往该节点的所有下级节点。O(n^2)的时间复杂度。
        while (minnode != null) {
            int minsiatance = mindistancemap.get(minnode);
            //map表中的from节点到该minnode节点的当前最短的距离，也是当前map中的最短距离
            for (edges next : minnode.fromedges) {//遍历该节点的所有边，更新from节点到图中各节点的最短距离
                if (!mindistancemap.containsKey(next.to)) {
                    parentmap.put(next.to,minnode);
                    mindistancemap.put(next.to, minsiatance + next.weight);
                    //map表中已经不存在了from节点到该边所达到的节点next.to的最短距离，
                    // 那么直接进行更新，即从当前最小距离节点进行跳跃到next.to点形成更短的距离。
                    //同时直接更新它的最短路径上的前驱节点。
                } else {
                    if(mindistancemap.get(next.to)>(minsiatance + next.weight)){
                        //若已经存在了，那么就和原来的最短距离进行比较看那个形成的更小。
                        //同时维护它的前驱节点。
                        parentmap.put(next.to,minnode);
                        mindistancemap.put(next.to,minsiatance + next.weight);
                    }
                }
            }
            lockednode.add(minnode);//minnode节点已经作为过跳跃节点了，进行锁定。
            minnode = getmin_and_unlockednode(mindistancemap, lockednode);
            //表中的找下一个最短距离的节点，并且没有作为过中间跳跃节点的节点
        }
        return mindistancemap;
    }
    //找出map表中的最段距离的节点，并且没有被锁定即没有作为过跳跃节点的节点
    public static nodes getmin_and_unlockednode(HashMap<nodes, Integer> mindistancemap, HashSet<nodes> lockednode) {
        int middisatance = Integer.MAX_VALUE;
        nodes cur = null;
        for (Map.Entry<nodes, Integer> w : mindistancemap.entrySet()) {
            nodes cu = w.getKey();
            int fromdistance = w.getValue();
            if (!lockednode.contains(cu) && fromdistance < middisatance) {
                cur = cu;
                middisatance = fromdistance;
            }
        }
        return cur;
    }

    HashMap<nodes,Integer> mindistance;
    HashMap<nodes,nodes> parentmap;
    public boolean BELLMAN_FORD(nodes from,graphmap test){
        mindistance=new HashMap<nodes,Integer>();
        parentmap=new HashMap<nodes,nodes>();
        for(int i = 0;i<test.edgesmap.size()-1;i++){
          for(edges edge:test.edgesmap){
              RELAX(edge);
          }
        }
        for(edges edge:test.edgesmap){
                nodes v1 = edge.from;
                nodes v2 = edge.to;
                int w = edge.weight;
                if(mindistance.get(v2)>mindistance.get(v1)+w)
                    return false;//存在复权重的边。
        }
        return true;
    }
    //对每一条边进行松弛操作。
    public void RELAX(edges edge){
        nodes v1 = edge.from;
        nodes v2 = edge.to;
        int w = edge.weight;
        if(mindistance.get(v2)>mindistance.get(v1)+w){//当前存储的距离更大，则进行更新
            mindistance.put(v2,mindistance.get(v1)+w);
            parentmap.put(v2,v1);
        }
    }

    int[][] matrix;
   /*public void floyd(nodes[][] path,graphmap test) {
        matrix=new int[test.nodesmap.size()][test.nodesmap.size()];
        for (int i = 0; i < test.nodesmap.size(); i++) {
            for (int j = 0; j < test.nodesmap.size(); j++) {
                path[i][j] = test.nodesmap.get(j);//// "顶点i"到"顶点j"的最短路径是经过顶点j。
                matrix[i][j]=test.nodesmap.get(i).fromedges.get();//// "顶点i"到"顶点j"的路径长度为"i到j的权值"。

            }
        }

        for (int m = 0; m < test.nodesmap.size(); m++) {
            for (int i = 0; i < test.nodesmap.size(); i++) {
                for (int j = 0; j <test.nodesmap.size(); j++) {
                    if (matrix[i][m] + matrix[m][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][m] + matrix[m][j];
                        //记录经由哪个点到达
                        path[i][j] = path[i][m];
                    }
                }
            }
        }
    }*/
}