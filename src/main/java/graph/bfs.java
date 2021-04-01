package graph;

import java.util.*;

public class bfs {
    // 从node出发，进行宽度优先遍历
    public static void bfs(nodes node) {
        if (node == null) {
            return;
        }
        Queue<nodes> queue = new LinkedList<>();
        HashSet<nodes> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            nodes cur = queue.poll();
            System.out.println(cur.id);
            for (nodes next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
    public static ArrayList<nodes> bfs(nodes start, nodes end) {
        if (start == null || end== null) {
            return null;
        }
        ArrayList<nodes> one=new ArrayList<>();
        Queue<nodes> queue = new LinkedList<>();
        HashSet<nodes> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        HashMap<ArrayList<nodes>,Integer> save=new HashMap<ArrayList<nodes>,Integer>();
        while (!queue.isEmpty()){
            nodes cur = queue.poll();
            System.out.println(cur.id);
            for (nodes next : cur.nexts) {
                if(next==end){

                }
                if (!set.contains(next)){
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}

