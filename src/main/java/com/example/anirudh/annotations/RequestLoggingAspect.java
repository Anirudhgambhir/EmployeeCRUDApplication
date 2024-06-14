package com.example.anirudh.annotations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Component
@Slf4j
public class RequestLoggingAspect {

    @Before("@annotation(RequestLogging)")
    public void logBefore(JoinPoint joinPoint) {
        // Log method entry for controllers
        Object[] args = joinPoint.getArgs();
        if (Objects.nonNull(args)) {
            String parameters = (String)((Stream) Arrays.stream(args).sequential()).map(Object::toString).collect(Collectors.joining());
            log.info("Entering method: with parameters: {}", parameters);
        }
    }
}
