package app.utils;

import app.repository.UsuarioRepositoryImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FormRuleValidator {

    private UsuarioRepositoryImpl usuarioRepositoryImpl;
    public FormRuleValidator(UsuarioRepositoryImpl usuarioRepositoryImpl){
        this.usuarioRepositoryImpl = usuarioRepositoryImpl;
    }

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
                        "Invalid integer value.");

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
                        "Invalid decimal value.");

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
                    "Value cannot be empty.");
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
                    "Invalid email format.");
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
                    "Password must contain at least 6 characters.");
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
                    "Phone must contain exactly 10 digits.");
        }
    }

    public static boolean validateYesNo(
            String prompt) {

        while (true) {

            System.out.print(
                    prompt + " (Y/N): ");

            String answer =
                    sc.nextLine()
                            .trim()
                            .toUpperCase();

            if (answer.equals("Y")) {

                return true;
            }

            if (answer.equals("N")) {

                return false;
            }

            System.out.println(
                    "Enter Y or N.");
        }
    }
}