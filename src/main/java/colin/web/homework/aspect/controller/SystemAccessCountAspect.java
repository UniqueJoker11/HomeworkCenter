package colin.web.homework.aspect.controller;

import colin.web.homework.tools.IpConvertUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 2015/10/19.
 * 系统访问统计
 */
@Service
@Aspect
public class SystemAccessCountAspect {
    private Map<String,Object> accessMap=new HashMap<String,Object>();
    @Pointcut(value = "within(*colin.web.homework.controller.dashboard.TemplateDashboardController)")
    public void accessPointcut(){

    }
    @Before(value = "accessPointcut()")
    public void countUserAccess(JoinPoint joinPoint){
        HttpServletRequest request= (HttpServletRequest) joinPoint.getArgs()[0];
        System.out.println("远程访问地址是");
        System.out.println(request.getRemoteAddr()+"----"+request.getRemoteUser()+"---"+ IpConvertUtils.stringToBigInt(request.getRemoteAddr()));
    }
}
