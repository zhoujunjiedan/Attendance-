package com.example.demo.service;

import com.example.demo.bean.Attendance;
import com.example.demo.bean.Demo;
import com.example.demo.bean.Student;
import com.example.demo.bean.Subject;
import com.example.demo.mapper.DemoMappper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class DemoService {
    @Autowired
    private DemoMappper demoMappper;
    public List<Demo> getname(){
        return demoMappper.getName();
    }
    public  String getId(String name){
        return  demoMappper.getId(name);
    }

    public void insersubject(Attendance attendance) {
        demoMappper.insertsubject(attendance);
    }
    public  void checkin(Attendance attendance){
        demoMappper.checkin(attendance);
    }

    public Date checktime(String subject){
       return demoMappper.checktime(subject);
    }

    public void InsertStudent(Student student){
        demoMappper.InsertStudent(student);
    }

    public  List<Subject> subject(){
        return  demoMappper.GetSubject();
    }
}
