package com.amm.utils;

import com.amm.entity.Student;
import com.amm.exception.StudentNotFoundException;

/**
 * Created by csw on 2016/5/30 9:12.
 * explain：
 */
public class ValidateUtil {

    public static void notNullStudentValidate(Student student) {

        if(null == student) {
            throw new StudentNotFoundException("用户名不存在");
        }
    }
}
