package org.lc.se.api;

import com.sun.istack.internal.NotNull;

public class MyNum implements Comparable<MyNum> {

    private int num;

    @Override
    public int compareTo(@NotNull MyNum o) {
        if (o.num == num) {
            return 0;
        } else if(o.num > num) {
            return 1;
        } else {
            return -1;
        }
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
