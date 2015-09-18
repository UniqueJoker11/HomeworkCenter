package colin.web.homework.controller.system;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by DELL on 2015/8/26.
 */
@Controller
@Scope("request")
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class UserManageController extends BaseController{

    /**
     * 显示用户管理页面
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_USER_MANAGE_PAGE,method = RequestMethod.GET)
    public String showUserManagePage(){
        return HomeworkConstants.PAGE_USER_MANAGE_VIEW;
    }
    @RequestMapping(value = HomeworkConstants.CONTROLLER_USER_MANAGE_INFO,method = RequestMethod.POST)
    public Object getManageUserInfo(){
        return null;
    }

}
