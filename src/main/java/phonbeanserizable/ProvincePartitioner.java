package phonbeanserizable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class ProvincePartitioner extends Partitioner<Text,serizablebean> {
    @Override
    public int getPartition(Text text, serizablebean serizablebean, int i) {
       String phone=text.toString().substring(0, 3);

        int partition = 5;

        // 2 判断是哪个省
        if ("136".equals(phone)) {
            partition = 0;
        }else if ("137".equals(phone)) {
            partition = 1;
        }else if ("138".equals(phone)) {
            partition = 2;
        }else if ("139".equals(phone)) {
            partition = 3;
        }else {
            partition=4;
        }

        return partition;
    }
}
