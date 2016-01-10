package colin.web.homework.aspect.controller;

import colin.web.homework.service.NavManageService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DELL on 2016/1/7.
 */
@Service
@Aspect
public class BlogNavMenuAspect {

    @Autowired
    private NavManageService navManageService;

    @Pointcut(value = "execution(* colin.web.homework.controller.blog.*.show*(..))")
    public void initInteceptorMethod() {

    }

    @After(value = "initInteceptorMethod()")
    public void insertIntoNavMenu(JoinPoint joinPoint) {
        System.out.println("后置拦截器");
        HttpServletRequest request=(HttpServletRequest)joinPoint.getArgs()[0];
        request.setAttribute("navList",navManageService.fetchAllNavManage());
    }
}
