package com.wondertek.baiying.util.排序;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by wd on 2018/1/23.
 */
public class ArraySort {

    public static void main(String[] args) {
        int[] array = {5,2,23,45,1,22,11,4,6};
        //Arrays.sort(array);
        //int[] list = insertionSort(array);
        int[] list = insertSort(array);
//        int[] list = bubbleSort(array);
        for (int i = 0;i < list.length;i++) {
            System.out.println(list[i]);
        }
        //System.out.println(1 % 2);
    }

    /**
     * 归并排序测试
     */
    @Test
    public void mergeSortTest() {
        int[] array = {5,2,23,45,1,22,11,4,6};
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 快速排序测试
     */
    @Test
    public void quickSortTest() {
        int[] array = {5,2,23,45,1,22,11,4,6,-1};
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 插入排序
     * @param list
     * @return
     */
    public static int[] insertionSort(int[] list) {
        Arrays.sort(list);
        for (int i =1;i<list.length;i++) {
            int currentElement = list[i];
            int k ;
            for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
                list[k + 1] = list[k];
            }
            list[k+1] = currentElement;
        }
        return list;
    }

    /**
     * 插入排序
     * @param list
     * @return
     */
    public static int[] insertSort(int[] list) {
        int j;
        //从下标为1的位置选择合适的位置插入,下标为0的元素默认是有序的
        for (int i = 0; i < list.length; i++) {
            int temp = list[i];
            j = i;
            while (j > 0 && temp < list[j-1]) {
                list[j] = list[j - 1];
                j--;
            }
            list[j] = temp;
        }
        return list;
    }

    /**
     * 冒泡排序
     * @param list
     * @return
     */
    public static int[] bubbleSort(int[] list) {
        boolean needNextPass = true;
        for (int k = 1; k < list.length && needNextPass; k++) {
            needNextPass = false;
            for (int i = 0; i < list.length - k; i++) {
                if (list[i] > list[i + 1]) {
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    needNextPass = true;
                }
            }
        }
        return list;
    }

    /**
     * 归并排序实现
     * @param list
     * @return
     */
    public static void mergeSort(int[] list) {

        if (list.length > 1) {
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);

            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            merge(firstHalf, secondHalf, list);
        }

        //System.out.println(Arrays.toString(list));
    }

    private static void merge(int[] list1, int[] list2, int[] temp) {
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;

        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1] < list2[current2]) {
                temp[current3++] = list1[current1++];
            } else {
                temp[current3++] = list2[current2++];
            }
        }
        while (current1 < list1.length) {
            temp[current3++] = list1[current1++];
        }
        while (current2 < list2.length) {
            temp[current3++] = list2[current2++];
        }
    }


    /**
     * 快速排序实现
     * @param list
     */
    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    public static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    private static int partition(int[] list, int first, int last) {
        //选择数组的第一个元素作为主元
        int pivot = list[first];
        int low = first + 1;
        int high = last;

        while (high > low) {
            while (low <= high && list[low] <= pivot) {
                low++;
            }
            while (low <= high && list[high] > pivot) {
                high--;
            }

            if (high > low) {
                int temp = list[low];
                list[low] = list[high];
                list[high] = temp;
            }
        }

        while (high > first && list[high] >= pivot) {
            high--;
        }
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return first;
        }

    }


    public static void heap(int[] list) {

    }
}
