package colin.web.homework.core.vo;

import java.util.List;

/**
 * Created by DELL on 2015/9/21.
 * 用户角色Vo
 */
public class HomeworkUserRole {
    private String role_id;
    private String role_name;
    private String role_description;
    private boolean isOwned;
    private List<HomeworkUserAuthority> authorityList;
    private List<HomeworkMenuVo> menuList;

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public List<HomeworkMenuVo> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<HomeworkMenuVo> menuList) {
        this.menuList = menuList;
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

    public List<HomeworkUserAuthority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<HomeworkUserAuthority> authorityList) {
        this.authorityList = authorityList;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public void setOwned(boolean isOwned) {
        this.isOwned = isOwned;
    }
}
