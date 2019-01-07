package org.lc.se.reflect;

import java.util.Arrays;

public class ReflectTest {

    private static final String REFLECT_BEAN_FULL_NAME = "ReflectBean";

    public static void main(String[] args) throws Exception {

//        testDiffMethodOfGetClass();
//        testGetSuperClassAndInterface();
//        testGetAllConstructor();
        testGetClassAllMsg();
    }

    private Class getClassByFullName(String className) {
        Class clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    private static void testDiffMethodOfGetClass() throws Exception {
        Class cla1 = null;
        Class cla2 = null;
        Class cla3 = null;

        cla1 = Class.forName(REFLECT_BEAN_FULL_NAME);
        cla2 = new ReflectBean().getClass();
        cla3 = ReflectBean.class;

    }

    private static void testGetSuperClassAndInterface() throws Exception {
        Class clz = Class.forName(REFLECT_BEAN_FULL_NAME);

    }

    private static void testGetAllConstructor() throws Exception {
//        Class clz = Class.forName(REFLECT_BEAN_FULL_NAME);
//        Constructor[] constructors = clz.getConstructors();
//        for (Constructor c : constructors) {
//            log.warn(c.getName());
//            log.warn("parameter types has :{}", Arrays.toString(c.getParameterTypes()));
//
//            Parameter[] ps = c.getParameters();
//            if (ps != null) {
//                for (Parameter p : ps) {
//                    log.warn("constructor param :{}", p.getName());
//                    log.warn(p.toString());
//                    log.warn(Arrays.toString(p.getDeclaredAnnotations()), Arrays.toString(p.getAnnotations()));
//                }
//            }
//        }
//
//        ReflectBean bean = (ReflectBean) clz.newInstance();
//        bean.setField("test");
//        log.warn(new Gson().toJson(bean));
    }

    private static void testGetClassAllMsg() throws Exception {
        Class clz = Class.forName(REFLECT_BEAN_FULL_NAME);
        Package p = clz.getPackage();
        String simpleName = clz.getSimpleName();



    }
}
