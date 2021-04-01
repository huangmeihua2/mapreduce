package ETL;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LoggerMapper extends Mapper<LongWritable,Text,Text,NullWritable> {
    Text k=new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //1.加载一行的数据，读出文件内的一行数据。
        String line=value.toString();

        //2.判断读取的每一行数据长度是否大于指定长度
        boolean count=testlog(line,context);

        //3.进行判断，只有为true才进行输出，并且这里不是加入map<key,k>的输出了，而是直接一行输出到文件中，否则直接return。
        if(count){
            k.set(line);//进行数据转换，将line中的string数据转换为Text进行写入文件中。
            context.write(k,NullWritable.get());//直接一行东西写入输出文件中。
        }
        return;
    }

    private boolean testlog(String line, Context context) {
        String[] word=line.split(" ");
        if(word.length>11){
            context.getCounter("Map","true").increment(1);
            return true;
        }else {
            context.getCounter("Map","false").increment(1);
            return false;
        }
    }
}
