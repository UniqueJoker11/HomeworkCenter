package colin.web.homework.controller.template;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import colin.web.homework.core.vo.HomeworkTemplateVo;
import colin.web.homework.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 模板管理一览
 * Created by DELL on 2015/7/27.
 */
@Controller
@RequestMapping(value = HomeworkConstants.CONTROLLER_MANAGER)
public class TemplateManageController extends BaseController {

    @Autowired
    private TemplateService templateService;
    /**
     * 显示模板管理一览页面
     *
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_TEMPLATE_MANAGE, method = RequestMethod.GET)
    public String showTemplateManagePage(HttpServletRequest request) {
        List<HomeworkTemplateVo> menuList=this.templateService.initTemplateInfoService();
        request.setAttribute("templateList",menuList);
        return HomeworkConstants.PAGE_TEMPLATE_MANAGE;
    }

    /**
     * ajax获取模板信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public Object showTemplateInfoByPage(HttpServletRequest request){
        //初始化模板列表内容
        int currentPage=0;
        if(request.getParameter("currentPage")!=null){
            currentPage=Integer.valueOf(request.getParameter("currentPage").toString());
        }
        int pageSize=10;
        if(request.getParameter("pageSize")!=null){
            pageSize=Integer.valueOf(request.getParameter("pageSize").toString());
        }
        Map<String,Object> templateMap=templateService.fetchTemplateWithPage(null,currentPage,pageSize);
        return templateMap;
    }
}