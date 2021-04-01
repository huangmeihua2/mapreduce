package comparable;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ComparableDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[]{"C:\\Users\\Administrator\\Desktop\\datasource\\phone", "C:\\Users\\Administrator\\Desktop\\testdata\\comparable1"};

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
       job.setJarByClass(ComparableDriver.class);
        //以下三行代码为Mappper类及输出类型

        job.setMapperClass(ComparableMapper.class);
        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);

        //以下三行代码为Reduce类及输出类型
        job.setReducerClass(ComparableReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 6 提交
        job.waitForCompletion(true);
    }
}
