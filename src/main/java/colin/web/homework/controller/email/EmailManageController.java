package colin.web.homework.controller.email;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.controller.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DELL on 2015/8/20.
 */
@Controller
@Scope(value = "request")
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class EmailManageController extends BaseController{
}
