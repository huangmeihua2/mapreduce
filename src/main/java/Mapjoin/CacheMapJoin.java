package Mapjoin;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * NullWritable 和 FlowBean
 * <电话号码,上行流量 下行流量 流量总和>   ==> <Text,FlowBean>
 * <电话号码 上行流量 下行流量 流量总和 , NullWritable>  ==> <FlowBean,NullWritable>
 *
 */
public class CacheMapJoin extends Mapper<LongWritable, Text, Text, NullWritable> {

    Map<String, String> pMap = new HashMap<>();
    Text k = new Text();

    /**
     * setup方法只运行一次   ===> this.setup(context);源码
     *
     * static {} ===> 静态代码块
     *map中是会在task任务中多次执行的。
     * @param context  上下文
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

        System.out.println("我要初始化");
        //读取pd.txt文件
        BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(
                                        new FileInputStream(
                                             new File("F:\\date\\A\\mapjoin\\pd.txt")),"UTF-8"));

        String line;
        //读取每一行的数据直到为空为止
        while (StringUtils.isNotEmpty(line = reader.readLine())) {
            String[] fields = line.split("\t");

            String pid = fields[0];
            String pname = fields[1];

            pMap.put(pid, pname);

        }

        reader.close();
    }

    /**
     * 循环加载map方法  ==>
     *      while(context.nextKeyValue()) {
                 this.map(context.getCurrentKey(), context.getCurrentValue(), context);
            }
     *数据样式:1004	    01	4
     *
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key,
                       Text value,
                       Context context) throws IOException, InterruptedException {
        System.out.println("======我在Map里=======");

        //加载order.txt中的数据  数据样式:1004	    01	4    |   pd.txt  01 小米
        String line = value.toString();
        String[] fields = line.split("\t");
        //订单号
        String orderId = fields[0];
        //产品id
        String pid = fields[1];
        //产品数量
        String amount = fields[2];

        //通过pid,来找pname
        String pname = pMap.get(pid);

        //1004	    01	4  ===> 1004	    小米 	4
        k.set(orderId + "\t" + pname + "\t" + amount);

        context.write(k,NullWritable.get());
    }

    /**
     * cleanup方法只运行一次 ===> this.cleanup(context)
     * 收尾\关闭
     *
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        System.out.println("我要关闭了");
    }
}
