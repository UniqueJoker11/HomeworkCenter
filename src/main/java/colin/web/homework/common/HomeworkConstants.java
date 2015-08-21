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
    public static final String PAGE_PERSON_MANAGE_VIEW="person_manage";
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
    //返回所有的模板管理对象
    public static final String CONTROLLER_TEMPLATE_FETCHALL="fetchall_template.action";
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


    //获取当日的行程
    public static final String CONTROLLER_ACTION_FETCH_CURRENT_SCHEDULE = "/fetch_current_schedule.action";
    //获取当日的行程
    public static final String CONTROLLER_ACTION_FETCH_TODAY_SCHEDULE = "/fetch_today_schedule.action";
    //发布当日的行程
    public static final String CONTROLLER_ACTION_PUBLISH_CURRENT_SCHEDULE = "/publish_current_schedule.action";
    //访问用户的管理页面
    public static final String CONTROLLER_PERSON_MANAGE_PAGE="/person_manage.html";
    //
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
    public static final String RESOURCES_STORE_DIR = "upload" + File.separator + "resources";
    public static final String IMAGE_STORE_DIR = "upload" + File.separator + "images";
    public static final String RESOURCES_COMPRESS_DIR = "upload" + File.separator + "template";

    /**
     * 用户存放信息
     */
    public static final String SESSION_USERINFO = "userInfo";

}
