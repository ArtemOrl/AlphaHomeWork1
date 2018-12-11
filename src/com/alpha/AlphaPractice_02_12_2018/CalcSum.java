package com.alpha.AlphaPractice_02_12_2018;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class CalcSum extends RecursiveTask<Integer> {
    private int UNIT_SIZE = 15;
    private int[] values;
    private int startPos, endPos;

    public CalcSum(int[] values) {
        this(values, 0, values.length);
    }

    public CalcSum(int[] values, int startPos, int endPos) {
        this.values = values;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    protected Integer compute() {
        final int currentSize = endPos - startPos;
        if (currentSize <= UNIT_SIZE) {
            return computeSum();
        } else {
            int center = currentSize / 2;
            int leftEnd = startPos + center;
            CalcSum leftSum = new CalcSum(values, startPos, leftEnd);
            int rightStart = startPos + center + 1;
            CalcSum rightSum = new CalcSum(values, rightStart, endPos);
            return (rightSum.compute() + leftSum.join());
        }
    }

    private Integer computeSum() {
        int sum = 0;
        for (int i = startPos; i < endPos; i++) {
            sum += values[i];
        }
        System.out.println("Sum(" + startPos + "-" + endPos + "):" + sum);
        return sum;
    }

    public static void main(String[] args) {
        int[] intArray = new int[100];
        java.util.Random randomValues = new java.util.Random();
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = randomValues.nextInt(10);
        }
        ForkJoinPool pool = new ForkJoinPool();
        CalcSum calculator = new CalcSum(intArray);
        System.out.println(pool.invoke(calculator));
    }
}
