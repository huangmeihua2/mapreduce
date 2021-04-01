package findcommonfriends;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class firstfrienmapper extends Mapper<LongWritable,Text,Text,Text>{
    @Override//对输入的一行的数据进行转换为字符串进行切割，清洗处理，可以理解成就是处理成键值对写入文件中，并且key可以重复。
            // 但是在输入到reduce时，相同的key会进行value合并后形成valuelist再进reduce方法处理。
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        /**
         * ctlr + o 重写 Mapper方法
         *
         * @param key     偏移量
         * @param value   A:B,C,D,F,E,O   ==> B,C,D,F,E,O:A  ==> B A , C A , D A , F A , E A , O A(后者是前者的好友)
         * @param context 上下
         * @throws IOException
         * @throws InterruptedException
         */
        //按行加载，并且将一行数据转换为java的string
        String line=value.toString();
        //进行切割
        String[] fields=line.split(":");
        //再次进行详细切割并且写入map中，并写入文件中。
        String usercsdn=fields[0];
        String[] friends=fields[1].split(",");
         //根据组成的键值对写出，直到一行内容写完为止。
        for(String friend : friends) context.write(new Text(friend), new Text(usercsdn));


    }
}
