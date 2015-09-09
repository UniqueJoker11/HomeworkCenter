package colin.web.homework.core.pojo;

import colin.web.homework.annotation.Column;
import colin.web.homework.annotation.Id;
import colin.web.homework.annotation.Table;
import org.springframework.stereotype.Component;



/**
 * Created by DELL on 2015/8/19.
 */
@Component
@Table(name = "homework_role")
public class Homework_Role_Entity {
    @Id
    @Column(name = "role_id")
    private String role_id;
    @Column(name = "parent_role_id")
    private String parent_role_id;
    @Column(name = "createdate")
    private String createdate;
    @Column(name = "role_name")
    private String role_name;
    @Column(name = "role_description")
    private String role_description;

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_description() {
        return role_description;
    }

    public void setRole_description(String role_description) {
        this.role_description = role_description;
    }

    public String getParent_role_id() {
        return parent_role_id;
    }

    public void setParent_role_id(String parent_role_id) {
        this.parent_role_id = parent_role_id;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
