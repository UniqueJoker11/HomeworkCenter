package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.AticleClassifyDao;
import colin.web.homework.core.pojo.Homework_Aticle_Classify_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import colin.web.homework.tools.DateToolsUtils;
import colin.web.homework.tools.StringToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DELL on 2015/12/25.
 */
@Service
public class AticleClassifyService {

    @Autowired
    private AticleClassifyDao aticleClassifyDao;

    /**
     * 获取所有的文章分类
     *
     * @return
     */
    public List<Homework_Aticle_Classify_Entity> fetchAllAticleClassifyService() {
        return aticleClassifyDao.fetchAll();
    }

    public void addAticleClassify(String classifyName, String currentUser) {
        Homework_Aticle_Classify_Entity aticle_classify_entity = new Homework_Aticle_Classify_Entity();
        aticle_classify_entity.setClassify_id(StringToolsUtils.getCommonUUID());
        aticle_classify_entity.setClassify_name(classifyName);
        aticle_classify_entity.setClassify_createtime(DateToolsUtils.getTodayCurrentTime());
        aticle_classify_entity.setClassify_createuser(currentUser);
        aticleClassifyDao.addObjInfo(aticle_classify_entity);
    }

    public void delAticleClassify(String classifyId) {
        aticleClassifyDao.deleteObjectById(Homework_Aticle_Classify_Entity.class, classifyId);
    }

    public void updateAticleClassify(String classifyId, String classifyName){
        Homework_Aticle_Classify_Entity classify_entity=aticleClassifyDao.selectObjectById(Homework_Aticle_Classify_Entity.class,classifyId,new DefaultRowmapper<Homework_Aticle_Classify_Entity>(Homework_Aticle_Classify_Entity.class.getName()));
        classify_entity.setClassify_name(classifyName);
        aticleClassifyDao.updateObjInfo(classify_entity);
    }
}
