package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.UserDao;
import colin.web.homework.core.pojo.Homework_User_Entity;
import colin.web.homework.core.pojo.Homework_Userinfo_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import colin.web.homework.core.vo.HomeworkUserInfo;
import colin.web.homework.core.vo.HomeworkUserRole;
import colin.web.homework.core.vo.HomeworkUserinfoDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
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
     *
     * @return
     */
    public List<Homework_User_Entity> getAllUserInfo() {
        return this.userDao.seletcObjectByMap(Homework_User_Entity.class, null, new DefaultRowmapper<Homework_User_Entity>(Homework_User_Entity.class.getName()));
    }

    /**
     * 根绝用户名和密码验证用户的信息
     *
     * @param username
     * @param password
     * @return
     */
    public Map<String, Object> validateUserInfoByNamePwd(String username, String password) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_name", username);
        params.put("user_password", password);
        return userDao.validateUserInfoByNamePwd(params);
    }

    /**
     * 根绝用户的ID来查询用户的角色信息
     *
     * @param user_id
     * @return
     */
    public List<HomeworkUserInfo> fetchUserInfoListByUserRole(String user_id) {
        List<HomeworkUserInfo> userInfoList = userDao.fetchUserRoleInfo(initParams(user_id));
        return userInfoList;
    }

    /**
     * 根据用户id来获取用户的详细信息
     * @param user_id
     * @return
     */
    public HomeworkUserinfoDetail findUserinfoDetail(String user_id){
        Homework_User_Entity user_entity=userDao.selectObjectById(Homework_User_Entity.class, user_id, new DefaultRowmapper<Homework_User_Entity>(Homework_User_Entity.class.getName()));
        final HomeworkUserinfoDetail userinfoDetail=new HomeworkUserinfoDetail();
        userinfoDetail.setUser_callname(user_entity.getUser_callname());
        userinfoDetail.setUser_email(user_entity.getUser_email());
        userinfoDetail.setUser_id(user_entity.getUser_id());
        userinfoDetail.setUser_name(user_entity.getUser_name());
        userinfoDetail.setUser_phone(user_entity.getUser_phone());
        userinfoDetail.setUser_head(user_entity.getUser_head());
        String searchUserDetailSql="select user_info_firstname,user_info_lastname,user_info_province,user_info_city,user_info_area,user_info_birthday,user_info_address,user_info_gendar,user_info_blood,user_info_qq,user_info_college  from homework_user_info where user_id=:user_id";
        this.userDao.getJdbcTemplate().query(searchUserDetailSql, this.initParams(user_id), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                userinfoDetail.setUser_info_address(resultSet.getString("user_info_address"));
                userinfoDetail.setUser_info_area(resultSet.getString("user_info_area"));
                userinfoDetail.setUser_info_birthday(resultSet.getString("user_info_birthday"));
                userinfoDetail.setUser_info_blood(resultSet.getString("user_info_blood"));
                userinfoDetail.setUser_info_city(resultSet.getString("user_info_city"));
                userinfoDetail.setUser_info_college(resultSet.getString("user_info_college"));
                userinfoDetail.setUser_info_firstname(resultSet.getString("user_info_firstname"));
                userinfoDetail.setUser_info_gendar(resultSet.getString("user_info_gendar"));
                userinfoDetail.setUser_info_lastname(resultSet.getString("user_info_lastname"));
                userinfoDetail.setUser_info_address(resultSet.getString("user_info_address"));
                userinfoDetail.setUser_info_area(resultSet.getString("user_info_area"));
                userinfoDetail.setUser_info_province(resultSet.getString("user_info_province"));
                userinfoDetail.setUser_info_qq(resultSet.getString("user_info_qq"));
            }
        });
        return userinfoDetail;
    }

    public List<HomeworkUserRole> fetchUserRoleDetail(String user_id) {
        return userDao.fetchUserRoleList(initParams(user_id));
    }

    /**
     * 更新用户的角色配置
     * @param userId
     * @param roleIds
     * @return
     */
    public boolean updateUserRoleConfig(String userId,String[] roleIds){
       return this.userDao.updateUserRoleConfig(userId, roleIds);
    }

    /**
     * 根据用户id删除其信息及其角色
     * @param userId
     * @return
     */
    public boolean deleteUserinfoById(String userId){
        return this.userDao.delUserInfoById(userId);
    }

    private Map<String, Object> initParams(String user_id) {
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("user_id", user_id);
        return params;
    }
}
