import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("Добро пожаловать в мой калькулятор! Здесь вы можете использовать, как арабские, так и римские числа. (Например, 2+2 или II+II). Обращаю ваше внимание, что числа должны быть от 1 до 10");
        System.out.println("После выполнения математической операции программа предложит вам продолжить использование. Чтобы продолжить, необходимо будет написать ДА прямо верхним регистром.");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Пожалуйста, введите математический пример из двух чисел.");
            String expression = scanner.nextLine();
            System.out.println(parse(expression));

            System.out.println("Желаете продолжить? (Напишите, пожалуйста ДА или НЕТ)");
            String input = scanner.nextLine();

            if (!input.equalsIgnoreCase("ДА")) {
                break;
            }
        }

        System.out.println("Будем ждать вас снова! До свидания!");

    }

    public static String parse(String expression) throws Exception {

        int a;
        int b;
        String between;
        String result;
        boolean isRoman;

        String[] operands = expression.split("[+\\-*/]");

        if (operands.length != 2) throw new Exception("Необходимо 2 операнда");
        between = detectOperation(expression);

        if (between == null) throw new Exception("Данная математическая операция не поддерживается");

        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            a = Roman.convertToArabian(operands[0]);
            b = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            a = Integer.parseInt(operands[0]);
            b = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        else {
            throw new Exception("К сожалению, возможность работать со смешанными числами в этом калькуляторе не предусмотрена");
        }

        if (a > 10 || b > 10) {
            throw new Exception("Цифры должны быть от 1 до 10");
        }

        int arabian = calc(a, b, between);

        if (isRoman) {
            if (arabian <= 0) {
                throw new Exception("Увы, римское число должно быть не меньше нуля");
            }
            result = Roman.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }

        return result;
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String between) {
        if (between.equals("+")) return a + b;
        else if (between.equals("-")) return a - b;
        else if (between.equals("*")) return a * b;
        else return a / b;
    }

}

class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }
}