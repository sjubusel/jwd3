package by.epamtc.jwd.busel.assignment03;

import java.util.Arrays;
import java.util.Random;

public class Task03 {
    private static Random random = new Random(System.nanoTime());

    private static int getCubePosition() {
        return random.nextInt(10);
    }

    private static int getCubePositionWhichIsNot(int firstCubePosition) {
        int secondCubePosition;
        do {
            secondCubePosition = random.nextInt(10);
        } while (secondCubePosition == firstCubePosition);
        return secondCubePosition;
    }

    private static int getRandomCubeValue() {
        return random.nextInt(6) + 1;
    }

    private static int getRandomCubeValueWhichCorrelatesTo(int firstCubeValue,
            int positionDifference) {
        if (positionDifference % 3 == 0) {
            return firstCubeValue;
        }
        int secondCubeValue;
        do {
            secondCubeValue = getRandomCubeValue();
        } while (firstCubeValue + secondCubeValue >= 10);
        return secondCubeValue;
    }

    private static int[] getInitialCode(int firstCubePosition,
            int secondCubePosition, int firstCubeValue, int secondCubeValue) {
        int[] code = new int[10];
        code[firstCubePosition] = firstCubeValue;
        code[secondCubePosition] = secondCubeValue;
        return code;
    }

    private static void findOpeningCode(int[] code, int firstPosition,
            int secondPosition, int firstCubeValue, int secondCubeValue,
            int positionDifference) {
        int sign = positionDifference % 3;
        int nextValueToAdd = 0;
        switch (sign) {
            case 0:
                nextValueToAdd = (10 - firstCubeValue) / 2;
                break;
            case 1:
                nextValueToAdd = secondCubeValue;
                break;
            case 2:
                nextValueToAdd = 10 - firstCubeValue - secondCubeValue;
                break;
        }
        fillMiddle(code, firstPosition, secondPosition, nextValueToAdd);
        fillLeft(code, firstPosition);
        fillRight(code, secondPosition);
    }

    private static void fillMiddle(int[] code, int firstPosition, int secondPosition, int nextValueToAdd) {
        int nextPosition = firstPosition + 1;
        if (nextPosition < secondPosition) {
            code[nextPosition++] = nextValueToAdd;
            for (int i = nextPosition; i < secondPosition; i++) {
                code[i] = 10 - code[i - 1] - code[i - 2];
            }
        }
    }

    private static void fillRight(int[] code, int secondPosition) {
        if (secondPosition < (code.length - 1)) {
            for (int i = secondPosition + 1; i < code.length; i++) {
                code[i] = 10 - code[i - 1] - code[i - 2];
            }
        }
    }

    private static void fillLeft(int[] code, int firstPosition) {
        if (firstPosition > 0) {
            for (int i = firstPosition - 1; i >= 0; i--) {
                code[i] = 10 - code[i + 1] - code[i + 2];
            }
        }
    }

    public static void main(String[] args) {
        int firstCubePosition = getCubePosition();
        int secondCubePosition = getCubePositionWhichIsNot(firstCubePosition);
        if (secondCubePosition < firstCubePosition) {
            int temp = secondCubePosition;
            secondCubePosition = firstCubePosition;
            firstCubePosition = temp;
        }
        int positionDifference = secondCubePosition - firstCubePosition;

        int firstCubeValue = getRandomCubeValue();
        int secondCubeValue = getRandomCubeValueWhichCorrelatesTo
                (firstCubeValue, positionDifference);

        int[] code = getInitialCode(firstCubePosition, secondCubePosition,
                firstCubeValue, secondCubeValue);
        System.out.printf("Initial \"openable\" code:\n%s\n",
                Arrays.toString(code));

        findOpeningCode(code, firstCubePosition, secondCubePosition,
                firstCubeValue, secondCubeValue, positionDifference);
        System.out.printf("Code, which can open a superdoorlock:\n%s\n",
                Arrays.toString(code));
    }
}
