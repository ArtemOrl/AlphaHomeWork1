package com.alpha.AlphaPractice_11_11_2018;
/*
№7. Дан массив коэффициентов в многочлене, нужно вывести многочлен. [3, 0, 1, 2] -> 3*x*x*x + x + 2
 */
public class Mnogochlen {
//    static int[] mass = {3, 2, 0, 1, 0};
//    static int[] mass = {3, 2, 0, 1};
    static int[] mass = {0};
    public static void main(String[] args) {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(123);
//        System.out.println(stringBuilder.toString().charAt(0));
        vivod(mass);
    }

    static void vivod(int[] mass){
        StringBuilder stringBuilder = new StringBuilder();
        int n = 0;
        boolean zero = false;
        int stepen = mass.length-1;
        for (int i = 0; i < mass.length; i++) {
            n = mass[i];
            if (n>0 || n<0) zero = true;
            if (n==0) {
                continue;
            }
            if (n==1 && i!=mass.length-1 && mass[i]==0){
                stringBuilder.append("x");
                continue;
            } else if (n==1 && i==mass.length-1 && mass[i]!=0){
                stringBuilder.append("1");
                continue;
            }
            if (n!=1)
            stringBuilder.append(n);
            for (int j = 0; j < stepen; j++) {
                stringBuilder.append("x");
            }
            n--;
            stepen--;
            stringBuilder.append("+");
        }
        if (zero==false) stringBuilder.append("0");
        String jopa = stringBuilder.toString();
        if (jopa.startsWith("1")) stringBuilder.deleteCharAt(0);
        if (jopa.endsWith("+")) stringBuilder.deleteCharAt(stringBuilder.length()-1);
//        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        System.out.println(stringBuilder.toString());
    }
}
