package wordcount;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class selfpartitioner extends Partitioner<IntWritable, IntWritable> {
    @Override
    public int getPartition(IntWritable intWritable, IntWritable intWritable2, int i) {
        int cur=Integer.valueOf(intWritable.toString());
        if(cur<10000){
            return 0;
        }else if(cur<20000){
            return 1;
        }else if(cur<30000){
            return 2;
        }else{
            return 4;
        }
    }
}
