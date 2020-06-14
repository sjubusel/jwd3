package by.epamtc.jwd.busel.assignment03;

import java.util.Random;
import java.util.Scanner;

public class Task01 {
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
//            array[i] = random.nextInt(13) - 6;
            array[i] = random.nextInt();
            System.out.print(array[i] + " ");
        }
        System.out.println();
        return array;
    }

    private static int getMaxValueAccordingToTaskStatement(int[] array) {
        int max = 0x80000000;
        for (int i = 0; i < array.length / 2; i++) {
            int sum = array[i] + array[array.length - i - 1];
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println("Please, insert any positive integer, which will" +
                " be \"N\"");
        int n = getPositiveIntegerFromConsole();
        int[] array = createArrayWithRandomValues(n * 2);
        int max = getMaxValueAccordingToTaskStatement(array);
        System.out.printf("MaxValue according to Task's statement is \"%d\".\n",
                max);
    }
}
