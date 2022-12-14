package testAOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(public * testAOP.AOPTest.*Method(..))")//芝士匹配所有以Method的方法
    private void needMethod() {
    }

   /* @Around("needMethod()")
    public Object adviceMethod(ProceedingJoinPoint pjp) throws Throwable {
        Signature signature = pjp.getSignature();//获取切入点签名
        System.out.println("切入点名为:" + signature.getDeclaringTypeName() +"."+ signature.getName());
        long beginTime = System.currentTimeMillis();
        Object[] args = pjp.getArgs();// 获取方法参数
        args[0] = 890;// 改变参数
        Object obj = pjp.proceed();// 执行方法
        System.out.println("该方法的参数是" + Arrays.toString(args));//输出参数
        Thread.sleep(200);
        long endTime = System.currentTimeMillis();
        long spendTime = endTime - beginTime;
        System.out.println("所耗费的时间为" + spendTime);
        return obj;
    }*/

    @AfterReturning(value = "needMethod()", returning = "obj")
    public void adviceMethod(Object obj) {
        System.out.println("这是返回之后的通知:" + obj);
    }

    @AfterThrowing(value = "needMethod()", throwing = "e")
    public void adviceMethod03(Throwable e) {
        System.out.println("这是它抛出的异常" + e);
    }
}
