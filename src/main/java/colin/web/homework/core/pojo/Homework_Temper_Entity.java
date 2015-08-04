package colin.web.homework.core.pojo;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by DELL on 2015/8/4.
 */
@Component
@Table(name = "homework_temper")
public class Homework_Temper_Entity {
    @Column(name = "temper_id",nullable = false)
    private String temper_id;
    @Column(name = "temper_content",nullable = false)
    private String temper_content;
    @Column(name = "temper_date",nullable = false)
    private Timestamp temper_date;
    @Column(name = "tmmper_user",nullable = false)
    private String tmmper_user;

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

    public Timestamp getTemper_date() {
        return temper_date;
    }

    public void setTemper_date(Timestamp temper_date) {
        this.temper_date = temper_date;
    }

    public String getTmmper_user() {
        return tmmper_user;
    }

    public void setTmmper_user(String tmmper_user) {
        this.tmmper_user = tmmper_user;
    }
}
