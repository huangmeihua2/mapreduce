package friend;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class OneFriendsReducer extends Reducer<Text, Text, Text, Text> {

    /**
     *
     * @param key friend     B,C,D,F,E,O     A,C,E,K
     * @param values person  A               B
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key,
                          Iterable<Text> values,
                          Context context) throws IOException, InterruptedException {

        StringBuffer sb = new StringBuffer();

        for (Text person : values) {
            //A,B,C,D,
            sb.append(person).append(",");
        }

        /**
         * A	I,K,C,B,G,F,H,O,D,
         * B	A,F,J,E,
         * C	A,E,B,H,F,G,K,
         */
        context.write(key,new Text(sb.toString()));

    }
}
