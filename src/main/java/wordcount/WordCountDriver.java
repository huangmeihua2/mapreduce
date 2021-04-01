package wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WordCountDriver {
    public static void main(String[] args) throws Exception {
        args = new String[]{"C:\\Users\\Administrator\\Desktop\\word","C:\\Users\\Administrator\\Desktop\\huang2"};
        //1.获取配置
        Configuration conf = new Configuration();
        //2.获取Job实例,创建的job提交器。
        Job job = Job.getInstance(conf);
        //3.加载驱动类
        job.setJarByClass(WordCountDriver.class);
        job.setMapperClass(WordCountMapper.class);
        job.setCombinerClass(WordcountCombiner.class);
        job.setReducerClass(WordCountReducer.class);
        //4.类型 Map的输出==Reduce的输入的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //输出的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
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
