package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.MenuDao;
import colin.web.homework.core.dao.decoratedao.RoleDao;
import colin.web.homework.core.pojo.Homework_Menu_Entity;
import colin.web.homework.core.vo.HomeworkMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DELL on 2015/8/26.
 */
@Service
public class DashboardService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    /**
     * 根据用户的id来返回所有的用户信息
     * @param userId
     * @return
     */
    public List<HomeworkMenuVo> getUserMenuInfo(String userId) {
        List<String> roleIds = roleService.getUserRoleIdInfo(userId);//获取用户的所有角色id
        if (roleIds != null && !roleIds.isEmpty()) {
            return menuService.getUserMenuInfo(roleIds);
        } else {
            return null;
        }
    }
}
