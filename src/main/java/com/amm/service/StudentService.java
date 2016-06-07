package com.amm.service;

import com.amm.entity.Student;

import java.util.List;

/**
 * Created by csw on 2016/3/16.
 */
public interface StudentService {

    Student create(Student student);

    List<Student> getAll();

    Student findOne(Integer id);

    Student updateStudentInfo(Student updateStu);

    Student deleteStudent(Integer id);

    boolean isValidUserName(String userName);
}
