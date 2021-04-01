package mapcachejoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class tablereduce extends Reducer<Text,tableorder,tableorder,NullWritable> {


    @Override
    protected void reduce(Text key, Iterable<tableorder> values, Context context) throws IOException, InterruptedException {
        String pname=null;//用于存储在同一个key,即同一个pid下面的商品名称，从产品表中获得.
                          //并且一个key中只有一个value内含有商品名字。从产品表中获得
        List<tableorder> orderBeans=new ArrayList<tableorder>();
        //所有的数据
        for (tableorder bean : values) {

            if ("0".equals(bean.getFlag())) {
                //订单表(order.txt)  ==> "0"
                tableorder orderbean = new tableorder();

                try {
                    //把Bean中的数据拷贝到orderBean中 BeanUtils工具类: 把参数中的bean拷贝到orderbean中
                    BeanUtils.copyProperties(orderbean, bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                orderBeans.add(orderbean);

            } else {
                //产品表pdBean(pd.txt) ===> "1"
                pname = bean.getPname();//只是为了获得产品名字从而添加到tableorder对象存储的order表中。

            }
        }
        for(tableorder bean:orderBeans){
            bean.setPname(pname);
            context.write(bean,NullWritable.get());
        }
    }
}
