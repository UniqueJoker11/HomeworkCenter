package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.NavManageDao;
import colin.web.homework.core.pojo.Homework_Nav_Manage_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import colin.web.homework.core.vo.HomeworkNavManageVo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2015/12/27.
 */
@Service
@Transactional
public class NavManageService {

    @Autowired
    private NavManageDao navManageDao;

    public List<HomeworkNavManageVo> fetchAllNavManage() {
        List<Homework_Nav_Manage_Entity> navManageEntities = navManageDao.seletcObjectByMap(Homework_Nav_Manage_Entity.class, null, new DefaultRowmapper<Homework_Nav_Manage_Entity>(Homework_Nav_Manage_Entity.class.getName()));
        if (navManageEntities == null || navManageEntities.isEmpty()) {
            return null;
        } else {
            List<HomeworkNavManageVo> rootNavManageEntities = new ArrayList<HomeworkNavManageVo>(navManageEntities.size());
            List<HomeworkNavManageVo> subNavManageEntities = new ArrayList<HomeworkNavManageVo>(navManageEntities.size());
            for (Homework_Nav_Manage_Entity nav_manage_entity : navManageEntities) {
                HomeworkNavManageVo navManageVo = new HomeworkNavManageVo();
                try {
                    BeanUtils.copyProperties(navManageVo, nav_manage_entity);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (nav_manage_entity.getNav_parent_id().equals("root")) {
                    rootNavManageEntities.add(navManageVo);
                } else {
                    subNavManageEntities.add(navManageVo);
                }
            }
            for (HomeworkNavManageVo rootEntity : rootNavManageEntities) {
                rootEntity.setChildNavManageVoList(assembleNavManage(rootEntity, subNavManageEntities));
            }
            return rootNavManageEntities;
        }
    }

    /**
     * 递归元素
     *
     * @param rootNavManageEntity
     * @param subNavManageEntities
     * @return
     */
    public List<HomeworkNavManageVo> assembleNavManage(HomeworkNavManageVo rootNavManageEntity, List<HomeworkNavManageVo> subNavManageEntities) {
        List<HomeworkNavManageVo> childNavManageList = new ArrayList<HomeworkNavManageVo>();
        for (HomeworkNavManageVo subNavManageNav : subNavManageEntities) {
            if (rootNavManageEntity.getNav_id().equals(subNavManageNav.getNav_parent_id())) {
                subNavManageNav.setChildNavManageVoList(assembleNavManage(subNavManageNav, subNavManageEntities));
                childNavManageList.add(subNavManageNav);
            }
        }
        return childNavManageList;
    }

    public void addNavManageEntity(Map<String, Object> params) {
        navManageDao.addObjInfo(copyNavManageEntity(params));
    }

    public void delNavManageEntity(String idVal) {
        navManageDao.deleteObjectById(Homework_Nav_Manage_Entity.class, idVal);
    }

    public void updateMangeEntity(Map<String, Object> params) {
        navManageDao.updateObjInfo(copyNavManageEntity(params));
    }

    /**
     * 生成nav对象
     * @param params
     * @return
     */
    public Homework_Nav_Manage_Entity copyNavManageEntity(Map<String, Object> params) {
        Homework_Nav_Manage_Entity navManageEntity = new Homework_Nav_Manage_Entity();
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
