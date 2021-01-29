package ru.vsu.cs.course3.utils;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUtils {
    public static void print(String x) {
        System.out.println(x);
    }

    public static void printFormat(String format, Object... args) {
        print(String.format(format, args));
    }

    public static int readInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static BigDecimal readBigDecimal() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextBigDecimal();
    }

    public static int promptInt(String welcome) {
        while (true) {
            try {
                System.out.print(welcome);
                return readInt();
            } catch (NumberFormatException ignored) {
            }
        }
    }

    public static String promptLine(String welcome) {
        System.out.print(welcome);
        return readLine();
    }

    public static BigDecimal promptBigDecimal(String welcome) {
        while (true) {
            try {
                System.out.print(welcome);
                return readBigDecimal();
            } catch (InputMismatchException ignored) {
            }
        }
    }
}
