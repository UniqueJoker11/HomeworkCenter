package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.SigninDao;
import colin.web.homework.core.pojo.Homework_User_Entity;
import colin.web.homework.core.rowmapper.Homework_User_Rowmapper;
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
public class SigninService {

    @Autowired
    private Homework_User_Entity homework_user_entity;
    @Autowired
    private Homework_User_Rowmapper homework_User_Rowmapper;
    @Autowired
    private SigninDao signinDao;

    /**
     * 驗證用戶登錄,根絕用戶名和密碼查詢
     *
     * @param params
     * @return
     */
    public Map<String, Object> valdiateUserSignin(Map<String, Object> params) {
        //組合用戶的查詢對象
        List<Homework_User_Entity> resultList = signinDao.seletcObjectByMap(Homework_User_Entity.class, params, homework_User_Rowmapper);
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
        Map<String, Object> signinParams = signinDao.validateUserSignin(params);
        return (Boolean) signinParams.get("isExists");
    }
}
