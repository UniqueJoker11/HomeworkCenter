package colin.web.homework.core.dao.decoratedao;

import colin.web.homework.core.pojo.Homework_User_Entity;
import colin.web.homework.core.pojo.Homework_User_Role_Entity;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/26.
 */
@Repository
public class UserDao extends DecorateCommnDao {


    /**
     * 根据用户名和密码验证用户的登录信息是否存在
     * @param params
     * @return
     */
    public Map<String,Object> validateUserInfoByNamePwd(Map<String, Object> params) {
        String searchSql = "select user_id,user_callname from homework_user where (user_name,user_password)=(:user_name,:user_password)";
        final Map<String,Object> result=new HashMap<>();
        this.getJdbcTemplate().query(searchSql, params, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                if(resultSet.getString("user_id")!=null){
                    result.put("isExists",true);
                    result.put("callname",resultSet.getString("user_callname"));
                }else{
                    result.put("isExists",false);
                }
            }
        });
       return result;
    }

    public List<Homework_User_Role_Entity> fetchUserRoleInfo(){
        String searchUserRoleSql="select user_id from homework_user_role where role_id in(select role_id from homework_user_role where user_id=:user_id)";
       // List<String> role_id
        return null;
    }
}
