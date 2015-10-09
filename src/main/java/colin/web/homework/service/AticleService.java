package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.AticleDao;
import colin.web.homework.core.pojo.Homework_Aticle_Entity;
import colin.web.homework.core.pojo.Homework_Template_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import colin.web.homework.core.vo.HomeworkAticleVo;
import colin.web.homework.tools.DateToolsUtils;
import colin.web.homework.tools.StringToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2015/8/24.
 */
@Service
@Transactional
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

    /**
     * 分页查询文章数据
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public Map<String, Object> findAllAticleInfoByPage(int pageIndex,int pageSize){
        return this.aticleDao.getOrderObjectsByPage(Homework_Aticle_Entity.class,null,"aticle_createtime",pageIndex,pageSize,new DefaultRowmapper<Homework_Aticle_Entity>(Homework_Aticle_Entity.class.getName()),true);
    }

    /**
     * 获取全部的文章
     * @return
     */
    public List<HomeworkAticleVo> findAllAticleInfo(){
        List<Homework_Aticle_Entity> aticle_entities=aticleDao.seletcObjectByMap(Homework_Aticle_Entity.class,null,new DefaultRowmapper<Homework_Aticle_Entity>(Homework_Aticle_Entity.class.getName()));
        return this.transferAticleEntity(aticle_entities);
    }

    /**
     * 转换vo
     * @param aticle_entities
     * @return
     */
    private List<HomeworkAticleVo> transferAticleEntity(List<Homework_Aticle_Entity> aticle_entities){
        List<HomeworkAticleVo> aticleVos=new ArrayList<HomeworkAticleVo>();
        for(Homework_Aticle_Entity aticle_entity:aticle_entities){
            HomeworkAticleVo aticleVo=new HomeworkAticleVo();
            aticleVo.setAticle_author(aticle_entity.getAticle_author());
            aticleVo.setAticle_category(aticle_entity.getAticle_category());
            aticleVo.setAticle_createtime(aticle_entity.getAticle_createtime());
            aticleVo.setAticle_digest(aticle_entity.getAticle_digest());
            aticleVo.setAticle_id(aticle_entity.getAticle_id());
            aticleVo.setAticle_name(aticle_entity.getAticle_name());
            aticleVo.setAticle_read_num(aticle_entity.getAticle_read_num());
            aticleVo.setKey_words(aticle_entity.getKey_words());
            aticleVos.add(aticleVo);
        }
        return aticleVos;
    }

}
