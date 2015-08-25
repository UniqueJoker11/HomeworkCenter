package colin.web.homework.controller.system;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import colin.web.homework.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 2015/8/24.
 */
@Controller
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class MenuManageController extends BaseController {

    @Autowired
    private MenuService menuService;

    /**
     * 显示菜单管理主页面
     *
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_MEMU_MANAGE_PAGE, method = RequestMethod.GET)
    public String showMenuManagePage() {
        return HomeworkConstants.PAGE_MENU_MANAGE_VIEW;
    }

    /**
     * 加载根菜单的内容
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_FETCH_MENU_ROOT, method = RequestMethod.POST)
    public Object fetchSystemRootMenuList() {
        return this.menuService.getMenuNodeInfo("root");
    }

    /**
     * 加载子节点的内容
     *
     * @param menuParId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_FETCH_MENU_NODE, method = RequestMethod.POST)
    public Object fetchSystemNodeMenuList(@RequestParam(value = "menuParId") String menuParId) {
        return this.menuService.getMenuNodeInfo(menuParId);
    }

    /**
     * 根绝id删除节点
     *
     * @param menuId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_DEL_MENU_INFO, method = RequestMethod.POST)
    public Object deleteSystemMenuInfo(@RequestParam(value = "menuId") String menuId) {
        return this.menuService.deleteMenuInfo(menuId);
    }

    /**
     * 添加菜单根目录
     *
     * @param menuName
     * @param menuIcon
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ADD_ROOT_MENU_INFO, method = RequestMethod.POST)
    public Object addSystemRootMenuInfo(@RequestParam(value = "menuName") String menuName, @RequestParam(value = "menuIcon") String menuIcon, @RequestParam(value = "menuOrder") String menuOrder) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("menuName", menuName);
        paramsMap.put("menuIcon", menuIcon);
        paramsMap.put("menuOrder", menuOrder);
        paramsMap.put("createUser", super.fetchUserInfo().getUser_name());
        return this.menuService.saveMenuInfo(paramsMap);
    }

    /**
     * 添加菜单子节点
     *
     * @param menuName
     * @param menuIcon
     * @param menuUrl
     * @param parentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ADD_NODE_MENU_INFO, method = RequestMethod.POST)
    public Object addSystemNodeMenuInfo(@RequestParam(value = "menuName") String menuName, @RequestParam(value = "menuIcon") String menuIcon, @RequestParam(value = "menuUrl") String menuUrl, @RequestParam(value = "parentId") String parentId, @RequestParam(value = "menuOrder") String menuOrder) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("menuName", menuName);
        paramsMap.put("menuIcon", menuIcon);
        paramsMap.put("menuUrl", menuUrl);
        paramsMap.put("parentId", parentId);
        paramsMap.put("menuOrder", menuOrder);
        paramsMap.put("createUser", super.fetchUserInfo().getUser_name());
        return this.menuService.saveMenuInfo(paramsMap);
    }

    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_UPDATE_MENU_INFO, method = RequestMethod.POST)
    public Object updateSystemMenuInfo(HttpServletRequest request) {
        return this.menuService.updateMenuInfo(super.formatRequestParamsMap(request));
    }
}
