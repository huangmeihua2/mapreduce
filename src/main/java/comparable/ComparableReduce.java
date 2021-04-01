package comparable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Mapper的输出类型等于Reducer的输入类型
 * FlowBean, Text  : <上传 下载 总和 ,电话号>
 * Text, FlowBean  : <电话号,上传 下载 总和>
 */
public class ComparableReduce extends Reducer<FlowBean, Text, Text, FlowBean> {
    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text text : values) {
            context.write(text, key);
        }
    }
}
