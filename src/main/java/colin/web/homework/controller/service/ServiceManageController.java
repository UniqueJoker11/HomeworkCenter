package colin.web.homework.controller.service;

import colin.web.homework.common.HomeworkConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by DELL on 2015/10/19.
 * 服务管理，针对第三方平台开发
 */
@Controller
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class ServiceManageController {

    /**
     * 显示服务总览页
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_SERVICE_OVERVIEW_SHOW, method = RequestMethod.GET)
    public String showServiceOverviewPage() {
        return HomeworkConstants.PAGE_SERVICE_OVERVIEW_VIEW;
    }
}
