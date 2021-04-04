package NIO;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class testBuffer {
    public static void main(String[] args) {
        byte[] buterray=new byte[]{1,2,3};
        short[] shorbutarray=new short[]{1,2,3,4};
        int[] intbutearray=new int[]{1,2,3,5,4};
        long[] longbutearray=new long[]{1,2,3,4,6};
        float[] flaotarray=new float[]{1,2,3};
        double[] dfd=new double[]{1.0,2.0,3.0};
        char[] dfdf=new char[]{'f','f','f'};
        ByteBuffer bc = ByteBuffer.wrap(buterray);
        ByteBuffer.allocateDirect(20);
        CharBuffer cc=CharBuffer.allocate(20);
        System.out.println(cc.position()+" "+cc.limit());
        cc.put("我是中国人我在中华人民共和国");
        System.out.println(cc.position()+" "+cc.limit());
        cc.position(0);
        System.out.println(cc.position()+" "+cc.limit());
        for(int i=0;i<cc.limit();i++){
            System.out.print(cc.get());
        }
        System.out.println(" ");
        System.out.println(cc.position()+" "+cc.limit());

        cc.clear();
        System.out.println(cc.position()+" "+cc.limit());
        cc.put("我是中国人我在中华人民共和国");
        System.out.println("A"+cc.position()+" "+cc.limit());
        cc.flip();
        System.out.println("B"+cc.position()+" "+cc.limit());
        for(int i=0;i<cc.limit();i++){
            System.out.print(cc.get());

        }
        System.out.println(" ");
        System.out.println("c"+cc.position()+" "+cc.limit());
    }
}
