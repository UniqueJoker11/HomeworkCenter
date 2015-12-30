package colin.web.homework.core.pojo;

import colin.web.homework.annotation.Column;
import colin.web.homework.annotation.Id;
import colin.web.homework.annotation.Table;

/**
 * Created by DELL on 2015/12/29.
 */
@Table(name = "homework_pictures")
public class Homework_Pictures_Entity {
    @Id
    @Column(name = "pictures_id")
    private String pictures_id;
    @Column(name = "pictures_name")
    private String pictures_name;
    @Column(name = "pictures_url")
    private String pictures_url;
    @Column(name = "pictures_backup")
    private String pictures_backup;
    @Column(name = "pictures_createtime")
    private String pictures_createtime;
    @Column(name = "pictures_user")
    private String pictures_user;

    public String getPictures_id() {
        return pictures_id;
    }

    public void setPictures_id(String pictures_id) {
        this.pictures_id = pictures_id;
    }

    public String getPictures_name() {
        return pictures_name;
    }

    public void setPictures_name(String pictures_name) {
        this.pictures_name = pictures_name;
    }

    public String getPictures_url() {
        return pictures_url;
    }

    public void setPictures_url(String pictures_url) {
        this.pictures_url = pictures_url;
    }

    public String getPictures_backup() {
        return pictures_backup;
    }

    public void setPictures_backup(String pictures_backup) {
        this.pictures_backup = pictures_backup;
    }

    public String getPictures_createtime() {
        return pictures_createtime;
    }

    public void setPictures_createtime(String pictures_createtime) {
        this.pictures_createtime = pictures_createtime;
    }

    public String getPictures_user() {
        return pictures_user;
    }

    public void setPictures_user(String pictures_user) {
        this.pictures_user = pictures_user;
    }
}
