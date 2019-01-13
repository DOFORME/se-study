package org.lc.se.api;

public class CloneDemo {

    public static void main(String[] args) throws Exception {
        testCloneOfPackageClass();
    }

    // 浅克隆
    static void testCloneOfPackageClass() throws Exception {
        Employee e = new Employee();
        e.setAge(10);
        e.setName("e 1");
        MyNum num = new MyNum();
        num.setNum(5);
        e.setMyNum(num);
        Employee e2 = e.clone();
        e.setAge(5);
        e.setName("e 1 change");
        MyNum myNum = e.getMyNum();
        myNum.setNum(10);
        System.out.println(e2.getAge());
        System.out.println(e2.getName());
        System.out.println(e2.getMyNum().getNum());
    }
}
