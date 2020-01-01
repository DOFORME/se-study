package org.lc.se.genericity;


public class GenericClass<T> {

    private T field1;

    public static void main(String[] args) {
        showGenericType();
        genericMethod(1);
        String str = "hello";
        swap(str);
        System.out.println(str);
    }

    private static void showGenericType() {
        GenericClass<String> gcs = new GenericClass<>();
        gcs.setField1("abc");
        System.out.println(gcs.field1.getClass());
        System.out.println(gcs.getField1());

        genericMethod("abc");
    }

    public T getField1() {
        return field1;
    }

    public void setField1(T field1) {
        this.field1 = field1;
    }

    public static <T> void genericMethod(T t) {
        System.out.println("方法类型为：" + t.getClass());
    }


    private static void swap(Integer x, Integer y) {
        int temp = x;
        x = y;
        y = temp;
    }

    private static void swap(String str) {
        str = "java";
    }


    private static void swap(StringBuilder sb) {
        sb = new StringBuilder("new sb");
        sb.append("++");
    }



}
