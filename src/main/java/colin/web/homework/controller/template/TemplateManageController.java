package colin.web.homework.controller.template;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 模板管理一览
 * Created by DELL on 2015/7/27.
 */
@Controller
@RequestMapping(value = HomeworkConstants.CONTROLLER_MANAGER)
public class TemplateManageController extends BaseController {

    /**
     * 显示模板管理一览页面
     *
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_TEMPLATE_MANAGE, method = RequestMethod.GET)
    public String showTemplateManagePage() {
        return HomeworkConstants.PAGE_TEMPLATE_MANAGE;
    }
}
