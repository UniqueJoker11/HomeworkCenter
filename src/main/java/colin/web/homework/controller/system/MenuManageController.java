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
     * @param menuId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_DEL_MENU_INFO, method = RequestMethod.POST)
    public Object deleteSystemMenuInfo(@RequestParam(value = "menuId") String menuId) {
        return this.menuService.deleteMenuInfo(menuId);
    }
}
