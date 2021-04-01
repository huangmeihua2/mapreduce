package Phonbean;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Text : 电话号
 * FlowBean ： 上传 、下载 、总流量
 * 这个reduce的作用是用来聚合每一行的总流量
 */
public class FlowReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
    FlowBean v = new FlowBean();

    @Override
    protected void reduce(Text key,
                          Iterable<FlowBean> values,
                          Context context) throws IOException, InterruptedException {
        long sum_upFlow = 0;
        long sum_downFlow = 0;

        //变量.
        for (FlowBean flowBean : values) {
            sum_upFlow += flowBean.getUpFlow();
            sum_downFlow += flowBean.getDownFlow();
        }


        v.set(sum_upFlow, sum_downFlow);


        context.write(key, v);

    }
}
