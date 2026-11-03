package app.utils;

<<<<<<< HEAD
=======
import app.repository.UsuarioRepositoryImpl;

>>>>>>> 50c9d38602c8a1e449437fdd0a906df001cbd04b
import java.util.InputMismatchException;
import java.util.Scanner;

public class FormRuleValidator {

<<<<<<< HEAD
    static Scanner sc = new Scanner(System.in);
=======
    private UsuarioRepositoryImpl usuarioRepositoryImpl;
    public FormRuleValidator(UsuarioRepositoryImpl usuarioRepositoryImpl){
        this.usuarioRepositoryImpl = usuarioRepositoryImpl;
    }

    private static final Scanner sc =
            new Scanner(System.in);
>>>>>>> 50c9d38602c8a1e449437fdd0a906df001cbd04b

    public static int validateInt(String prompt) {

        while (true) {
<<<<<<< HEAD
            try {
                System.out.println(prompt);
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido. " + e.getMessage());
=======

            try {

                System.out.print(prompt + " ");

                int value = sc.nextInt();
                sc.nextLine();

                return value;

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid integer value.");

>>>>>>> 50c9d38602c8a1e449437fdd0a906df001cbd04b
                sc.nextLine();
            }
        }
    }

<<<<<<< HEAD
    public static Double validateDouble(String prompt) {

        while (true) {
            try {
                System.out.println(prompt);
                Double value = sc.nextDouble();
                sc.nextLine();
                return value;

            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número decimal válido." + e.getMessage());
                sc.nextLine(); // Limpiar el buffer del scanner
            }
        }

    }

    public static Boolean validateBoolean(String prompt) {

        while (true) {
            try {
                System.out.println(prompt);
                Boolean value = sc.nextBoolean();
                sc.nextLine();
                return value;

            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un valor Booleano valido." + e.getMessage());
                sc.nextLine(); // Limpiar el buffer del scanner
=======
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
>>>>>>> 50c9d38602c8a1e449437fdd0a906df001cbd04b
            }
        }
    }

<<<<<<< HEAD

    public static String validateString(String prompt) {

        while (true) {
            System.out.println(prompt);
            String value = sc.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            } else {
                System.out.println("Error: Ingrese un valor de texto válido.");
            }
        }
    }
}
=======
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
>>>>>>> 50c9d38602c8a1e449437fdd0a906df001cbd04b
