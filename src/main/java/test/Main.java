package test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        int[][] save=new int[m][2];
        HashSet<Integer> set=new HashSet<Integer>();
        for(int i=0;i<m;i++){
            save[i][0]=in.nextInt();
            save[i][1]=in.nextInt();
        }
        HashMap<Integer,ArrayList<Integer>> mark=new HashMap();
        for(int i=0;i<m;i++){
            int from=save[i][0];
            int to=save[i][1];
            if(!mark.containsKey(from)){
                ArrayList<Integer> one=new ArrayList<Integer>();
                one.add(to);
                mark.put(from,one);
            }else{
                ArrayList<Integer> tmp=mark.get(from);
                tmp.add(to);
            }

        }
        int q=in.nextInt();
        for(int i=0;i<q;i++){
            int x=in.nextInt();
            int y=in.nextInt();
            Queue<Integer> path=new LinkedList();
            path.add(x);
            int res=0;
            boolean find=true;
            while(!path.isEmpty()){
                ArrayList<Integer> cur=mark.get(path.poll());
                res++;
                for(Integer c:cur){
                    if(c==y){
                        System.out.println(res);
                        find=false;
                        break;
                    }else{
                        path.add(c);
                    }
                }
            }
            if(!find){
                System.out.println(-1);
                break;
            }
        }

    }


}
