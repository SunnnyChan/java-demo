package me.sunny.demo.basics.ioc.entity;

public class TClass {
  private String cname;// 班级名称
  private Teacher teacher; // 老师

  public String getCname() {
    return cname;
  }

  public void setCname(String cname) {
    this.cname = cname;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }
}