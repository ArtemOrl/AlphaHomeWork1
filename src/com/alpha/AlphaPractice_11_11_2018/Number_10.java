package com.alpha.AlphaPractice_11_11_2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
№10. В натуральном числе вычеркнуть цифру так, чтобы оставшееся число было как можно больше
 */
public class Number_10 {
    static int number = 38563029;


    static int maxValueAfterDeletingOfOneDigit (int number){
        // превратили число в стринг и разобрали
        String sequence = Integer.toString(number);
        String[] numbers = sequence.split("");
        List<String> num = Arrays.asList(numbers);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(num);

        char[] charArray = sequence.toCharArray();

        int result = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            // создадим arrayList который будем мучать
            ArrayList<String> arrayList1 = new ArrayList<>(arrayList);
            // удалили i-й элемент и запомнили его (но зачем???)
            String arrString = arrayList1.remove(i);

            // создали строку из массива с удаленным i-м элементом
            String tempString = new String(arrayList1.toString().toCharArray());
            // собрали его в надлежащий вид для превращения в int
            tempString = tempString.replaceAll(",", "").replaceAll(" ","")
            .replaceAll("\\[", "").replaceAll("]","");

            // и вуаля у нас число с вычеркнутой цифрой
            int temp = Integer.parseInt(tempString);

            // сравнили и дальше как-бэ ищем максимальный элемент новой последовательности
            if (temp>result) result = temp;

        }
        return result;
    }

    public static void main(String[] args) {


        System.out.println(maxValueAfterDeletingOfOneDigit(9879));
    }

//    Тренировочный день
//    static int maxValueAfterDeletingOfOneDigit (int number){
//        String sequence = Integer.toString(number);
//        String[] numbers = sequence.split("");
//        List<String> num = Arrays.asList(numbers);
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.addAll(num);
////        ArrayList<String> arrayList1 = new ArrayList<>(arrayList);
//        char[] charArray = sequence.toCharArray();
//
//        int result = 0;
//        for (int i = 0; i < arrayList.size(); i++) {
//            // создадим arrayList который будем мучать
//            ArrayList<String> arrayList1 = new ArrayList<>(arrayList);
//            // удалили i-й элемент и запомнили его (но зачем???)
//            String arrString = arrayList1.remove(i);
//            System.out.println(arrString + " arrString what removed");
////            System.out.println(arrayList1.toString());
//            // создали строку из массива с удаленным i-м элементом
//            String tempString = new String(arrayList1.toString().toCharArray());
//            // собрали его в надлежащий вид для превращения в int
//            tempString = tempString.replaceAll(",", "").replaceAll(" ","")
//                    .replaceAll("\\[", "").replaceAll("]","");
////            System.out.println(tempString);
//            // и вуаля
//            int temp = Integer.parseInt(tempString);
//            System.out.println(temp);
//            // сравнили и дальше как-бэ ищем максимальный элемент новой последовательности
//            if (temp>result) result = temp;
////            arrayList.add(i, arrString);
////            System.out.println(arrayList.toString() + " arraylist after adding");
//        }
//        return result;
//    }
}
