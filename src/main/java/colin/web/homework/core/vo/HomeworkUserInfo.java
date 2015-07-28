package colin.web.homework.core.vo;

import org.springframework.stereotype.Component;

/**
 * Created by DELL on 2015/7/15.
 */
public class HomeworkUserInfo {
    private String user_id;
    private String user_name;
    private String user_password;
    private String user_callname;
    private String user_organize_id;
    private String user_email;
    private String user_phone;
    private String user_createtime;
    private String user_logintime;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_callname() {
        return user_callname;
    }

    public void setUser_callname(String user_callname) {
        this.user_callname = user_callname;
    }

    public String getUser_organize_id() {
        return user_organize_id;
    }

    public void setUser_organize_id(String user_organize_id) {
        this.user_organize_id = user_organize_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_createtime() {
        return user_createtime;
    }

    public void setUser_createtime(String user_createtime) {
        this.user_createtime = user_createtime;
    }

    public String getUser_logintime() {
        return user_logintime;
    }

    public void setUser_logintime(String user_logintime) {
        this.user_logintime = user_logintime;
    }
}
