package ETL2;

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
        args = new String[]{"C:\\Users\\Administrator\\Desktop\\web.txt","C:\\Users\\Administrator\\Desktop\\huangmeihua2"};
        ////1.获取配置
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //加载驱动类
        job.setJarByClass(LogDriver.class);
        job.setMapperClass(LoggerMapper.class);

        //设置输出的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        //数据输入输出路径
        //4.输入数据和输出数据的路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //5.job提交
        job.waitForCompletion(true);
    }
}
