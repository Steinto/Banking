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
    public Banking() {
        Scanner keyboard = new Scanner(System.in);
        try{
            File myFile = new File("bankData - bankData.csv");
            if(myFile.createNewFile()){
                System.out.println("File created: " + myFile.getName());
            }else{
                System.out.println("File already exists.");
            }
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        // accounts = Load();
        Load();
        
        
        
        while(cont){
            // System.out.print('\u000C');
            System.out.println("Enter first and last legal name of customer below, including capitals");
            String name = keyboard.nextLine();
            // System.out.println(name);
            // System.out.println((HaveAccounts(name, accounts)));
            if(HaveAccounts(name) == true){
                Memberturn(name);
            }else if(HaveAccounts(name) == false){
                Nonmemberturn(name);
            }
        }
        
    }
    public void Memberturn(String name){
        System.out.print('\u000C');
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Customer: " + name);
        boolean contTurn = true;
        while(contTurn) {
            
            System.out.println("please enter account number of the account the customer wants to \ninquire about, including hyphens e.g. XX-XXXX-XXXXXXX-XX\nTo start with a new customer, input 'exit'");
            String accNumber = keyboard.nextLine();
            
            int acc = FindAccounts(name, accNumber);
            // System.out.println(acc);
            if(acc > -1){
                System.out.println("please enter nunmber corrsponding to the action you want to do \n1. show balance\n2. make a withdrawl\n3. make a deposite\n4. close account\n5. (accountant)start with new customer");
                int action = keyboard.nextInt();
                switch(action) {
                    case 1:
                        System.out.println(acc);
                        System.out.println(accounts.get(acc).getBalance());
                        break;
                    case 2:
                        System.out.println("please enter the ammount the customer wants to withdraw \nas a positive number to 2 decemal places");
                        double withdrawalAmmount = keyboard.nextDouble();
                        accounts.get(acc).withdraw(withdrawalAmmount);
                        break;
                    case 3:
                        System.out.println("please enter the ammount the customer wants to deposit \nas a positive number to 2 decemal places");
                        double depositAmmount = keyboard.nextDouble();
                        accounts.get(acc).deposit(depositAmmount);
                        break;
                    case 4:
                    
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
    public void Nonmemberturn(String name){
        Scanner keyboard = new Scanner(System.in);
        System.out.print('\u000C');
        System.out.println("Customer: " + name);
        System.out.println("It seems you don't currently have an account with us.\nWould you like to open one?\nEnter 'yes' or 'no' to start with a new customer");
        String action = keyboard.nextLine();
        switch(action) {
            case "yes":
                System.out.println("please enter full adress e.g. x example street");
                String adress = keyboard.nextLine();
                System.out.println("please enter the type of account you want to open\n'Savings' has a minimum balance of $" + egSavings.getMinBalance() + " and a maximum withdraw of $" + egSavings.getMaxWithdraw());
                System.out.println("'Current' has a minimum balance of $" + egCurrent.getMinBalance() + " and a maximum withdraw of $" + egCurrent.getMaxWithdraw());
                System.out.println("'Everyday' has a minimum balance of $" + egEveryday.getMinBalance() + " and a maximum withdraw of $" + egEveryday.getMaxWithdraw());
                String accType = keyboard.nextLine();
                
                accounts.add(new Account(name, adress, accType));
                System.out.println("that follows is the customers account number, \nplease make sure to ensure they write this down as they will need it to access their account\n" + accounts.get(accounts.size() - 1).getAccNumber());
                
                break;
            case "no":
                
                break;
        }
        
    }
    public int FindAccounts(String name, String accNum){
        for( int i = 0; i < accounts.size(); i++){
            if(name.equals(accounts.get(i).getName()) && accNum.equals(accounts.get(i).getAccNumber())){
                return i;
            }
        }
        return -1;
    }
    public boolean HaveAccounts(String name){
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
    public void Load(){
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
            Printall(accounts);
            
            
            
        }catch(Exception e){
            System.out.print(e);
        }
        //return accounts;
    }
    public void Printall(ArrayList<Account> arrL){
        for(int i = 0 ; i < arrL.size();  i++){
            System.out.println(arrL.get(i).getName());
        }
    }
}