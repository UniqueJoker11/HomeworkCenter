package colin.web.homework.controller.aticle;

import colin.web.homework.common.CommonReturnResult;
import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import colin.web.homework.core.vo.HomeworkNavManageVo;
import colin.web.homework.service.NavClassifyService;
import colin.web.homework.service.NavManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by ASUS on 2015/12/27.
 */
@Controller
@Scope("request")
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class NavManageController extends BaseController{

    @Autowired
    private NavManageService manageService;

    @Autowired
    private NavClassifyService navClassifyService;

    @RequestMapping(value = HomeworkConstants.CONTROLLER_NAV_MANAGE_SHOW_PAGE)
    public String showNavManagePage() {
        return HomeworkConstants.PAGE_NAV_MANAGE_VIEW;
    }

    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_NAV_MANAGE_ADD_ACTION, method = RequestMethod.POST)
    public Object addNavManage(@RequestParam String navName, @RequestParam(required = false, defaultValue = "root") String navParentId,@RequestParam(required = false,defaultValue = "#") String navUrl) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nav_name", navName);
        params.put("nav_parent_id", navParentId);
        params.put("nav_user",super.fetchUserInfo().getUser_name());
        params.put("nav_url",navUrl);
        manageService.addNavManageEntity(params);
        return new CommonReturnResult(true);
    }

    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_NAV_MANAGE_DEL_ACTION, method = RequestMethod.POST)
    public Object delNavManage(@RequestParam String navId) {
        manageService.delNavManageEntity(navId);
        return new CommonReturnResult(true);
    }

    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_NAV_MANAGE_UPDATE_ACTION, method = RequestMethod.POST)
    public Object updateNavManage(@RequestParam String idVal,@RequestParam String navName, @RequestParam String navParentId,@RequestParam(required = false,defaultValue = "#") String navUrl) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nav_id", idVal);
        params.put("nav_name", navName);
        params.put("nav_parent_id", navParentId);
        params.put("nav_url",navUrl);
        manageService.updateMangeEntity(params);
        return new CommonReturnResult(true);
    }

    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_NAV_MANAGE_FETCH_ACTION)
    public Object fetchNavManage() {
        List<HomeworkNavManageVo> navManageVoList = manageService.fetchAllNavManage();
        return navManageVoList == null ? new CommonReturnResult(false) : new CommonReturnResult(true, navManageVoList);
    }

    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_NAV_MANAGE_ROOT_FETCH_ACTION)
    public Object fetchAllRootNavManage() {
        return new CommonReturnResult(true, manageService.fetchAppRootNavEntity());
    }

    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_NAV_MANAGE_CLASSIFY_FETCH_ACTION)
    public Object fetchNavClassifyInfo(@RequestParam String navId){
        return new CommonReturnResult(true,navClassifyService.fetchNavClassifyTree(navId));
    }
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_NAV_MANAGE_CLASSIFY_UPDATE_ACTION)
    public Object updateNavClassifyInfo(@RequestParam String navId,@RequestParam(name = "classifyIds[]") String[] classifyIds){
        //先删除所有的原来的信息
        navClassifyService.delNavClassify(navId);
        navClassifyService.addNavClassifies(navId,classifyIds);
        return new CommonReturnResult(true);
    }
}
