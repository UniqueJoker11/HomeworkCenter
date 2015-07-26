package colin.web.homework.controller;

import colin.web.homework.common.HomeworkConstants;

import colin.web.homework.core.dao.decoratedao.MenuDao;
import colin.web.homework.core.vo.MenuVo;
import colin.web.homework.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ASUS on 2015/7/26.
 */
@Controller
@RequestMapping(value = HomeworkConstants.CONTROLLER_MANAGER)
public class DashboardController extends BaseController {

    @Autowired
    private MenuService menuService;//加载菜单服务

    @RequestMapping(value = HomeworkConstants.CONTROLLER_DASHBOARD, method = RequestMethod.GET)
    public String showDashboardPage(HttpServletRequest request) {
        //加载左侧导航菜单内容
        List<MenuVo> menuList=menuService.getMenuInfoService();
        request.setAttribute("menuList",menuList);
        return HomeworkConstants.PAGE_DASHBOARD;
    }
}
