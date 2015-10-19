package colin.web.homework.aspect.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * Created by ASUS on 2015/8/11.
 */
@Service
@Aspect
public class LoggerRecordAspect {
    @Pointcut(value = "within(colin.web.homework.controller.*Controller)")
    public void initPointCut(){}

    @Before(value = "initPointCut()")
    public void recordVisitClassInfo(JoinPoint joinPoint){
        System.out.println("当前方法名的参数数量是"+joinPoint.getArgs().length);
        System.out.println("当前的访问类名是"+joinPoint.getTarget().toString()+"当前的访问方法是"+joinPoint.getSignature().getName());
    }
}
