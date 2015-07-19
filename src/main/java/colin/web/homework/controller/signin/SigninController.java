package colin.web.homework.controller.signin;

import colin.web.homework.controller.BaseController;
import colin.web.homework.service.SigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ASUS on 2015/7/11.
 */
@Controller
@RequestMapping(value = "homework")
public class SigninController extends BaseController {

    @Autowired
    private SigninService signinService;

    /**
     * 显示登陆页面
     *
     * @return
     */
    @RequestMapping(value = "/signin.html")
    public String showSigninPage() {
        return "signin";
    }

    /**
     * 驗證用戶登錄的方法
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/userSignin.html",method = RequestMethod.POST)
    public ModelAndView userSignin(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("user_name", username);
        searchParams.put("user_password", password);
        Map<String, Object> searchResult = signinService.valdiateUserSignin(searchParams);
        ModelAndView userIndex = new ModelAndView();
        if ((Boolean) searchResult.get("isExists")) {
            //TODO 存放用户信息到session
            userIndex.setViewName("index");
            return userIndex;
        }else{
            userIndex.setViewName("signin");
            return userIndex;
        }

    }
}
