package colin.web.homework.controller.system;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import colin.web.homework.core.vo.HomeworkUserInfo;
import colin.web.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by DELL on 2015/8/26.
 */
@Controller
@Scope("request")
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class UserManageController extends BaseController {

    @Autowired
    private UserService userService;
    /**
     * 显示用户管理页面
     *
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_USER_MANAGE_PAGE, method = RequestMethod.GET)
    public String showUserManagePage() {
        List<HomeworkUserInfo> userInfoList=userService.fetchUserInfoListByUserRole(super.fetchUserInfo().getUser_id());
        super.getRequestObj().setAttribute("userList",userInfoList);
        return HomeworkConstants.PAGE_USER_MANAGE_VIEW;
    }

    /**
     * 获取要用户当前的权限ID
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_USER_MANAGE_INFO, method = RequestMethod.POST)
    public Object getManageUserInfo(@RequestParam(value = "userId")String userId) {
        return userService.fetchUserRoleDetail(userId);
    }

}
