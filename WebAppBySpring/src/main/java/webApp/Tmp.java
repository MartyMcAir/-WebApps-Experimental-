package webApp;

import java.util.Arrays;

public class Tmp {
    public static void main(String[] args) {
        // https://www.youtube.com/watch?v=Nn-kRhn2znw&list=PLAma_mKffTOR5o0WNHnY0mTjKxnCgSXrZ&index=19
        // alishev - рассматривает ошибки и как PVS Studio их находит _ до 5ой минуты
        int[][] matrix = new int[10][10];

        for (int i = 0, k = 0; i < matrix.length; i++) {

            // https://www.viva64.com/ru/pvs-studio-download/?promo=alishev - PVS Studio
            // эту ошибку увидет
//            for (int j = 0; j < matrix[i].length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = k;
                k++;
            }
        }

//        System.out.println(Arrays.toString(matrix));
        for (int[] ints : matrix) {
            for (int anInt : ints) System.out.println(anInt); // 0 - 99
        }
    }
}