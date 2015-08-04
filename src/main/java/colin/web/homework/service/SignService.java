package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.SignDao;
import colin.web.homework.core.pojo.Homework_User_Entity;
import colin.web.homework.core.rowmapper.Homework_User_Rowmapper;
import colin.web.homework.tools.StringToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2015/7/11.
 */
@Service
@Transactional
public class SignService {

    @Autowired
    private Homework_User_Entity homework_user_entity;
    @Autowired
    private Homework_User_Rowmapper homework_User_Rowmapper;
    @Autowired
    private SignDao signDao;

    /**
     * 驗證用戶登錄,根絕用戶名和密碼查詢
     *
     * @param params
     * @return
     */
    public Map<String, Object> valdiateUserSignin(Map<String, Object> params) {
        //組合用戶的查詢對象
        List<Homework_User_Entity> resultList = signDao.seletcObjectByMap(Homework_User_Entity.class, params, homework_User_Rowmapper);
        Map<String, Object> userInfoMap = new HashMap<>();
        if (resultList != null && !resultList.isEmpty()) {
            userInfoMap.put("isExists", true);
            userInfoMap.put("userEntity", resultList.get(0));
        } else {
            userInfoMap.put("isExists", false);
        }
        return userInfoMap;
    }

    /**
     * 方法描述：验证用户名是否存在
     * 注意事项：
     *
     * @param params
     * @return
     * @Exception 异常对象
     */
    public Boolean validateUsernameService(Map<String, Object> params) {
        Map<String, Object> signinParams = signDao.validateUserSignin(params);
        return (Boolean) signinParams.get("isExists");
    }

    /**
     * 提交用户的注册信息
     *
     * @return
     */
    public boolean saveUserSigninUpService(Map<String, Object> params) {
        Homework_User_Entity user_entity = new Homework_User_Entity();
        user_entity.setUser_id(StringToolsUtils.getCommonUUID());
        user_entity.setUser_name(params.get("user_name").toString());
        user_entity.setUser_password(params.get("user_password").toString());
        user_entity.setUser_email(params.get("user_email").toString());
        return this.signDao.addObjInfo(user_entity);
    }
}
