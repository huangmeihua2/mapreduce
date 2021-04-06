package NIO;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class testip {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> net=NetworkInterface.getNetworkInterfaces();
            while(net.hasMoreElements()){
                NetworkInterface each=net.nextElement();
                System.out.println("网络设备名称"+each.getName());
                System.out.println("网络设备名称1"+each.getDisplayName());
                System.out.println("网络设备名称"+each.getIndex());
                System.out.println("iswork"+" "+each.isUp());
                System.out.println("isloopback"+" "+each.isLoopback());
                System.out.println("isloopback"+" "+each.getMTU());
                byte[] byteArray=each.getHardwareAddress();
                if(byteArray!=null&&byteArray.length!=0){
                    for(int i=0;i<byteArray.length;i++){
                        System.out.print(byteArray[i]+" ");
                    }
                    System.out.println(" ");
                }
                System.out.println(" ");
                System.out.println(" ");
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
