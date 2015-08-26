package colin.web.homework.controller;

import colin.web.homework.common.HomeworkConstants;
import colin.web.homework.core.vo.HomeworkUserInfo;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ASUS on 2015/7/11.
 */
public class BaseController {

    /**
     * 获取基于应用程序的url绝对路径
     *
     * @param request
     * @return
     */
    protected final String getAppBaseUrl(HttpServletRequest request, String url) {
        Assert.hasLength(url, "url不能为空");
        Assert.isTrue(url.startsWith("/"), "必须以/打头");
        return request.getContextPath() + url;
    }

    /**
     * 获取HttpServletRequest
     *
     * @return
     */
    protected HttpServletRequest getRequestObj() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取ServletContext
     *
     * @return
     */
    protected ServletContext getServletContext() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getServletContext();
    }

    /**
     * 获取HttpSession
     *
     * @return
     */
    protected HttpSession getSessionObj() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

    /**
     * 获取存放在Session中的用户信息
     */
    protected HomeworkUserInfo fetchUserInfo() {
        HomeworkUserInfo homeworkUserInfo = (HomeworkUserInfo) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("userInfo");
        return homeworkUserInfo;
    }

    /**
     * 判断用户是否已经登录
     *
     * @return
     */
    protected boolean userIsLogin() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute(HomeworkConstants.SESSION_USERINFO) == null ? false : true;
    }

    public Map<String, Object> formatRequestParamsMap(HttpServletRequest request) {
        Map<String, String[]> paramsMap = request.getParameterMap();
        Set<String> keySet = paramsMap.keySet();
        Map<String, Object> resultMap = new HashMap<>();
        for (String key : keySet) {
            resultMap.put(key, request.getParameter(key));
        }
        return resultMap;
    }
}
