/**
 * Main
 *
 * Toby S
 * 31/03/2025
 */
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;
import java.util.Arrays;
public class Banking
{
    public ArrayList<Account> accounts = new ArrayList<Account>();
    public Account egSavings = new Account("Savings");
    public Account egCurrent = new Account("Current");
    public Account egEveryday = new Account("Everyday");
    public boolean cont = true;
    public File bankData = new File("bankData - bankData.csv");

    public Banking() {
        Scanner keyboard = new Scanner(System.in);
        try{
            if(bankData.createNewFile()){
                System.out.println("File created: " + bankData.getName());
            }else{
                System.out.println("File already exists.");
            }
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        // accounts = Load();
        read();

        while(cont){
            // System.out.print('\u000C');
            System.out.println("Enter first and last legal name of customer below, including capitals.\nEnter 'exit' to print the end of day report and stop program ");
            String name = keyboard.nextLine();
            if(name.equals("exit")){
                double[] report = generateReport();
                System.out.println(report[0]);
                System.out.println(report[1]);
                write();
                cont = false;

            }else{
                if(haveAccounts(name) == true){
                    memberTurn(name);
                }else if(haveAccounts(name) == false){//remove have accounts
                    nonMemberTurn(name);
                }
            }
        }

    }

    public double[] generateReport(){
        double[] report = new double[2];
        for(int i = 0;i < accounts.size();i++){
            report[0] = report[0] + accounts.get(i).getBalance();
            // System.out.println(accounts.get(i).getBalance());
            // System.out.println(report[0]);
            report[1] = report[1] + accounts.get(i).getTotalTransactions();
        }

        return report;
    }

    public void memberTurn(String name){
        System.out.print('\u000C');
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Customer: " + name);
        boolean contTurn = true;
        System.out.println("please enter account number of the account the customer wants to \ninquire about, including hyphens e.g. XX-XXXX-XXXXXXX-XX\nTo start with a new customer, input 'exit'");
        String accNumber = keyboard.nextLine();
        
        if(accNumber.equals("exit")){
            contTurn = false;
        }
        
        int account = findAccounts(name, accNumber);
        while(contTurn) {

            // System.out.println(acc);
            if(account > -1){
                System.out.println("please enter nunmber corrsponding to the action you want to do \n1. show balance\n2. make a withdrawl\n3. make a deposite\n4. close account\n5. (accountant)start with new customer");
                int action = keyboard.nextInt();
                switch(action) {
                    case 1:
                        // System.out.println(account);
                        System.out.println(accounts.get(account).getBalance());
                        break;
                    case 2:
                        System.out.println("please enter the ammount the customer wants to withdraw \nas a positive number to 2 decemal places");
                        double withdrawalAmmount = keyboard.nextDouble();
                        accounts.get(account).withdraw(withdrawalAmmount);
                        break;
                    case 3:
                        System.out.println("please enter the ammount the customer wants to deposit \nas a positive number to 2 decemal places");
                        double depositAmmount = keyboard.nextDouble();
                        accounts.get(account).deposit(depositAmmount);
                        break;
                    case 4:
                        accounts.get(account).setState(false);
                        break;
                    case 5:
                        contTurn = false;
                        break;
                    default:
                }
            }else{
                System.out.println("not a valid account number\nPlease re-enter");
            }
        }

    }

    public void nonMemberTurn(String name){
        Scanner keyboard = new Scanner(System.in);
        System.out.print('\u000C');
        System.out.println("Customer: " + name);
        System.out.println("It seems you don't currently have an account with us.\nWould you like to open one?\nEnter 'yes' to make a new account\nor 'no' to start with a new customer");
        String action = keyboard.nextLine();
        switch(action) {
            case "yes":
                System.out.println("please enter full adress e.g. x example street");
                String adress = keyboard.nextLine();
                System.out.println("please enter the number coresponding to the type of account you want to open\n1. 'Savings' has a minimum balance of $" + egSavings.getMinBalance() + " and a maximum withdraw of $" + egSavings.getMaxWithdraw());
                System.out.println("2. 'Current' has a minimum balance of $" + egCurrent.getMinBalance() + " and a maximum withdraw of $" + egCurrent.getMaxWithdraw());
                System.out.println("3. 'Everyday' has a minimum balance of $" + egEveryday.getMinBalance() + " and a maximum withdraw of $" + egEveryday.getMaxWithdraw());
                int accountTypeAction = keyboard.nextInt();
                String accountType = " ";
                switch(accountTypeAction) {
                    case 1:
                        accountType = "Savings";
                        break;
                    case 2:
                        accountType = "Current";
                        break;
                    case 3:
                        accountType = "Everyday";
                        break;

                }

                //add verification of data
                accounts.add(new Account(name, adress, accountType));
                System.out.println("that follows is the customers account number, \nplease make sure to ensure they write this down as they will need it to access their account\n" + accounts.get(accounts.size() - 1).getAccNumber());

                memberTurn(name);
                break;
            case "no":
                System.out.println("if you dont want to open an account\nwe can help you today");
                break;
        }

    }

    public int findAccounts(String name, String accNum){
        for( int i = 0; i < accounts.size(); i++){
            if(name.equals(accounts.get(i).getName()) && accNum.equals(accounts.get(i).getAccNumber())){
                return i;
            }
        }
        return -1;
    }

    public boolean haveAccounts(String name){
        for( int i = 0; i < accounts.size(); i++){
            // System.out.println(accounts.get(i).getName());
            // System.out.println(name);
            if(name.equals(accounts.get(i).getName())){
                // System.out.println("3");
                return true;
            }
        }
        return false;
    }

    public void write(){
        try{
            FileWriter myWriter = new FileWriter(bankData);
            for(int i = 0; i < accounts.size(); i++){
                System.out.println("hiiiiiiiiiii");
                myWriter.write("wsg");
                myWriter.write(accounts.get(i).getName() + "," + accounts.get(i).getAdress());
            }
            
            myWriter.flush();
            myWriter.close();
        }catch(IOException e){
            System.out.println("error couldnt write to file");
        }

    }
    public void read(){
        try{
            File myFile = new File("bankData - bankData.csv");
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()) {
                List<List<String>> data = new ArrayList<>();
                ArrayList<String> arrL = new ArrayList<String>();
                List<String> lineData = Arrays.asList(myReader.nextLine().split(","));
                data.add(lineData);

                for (List<String> list : data) {
                    for (String str : list) {
                        arrL.add(str);
                    }
                }

                // Account account = new Account(arrL.get(0), arrL.get(1), arrL.get(2), arrL.get(3), Double.parseDouble(arrL.get(4)));
                accounts.add(new Account(arrL.get(0), arrL.get(1), arrL.get(2), arrL.get(3), Double.parseDouble(arrL.get(4))));
            }
            printAll(accounts);

        }catch(Exception e){
            System.out.print(e);
        }
        //return accounts;
    }

    public void printAll(ArrayList<Account> arrL){
        for(int i = 0 ; i < arrL.size();  i++){
            System.out.println(arrL.get(i).getBalance());
        }
    }
}