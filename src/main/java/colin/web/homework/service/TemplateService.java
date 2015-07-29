package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.TemplateDao;
import colin.web.homework.core.pojo.Homework_Template_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import colin.web.homework.core.vo.HomeworkTemplateVo;
import colin.web.homework.tools.DateToolsUtils;
import colin.web.homework.tools.StringToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * @return
     */
    public List<HomeworkTemplateVo> initTemplateInfoService(){
        List<Homework_Template_Entity> allTemplateList=this.templateDao.seletcObjectByMap(Homework_Template_Entity.class,null,new DefaultRowmapper<Homework_Template_Entity>(Homework_Template_Entity.class.getName()));
        return this.transferPoToVo(allTemplateList);
    }
    /**
     * 分页查询当前的上传模板内容，
     * @param params
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Map<String,Object> fetchTemplateWithPage(Map<String,Object> params,int currentPage,int pageSize){
       return templateDao.getOrderObjectsByPage (Homework_Template_Entity.class,params,"template_create_time",currentPage,pageSize,new DefaultRowmapper<HomeworkTemplateVo>(HomeworkTemplateVo.class.getName()),false);
    }
    /**
     * 保存模板对象
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
     * 转换数据对象
     * @param sourceList
     * @return
     */
    private List<HomeworkTemplateVo> transferPoToVo(List<Homework_Template_Entity> sourceList){
        List<HomeworkTemplateVo> templateVoList=new ArrayList<HomeworkTemplateVo>();
        for(Homework_Template_Entity template_entity:sourceList){
            HomeworkTemplateVo templateVo=new HomeworkTemplateVo();
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
}
