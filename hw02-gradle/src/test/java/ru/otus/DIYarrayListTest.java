package ru.otus;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class DIYarrayListTest {
    @Test
    public void collectionsCopyTest() {
        final int SRC_LIST_FOR_COPY_TEST_SIZE = 40;
        final int DIY_LIST_SIZE = 40;

        System.out.println("Testing...");
        System.out.println("Collections.copy(DIYarrayList, ArrayList)");
        System.out.println();

        List<Integer> srcListForCopyTest;
        srcListForCopyTest = new ArrayList<>();
        for(int i = 0; i<SRC_LIST_FOR_COPY_TEST_SIZE; i++){
            srcListForCopyTest.add((int)(Math.random()*100));
        }

        System.out.println("srcListForCopyTest:");
        System.out.println("size: " + srcListForCopyTest.size());
        System.out.println(srcListForCopyTest.toString());
        System.out.println();

        List<Integer> diyList = new DIYarrayList<>();

        for(int i=0; i<DIY_LIST_SIZE; i++){
            diyList.add((int)(Math.random()*100));
        }

        System.out.println("diyList (before Collections.copy()):");
        System.out.println("size: " + diyList.size());
        System.out.println(diyList.toString());
        System.out.println();

        Collections.copy(diyList, srcListForCopyTest);

        System.out.println("diyList (after Collections.copy()):");
        System.out.println("size: " + diyList.size());
        System.out.println(diyList.toString());
        System.out.println();

        assertIterableEquals(srcListForCopyTest, diyList);

        System.out.println("Passed");
    }

    @Test
    public void collectionsAddAllTest(){
        final int DIY_LIST_SIZE = 40;

        List<Integer> diyList = new DIYarrayList<>();
        for(int i = 0; i<DIY_LIST_SIZE; i++){
            diyList.add((int)(Math.random()*100));
        }

        List<Integer> srcListForAddAllTest = new ArrayList<>(diyList);

        System.out.println("Testing...");
        System.out.println("Collections.addAll(DIYarrayList, ArrayList)");
        System.out.println();

        System.out.println("srcListForAddAllTest (before Collections.addAll()):");
        System.out.println("size: " + srcListForAddAllTest.size());
        System.out.println(srcListForAddAllTest.toString());
        System.out.println();

        System.out.println("diyList (before Collections.addAll()):");
        System.out.println("size: " + diyList.size());
        System.out.println(diyList.toString());
        System.out.println();

        Integer firstElementForAddAllTest = (int)(Math.random()*100);
        Integer secondElementForAddAllTest = (int)(Math.random()*100);
        Integer thirdElementForAddAllTest = (int)(Math.random()*100);

        System.out.println("firstElementForAddAllTest: " + firstElementForAddAllTest);
        System.out.println("secondElementForAddAllTest: " + secondElementForAddAllTest);
        System.out.println("thirdElementForAddAllTest: " + thirdElementForAddAllTest);
        System.out.println();

        Collections.addAll(srcListForAddAllTest, firstElementForAddAllTest, secondElementForAddAllTest, thirdElementForAddAllTest);
        Collections.addAll(diyList, firstElementForAddAllTest, secondElementForAddAllTest, thirdElementForAddAllTest);

        System.out.println("srcListForAddAllTest (after Collections.addAll()):");
        System.out.println("size: " + diyList.size());
        System.out.println(diyList.toString());
        System.out.println();

        System.out.println("diyList (after Collections.addAll()):");
        System.out.println("size: " + diyList.size());
        System.out.println(diyList.toString());
        System.out.println();

        assertIterableEquals(srcListForAddAllTest, diyList);

        System.out.println("Passed");
    }


    @Test
    public void collectionsSortTest(){
        final int DIY_LIST_SIZE = 40;

        List<Integer> diyList = new DIYarrayList<>();
        for(int i = 0; i<DIY_LIST_SIZE; i++){
            diyList.add((int)(Math.random()*100));
        }

        List<Integer> srcListForSortTest = new ArrayList<>(diyList);

        System.out.println("Testing...");
        System.out.println("Collections.sort(DIYarrayList, Comparator.reverseOrder())");
        System.out.println();

        System.out.println("srcListForAddAllTest (before Collections.sort()):");
        System.out.println("size: " + srcListForSortTest.size());
        System.out.println(srcListForSortTest.toString());
        System.out.println();

        System.out.println("diyList (before Collections.sort()):");
        System.out.println("size: " + diyList.size());
        System.out.println(diyList.toString());
        System.out.println();

        Collections.sort(srcListForSortTest, Comparator.reverseOrder());
        Collections.sort(diyList, Comparator.reverseOrder());

        System.out.println("srcListForAddAllTest (after Collections.sort()):");
        System.out.println("size: " + srcListForSortTest.size());
        System.out.println(srcListForSortTest.toString());
        System.out.println();

        System.out.println("diyList (after Collections.sort()):");
        System.out.println("size: " + diyList.size());
        System.out.println(diyList.toString());
        System.out.println();

        assertIterableEquals(srcListForSortTest, diyList);

        System.out.println("Passed");

    }
}