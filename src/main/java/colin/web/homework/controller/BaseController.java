package colin.web.homework.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ASUS on 2015/7/11.
 */
public class BaseController {
    /**
     * 获取系统Session
     * @param request
     * @return
     */
    protected HttpSession getSystemHttpSession(HttpServletRequest request) {
        return request.getSession();
    }

}
