package final_project;

import static final_project.Account.balancee;
import static final_project.SavingAccount.flag;
import static final_project.SavingAccount.transactions;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckingAccount extends Account {

    static float afterTansferBalnaceFrom, afterTansferBalnaceTo, amount;
    static float bankCommission = 0.04f;
    static String to;

    public CheckingAccount() {
    }

    public CheckingAccount(String accountNumber){
        try {
            Customer.getFileData();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static float deposit(float amount) {
        if (amount > 0) {
            balancee = (balancee + amount) - (amount * bankCommission);
            transactions.add("\nYou've deposited " + amount + " form your checking account\nyour current balance: " + balancee + "$");
            System.out.println("done successfully");
            try {
                writeNewData(amount);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            flag = true;
        }
        return balancee;
    }

    public static float withDraw(float amount) {
        if (amount > 0 && amount <= balancee && !Account.isIsBlocked()) {
            balancee = (balancee - amount) - (amount * bankCommission);
            transactions.add("\nYou've wiehdraw " + amount + " form your checking account\nyour current balance: " + balancee + "$");
            System.out.println("done successfully");
            try {
                writeNewData(amount);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            flag = false;
        } else {
            System.out.println("This process cannot be completed.");
        }
        return balancee;
    }

    public static float checkBalance() {
        return balancee;
    }
    
    public static void Transfer(String accountNumber,float amount){
        if(amount>0&&amount<=balancee){
            for(int i=0;i<Customer.accounts.size();i++){
                if(Customer.accounts.get(i).equals(accountNumber)){
                    CheckingAccount c = new CheckingAccount(to);
                    setBalancee(getBalance()-amount);
                    c.setBalance(c.getBalance()+(amount-(amount*bankCommission)));
                    System.out.println("done");
                }
            }
        }else if (Customer.accountType.equalsIgnoreCase("SavingAccount")) {
            System.out.println("This operation cannot be performed");
        }
    }

    public static void writeAfterTransferFrom(float amount) throws IOException {
        File file = new File("C:\\customers files\\" + accountNumberr + ".txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pr = new PrintWriter(fw);
        pr.print(accountNumberr + " ");
        pr.print(Customer.name + " ");
        pr.print(Customer.city + " ");
        pr.print(Customer.age + " ");
        pr.print(Account.getPassword() + " ");
        writeOnFile(accountNumberr,"\nYou've transfer " + amount + " form your checking account to account number "+to+"\nyour current balance: " + balancee + "$");
        pr.print("CheckingAccount");
        pr.close();
        fw.close();
    }

    public static void writeAfterTransferTo(CheckingAccount c, float amount) throws IOException {
        File file = new File("C:\\customers files\\" + c.accountNumberr + ".txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pr = new PrintWriter(fw);
        pr.print(accountNumberr + " ");
        pr.print(Customer.name + " ");
        pr.print(Customer.city + " ");
        pr.print(Customer.age + " ");
        pr.print(Account.getPassword() + " ");
        writeOnFile(c.accountNumberr,"\nThe amount of " + amount + " has been transferred to your account \nyour current balance: " + balancee + "$");
        pr.print("CheckingAccount");
        pr.close();
        fw.close();
    }

    public static void writeNewData(float amount) throws IOException {
        File file = new File("C:\\customers files\\" + Account.getAccountNumberr() + ".txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pr = new PrintWriter(fw);
        pr.print(accountNumberr + " ");
        pr.print(Customer.name + " ");
        pr.print(Customer.city + " ");
        pr.print(Customer.age + " ");
        pr.print(Account.getPassword() + " ");
        pr.print(balancee + " ");
        pr.print("CheckingAccount");
        for (int i = 0; i < transactions.size(); i++) {
            pr.print(transactions.get(i));
        }
        writeTransactions(amount);
        pr.close();
        fw.close();
    }

    public static void writeTransactions(float amount) throws IOException {
        File file = new File("C:\\customers files\\" + Account.getAccountNumberr() + ".txt");
        FileWriter fw =new FileWriter(file, true);
        PrintWriter pr = new PrintWriter(fw);
        if (flag == false) {
            pr.print("\nYou've deposited " + amount + " form your Checking Account...your current balance: " + balancee + "$");
        } else {
            pr.print("\nYou've withdraw " + amount + " form your Checking Account...your current balance: " + balancee + "$");
        }
        pr.close();
        try {
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void writeOnFile(String accountNumber, String text) throws FileNotFoundException {
        File file = new File("C:\\customers files\\" + accountNumberr + ".txt");
        PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
            pw.println(text);
            pw.close();
    }
}
