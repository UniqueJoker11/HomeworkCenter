package colin.web.homework.controller;

import colin.web.homework.common.HomeworkConstants;

import colin.web.homework.core.vo.HomeworkMenuVo;
import colin.web.homework.core.vo.HomeworkUserInfo;
import colin.web.homework.service.DashboardService;
import colin.web.homework.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by ASUS on 2015/7/26.
 */
@Controller
@RequestMapping(value = HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class DashboardController extends BaseController {

    @Autowired
    private DashboardService dashboardService;//加载菜单服务

    /**
     * 显示404错误页面
     *
     * @return
     */
    @RequestMapping(value = "/notfound.html", method = RequestMethod.GET)
    public String showNotFoundPage() {
        return "notfound";
    }

    /**
     * 显示未授权页面
     *
     * @return
     */
    @RequestMapping(value = "/unauthorized.html", method = RequestMethod.GET)
    public String showUnauthorizedPage() {
        return "unauthorized";
    }

    @RequestMapping(value = HomeworkConstants.CONTROLLER_DASHBOARD)
    public String showDashboardPage(HttpServletRequest request) {
        //查看用户是否登录
        if (!super.userIsLogin()) {
            return "redirect:" + HomeworkConstants.CONTROLLER_MANAGER_PREFIX + HomeworkConstants.CONTROLLER_SIGNIN;
        } else {
            //加载用户的信息
            HomeworkUserInfo homeworkUserInfo = super.fetchUserInfo();
            //加载左侧导航菜单内容
            List<HomeworkMenuVo> menuList = dashboardService.getUserMenuInfo(homeworkUserInfo.getUser_id());
            //设定用户的信息和菜单信息
            request.setAttribute("menuList", menuList);
            request.setAttribute("userinfo", homeworkUserInfo);
            return HomeworkConstants.PAGE_DASHBOARD;
        }
    }
}
