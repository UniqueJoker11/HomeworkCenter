package colin.web.homework.controller.system;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import colin.web.homework.core.vo.HomeworkUserInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DELL on 2015/8/20.
 */
@Controller
@Scope(value = "request")
@RequestMapping(value = HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class PersonManageController extends BaseController{
    @RequestMapping(value = HomeworkConstants.CONTROLLER_PERSON_MANAGE_PAGE, method = RequestMethod.GET)
    public String showPersonManagePage() {
        //获取用户的信息
        HomeworkUserInfo userInfo=super.fetchUserInfo();
        //List<>
        return HomeworkConstants.PAGE_PERSON_MANAGE_VIEW;
    }

    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ACTION_FETCH_MANAGE_USER_ROLE, method = RequestMethod.POST)
    public Object fetchAllUserInfo() {
        return null;
    }
}
