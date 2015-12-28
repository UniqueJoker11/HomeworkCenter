package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.NavClassifyDao;
import colin.web.homework.core.pojo.Homework_Nav_Classify_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import colin.web.homework.tools.StringToolsUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by DELL on 2015/12/28.
 */
@Service
@Transactional
public class NavClassifyService {

    @Autowired
    private NavClassifyDao classifyDao;

    /**
     * 添加单独的对象
     *
     * @param params
     */
    public void addNavClassify(Map<String, Object> params) {
        Homework_Nav_Classify_Entity navManageEntity = this.copyNavClassifyEntity(params);
        navManageEntity.setNav_classify_id(StringToolsUtils.getCommonUUID());
        classifyDao.addObjInfo(navManageEntity);
    }

    public void addNavClassifies(String nav_id, String[] classifyIds) {
        String batchUpdateSql = "insert into homework_nav_classify values(:navClassifyId,:navId,:classifyId)";
        Map<String, Object>[] params = new Map[classifyIds.length];
        for (int i = 0; i < classifyIds.length; i++) {
            params[i] = new HashMap<String, Object>();
            params[i].put("navClassifyId", StringToolsUtils.getCommonUUID());
            params[i].put("navId", nav_id);
            params[i].put("classifyId", classifyIds[i]);
        }
        classifyDao.getJdbcTemplate().batchUpdate(batchUpdateSql, params);

    }

    public void delNavClassify(String navId) {
        String delNavClassify = "delete from homework_mav_classify where nav_id=:navId";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("navId", navId);
        classifyDao.getJdbcTemplate().update(delNavClassify, params);
    }

    public List<Homework_Nav_Classify_Entity> fetchAllNavByNavId(String navId) {
        String delNavClassify = "select * from homework_mav_classify where nav_id=:navId";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("navId", navId);
        return classifyDao.getJdbcTemplate().query(delNavClassify, params, new DefaultRowmapper<Homework_Nav_Classify_Entity>(Homework_Nav_Classify_Entity.class.getName()));
    }

    /**
     * 生成nav对象
     *
     * @param params
     * @return
     */
    public Homework_Nav_Classify_Entity copyNavClassifyEntity(Map<String, Object> params) {
        Homework_Nav_Classify_Entity navManageEntity = new Homework_Nav_Classify_Entity();
        try {
            BeanUtils.copyProperties(navManageEntity, params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return navManageEntity;
    }
}
