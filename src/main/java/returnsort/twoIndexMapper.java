package returnsort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class twoIndexMapper extends Mapper<LongWritable, Text, Text, Text> {
    Text k = new Text();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        //itstar--a.txt  3
        String[] fields = line.split("--");
        //itstar
        k.set(fields[0]);
        //a.txt  3
        v.set(fields[1]);
        context.write(k, v);
    }
}
