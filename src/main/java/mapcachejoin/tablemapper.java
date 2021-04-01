package mapcachejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class tablemapper extends Mapper<LongWritable,Text,Text,tableorder> {
    Text k = new Text();
    tableorder bean = new tableorder();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1.根据文件名识别两张表 order.txt  pd.txt
        FileSplit split = (FileSplit) context.getInputSplit();
        //获取读取路径下的文件名
        String name = split.getPath().getName();

        String line=value.toString();
        if(name.startsWith("order")){
            String[] fields=line.split("\t");

            bean.setAmount(Integer.parseInt(fields[2]));
            bean.setFlag("0");
            bean.setOrderId(fields[0]);
            bean.setPid(fields[1]);
            bean.setPname("");
            k.set(fields[1]);//以pid作为键值。
        }else {
            String[] fields=line.split("\t");

            bean.setAmount(0);
            bean.setFlag("1");
            bean.setOrderId("");
            bean.setPid(fields[0]);
            bean.setPname(fields[1]);
            k.set(fields[0]);//以pid作为键值。
        }
     context.write(k,bean);
    }
}
