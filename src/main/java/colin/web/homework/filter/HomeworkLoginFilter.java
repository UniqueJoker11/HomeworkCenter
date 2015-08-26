package colin.web.homework.filter;

import colin.web.homework.core.vo.HomeworkUserInfo;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by DELL on 2015/8/26.
 */
public class HomeworkLoginFilter extends AccessControlFilter{
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        System.out.println(request.getSession().getAttribute("userInfo")==null);
        if(request.getSession().getAttribute("userInfo")!=null){
            HomeworkUserInfo userInfo= (HomeworkUserInfo) request.getSession().getAttribute("userInfo");
            System.out.println(userInfo.getUser_name());
            System.out.println("我被调到为佛尼尔");
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        System.out.println("fioejfiaerjofeo");
        return true;
    }
}
