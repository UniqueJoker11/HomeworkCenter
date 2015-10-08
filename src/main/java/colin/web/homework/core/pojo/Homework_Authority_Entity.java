package colin.web.homework.core.pojo;

import colin.web.homework.annotation.Id;
import colin.web.homework.annotation.Table;

import java.io.Serializable;

/**
 * Created by ASUS on 2015/8/29.
 */
@Table(name = "homework_authority")
public class Homework_Authority_Entity implements Serializable{
    @Id
    private String authority_id;
    private String parent_authority_id;
    private String authority_name;
    private String authoruty_description;

    public String getAuthority_id() {
        return authority_id;
    }

    public void setAuthority_id(String authority_id) {
        this.authority_id = authority_id;
    }

    public String getParent_authority_id() {
        return parent_authority_id;
    }

    public void setParent_authority_id(String parent_authority_id) {
        this.parent_authority_id = parent_authority_id;
    }

    public String getAuthority_name() {
        return authority_name;
    }

    public void setAuthority_name(String authority_name) {
        this.authority_name = authority_name;
    }

    public String getAuthoruty_description() {
        return authoruty_description;
    }

    public void setAuthoruty_description(String authoruty_description) {
        this.authoruty_description = authoruty_description;
    }
}
