package final_project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Final_Project {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner in = new Scanner(System.in);
        int choice1, choice3 = 0;
        String accountType;
        Customer.showMenu1();
        choice1 = in.nextInt();
        System.out.println("Enter Your Account Number");
        long accountNumber = in.nextLong();
        String accountNumber2 = Long.toString(accountNumber);

        System.out.println("Enter Your Password");
        int password = in.nextInt();

        do {
            switch (choice1) {
                case (1): {
                    Customer.login(accountNumber2, password);
                    choice3 = in.nextInt();
                    switch (choice3) {
                        case (1): {
                            System.out.println("Enter the amount :");
                            float amount = in.nextFloat();
                            if (Customer.accountType.equalsIgnoreCase("SavingAccount")) {
                                SavingAccount.deposit(amount);
                            } else if (Customer.accountType.equalsIgnoreCase("CheckingAccount")) {
                                CheckingAccount.deposit(amount);
                            }
                            break;
                        }
                        case (2): {
                            System.out.println("Enter the amount :");
                            float amount = in.nextFloat();
                            in.nextLine();
                            if (Customer.accountType.equalsIgnoreCase("SavingAccount")) {
                                SavingAccount.withDraw(amount);
                            } else if (Customer.accountType.equalsIgnoreCase("CheckingAccount")) {
                                CheckingAccount.withDraw(amount);
                            }
                            break;
                        }
                        case (3): {
                            System.out.println("Enter the account number you want to transfer the amount to.");
                            String to = in.nextLine();
                            in.nextLine();
                            System.out.println("Enter the amount :");
                            float amount = in.nextFloat();

                            CheckingAccount.Transfer(to,amount);
                            break;
                        }
                        case (4): {
                            System.out.println(Customer.checkBalance());
                            break;
                        }
                        case (5): {
                            Account.printAccountTransactions();
                            break;
                        }
                        case (6): {
                            
                        }
                        default:
                            System.out.println("Invalid Choice");
                            break;
                    }
                    break;
                }
                case (2): {
                    System.out.println("Enter Your Information please");
                    System.out.println("*****************************");
                    System.out.println(" Enter your Name :");
                    String name = in.next();
                    System.out.println(" Your city :");
                    String city = in.next();
                    System.out.println(" Your age :");
                    int age = in.nextInt();
                    System.out.println(" Your Password :");
                    int passwordd = in.nextInt();
                    System.out.println(" Your Balance :");
                    float balance = in.nextFloat();
                    in.nextLine();
                    Customer.showMenu2();
                    accountType = in.nextLine();
                    Customer c = new Customer(name, city, age, passwordd, (int) balance, accountType);
                    c.createCustomerFile(Account.accountNumberr);
                    break;
                }
                case (3): {
                    System.out.println("nice to work with you...");
                    break;
                }
                default: {
                    System.out.println("Invalid Choice");
                    break;
                }
            }
        } while (choice1 != 3);
    }
}
