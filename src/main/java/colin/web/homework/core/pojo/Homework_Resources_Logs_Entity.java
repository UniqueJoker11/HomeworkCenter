package colin.web.homework.core.pojo;

import colin.web.homework.annotation.Column;
import colin.web.homework.annotation.Id;
import colin.web.homework.annotation.Table;

/**
 * Created by DELL on 2016/1/8.
 */
@Table(name = "homework_resources_logs")
public class Homework_Resources_Logs_Entity {
    @Id
    @Column(name = "resources_id")
    private String resources_id;
    @Column(name = "resources_name")
    private String resources_name;
    @Column(name = "resources_createtime")
    private String resources_createtime;
    @Column(name = "resources_tags")
    private String resources_tags;
    @Column(name = "resources_nav_id")
    private String resources_nav_id;
    @Column(name = "resources_user")
    private String resources_user;

    public String getResources_id() {
        return resources_id;
    }

    public void setResources_id(String resources_id) {
        this.resources_id = resources_id;
    }

    public String getResources_name() {
        return resources_name;
    }

    public void setResources_name(String resources_name) {
        this.resources_name = resources_name;
    }

    public String getResources_createtime() {
        return resources_createtime;
    }

    public void setResources_createtime(String resources_createtime) {
        this.resources_createtime = resources_createtime;
    }

    public String getResources_tags() {
        return resources_tags;
    }

    public void setResources_tags(String resources_tags) {
        this.resources_tags = resources_tags;
    }

    public String getResources_nav_id() {
        return resources_nav_id;
    }

    public void setResources_nav_id(String resources_nav_id) {
        this.resources_nav_id = resources_nav_id;
    }

    public String getResources_user() {
        return resources_user;
    }

    public void setResources_user(String resources_user) {
        this.resources_user = resources_user;
    }
}
