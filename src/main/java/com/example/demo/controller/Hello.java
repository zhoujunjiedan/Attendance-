package com.example.demo.controller;

import com.example.demo.bean.*;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Controller
public class Hello {

    private String name1;
    private String subject1;


    @Autowired

    private DemoService demoService;


    @GetMapping("/index")
    public String getName(Model model) {

        List<Demo> d = demoService.getname();
        List<Subject> subject=demoService.subject();
        List<Object> name = new ArrayList<>();
        List<Object> s=new ArrayList<>();
        for (int j=0;j<subject.size();j++){
            s.add(j,subject.get(j));
        }
        for (int i = 0; i < d.size(); i++) {
            name.add(i, d.get(i).getName());
        }
        model.addAttribute("loadName", name);
        model.addAttribute("subject1", s);
        return "index";
    }

    @GetMapping("/About")
    public String About() {

        return "About";
    }




    @GetMapping("/imgcapture")
    public String imgcapture() {
        return "imgcapture";
    }
    @PostMapping("/InsertStudent")
    public  String InsertStudent(Student student){
        System.out.println(student.getName());
       student.setName(student.getName().replaceAll(" ","_"));
        demoService.InsertStudent(student);


        return "redirect:/imgcapture";
    }

    @PostMapping("/imgcapture1")
    @ResponseBody
    public void imgcapture1(String imgurl,String Stuname) throws IOException {
        String a = imgurl.replace("data:image/png;base64,", "");
        System.out.println(Stuname);
        Base64.Decoder decoder = Base64.getDecoder();
        FileOutputStream out = null;
        byte[] b = decoder.decode(a);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {//调整异常数据
                b[i] += 256;
            }
        }
        //生成文件
        URL path=this.getClass().getResource("");
        String file = new File(path.getFile(),"../../../../static/labeled_images/"+Stuname).getCanonicalPath();
        if (mkDirectory(file)) {
            System.out.println(file + "建立完毕");
        }
        else{
            System.out.println(file + "建立失败！此目录或许已经存在！");
        }
        out = new FileOutputStream(file+"/1.png");
        out.write(b);
        out.flush();
        out.close();

    }

    public static boolean mkDirectory(String path) {
        File file = null;
        try {
            file = new File(path);
            if (!file.exists()) {
                return file.mkdirs();
            }
            else{
                return false;
            }
        } catch (Exception e) {
        } finally {
            file = null;
        }
        return false;
    }



    @GetMapping("/joinsubject")
    public String joinsubject() {
        return "joinsubject";
    }

    @GetMapping("/checkinsuccess")
    public String checkinsuccess() {


        return "checkinsuccess";
    }

    @PostMapping("/getId")
    @ResponseBody
    public void getId(String name) {
        // System.out.print(name);
        name1 = name;
        Stuname s = new Stuname();
        // s.setName(name);
        System.out.println(demoService.getId(name));

    }


    @PostMapping(value = "/joinsubject")
    public String insertsubject(Attendance attendance) {
        subject1 = attendance.getSubject();
        System.out.print(attendance);
        attendance.setTotal_number(1);
        demoService.insersubject(attendance);
        return "redirect:/index";
    }

    @PostMapping(value = "/checkinsuccess1")
    public String checkin(Attendance a) {
        // System.out.print(a.getSubject());
        a.setSid(Integer.parseInt(demoService.getId(name1)));
        // System.out.print(demoService.getId(name1));
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        String nowtime = sdf.format(date);
        System.out.println("现在时间：" + sdf.format(date)); // 输出已经格式化的现在时间（24小时制）
        a.setRealtime(sdf.format(date));
        a.setCount(3);
        Date subjecttime = demoService.checktime(a.getSubject());
        System.out.print(subjecttime);
        if (date.before(subjecttime)) {
            System.out.print("shabi ");
            a.setStatus("ontime");
        } else {
            System.out.print("late");
            a.setStatus("late");
        }


        demoService.checkin(a);
        return "redirect:/checkinsuccess";
    }

    @PostMapping("/testimg")
    @ResponseBody
    public void testimg(String imgurl,String Stuname) throws IOException {
        String a = imgurl.replace("data:image/png;base64,", "");
        System.out.println(Stuname);
        Base64.Decoder decoder = Base64.getDecoder();
        FileOutputStream out = null;
        byte[] b = decoder.decode(a);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {//调整异常数据
                b[i] += 256;
            }
        }
        //生成文件
        URL path=this.getClass().getResource("");
        String file = new File(path.getFile(),"../../../../static/test_images/"+Stuname).getCanonicalPath();
        if (mkDirectory1(file)) {
            System.out.println(file + "建立完毕");
        }
        else{
            System.out.println(file + "建立失败！此目录或许已经存在！");
        }
        out = new FileOutputStream(file+"/1.png");
        out.write(b);
        out.flush();
        out.close();

    }

    public static boolean mkDirectory1(String path) {
        File file = null;
        try {
            file = new File(path);
            if (!file.exists()) {
                return file.mkdirs();
            }
            else{
                return false;
            }
        } catch (Exception e) {
        } finally {
            file = null;
        }
        return false;
    }

}




