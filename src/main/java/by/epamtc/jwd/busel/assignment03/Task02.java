package by.epamtc.jwd.busel.assignment03;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task02 {
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
        }
        return array;
    }

    private static int[] getNewArrayWithDeletedMinElement(int[] array) {
        int min = 0x7fffffff;
        int counterOfMinValues = 1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                counterOfMinValues = 1;
            } else if (array[i] == min) {
                counterOfMinValues++;
            }
        }
        return createNewArrayWithoutMinElements(array, min, counterOfMinValues);
    }

    private static int[] createNewArrayWithoutMinElements(int[] array, int min,
            int minValuesQuantity) {
        int[] modifiedArray = new int[array.length - minValuesQuantity];
        int index = 0;
        for (int element : array) {
            if (element != min) {
                modifiedArray[index++] = element;
            }
        }
        return modifiedArray;
    }

    public static void main(String[] args) {
        System.out.println("Please, insert any positive integer, which will" +
                " be \"N\"");
        int n = getPositiveIntegerFromConsole();
        int[] array = createArrayWithRandomValues(n);
        int[] newArray = getNewArrayWithDeletedMinElement(array);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(newArray));
    }
}
