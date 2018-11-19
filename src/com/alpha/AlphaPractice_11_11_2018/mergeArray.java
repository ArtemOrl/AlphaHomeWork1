package com.alpha.AlphaPractice_11_11_2018;

/*
№4. Даныдвамассиваx[1]6...6x[k]иy[1]6...6y[l].«Соедините»ихвмассивz[1]6...6 z[m] (m = k + l;
каждый элемент должен входить в массив z столько раз, сколько раз
он входит в общей сложности в массивы x и y). Число действий порядка m.
 */

public class mergeArray {
    static int[] arr = {1, 3, 5, 7};
    static int[] arr1 = {2, 4, 6, 8, 9, 10};
    public static void main(String[] args) {

        int[] merge = merge1(arr, arr1);

        for (int i = 0; i < merge.length; i++) {
            System.out.print(merge[i] + " ");

        }

    }

    static int[] merge (int[] arr, int[] arr1){
//        int[] mergeArr = new int[arr.length + arr1.length];
//        int n = arr.length > arr1.length ? arr.length : arr1.length;
//        int k = 0;
//        for (int i = 0; i < n; i++) {
//            if (i >= arr.length){
//                mergeArr[i] = arr1[i];
//                k++;
//            }
//
//            else if (i >= arr1.length) {
//                mergeArr[k] = arr[i];
//                k++;
//            }
//            else if (arr[i] < arr1[i]){
//                mergeArr[k] = arr[i];
//                mergeArr[k+1] = arr1[i];
//                k+=2;
//            }
//            else if (arr[i] > arr1[i]){
//                mergeArr[k] = arr1[i];
//                mergeArr[k+1] = arr[i];
//                k+=2;
//            }
//        }
//        return mergeArr;

        int[] mergeArr = new int[arr.length + arr1.length];
        int k = 0;
        int n = arr.length + arr1.length;
        for (int i = 0; i < n; i++) {
            if (k>=arr.length){
                mergeArr[i] = arr1[k];
                k++;
            }
            else if (k>=arr1.length){
                mergeArr[i] = arr[k];
                k++;
            }

            else if (k>=arr1.length-1 && k>=arr.length-1) break;
            else if (arr[k] > arr1[k]){
                mergeArr[i] = arr1[k];
                mergeArr[i+1] = arr[k];
                i++;
                k++;
            }
            else if (arr[k] < arr1[k]){
                mergeArr[i] = arr[k];
                mergeArr[i+1] = arr1[k];
                i++;
                k++;
            }

        }
        return mergeArr;


    }

    static int[] merge1 (int[] a, int[] b){
        int[] mergeArr = new int[a.length + b.length];
        int ai = 0, bi = 0;
        for (int i = 0; i < mergeArr.length; i++) {
            if (ai >= a.length) mergeArr[i] = b[bi++];
            else if (bi >= b.length) mergeArr[i] = a[ai++];
            else mergeArr[i] = (a[ai] < b[bi]) ? a[ai++] : b[bi++];
        }
        return mergeArr;
    }
}
