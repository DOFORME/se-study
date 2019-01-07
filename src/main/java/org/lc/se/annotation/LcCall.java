package org.lc.se.annotation;

public class LcCall {

    @Lc("my annotation")
    @SuppressWarnings("")
    public void callAnnotationLc() {
        System.out.println("test annotation");
    }
}
