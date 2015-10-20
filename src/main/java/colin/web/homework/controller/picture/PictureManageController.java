package colin.web.homework.controller.picture;

import colin.web.homework.common.HomeworkConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by DELL on 2015/10/20.
 */
@Controller
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER_PREFIX)
public class PictureManageController {

    /**
     * 显示图片收藏页面
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_COLLECT_PICTURE, method = RequestMethod.GET)
    public String showPictureCollectPage() {
        return HomeworkConstants.PAGE_COLLECT_PICTURE_VIEW;
    }
}
