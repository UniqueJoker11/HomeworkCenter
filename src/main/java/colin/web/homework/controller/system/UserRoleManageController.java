package colin.web.homework.controller.system;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by DELL on 2015/10/20.
 */
@Controller
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class UserRoleManageController extends BaseController{

    @RequestMapping(value = HomeworkConstants.CONTROLLER_SYSTEM_USER_ROLE_MANAGE_PAGE,method = RequestMethod.GET)
    public String showUserroleManagePage(){
        return HomeworkConstants.PAGE_USER_ROLE_MANAGE_VIEW;
    }

}
