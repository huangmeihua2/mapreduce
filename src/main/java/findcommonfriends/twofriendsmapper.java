package findcommonfriends;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;

public class twofriendsmapper extends Mapper<LongWritable,Text,Text,Text> {
    @Override//只考虑一行数据的处理。因为每一行调用一次该方法。
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //加载一行
        String line=value.toString();
        //第一步切分
        String[] fields=line.split("\t");
       //取出好友来
        String friden=fields[0];
        //再次切分后面的用户
        //切分出用户来
        String[] csdnuser=fields[1].split(",");
        Arrays.sort(csdnuser);
        for(int i=0;i<csdnuser.length-1;i++){
            for(int j=i+1;j<csdnuser.length;j++){
                context.write(new Text(csdnuser[i]+"-"+csdnuser[j]),new Text(friden));
            }
        }
    }
}
