package findcommonfriends;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


import java.io.IOException;
       //输入的是<friend,usercsdnlist>,根据次键值对，找出friend是那些用户的好友
public class firstfriendreduce extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        StringBuffer sb=new StringBuffer();
        for(Text usercsdn:values){//一个key即好友可能是多个博客的好友
            sb.append(usercsdn).append(",");
        }

        context.write(key,new Text(sb.toString()));
    }
}
