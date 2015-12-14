package colin.web.homework.core.dao.decoratedao;

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
        String searchMenuSql = "select menu_id from homework_role_menu where role_id=:role_id";
        Map<String, Object> searchParams = new HashMap<String,Object>();
        Set<String> menuIds = new HashSet<String>();
        for (String roleId : roleIds) {
            searchParams.put("role_id", roleId);
            //开始查询
            List<String> menuList = this.getJdbcTemplate().queryForList(searchMenuSql, searchParams, String.class);
            //去重复
            menuIds.addAll(menuList);
        }
        //根绝menuId返回所有的Menu对象
        List<Homework_Menu_Entity> menu_entityList = new ArrayList<Homework_Menu_Entity>();
        for (String menuId : menuIds) {
            menu_entityList.add(this.selectObjectById(Homework_Menu_Entity.class, menuId, new DefaultRowmapper<Homework_Menu_Entity>(Homework_Menu_Entity.class.getName())));
        }
        Collections.sort(menu_entityList,new MenuComparator());
        return menu_entityList;
    }
    public class MenuComparator implements Comparator<Homework_Menu_Entity>{

        /**
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return a negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         * @throws NullPointerException if an argument is null and this
         *                              comparator does not permit null arguments
         * @throws ClassCastException   if the arguments' types prevent them from
         *                              being compared by this comparator.
         */
        @Override
        public int compare(Homework_Menu_Entity o1, Homework_Menu_Entity o2) {
            return o1.getMenu_order()>o2.getMenu_order()?1:-1;
        }
    }
}
