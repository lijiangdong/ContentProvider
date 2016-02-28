package com.example.ljd.contentprovider;


/**
 * Created by ljd-PC on 2016/2/27.
 */
public class User {
    private int userId;
    private String userName;
    private boolean isMale;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    @Override
    public String toString(){
        return String.format("[userId:%s, userName:%s, isMale:%s]", userId, userName, isMale);
    }
}
