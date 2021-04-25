package com.imyeego.mozart;

import android.view.View;

import androidx.arch.core.executor.ArchTaskExecutor;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testLinkedHashMap() {
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>(16, 0.75f, true);

        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");

        map.get(2);
        assertEquals("[1, 2 ,3]", map.keySet().toString());
    }

    @Test
    public void testHashMap() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(3, "4");

//        System.out.printf(map.get(3));

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        queue.add(4);
        queue.add(2);
        queue.add(1);
        queue.add(5);

//        System.out.printf("" + queue.poll());

        int[] array = {0, 1, 5, 6, 10};
//        System.out.println("" + binSearch(array, 1));

        String s1 = "123";

        System.out.println(s1.hashCode());
        System.out.println(s1.hashCode());


    }



    static int binSearch(int[] array, int target) {


        if(array == null) return -1;
        int size = array.length;
        int i = 0;
        int j = size - 1;
        while(i <= j) {
            int mid = (i + j) >> 1;
            if (array[mid] == target) return mid;
            if (array[mid] > target) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }


        return -1;
    }
}