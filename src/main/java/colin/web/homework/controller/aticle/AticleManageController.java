package colin.web.homework.controller.aticle;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DELL on 2015/8/20.
 */
@Controller
@Scope(value = "request")
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class AticleManageController extends BaseController {
    /**
     * 显示文章的管理界面
     *
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ATICLE_BROWSER_PAGE, method = RequestMethod.GET)
    public String showAticleBrowserPage() {
        return HomeworkConstants.PAGE_ATICLE_BROWSER_VIEW;
    }

    /**
     * 显示文章的添加页面
     *
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ATICLE_ADD_PAGE, method = RequestMethod.GET)
    public String showAticleAddPage() {
        return HomeworkConstants.PAGE_ATICLE_ADD_VIEW;
    }

    /**
     * 添加文章对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ATICLE_ADD_ACTION,method =RequestMethod.POST)
    public boolean addAticleObj(){
        //TODO
        return true;
    }

    /**
     * 根绝id删除对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ATICLE_DELETE_ACTION,method = RequestMethod.POST)
    public boolean delAticleObj(@RequestParam(value = "aticleId")String aticleId){
        //TODO
        return true;
    }

    /**
     * 根绝id编辑对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ATICLE_EDIT_ACTION,method = RequestMethod.POST)
    public boolean editAticleObj(){
        //TODO
        return true;
    }

    /**
     * 分页查询对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ATICLE_SEARCH_ACTION,method = RequestMethod.POST)
    public Object searchAticleObjWithPage(){
        return null;
    }
}
