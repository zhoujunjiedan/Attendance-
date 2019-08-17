package com.example.demo.mapper;

import com.example.demo.bean.Attendance;
import com.example.demo.bean.Demo;
import com.example.demo.bean.Student;
import com.example.demo.bean.Subject;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
@Mapper
@Repository
public interface DemoMappper {



   @Select("select name from demo1.student")

    public List<Demo> getName();



    @Select("select s.Sid from demo1.student s where s.name=#{name}")

    public String getId(@Param("name") String name);


    @Insert("INSERT INTO demo1.attendance (subject, Sid,total_number) VALUES (#{subject},#{Sid},#{total_number})")
    public void insertsubject(Attendance attendance);

    @Update("UPDATE demo1.attendance SET realtime = #{realtime}, status = #{status},count=#{count} WHERE Sid = #{Sid} and subject=#{subject}")
 public void checkin(Attendance attendance);

    @Select("select time  from demo1.subject where subject=#{subject}")
 public Date checktime(@Param("subject") String subject);


    @Select("INSERT INTO demo1.student (Sid, name,sex) VALUES (#{Sid},#{name},#{sex})")
    public  void InsertStudent(Student student);

    @Select("SELECT * FROM demo1.subject")
    public  List<Subject> GetSubject();



}
