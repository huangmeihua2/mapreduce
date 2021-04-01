package returnsort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class twoIndexDriver {

    public static void main(String[] args) throws Exception {

        args = new String[] { "F:\\date\\Descsort1\\part-r-00000", "F:\\date\\Descsort41" };

        Configuration config = new Configuration();
        Job job = Job.getInstance(config);

        job.setJarByClass(twoIndexDriver.class);
        job.setMapperClass(twoIndexMapper.class);
        job.setReducerClass(twoIndexReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
