package colin.web.homework.controller;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ASUS on 2015/7/12.
 */
@Controller
@RequestMapping(value = HomeworkConstants.CONTROLLER_MANAGER)
public class SignupController extends BaseController {

    /**
     * 显示注册页面
     *
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_SIGNUP, method = RequestMethod.GET)
    public String showSignupPage() {
        return HomeworkConstants.PAGE_SIGNUP;
    }

    @RequestMapping(value = HomeworkConstants.CONTROLLER_SIGNUP_FORM, method = RequestMethod.POST)
    public Object userSignup(@RequestParam String username, @RequestParam String password, @RequestParam String codeParam) {
        return null;
    }
}
