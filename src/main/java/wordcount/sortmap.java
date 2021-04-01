package wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class sortmap extends Mapper<LongWritable,Text,IntWritable,IntWritable> {
    public void map(LongWritable key,Text value,Context context) throws IOException,InterruptedException
    {
        String[] split = value.toString().split("\\s+");
        for (int i = 0; i <split.length ; i++) {
            try{
                IntWritable intWritable = new IntWritable(Integer.parseInt(split[i]));
                context.write(intWritable, intWritable);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}