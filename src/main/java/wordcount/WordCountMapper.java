package wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    /**
     * ctrl + o 呼出他要重写的方法
     *
     * @param key     key的类型    ===>  偏移量
     * @param value   value的类型  ===> 文本
     * @param context 联系上下文  ===> 联系Reduce
     * @throws IOException
     * @throws InterruptedException
     */
    //map框架是每读一行就调用一次方法，只有每个key在缓存中收集全了才会进入reduce中。
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        /**
         * 可以在多个集群上运行该方法进行统计。
         * 中文字符问题："UTF-8"或者"GBK"
         * 1.获取字节
         * 2.从头
         * 3.到尾
         * 4.编码格式
         */

        //1.数据的toString 加载 （每一行）
        String line = value.toString();
        //2.数据切割  按空格切割
        String[] words = line.split(" ");

        //3.数据标号,输出（Text,IntWritable）,每个行字符为1，并且加入MAP中，形成<key,1>对。
        //也可以不加入到map中。
        for (String word : words) {
            context.write(new Text(word),new IntWritable(1));
        }
        System.out.println("===========我是Map方法============");
    }
}
