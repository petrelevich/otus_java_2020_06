package ru.otus;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;


public class HelloOtus {
    public static void main(String[] args) {
        Range<Integer> range1 = Range.closed(0, 9);

        System.out.print("[0,9] : ");
        System.out.print("[ ");
        for(int grade : ContiguousSet.create(range1, DiscreteDomain.integers())) {
            System.out.print(grade +" ");
        }
        System.out.println("]");
    }
}
