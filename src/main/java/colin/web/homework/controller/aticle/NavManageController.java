package colin.web.homework.controller.aticle;

import colin.web.homework.common.HomeworkConstants;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ASUS on 2015/12/27.
 */
@Controller
@Scope("request")
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class NavManageController {

    @RequestMapping(value = HomeworkConstants.CONTROLLER_NAV_MANAGE_SHOW_PAGE)
    public String showNavManagePage(){
        return HomeworkConstants.PAGE_NAV_MANAGE_VIEW;
    }

    @RequestMapping(value = HomeworkConstants.CONTROLLER_NAV_MANAGE_ADD_ACTION,method = RequestMethod.POST)
    public Object addNavManage(){
        return null;
    }
    @RequestMapping(value = HomeworkConstants.CONTROLLER_NAV_MANAGE_DEL_ACTION,method = RequestMethod.POST)
    public Object delNavManage(){
        return null;
    }
    @RequestMapping(value = HomeworkConstants.CONTROLLER_NAV_MANAGE_UPDATE_ACTION,method = RequestMethod.POST)
    public Object updateNavManage(){
        return null;
    }
    @RequestMapping(value = HomeworkConstants.CONTROLLER_NAV_MANAGE_FETCH_ACTION,method = RequestMethod.POST)
    public Object fetchNavManage(){
        return null;
    }
}
