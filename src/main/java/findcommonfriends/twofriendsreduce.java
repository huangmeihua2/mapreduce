package findcommonfriends;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class twofriendsreduce extends Reducer<Text,Text,Text,Text>{
    @Override//一个key将会调用一次该方法。
    protected void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        StringBuffer sum=new StringBuffer();
        for(Text friend:values){
            sum.append(friend).append(" ");
        }
        context.write(key,new Text(sum.toString()));//可以将字符串转换为Text类型，写入磁盘文件中。
    }
}
