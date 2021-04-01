package wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class sortdriver {
    public static void main(String[] args) throws Exception {
        args = new String[]{"C:\\Users\\Administrator\\huangmeihua\\sources","C:\\Users\\Administrator\\huangmeihua\\result"};
        //1.获取配置
        Configuration conf = new Configuration();
        //2.获取Job实例,创建的job提交器。
        Job job = Job.getInstance(conf);
        //3.加载驱动类
        job.setJarByClass(sortdriver.class);
        job.setMapperClass(sortmap.class);
        job.setReducerClass(sortreduce.class);
        //4.类型 Map的输出==Reduce的输入的类型
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        //输出的类型
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);
        //自定义分区器
        job.setPartitionerClass(selfpartitioner.class);
        job.setNumReduceTasks(4);
        //5.数据输入/输出的路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //6.提交  ===> 包含Submit
        job.waitForCompletion(true);
    }
}
