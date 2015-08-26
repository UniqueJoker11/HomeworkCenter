package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.RoleDao;
import colin.web.homework.core.pojo.Homework_Role_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2015/8/26.
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 根据userId返回角色对象
     *
     * @param userId
     * @return
     */
    public List<Homework_Role_Entity> getUserRoleInfo(String userId) {
        List<String> roleList = roleDao.getUserRoleInfo(userId);
        List<Homework_Role_Entity> role_entityList = new ArrayList<>();
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
}
