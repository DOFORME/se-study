package org.lc.se;

import org.junit.Test;

import java.util.Arrays;

public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello World and GitHub!");
    }

    @Test
    public void test() {
        int[] arr = {2, 8, 2, 4, 2, 3, 5};
        qSort(arr, 0, 5);
    }


    public static void qSort(int[] arr, int head, int tail) {
        if (head >= tail || arr == null || arr.length <= 1) {
            return;
        }
        int i = head, j = tail, pivot = arr[(head + tail) / 2];
        while (i <= j) {
            while (arr[i] < pivot) {
                ++i;
            }
            while (arr[j] > pivot) {
                --j;
            }
            if (i < j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                ++i;
                --j;
                System.out.println("交换：" + Arrays.toString(arr));
            } else if (i == j) {
                ++i;
            }
        }
        qSort(arr, head, j);
        qSort(arr, i, tail);
    }
}
