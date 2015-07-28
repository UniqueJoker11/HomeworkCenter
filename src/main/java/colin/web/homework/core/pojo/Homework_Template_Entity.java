package colin.web.homework.core.pojo;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by DELL on 2015/7/27.
 */
@Component
@Table(name = "homework_template")
public class Homework_Template_Entity {
    @Column(name="template_id")
    private String template_id;
    @Column(name="template_name")
    private String template_name;
    @Column(name="template_snapshot")
    private String template_snapshot;
    @Column(name="template_resource_url")
    private String template_resource_url;
    @Column(name="template_uplodad_user")
    private String template_uplodad_user;
    @Column(name="template_create_time")
    private String template_create_time;
    @Column(name="template_access_url")
    private String template_access_url;
    @Column(name = "templaye_browser_num")
    private int templaye_browser_num;
    @Column(name = "template_describe")
    private String template_describe;
    @Column(name = "template_tip")
    private String template_tip;
    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getTemplate_snapshot() {
        return template_snapshot;
    }

    public void setTemplate_snapshot(String template_snapshot) {
        this.template_snapshot = template_snapshot;
    }

    public String getTemplate_resource_url() {
        return template_resource_url;
    }

    public void setTemplate_resource_url(String template_resource_url) {
        this.template_resource_url = template_resource_url;
    }

    public String getTemplate_uplodad_user() {
        return template_uplodad_user;
    }

    public void setTemplate_uplodad_user(String template_uplodad_user) {
        this.template_uplodad_user = template_uplodad_user;
    }

    public String getTemplate_create_time() {
        return template_create_time;
    }

    public void setTemplate_create_time(String template_create_time) {
        this.template_create_time = template_create_time;
    }

    public String getTemplate_access_url() {
        return template_access_url;
    }

    public void setTemplate_access_url(String template_access_url) {
        this.template_access_url = template_access_url;
    }

    public int getTemplaye_browser_num() {
        return templaye_browser_num;
    }

    public void setTemplaye_browser_num(int templaye_browser_num) {
        this.templaye_browser_num = templaye_browser_num;
    }

    public String getTemplate_describe() {
        return template_describe;
    }

    public void setTemplate_describe(String template_describe) {
        this.template_describe = template_describe;
    }

    public String getTemplate_tip() {
        return template_tip;
    }

    public void setTemplate_tip(String template_tip) {
        this.template_tip = template_tip;
    }
}
