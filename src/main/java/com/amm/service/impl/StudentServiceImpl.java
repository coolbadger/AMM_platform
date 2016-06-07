package com.amm.service.impl;

import com.amm.entity.Student;
import com.amm.repository.StudentRepository;
import com.amm.service.StudentService;
import org.apache.commons.lang3.Validate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by csw on 2016/3/16.
 */
@Component("studentService")
@Scope("prototype")
public class StudentServiceImpl extends BaseService implements StudentService{

    @Resource
    private StudentRepository studentRepository;

    public Student create(Student created) {
        logger.debug("creating a new student with information: " + created);

        return studentRepository.save(created);
    }

    public List<Student> getAll() {

        return (List<Student>) studentRepository.findAll();
    }

    public Student findOne(Integer id) {

        return studentRepository.findOne(id);
    }

    public Student updateStudentInfo(Student updateStu) {
        Validate.notNull(updateStu, "can't update student info");
        Validate.notNull(updateStu.getId(), "can't update student info, id is null");

        Student saveStu = this.findOne(updateStu.getId());
        saveStu = updateStu.changeInfo(saveStu);

        saveStu = studentRepository.save(saveStu);

        return saveStu;
    }

    public Student deleteStudent(Integer id) {
        Validate.notNull(id, "can't find student, id is null");

        Student student = this.findOne(id);

        Validate.notNull(student, "can't find student, student is none");
        student.setIsDelete(true);
        student = studentRepository.save(student);

        return student;
    }

    public boolean isValidUserName(String userName) {
        boolean result = Boolean.TRUE;

        if(this.findByUserName(userName) != null) {
            result = Boolean.FALSE;
        }

        return result;
    }

    private Student findByUserName(String userName) {
        Validate.notNull(userName, "can't find student, userName must be not null");
        logger.debug(String.format("find student by userName [%s]", userName));

        return studentRepository.findByUserName(userName);
    }
}
