package colin.web.homework.core.dao.decoratedao;

import colin.web.homework.core.pojo.Homework_Aticle_Entity;
import colin.web.homework.core.pojo.Homework_Menu_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by ASUS on 2015/7/26.
 */
@Repository
public class MenuDao extends DecorateCommnDao {

    /**
     * 根绝roleId来查询用户的菜单
     *
     * @param roleIds
     * @return
     */
    public List<Homework_Menu_Entity> getUserMenuInfoByRoleId(List<String> roleIds) {
        StringBuilder roleIdTemplate=new StringBuilder("");
        Map<String,Object> params=new HashMap<String, Object>();
        for(int i=0;i<roleIds.size();i++){
            params.put("roleId"+i,roleIds.get(i));
            roleIdTemplate.append(":roleId"+i).append(",");
        }
        String searchMenuSql = "select * from homework_menu where menu_id in ( select menu_id from homework_role_menu where role_id in("+ roleIdTemplate.substring(0,roleIdTemplate.length()-1)+")) ORDER BY menu_parent_id";
        return super.getJdbcTemplate().query(searchMenuSql,params,new DefaultRowmapper<Homework_Menu_Entity>(Homework_Menu_Entity.class.getName()));
    }

    /**
     * 根据用户的id获取用户的所拥有的的菜单
     * @param params
     * @return
     */
    public List<Homework_Menu_Entity> fetchUserMenuEntityByUserId(Map<String,Object> params){
        String searchSql="select * from homework_menu WHERE menu_id in (select DISTINCT(menu_id) from homework_role_menu where role_id in (select role_id from homework_user_role where user_id=:userId)) ORDER BY menu_order";
        return super.getJdbcTemplate().query(searchSql, params, new DefaultRowmapper<Homework_Menu_Entity>(Homework_Menu_Entity.class.getName()));
    }
}
