package friend;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TwoFriendsReducer extends Reducer<Text, Text, Text, Text> {

    /**
     * @param key     B-C    B-D
     * @param values  A      XX
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key,
                          Iterable<Text> values,
                          Context context) throws IOException, InterruptedException {
        StringBuffer sb = new StringBuffer();
        for (Text friend : values) {
            //A B E F
            sb.append(friend).append(" ");
        }
        //key:B-C  vallue:A B E F
        context.write(key, new Text(sb.toString()));
    }
}
