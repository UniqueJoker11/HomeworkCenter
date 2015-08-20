package colin.web.homework.controller.system;

import colin.web.homework.common.HomeworkConstants;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by DELL on 2015/8/20.
 */
@Controller
@Scope(value = "request")
@RequestMapping(value = HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class PersonManageController {

    @RequestMapping(value = HomeworkConstants.CONTROLLER_PERSON_MANAGE_PAGE,method = RequestMethod.GET)
    public String showPersonManagePage(){
        return HomeworkConstants.PAGE_PERSON_MANAGE_VIEW;
    }
}
