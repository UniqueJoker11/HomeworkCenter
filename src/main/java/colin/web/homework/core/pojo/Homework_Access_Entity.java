package colin.web.homework.core.pojo;

import colin.web.homework.annotation.Table;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by DELL on 2015/10/19.
 */
@Table(name = "homework_access")
public class Homework_Access_Entity {
    private String access_id;
    private long access_num;
    private Date access_date;
    private String access_address;
    private Timestamp access_last_access_date;
    private String access_info;

    public String getAccess_id() {
        return access_id;
    }

    public void setAccess_id(String access_id) {
        this.access_id = access_id;
    }

    public long getAccess_num() {
        return access_num;
    }

    public void setAccess_num(long access_num) {
        this.access_num = access_num;
    }

    public Date getAccess_date() {
        return access_date;
    }

    public void setAccess_date(Date access_date) {
        this.access_date = access_date;
    }

    public String getAccess_address() {
        return access_address;
    }

    public void setAccess_address(String access_address) {
        this.access_address = access_address;
    }

    public Timestamp getAccess_last_access_date() {
        return access_last_access_date;
    }

    public void setAccess_last_access_date(Timestamp access_last_access_date) {
        this.access_last_access_date = access_last_access_date;
    }

    public String getAccess_info() {
        return access_info;
    }

    public void setAccess_info(String access_info) {
        this.access_info = access_info;
    }
}
