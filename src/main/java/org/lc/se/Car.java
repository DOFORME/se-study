package org.lc.se;

import java.io.Serializable;

public class Car implements Serializable {

    private static final long serialVersionUID = 232634697114126727L;

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
