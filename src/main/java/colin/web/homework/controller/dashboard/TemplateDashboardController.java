package colin.web.homework.controller.dashboard;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import com.fasterxml.jackson.databind.deser.Deserializers;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by DELL on 2015/8/19.
 */
@Controller
@Scope(value = "request")
@RequestMapping(value = HomeworkConstants.CONTROLLER_TEMPLATTE_PREFIX)
public class TemplateDashboardController extends BaseController {

    /**
     * 显示模板首页
     *
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_TAMPLATE_DASHBOARD, method = RequestMethod.GET)
    public String showDashboardPage() {
        return HomeworkConstants.PAGE_TEMPLATE_DASHBOARD;
    }

}
