package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.AticleDao;
import colin.web.homework.core.pojo.Homework_Aticle_Entity;
import colin.web.homework.tools.DateToolsUtils;
import colin.web.homework.tools.StringToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by DELL on 2015/8/24.
 */
@Service
public class AticleService {

    @Autowired
    private AticleDao aticleDao;

    /**
     * 添加文章内容
     *
     * @param aticleParams
     * @return
     */
    public boolean addAticleInfo(Map<String, Object> aticleParams) {
        Homework_Aticle_Entity aticle_entity = new Homework_Aticle_Entity();
        aticle_entity.setAticle_id(StringToolsUtils.getCommonUUID());
        aticle_entity.setAticle_author(aticleParams.get("aticleUser").toString());
        aticle_entity.setAticle_category(aticleParams.get("aticleCategory").toString());
        aticle_entity.setAticle_content(aticleParams.get("aticleContent").toString());
        aticle_entity.setAticle_createtime(DateToolsUtils.getTodayCurrentTime());
        aticle_entity.setAticle_digest(aticleParams.get("aticleDigest").toString());
        aticle_entity.setAticle_name(aticleParams.get("aticleTitle").toString());
        aticle_entity.setKey_words(aticleParams.get("aticleTips").toString());
        return this.aticleDao.addObjInfo(aticle_entity);
    }

    /**
     * 根据id删除文章内容
     *
     * @param aticleId
     * @return
     */
    public boolean deleteAticleInfo(String aticleId) {
        return this.aticleDao.deleteObjectById(Homework_Aticle_Entity.class, aticleId);
    }

    /**
     * 更新文章内容
     *
     * @param aticleParams
     * @return
     */
    public boolean updateAticleInfo(Map<String, Object> aticleParams) {
        Homework_Aticle_Entity aticle_entity = new Homework_Aticle_Entity();
        return this.aticleDao.addObjInfo(aticle_entity);
    }

}
