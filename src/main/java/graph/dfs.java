package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class dfs {
    public static void dfs(nodes node) {
        if (node == null) {
            return;
        }
        Stack<nodes> stack = new Stack<>();
        HashSet<nodes> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.id);
        while (!stack.isEmpty()) {
            nodes cur = stack.pop();
            for (nodes next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.id);
                    break;
                }
            }
        }
    }
    int min=Integer.MAX_VALUE;
    ArrayList<nodes> finalpath;
    public  void dfs1(nodes start, nodes ende,ArrayList<nodes> path,HashSet<nodes> save,int m) {
        if (start== null||ende==null) {
            return;
        }
        if(start==ende){
            if(m<min){
                finalpath=path;//头尾两个节点没有不会包含在里面
                min=m;
            }
        }
        for(edges next:start.fromedges){
            if(!save.contains(next.to)){
                path.add(next.to);
                save.add(next.to);
                dfs1(next.to,ende,path,save,m+next.weight);
                path.remove(next.to);
                save.remove(next.to);
            }
        }
    }


}

