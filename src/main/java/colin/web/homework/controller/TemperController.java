package colin.web.homework.controller;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.core.pojo.Homework_Temper_Entity;
import colin.web.homework.service.TemperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/4.
 */
@Controller
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER)
public class TemperController extends BaseController {
    @Autowired
    private TemperService temperService;

    /**
     * 显示每日心情页面
     *
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_TEMPLATE_TODAY_TEMPER, method = RequestMethod.GET)
    public String showTemperPage() {
        return HomeworkConstants.PAGE_TEMPLATE_TODAY_TEMPER;
    }

    /**
     * 返回当日的心情
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ACTION_FETCH_CURRENT_TEMPER, method = RequestMethod.GET)
    public Map<String, Object> fetchCurrentTemper(@RequestParam(value = "start", required = true) String start, @RequestParam(value = "end", required = true) String end,@RequestParam(value = "_")String _) {
        Map<String, Object> searchParam = new HashMap<>();
        searchParam.put("start", start);
        searchParam.put("end", end);
        List<Homework_Temper_Entity> resultList = temperService.fetchUserTemper(searchParam);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("temperList",resultMap);
        return resultMap;
    }

    /**
     * 发布当日的心情
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ACTION_PUBLISH_CURRENT_TEMPLER, method = RequestMethod.GET)
    public Map<String, Object> publishCurrentTempler() {
        return null;
    }
}
