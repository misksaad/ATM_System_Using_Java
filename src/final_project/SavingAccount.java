package final_project;
import java.io.*;
import java.util.ArrayList;
public class SavingAccount extends Account {

    static float bankCommission = 0.02f;
    static boolean flag = false;
    public static ArrayList<String> transactions = new ArrayList<>();

    public static float checkBalance() {
        return Account.balancee;
    }

    public static float deposit(float amount){
        if(amount>0){
            balancee = (balancee + amount) - (amount * bankCommission);
            transactions.add("\nYou've deposited " + amount + " form your saving account\nyour current balance: " + balancee + "$");
            System.out.println("done successfully");
        }
        try {
            writeNewData(amount);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        flag = true;
        return balancee;
    }

    public static float withDraw(float amount) {
        if (amount>0&&amount <= balancee && !Account.isIsBlocked()) {
            balancee = (balancee - amount) - (amount * bankCommission);
            transactions.add("\nYou've withdraw " + amount + " form your saving account\nyour current balance: " + balancee + "$");
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
        pr.print("SavingAccount");
        for (int i = 0; i < transactions.size(); i++) {
            pr.print(transactions.get(i));
        }
        pr.close();
        fw.close();
    }

    public static void writeTransactions(float amount){
        File file = new File("C:\\customers files\\" + Account.getAccountNumberr() + ".txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        PrintWriter pr = new PrintWriter(fw);
        if (flag == false) {
            pr.print("\nYou've deposited " + amount + " form your saving account\nyour current balance: " + balancee + "$");
        } else {
            pr.print("\nYou've withdraw " + amount + " form your saving account\nyour current balance: " + balancee + "$");
        }
        pr.close();
        try {
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}