package Mapjoin;

import Phonbean.FlowBean;
import Phonbean.FlowDriver;
import Phonbean.FlowMapper;
import Phonbean.FlowReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.net.URI;

public class CacheDriver {
    public static void main(String[] args) throws Exception {

        args = new String[]{"F:\\date\\A\\mapjoin\\order.txt", "F:\\date\\A\\mapjoin\\outMapJoin123"};

        // 1 获取配置信息，或者job对象实例
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        //job.setJarByClass(CacheDriver.class);

        job.setMapperClass(CacheMapJoin.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        //不用reduce
        job.setNumReduceTasks(3);
        //把小文件加到缓存当中,方便加快速度读取  ===>   数据.cache()  ==> 加缓存
        job.addCacheFile(new URI("file:///f:/date/A/mapjoin/pd.txt"));


        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));


        job.waitForCompletion(true);
    }
}
