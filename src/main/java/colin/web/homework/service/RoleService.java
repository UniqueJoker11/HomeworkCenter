package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.RoleDao;
import colin.web.homework.core.pojo.Homework_Role_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import colin.web.homework.tools.StringToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/26.
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 返回所有的系统角色
     * @return
     */
    public List<Homework_Role_Entity> listSystemRoleInfoService(){
        return roleDao.seletcObjectByMap(Homework_Role_Entity.class,null,new DefaultRowmapper<Homework_Role_Entity>(Homework_Role_Entity.class.getName()));
    }

    /**
     * 根据userId返回角色对象
     *
     * @param userId
     * @return
     */
    public List<Homework_Role_Entity> getUserRoleInfo(String userId) {
        List<String> roleList = roleDao.getUserRoleInfo(userId);
        List<Homework_Role_Entity> role_entityList = new ArrayList<Homework_Role_Entity>();
        for (String roleId : roleList) {
            role_entityList.add(roleDao.selectObjectById(Homework_Role_Entity.class, roleId, new DefaultRowmapper<Homework_Role_Entity>(Homework_Role_Entity.class.getName())));
        }
        return role_entityList;
    }

    /**
     * 根绝用户的id返回用户的角色id集合
     * @param userId
     * @return
     */
    public List<String> getUserRoleIdInfo(String userId){
        return roleDao.getUserRoleInfo(userId);
    }
    /**
     * 更新系统角色对应的菜单配置
     * @param roleId
     * @return
     */
    public void delSystemRoleMenu(String roleId){
        //删除原来角色配置的菜单
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("roleId",roleId);
        this.roleDao.delSystemRoleMenu(params);
    }

    /**
     * 根绝角色id来插入新的菜单配置
     * @param roleId
     * @param menuIds
     * @return
     */
    public void insertSystemRoleMenu(String roleId,List<String> menuIds){
        List<Map<String,Object>> params=new ArrayList<Map<String,Object>>();
        for(String menuId:menuIds){
            Map<String,Object> menuParams=new HashMap<String,Object>();
            menuParams.put("roleMenuId", StringToolsUtils.getCommonUUID());
            menuParams.put("roleId",roleId);
            menuParams.put("menuId",menuId);
            params.add(menuParams);
        }
        this.roleDao.insertSystemRoleMenu(params);
    }
}
