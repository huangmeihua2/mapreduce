package comparable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * LongWritable, Text   偏移量,文本数据
 * FlowBean,Text     <上传 下载 总和 ,电话号>
 */
public class ComparableMapper extends Mapper<LongWritable, Text, FlowBean, Text> {
    @Override
    protected void map(LongWritable key,
                       Text value,
                       Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split("\t");

        //13602846565	4848	2910	7758
        String phoneNum = fields[0];

        long upFlow = Long.parseLong(fields[1]);
        long downFlow = Long.parseLong(fields[2]);
        long sumFlow = Long.parseLong(fields[3]);

        FlowBean flowBean = new FlowBean(upFlow, downFlow, sumFlow);
        //在flowbean对象中是根据总流量进行排序的，所以会进行排序key对象flowbean.
        // 这样在后面的reduce阶段，再调换下二者，进行输出。
        // 也会有多个号码是一样的再value中，对一同一个key。
        //其实也可以进行这样输出，不用reduce阶段。
        context.write(flowBean, new Text(phoneNum));

    }
}
