package phonbeanserizable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class serizableReduce extends Reducer<Text,serizablebean,Text,serizablebean> {
    serizablebean bean=new serizablebean();
    @Override
    protected void reduce(Text key, Iterable<serizablebean> values, Context context) throws IOException, InterruptedException {
        long sum_upflow=0;
        long sum_downflow=0;

        for(serizablebean a:values){
           sum_upflow+=a.getSumFlow();
           sum_downflow+=a.getDownFlow();
        }
        bean.set1(sum_downflow,sum_downflow);

        context.write(key,bean);
    }
}
