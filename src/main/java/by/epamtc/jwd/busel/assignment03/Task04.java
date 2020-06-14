package by.epamtc.jwd.busel.assignment03;

import java.util.*;

public class Task04 {
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
        Arrays.sort(array);
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

    public static void main(String[] args) {
        System.out.println("Please, insert any positive integer, which will" +
                " be \"N\"");
        int n = getPositiveIntegerFromConsole();
        int[] array = createArrayWithRandomValues(n);
        int mostFrequentIntByArray = findMostFrequentInt(array);
        int checkingValue = findMostFrequentIntCheckingByMap(array);
        System.out.printf("Array:\n%s\n", Arrays.toString(array));
        System.out.printf("The most frequent element of the array: %d\n",
                mostFrequentIntByArray);
        System.out.printf("Checking value by HashMap: %d\n", checkingValue);
    }
}
