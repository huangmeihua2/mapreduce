package phonbeanserizable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class combine extends Reducer<Text,serizablebean,Text,serizablebean>{
    serizablebean bean=new serizablebean();
    @Override
    protected void reduce(Text key, Iterable<serizablebean> values, Context context) throws IOException, InterruptedException {
        long upflow=0;
        long downflow=0;
        for(serizablebean a:values){
            upflow+=a.getUpFlow();
            downflow+=a.getDownFlow();
        }
        bean.set1(upflow,downflow);
        context.write(key,bean);
    }
}
