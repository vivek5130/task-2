import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opt;

        System.out.println("Welcome to Cyber Password Generator");
        do {
            menu();
            opt = scanner.nextInt();
            switch (opt) {
                case 1:

                    GeneratePassword();
                    break;
                case 2:
                    String checkScore = checkPassword();
                    System.out.println(checkScore);
                    break;
                case 3:
                    UseFullInfo();
                    break;
                case 4:
                    System.out.println("Exited successfully");
                    return;

                default:
                    System.out.println("Enter valid option");
                    break;
            }

        } while (opt != 4);

        scanner.close();
    }

    public static void menu() {
        System.out.println("Enter your choice");
        System.out.println("1.Generate password");
        System.out.println("2. Check your password strength");
        System.out.println("3. Print useful Info");
        System.out.println("4. Exit");

    }

    public static void GeneratePassword() {
        boolean IncludeUpper = false;
        boolean IncludeLower = false;
        boolean IncludeNum = false;
        boolean IncludeSym = false;
        int len = 1;
        boolean correctParams = false;
        System.out.println("Answer the following questions in yes or no");
        do {
            String input;

            correctParams = false;
            do {
                System.out.println("Do you want uppercase letters like 'ABCD..' to be present?");
                input = scanner.nextLine();
                if (addQuery(input)) {
                    IncludeUpper = true;
                }
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));
            do {
                System.out.println("Do you want lowercase letters like 'abcd..' to be present? ");
                input = scanner.nextLine();
                if (addQuery(input)) {
                    IncludeLower = true;
                }
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));
            do {
                System.out.println("Do you want Symbols \"!@#$...\" to be used? ");
                input = scanner.nextLine();
                if (addQuery(input)) {
                    IncludeSym = true;
                }
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));
            do {
                System.out.println("Do you want Numbers \"1234...\" to be used? ");
                input = scanner.nextLine();
                if (addQuery(input)) {
                    IncludeNum = true;
                }
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            System.out.println("Enter the length of your password");
            len = scanner.nextInt();

            if (!IncludeUpper && !IncludeLower && !IncludeNum && !IncludeSym) {
                System.out.println("You have selected no characters to generate your " +
                        "password, at least one of your answers should be Yes\n");
                correctParams = true;
            }

        } while (correctParams);
        // System.out.println("Your Generated password is: " + "adff");
        String genPass = createPassword(IncludeUpper, IncludeLower, IncludeNum, IncludeSym, len);
        System.out.println("Your password is" + genPass);
    }

    public static boolean addQuery(String s) {
        if (s.equalsIgnoreCase("yes")) {
            return true;
        }
        return false;

    }

    public static String createPassword(boolean uppercaseIncluded, boolean lowercaseIncluded, boolean numbersIncluded,
            boolean specialCharactersIncluded, int length) {
        final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String NUMBERS = "1234567890";
        final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
        final String SYMBOLS = "!@#$%^&*()-_=+\\/~?";
        final StringBuilder pool;
        pool = new StringBuilder();
        if (uppercaseIncluded)
            pool.append(UPPERCASE_LETTERS);

        if (lowercaseIncluded)
            pool.append(LOWERCASE_LETTERS);

        if (numbersIncluded)
            pool.append(NUMBERS);

        if (specialCharactersIncluded)
            pool.append(SYMBOLS);

        final StringBuilder pass = new StringBuilder("");
        final int alphabetLength = pool.toString().length();

        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min;
            pass.append(pool.toString().charAt(index));
        }
        return pass.toString();

    }

    public static String checkPassword() {
        String input;
        System.out.println("Enter your password");
        input = scanner.next();
        // calculating score
        String s = input;
        boolean UsedUpper = false;
        boolean UsedLower = false;
        boolean UsedNum = false;
        boolean UsedSym = false;

        int Score = 0;

        for (int i = 0; i < s.length(); i++) {
            char C = s.charAt(i);
            if ((int) C >= 65 && (int) C <= 90)
                UsedUpper = true;
            if ((int) C >= 97 && (int) C <= 122)
                UsedLower = true;
            if ((int) C >= 60 && (int) C <= 71)
                UsedNum = true;
            if (!(UsedUpper || UsedLower || UsedNum))
                UsedSym = true;
        }

        if (UsedUpper)
            Score += 1;
        if (UsedLower)
            Score += 1;
        if (UsedNum)
            Score += 1;
        if (UsedSym)
            Score += 1;

        if (s.length() >= 8)
            Score += 1;
        if (s.length() >= 16)
            Score += 1;
        if (Score == 6) {
            return "This is a very good password :D check the Useful Information section to make sure it satisfies the guidelines, Score is: "
                    + Score;
        } else if (Score >= 4) {
            return "This is a good password :) but you can still do better , Score is" + Score;
        } else if (Score >= 3) {
            return "This is a medium password :/ try making it better , Score is" + Score;
        } else {
            return "This is a weak password :( definitely find a new one , Score is" + Score;
        }

    }

    public static void UseFullInfo() {
        System.out.println("Password Security Tips:");
        System.out.println("1. Use a minimum password length of 8 characters or more.");
        System.out.println("2. Mix lowercase and uppercase letters, numbers, and symbols.");
        System.out.println("3. Generate passwords randomly when possible.");
        System.out.println("4. Avoid reusing passwords across different accounts.");
        System.out.println("5. Stay away from predictable patterns, like keyboard sequences or common words.");
        System.out.println("6. Keep personal information out of your passwords.");
        System.out.println("7. Don't use passwords solely based on simple combinations of common elements.");

    }
}