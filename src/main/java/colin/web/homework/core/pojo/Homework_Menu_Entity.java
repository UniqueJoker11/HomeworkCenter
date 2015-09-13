package colin.web.homework.core.pojo;

import colin.web.homework.annotation.Column;
import colin.web.homework.annotation.Id;
import colin.web.homework.annotation.Table;
import org.springframework.stereotype.Component;



/**
 * Created by ASUS on 2015/7/24.
 */
@Component
@Table(name = "homework_menu")
public class Homework_Menu_Entity {
    @Id
    @Column(name = "menu_id")
    private String menu_id;
    @Column(name = "menu_name")
    private String menu_name;
    @Column(name = "menu_parent_id")
    private String menu_parent_id;
    @Column(name = "menu_url")
    private String menu_url;
    @Column(name = "menu_create_user")
    private String menu_create_user;
    @Column(name = "menu_create_time")
    private String menu_create_time;
    @Column(name = "menu_icon")
    private String menu_icon;
    @Column(name = "menu_order")
    private int menu_order;

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_parent_id() {
        return menu_parent_id;
    }

    public void setMenu_parent_id(String menu_parent_id) {
        this.menu_parent_id = menu_parent_id;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public String getMenu_create_user() {
        return menu_create_user;
    }

    public void setMenu_create_user(String menu_create_user) {
        this.menu_create_user = menu_create_user;
    }

    public String getMenu_create_time() {
        return menu_create_time;
    }

    public void setMenu_create_time(String menu_create_time) {
        this.menu_create_time = menu_create_time;
    }

    public String getMenu_icon() {
        return menu_icon;
    }

    public void setMenu_icon(String menu_icon) {
        this.menu_icon = menu_icon;
    }

    public int getMenu_order() {
        return menu_order;
    }

    public void setMenu_order(int menu_order) {
        this.menu_order = menu_order;
    }
}
