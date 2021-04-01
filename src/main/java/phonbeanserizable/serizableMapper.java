package phonbeanserizable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class serizableMapper extends Mapper<LongWritable,Text,Text,serizablebean> {
    Text k=new Text();
    serizablebean v=new serizablebean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line=value.toString();

       String[] fields=line.split("\t");

       String phnumb=fields[1];
       //将取出来的字符串对象，转换为long基本类型。
        long upFlow = Long.parseLong(fields[fields.length - 3]);
        long downFlow = Long.parseLong(fields[fields.length - 2]);
        k.set(phnumb);//将字符串转换为Text,作为map的输出key类型。
        v.set1(upFlow,downFlow);

        context.write(k,v);
    }
}

