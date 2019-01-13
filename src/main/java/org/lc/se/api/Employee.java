package org.lc.se.api;


public class Employee implements Cloneable {

    private Integer age;

    private String name;

    private MyNum myNum;

    public Employee clone() throws CloneNotSupportedException {
        return (Employee) super.clone();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyNum getMyNum() {
        return myNum;
    }

    public void setMyNum(MyNum myNum) {
        this.myNum = myNum;
    }
}
