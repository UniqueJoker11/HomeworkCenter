package colin.web.homework.controller;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.core.vo.HomeworkScheduleVo;
import colin.web.homework.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/4.
 */
@Controller
@RequestMapping(HomeworkConstants.CONTROLLER_MANAGER)
public class ScheduleController extends BaseController {
    @Autowired
    private ScheduleService scheduleService;

    /**
     * 显示每日心情页面
     *
     * @return
     */
    @RequestMapping(value = HomeworkConstants.CONTROLLER_TEMPLATE_TODAY_TEMPER, method = RequestMethod.GET)
    public String showTemperPage() {
        return HomeworkConstants.PAGE_TEMPLATE_TODAY_SCHEDULE;
    }

    /**
     * 返回查询的日程
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ACTION_FETCH_CURRENT_SCHEDULE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object fetchSchedule(@RequestParam(value = "start", required = true) String start, @RequestParam(value = "end", required = true) String end, @RequestParam(value = "_") String _) {
        Map<String, Object> searchParam = new HashMap<>();
        searchParam.put("start", start);
        searchParam.put("end", end);
        List<HomeworkScheduleVo> resultList = scheduleService.fetchUserSchedule(searchParam);
        return resultList;
    }

    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ACTION_FETCH_TODAY_SCHEDULE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object fetchTodaySchedule(@RequestParam(value = "start", required = true) String start, @RequestParam(value = "end", required = true) String end) {
        Map<String, Object> searchParam = new HashMap<>();
        searchParam.put("start", start);
        searchParam.put("end", end);
        List<HomeworkScheduleVo> resultList = scheduleService.fetchUserTodaySchedule(searchParam);
        return resultList;
    }

    /**
     * 发布当日的心情
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = HomeworkConstants.CONTROLLER_ACTION_PUBLISH_CURRENT_SCHEDULE, method = RequestMethod.POST)
    public Map<String, Object> publishCurrentTempler(@RequestParam(value = "schedule_start", required = true) String schedule_start, @RequestParam(value = "schedule_end", required = true) String schedule_end, @RequestParam(value = "schedule_title", required = true) String schedule_title) {
        Map<String, Object> scheduleParams = new HashMap<>();
        scheduleParams.put("schedule_start", schedule_start);
        scheduleParams.put("schedule_end", schedule_start);
        scheduleParams.put("user_name", super.fetchUserInfo().getUser_name());
        scheduleParams.put("schedule_title", schedule_title);
        boolean result = false;
        try {
            result = scheduleService.publishUserSchedule(scheduleParams);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isSuccess", result);
        return resultMap;
    }
}
