package itis.semestrovka.aspects;

import itis.semestrovka.security.details.UserDetailsImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class UpdateUserDetailsLoggingAspect {
    private Logger logger = Logger.getLogger(UpdateUserDetailsLoggingAspect.class.getName());

    @Pointcut("execution(public * itis.semestrovka.services.implementations.UpdateUserDetailsServiceImpl.updateUserDetails(..))")
    public void getUserInfo() {}

    @Before("getUserInfo()")
    public void beforeAdding(JoinPoint joinPoint) {
        UserDetailsImpl userDetails = (UserDetailsImpl) Arrays.stream(joinPoint.getArgs()).findFirst().get();
        logger.log(Level.INFO, "UserDetails before: " + userDetails.getUser().toString());
    }
}
