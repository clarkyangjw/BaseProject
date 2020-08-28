package com.clark.util;

public class LoginUtils {

    public static boolean checkUsername(String username){
        //8到16位以字母开头的字母数字下划线的用户名
        String regExp = "^[a-zA-Z]\\w{7,15}$";
        if(username.matches(regExp)) {
            return true;
        }
        return false;
    }

    public static boolean checkPassword(String pwd) {
        //8到16位以大写字母开头的字母数字下划线的密码
        String regExp="^[A-Z]\\w{7,15}$";
        if(pwd.matches(regExp)) {
            return true;
        }
        return false;
    }

}
