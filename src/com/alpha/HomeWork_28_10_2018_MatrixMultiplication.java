package com.alpha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

// Использовано 4 метода:
// 1. Простое перемножение О(n^3)
// 2. Перемножение с использованием транспонирования матриц (на вид тоже O(n^3),
// но работает реально быстрее - архитектура процессоров и все такое
// 3. Метод Штрассена (взят из Википедии)
// 4. Метод Штрассена с использованием ForkJoinPool и RecursiveTask
// хитрый план запустить вычисление умножения по методу Штрассена в много потоков!!!

// Результаты такие:
// лучше всего работает метод с транспонированием на матрицах с длиной стороны до 1000 элементов
// дальше Штрассен
// А вот чем больше матрицы, тем лучше себя проявляет многопоточный режим
// Причем я знаю что там многопоточность сама настроена, но как я не знаю
// Но все равно на больших матрицах многопоточный Штрассен - самый быстрый


public class HomeWork_28_10_2018_MatrixMultiplication {

    // стандартная методика сложность O(n^3)

    public static int[][] multiply(int[][] a, int[][] b) {

        int rowsA = a.length;
        int columnsB = b[0].length;
        int columnsA_rowsB = a[0].length;

        int[][] c = new int[rowsA][columnsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < columnsB; j++) {
                int sum = 0;
                for (int k = 0; k < columnsA_rowsB; k++) {
                    sum += a[i][k] * b[k][j];
                }
                c[i][j] = sum;
            }
        }

