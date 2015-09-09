package colin.web.homework.core.pojo;

import colin.web.homework.annotation.Column;
import colin.web.homework.annotation.Id;
import colin.web.homework.annotation.Table;
import org.springframework.stereotype.Component;



/**
 * Created by DELL on 2015/8/19.
 */
@Component
@Table(name = "homework_role_menu")
public class Homework_Role_Menu_Entity {
    @Id
    @Column(name="role_menu_id")
    private String role_menu_id;
    @Column(name="role_id")
    private String role_id;
    @Column(name="menu_id")
    private String menu_id;

    public String getRole_menu_id() {
        return role_menu_id;
    }

    public void setRole_menu_id(String role_menu_id) {
        this.role_menu_id = role_menu_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }
}
