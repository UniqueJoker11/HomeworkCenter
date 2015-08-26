package colin.web.homework.core.pojo;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ASUS on 2015/7/11.
 */
@Component
@Table(name = "homework_user")
public class Homework_User_Entity {
    @Id
    @Column(name = "user_id")
    private String user_id;
    @Column(name = "user_name")
    private String user_name;
    @Column(name = "user_password")
    private String user_password;
    @Column(name = "user_head")
    private String user_head;
    @Column(name = "user_callname")
    private String user_callname;
    @Column(name = "user_email")
    private String user_email;
    @Column(name = "user_phone")
    private String user_phone;
    @Column(name = "user_createtime")
    private String user_createtime;
    @Column(name = "user_logintime")
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

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
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
