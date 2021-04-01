package friend;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TwoFriendsDriver {
    public static void main(String[] args) throws Exception {

        //第一个MR得输出 作为第二个MR得输入
        args = new String[]{"F:\\date\\A\\Andy1019","F:\\date\\A\\friends1111111"};

        //1.获取conf
        Configuration conf = new Configuration();
        //2.获取job
        Job job = Job.getInstance(conf);

        //3.指定类
        job.setJarByClass(TwoFriendsDriver.class);
        job.setMapperClass(TwoFriendsMapper.class);
        job.setReducerClass(TwoFriendsReducer.class);

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
