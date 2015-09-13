package colin.web.homework.core.pojo;

import colin.web.homework.annotation.Column;
import colin.web.homework.annotation.Id;
import colin.web.homework.annotation.Table;
import org.springframework.stereotype.Component;



/**
 * Created by ASUS on 2015/7/12.
 */
@Component
@Table(name = "homework_user_info")
public class Homework_Userinfo_Entity {
    @Id
    @Column(name = "user_info_id")
    private String user_info_id;
    @Column(name = "user_info_firstname")
    private String user_info_firstname;
    @Column(name = "user_info_lastname")
    private String user_info_lastname;
    @Column(name = "user_info_email")
    private String user_info_email;
    @Column(name = "user_info_province")
    private String user_info_province;
    @Column(name = "user_info_city")
    private String user_info_city;
    @Column(name = "user_info_area")
    private String user_info_area;
    @Column(name = "user_info_birthday")
    private String user_info_birthday;
    @Column(name = "user_info_address")
    private String user_info_address;
    @Column(name = "user_id")
    private String user_id;

    public String getUser_info_id() {
        return user_info_id;
    }

    public void setUser_info_id(String user_info_id) {
        this.user_info_id = user_info_id;
    }

    public String getUser_info_firstname() {
        return user_info_firstname;
    }

    public void setUser_info_firstname(String user_info_firstname) {
        this.user_info_firstname = user_info_firstname;
    }

    public String getUser_info_lastname() {
        return user_info_lastname;
    }

    public void setUser_info_lastname(String user_info_lastname) {
        this.user_info_lastname = user_info_lastname;
    }

    public String getUser_info_email() {
        return user_info_email;
    }

    public void setUser_info_email(String user_info_email) {
        this.user_info_email = user_info_email;
    }

    public String getUser_info_province() {
        return user_info_province;
    }

    public void setUser_info_province(String user_info_province) {
        this.user_info_province = user_info_province;
    }

    public String getUser_info_city() {
        return user_info_city;
    }

    public void setUser_info_city(String user_info_city) {
        this.user_info_city = user_info_city;
    }

    public String getUser_info_area() {
        return user_info_area;
    }

    public void setUser_info_area(String user_info_area) {
        this.user_info_area = user_info_area;
    }

    public String getUser_info_birthday() {
        return user_info_birthday;
    }

    public void setUser_info_birthday(String user_info_birthday) {
        this.user_info_birthday = user_info_birthday;
    }

    public String getUser_info_address() {
        return user_info_address;
    }

    public void setUser_info_address(String user_info_address) {
        this.user_info_address = user_info_address;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
