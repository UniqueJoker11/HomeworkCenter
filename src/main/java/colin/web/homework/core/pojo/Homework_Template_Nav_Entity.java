package colin.web.homework.core.pojo;

import colin.web.homework.annotation.Column;
import colin.web.homework.annotation.Id;
import colin.web.homework.annotation.Table;

/**
 * Created by DELL on 2016/1/26.
 */
@Table(name = "homework_template_nav")
public class Homework_Template_Nav_Entity {
    @Id
    @Column(name = "template_nav_id")
    private String template_nav_id;
    @Column(name = "template_nav_src")
    private String template_nav_src;
    @Column(name = "template_nav_digest")
    private String template_nav_digest;
    @Column(name = "template_nav_order")
    private String template_nav_order;
    @Column(name = "template_nav_user")
    private String template_nav_user;
    @Column(name = "template_nav_createtime")
    private String template_nav_createtime;
    @Column(name = "template_nav_updatetime")
    private String template_nav_updatetime;

    public String getTemplate_nav_id() {
        return template_nav_id;
    }

    public void setTemplate_nav_id(String template_nav_id) {
        this.template_nav_id = template_nav_id;
    }

    public String getTemplate_nav_src() {
        return template_nav_src;
    }

    public void setTemplate_nav_src(String template_nav_src) {
        this.template_nav_src = template_nav_src;
    }

    public String getTemplate_nav_digest() {
        return template_nav_digest;
    }

    public void setTemplate_nav_digest(String template_nav_digest) {
        this.template_nav_digest = template_nav_digest;
    }

    public String getTemplate_nav_order() {
        return template_nav_order;
    }

    public void setTemplate_nav_order(String template_nav_order) {
        this.template_nav_order = template_nav_order;
    }

    public String getTemplate_nav_user() {
        return template_nav_user;
    }

    public void setTemplate_nav_user(String template_nav_user) {
        this.template_nav_user = template_nav_user;
    }

    public String getTemplate_nav_createtime() {
        return template_nav_createtime;
    }

    public void setTemplate_nav_createtime(String template_nav_createtime) {
        this.template_nav_createtime = template_nav_createtime;
    }

    public String getTemplate_nav_updatetime() {
        return template_nav_updatetime;
    }

    public void setTemplate_nav_updatetime(String template_nav_updatetime) {
        this.template_nav_updatetime = template_nav_updatetime;
    }
}
