package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.MenuDao;
import colin.web.homework.core.pojo.Homework_Menu_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import colin.web.homework.core.vo.HomeworkMenuVo;
import colin.web.homework.core.vo.MenuTreeNodeVo;
import colin.web.homework.tools.DateToolsUtils;
import colin.web.homework.tools.StringToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by ASUS on 2015/7/26.
 */
@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    /**
     * 加载所有的菜单内容
     *
     * @return
     */

    public List<HomeworkMenuVo> getMenuInfoService() {
        RowMapper entityMapper = new DefaultRowmapper<Homework_Menu_Entity>(Homework_Menu_Entity.class.getName());
        //获取所有的菜单
        List<Homework_Menu_Entity> menuList = menuDao.getOrderObjects(Homework_Menu_Entity.class, null, "menu_order", null, null, entityMapper, false);
        //开始整理菜单
        List<HomeworkMenuVo> resultList = this.reformatMenuList(menuList);
        return resultList;
    }

    /**
     * 根绝角色id获取菜单信息
     *
     * @param roleIds
     * @return
     */
    public List<HomeworkMenuVo> getUserMenuInfo(List<String> roleIds) {
        return this.reformatMenuList(menuDao.getUserMenuInfoByRoleId(roleIds));
    }

    /**
     * 获取系统所有的节点，用户拥有的菜单会被选中
     *
     * @param roleId
     * @return
     */
    public List<MenuTreeNodeVo> fetchMenuTreeinfo(String roleId) {
        //获取用户所拥有的角色菜单
        List<Homework_Menu_Entity> userRoleMenuList = this.menuDao.getUserMenuInfoByRoleId(Arrays.asList(new String[]{roleId}));

        //获取系统所有的菜单
        List<Homework_Menu_Entity> systemMenuList = this.menuDao.getOrderObjects(Homework_Menu_Entity.class, null, "menu_order", null, null, new DefaultRowmapper<Homework_Menu_Entity>(Homework_Menu_Entity.class.getName()), false);

        //遍历出所有的顶级节点
        List<MenuTreeNodeVo> syetemMenuList = new ArrayList<MenuTreeNodeVo>();//存储顶级节点
        for (Homework_Menu_Entity menu_entity : systemMenuList) {
            MenuTreeNodeVo treeNodeVo = new MenuTreeNodeVo();
            treeNodeVo.setName(menu_entity.getMenu_name());
            treeNodeVo.setId(menu_entity.getMenu_id());
            treeNodeVo.setpId(menu_entity.getMenu_parent_id());
            for (Homework_Menu_Entity user_menu_entity : userRoleMenuList) {
                if (user_menu_entity.getMenu_id().equals(menu_entity.getMenu_id())) {
                    treeNodeVo.setChecked(true);
                }
            }
            if (menu_entity.getMenu_parent_id().equals("root")) {
                treeNodeVo.setParent(true);
                treeNodeVo.setOpen(true);
            }
            syetemMenuList.add(treeNodeVo);
        }
        return syetemMenuList;

    }


    /**
     * 获取跟菜单目录
     *
     * @return
     */
    public List<HomeworkMenuVo> getMenuRootInfo() {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("menu_parent_id", "root");
        List<Homework_Menu_Entity> menu_entityList = this.menuDao.seletcObjectByMap(Homework_Menu_Entity.class, params, new DefaultRowmapper<Homework_Menu_Entity>(Homework_Menu_Entity.class.getName()));
        //转换对象
        return this.reformatMenuList(menu_entityList);
    }

    /**
     * 获取菜单的子节点
     *
     * @param parent_id
     * @return
     */
    public List<HomeworkMenuVo> getMenuNodeInfo(String parent_id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("menu_parent_id", parent_id);
        List<Homework_Menu_Entity> menu_entityList = this.menuDao.seletcObjectByMap(Homework_Menu_Entity.class, params, new DefaultRowmapper<Homework_Menu_Entity>(Homework_Menu_Entity.class.getName()));
        //转换对象
        return this.transferMenuList(menu_entityList);
    }

    /**
     * 新增菜单的内容
     */
    public boolean saveMenuInfo(Map<String, Object> menuParams) {
        Homework_Menu_Entity homework_menu_entity = new Homework_Menu_Entity();
        homework_menu_entity.setMenu_id(StringToolsUtils.getCommonUUID());
        homework_menu_entity.setMenu_icon(menuParams.get("menuIcon").toString());
        homework_menu_entity.setMenu_name(menuParams.get("menuName").toString());
        homework_menu_entity.setMenu_order(Integer.parseInt(menuParams.get("menuOrder").toString()));
        if (menuParams.containsKey("menuUrl")) {
            homework_menu_entity.setMenu_url(menuParams.get("menuUrl").toString());
            homework_menu_entity.setMenu_parent_id(menuParams.get("parentId").toString());
        } else {
            homework_menu_entity.setMenu_url("#");
            homework_menu_entity.setMenu_parent_id("root");
        }
        homework_menu_entity.setMenu_create_user(menuParams.get("createUser").toString());
        homework_menu_entity.setMenu_create_time(DateToolsUtils.getTodayCurrentTime());

        return this.menuDao.addObjInfo(homework_menu_entity);
    }

    /**
     * 更新菜单的内容
     *
     * @param menuParams
     * @return
     */
    public boolean updateMenuInfo(Map<String, Object> menuParams) {
        Homework_Menu_Entity homework_menu_entity = new Homework_Menu_Entity();
        homework_menu_entity.setMenu_id(menuParams.get("menuId").toString());
        if (menuParams.containsKey("menuIcon")) {
            homework_menu_entity.setMenu_icon(menuParams.get("menuIcon").toString());
        }
        if (menuParams.containsKey("menuName")) {
            homework_menu_entity.setMenu_name(menuParams.get("menuName").toString());
        }
        if (menuParams.containsKey("menuOrder")) {
            homework_menu_entity.setMenu_order(Integer.parseInt(menuParams.get("menuOrder").toString()));
        }
        if (menuParams.containsKey("menuUrl")) {
            homework_menu_entity.setMenu_url(menuParams.get("menuUrl").toString());
        } else {
            homework_menu_entity.setMenu_url("#");
        }
        return this.menuDao.updateObjInfo(homework_menu_entity);
    }

    /**
     * 根绝Id删除菜单对象
     *
     * @param menuId
     * @return
     */
    public boolean deleteMenuInfo(String menuId) {
        return this.menuDao.deleteObjectById(Homework_Menu_Entity.class, menuId);
    }

    /**
     * 重新构造返回结果
     *
     * @param menuList
     * @return
     */
    private List<HomeworkMenuVo> reformatMenuList(List<Homework_Menu_Entity> menuList) {
        List<HomeworkMenuVo> rootList = new ArrayList<HomeworkMenuVo>(), subList = new ArrayList<HomeworkMenuVo>();
        for (Homework_Menu_Entity menu_entity : menuList) {
            if (menu_entity.getMenu_parent_id().equals("root")) {
                rootList.add(this.transferMenu(menu_entity));
            } else {
                subList.add(this.transferMenu(menu_entity));
            }
        }
        return rebulidMenuList(rootList, subList);
    }

    private List<HomeworkMenuVo> rebulidMenuList(List<HomeworkMenuVo> rootList, List<HomeworkMenuVo> subList) {
        for (HomeworkMenuVo homeworkMenuVo : rootList) {
            List<HomeworkMenuVo> childMenuList = new ArrayList<HomeworkMenuVo>();
            for (HomeworkMenuVo childMenu : subList) {
                if (homeworkMenuVo.getMenu_id().equals(childMenu.getMenu_parent_id())) {
                    childMenuList.add(childMenu);
                }
            }
            homeworkMenuVo.setChildMenuList(childMenuList);
        }
        return rootList;
    }

    /**
     * 转换Menu实体类
     *
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

    /**
     * 转换menuList
     *
     * @param menu_entityList
     * @return
     */
    public List<HomeworkMenuVo> transferMenuList(List<Homework_Menu_Entity> menu_entityList) {
        List<HomeworkMenuVo> homeworkMenuVos = new ArrayList<HomeworkMenuVo>();
        for (Homework_Menu_Entity menu_entity : menu_entityList) {
            homeworkMenuVos.add(transferMenu(menu_entity));
        }
        return homeworkMenuVos;
    }
}
