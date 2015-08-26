package colin.web.homework.filter;

import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by DELL on 2015/8/26.
 */
public class HomeworkLoginFilter extends AccessControlFilter{
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        System.out.println("我被调到为佛尼尔");
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        System.out.println("fioejfiaerjofeo");
        return true;
    }
}
