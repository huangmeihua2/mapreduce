package mapcachejoin;

import Mapjoin.CacheMapJoin;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class mapjoinDriver {
    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, InterruptedException {

       args=new String[]{"C:\\Users\\Administrator\\Desktop\\order.txt","C:\\Users\\Administrator\\Desktop\\testdata\\mapjoin1"};
        // 1 获取配置信息，或者job对象实例
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(mapjoinDriver.class);

        job.setMapperClass(cachemapjoin.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        //不用reduce
        job.setNumReduceTasks(0);
        //把小文件加到缓存当中,方便加快速度读取  ===>   数据.cache()  ==> 加缓存
        job.addCacheFile(new URI("file:///C:/Users/Administrator/Desktop/pd.txt"));


        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.waitForCompletion(true);
    }
}
