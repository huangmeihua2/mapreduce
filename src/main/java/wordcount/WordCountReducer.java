package wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
     // 对于前面输入的数据，每一个key或者行都会调用，并且第二个value是调入的集合。
     // 然后进行聚合统计，然后写入context文件。<key,valuelist{1,3,2,1,,1,1}>
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values,
                          Context context) throws IOException, InterruptedException {
        //1.初始化
        int num = 0;
        //2.对于相同的key，有一个加一个
        for (IntWritable b:values) {
            num += b.get();
        }

        //3.针对于每一个单词key的聚合统计
        context.write(key,new IntWritable(num));
    }
}
