package colin.web.homework.common;

import java.io.File;

/**
 * Created by ASUS on 2015/7/26.
 * 系统常量
 */
public class HomeworkConstants {
    /**
     * 页面的名称常量
     */
    //登陆页面
    public static final String PAGE_SIGNIN = "signin";
    //注册页面
    public static final String PAGE_SIGNUP = "signup";
    //主页
    public static final String PAGE_DASHBOARD = "dashboard";
    //模板管理页面
    public static final String PAGE_TEMPLATE_MANAGE = "template_view";
    //模板管理添加页面
    public static final String PAGE_TEMPLATE_ADD_MANAGE = "template_add";

    //个人日历记事本
    public static final String PAGE_TEMPLATE_TODAY_SCHEDULE = "schedule";
    //文章一览页面
    public static final String PAGE_ATICLE_BROWSER_VIEW = "aticle_browser";
    //文章添加页面
    public static final String PAGE_ATICLE_ADD_VIEW = "aticle_add";

    //转载文章页面
    public static final String PAGE_REPRINT_ATICLE_VIEW="reprint_aticle";

    //用户的信息管理页面
    public static final String PAGE_PERSON_MANAGE_VIEW="person_data";

    //收藏图片页面
    public static final String PAGE_COLLECT_PICTURE_VIEW="collect_picture";
    //服务一览页面
    public static final String PAGE_SERVICE_OVERVIEW_VIEW="service_overview";

    //菜单管理首页
    public static final String PAGE_MENU_MANAGE_VIEW="system_menu";
    //用户管理界面
    public static final String PAGE_USER_MANAGE_VIEW="system_user";

    //用户角色管理页面
    public static final String PAGE_USER_ROLE_MANAGE_VIEW="system_user_role";
    //用户权限管理页面
    public static final String PAGE_USER_AUTHORITY_MANAGE_VIEW="system_user_authority";


    //前台模板首页
    public static final String PAGE_TEMPLATE_DASHBOARD = "template/dashboard";



    /**
     * 页面拦截器名称
     */
    //模板的访问默认前缀
    public static final String CONTROLLER_TEMPLATTE_PREFIX = "/template";
    //后台访问默认前缀
    public static final String CONTROLLER_MANAGER_PREFIX = "/homework";
    //请求用户登陆页面
    public static final String CONTROLLER_SIGNIN = "/signin.html";
    //验证用户名是否存在
    public static final String CONTROLLER_USERINFO_VALIDATION = "/usernamevalidate.action";
    //请求用户注册页面
    public static final String CONTROLLER_SIGNUP = "/signup.html";
    //请求用户的管理首页
    public static final String CONTROLLER_DASHBOARD = "/dashboard.html";
    //验证用户的表单提交
    public static final String CONTROLLER_SIGNIN_FORM = "/userSignin.html";
    //验证用户的注册表单提交
    public static final String CONTROLLER_SIGNUP_FORM = "/signupUserinfo.html";
    //模板管理页面
    public static final String CONTROLLER_TEMPLATE_MANAGE = "/template_view.html";
    //模板提交的页面
    public static final String CONTROLLER_TEMPLATE_ADD = "/template_add.html";
    //模板提交表单的请求
    public static final String CONTROLLER_TEMPLATE_ADD_FORM = "/template_add_form.action";
    //模板上传模板截图
    public static final String CONTROLLER_TEMPLATE_UPLOAD_SNAPSHOT_PICTURE="uploadSnapshotPic.action";
    //上传压缩文件
    public static final String CONTROLLER_TEMPLATE_UPLOAD_ZIP_FILE="uploadTemplateZip.action";
    //返回所有的模板管理对象
    public static final String CONTROLLER_TEMPLATE_FETCHALL="/fetchall_template.action";
    //ajax请求分页返回模板的内容
    public static final String CONTROLLER_TEMPLATE_PAGE_LIST = "/fetch_template.action";
    //ajax根据id返回模板的内容信息
    public static final String CONTROLLER_TEMPLATE_SEARCH = "/template_search.action";
    //ajax更新模板的内容
    public static final String CONTROLLER_TEMPLATE_UPDATE = "/update_template.action";
    //ajax删除模板的内容
    public static final String CONTROLLER_TEMPLATE_DELETE = "/delate_template.action";
    //获取模板所有的标签，不重复
    public static final String CONTROLLER_TEMPLATE_FETCH_TIPS="/fetch_all_tips.action";
    //获取最近的模板
    public static final String CONTROLLER_TEMPLATE_FETCH_RECENTLY_TEMPLATE="/fetch_recent_template.action";
    //下载模板内容
    public static final String CONTROLLER_TEMPLATE_DOWNLOAD="/template_download.action";

    //收藏图片
    public static final String CONTROLLER_COLLECT_PICTURE="/collect_picture.html";

    //服务总览
    public static final String CONTROLLER_SERVICE_OVERVIEW_SHOW="/service_overview.html";


    //记事本页面
    public static final String CONTROLLER_TEMPLATE_TODAY_TEMPER = "/user_schedule.html";


