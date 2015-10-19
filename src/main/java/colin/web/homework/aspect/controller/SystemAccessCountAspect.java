package colin.web.homework.aspect.controller;

import colin.web.homework.tools.DateToolsUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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
    private Map<String,Integer> sysAccessMap =new HashMap<String,Integer>();

    @Pointcut(value = "execution(*userSignin *(..))")
    public void sysAccessPointcut(){

    }
    @Pointcut(value = "execution(*showTemplateManagePage *(..))")
    public void templateAccessPointcut(){

    }
    @Pointcut(value = "execution(*showBlogIndex *(..))")
    public void aticleAccessPointcut(){

    }
    /**
     * 统计修通的的访问
     * @param joinPoint
     */
    @Before(value = "sysAccessPointcut()")
    public void countUserAccess(JoinPoint joinPoint){
        HttpServletRequest request= (HttpServletRequest) joinPoint.getArgs()[0];
        String currentAccessKey=(request.getRemoteAddr() + DateToolsUtils.getTodayDate());
        if(sysAccessMap.containsKey(currentAccessKey)){
           sysAccessMap.put(currentAccessKey, sysAccessMap.get(currentAccessKey)+1);
        }else{
            sysAccessMap.put(currentAccessKey, 1);
        }
    }

}
