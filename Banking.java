
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
        accounts = Load();
        
        
        
        boolean cont = true;
        while(cont){
            System.out.println("Enter first and last legal name of customer below, including capitals");
            String name = keyboard.nextLine();
            System.out.println("please enter account number of the account the customer wants to inquire about, including hyphens e.g. XX-XXXX-XXXXXXX-XX");
            String accNumber = keyboard.nextLine();
            // System.out.println(name);
            // System.out.println((HaveAccounts(name, accounts)));
            if(HaveAccounts(name, accounts) == true){
                Memberturn(name, accNumber, accounts);
            }else if(HaveAccounts(name, accounts) == false){
                Nonmemberturn(name, accounts);
            }
        }
        
    }
    static void Memberturn(String name, String accNumber, ArrayList<Account> accounts){
        Scanner keyboard = new Scanner(System.in);
        boolean cont = true;
        while(cont) {
            
            System.out.println("please enter nunmber corrsponding to the action you want to do \n1. show balance\n2. make a withdrawl\n3. make a deposite\n4.close account");
            int action = keyboard.nextInt();
            int acc = FindAccounts(name, accNumber, accounts);
            
            switch(action) {
                case 1:
                    System.out.println(accounts.get(acc).getBalance());
                    break;
                case 2:
                    System.out.println("please enter the ammount the customer wants to withdraw as a positive number to 2 decemal places");
                    double withdrawalAmmount = keyboard.nextDouble();
                    accounts.get(acc).withdraw(withdrawalAmmount);
                    break;
                case 3:
                    System.out.println("please enter the ammount the customer wants to deposit as a positive number to 2 decemal places");
                    double depositAmmount = keyboard.nextDouble();
                    accounts.get(acc).deposit(depositAmmount);
                    break;
                case 4:
                
                    break;
                default:
            
            }
        }
        
        
    }
    static void Nonmemberturn(String name, ArrayList<Account> accounts){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("please enter nunmber corrsponding to the action you want to do \n1. show balance\n2. make a withdrawl\n3. make a deposite\n4.close account");
        int action = keyboard.nextInt();
            
    }
    static int FindAccounts(String name, String accNum, ArrayList<Account> accounts){
        for( int i = 0; i < accounts.size(); i++){
            if(name.equals(accounts.get(i).getName()) && accNum.equals(accounts.get(i).getAccNumber())){
                return i;
            }
        }
        return -1;
    }
    static boolean HaveAccounts(String name, ArrayList<Account> accounts){
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
    static ArrayList<Account> Load(){
        
        ArrayList<Account> accounts = new ArrayList<Account>();
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

            
                Account account = new Account(arrL.get(0), arrL.get(1), arrL.get(2), arrL.get(3), Double.parseDouble(arrL.get(4)));
                accounts.add(new Account(arrL.get(0), arrL.get(1), arrL.get(2), arrL.get(3), Double.parseDouble(arrL.get(4))));
            }
            Printall(accounts);
            
            
            
        }catch(Exception e){
            System.out.print(e);
        }
        return accounts;
    }
    static void Printall(ArrayList<Account> arrL){
        for(int i = 0 ; i < arrL.size();  i++){
            System.out.println(arrL.get(i).getName());
        }
    }
}