        return c;
    }

    // Умножение матриц с использованием транспонирования (получается побыстрее,
    // но теоретическая сложность не падает)

    public static int[][] multiplyTransposed(int[][] a, int[][] b) {

        int rowsA = a.length;
        int columnsB = b[0].length;
        int columnsA_rowsB = a[0].length;

        int columnB[] = new int[columnsA_rowsB];
        int[][] c = new int[rowsA][columnsB];


        for (int j = 0; j < columnsB; j++) {
            for (int k = 0; k < columnsA_rowsB; k++) {
                columnB[k] = b[k][j];
            }

            for (int i = 0; i < rowsA; i++) {
                int rowA[] = a[i];
                int sum = 0;
                for (int k = 0; k < columnsA_rowsB; k++) {
                    sum += rowA[k] * columnB[k];
                }
                c[i][j] = sum;
            }
        }

        return c;
    }

    // тут просто суммирование двух матриц (вспомогательный метод для метода Штрассена)

    private static int[][] summation(int[][] a, int[][] b) {

        int n = a.length;
        int m = a[0].length;
        int[][] c = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }

    // вычитание двух матриц (вспомогательный метод для метода Штрассена)

    private static int[][] subtraction(int[][] a, int[][] b) {

        int n = a.length;
        int m = a[0].length;
        int[][] c = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }

    // заквадратим матрицы (вспомогательный метод для метода Штрассена)
    // приведем матрицы к нужному размеру
    // public static void arraycopy(Object src,  int srcPos, Object dest, int destPos, int length)

    private static int[][] addition2SquareMatrix(int[][] a, int n) {

        int[][] result = new int[n][n];

        for (int i = 0; i < a.length; i++) {
            System.arraycopy(a[i], 0, result[i], 0, a[i].length);
        }
        return result;
    }

    // получение подМатрицы (делим на части большую матрицу чтобы запустить процесс по разным нитям)

    private static int[][] getSubmatrix(int[][] a, int n, int m) {
        int[][] result = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(a[i], 0, result[i], 0, m);
        }
        return result;
    }

    // разделение матрицы на 4 части (вспомогательный метод для метода Штрассена)

    private static void splitMatrix(int[][] a, int[][] a11, int[][] a12, int[][] a21, int[][] a22) {

        int n = a.length >> 1;

        for (int i = 0; i < n; i++) {
            System.arraycopy(a[i], 0, a11[i], 0, n);
            System.arraycopy(a[i], n, a12[i], 0, n);
            System.arraycopy(a[i + n], 0, a21[i], 0, n);
            System.arraycopy(a[i + n], n, a22[i], 0, n);
        }
    }

    // сборка матрицы обратно (вспомогательный метод для метода Штрассена)

    private static int[][] collectMatrix(int[][] a11, int[][] a12, int[][] a21, int[][] a22) {

        int n = a11.length;
        int[][] a = new int[n << 1][n << 1];

        for (int i = 0; i < n; i++) {
            System.arraycopy(a11[i], 0, a[i], 0, n);
            System.arraycopy(a12[i], 0, a[i], n, n);
            System.arraycopy(a21[i], 0, a[i + n], 0, n);
            System.arraycopy(a22[i], 0, a[i + n], n, n);
        }

        return a;
    }

    // многопоточное умножение матрицы с помощью алгоритма Штрассена
    // Идею я спер конечно, изначально делал просто метод Штрассена без разделения
    // матрицы на части и многопоточности))
    // RecursiveTask потому что он сам распределяет нагрузку
    // (самому распределить мне сложно пока)


    private static class myRecursiveTask extends RecursiveTask<int[][]> {

        int n;
        int[][] a;
        int[][] b;

        public myRecursiveTask(int[][] a, int[][] b, int n) {
            this.a = a;
            this.b = b;
            this.n = n;
        }


        @Override
        protected int[][] compute() {
            if (n <= 128) {
                return multiplyTransposed(a, b);
            }

            n >>= 1;

            int[][] a11 = new int[n][n];
            int[][] a12 = new int[n][n];
            int[][] a21 = new int[n][n];
            int[][] a22 = new int[n][n];

            int[][] b11 = new int[n][n];
            int[][] b12 = new int[n][n];
            int[][] b21 = new int[n][n];
            int[][] b22 = new int[n][n];

            splitMatrix(a, a11, a12, a21, a22);
            splitMatrix(b, b11, b12, b21, b22);

            myRecursiveTask task_p1 = new myRecursiveTask(summation(a11, a22), summation(b11, b22), n);
            myRecursiveTask task_p2 = new myRecursiveTask(summation(a21, a22), b11, n);
            myRecursiveTask task_p3 = new myRecursiveTask(a11, subtraction(b12, b22), n);
            myRecursiveTask task_p4 = new myRecursiveTask(a22, subtraction(b21, b11), n);
            myRecursiveTask task_p5 = new myRecursiveTask(summation(a11, a12), b22, n);
            myRecursiveTask task_p6 = new myRecursiveTask(subtraction(a21, a11), summation(b11, b12), n);
            myRecursiveTask task_p7 = new myRecursiveTask(subtraction(a12, a22), summation(b21, b22), n);

            task_p1.fork();
            task_p2.fork();
            task_p3.fork();
            task_p4.fork();
            task_p5.fork();
            task_p6.fork();
            task_p7.fork();

            int[][] p1 = task_p1.join();
            int[][] p2 = task_p2.join();
            int[][] p3 = task_p3.join();
            int[][] p4 = task_p4.join();
            int[][] p5 = task_p5.join();
            int[][] p6 = task_p6.join();
            int[][] p7 = task_p7.join();

            int[][] c11 = summation(summation(p1, p4), subtraction(p7, p5));
            int[][] c12 = summation(p3, p5);
            int[][] c21 = summation(p2, p4);
            int[][] c22 = summation(subtraction(p1, p2), summation(p3, p6));

            return collectMatrix(c11, c12, c21, c22);
        }

    }

    // Запуск многопоточного добра

    public static int[][] multiStrassenForkJoin(int[][] a, int[][] b) {

        int nn = getNewDimension(a, b); // нашли, насколько надо заквадратить
        int[][] a_n = addition2SquareMatrix(a, nn);
        int[][] b_n = addition2SquareMatrix(b, nn);

        myRecursiveTask task = new myRecursiveTask(a_n, b_n, nn);
        ForkJoinPool pool = new ForkJoinPool();
        int[][] fastFJ = pool.invoke(task);

        return getSubmatrix(fastFJ, a.length, b[0].length);
    }

    // Просто складываем матрицы с помощью алгоритма Штрассена без многопоточности


    private static int[][] multiStrassen(int[][] a, int[][] b, int n) {
        if (n <= 128) {
            return multiplyTransposed(a, b);
        }

        n = n >> 1;
        ArrayList<Object> objects = new ArrayList<>();

        int[][] a11 = new int[n][n];
        int[][] a12 = new int[n][n];
        int[][] a21 = new int[n][n];
        int[][] a22 = new int[n][n];

        int[][] b11 = new int[n][n];
        int[][] b12 = new int[n][n];
        int[][] b21 = new int[n][n];
        int[][] b22 = new int[n][n];

        splitMatrix(a, a11, a12, a21, a22);
        splitMatrix(b, b11, b12, b21, b22);

        int[][] p1 = multiStrassen(summation(a11, a22), summation(b11, b22), n);
        int[][] p2 = multiStrassen(summation(a21, a22), b11, n);
        int[][] p3 = multiStrassen(a11, subtraction(b12, b22), n);
        int[][] p4 = multiStrassen(a22, subtraction(b21, b11), n);
        int[][] p5 = multiStrassen(summation(a11, a12), b22, n);
        int[][] p6 = multiStrassen(subtraction(a21, a11), summation(b11, b12), n);
        int[][] p7 = multiStrassen(subtraction(a12, a22), summation(b21, b22), n);

        int[][] c11 = summation(summation(p1, p4), subtraction(p7, p5));
        int[][] c12 = summation(p3, p5);
        int[][] c21 = summation(p2, p4);
        int[][] c22 = summation(subtraction(p1, p2), summation(p3, p6));

        return collectMatrix(c11, c12, c21, c22);
    }


    // алгоритм работает только с квадратными матрицами,
    // размерность которых равна степени 2, эта функция приводит
    // исходные матрицы к такому виду
    private static int log2(int x) {
        int result = 1;
        while ((x >>= 1) != 0) {
            result++;
        }

        return result;
    }

    // найдем, насколько надо заквадратить матрицу

    private static int getNewDimension(int[][] a, int[][] b) {
        return 1 << log2(Collections.max(Arrays.asList(a.length, a[0].length, b[0].length)));
    }

    // создадим рандомную матрицу

    public static int[][] randomMatrix(int m, int n) {
        int[][] a = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = new Random().nextInt(100);
            }
        }
        return a;
    }

    // напечатаем матрицу

    public static void printMatrix(int[][] a) {
        for (int i = 0; i < a[0].length; i++) {
            System.out.print("-------");
        }
        System.out.println();
        for (int[] anA : a) {
            System.out.print("|");
            for (int anAnA : anA) {
                System.out.printf("%4d |", anAnA);
            }

            System.out.println();
            for (int i = 0; i < a[0].length; i++) {
                System.out.print("-------");
            }
            System.out.println();
        }
    }

    // тест

    public static void test(int n, int m, int l) {

        int[][] a = randomMatrix(n, l);
        int[][] b = randomMatrix(l, m);
        long start, end;

        //  TEST 1
        start = System.currentTimeMillis();
        int[][] matrixByStrassenFJ = multiStrassenForkJoin(a, b);
        end = System.currentTimeMillis();
        System.out.printf("Strassen Fork-Join Multiply [A:%dx%d; B:%dx%d]: \tElapsed: %dms\n", n, l, l, m, end - start);

        //  TEST 2
        start = System.currentTimeMillis();
        int nn = getNewDimension(a, b);

        int[][] a_n = addition2SquareMatrix(a, nn);
        int[][] b_n = addition2SquareMatrix(b, nn);

        int[][] temp = multiStrassen(a_n, b_n, nn);
        int[][] matrixByStrassen = getSubmatrix(temp, n, m);
        end = System.currentTimeMillis();
        System.out.printf("Strassen Multiply [A:%dx%d; B:%dx%d]: \tElapsed: %dms\n", n, l, l, m, end - start);

        //  TEST 3
        start = System.currentTimeMillis();
        int[][] matrixByUsual = multiply(a, b);
        end = System.currentTimeMillis();
        System.out.printf("Usual Multiply [A:%dx%d; B:%dx%d]: \tElapsed: %dms\n", n, l, l, m, end - start);

        //  TEST 4
        start = System.currentTimeMillis();
        int[][] matrixByUsualTransposed = multiplyTransposed(a, b);
        end = System.currentTimeMillis();
        System.out.printf("Usual Multiply Transposed [A:%dx%d; B:%dx%d]: \tElapsed: %dms\n", n, l, l, m, end - start);

        System.out.println("Matrices are equal: " + Arrays.deepEquals(matrixByStrassenFJ, matrixByStrassen));
        System.out.println("Matrices are equal: " + Arrays.deepEquals(matrixByStrassenFJ, matrixByUsual));
        System.out.println("Matrices are equal: " + Arrays.deepEquals(matrixByStrassenFJ, matrixByUsualTransposed));

    }




    public static void main(String[] args) {


        test(100, 70, 100);

//        Результат
//        Strassen Fork-Join Multiply [A:100x100; B:100x70]: 	Elapsed: 17ms
//        Strassen Multiply [A:100x100; B:100x70]: 	Elapsed: 1ms
//        Usual Multiply [A:100x100; B:100x70]: 	Elapsed: 4ms
//        Usual Multiply Transposed [A:100x100; B:100x70]: 	Elapsed: 0ms



        test(1000, 700, 1000);

//        Результат
//        Strassen Fork-Join Multiply [A:1000x1000; B:1000x700]: 	Elapsed: 317ms
//        Strassen Multiply [A:1000x1000; B:1000x700]: 	Elapsed: 495ms
//        Usual Multiply [A:1000x1000; B:1000x700]: 	Elapsed: 2865ms
//        Usual Multiply Transposed [A:1000x1000; B:1000x700]: 	Elapsed: 298ms


        test(2000, 1500, 2000);

//        Результат
//        Strassen Fork-Join Multiply [A:2000x2000; B:2000x1500]: 	Elapsed: 1433ms
//        Strassen Multiply [A:2000x2000; B:2000x1500]: 	Elapsed: 2505ms
//        Usual Multiply [A:2000x2000; B:2000x1500]: 	Elapsed: 63540ms
//        Usual Multiply Transposed [A:2000x2000; B:2000x1500]: 	Elapsed: 3989ms

    }
}
