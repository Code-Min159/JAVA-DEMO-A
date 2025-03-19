package com.lesson.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.lesson.demo.service.UserService;

import java.util.Objects;

@Aspect
@Component //組件
public class LoggingAspect {

    @Autowired //
    public UserService userService;

    /**
    @Around("execution(* com.lesson.demo.controller.*.*(...))")
    //ProceedingJoinPoint 插入點
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws  Throwable{
        long start = System.currentTimeMillis(); //
        Object result = joinPoint.proceed();    //
        long end = System.currentTimeMillis();   //
        System.out.println(joinPoint.getSignature() + " executed in " + (end - start) + "ms");
         return result;
    }
    **/

    @Around("execution(* com.lesson.demo.controller.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println(joinPoint.getSignature() + " executed in " + (end - start) + "ms");
        return result;
    }

    /**
     * Before("execution(* com.lesson.demo.controller.*.*(..))")
     * public void  logFunctionStart(JoinPoint joinPoint){
     * System.out.println(joinPoint.getSignature() + "start");
     * }
     **/

    @Before("execution(* com.lesson.demo.controller.*.*(..))")
    public void logFunctionStart(JoinPoint joinPoint) throws Exception {
        // 拿實際http request 內的值
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String userRole = userService.gerUserRole("");
        if (!Objects.equals(userRole, "admin")) {
            throw new Exception("no access");
        }
        System.out.println(joinPoint.getSignature() + "start");
    }

}
