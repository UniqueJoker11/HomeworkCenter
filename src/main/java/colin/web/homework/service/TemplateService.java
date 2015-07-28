package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.TemplateDao;
import colin.web.homework.core.pojo.Homework_Template_Entity;
import colin.web.homework.tools.DateToolsUtils;
import colin.web.homework.tools.StringToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 保存模板对象
     * @param tamplateSnapshotUrl
     * @param templateResource
     * @param tamplateName
     * @param templateTips
     * @param tamplateDescribe
     * @return
     */
    public boolean addTemplateService(String tamplateSnapshotUrl,String templateResource,String tamplateName,String templateTips,String tamplateDescribe,String accessUrl,String uploadUser){
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
}
