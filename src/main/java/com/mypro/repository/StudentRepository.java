package com.mypro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypro.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	public Student findById(Long id);
}
