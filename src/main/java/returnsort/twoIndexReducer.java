package returnsort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class twoIndexReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        for (Text value : values) {
            sb.append(value.toString().replace("\t", "-->") + "\t");
        }
        //key：单词 value:aa.txt --> 3 b.txt --> 1 c.txt --> 1
        context.write(key, new Text(sb.toString()));
    }
}
