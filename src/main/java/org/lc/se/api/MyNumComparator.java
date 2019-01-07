package org.lc.se.api;

import java.util.Comparator;

public class MyNumComparator implements Comparator<MyNum> {

    @Override
    public int compare(MyNum o1, MyNum o2) {
        if (o1 == null || o2 == null) {
            throw new RuntimeException("can't compare null");
        }
        if (o1.getNum() < o2.getNum()) {
            return 1;
        } else if(o1.getNum() == o2.getNum()) {
            return 0;
        } else {
            return -1;
        }
    }
}
