package NIO;

import java.io.Serializable;

public class userinfo implements Serializable {
    private long id;
    private String username;
    private String password;

    public userinfo(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
