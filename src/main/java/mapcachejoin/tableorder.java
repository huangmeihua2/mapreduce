package mapcachejoin;

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
public class tableorder implements Writable {
    /**
     * orderId : 订单号    =====> 订单表//产品表没有订单好
     * pid    : 产品号     =====> 订单表\产品表
     * amount : 产品数量   =====> 订单表//产品表没有数量
     * pname  : 产品名     =====> 产品表//订单表没有产品名字
     * flag   : 两个表的标记
     */
    private String orderId;
    private String pid;
    private int amount;
    private String pname;
    private String flag;//打标签以区分不同的表源

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(orderId);
        dataOutput.writeUTF(pid);
        dataOutput.writeInt(amount);
        dataOutput.writeUTF(pname);
         dataOutput.writeUTF(flag);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.orderId = dataInput.readUTF();
        this.pid = dataInput.readUTF();
        this.amount = dataInput.readInt();
        this.pname = dataInput.readUTF();
        this.flag = dataInput.readUTF();
    }
    @Override
    public String toString() {
        //订单号   产品名 产品数量
        return orderId + "\t" + pname + "\t" + amount;
    }
}
