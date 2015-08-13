package colin.web.homework.core.dao.decoratedao;

import colin.web.homework.core.pojo.Homework_Schedule_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/4.
 */
@Repository
public class ScheduleDao extends DecorateCommnDao{
    /**
     * 返回牧歌区间段的所有安排
     * @param searchParams
     * @return
     */
    public List<Homework_Schedule_Entity> fetchAmongDateSchedule(Map<String,Object> searchParams){
        StringBuilder searchSql=new StringBuilder("select * from homework_schedule");
        if(searchParams!=null&&!searchParams.isEmpty()){
            Object[] searchObjs=searchParams.keySet().toArray();
            searchSql.append(" where ").append("schedule_start_date>=:").append(searchObjs[0]).append(" or ").append("schedule_end_date<=:").append(searchObjs[1]);
        }
      return super.getJdbcTemplate().query(searchSql.toString(),searchParams,new DefaultRowmapper<Homework_Schedule_Entity>(Homework_Schedule_Entity.class.getName()));
    }
    /**
     * 返回牧歌区间段的所有安排
     * @param searchParams
     * @return
     */
    public List<Homework_Schedule_Entity> fetchTodaySchedule(Map<String,Object> searchParams){
        StringBuilder searchSql=new StringBuilder("select * from homework_schedule");
        if(searchParams!=null&&!searchParams.isEmpty()){
            Object[] searchObjs=searchParams.keySet().toArray();
            searchSql.append(" where ").append("schedule_start_date>=:").append(searchObjs[0]).append(" and ").append("schedule_end_date<=:").append(searchObjs[1]);
        }
        return super.getJdbcTemplate().query(searchSql.toString(),searchParams,new DefaultRowmapper<Homework_Schedule_Entity>(Homework_Schedule_Entity.class.getName()));
    }
}
