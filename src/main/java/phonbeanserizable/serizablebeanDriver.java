package phonbeanserizable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class serizablebeanDriver {
public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
    args=new String[]{"C:\\Users\\Administrator\\Desktop\\phone_data.txt","C:\\Users\\Administrator\\Desktop\\testdata\\phone1"};
    Configuration conf = new Configuration();
    Job job= Job.getInstance(conf);
    //指定本程序所在的路径
    job.setJarByClass(serizablebeanDriver.class);

    // 2 指定本业务job要使用的mapper/Reducer业务类
    job.setMapperClass(serizableMapper.class);
    job.setReducerClass(serizableReduce.class);

    //设置预合
    job.setCombinerClass(combine.class);

    //设置自定义分区类
    job.setPartitionerClass(ProvincePartitioner.class);
    job.setNumReduceTasks(5);

    //指定mapkey value的输出类型
    job.setOutputKeyClass(Text.class);
    job.setMapOutputValueClass(serizablebean.class);
    //指定reduce的key value输出类型
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(serizablebean.class);

    FileInputFormat.setInputPaths(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    job.waitForCompletion(true);

}
}
