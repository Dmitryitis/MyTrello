package com.trello.mytrello_api.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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
}
