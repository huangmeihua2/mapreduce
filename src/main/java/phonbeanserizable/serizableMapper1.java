package phonbeanserizable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class serizableMapper1 extends Mapper<LongWritable,Text,serizablebean1,Text>{
    serizablebean1 bean=new serizablebean1();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line=value.toString();
        String[] fields=line.split("\t");

        String phernumb=fields[1];

        long upFlow = Long.parseLong(fields[fields.length - 3]);
        long downFlow = Long.parseLong(fields[fields.length - 2]);

        bean.set1(upFlow,downFlow);

        context.write(bean,new Text(phernumb));

    }
}
