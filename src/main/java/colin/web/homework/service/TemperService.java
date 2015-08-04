package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.TemperDao;
import colin.web.homework.core.pojo.Homework_Temper_Entity;
import colin.web.homework.core.pojo.Homework_Template_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/4.
 */
@Service
public class TemperService {

    @Autowired
    private TemperDao temperDao;
    /**
     * 获取用户的心情
     * @param searchParams
     * @return
     */
    public List<Homework_Temper_Entity> fetchUserTemper(Map<String,Object> searchParams){
        return  temperDao.getAmongObjectWithOrder(Homework_Temper_Entity.class,searchParams,null,"temper_date",new DefaultRowmapper<Homework_Temper_Entity>(Homework_Temper_Entity.class.getName()),false);
    }
}
