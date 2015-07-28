package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.MenuDao;
import colin.web.homework.core.pojo.Homework_Menu_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import colin.web.homework.core.vo.HomeworkMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2015/7/26.
 */
@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    /**
     * 获取主页的左侧导航栏内容
     *
     * @return
     */

    public List<HomeworkMenuVo> getMenuInfoService() {
        RowMapper entityMapper = new DefaultRowmapper<Homework_Menu_Entity>(Homework_Menu_Entity.class.getName());
        //获取所有的菜单
        List<Homework_Menu_Entity> menuList = menuDao.getOrderObjects(Homework_Menu_Entity.class, null, "menu_order", null, null,entityMapper, false);
        //开始整理菜单
        List<HomeworkMenuVo> resultList=this.reformatMenuList(menuList);
        return resultList;

    }

    /**
     * 重新构造返回结果
     * @param menuList
     * @return
     */
    private List<HomeworkMenuVo> reformatMenuList(List<Homework_Menu_Entity> menuList) {
        List<HomeworkMenuVo> rootList = new ArrayList<>(), subList = new ArrayList<>();
        for (Homework_Menu_Entity menu_entity : menuList) {
            if (menu_entity.getMenu_parent_id().equals("root")) {
                rootList.add(this.transferMenu(menu_entity));
            } else {
                subList.add(this.transferMenu(menu_entity));
            }
        }
        return rebulidMenuList(rootList,subList);
    }

    public List<HomeworkMenuVo> rebulidMenuList(List<HomeworkMenuVo> rootList,List<HomeworkMenuVo> subList){
        for(HomeworkMenuVo homeworkMenuVo :rootList){
            List<HomeworkMenuVo> childMenuList=new ArrayList<>();
            for(HomeworkMenuVo childMenu:subList){
                if(homeworkMenuVo.getMenu_id().equals(childMenu.getMenu_parent_id())){
                    childMenuList.add(childMenu);
                }
            }
            homeworkMenuVo.setChildMenuList(childMenuList);
        }
        return rootList;
    }
    /**
     * 转换Menu实体类
     * @param menu_entity
     * @return
     */
    private HomeworkMenuVo transferMenu(Homework_Menu_Entity menu_entity) {
        HomeworkMenuVo homeworkMenuVo = new HomeworkMenuVo();
        homeworkMenuVo.setMenu_id(menu_entity.getMenu_id());
        homeworkMenuVo.setMenu_parent_id(menu_entity.getMenu_parent_id());
        homeworkMenuVo.setMenu_icon(menu_entity.getMenu_icon());
        homeworkMenuVo.setMenu_name(menu_entity.getMenu_name());
        homeworkMenuVo.setMenu_url(menu_entity.getMenu_url());
        return homeworkMenuVo;
    }
}
