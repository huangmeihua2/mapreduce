package mapcachejoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.FileInputStream;
import java.io.IOException;

public class tableDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args=new String[]{"C:\\Users\\Administrator\\Desktop\\datasource","C:\\Users\\Administrator\\Desktop\\testdata\\reducejoin1"};
        // 1 获取配置信息，或者job对象实例
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(tableDriver.class);

        job.setMapperClass(tablemapper.class);
        job.setReducerClass(tablereduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(tableorder.class);

        job.setOutputKeyClass(tableorder.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path( args[0]));
        FileOutputFormat.setOutputPath(job,new Path( args[1]));
        job.waitForCompletion(true);

    }
}
