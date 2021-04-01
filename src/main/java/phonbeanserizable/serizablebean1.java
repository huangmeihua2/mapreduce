package phonbeanserizable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class serizablebean1 implements WritableComparable<serizablebean1>{

    private long upFlow;
    private long downFlow;
    private long sumFlow;


    public void set1(long upFlow, long downFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }

    /**
     *序列化方法
     * 注意：1）类型：long 对应 writeLong   String 对应 writeUTF
     *      2）顺序：write序列化和readFields反序列反序列化的字段顺序要一致
     */

    @Override
    public int compareTo(serizablebean1 o) {
        // 倒序排列，从大到小
        return this.sumFlow > o.getSumFlow() ? -1 : 1;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.upFlow = dataInput.readLong();
        this.downFlow = dataInput.readLong();
        this.sumFlow = dataInput.readLong();
    }
    @Override
    public String toString() {
        return upFlow +"\t" + downFlow + "\t" + sumFlow ;
    }
}
