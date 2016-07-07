package database.thing;

import android.database.Cursor;

/**
 * Created by User on 04/07/2016.
 */
public class User {
    private  int userId;
    private String userName;
    private String email;
    private String password;


    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) { this.userId = userId; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
