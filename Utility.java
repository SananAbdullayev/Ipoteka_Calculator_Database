package ipoteka_calculator_database;

import java.math.BigDecimal;
import java.util.Scanner;

public class Utility {
    public static int ipoteUsulu() {
        int a;
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("İpoteka üsulu:\n  Standart -> 1 \n  Güzəştli -> 2");
        a = scanner2.nextInt();
        return a;
    }

    public static int tikintiIli() {
        int a;
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Tikinti ili:\n  1970-ci ildən yuxarı mənzillər -> 1 \n " +
                " 2013-cü ildən yuxarı mənzilər -> 2");
        a = scanner2.nextInt();
        return a;
    }

    public static int verilmeUsulu() {
        int a;
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Verilmə üsulu:\n  Zəmanətli -> 1 \n " +
                " Zəmanətsiz -> 2");
        a = scanner2.nextInt();
        return a;
    }

    public static BigDecimal menzilDeyeri() {
        BigDecimal b = BigDecimal.ZERO;
        boolean flag = true;
        Scanner scanner2 = new Scanner(System.in);
        while (flag) {
            System.out.print("Mənzilin Dəyəri (min - 20000 AZN): ");
            b = scanner2.nextBigDecimal();
            if (b.compareTo(BigDecimal.valueOf(20000)) < 0) {
                System.out.print("Minimal qiymət 20000 AZN dir\n Zəhmət olmasa təkrar daxil edin: ");
                flag = true;
            } else {
                flag = false;
            }
        }
        return b;
    }

    public static int muddet() {
        int b = 0;
        boolean flag = true;
        Scanner scanner2 = new Scanner(System.in);
        while (flag) {
            System.out.print("Müddət (ay): ");
            b = scanner2.nextInt();
            if (b > 36 && b <= 360) {
                flag = false;
            } else {
                System.out.println("Müddət 36 - 360 (ay) arası olmalıdır\n Zəhmət olmasa təkrar daxil edin: ");
                flag = true;
            }
        }
        return b;
    }
}
