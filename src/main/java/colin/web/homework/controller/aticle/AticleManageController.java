package colin.web.homework.controller.aticle;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import colin.web.homework.service.AticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 2015/8/20.
 */
@Controller
@Scope(value = "request")
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class AticleManageController extends BaseController {

    @Autowired
    private AticleService aticleService;
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
     * 转载文章页面
     *
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ATICLE_REPRINT_PAGE, method = RequestMethod.GET)
    public String showReprintAticlePage() {
        return HomeworkConstants.PAGE_REPRINT_ATICLE_VIEW;
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
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ATICLE_ADD_ACTION, method = RequestMethod.POST)
    public boolean addAticleObj(@RequestParam(value = "aticleTitle") String aticleTitle, @RequestParam(value = "aticleDigest") String aticleDigest, @RequestParam(value = "aticleCategory") String aticleCategory, @RequestParam(value = "aticleTips") String aticleTips, @RequestParam(value = "aticleContent") String aticleContent) {
        Map<String,Object> aticleMap=new HashMap<>();
        aticleMap.put("aticleTitle",aticleTitle);
        aticleMap.put("aticleDigest",aticleDigest);
        aticleMap.put("aticleCategory",aticleCategory);
        aticleMap.put("aticleTips",aticleTips);
        aticleMap.put("aticleContent",aticleContent);
        aticleMap.put("aticleUser",super.fetchUserInfo().getUser_name());
        return this.aticleService.addAticleInfo(aticleMap);
    }

    /**
     * 根绝id删除对象
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ATICLE_DELETE_ACTION, method = RequestMethod.POST)
    public boolean delAticleObj(@RequestParam(value = "aticleId") String aticleId) {
        //TODO
        return true;
    }

    /**
     * 根绝id编辑对象
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ATICLE_EDIT_ACTION, method = RequestMethod.POST)
    public boolean editAticleObj() {
        //TODO
        return true;
    }

    /**
     * 分页查询对象
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ATICLE_SEARCH_ACTION, method = RequestMethod.POST)
    public Object searchAticleObjWithPage() {
        return null;
    }
}
