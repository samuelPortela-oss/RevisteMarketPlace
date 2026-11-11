package app.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FormRuleValidator {

    private static final Scanner sc =
            new Scanner(System.in);

    public static int validateInt(String prompt) {

        while (true) {

            try {

                System.out.print(prompt + " ");

                int value = sc.nextInt();
                sc.nextLine();

                return value;

            } catch (InputMismatchException e) {

                System.out.println(
                        "Valor entero inválido.");

                sc.nextLine();
            }
        }
    }

    public static double validateDouble(
            String prompt) {

        while (true) {

            try {

                System.out.print(prompt + " ");

                double value = sc.nextDouble();
                sc.nextLine();

                return value;

            } catch (InputMismatchException e) {

                System.out.println(
                        "Valor decimal inválido.");

                sc.nextLine();
            }
        }
    }

    public static String validateString(
            String prompt) {

        while (true) {

            System.out.print(prompt + " ");

            String value =
                    sc.nextLine().trim();

            if (!value.isEmpty()) {

                return value;
            }

            System.out.println(
                    "El valor no puede estar vacío.");
        }
    }

    public static String validateEmail(
            String prompt) {

        while (true) {

            System.out.print(prompt + " ");

            String email =
                    sc.nextLine().trim();

            if (email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                return email;
            }

            System.out.println(
                    "Formato de correo inválido.");
        }
    }

    public static String validatePassword(
            String prompt) {

        while (true) {

            System.out.print(prompt + " ");

            String password =
                    sc.nextLine();

            if (password.length() >= 6) {

                return password;
            }

            System.out.println(
                    "La contraseña debe tener al menos 6 caracteres.");
        }
    }

    public static String validatePhone(
            String prompt) {

        while (true) {

            System.out.print(prompt + " ");

            String phone =
                    sc.nextLine().trim();

            if (phone.matches("\\d{10}")) {

                return phone;
            }

            System.out.println(
                    "El teléfono debe tener exactamente 10 dígitos.");
        }
    }

    public static boolean validateYesNo(
            String prompt) {

        while (true) {

            System.out.print(
                    prompt + " (S/N): ");

            String answer =
                    sc.nextLine()
                            .trim()
                            .toUpperCase();

            if (answer.equals("S")) {

                return true;
            }

            if (answer.equals("N")) {

                return false;
            }

            System.out.println(
                    "Ingrese S o N.");
        }
    }
}
