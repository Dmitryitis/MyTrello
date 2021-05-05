package com.trello.mytrello_api.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {

    private final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("execution(* com.trello.mytrello_api.controller.AuthenticationRestControllerV1.*(..))")
    public void stringProcessingMethods() {
    }

    @Before("stringProcessingMethods()")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        logger.log(Level.INFO, "Начал работать: " + methodName);
    }

    @After("stringProcessingMethods()")
    public void logMethodCallBack(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        logger.log(Level.INFO, "Отработал: " + methodName);
    }

    @Around("execution(* com.trello.mytrello_api.service.impl.UserDetailsServiceImpl.loadUserByUsername(..)))")
    public Object logCacheMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String wrappedClassName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        logger.log(Level.INFO, "LOG by AOP - invoking " + methodName + " " + Arrays.toString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();

        logger.log(Level.INFO,"LOG by AOP - result of "+ methodName + " "+ result);

        return result;
    }
}
