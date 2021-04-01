package Mapjoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Text, TableBean
 * <(pid), (orderId,pid,amount,pname,flag)>
 * <p>
 * TableBean, NullWritable
 * <(orderId,pid,amount,pname,flag),空>
 */
public class TableReducer extends Reducer<Text, TableBean, TableBean, NullWritable> {
    @Override
    protected void reduce(Text key,
                          Iterable<TableBean> values,
                          Context context) throws IOException, InterruptedException {
        //orderBeans 订单表(order.txt)和 pdBean(pd.txt)
        List<TableBean> orderBeans = new ArrayList<>();

        String pname = null;
        //所有的数据
        for (TableBean bean : values) {

            if ("0".equals(bean.getFlag())) {
                //订单表(order.txt)  ==> "0"
                TableBean orderbean = new TableBean();

                try {
                    //把Bean中的数据拷贝到orderBean中 BeanUtils工具类: 把参数中的bean拷贝到orderbean中
                    BeanUtils.copyProperties(orderbean, bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                orderBeans.add(orderbean);

            } else {
                //产品表pdBean(pd.txt) ===> "1"
                pname = bean.getPname1();

            }
        }

        for (TableBean  bean2 : orderBeans) {
            //从pdbean中获取产品名,给到orderBean中
            bean2.setPname1(pname);
            //TableBean(orderId=1004, pid=01, amount=4, pname=小米, flag=0)
            context.write(bean2,NullWritable.get());
        }


    }
}
