package friend;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OneFriendsMapper extends Mapper<LongWritable, Text, Text, Text> {

    /**
     * ctlr + o 重写 Mapper方法
     *
     * @param key     偏移量
     * @param value   A:B,C,D,F,E,O   ==> B,C,D,F,E,O:A  ==> B A , C A , D A , F A , E A , O A(后者是前者的好友)
     * @param context 上下
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key,
                       Text value,
                       Context context) throws IOException, InterruptedException {
        //加载： A:B,C,D,F,E,O
        String line = value.toString();

        //分割：根据业务逻辑决定用 ：分割  {"A","B,C,D,F,E,O"}
        String[] fields = line.split(":");

        //数据划分
        String person = fields[0];
        //{"B","C","D","F","E","O"}
        String[] friends = fields[1].split(",");

        for (String friend : friends) {
//            B A  || C A || D  A ||
            context.write(new Text(friend),new Text(person));
        }


    }
}
