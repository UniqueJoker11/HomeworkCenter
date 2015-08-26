package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.UserDao;
import colin.web.homework.core.pojo.Homework_User_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/26.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 返回当前所有的用户的信息
     * @return
     */
    public List<Homework_User_Entity> getAllUserInfo() {
        return this.userDao.seletcObjectByMap(Homework_User_Entity.class, null, new DefaultRowmapper<Homework_User_Entity>(Homework_User_Entity.class.getName()));
    }

    /**
     * 根绝用户名和密码验证用户的信息
     * @param username
     * @param password
     * @return
     */
    public Map<String,Object> validateUserInfoByNamePwd(String username,String password){
        Map<String,Object> params=new HashMap<>();
        params.put("user_name",username);
        params.put("user_password",password);
       return userDao.validateUserInfoByNamePwd(params);
    }
}
