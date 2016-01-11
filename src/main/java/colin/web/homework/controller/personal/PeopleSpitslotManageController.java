package colin.web.homework.controller.personal;

import colin.web.homework.common.HomeworkConstants;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DELL on 2016/1/11.
 */
@Controller
@Scope("request")
@RequestMapping(value = HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class PeopleSpitslotManageController {


    /**
     * 显示吐槽页面
     * @param request
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_SERVICE_PEOPLE_SPITSLOT, method = RequestMethod.GET)
    public String showPeopleSpitslotPage(HttpServletRequest request) {
        return HomeworkConstants.PAGE_TEMPLAYE_PEOPLE_SPITSLOT;
    }
}
