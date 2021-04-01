package comparable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


/**
 * Writable ==> 序列化对象传输
 * WritableComparable  ===> 序列化对象传输且比较(排序)
 * 注:序列化与反序列化顺序一致
 * 必要要有空参构造器,也就是lombok中的@NoArgsConstructor
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlowBean implements WritableComparable<FlowBean> {
    private long upFlow;
    private long downFlow;
    private long sumFlow;

    @Override
    public int compareTo(FlowBean o) {
        //-1 是倒叙 ,1是正序 , 0 是相等
        return this.sumFlow > o.sumFlow ? -1 : 1;
    }

    /**
     * 序列化
     *
     * @param dataOutput
     * @throws IOException
     */
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);
    }

    /**
     * 反序列化
     *
     * @param dataInput
     * @throws IOException
     */
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.upFlow = dataInput.readLong();
        this.downFlow = dataInput.readLong();
        this.sumFlow = dataInput.readLong();
    }

    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }
}
