package user;

import java.io.Serializable;

public class UserVO implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;

    public UserVO() {};

    public UserVO(int id, String username)
    {
        this.id = id;
        this.username = username;
    }

    public int getUserId() {
        return id;
    }
    public void setUserId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return username;
    }
    public void setUserName(String username) {
        this.username = username;
    }
}
