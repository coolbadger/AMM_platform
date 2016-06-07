package com.amm.controller;

import com.amm.entity.Student;
import com.amm.constant.ExceptionCode;
import com.amm.exception.InvalidOperatorException;
import com.amm.service.StudentService;
import com.amm.model.ResultModel;
import com.amm.utils.ValidateUtil;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by csw on 2016/3/16.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController extends BaseController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.POST)
    public Student create(@RequestParam("userName") String userName,
                          @RequestParam(value = "password", defaultValue = "888888", required = false) String password,
                          @RequestParam(value = "email", required = false) String email,
                          @RequestBody(required = false) Student student) {
        Validate.notNull(userName, "The userName must not be null");
        logger.debug(String.format("create student by userName [%s], password [%s]", userName, password));

        //验证用户名是否存在（或有效性验证）
        if(!studentService.isValidUserName(userName)) {
            throw new InvalidOperatorException("用户名无效，已存在");
        }

        Student saveStu = new Student(userName, password, email);

        saveStu = studentService.create(saveStu);

        return saveStu;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> listStudents() {
        System.out.println("查找所有学生");

        return studentService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student findStudent(@PathVariable Integer id) {
        Validate.notNull(id, "can't find student, id is null");
        logger.debug(String .format("find  student inform by id [%d]", id));

        Student student = studentService.findOne(id);

        ValidateUtil.notNullStudentValidate(student);

        return student;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        Validate.notNull(id, "can't find student, id is null");
        Validate.notNull(student, "can't update student , student is null");

        student.setId(id);

        student = studentService.updateStudentInfo(student);

        return student;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultModel deleteStudent(@PathVariable Integer id) {
        Validate.notNull(id, "can't find student, id is null");

        ResultModel resultModel = null;

        Student student = studentService.deleteStudent(id);

        if(student.isDelete()) {
            resultModel = new ResultModel(ExceptionCode.DELETE_SUCCESS);
        } else {
            resultModel = new ResultModel(ExceptionCode.DELETE_FAIL);
        }
        return resultModel;
    }
}
