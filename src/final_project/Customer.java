package final_project;

import static final_project.Account.accountNumberr;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer {

    static int tryy = 0;
    static String name, city;
    static String accountType;
    static int age;
    public static Account account;
    private static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<String> transactions = new ArrayList<>();
    public static ArrayList<String> accounts = new ArrayList<>();

    public static ArrayList<String> getAccounts() {
        return accounts;
    }
    static int filePassword = 0;
    static String[] bank;
    static String fileAccountNumber;
    static float fileBalance;

    public Customer(String name, String city, int age, int password, float balance, String accountType) throws FileNotFoundException {
        setName(name);
        setCity(city);
        setAge(age);
        Account.setPassword(password);
        Account.setAccountType(accountType);
        Account.balancee = balance;
        if (Account.getAccountType().equalsIgnoreCase("1")) {
            CheckingAccount ca = new CheckingAccount();
        } else if (Account.getAccountType().equalsIgnoreCase("2")) {
            SavingAccount Sa = new SavingAccount();
        } else {
            System.out.println("invalid account type");
        }
        Account.setAccountNumber(createaccountNumber());
    }

    public Customer(String fileAccountNumber, int filePassword, float fileBalance) {
        Account.setPassword(filePassword);
        Account.setAccountNumber(fileAccountNumber);
        Account.balancee = fileBalance;
    }

    public static void getFileData() throws FileNotFoundException {
        ArrayList<Customer> customers = new ArrayList<>();
        File file = new File("C:\\customers files\\" + fileAccountNumber + ".txt");
        Scanner input = new Scanner(file);
        bank = input.nextLine().split(" ");
        fileAccountNumber = bank[0].trim();
        name = bank[1].trim();
        city = bank[2].trim();
        age = Integer.parseInt(bank[3].trim());
        filePassword = Integer.parseInt(bank[4].trim());
        Account.setBalancee(Float.parseFloat(bank[5].trim()));
        accountType = bank[6].trim();
        customers.add(new Customer(fileAccountNumber, filePassword, fileBalance));
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        if (!name.equals(null)) {
            Customer.name = name;
        }
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        if (!city.equals(null)) {
            Customer.city = city;
        }
    }

    public static String getAccountType() {
        return accountType;
    }

    public static void setAccountType(String accountType) {
        if (!accountType.equals(null)) {
            Customer.accountType = accountType;
        }
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        if (age > 0 && age < 100) {
            Customer.age = age;
        }
    }

    public static void setAccount(Account account) {
        Customer.account = account;
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static void setCustomers(ArrayList<Customer> customers) {
        Customer.customers = customers;
    }

    public static int getFilePassword() {
        return filePassword;
    }

    public static void setFilePassword(int filePassword) {
        Customer.filePassword = filePassword;
    }

    public static String[] getBank() {
        return bank;
    }

    public static void setBank(String[] bank) {
        Customer.bank = bank;
    }

    public static String getFileAccountNumber() {
        return fileAccountNumber;
    }

    public static void setFileAccountNumber(String fileAccountNumber) {
        Customer.fileAccountNumber = fileAccountNumber;
    }

    public static float getFileBalance() {
        return fileBalance;
    }

    public static void setFileBalance(float fileBalance) {
        Customer.fileBalance = fileBalance;
    }

    public static float checkBalance() {
        return Account.balancee;
    }

    public static void showMenu3() {
        System.out.println("1. Deposit\n2. Withdraw\n3. Transfer (if it’s a checking account)\n4. Check balance\n5. Print account transactions\n6. Exit");
    }

    public static void showMenu1() {
        System.out.println("Welcome\n 1.login\n 2.register\n 3.Exit");
    }

    public static void showMenu2() {
        System.out.println("Please choose the account type :\n 1. Checking Account\n 2. Saving Account");
    }

    public void createCustomerFile(String accountNumber) {
        File folder = new File("C:\\customers files");
        if (!folder.exists()) {
            folder.mkdir();
        }
        try {
            File file = new File(folder.getPath() + "\\" + accountNumber + ".txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            PrintWriter pr = new PrintWriter(fw);
            pr.print(Account.getAccountNumberr() + " ");
            pr.print(name + " ");
            pr.print(city + " ");
            pr.print(age + " ");
            pr.print(Account.getPassword() + " ");
            pr.print(Account.getBalancee() + " ");
            if (Account.getAccountType().equalsIgnoreCase("1")) {
                pr.print("CheckingAccount");
            } else if (Account.getAccountType().equalsIgnoreCase("2")) {
                pr.print("SavingAccount");
            }
            pr.close();
            fw.close();
            System.out.println("done");
            System.out.println("Your information is:\n Account Number:" + Account.getAccountNumberr() + "\n Password: " + Account.getPassword());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String createaccountNumber() {
        return Math.round(Math.random() * 10000000000l) + "";
    }

    public static void login(String accountNumber2, int password) throws FileNotFoundException, IOException {
        do {
            File[] files = new File("C:\\customers files").listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    accounts.add(file.getName().substring(0, 10));
                }
            }
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).substring(0, 10).equalsIgnoreCase(accountNumber2)) {
                    File file = new File("C:\\customers files\\" + accountNumber2 + ".txt");
                    Scanner input = new Scanner(file);
                    bank = input.nextLine().split(" ");
                    fileAccountNumber = bank[0].trim();
                    name = bank[1].trim();
                    city = bank[2].trim();
                    age = Integer.parseInt(bank[3].trim());
                    filePassword = Integer.parseInt(bank[4].trim());
                    fileBalance = Float.parseFloat(bank[5].trim());
                    accountType = bank[6].trim();
                    int lines = 0;

                    BufferedReader bufReader = new BufferedReader(new FileReader(file));

                    String line = bufReader.readLine();

                    while (line != null) {
                        transactions.add(line);
                        line = bufReader.readLine();
                    }
                    bufReader.close();

                    customers.add(new Customer(fileAccountNumber, filePassword, fileBalance));
                }
            }
            if (filePassword == password) {
                System.out.println("\n**********************");
                System.out.printf("** You're logged in **\n");
                System.out.println("**********************\n");
                Customer.showMenu3();
                break;
            } else if (filePassword != password) {
                System.out.println("The password is invalid");
                tryy++;
            }
            if (tryy == 3) {
                System.out.println("Are you really the user ?");
                System.exit(0);
            }
        } while (tryy < 3);
    }
}
