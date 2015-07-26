package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.MenuDao;
import colin.web.homework.core.pojo.Homework_Menu_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import colin.web.homework.core.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<MenuVo> getMenuInfoService() {
        RowMapper entityMapper = new DefaultRowmapper<Homework_Menu_Entity>(Homework_Menu_Entity.class.getName());
        //获取所有的菜单
        List<Homework_Menu_Entity> menuList = menuDao.getOrderObjects(Homework_Menu_Entity.class, null, "menu_order", null, null,entityMapper, false);
        //开始整理菜单
        List<MenuVo> resultList=this.reformatMenuList(menuList);
        return resultList;

    }

    /**
     * 重新构造返回结果
     * @param menuList
     * @return
     */
    private List<MenuVo> reformatMenuList(List<Homework_Menu_Entity> menuList) {
        List<MenuVo> rootList = new ArrayList<>(), subList = new ArrayList<>();
        for (Homework_Menu_Entity menu_entity : menuList) {
            if (menu_entity.getMenu_parent_id().equals("root")) {
                rootList.add(this.transferMenu(menu_entity));
            } else {
                subList.add(this.transferMenu(menu_entity));
            }
        }
        return rebulidMenuList(rootList,subList);
    }

    public List<MenuVo> rebulidMenuList(List<MenuVo> rootList,List<MenuVo> subList){
        for(MenuVo menuVo:rootList){
            List<MenuVo> childMenuList=new ArrayList<>();
            for(MenuVo childMenu:subList){
                if(menuVo.getMenu_id().equals(childMenu.getMenu_parent_id())){
                    childMenuList.add(childMenu);
                }
            }
            menuVo.setChildMenuList(childMenuList);
        }
        return rootList;
    }
    /**
     * 转换Menu实体类
     * @param menu_entity
     * @return
     */
    private MenuVo transferMenu(Homework_Menu_Entity menu_entity) {
        MenuVo menuVo = new MenuVo();
        menuVo.setMenu_id(menu_entity.getMenu_id());
        menuVo.setMenu_parent_id(menu_entity.getMenu_parent_id());
        menuVo.setMenu_icon(menu_entity.getMenu_icon());
        menuVo.setMenu_name(menu_entity.getMenu_name());
        menuVo.setMenu_url(menu_entity.getMenu_url());
        return menuVo;
    }
}
