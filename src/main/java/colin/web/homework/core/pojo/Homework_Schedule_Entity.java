package colin.web.homework.core.pojo;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by DELL on 2015/8/4.
 */
@Component
@Table(name = "homework_schedule")
public class Homework_Schedule_Entity implements Serializable{
    @Id
    @Column(name = "schedule_id",nullable = false)
    private String schedule_id;
    @Column(name = "schedule_content",nullable = false)
    private String schedule_content;
    @Column(name = "schedule_start_date",nullable = false)
    private Timestamp schedule_start_date;
    @Column(name = "schedule_end_date",nullable = false)
    private Timestamp schedule_end_date;
    @Column(name = "schedule_user",nullable = false)
    private String schedule_user;

    public String getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(String schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getSchedule_content() {
        return schedule_content;
    }

    public void setSchedule_content(String schedule_content) {
        this.schedule_content = schedule_content;
    }

    public Timestamp getSchedule_start_date() {
        return schedule_start_date;
    }

    public void setSchedule_start_date(Timestamp schedule_start_date) {
        this.schedule_start_date = schedule_start_date;
    }

    public Timestamp getSchedule_end_date() {
        return schedule_end_date;
    }

    public void setSchedule_end_date(Timestamp schedule_end_date) {
        this.schedule_end_date = schedule_end_date;
    }

    public String getSchedule_user() {
        return schedule_user;
    }

    public void setSchedule_user(String schedule_user) {
        this.schedule_user = schedule_user;
    }
}
