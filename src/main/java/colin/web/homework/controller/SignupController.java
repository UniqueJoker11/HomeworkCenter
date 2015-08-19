package colin.web.homework.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@Scope(value = "request")
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class SignupController extends BaseController {

	@Autowired
	private SignupService signupService;

	/**
	 * 
	 * 方法描述： 显示注册页面 注意事项：
	 * 
	 * @return
	 * @Exception 异常对象
	 */
	@RequestMapping(value = HomeworkConstants.CONTROLLER_SIGNUP,method = {RequestMethod.GET,RequestMethod.HEAD})
    public String showSignupPage(HttpServletRequest request){
		return HomeworkConstants.PAGE_SIGNUP;
	}

	@RequestMapping(value = HomeworkConstants.CONTROLLER_SIGNUP_FORM, method = RequestMethod.POST)
	public String signupUserinfo(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,@RequestParam(value="confirmPassword",required=true) String confirmPassword) {
		if (username == null || username.trim().equals("") || password == null
				|| password.trim().equals("")) {
			return HomeworkConstants.PAGE_SIGNUP;
		} else {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("user_name",username);
			params.put("user_password",password);
			boolean result = this.signupService.signupUserinfoService(params);
			if (result) {
				return "redirect:"+HomeworkConstants.CONTROLLER_MANAGER_PREFIX +HomeworkConstants.CONTROLLER_SIGNIN;
			} else {
				return HomeworkConstants.PAGE_SIGNUP;
			}
		}
	}

}
