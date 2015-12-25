package colin.web.homework.controller.aticle;

import colin.web.homework.common.CommonReturnResult;
import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import colin.web.homework.service.AticleClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DELL on 2015/12/24.
 * 文章分类管理页面
 */
@Controller
@Scope("request")
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class AticleClassifyManageController extends BaseController {
    @Autowired
    public AticleClassifyService aticleClassifyService;

    /**
     * 显示文章分类页面
     *
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ATICLE_CATEGORY_SHOW_PAGE, method = RequestMethod.GET)
    public String showAticleClassifyPage(HttpServletRequest request) {
        request.setAttribute("classifyList", aticleClassifyService.fetchAllAticleClassifyService());
        return HomeworkConstants.PAGE_ATICLE_CATEGORY_VIEW;
    }

    @ResponseBody
    @RequestMapping(value =HomeworkConstants.CONTROLLER_ATICLE_CATEGORY_ALL_ACTION )
    public Object fetchAllAticleClassify(){
        return new CommonReturnResult(true,aticleClassifyService.fetchAllAticleClassifyService());
    }
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ATICLE_CATEGORY_ADD_ACTION, method = RequestMethod.POST)
    public Object addAticleClassify(@RequestParam String classifyName) {
        aticleClassifyService.addAticleClassify(classifyName, super.fetchUserInfo().getUser_name());
        return new CommonReturnResult(true);
    }

    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ATICLE_CATEGORY_DEL_ACTION, method = RequestMethod.POST)
    public Object delAticleClassify(@RequestParam String classifyId) {
        aticleClassifyService.delAticleClassify(classifyId);
        return new CommonReturnResult(true);
    }

    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ATICLE_CATEGORY_UPDATE_ACTION, method = RequestMethod.POST)
    public Object updateAticleClassify(@RequestParam String classifyId, @RequestParam String classifyName) {
        aticleClassifyService.updateAticleClassify(classifyId, classifyName);
        return new CommonReturnResult(true);
    }

}
