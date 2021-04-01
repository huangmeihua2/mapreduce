package Mapjoin;

import lombok.*;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TableBean implements Writable{

    /**
     * orderId : 订单号    =====> 订单表
     * pid    : 产品号     =====> 订单表\产品表
     * amount : 产品数量   =====> 订单表
     * pname  : 产品名     =====> 产品表
     * flag   : 两个表的标记
     */
    private String orderId1;
    private String pid;
    private int amount;
    private String pname1;
    private String flag;

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(orderId1);
        dataOutput.writeUTF(pid);
        dataOutput.writeInt(amount);
        dataOutput.writeUTF(pname1);
        dataOutput.writeUTF(flag);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.orderId1 = dataInput.readUTF();
        this.pid = dataInput.readUTF();
        this.amount = dataInput.readInt();
        this.pname1 = dataInput.readUTF();
        this.flag = dataInput.readUTF();
    }

    @Override
    public String toString() {
        //订单号   产品名 产品数量
        return orderId1 + "\t" + pname1 + "\t" + amount;
    }
}
