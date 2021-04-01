package findcommonfriends;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class twofriendsdriver {
    public static void main(String[] args) throws Exception {

        //第一个MR得输出 作为第二个MR得输入
        args = new String[]{"C:\\Users\\Administrator\\Desktop\\huangmeihua6","C:\\Users\\Administrator\\Desktop\\fdf"};

        //1.获取conf
        Configuration conf = new Configuration();
        //2.获取job
        Job job = Job.getInstance(conf);

        //3.指定类
        job.setJarByClass(twofriendsdriver.class);
        job.setMapperClass(twofriendsmapper.class);
        job.setReducerClass(twofriendsreduce.class);

        //4.泛型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //5.路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.waitForCompletion(true);


    }
}
