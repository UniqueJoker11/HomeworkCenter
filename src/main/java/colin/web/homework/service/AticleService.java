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
import java.util.HashMap;
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
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public Map<String, Object> findAllAticleInfoByPage(int pageIndex, int pageSize) {
        return this.aticleDao.getOrderObjectsByPage(Homework_Aticle_Entity.class, null, "aticle_createtime", pageIndex, pageSize, new DefaultRowmapper<Homework_Aticle_Entity>(Homework_Aticle_Entity.class.getName()), true);
    }

    /**
     * 获取全部的文章
     *
     * @return
     */
    public List<HomeworkAticleVo> findAllAticleInfo() {
        List<Homework_Aticle_Entity> aticle_entities = aticleDao.seletcObjectByMap(Homework_Aticle_Entity.class, null, new DefaultRowmapper<Homework_Aticle_Entity>(Homework_Aticle_Entity.class.getName()));
        return this.transferAticleEntity(aticle_entities);
    }

    public Map<String, Object> findAticlesByNavId(String navId, int startIndex, int pageSize) {
        String searchSql = "select * from homework_aticle where aticle_category in (select classify_id from homework_nav_classify where nav_id=:navId) order by aticle_createtime DESC LIMIT :startIndex,:pageSize";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("navId", navId);
        params.put("startIndex", (startIndex-1)*pageSize);
        params.put("pageSize", pageSize);
        String searchAllSql = "select * from homework_aticle where aticle_category in (select classify_id from homework_nav_classify where nav_id=:navId) order by aticle_createtime";
        List<Homework_Aticle_Entity> resultList = this.aticleDao.getJdbcTemplate().query(searchSql, params, new DefaultRowmapper<Homework_Aticle_Entity>(Homework_Aticle_Entity.class.getName()));
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (resultList != null && !resultList.isEmpty()) {
            resultMap.put("currentData", resultList);
            List<Homework_Aticle_Entity> aticleAllList = this.aticleDao.getJdbcTemplate().query(searchAllSql, params, new DefaultRowmapper<Homework_Aticle_Entity>(Homework_Aticle_Entity.class.getName()));
            resultMap.put("totalCount", aticleAllList.size());
            resultMap.put("currentPage", startIndex);
            resultMap.put("totalPage", aticleAllList.size() % pageSize == 0 ? aticleAllList.size() / pageSize : (aticleAllList.size() / pageSize + 1));

        }
        return resultMap;
    }

    /**
     * 查询文章详情
     *
     * @param aticleId
     * @return
     */
    public Homework_Aticle_Entity findAticleDetailInfo(String aticleId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("aticle_id", aticleId);
        List<Homework_Aticle_Entity> aticle_entities = this.aticleDao.seletcObjectByMap(Homework_Aticle_Entity.class, params, new DefaultRowmapper<Homework_Aticle_Entity>(Homework_Aticle_Entity.class.getName()));
        if (aticle_entities != null && !aticle_entities.isEmpty()) {
            return aticle_entities.get(0);
        } else {
            return null;
        }
    }

    /**
     * 查询推荐的文章
     *
     * @param tips
     * @param aticleId
     * @return
     */
    public List<HomeworkAticleVo> findRecommondAticleInfo(String tips, String aticleId) {
        List<Homework_Aticle_Entity> aticleEntityList = this.aticleDao.findRecommondAticleList(tips.split("，"), aticleId);
        if (aticleEntityList.size() > 6) {
            for (int j = 5; j < aticleEntityList.size(); j++) {
                aticleEntityList.remove(j);
            }
        }
        return this.transferAticleEntity(aticleEntityList);
    }

    /**
     * 查询上一篇或下一篇文章
     *
     * @param createTime
     * @param nextOrPrev
     */
    public HomeworkAticleVo findUponAticlesInfo(String createTime, int nextOrPrev) {
        List<HomeworkAticleVo> aticleVoList = this.transferAticleEntity(this.aticleDao.findUponAticles(createTime, nextOrPrev));
        if (aticleVoList != null && !aticleVoList.isEmpty()) {
            return aticleVoList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 转换vo
     *
     * @param aticle_entities
     * @return
     */
    private List<HomeworkAticleVo> transferAticleEntity(List<Homework_Aticle_Entity> aticle_entities) {
        if (aticle_entities != null && !aticle_entities.isEmpty()) {
            List<HomeworkAticleVo> aticleVos = new ArrayList<HomeworkAticleVo>();
            for (Homework_Aticle_Entity aticle_entity : aticle_entities) {
                HomeworkAticleVo aticleVo = new HomeworkAticleVo();
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
        } else {
            return null;
        }

    }

}
