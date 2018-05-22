package com.pratice.arithmetic.select;

/**
 * @author zakyoung
 * @Title: 选择排序
 * @Description: TODO
 * @date 2018-05-01
 */
public class SelectSort {


    public static void main(String[] args) {
        int [] a ={15,23,10,2,30,500,32,};

        SelectSort selectSort = new SelectSort();
        selectSort.selectSort(a);
    }

    private  void selectSort(int [] a) {
        int n = a.length;
        for (int k = 0; k < n - 1; k++) {
            int min = k;
            for (int i = k + 1; i < n; i++)
                if (a[i] < a[min]) min = i;
            if (k != min) {
                int temp = a[k];
                a[k] = a[min];
                a[min] = temp;
            }//end of if
        }//end of for


        //循环遍历
        for (int  sort: a
             ) {
            System.out.print(sort+"   ");
        }
    }


}
