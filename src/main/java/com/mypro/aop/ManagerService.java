/*package com.mypro.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypro.entity.Student;
import com.mypro.repository.StudentRepository;

@Service
public class ManagerService {

	@Autowired
	private StudentRepository studentRepository;
	
	private Logger logger = LoggerFactory.getLogger(ManagerService.class);

	public void saveManager() {
		Student student = new Student();
		student.setAddress("这是初始化的地址");
		student.setName("这是初始化的名称");
		studentRepository.save(student);
		logger.info("执行学生信息初始化操作");
	}

	public void delManager() {
		logger.info("执行管理员删除操作");
	}

	public Student update(Student student) {
		return student;
	}
}
*/