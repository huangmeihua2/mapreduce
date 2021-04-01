package ETL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;


import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


public class LogDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //输入输出路径
        args = new String[]{"C:\\Users\\Administrator\\Desktop\\web.txt","C:\\Users\\Administrator\\Desktop\\huangmeihua1"};

        ////1.获取配置
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //2.加载反射类
        job.setJarByClass(LogDriver.class);
        job.setMapperClass(LoggerMapper.class);
        //job.setReducerClass();
        //3.map的输出和reduce的输出的类型
        //job.setMapOutputKeyClass();
        //job.setMapOutputValueClass();这里不用进行reduce所以不用进行设置map的输出类型，只要设置输出就可以。
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        //5.数据输入/输出的路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //5.job提交
        job.waitForCompletion(true);
    }
}
