package colin.web.homework.controller;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.core.pojo.Homework_User_Entity;
import colin.web.homework.core.vo.HomeworkUserInfo;
import colin.web.homework.service.SigninService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ASUS on 2015/7/11.
 */
@Controller
@RequestMapping(value = HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class SigninController extends BaseController {

    @Autowired
    private SigninService signinService;

    /**
     * 显示登陆页面
     *
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_SIGNIN)
    public String showSigninPage() {
        return HomeworkConstants.PAGE_SIGNIN;
    }

    /**
     * 方法描述：验证用户的用户名是否存在
     * 注意事项：
     *
     * @param username
     * @return
     * @Exception 异常对象
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_USERINFO_VALIDATION, method = RequestMethod.POST)
    public Boolean validateUserSigninUsername(
            @RequestParam(value = "username", required = true) String username) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (username == null || username.trim().equals("")) {
            return false;
        } else {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("user_name", username);
            Boolean validateResult = this.signinService.validateUsernameService(params);
            if (validateResult) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 驗證用戶登錄的方法
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_SIGNIN_FORM, method = RequestMethod.POST)
    public String userSignin(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("user_name", username);
        searchParams.put("user_password", password);
        Map<String, Object> searchResult = signinService.valdiateUserSignin(searchParams);
        if ((Boolean) searchResult.get("isExists")) {
            Homework_User_Entity homework_user_entity = (Homework_User_Entity) searchResult.get("userEntity");
            //初始化用户信息
            HomeworkUserInfo userInfo = this.initUserInfo(homework_user_entity);
            //存放Session
            super.getSessionObj().setAttribute(HomeworkConstants.SESSION_USERINFO, userInfo);
            Subject user = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUser_name(),userInfo.getUser_password());
            token.setRememberMe(true);
            return "redirect:" + HomeworkConstants.CONTROLLER_MANAGER_PREFIX + HomeworkConstants.CONTROLLER_DASHBOARD;
        } else {
            return HomeworkConstants.PAGE_SIGNIN;
        }

    }

    /**
     * 初始化用户的信息
     *
     * @param homework_user_entity
     * @return
     */
    public HomeworkUserInfo initUserInfo(Homework_User_Entity homework_user_entity) {
        HomeworkUserInfo userInfo = new HomeworkUserInfo();
        userInfo.setUser_id(homework_user_entity.getUser_id());
        // userInfo.setUser_phone(homework_user_entity.getUser_phone());
        userInfo.setUser_password(homework_user_entity.getUser_password());
        userInfo.setUser_callname(homework_user_entity.getUser_callname());
        //userInfo.setUser_createtime(homework_user_entity.getUser_createtime());
        // userInfo.setUser_email(homework_user_entity.getUser_email());
        // userInfo.setUser_logintime(homework_user_entity.getUser_logintime());
        userInfo.setUser_name(homework_user_entity.getUser_name());
        //  userInfo.setUser_organize_id(homework_user_entity.getUser_organize_id());
        return userInfo;
    }
}
