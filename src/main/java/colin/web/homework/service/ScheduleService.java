package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.ScheduleDao;
import colin.web.homework.core.pojo.Homework_Schedule_Entity;
import colin.web.homework.core.vo.HomeworkScheduleVo;
import colin.web.homework.tools.StringToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/4.
 */
@Service
public class ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    /**
     * 获取用户的行程
     *
     * @param searchParams
     * @return
     */
    public List<HomeworkScheduleVo> fetchUserSchedule(Map<String, Object> searchParams) {
        List<Homework_Schedule_Entity> scheduleEntityList = scheduleDao.fetchAmongDateSchedule(searchParams);
        return this.transferScheduleEntity(scheduleEntityList);
    }

    /**
     * 获取用户的今日行程
     *
     * @param searchParams
     * @return
     */
    public List<HomeworkScheduleVo> fetchUserTodaySchedule(Map<String, Object> searchParams) {
        List<Homework_Schedule_Entity> scheduleEntityList = scheduleDao.fetchTodaySchedule(searchParams);
        return this.transferScheduleEntity(scheduleEntityList);
    }

    public boolean publishUserSchedule(Map<String, Object> scheduleParams) throws ParseException {
        Homework_Schedule_Entity homework_schedule_entity = new Homework_Schedule_Entity();
        homework_schedule_entity.setSchedule_id(StringToolsUtils.getCommonUUID());
        homework_schedule_entity.setSchedule_user(scheduleParams.get("user_name").toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startDate = sdf.parse(scheduleParams.get("schedule_start").toString());
        Timestamp startTimestamp = new Timestamp(startDate.getTime());
        homework_schedule_entity.setSchedule_start_date(startTimestamp);
        Date endDate =sdf.parse(scheduleParams.get("schedule_end").toString());
        Timestamp endTimestamp = new Timestamp(endDate.getTime());
        homework_schedule_entity.setSchedule_end_date(endTimestamp);
        homework_schedule_entity.setSchedule_content(scheduleParams.get("schedule_title").toString());
        return scheduleDao.addObjInfo(homework_schedule_entity);
    }

    /**
     * 转换对象
     *
     * @param scheduleEntityList
     * @return
     */
    private List<HomeworkScheduleVo> transferScheduleEntity(List<Homework_Schedule_Entity> scheduleEntityList) {
        List<HomeworkScheduleVo> scheduleVoList = new ArrayList<>();
        for (Homework_Schedule_Entity schedule_entity : scheduleEntityList) {
            HomeworkScheduleVo scheduleVo = new HomeworkScheduleVo();
            scheduleVo.setId(schedule_entity.getSchedule_id());
            scheduleVo.setTitle(schedule_entity.getSchedule_content());
            scheduleVo.setStart(schedule_entity.getSchedule_start_date().toString());
            scheduleVo.setEnd(schedule_entity.getSchedule_end_date().toString());
            scheduleVoList.add(scheduleVo);
        }
        return scheduleVoList;
    }
}
