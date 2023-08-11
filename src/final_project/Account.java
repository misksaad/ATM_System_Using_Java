package final_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Account {

    static String accountNumberr;
    static int passwordd;
    static String accountTypee;
    public static float balancee;
    public static ArrayList<Account> account = new ArrayList<>();
    private static boolean isBlocked;

    public static void setAccountNumber(String accountNumber) {
        accountNumberr = accountNumber;
    }

    public static String getAccountType() {
        return accountTypee;
    }

    public static void setAccountType(String accountType) {
        accountTypee = accountType;
    }

    public static int getPassword() {
        return passwordd;
    }

    public static String getAccountNumberr() {
        return accountNumberr;
    }

    public static int getPasswordd() {
        return passwordd;
    }

    public static String getAccountTypee() {
        return accountTypee;
    }

    public static float getBalancee() {
        return balancee;
    }

    public static ArrayList<Account> getAccount() {
        return account;
    }

    public static void setAccountNumberr(String accountNumberr) {
        Account.accountNumberr = accountNumberr;
    }

    public static void setPasswordd(int passwordd) {
        Account.passwordd = passwordd;
    }

    public static void setAccountTypee(String accountTypee) {
        Account.accountTypee = accountTypee;
    }

    public static void setBalancee(float balancee) {
        if (balancee > 0) {
            Account.balancee = balancee;
        }
    }

    public static void setAccount(ArrayList<Account> account) {
        Account.account = account;
    }

    public static void setPassword(int password) {
        passwordd = password;
    }

    public static float getBalance() {
        return balancee;
    }

    public static float setBalance(float balance) {
        balancee = balance;
        return balancee;
    }

    public static boolean isIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public static void printAccountTransactions() throws IOException {
        File file = new File("C:\\customers files\\" + Account.getAccountNumberr() + ".txt");

        try {
            BufferedReader in = new BufferedReader(new FileReader("C:\\customers files\\" + Account.getAccountNumberr() + ".txt"));
            String str;

            List<String> list = new ArrayList<String>();
            while ((str = in.readLine()) != null) {
                list.add(str);
            }
            String[] stringArr = list.toArray(new String[0]);
            System.out.print("Transactions:\n");
            for(int i=1;i<stringArr.length;i++) {
                System.out.println("  "+i+"."+stringArr[i]);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
