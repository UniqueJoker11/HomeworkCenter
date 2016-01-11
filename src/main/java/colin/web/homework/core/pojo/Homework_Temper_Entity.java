package colin.web.homework.core.pojo;

import colin.web.homework.annotation.Column;
import colin.web.homework.annotation.Id;
import colin.web.homework.annotation.Table;

/**
 * Created by DELL on 2016/1/11.
 */
@Table(name = "homework_temper")
public class Homework_Temper_Entity {
    @Id
    @Column(name = "temper_id")
    private String temper_id;
    @Column(name = "temper_content")
    private String temper_content;
    @Column(name = "temper_createtime")
    private String temper_createtime;
    @Column(name = "temper_user")
    private String temper_user;

    public String getTemper_id() {
        return temper_id;
    }

    public void setTemper_id(String temper_id) {
        this.temper_id = temper_id;
    }

    public String getTemper_content() {
        return temper_content;
    }

    public void setTemper_content(String temper_content) {
        this.temper_content = temper_content;
    }

    public String getTemper_createtime() {
        return temper_createtime;
    }

    public void setTemper_createtime(String temper_createtime) {
        this.temper_createtime = temper_createtime;
    }

    public String getTemper_user() {
        return temper_user;
    }

    public void setTemper_user(String temper_user) {
        this.temper_user = temper_user;
    }
}
