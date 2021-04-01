package Mapjoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * LongWritable, Text
 *    偏移量 , 整行文本数据
 * Text, TableBean
 *    <(pid), (orderId,pid,amount,pname,flag)>
 */
public class TableMapper extends Mapper<LongWritable, Text, Text, TableBean> {
    Text k = new Text();
    TableBean bean = new TableBean();

    @Override
    protected void map(LongWritable key,
                       Text value,
                       Context context) throws IOException, InterruptedException {
        //1.根据文件名识别两张表 order.txt  pd.txt
        FileSplit split = (FileSplit) context.getInputSplit();
        //获取读取路径下的文件名
        String name = split.getPath().getName();

        //数据转换
        String line = value.toString();

        if (name.startsWith("order")) {
            //订单表==> order.txt : orderId    pid amount
            String[] fields = line.split("\t");
            //orderId
            bean.setOrderId1(fields[0]);
            //pid
            bean.setPid(fields[1]);
            //amount
            bean.setAmount(Integer.parseInt(fields[2]));
            //pname
            bean.setPname1("");
            //flag
            bean.setFlag("0");

            //pid
            k.set(fields[1]);
        }else {
            //产品表===> pd.txt : pid  pname
            String[] fields = line.split("\t");
            bean.setOrderId1("");
            bean.setPid(fields[0]);
            bean.setAmount(0);
            bean.setPname1(fields[1]);
            bean.setFlag("1");

            //pid
            k.set(fields[0]);
        }

        context.write(k,bean);
    }
}
