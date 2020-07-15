package by.epamtc.jwd.busel.assignment03;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Task04 {
    private static Scanner scanner = new Scanner(System.in);

    private static int receivePositiveIntegerFromConsole() {
        while (true) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number > 0) {
                    return number;
                } else {
                    System.out.printf("Non-positive number \"%d\"\n", number);
                }
            } else {
                System.out.printf("Invalid input \"%s\"\n", scanner.next());
            }
        }
    }

    private static int[] createArrayWithRandomValues(int arrayLength) {
        int[] array = new int[arrayLength];
        Random random = new Random(System.nanoTime());
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(21) - 10;
//            array[i] = random.nextInt();
        }
        return array;
    }

    private static int findMostFrequentIntCheckingByMap(int[] array) {
        HashMap<Integer, Integer> checkingMap = new HashMap<>();
        for (int element : array) {
            if (checkingMap.containsKey(element)) {
                checkingMap.put(element, checkingMap.get(element) + 1);
            } else {
                checkingMap.put(element, 1);
            }
        }
        int number = Integer.MAX_VALUE;
        int counter = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : checkingMap.entrySet()) {
            if (entry.getValue() > counter) {
                number = entry.getKey();
                counter = entry.getValue();
            } else if (entry.getValue() == counter && entry.getKey() < number) {
                number = entry.getKey();
                counter = entry.getValue();
            }
        }
        return number;
    }

    private static int findMostFrequentInt(int[] array) {
//        Arrays.sort(array);
        quickSort(array, 0, array.length - 1);
        int lowestFrequentNumber = array[0];
        int counterLowestFrequent = 1;
        int currentCounter = 1;
        for (int i = 1; i < array.length; i++) {
            if (lowestFrequentNumber == array[i]) {
                counterLowestFrequent++;
            }
            currentCounter = (array[i] == array[i - 1])
                             ? currentCounter + 1
                             : 1;
            if (currentCounter > counterLowestFrequent) {
                lowestFrequentNumber = array[i];
                counterLowestFrequent = currentCounter;
            }
        }
        return lowestFrequentNumber;
    }

    public static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int pivot = partition(array, left, right);

            quickSort(array, left, pivot - 1);
            quickSort(array, pivot + 1, right);
        }
    }

    private static int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[right];
        array[right] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        System.out.println("Please, insert any positive integer, which will" +
                " be \"N\"");
        int n = receivePositiveIntegerFromConsole();
        int[] array = createArrayWithRandomValues(n);
        int mostFrequentIntByArray = findMostFrequentInt(array);
        int checkingValue = findMostFrequentIntCheckingByMap(array);

        System.out.printf("Array:\n%s\n", Arrays.toString(array));
        System.out.printf("The most frequent element of the array: %d\n",
                mostFrequentIntByArray);
        System.out.printf("Checking value by HashMap: %d\n", checkingValue);

        scanner.close();
    }
}
