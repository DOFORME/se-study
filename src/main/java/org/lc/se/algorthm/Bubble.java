package org.lc.se.algorthm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class Bubble {
    private static int[] arr = {5, 6, 1, 3, 4, 9, 8};
    @Test
    void test1() {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
