package findcommonfriends;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class firstfrienddriver {
    public static  void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
      args=new String[]{"C:\\Users\\Administrator\\Desktop\\friends.txt",
                          "C:\\Users\\Administrator\\Desktop\\huangmeihua6"};

        Configuration conf=new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(firstfrienddriver.class);
        job.setMapperClass(firstfrienmapper.class);
        job.setReducerClass(firstfriendreduce.class);

        //设置map和reduce阶段的数据输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        //5.路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //提交
        job.waitForCompletion(true);
    }
}
