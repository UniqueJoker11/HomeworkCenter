package colin.web.homework.core.vo;


import java.util.List;

/**
 * Created by ASUS on 2015/12/27.
 */
public class HomeworkNavManageVo {

    private String nav_id;
    private String nav_name;
    private String nav_parent_id;
    private String nav_createtime;
    private String nav_user;
    private String nav_url;
    private List<HomeworkNavManageVo> childNavManageVoList;

    public String getNav_id() {
        return nav_id;
    }

    public void setNav_id(String nav_id) {
        this.nav_id = nav_id;
    }

    public String getNav_name() {
        return nav_name;
    }

    public void setNav_name(String nav_name) {
        this.nav_name = nav_name;
    }

    public String getNav_parent_id() {
        return nav_parent_id;
    }

    public void setNav_parent_id(String nav_parent_id) {
        this.nav_parent_id = nav_parent_id;
    }

    public String getNav_createtime() {
        return nav_createtime;
    }

    public void setNav_createtime(String nav_createtime) {
        this.nav_createtime = nav_createtime;
    }

    public String getNav_user() {
        return nav_user;
    }

    public void setNav_user(String nav_user) {
        this.nav_user = nav_user;
    }

    public List<HomeworkNavManageVo> getChildNavManageVoList() {
        return childNavManageVoList;
    }

    public void setChildNavManageVoList(List<HomeworkNavManageVo> childNavManageVoList) {
        this.childNavManageVoList = childNavManageVoList;
    }

    public String getNav_url() {
        return nav_url;
    }

    public void setNav_url(String nav_url) {
        this.nav_url = nav_url;
    }
}
