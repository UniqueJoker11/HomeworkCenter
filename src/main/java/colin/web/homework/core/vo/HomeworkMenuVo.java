package colin.web.homework.core.vo;

import java.util.List;

/**
 * Created by ASUS on 2015/7/26.
 * 菜单类
 */
public class HomeworkMenuVo {
    private String menu_id;
    private String menu_name;
    private String menu_parent_id;
    private String menu_url;
    private String menu_icon;
    private List<HomeworkMenuVo> childMenuList;

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

    public String getMenu_icon() {
        return menu_icon;
    }

    public void setMenu_icon(String menu_icon) {
        this.menu_icon = menu_icon;
    }

    public List<HomeworkMenuVo> getChildMenuList() {
        return childMenuList;
    }

    public void setChildMenuList(List<HomeworkMenuVo> childMenuList) {
        this.childMenuList = childMenuList;
    }
}
