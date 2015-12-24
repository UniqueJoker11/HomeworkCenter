package colin.web.homework.core.dao.decoratedao;

import colin.web.homework.core.pojo.Homework_Role_Entity;
import colin.web.homework.core.pojo.Homework_User_Role_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/26.
 */
@Repository
public class RoleDao extends DecorateCommnDao {

    /**
     * 根据用户id获取所有的角色id
     *
     * @param userId
     * @return
     */
    public List<String> getUserRoleInfo(String userId) {
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("user_id", userId);
        //根绝userId获取到所有的用户角色
        String searchSql = "select role_id from homework_user_role where user_id=:user_id";
        List<String> roleList = this.getJdbcTemplate().queryForList(searchSql, searchParams, String.class);
        return roleList;
    }

    /**
     * 根绝角色id删除对应的菜单配置
     * @param params
     */
    public void delSystemRoleMenu(Map<String,Object> params){
        String delSql="delete from homework_role_menu where role_id=:roleId";
        this.getJdbcTemplate().update(delSql,params);
    }

    /**
     * 根绝角色的id插入菜单
     * @param roleMenuParams
     */
    public void insertSystemRoleMenu(List<Map<String,Object>> roleMenuParams){
        String insertSql="insert into homework_role_menu(role_menu_id,role_id,menu_id) values(:roleMenuId,:roleId,:menuId)";
        for(Map<String,Object> params:roleMenuParams){
            this.getJdbcTemplate().update(insertSql,params);
        }
    }

}
