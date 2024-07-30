package org.example.discussionrest.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(public * org.example.discussionrest.service.*.*(..))")
    public void servicePointcut() {
    }

    @Pointcut("execution(public * org.example.discussionrest.dao.*.*(..))")
    public void daoPointcut() {
    }

    @Pointcut("execution(public * org.example.discussionrest.controller.*.*(..))")
    public void controllerPointcut() {
    }

    @Pointcut("servicePointcut() || daoPointcut()")
    public void logThrowablePointcut() {
    }

    @Pointcut("controllerPointcut()")
    public void logPointcut() {
    }

    @Around("logThrowablePointcut()")
    public Object logThrowableAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return logAround(joinPoint);
        } catch (Throwable throwable) {
            log.warn(throwable.getMessage(), throwable);
            throw throwable;
        }
    }

    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logExecution(joinPoint);
        Object result = joinPoint.proceed();
        logResult(joinPoint, result);
        return result;
    }

    private void logExecution(ProceedingJoinPoint joinPoint) {
        log.debug("Execution {}.{}() with arguments {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

    private void logResult(ProceedingJoinPoint joinPoint, Object result) {
        log.debug("Result of {}.{}() with arguments {}: {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()), result);
    }
}
