package com.example.demo.bean;

public class Attendance {
    private String subject;
    private int Sid;
    private String realtime;
    private  String status;
    private  int total_number;

    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSid() {
        return Sid;
    }

    public void setSid(int sid) {
        Sid = sid;
    }

    public String getRealtime() {
        return realtime;
    }

    public void setRealtime(String realtime) {
        this.realtime = realtime;
    }
    @Override
    public String toString() {
        return "Attendance{" +
                "subject='" + subject + '\'' +
                ", Sid=" + Sid +
                ", realtime='" + realtime + '\'' +
                ", status='" + status + '\'' +
                ", count=" + count +
                '}';
    }


}
