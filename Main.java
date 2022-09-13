import java.util.Arrays;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[] romans1to10 = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public static void main(String[] args) {
        System.out.println("Введите выражение от 1 до 10 арабскими числами [2 + 2] или римскими числами [II + V] + Enter ");
        String inputUser = scanner.nextLine();
        String result = calc(inputUser);
        System.out.println(result);
    }


    public static String calc(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new RuntimeException("Много символов");
        }
        String operators = "+-/*";
        if (!(parts[1].length() == 1 && operators.contains(parts[1]))) {
            throw new RuntimeException("Неправильный символ операции");
        }
        return checkNumberType(parts[0], parts[2], parts[1]);
    }

    public static String checkNumberType(String num1, String num2, String operand) {
        if (Arrays.asList(romans1to10).contains(num1) && Arrays.asList(romans1to10).contains(num2)) {
            return countRomanNumbers(num1, num2, operand);
        }
        return stringsToArab(num1, num2, operand);
    }

    public static String countRomanNumbers(String num1, String num2, String operand) {
        int number1 = romanToNumber(num1);
        int number2 = romanToNumber(num2);
        int result = calculate(number1, number2, operand);
        if (result < 0) {
            throw new RuntimeException("Результат не может быть отрицательным,в римских числах нет отрицательных значений");
        }
        return convertNumToRoman(result);
    }

    public static String stringsToArab(String num1, String num2, String operand) {
        int arab1;
        int arab2;
        try {
            arab1 = Integer.parseInt(num1);
            arab2 = Integer.parseInt(num2);

        } catch (NumberFormatException e) {
            throw new RuntimeException("Введите либо только арабские, либо только римские числа, не более 10");
        }
        if (arab1 > 10 || arab2 > 10) {
            throw new RuntimeException("Числа не должны быть больше 10");
        }
        int result = calculate(arab1, arab2, operand);
        return String.valueOf(result);
    }


    public static int calculate(int num1, int num2, String operand) {
        int result = 0;
        switch (operand.charAt(0)) {
            case '+' -> result = num1 + num2;
            case '-' -> result = num1 - num2;
            case '*' -> result = num1 * num2;
            case '/' -> result = num1 / num2;
            default -> System.out.println("Неверный оператор");
        }
        return result;

    }

    public static String convertNumToRoman(int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        return roman[numArabian];
    }

    public static int romanToNumber(String roman) {
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> throw new RuntimeException("Невепный символ");
        };


    }
}