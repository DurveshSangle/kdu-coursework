package kdu.backend.hw5.q3;

import kdu.backend.hw5.Logging;

import java.util.Scanner;

public class FactorsAndFactorial {
    private static final Logging log = new Logging();
    private static long factorialOfNumber(int number) {
        if (number == 0 || number == 1) {
            return 1;
        } else {
            return number * factorialOfNumber(number - 1);
        }
    }

    private static String factorsOfNumber(int number) {
        StringBuilder msg = new StringBuilder("Factors of " + number + " are: ");
        for (int i = 1; i <= number; ++i) {
            if (number % i == 0) {
                msg.append(i);
                msg.append(" ");
            }
        }
        return msg.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        log.logInfo("Please enter a number: ");
        int number = sc.nextInt();
        sc.close();

        Thread factorialThread = new Thread(() -> {
            long factorialResult = factorialOfNumber(number);
            String msg = "Factorial of " + number + " is: " + factorialResult;
            log.logInfo(msg);
        });

        Thread factorsThread = new Thread(() -> {
            String msg = factorsOfNumber(number);
            log.logInfo(msg);
        });

        factorialThread.start();
        factorsThread.start();

        try {
            factorialThread.join();
            factorsThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.logError("Thread Interrupted");
        }
    }
}
