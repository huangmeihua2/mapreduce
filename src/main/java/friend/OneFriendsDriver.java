package friend;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class OneFriendsDriver {
    public static void main(String[] args) throws Exception {

        args = new String[]{"F:\\date\\A\\friends.txt", "F:\\date\\A\\Andy1019"};

        //1.获取conf
        Configuration conf = new Configuration();
        //2.获取job
        Job job = Job.getInstance(conf);

        //3.指定类
        job.setJarByClass(OneFriendsDriver.class);
        job.setMapperClass(OneFriendsMapper.class);
        job.setReducerClass(OneFriendsReducer.class);

        //4.泛型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //5.路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //提交
        job.waitForCompletion(true);
    }
}
