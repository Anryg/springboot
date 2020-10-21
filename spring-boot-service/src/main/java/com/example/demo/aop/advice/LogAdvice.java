package com.example.demo.aop.advice;

import com.alibaba.fastjson.JSON;
import com.example.demo.aop.annotation.LogAnnotation;
import com.example.demo.model.StatisticsReturnEntity;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @DESC: 定义需要织入的advice
 * @Auther: Anryg
 * @Date: 2020/9/9 17:01
 */
@Component(value = "logAdvice")
@Aspect
@Slf4j/*(topic = "logAdvice")*/
public class LogAdvice {

    /**
     * @DESC: 定义对应point cut的advice
     * */
    @Before(value = "com.example.demo.aop.pointcut.PointCut.logPointCut()")
    public void beforeAdvice(JoinPoint joinPoint) throws NoSuchMethodException, ClassNotFoundException {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestURL = request.getRequestURL().toString();
        String requestType = request.getMethod();
        String requestAddr = request.getRemoteAddr();
        String absoluteMethodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        String methodDesc = getMethodDesc(joinPoint);
        String methodArgs = new Gson().toJson(joinPoint.getArgs());

        log.info("==========================Start============================");
        log.info("URL          :{}",requestURL);
        log.info("DESC         :{}",methodDesc);
        log.info("HTTP method  :{}",requestType);
        log.info("Invoke method:{}",absoluteMethodName);
        log.info("IP           :{}",requestAddr);
        log.info("Method args  :{}",methodArgs);
    }

    /**
     * @DESC: 方法退出之后
     * */
    @After(value = "com.example.demo.aop.pointcut.PointCut.logPointCut()")
    public void afterAdvice(){
        log.info("==========================End==============================");
    }

    /**
     * @DESC: 方法返回之后
     * */
    @AfterReturning(pointcut = "com.example.demo.aop.pointcut.PointCut.logPointCut()",returning = "result")
    public void afterReturn(Object result){
        log.info("This is after return advice from LogAdvice...{}",result);
    }

    /**
     * @DESC: 方法抛出异常之后的处理方式，因为已经定义了GlobalExceptionHandler，这里就不再需要定义
     * */
/*    @AfterThrowing(value = "com.example.demo.aop.pointcut.PointCut.logPointCut()",throwing = "e")
    public void afterThrow(Throwable e){
        log.error("This is after throw from LogAdvice...{}",e.getMessage());
    }*/

    /**
     * @DESC: 环绕整个方法,用来统计方法执行时间
     * @return : 返回join point方法的返回值，切记这里一定要返回，否则web无法获取到join point方法返回的结果
     * */
    @Around(value = "com.example.demo.aop.pointcut.PointCut.logPointCut()")
    public Object around(ProceedingJoinPoint/**只有around才能使用该对象*/ joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnObject = joinPoint.proceed();/*切点执行，由此开始执行before,after return,after方法*/
        long endTime = System.currentTimeMillis();
        if (returnObject instanceof StatisticsReturnEntity){
            StatisticsReturnEntity returnEntity = (StatisticsReturnEntity) returnObject;
            returnEntity.setCostTime((int) (endTime - startTime));/**将返回的结果对象织入时间属性*/
            return returnEntity;
        }
        log.info("The method invoke cost time: {} ms",(endTime - startTime));
        return returnObject;
    }

    /**
     * @DESC: 获取调用方法的描述
     * */
    private String getMethodDesc(JoinPoint joinPoint) throws ClassNotFoundException, NoSuchMethodException {
        String desc = "";
        String className = joinPoint.getTarget().getClass().getName();/**当前join point的类名*/
        String methodName = joinPoint.getSignature().getName();/**当前join point的方法名*/
        Class<?> clazz = Class.forName(className);
        Method[] methods = clazz.getMethods();
        for (Method method:methods){
            if (method.getName().equals(methodName)){
                String des = method.getAnnotation(LogAnnotation.class).desc();
                if (null != des) desc = des;
            }
        }
        return desc;
    }
}
