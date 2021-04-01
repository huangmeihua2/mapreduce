package mapcachejoin;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class cachemapjoin extends Mapper<LongWritable,Text,Text,NullWritable> {
   Map<String, String> putmap= new HashMap();
   Text k=new Text();//后面可以将字符串类型通过set方法直接自动进行包装，因为字符串类型的java程序更好运行。
    //该方法只进行加载工作，不想map方法多次进行执行的。
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        System.out.println("start initiallize");
        //使用缓存流DufferReader将全部文件内容缓存下来，到缓存流中。
        BufferedReader reader=new BufferedReader(
               new InputStreamReader( new FileInputStream(
                 new File("C:\\Users\\Administrator\\Desktop\\pd.txt")
               )
               )
               );

        String line;
        while (StringUtils.isNotEmpty(line=reader.readLine())){
            String[] fields=line.split("\t");
            putmap.put(fields[0],fields[1]);
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
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        System.out.println("map 方法");
        String line = value.toString();
        String[] fields = line.split("\t");
        //订单号
        String orderId = fields[0];
        //产品id
        String pid = fields[1];
        //产品数量
        String amount = fields[2];
        //将小表进行连接在一起。
        String name=putmap.get(pid);
        k.set(orderId + "\t" + name + "\t" + amount);
        context.write(k,NullWritable.get());
    }


   //只执行一次，做一些收尾工作。
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        System.out.println("game over");
    }
}
