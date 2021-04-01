package phonbeanserizable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class serizableDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args=new String[]{"C:\\Users\\Administrator\\Desktop\\datasource\\phone","C:\\Users\\Administrator\\Desktop\\testdata\\phone1"};
        Configuration conf = new Configuration();
        Job job= Job.getInstance(conf);
        //指定本程序所在的路径
        job.setJarByClass(serizableDriver.class);

        // 2 指定本业务job要使用的mapper/Reducer业务类
        job.setMapperClass(serizableMapper1.class);
        job.setReducerClass(serizableReduce1.class);

        //设置预合,提前基础一个类就可以。
        // 就是Reduce，相当于在shuffle中会进行一次分组并输入到conbine中进行预合并。




        //指定mapkey value的输出类型
        job.setOutputKeyClass(serizablebean1.class);
        job.setMapOutputValueClass(Text.class);
        //指定reduce的key value输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(serizablebean1.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);

    }
}
