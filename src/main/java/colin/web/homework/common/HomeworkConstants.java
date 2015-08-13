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
    /**
     * 页面拦截器名称
     */
    public static final String CONTROLLER_MANAGER = "/homework";
    //请求用户登陆页面
    public static final String CONTROLLER_SIGNIN = "/signin.html";
    //验证用户名是否存在
    public static final String CONTROLLER_USERINFO_VALIDATION="/usernamevalidate.action";
    //请求用户注册页面
    public static final String CONTROLLER_SIGNUP = "/signup.html";
    //请求用户的管理首页
    public static final String CONTROLLER_DASHBOARD = "/dashboard.html";
    //验证用户的表单提交
    public static final String CONTROLLER_SIGNIN_FORM = "/userSignin.html";
    //验证用户的注册表单提交
    public static final String CONTROLLER_SIGNUP_FORM = "/userSignup.html";
    //模板管理页面
    public static final String CONTROLLER_TEMPLATE_MANAGE = "/template_view.html";
    //模板提交的页面
    public static final String CONTROLLER_TEMPLATE_ADD = "/template_add.html";
    //模板编辑页面
    public static final String CONTROLLER_TEMPLATE_EDIT = "/template_edit.html";
    //模板提交表单的请求
    public static final String CONTROLLER_TEMPLATE_ADD_FORM = "/template_add_form.action";
    //向openfire注册一个用户
    public static final String CONTROLLER_USER_CHAT_REGISTER="/register_user_chat.action";
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
