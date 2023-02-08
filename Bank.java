import java.util.*;

public class Bank {
    public static void main(String args[]) {

        int userInput = 9;
        String userName, userPassword;

        System.out.println("Welcome to Banking lite\n");
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Login to your bank: ");
            userName = sc.nextLine();

            System.out.println("Enter your password: ");
            userPassword = sc.nextLine();

            boolean loginStatus = Auth.userLogin(userName, userPassword);

            if (loginStatus) {
                BankingOperations bankingOperations = new BankingOperations(20000);


                while (userInput != 0) {

                    System.out.println("Enter Option from the below numbers: ");
                    System.out.println("\n1. Withdraw cash\n2. Past Transactions\n3. To logout");
                    userInput = sc.nextInt();

                    switch (userInput) {



                        case 1:
                            {
                                System.out.println("Enter the amount to withdraw: ");
                                int amt = sc.nextInt();
                                bankingOperations.withdrawCash(amt);
                                break;
                            }
                        case 2:
                            bankingOperations.pastTransactions();
                            break;
                        case 3:
                            System.out.println("Logged out successfully.");
                            userInput = 0;
                            break;
                        default:
                            System.out.println("Invalid");
                    }
                }


            } else System.out.println("Password constraints not satisfied.");
        }


    }
}

class Auth {
    String userName;
    String userPassword;

    static boolean userLogin(String userName, String userPassword) {
        // user password condition
        Boolean[] passwordConditions = new Boolean[] {
            false,
            false,
            false,
            false
        };
        boolean response = true;

        if (userPassword.length() < 6 || userPassword.length() > 12) {
            return false;
        } else {
            for (int i = 0; i < userPassword.length(); i++) {
                int passChar = userPassword.charAt(i);

                if (passChar >= 97 && passChar <= 122) {
                    passwordConditions[0] = true;

                } else if (passChar >= 48 && passChar <= 57) {
                    passwordConditions[1] = true;
                } else if (passChar >= 65 && passChar <= 90) {
                    passwordConditions[2] = true;
                } else if (passChar == 35 || passChar == 36 || passChar == 64) {
                    passwordConditions[3] = true;
                }
            }
        }

        // checking all constraints
        for (int i = 0; i < 4; i++) {
            if (passwordConditions[i] == false) {
                response = false;
            }
        }

        return response;
    }
}

class BankingOperations {
    int balance;
    static ArrayList < Integer > balanceHistory = new ArrayList < > ();
    String returnStatement;

    BankingOperations(int balance) {
        this.balance = balance;
    }

    // for withdrawing cash from the account
    void withdrawCash(int withdrawAmount) {
        int totalAmount = withdrawAmount + 100;
        if (totalAmount <= balance) {
            balance -= totalAmount;
            balanceHistory.add(withdrawAmount);
            System.out.println("Your have withdrawn Rs: " + withdrawAmount + " balance left: " + balance);
        } else {
            System.out.println("Your balance Rs" + balance + " not sufficient");
        }
    }

    // for checking withdrawal history
    void pastTransactions() {
        for (int x: balanceHistory) {
            System.out.println("Withdrawn " + x + "(exclusive of bank charges i.e Rs.100.)");
        }
    }
}