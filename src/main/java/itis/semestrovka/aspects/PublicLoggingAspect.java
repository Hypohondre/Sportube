package itis.semestrovka.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class PublicLoggingAspect {
    private Logger logger = Logger.getLogger(PublicLoggingAspect.class.getName());

    @Pointcut("execution(public * itis.semestrovka.services.implementations.ProfileServiceImpl.*(..))")
    public void getPublicInfo() {}

    @Pointcut("execution(private * itis.semestrovka.services.implementations.ProfileServiceImpl.*(..))")
    public void getPrivateInfo() {}

    @Around(value = "getPublicInfo()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        logger.log(Level.INFO, "Method: " + joinPoint.getSignature());
        logger.log(Level.INFO, "Args: " + Arrays.toString(joinPoint.getArgs()));
        logger.log(Level.INFO, "Started at: " + LocalTime.now());
        Object joinPoint1 = joinPoint.proceed();
        logger.log(Level.INFO, "Ended at: " + LocalTime.now());
        return joinPoint1;
    }

    @AfterReturning(value = "getPublicInfo()", returning = "returningValue")
    public void logReturningValue(JoinPoint joinPoint, Object returningValue) {
        logger.log(Level.INFO, "Returned value: " + returningValue);
    }

}