package Phonbean;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * LongWritable：数据偏移量
 * Text：一行的文本内容
 * Text：电话号码
 * FlowBean：上行流量、下行流量、总流量
 */
public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowBean> {
    Text k = new Text();
    FlowBean v = new FlowBean();


    @Override
    protected void map(LongWritable key,
                       Text value,
                       Context context) throws IOException, InterruptedException {
        //数据加载
        String line = value.toString();

        //切割
        String[] fields = line.split("\t");

        //电话号
        String phoneNum = fields[1];

        long upFlow = Long.parseLong(fields[fields.length - 3]);
        long downFlow = Long.parseLong(fields[fields.length - 2]);
        //k：电话号码
        k.set(phoneNum);
        //v: 上传 、下载 、总流量
        v.set(upFlow,downFlow);

        context.write(k,v);
    }
}
