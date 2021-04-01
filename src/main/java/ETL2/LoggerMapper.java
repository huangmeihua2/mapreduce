package ETL2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LoggerMapper extends Mapper<LongWritable,Text,Text,NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        //1.Text ===> String，加载一行数据，并将text转换为javas的string
        String line = value.toString();

        //2.解析日志
        LogBean bean = parseLog(line);

        //3.判断是否为非法数据
        if(bean.isValid()){
            context.write(new Text(bean.toString()),NullWritable.get());
            //反正输出就是这这样将指定的字符串输出，可以为切割的一行，还可以是字符数组，也可以为单个字符。
        }
        return;
    }

    private LogBean parseLog(String line) {
         //首先就是截取字段到字符串数字中，这个是字符串的方法split
        String[] fields=line.split(" ");
        //定义对象进行存储数据
        LogBean logBean=new LogBean();
        //进行数据的存储并且清洗
        if (fields.length > 11) {
            //2.封装数据
            logBean.setRemote_addr(fields[0]);
            logBean.setRemote_user(fields[1]);
            logBean.setTime_local(fields[3].substring(1));
            logBean.setRequest(fields[6]);
            logBean.setStatus(fields[8]);
            logBean.setBody_bytes_sent(fields[9]);
            logBean.setHttp_referer(fields[10]);

            //如果字段长度大于12，就拼接浏览器来源
            //筛选条件2：如果有浏览器来源就拼接上，看是否大于12
            if (fields.length > 12) {
                logBean.setHttp_user_agent(fields[11] + " " + fields[12]);
            }else {
                //浏览的相关信息
                logBean.setHttp_user_agent(fields[11]);
            }

            //判断状态码
            //筛选条件3：状态码大于等于400，是非法数据
            if (Integer.parseInt(logBean.getStatus()) >= 400) {
                logBean.setValid(false);
            }
        }else {
            logBean.setValid(false);
        }
        return logBean;
    }
}
