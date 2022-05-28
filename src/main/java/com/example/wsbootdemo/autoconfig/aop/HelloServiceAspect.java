package com.example.wsbootdemo.autoconfig.aop;

import com.example.wsbootdemo.exceptions.InvalidHelloPrefixException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;


@Aspect
public class HelloServiceAspect {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Before(value = "execution(* com.example.wsbootdemo.autoconfig.beans.HelloService.sayHello(..)) && args(name)")
	public void beforeAdvice(JoinPoint joinPoint, String name) {
		logger.info("Before method:" + joinPoint.getSignature());

		logger.info("Invoking HelloService with name - " + name );
	}

	@After(value = "execution(* com.example.wsbootdemo.autoconfig.beans.HelloService.*(..)) && args(name)")
	public void afterAdvice(JoinPoint joinPoint, String name) {
		logger.info("After method:" + joinPoint.getSignature());

		logger.info("Successfully invoked HelloService with name - " + name);
	}


	@Around("methodsToBeProfiled()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {

		StopWatch sw = new StopWatch(getClass().getSimpleName());
		try {

			sw.start(pjp.getSignature().getName());
			return pjp.proceed();
		} finally {

			sw.stop();
			System.out.println("Performance: "+sw.prettyPrint());
		}
	}

	@Pointcut("execution(public * com.example.wsbootdemo.autoconfig.beans..*.*(..))")
	public void methodsToBeProfiled(){
	}

	@AfterThrowing (pointcut = "execution(public * com.example.wsbootdemo.autoconfig.beans..*.*(..))", throwing = "ex")
	public void logAfterThrowingAllMethods(InvalidHelloPrefixException ex) throws Throwable  {
		logger.error("FATAL: Auto destruction procedure will be activated in 5 seconds: " + ex);
		try
		{
			Thread.sleep(1000);
			logger.error("FATAL: Auto destruction procedure will be activated in 4 seconds ...");
			Thread.sleep(1000);
			logger.error("FATAL: Auto destruction procedure will be activated in 3 seconds ...");
			Thread.sleep(1000);
			logger.error("FATAL: Auto destruction procedure will be activated in 2 seconds ...");
			Thread.sleep(1000);
			logger.error("FATAL: Auto destruction procedure will be activated in 1 seconds ...");
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			// log the exception.
		}
		System.exit(-10);
	}

}
