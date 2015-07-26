package colin.web.homework.common;

/**
 * Created by ASUS on 2015/7/26.
 * 系统常量
 */
public class HomeworkConstants {
    /**
     * 页面的名称常量
     */
    //登陆页面
    public static final String PAGE_SIGNIN="signin";
    //注册页面
    public static final String PAGE_SIGNUP="signup";
    //主页
    public static final String PAGE_DASHBOARD="dashboard";
    /**
     * 页面拦截器名称
     */
    public static final String CONTROLLER_MANAGER="/homework";
    //请求用户登陆页面
    public static final String CONTROLLER_SIGNIN="/signin.html";
    //请求用户注册页面
    public static final String CONTROLLER_SIGNUP="/signup.html";
    //请求用户的管理首页
    public static final String CONTROLLER_DASHBOARD="/dashboard.html";
    //验证用户的表单提交
    public static final String CONTROLLER_SIGNIN_FORM="/userSignin.html";
    //验证用户的注册表单提交
    public static final String CONTROLLER_SIGNUP_FORM="/userSignup.html";
    /**
     * 默认的分页数据
     */
    public static final int PAGE_SIZE=10;

}
