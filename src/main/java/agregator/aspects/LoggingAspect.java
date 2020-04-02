package agregator.aspects;

import java.io.IOException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import agregator.io.Log;

@Component
@Aspect
public class LoggingAspect {
    @Autowired
    private Log log;
    
    @Pointcut("execution(* agregator.controllers.*.*(..))")
    public void controllersMethods() {}
    
    @Before("controllersMethods()")
    public void requestBefore(JoinPoint joinPoint) {
        log.writeEvent("start " + joinPoint.getSignature().getName() + " request");
    }
    
    @AfterReturning("controllersMethods()")
    public void requestAfter(JoinPoint joinPoint) {
        log.writeEvent("end " + joinPoint.getSignature().getName() + " request");
    }
    
    @AfterThrowing(pointcut = "controllersMethods()",
                   throwing = "exc")
    public void requestIOException(JoinPoint joinPoint, IOException exc) {
        log.writeEvent("ERROR " + exc.getMessage());
        requestAfter(joinPoint);
    }
    
    @AfterThrowing(pointcut = "controllersMethods()",
                   throwing = "exc")
    public void requestSAXException(JoinPoint joinPoint, SAXException exc) {
        log.writeEvent("ERROR " + exc.getMessage());
        requestAfter(joinPoint);
    }
}