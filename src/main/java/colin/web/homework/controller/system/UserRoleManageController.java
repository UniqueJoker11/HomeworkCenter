package colin.web.homework.controller.system;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import colin.web.homework.service.MenuService;
import colin.web.homework.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by DELL on 2015/10/20.
 */
@Controller
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class UserRoleManageController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 初始化系统角色管理页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_SYSTEM_USER_ROLE_MANAGE_PAGE, method = RequestMethod.GET)
    public String showUserroleManagePage(HttpServletRequest request) {
        request.setAttribute("rolesList", roleService.listSystemRoleInfoService());
        return HomeworkConstants.PAGE_USER_ROLE_MANAGE_VIEW;
    }

    /**
     * 返回所有的系统角色
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_SYSTEM_USER_ROLE_LIST_ACTION, method = RequestMethod.POST)
    public Object listSystemRoleInfo() {
        return roleService.listSystemRoleInfoService();
    }

    /**
     * 获取角色对应的菜单内容
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_SYSTEM_ROLE_MENU_ACTION, method = RequestMethod.POST)
    public Object listSystemRoleMenuList(@RequestParam String roleId) {
        return menuService.fetchMenuTreeinfo(roleId);
    }

    /**
     * 更新配置角色的对应的菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_UPDATE_SYSTEM_ROLE_MENU_ACTION,method = RequestMethod.POST)
    public Object updateSystemRoleMenuConfig(@RequestParam String roleId,@RequestParam("menuIds[]") List<String> menuIds){
        //先删除原来的配置
        this.roleService.delSystemRoleMenu(roleId);
        //重新配置菜单
        this.roleService.insertSystemRoleMenu(roleId, menuIds);
        return true;
    }

    /**
     * 删除用户的角色
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_DELETE_SYSTEM_ROLE_MENU_ACTION,method = RequestMethod.POST)
    public Object delSystemRole(String roleId){
        this.roleService.delSystemRoleMenu(roleId);
        return true;
    }
}
