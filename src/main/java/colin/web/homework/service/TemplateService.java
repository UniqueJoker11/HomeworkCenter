package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.TemplateDao;
import colin.web.homework.core.pojo.Homework_Template_Entity;
import colin.web.homework.core.pojo.Homework_User_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import colin.web.homework.core.vo.HomeworkTemplateVo;
import colin.web.homework.tools.DateToolsUtils;
import colin.web.homework.tools.StringToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by DELL on 2015/7/28.
 */
@Service
public class TemplateService {
    @Autowired
    private TemplateDao templateDao;
    @Autowired
    private Homework_Template_Entity homework_template_entity;

    /**
     * 一次性获取全部的模板对象
     *
     * @return
     */
    public List<HomeworkTemplateVo> initTemplateInfoService() {
        List<Homework_Template_Entity> allTemplateList = this.templateDao.seletcObjectByMap(Homework_Template_Entity.class, null, new DefaultRowmapper<Homework_Template_Entity>(Homework_Template_Entity.class.getName()));
        return this.transferPoToVo(allTemplateList);
    }

    /**
     * 返回所有的模板标签
     *
     * @return
     */
    public Set<String> fetchAllTemplateTips() {
        return this.templateDao.fetchAllTemplateTips();
    }

    /**
     * 返回最新发布的8条模板数据
     *
     * @return
     */
    public List<HomeworkTemplateVo> fetchRecentlyTemplateList() {
        List<Homework_Template_Entity> template_entityList = this.templateDao.fetchRecentlyTemplateObj();
        return this.transferPoToVo(template_entityList);
    }

    /**
     * 分页查询当前的上传模板内容，
     *
     * @param params
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Map<String, Object> fetchTemplateWithPage(Map<String, Object> params, int currentPage, int pageSize) {
        return templateDao.getOrderObjectsByPage(Homework_Template_Entity.class, params, "template_create_time", currentPage, pageSize, new DefaultRowmapper<HomeworkTemplateVo>(HomeworkTemplateVo.class.getName()), false);
    }

    /**
     * 添加模板对象
     *
     * @param tamplateSnapshotUrl
     * @param templateResource
     * @param tamplateName
     * @param templateTips
     * @param tamplateDescribe
     * @return
     */
    public boolean addTemplateService(String tamplateSnapshotUrl, String templateResource, String tamplateName, String templateTips, String tamplateDescribe, String accessUrl, String uploadUser) {
        homework_template_entity.setTemplate_id(StringToolsUtils.getCommonUUID());
        homework_template_entity.setTemplate_access_url(accessUrl);
        homework_template_entity.setTemplate_create_time(DateToolsUtils.getTodayCurrentTime());
        homework_template_entity.setTemplate_describe(tamplateDescribe);
        homework_template_entity.setTemplate_resource_url(templateResource);
        homework_template_entity.setTemplate_name(tamplateName);
        homework_template_entity.setTemplate_snapshot(tamplateSnapshotUrl);
        homework_template_entity.setTemplate_tip(templateTips);
        homework_template_entity.setTemplaye_browser_num(0);
        homework_template_entity.setTemplate_uplodad_user(uploadUser);
        return templateDao.addObjInfo(homework_template_entity);
    }

    /**
     * 根据id来进行搜索模板
     *
     * @param template_id
     * @return
     */
    public HomeworkTemplateVo searchTemplateService(String template_id) {
        Homework_Template_Entity template_entity = templateDao.selectObjectById(Homework_Template_Entity.class, template_id, new DefaultRowmapper<Homework_Template_Entity>(Homework_Template_Entity.class.getName()));
        return this.transferPoToVo(template_entity);
    }

    /**
     * 更新模板对象
     *
     * @param params
     * @return
     */
    public boolean updateTemplateService(Map<String, Object> params) {
        Homework_Template_Entity template_entity = new Homework_Template_Entity();
        template_entity.setTemplate_id(params.get("template_id").toString());
        if (params.containsKey("template_tip")) {
            template_entity.setTemplate_tip(params.get("template_tip").toString());
        }
        if (params.containsKey("template_name")) {
            template_entity.setTemplate_name(params.get("template_name").toString());
        }
        if (params.containsKey("template_describe")) {
            template_entity.setTemplate_describe(params.get("template_describe").toString());
        }
        boolean result = this.templateDao.updateObjInfo(template_entity);
        return result;
    }

    /**
     * 根据id删除模板
     *
     * @param template_id
     * @return
     */
    public boolean deleteTemplateService(String template_id) {
        boolean result = this.templateDao.deleteObjectById(Homework_Template_Entity.class, template_id);
        return result;
    }

    /**
     * 转换数据对象
     *
     * @param sourceList
     * @return
     */
    private List<HomeworkTemplateVo> transferPoToVo(List<Homework_Template_Entity> sourceList) {
        List<HomeworkTemplateVo> templateVoList = new ArrayList<HomeworkTemplateVo>();
        for (Homework_Template_Entity template_entity : sourceList) {
            HomeworkTemplateVo templateVo = new HomeworkTemplateVo();
            templateVo.setTemplate_id(template_entity.getTemplate_id());
            templateVo.setTemplate_tip(template_entity.getTemplate_tip());
            templateVo.setTemplate_snapshot(template_entity.getTemplate_snapshot().split(",")[0]);
            templateVo.setTemplate_access_url(template_entity.getTemplate_access_url());
            templateVo.setTemplate_describe(template_entity.getTemplate_describe());
            templateVo.setTemplate_name(template_entity.getTemplate_name());
            templateVo.setTemplate_resource_url(template_entity.getTemplate_resource_url());
            templateVo.setTemplaye_browser_num(template_entity.getTemplaye_browser_num());
            templateVoList.add(templateVo);
        }
        return templateVoList;
    }

    /**
     * 转换根据id查询的对象
     *
     * @param template_entity
     * @return
     */
    private HomeworkTemplateVo transferPoToVo(Homework_Template_Entity template_entity) {
        HomeworkTemplateVo homeworkTemplateVo = new HomeworkTemplateVo();
        homeworkTemplateVo.setTemplate_id(template_entity.getTemplate_id());
        homeworkTemplateVo.setTemplate_describe(template_entity.getTemplate_describe());
        homeworkTemplateVo.setTemplate_snapshot(template_entity.getTemplate_snapshot());
        homeworkTemplateVo.setTemplate_tip(template_entity.getTemplate_tip());
        homeworkTemplateVo.setTemplate_name(template_entity.getTemplate_name());
        return homeworkTemplateVo;
    }
}
