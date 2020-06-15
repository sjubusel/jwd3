package by.epamtc.jwd.busel.assignment03;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task05 {
    private static int getPositiveIntegerFromConsole() {
        Scanner scanner = new Scanner(System.in);
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

    private static int computeFunction(int[] array) {
        int max = 0x80000000;
        int min = 0x7fffffff;
        int lastElementToEncounter = array.length % 2 != 0 ? array.length
                                                           : array.length - 1;
        for (int i = 0; i < lastElementToEncounter; i++) {
            if (i % 2 == 0) {
                if (array[i] < min) {
                    min = array[i];
                }
            } else {
                if (array[i] > max) {
                    max = array[i];
                }
            }
        }
        return max + min;
    }

    public static void main(String[] args) {
        System.out.println("Please, insert any positive integer, which will" +
                " be \"N\"");
        int n = getPositiveIntegerFromConsole();
        int[] array = createArrayWithRandomValues(n);
        int functionResult = computeFunction(array);
        System.out.printf("The result of the function\n" +
                "\"max(A2, A4, ..., A2k)\" + \"min(A1, A3, ..., A2k+1)\"\n" +
                "applied to the array %s\n" +
                "is %d.\n", Arrays.toString(array), functionResult);
    }
}
