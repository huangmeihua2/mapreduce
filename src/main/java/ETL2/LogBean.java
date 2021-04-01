package ETL2;

import lombok.*;

import java.util.HashMap;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LogBean {
    //客户端IP
    private String remote_addr;
    //用户名称 忽略<->
    private String remote_user;
    //时间
    private String time_local;
    //URL请求
    private String request;
    //返回状态码
    private String status;
    //文件内容大小
    private String body_bytes_sent;
    //链接页面
    private String http_referer;
    //浏览的相关信息
    private String http_user_agent;

    //判断数据是否合法
    private boolean valid = true;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.valid);
        sb.append("\001").append(this.remote_addr);
        sb.append("\001").append(this.remote_user);
        sb.append("\001").append(this.time_local);
        sb.append("\001").append(this.request);
        sb.append("\001").append(this.status);
        sb.append("\001").append(this.body_bytes_sent);
        sb.append("\001").append(this.http_referer);
        sb.append("\001").append(this.http_user_agent);
        return sb.toString();
    }
    public static void main(String[] args){
        HashMap<Object, Object> fdfd = new HashMap<>();
    }
}
