package findcommonfriends;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class allfriendsdriver {
    public static void main(String[] args) throws Exception{
        args=new String[]{"C:\\Users\\Administrator\\Desktop\\friends.txt",
                "C:\\Users\\Administrator\\Desktop\\friend1","C:\\Users\\Administrator\\Desktop\\friend2"};
        Configuration conf=new Configuration();
        Job job = Job.getInstance(conf);

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

        //job2//

        //2.获取job2
        Job job2 = Job.getInstance(conf);

        //3.指定类

        job2.setMapperClass(twofriendsmapper.class);
        job2.setReducerClass(twofriendsreduce.class);

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
        ControlledJob ajob = new ControlledJob(job.getConfiguration());
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
