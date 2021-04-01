package phonbeanserizable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class serizableReduce1 extends Reducer<serizablebean1,Text,Text,serizablebean1> {

    @Override
    protected void reduce(serizablebean1 key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for(Text t:values){
           context.write(t,key);//只是写出到文本中，
                             // 所以value中即使有相同的也可以作为键值，
                                 //  因为前期已经根据key,g归并排序了。
        }
    }
}
