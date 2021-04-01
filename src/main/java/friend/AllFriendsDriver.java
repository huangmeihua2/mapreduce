package friend;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class AllFriendsDriver {
    public static void main(String[] args) throws Exception {
        args = new String[]{"F:\\date\\A\\friends.txt", "F:\\date\\A\\Andy1019","F:\\date\\A\\Bndy1019"};

        //========================Job1分界线=========================
        //1.获取conf
        Configuration conf = new Configuration();
        //2.获取job  shift + F6
        Job job1 = Job.getInstance(conf);

        //3.指定类
        job1.setMapperClass(OneFriendsMapper.class);
        job1.setReducerClass(OneFriendsReducer.class);

        //4.泛型
        job1.setMapOutputKeyClass(Text.class);
        job1.setMapOutputValueClass(Text.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(Text.class);

        //5.路径
        FileInputFormat.setInputPaths(job1,new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(args[1]));


        //==========================Job2分界线==========================

        Job job2 = Job.getInstance(conf);

        //3.指定类
        job2.setMapperClass(TwoFriendsMapper.class);
        job2.setReducerClass(TwoFriendsReducer.class);

        //4.泛型
        job2.setMapOutputKeyClass(Text.class);
        job2.setMapOutputValueClass(Text.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(Text.class);

        //5.路径
        FileInputFormat.setInputPaths(job2,new Path(args[1]));
        FileOutputFormat.setOutputPath(job2, new Path(args[2]));

        //==========================Job控制器==========================

        //声明一个Job控制器名为"Andy"
        JobControl control = new JobControl("job");
        //通过控制器获取Job1任务得配置
        ControlledJob ajob = new ControlledJob(job1.getConfiguration());
        //通过控制器获取Job2任务得配置
        ControlledJob bjob = new ControlledJob(job2.getConfiguration());
        //bjob 依赖 ajob
        bjob.addDependingJob(ajob);

        //job控制器添加a、b
        control.addJob(ajob);
        control.addJob(bjob);

        //使用Thread控制Job控制器
        Thread thread = new Thread(control);
        thread.start();
    }
}
