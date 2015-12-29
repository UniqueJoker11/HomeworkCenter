package colin.web.homework.core.pojo;

import colin.web.homework.annotation.Column;
import colin.web.homework.annotation.Id;
import colin.web.homework.annotation.Table;

/**
 * Created by DELL on 2015/12/29.
 */
@Table(name ="homework_content_operate")
public class Homework_Content_Operate_Entity {
    @Id
    @Column(name = "content_operate_id")
    private String content_operate_id;
    @Column(name = "content_operate_resources_id")
    private String content_operate_resources_id;
    @Column(name = "content_operate_resources_describe")
    private String content_operate_resources_describe;
    @Column(name = "content_operate_resources_createtime")
    private String content_operate_resources_createtime;
    @Column(name = "content_operate_resources_user")
    private String content_operate_resources_user;

    public String getContent_operate_id() {
        return content_operate_id;
    }

    public void setContent_operate_id(String content_operate_id) {
        this.content_operate_id = content_operate_id;
    }

    public String getContent_operate_resources_id() {
        return content_operate_resources_id;
    }

    public void setContent_operate_resources_id(String content_operate_resources_id) {
        this.content_operate_resources_id = content_operate_resources_id;
    }

    public String getContent_operate_resources_describe() {
        return content_operate_resources_describe;
    }

    public void setContent_operate_resources_describe(String content_operate_resources_describe) {
        this.content_operate_resources_describe = content_operate_resources_describe;
    }

    public String getContent_operate_resources_createtime() {
        return content_operate_resources_createtime;
    }

    public void setContent_operate_resources_createtime(String content_operate_resources_createtime) {
        this.content_operate_resources_createtime = content_operate_resources_createtime;
    }

    public String getContent_operate_resources_user() {
        return content_operate_resources_user;
    }

    public void setContent_operate_resources_user(String content_operate_resources_user) {
        this.content_operate_resources_user = content_operate_resources_user;
    }
}