    //文章一览页面
    public static final String CONTROLLER_ATICLE_BROWSER_PAGE = "/aticle_browser.html";
    //文章增加页面
    public static final String CONTROLLER_ATICLE_ADD_PAGE = "/aticle_add.html";
    //转载文章页面
    public static final String CONTROLLER_ATICLE_REPRINT_PAGE = "/aticle_reprint.html";
    //文章内容编辑
    public static final String CONTROLLER_ATICLE_EDIT_ACTION = "/aticle.edit.action";
    //文章内容删除
    public static final String CONTROLLER_ATICLE_DELETE_ACTION = "/aticle_delete.action";
    //文章内容增加
    public static final String CONTROLLER_ATICLE_ADD_ACTION = "/aticle_add.action";
    //文章内容搜索
    public static final String CONTROLLER_ATICLE_SEARCH_ACTION = "/aticle_search.action";
    //加载全部的内容
    public static final String CONTROLLER_ATICLE_SEARCH_ALL_ACTION="/aticle_fetch_all.action";

    //获取当日的行程
    public static final String CONTROLLER_ACTION_FETCH_CURRENT_SCHEDULE = "/fetch_current_schedule.action";
    //获取当日的行程
    public static final String CONTROLLER_ACTION_FETCH_TODAY_SCHEDULE = "/fetch_today_schedule.action";
    //发布当日的行程
    public static final String CONTROLLER_ACTION_PUBLISH_CURRENT_SCHEDULE = "/publish_current_schedule.action";

    //访问用户的管理页面
    public static final String CONTROLLER_PERSON_MANAGE_PAGE="/person_data.html";
    //返回用户管理的人员的角色信息
    public static final String CONTROLLER_ACTION_FETCH_MANAGE_USER_ROLE="/fetch_user_role.action";

    //菜单管理页面
    public static final String CONTROLLER_MEMU_MANAGE_PAGE="/system_menu.html";
    //加载系统的根菜单
    public static final String CONTROLLER_FETCH_MENU_ROOT="/fetch_menu_root.action";
    //加载系统的子节点菜单
    public static final String CONTROLLER_FETCH_MENU_NODE="/fetch_menu_node.action";
    //更新系统的菜单内容
    public static final String CONTROLLER_UPDATE_MENU_INFO="/update_menu_info.action";
    //新增系统的根菜单内容
    public static final String CONTROLLER_ADD_ROOT_MENU_INFO="/add_root_menu_info.action";
    //新增系统的节点菜单内容
    public static final String CONTROLLER_ADD_NODE_MENU_INFO="/add_node_menu_info.action";
    //删除系统的菜单内容
    public static final String CONTROLLER_DEL_MENU_INFO="/del_menu_info.action";

    //用户管理界面
    public static final String CONTROLLER_USER_MANAGE_PAGE="/system_user.html";
    //加载管理用户的信息
    public static final String CONTROLLER_USER_MANAGE_INFO="/fetch_system_user_roleinfo.action";
    //更新用户的权限信息
    public static final String CONTROLLER_USER_ROLE_CONFIG_ACTION="/update_system_user_role.action";
    //删除用户的信息
    public static final String CONTROLLER_USER_DEL_ACTION="del_system_user.action";
    //更新用户的基本信息
    public static final String CONTROLLER_USER_UPDATE_ACTION="update_system_user_info.action";

    //用户角色管理页面
    public static final String CONTROLLER_SYSTEM_USER_ROLE_MANAGE_PAGE="/system_user_role.html";
    //获取系统所有角色
    public static final String CONTROLLER_SYSTEM_USER_ROLE_LIST_ACTION="/list_system_roles.action";
    //获取对应角色的菜单
    public static final String CONTROLLER_SYSTEM_ROLE_MENU_ACTION="/fetch_system_role_menu.action";
    //更新角色对应的菜单
    public static final String CONTROLLER_UPDATE_SYSTEM_ROLE_MENU_ACTION="/update_system_role_menu.action";
    //删除用户的角色
    public static final String CONTROLLER_DELETE_SYSTEM_ROLE_MENU_ACTION="/delete_system_role_menu.action";




    //用户权限管理页面
    public static final String CONTROLLER_SYSTEM_USER_AUTHORITY_PAGE="/system_authority.html";
    //上传图片
    public static final String CONTROLLER_COMMON_UPLOAD_IMAGE="/upload_image.action";
    /**
     * 模板的前台页面访问
     */
    public static final String CONTROLLER_TAMPLATE_DASHBOARD = "/template_dashboard.html";
    /**
     * 默认的分页数据
     */
    public static final int PAGE_SIZE = 10;
    /**
     * 默认的资源上传路径
     */
    public static final String RESOURCES_STORE_DIR = "upload/resources/";
    public static final String IMAGE_STORE_DIR = "upload/images/";
    public static final String RESOURCES_COMPRESS_DIR = "upload/template/";

    /**
     * 用户存放信息
     */
    public static final String SESSION_USERINFO = "userInfo";

}
