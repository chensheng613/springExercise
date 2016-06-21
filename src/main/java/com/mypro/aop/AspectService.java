/*package com.mypro.aop;

import net.sf.json.JSONObject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mypro.entity.Student;
import com.mypro.repository.StudentRepository;

@Component
@Aspect
public class AspectService {

	private Logger logger = LoggerFactory.getLogger(AspectService.class);

	@Autowired
	private StudentRepository studentRepository;
	
	@Pointcut("execution(* com.mypro.aop.ManagerService.* (..))")
	public void aspect() {

	}

	@Before(value = "aspect()")
	public void doBefore(JoinPoint joinpoint) {
		logger.info("切面执行之前"+joinpoint);
	}

	@Around(value = "aspect()")
	public Object doAround(ProceedingJoinPoint joinpoint) {
		Object object = joinpoint.getArgs()[0];
//		JSONObject json = JSONObject.fromObject(object);
//		logger.info("切面执行过程中" + joinpoint.getTarget().toString());
//		logger.info("切面执行过程中,获得参数" + joinpoint.getArgs());
//		System.out.println("这是需要更新的对象："+json.toString());
//		Long id = Long.parseLong(json.getString("id"));
//		Student student = studentRepository.findById(id);
//		logger.info("这是数据库里面的对象:"+JSONObject.fromObject(student).toString());
		return object;
	}

	@After(value = "aspect()")
	public void doAfter(JoinPoint joinpoint) {
	}

	@AfterReturning(value = "aspect()",argNames="object",returning="object")
	public void afterReturn(JoinPoint joinpoint,Object obj) {
		Object object = joinpoint.getArgs()[0];
		JSONObject json = JSONObject.fromObject(object);
		Long id = Long.parseLong(json.getString("id"));
		Student student = studentRepository.findById(id);
		logger.info("这是数据库里面的对象:"+JSONObject.fromObject(student).toString());
		logger.info("这是需要更新的对象："+json.toString());
		//执行日志记录功能
	}
}
*/