package ru.arina;

import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String args[]) {
        /* BigInteger - это класс, хранящийся в пакете java.math.
        Он представляет целые числа произвольной длины. */
        long time;
        BigInteger a = BigInteger.ZERO; // a = 0
        BigInteger b = BigInteger.ZERO; // b = 0

        // Считывание с консоли числа a
        System.out.print("Введите число a: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            a = new BigInteger(scanner.next());
            System.out.println("Введенное a = " + a);
        }

        // Считывание с консоли числа b
        System.out.print("Введите число b: ");
        scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            b = new BigInteger(scanner.next());
            System.out.println("Введенное b = " + b);
        }

        if (a.compareTo(BigInteger.ZERO) < 0 || b.compareTo(BigInteger.ZERO) < 0) { // a >= 0 или b >= 0
            System.out.println("Введите различные числа не меньше 0!");
            return;
        }

        if (a.compareTo(b) > 0) { // если a > b

            if (b.equals(BigInteger.ZERO)) {
                System.out.println("Наибольший общий делитель чисел " + a + " и " + b + ":");
                System.out.println("НОД(a,b) = d = " + a);
                System.out.println("Линейное представление d = НОД(a,b):");
                System.out.println(a + " = (" + 1 + ")*" + a + " + " + b + "*(" + 0 + ")");
                return;
            }

            time = System.currentTimeMillis();
            eaE(a, b);
            time = System.currentTimeMillis() - time;
            System.out.println("Время работы Расширенного алгоритма Евклида - " + time + " мс");
            time = System.currentTimeMillis();
            ebaE(a, b);
            time = System.currentTimeMillis() - time;
            System.out.println("Время работы Расширенного бинарного алгоритма Евклида - " + time + " мс");
            time = System.currentTimeMillis();
            eaEwtr(a, b);
            time = System.currentTimeMillis() - time;
            System.out.println("Время работы Расширенного алгоритма Евклида «усечёнными» остатками - " + time + " мс");

        } else if (b.compareTo(a) > 0) { // если b > a
            if (a.equals(BigInteger.ZERO)) {
                System.out.println("Наибольший общий делитель чисел " + a + " и " + b + ":");
                System.out.println("НОД(a,b) = d = " + b);
                System.out.println("Линейное представление d = НОД(a,b):");
                System.out.println(b + " = (" + 1 + ")*" + b + " + " + a + "*(" + 0 + ")");
                return;
            }

            time = System.currentTimeMillis();
            eaE(b, a);
            time = System.currentTimeMillis() - time;
            System.out.println("Время работы Расширенного алгоритма Евклида - " + time + " мс");
            time = System.currentTimeMillis();
            ebaE(b, a);
            time = System.currentTimeMillis() - time;
            System.out.println("Время работы Расширенного бинарного алгоритма Евклида - " + time + " мс");
            time = System.currentTimeMillis();
            eaEwtr(b, a);
            time = System.currentTimeMillis() - time;
            System.out.println("Время работы Расширенного алгоритма Евклида «усечёнными» остатками - " + time + " мс");

        } else if (a.equals(BigInteger.ZERO)) System.out.println("Введите различные числа не меньше 0!");
        else System.out.println("Наибольший общий делитель чисел " + a + " и " + b + " = " + a); // если a = b
    }

    /* Расширенный алгоритм Евклида */
    public static void eaE(BigInteger a, BigInteger b) {

        System.out.println("\n" + "Расширенный алгоритм Евклида: ");

        int i = 1;
        BigInteger q, c;
        BigInteger a1 = a, b1 = b;
        BigInteger x0 = BigInteger.ONE;
        BigInteger x1 = BigInteger.ZERO;
        BigInteger y0 = BigInteger.ZERO;
        BigInteger y1 = BigInteger.ONE;

        while (!b1.equals(BigInteger.ZERO)) {
            System.out.print("Итерация " + i + ". " + "НОД(" + a1 + "," + b1 + "): " + a1 + " = ");
            c = b1;
            q = a1.divide(c); // q - целая часть от деления a на b
            b1 = a1.mod(c);   // b1 - остаток часть от деления a на b
            a1 = c;
            System.out.println(c + "*" + q + " + " + b1);
            i++;

            if (b1.equals(BigInteger.ZERO)) { // если b = 0
                System.out.println("Наибольший общий делитель чисел " + a + " и " + b + ":");
                System.out.println("НОД(a,b) = d = " + a1);
                System.out.println("Линейное представление d = НОД(a,b):");
                System.out.println(a1 + " = (" + x1 + ")*" + a + " + " + b + "*(" + y1 + ")");
                System.out.println("Проверка:");
                q = (x1.multiply(a)).add(y1.multiply(b));
                System.out.println(a1 + " = " + q);
            } else {
                c = x1;
                x1 = x0.subtract(q.multiply(x1)); //x1 = x0 - q*x1
                System.out.println("Коэффициенты линейного представление d = НОД(a,b):");
                System.out.print("x = " + x1);
                x0 = c;
                c = y1;
                y1 = y0.subtract(q.multiply(y1)); //y1 = y0 - q*y1
                System.out.println("; y = " + y1);
                y0 = c;
            }
        }
    }

    /* Расширенный бинарный алгоритм Евклида */
    public static void ebaE(BigInteger a, BigInteger b) {

        System.out.println("\n" + "Расширенный бинарный алгоритм Евклида: ");

        int i = 1;
        BigInteger u = a, v = b;
        BigInteger g = BigInteger.ONE;  // g = 1
        BigInteger A = BigInteger.ONE;  // A = 1
        BigInteger B = BigInteger.ZERO; // B = 0
        BigInteger C = BigInteger.ZERO; // C = 0
        BigInteger D = BigInteger.ONE;  // D = 1
        BigInteger tw = new BigInteger("2"); // tw = 2

        while (u.mod(tw).equals(BigInteger.ZERO) && v.mod(tw).equals(BigInteger.ZERO)) { // пока u и v четные
            u = u.shiftRight(1); // = u/2
            v = v.shiftRight(1); // = v/2
            g = g.shiftLeft(1);  // = g*2
        }

        System.out.println("Число " + a + " поделить на " + g + " = " + u);
        System.out.println("Число " + b + " поделить на " + g + " = " + v);

        BigInteger a1 = u;
        BigInteger b1 = v;

        while (!u.equals(BigInteger.ZERO)) { // пока u не равно 0

            while (u.mod(tw).equals(BigInteger.ZERO)) { // пока u делится на 2
                u = u.shiftRight(1); // = u/2

                if (A.mod(tw).equals(BigInteger.ZERO) && B.mod(tw).equals(BigInteger.ZERO)) { // если  A и B четные
                    A = A.shiftRight(1); // = A/2
                    B = B.shiftRight(1); // = B/2

                } else {
                    A = (A.add(b1)).shiftRight(1); // = (A+b)/2
                    B = (B.subtract(a1)).shiftRight(1); // = (B-a)/2
                }
            }

            while (v.mod(tw).equals(BigInteger.ZERO)) { // пока v делится на 2
                v = v.shiftRight(1); // = v/2

                if (C.mod(tw).equals(BigInteger.ZERO) && C.mod(tw).equals(BigInteger.ZERO)) { // если  C и D четные
                    C = C.shiftRight(1); // = C/2
                    D = D.shiftRight(1); // = D/2

                } else {
                    C = (C.add(b1)).shiftRight(1); // = (C+b)/2
                    D = (D.subtract(a1)).shiftRight(1); // = (D-a)/2
                }
            }

            if (u.compareTo(v) >= 0) { // если u >= v
                u = u.subtract(v); // u - v
                A = A.subtract(C); // A - C
                B = B.subtract(D); // B - D
                System.out.println("Итерация " + i + ". НОД(" + v + "," + u + ")");
                System.out.println(" А = " + A + " B = " + B + " C = " + C + " D = " + D);
                i++;

            } else {
                v = v.subtract(u); // v - u
                C = C.subtract(A); // C - A
                D = D.subtract(B); // D - B
                System.out.println("Итерация " + i + ". НОД(" + v + "," + u + ")");
                System.out.println(" А = " + A + " B = " + B + " C = " + C + " D = " + D);
                i++;
            }
        }

        g = v.multiply(g); // g*v
        System.out.println("Наибольший общий делитель чисел " + a + " и " + b + ":");
        System.out.println("НОД(a,b) = d = " + g);
        System.out.println("Линейное представление d = НОД(a,b):");
        System.out.println(g + " = (" + C + ")*" + a + " + " + b + "*(" + D + ")");
        System.out.println("Проверка:");
        v = (C.multiply(a)).add(D.multiply(b));
        System.out.println(g + " = " + v);
    }

    /* Расширенный алгоритм Евклида с «усечёнными» остатками */
    public static void eaEwtr(BigInteger a, BigInteger b) {

        System.out.println("\n" + "Расширенный алгоритм Евклида с «усечёнными» остатками: ");

        int i = 1;
        BigInteger q, c;
        BigInteger a1 = a, b1 = b;
        BigInteger x0 = BigInteger.ONE;
        BigInteger x1 = BigInteger.ZERO;
        BigInteger x2 = BigInteger.ZERO;
        BigInteger y0 = BigInteger.ZERO;
        BigInteger y1 = BigInteger.ONE;
        BigInteger y2 = BigInteger.ZERO;

        while (!b1.equals(BigInteger.ZERO)) {
            System.out.print("Итерация " + i + ". " + "НОД(" + a1 + "," + b1 + "): " + a1 + " = ");
            c = b1;
            q = a1.divide(c); // q - целая часть от деления a на b
            b1 = a1.mod(c);   // b1 - остаток часть от деления a на b
            a1 = c;
            System.out.println(c + "*" + q + " + " + b1);
            i++;

            if (b1.equals(BigInteger.ZERO)) { // если b = 0
                System.out.println("Наибольший общий делитель чисел " + a + " и " + b + ":");
                System.out.println("НОД(a,b) = d = " + a1);
                System.out.println("Линейное представление d = НОД(a,b):");
                System.out.println(a1 + " = (" + x2 + ")*" + a + " + " + b + "*(" + y2 + ")");
                System.out.println("Проверка:");
                q = (x1.multiply(a)).add(y1.multiply(b));
                System.out.println(a1 + " = " + q);
            } else {
                System.out.println("Коэффициенты линейного представление d = НОД(a,b):");
                x2 = x0.subtract(q.multiply(x1)); //x2 = x0 - q*x1
                System.out.print("x = " + x2);
                y2 = y0.subtract(q.multiply(y1)); //y2 = y0 - q*y1
                System.out.println("; y = " + y2);

                x0 = x1;
                y0 = y1;

                if (b1.compareTo(a1.shiftRight(1)) > 0) {// если остаток > текущего b/2
                    b1 = a1.subtract(b1); // остаток = текущее b - текущий статок;
                    System.out.println("«усечённый» остаток a1= " + b1);

                    x1 = x1.subtract(x2); //x1 = x1 - x2
                    y1 = y1.subtract(y2); //y1 = y1 - y2

                } else {
                    x1 = x2;
                    y1 = y2;
                }
            }
        }
    }
}
