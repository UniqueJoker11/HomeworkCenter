package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.TemplateNavDao;
import colin.web.homework.core.pojo.Homework_Template_Nav_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import colin.web.homework.core.vo.HomeworkTemplateNavVo;
import colin.web.homework.tools.DateToolsUtils;
import colin.web.homework.tools.StringToolsUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2016/1/26.
 */
@Service
public class TemplateNavService {

    @Autowired
    private TemplateNavDao templateNavDao;

    /**
     * 添加模板导航轮播图对象
     *
     * @param navSrc
     * @param navDigest
     * @param navOrder
     * @param navUser
     */
    public void addTemplateNav(String navSrc, String navDigest, String navOrder, String navUser) {
        //封装对象
        Homework_Template_Nav_Entity template_nav_entity = new Homework_Template_Nav_Entity();
        template_nav_entity.setTemplate_nav_id(StringToolsUtils.getCommonUUID());
        template_nav_entity.setTemplate_nav_src(navSrc);
        template_nav_entity.setTemplate_nav_digest(navDigest);
        template_nav_entity.setTemplate_nav_order(navOrder);
        template_nav_entity.setTemplate_nav_user(navUser);
        template_nav_entity.setTemplate_nav_createtime(DateToolsUtils.getTodayCurrentTime());
        templateNavDao.addObjInfo(template_nav_entity);
    }

    public void updateTemplateNav(String navId, String navDigest, String navOrder) {
        Homework_Template_Nav_Entity template_nav_entity = new Homework_Template_Nav_Entity();
        template_nav_entity.setTemplate_nav_id(navId);
        template_nav_entity.setTemplate_nav_digest(navDigest);
        template_nav_entity.setTemplate_nav_order(navOrder);
        template_nav_entity.setTemplate_nav_updatetime(DateToolsUtils.getTodayCurrentTime());
        templateNavDao.updateObjInfo(template_nav_entity);
    }

    public List<HomeworkTemplateNavVo> fetchTemplateNav() throws InvocationTargetException, IllegalAccessException {
        List<Homework_Template_Nav_Entity> templateNavEntities = templateNavDao.getAmongObjectWithOrder(Homework_Template_Nav_Entity.class, null, "template_nav_order", "", new DefaultRowmapper<Homework_Template_Nav_Entity>(Homework_Template_Nav_Entity.class.getName()), true);
        return trransferTemplateBean(templateNavEntities);
    }

    private List<HomeworkTemplateNavVo> trransferTemplateBean(List<Homework_Template_Nav_Entity> templateNavEntities) throws InvocationTargetException, IllegalAccessException {
        if (templateNavEntities != null && !templateNavEntities.isEmpty()) {
            List<HomeworkTemplateNavVo> templateNavVos = new ArrayList<HomeworkTemplateNavVo>();
            for (Homework_Template_Nav_Entity template_nav_entity : templateNavEntities) {
                HomeworkTemplateNavVo templateNavVo = new HomeworkTemplateNavVo();
                BeanUtils.copyProperties(templateNavVo, template_nav_entity);
                templateNavVos.add(templateNavVo);
            }
            return templateNavVos;
        } else {
            return null;
        }
    }
}
