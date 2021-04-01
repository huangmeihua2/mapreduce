package friend;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

public class TwoFriendsMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key,
                       Text value,
                       Context context) throws IOException, InterruptedException {
        //1.加载 A	I,K,C,B,G,F,H,O,D,
        String line = value.toString();

        //2.切割 \t
        String[] friend_persons = line.split("\t");

        //3.取数
        String friend = friend_persons[0];
        String[] persons = friend_persons[1].split(",");



        for (int i = 0; i < persons.length - 1; i++) {
            for (int j = i + 1; j < persons.length; j++) {
                context.write(new Text(persons[i] + "-" + persons[j]), new Text(friend));
            }
        }

    }
}
