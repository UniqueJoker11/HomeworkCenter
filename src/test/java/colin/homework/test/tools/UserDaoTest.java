package colin.homework.test.tools;

import colin.web.homework.core.dao.decoratedao.RoleDao;
import colin.web.homework.core.dao.decoratedao.UserDao;
import colin.web.homework.core.pojo.Homework_Role_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by DELL on 2015/12/24.
 */
public class UserDaoTest extends BaseSpringTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Test
    public void testSearchRoleChildren() {
        List<Homework_Role_Entity> menu_entityList = roleDao.seletcObjectByMap(Homework_Role_Entity.class, null, new DefaultRowmapper<Homework_Role_Entity>(Homework_Role_Entity.class.getName()));
        List<String> result = userDao.formatRoleChildren(menu_entityList, "1e310db0-6331-4457-8868-ca6a6b21ae83");
        for (String roleId : result) {
            System.out.println(roleId);
        }
    }
}
