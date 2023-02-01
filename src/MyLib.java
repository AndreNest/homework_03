import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class MyLib {
    static void menu() {
        boolean flag = true;
        System.err.println("Программа запущена!");
        while (flag == true) {
            System.out.println("---------------------------------------------------------");
            System.out.println(" 1 - Реализовать алгоритм сортировки слиянием \n" +
                    " 2 - Удаление четных чисел из списка\n" +
                    " 3 - Найти в списке минимальное, максимальное и среднее значение\n" +
                    " 4 - Отключить программу. \n");
            int n = inputNum("Какое действие выполним? ");
            switch (n) {
                case 1:
                    sortedArrow();
                    break;
                case 2:
                    deletedNumInArrow();
                    break;
                case 3:
                    minMaxSred();
                    break;
                case 4:
                    System.err.println("Программа отключена!");
                    flag = false;
                    break;
            }

        }

    }

    static void sortedArrow() {
        int num1 = inputNum("Сколько случайных чисел будет в массиве? ");
        int minNum = inputNum("Минимальное значение случайноного числа? ");
        int maxNum = inputNum("Максимально значение случаного числа? ");
        int[] arrays = new int[num1];
        for (int i = 0; i < num1; i++) {
            arrays[i] = ThreadLocalRandom.current().nextInt(minNum,maxNum);
        }

        System.out.println(Arrays.toString(arrays));
        int[] result = mergeSort(arrays);
        System.out.println(Arrays.toString(result));
    }

    static int[] mergeSort(int[] sortArr) {
        int[] buffer1 = Arrays.copyOf(sortArr, sortArr.length);
        int[] buffer2 = new int[sortArr.length];
        int[] result = mergeSortInner(buffer1, buffer2, 0, sortArr.length);
        return result;
    }

    static int[] mergeSortInner(int[] buffer1, int[] buffer2, int startIndex, int endIndex) {
        if (startIndex >= endIndex - 1) {
            return buffer1;
        }

        int middle = startIndex + (endIndex - startIndex) / 2;
        int[] sorted1 = mergeSortInner(buffer1, buffer2, startIndex, middle);
        int[] sorted2 = mergeSortInner(buffer1, buffer2, middle, endIndex);
        int index1 = startIndex;
        int index2 = middle;
        int destIndex = startIndex;
        int[] result = sorted1 == buffer1 ? buffer2 : buffer1;
        while (index1 < middle && index2 < endIndex) {
            result[destIndex++] = sorted1[index1] < sorted2[index2]
                    ? sorted1[index1++] : sorted2[index2++];
        }
        while (index1 < middle) {
            result[destIndex++] = sorted1[index1++];
        }
        while (index2 < endIndex) {
            result[destIndex++] = sorted2[index2++];
        }
        return result;
    }

    static int inputNum(String text){
      int num = 0;
      Scanner iScaner = new Scanner(System.in);
      try {
          System.out.print(text);
          num = iScaner.nextInt();
          return num;
      }
      catch (Exception exception){
          System.out.println("Вы ввели не число! Введите число!");
      }
      return inputNum("Введите число!");
  }
    static void deletedNumInArrow(){
      int num = inputNum("Сколько случайных чисел будет в массиве? ");
      int min_num = inputNum("Минимальное значение случайного числа? ");
      int max_num = inputNum("максимальное значение случайного числа? ");
      ArrayList<Integer> listNum = new ArrayList<>(randomArray(num, min_num,max_num));
      System.out.println("Исходный массив: ");
      System.out.println(listNum);
      ArrayList<Integer> newList = new ArrayList<>();
        for (int i = 0; i < listNum.size(); i++) {
            if(listNum.get(i) % 2 != 0){
                newList.add(listNum.get(i));
            }
        }
        System.out.println("Полученный массив: ");
        System.out.println(newList);
    }

    static List randomArray(int arr_size, int min_num, int max_num) {
        List<Integer> arrays = new ArrayList<>();
        for (int i = 0; i < arr_size; i++) {
            arrays.add(ThreadLocalRandom.current().nextInt(min_num,max_num));
        }

        return arrays;
    }

    static void minMaxSred(){
        int num = inputNum("Сколько случайных чисел будет в массиве? ");
        int min_num = inputNum("Минимальное значение случайного числа? ");
        int max_num = inputNum("максимальное значение случайного числа? ");
        ArrayList arrayList = new ArrayList<>(randomArray(num, min_num, max_num));
        System.out.println(arrayList);
        int resultMin = (int) arrayList.get(0);
        int resultMax = (int) arrayList.get(0);
        double resultSred = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if(resultMin > (int) arrayList.get(i)){
                resultMin = (int) arrayList.get(i);
            }
            if(resultMax < (int) arrayList.get(i)){
                resultMax = (int) arrayList.get(i);
            }
            resultSred += (int) arrayList.get(i);
        }
        resultSred = resultSred / num;
        System.out.printf("""
                Минимальное число = %d\s
                Максимальное число = %d
                Среднее число = %.2f\n""", resultMin, resultMax, resultSred);
    }



}
