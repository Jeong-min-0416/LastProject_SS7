package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {

    // 실행 로그
    @Before("execution(* org.zerock.service.SampleService*.*(..))")
    public void logBefore() {
        log.info("SampleService(이)가 실행되었습니다.");
    }

    // 메서드의 인자 값을 로깅
    @Before("execution(* org.zerock.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
    public void logBeforeWithParam(String str1, String str2) {
        log.info("str1: " + str1);
        log.info("str2: " + str2);
    }

    //  실행 중에 예외가 발생
    @AfterThrowing(pointcut = "execution(* org.zerock.service.SampleService*.*(..))", throwing = "exception")
    public void logException(Exception exception) {
        log.info("SampleService 클래스의 메서드 실행 중에 예외 발생");
        log.info("exception: " + exception);
    }

    // 메서드 실행 전후에 실행 시간 및 관련 정보
    @Around("execution(* org.zerock.service.SampleService*.*(..))")
    public Object logTime(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();

        log.info("Target: " + pjp.getTarget());
        log.info("Param: " + Arrays.toString(pjp.getArgs()));

        Object result = null;

        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        log.info("TIME: " + (end - start));
        return result;
    }
}
