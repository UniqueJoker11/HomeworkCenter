package colin.web.homework.core.dao.decoratedao;

import colin.web.homework.core.rowmapper.Homework_User_Rowmapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2015/7/11.
 */
@Repository
public class SigninDao<Homework_User_Entity> extends DecorateCommnDao<Homework_User_Entity> {
    /**
     *
     * 方法描述：验证用户登录
     * 注意事项：
     * @param params
     * @return
     * @Exception 异常对象
     */
    public Map<String,Object> validateUserSignin(Map<String,Object> params){
        Homework_User_Rowmapper rowMapper=new Homework_User_Rowmapper();
        List<Homework_User_Entity> userList = super.seletcObjectByMap(Homework_User_Entity.class, params, rowMapper);
        //存放返回结果，isExists:用户是否存在,userEntity：假如存在用户在存放入用户信息，msg:反馈的验证信息
        Map<String,Object> validateMap=new HashMap<String,Object>();
        if(userList==null||userList.isEmpty()){
            validateMap.put("isExists",false);
            validateMap.put("msg","查询不到用户信息");
        }else if(userList.size()==1){
            validateMap.put("isExists", true);
            validateMap.put("userEntity",userList.get(0));
        }else{
            validateMap.put("isExists",false);
            validateMap.put("msg","用户信息出现问题，请稍后再试！");
        }
        return validateMap;
    }
}
