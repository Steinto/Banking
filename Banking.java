/**
 * Banking test class
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

    /**

     * Constructor for Banking object, takes no input.
     * 
     * Attempts to create file, wont work with file already there.
     * Starts main loop.
     */
    public Banking() {
        Scanner keyboard = new Scanner(System.in);
        File bankData = new File("bankData - bankData.csv");
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
        read();

        while(cont){
            System.out.print('\u000C');

            String name = "";
            boolean legalName = false;
            while(legalName == false){ //Keep asking untill 'name' is a legal name
                System.out.println("Enter first and last legal name of customer below, including capitals\nEnter 'exit' to print the end of day report and stop programme ");
                name = keyboard.nextLine();
                if(name.equals("exit")){
                    legalName = true;
                }else{
                    legalName = legalNameCheck(name);
                    System.out.print('\u000C');
                    System.out.println("'" + name + "' is not a legal full name, please re-enter\n");
                }
            }

            if(name.equals("exit")){
                double[] report = generateReport();
                System.out.print('\u000C');
                System.out.println("Total money in bank: $" + report[0] + "\nTotal transactions: $" + report[1] + "\n-programme terminated");
                write();
                cont = false;
            }else{
                if(findAccounts(name) > -1){
                    memberTurn(name);
                }else if(findAccounts(name) == -1){
                    nonMemberTurn(name);
                }
            }

        }
    }

    /**

     * takes no input.
     * 
     * Adds balances af all accounts,
     * sums total transactions of all open accounts.
     * 
     * Returns double array with 2 values, total balance and total transactions.
     */
    public double[] generateReport(){
        double[] report = new double[2];
        for(int i = 0;i < accounts.size();i++){
            report[0] = report[0] + accounts.get(i).getBalance();
            report[1] = report[1] + accounts.get(i).getTotalTransactions();
        }
        return report;
    }

    /**
     * 
     * 
     */
    public void memberTurn(String name){
        Scanner keyboard = new Scanner(System.in);
        boolean accountTurn = true;
        System.out.print('\u000C');
        int account = findAccounts(name);

        System.out.println("Customer: " + name);
        System.out.println("\n===WARMING READ THE FOLLOWING LINES===\n\nThe customer should have valid identification e.g.\n(drivers lisence, passport, birth certificate)\nif they don't have any identification don't let them access the account\n\nWhat follows is the customers account number, to access the account the customer MUST know this number\nask for this number and if they don't know it dont allow them to acces the account\nenter anything to move on\n\nAccount Number: " + accounts.get(account).getAccNumber());
        keyboard.nextLine();
        System.out.print('\u000C');
        while(accountTurn) {
            int action = checkInt("Customer: " + name + "\nAddress: " + accounts.get(account).getAddress() + "\nAccount Number: " + accounts.get(account).getAccNumber() + "\nAccount Type: " + accounts.get(account).getAccType() + "\nBalance: $" + accounts.get(account).getBalance() + "\n\nplease enter number corrsponding to the action the customer/you wants to do \n'1' make a withdrawal\n'2' make a deposit\n'3' close account\n'4' (Teller) start with new customer", 4);
            switch(action) {
                case 1:
                    System.out.print('\u000C');
                    double withdrawalAmmount = checkDouble("Please enter the ammount the customer wants to withdraw \nas a positive number to 2 decemal places\nif the number has more than 2 decimal places it will be rounded down");
                    System.out.print('\u000C');
                    accounts.get(account).withdraw(withdrawalAmmount);
                    break;
                case 2:
                    System.out.print('\u000C');
                    double depositAmmount = checkDouble("please enter the ammount the customer wants to deposit \nas a positive number to 2 decemal places\nif the number has more than 2 decimal plaves it will be rounded down");
                    System.out.print('\u000C');
                    accounts.get(account).deposit(depositAmmount);
                    break;
                case 3:
                    System.out.print('\u000C');
                    boolean verification = false;
                    while(verification == false){
                        System.out.println("===WARNING THIS WILL PERMINANTLY DELETE THE ACCOUNT===\n\nif the customer still wants to perminantly delete account enter 'yes'\nto go back enter 'no'");
                        String input = keyboard.nextLine();
                        switch(input){
                            case "yes":
                                accounts.remove(account);
                                verification = true;
                                accountTurn = false;
                                break;
                            case "no":
                                System.out.print('\u000C');
                                verification = true;
                                break;
                            default:
                                System.out.print('\u000C');
                                System.out.println("Error please enter 'yes' or 'no'\n");
                                break;
                        }
                    }
                    break;
                case 4:
                    accountTurn = false;
                    break;
                default:
            }

        }

    }

    /**

     * 
     */
    public void nonMemberTurn(String name){
        Scanner keyboard = new Scanner(System.in);
        boolean turn = true;
        System.out.print('\u000C');
        while(turn){
            System.out.println("Customer: " + name + "\n");
            System.out.println("No accounts under that name\nWould the customer like to open one?\nEnter 'yes' to make a new account\nor 'no' to start with a new customer");
            String action = keyboard.nextLine();
            System.out.print('\u000C');
            switch(action) {
                case "yes":
                    String address = "";
                    boolean legalAddress = false;
                    while(legalAddress == false){
                        System.out.println("please enter full address of customer e.g. x example street\nDo not enter a comma");
                        address = keyboard.nextLine();
                        legalAddress = legalAddressCheck(address);
                        System.out.print('\u000C');
                        System.out.println("'" + address + "' is not a legal address please re-enter\n");
                    }
                    System.out.print('\u000C');
                    System.out.println("address: " + address + "\n");
                    int accountTypeAction = checkInt("please enter the number corresponding to the type of account the customer wants to open\n'1' Savings has a minimum balance of $" + egSavings.getMinBalance() + " and a maximum withdraw of $" + egSavings.getMaxWithdraw() + "\n'2' Current has a minimum balance of $" + egCurrent.getMinBalance() + " and a maximum withdraw of $" + egCurrent.getMaxWithdraw() + "\n'3' Everyday has a minimum balance of $" + egEveryday.getMinBalance() + " and a maximum withdraw of $" + egEveryday.getMaxWithdraw(), 3);
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
                    accounts.add(new Account(name, address, accountType));
                    System.out.print('\u000C');
                    System.out.println("===WARNING READ THE FOLLOWING LINES===\n\nwhat follows is the customers account number, \nplease ensure the customer writes this down as they will need it to access their account\nenter anything to move on\n\nAccount Number: " + accounts.get(accounts.size() - 1).getAccNumber());
                    keyboard.nextLine();
                    memberTurn(name);
                    turn = false;
                    break;
                case "no":
                    turn = false;
                    break;
                default:
                    System.out.print('\u000C');
                    System.out.println("Error please enter 'yes' or 'no'\n");
                    break;
            }
        }
    }

    /**

     * Takes in String input (name).
     * Checks for
     * more then 1 word,
     * each word less then 70 characters,
     * checks if each word isn't NULL,
     * checks if each word is only letters.
     * 
     * If all of these conditions are met for the enterd string, returns boolean true.
     * If the string fails one of these checks returs boolean false.
     */
    public boolean legalNameCheck(String name){
        List<String> names = Arrays.asList(name.split(" "));
        if(names.size() < 2){
            return false;
        }
        for (int i = 0; i < names.size(); i++){
            if(names.get(i).length() > 70 || names.get(i).length() < 3){
                return false;
            }
        }

        for (int i = 0; i < names.size(); i++){
            if (names.get(i) == null){
                return false;
            }
            for (int j = 0; j < names.get(i).length(); j++){
                if((Character.isLetter(names.get(i).charAt(j))) == false){
                    return false;
                }
            }
        }
        return true;
    }

    /**

     * Takes in String input (str).
     * Attempts to parse str to int,
     * if successful returns boolean true,
     * if unsuccessful returns boolean false.
     */
    public static boolean isNumeric(String str) { 
        try {  
            Integer.parseInt(str);  
            return true;
        }catch(NumberFormatException e){  
            return false;  
        }  
    }

    /**

     * Takes in a string input (address).
     * Checks for
     * more then 1 word,
     * first word is numeric,
     * remaining words are NULL,
     * remaining words are letters.
     * 
     * If all of these conditions are met for the enterd string, returns boolean true.
     * If the string fails one of these checks returs boolean false.
     */
    public boolean legalAddressCheck(String address){
        List<String> addressData = Arrays.asList(address.split(" "));
        if(addressData.size() < 2){
            return false;
        }
        if(isNumeric(addressData.get(0)) == false){
            return false;
        }
        for (int i = 1; i < addressData.size(); i++){
            if (addressData.get(i) == null){
                return false;
            }
            for (int j = 1; j < addressData.get(i).length(); j++){
                if((Character.isLetter(addressData.get(i).charAt(j))) == false){
                    return false;
                }
            }
        }
        return true;
    }

    /**

     * Takes in a string input (name).
     * Serches account arraylist for an Account with the same name,
     * returns the index of that account in the arraylist as an integer.
     */
    public int findAccounts(String name){
        for( int i = 0; i < accounts.size(); i++){
            if(name.equals(accounts.get(i).getName())){
                return i;
            }
        }
        return -1;
    }

    /**
    
     * Takes no input.
     * 
     * Tries to write all data to bankData - bankData.csv.
     * Returns nothing.
     */
    public void write(){
        File bankData = new File("bankData - bankData.csv");
        try{
            FileWriter myWriter = new FileWriter(bankData);
            for(int i = 0; i < accounts.size(); i++){
                myWriter.write(accounts.get(i).getName() + ",");
                myWriter.write(accounts.get(i).getAddress() + ",");
                myWriter.write(accounts.get(i).getAccNumber() + ",");
                myWriter.write(accounts.get(i).getAccType() + ",");
                myWriter.write(accounts.get(i).getBalance() + "\n");
            }
            myWriter.flush();
            myWriter.close();
        }catch(IOException e){
            System.out.println("error couldnt write to file");
        }
    }

    /**
    
     * Rakes no input.
     * 
     * Tries to read data from bankData - bankData.csv and create Account objects depending on data in an arraylist.
     * 
     * Return nothing.
     */
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
                accounts.add(new Account(arrL.get(0), arrL.get(1), arrL.get(2), arrL.get(3), Double.parseDouble(arrL.get(4))));
            }
        }catch(Exception e){
            System.out.print(e);
        }
    }

    /**
    
     * Takes String as input (msg).
     * 
     * Attempts to parse uset input as double,
     * if successful returns the parsed double.
     * If unseccessful askes again.
     */
    public double checkDouble(String msg){
        Scanner keyboard = new Scanner(System.in);
        boolean cont = true;
        double input1 = 0;
        while(cont){
            System.out.println(msg);
            String input = keyboard.nextLine();
            Scanner scanner = new Scanner(input);
            try {
                input1 = Double.parseDouble(input);
                System.out.print('\u000C');
                System.out.println("Error please re-enter\n");
                if(input1 >= 0){
                    cont = false;
                }
            } catch (NumberFormatException e) {
                System.out.print('\u000C');
                System.out.println("Error please re-enter\n");
            }
        }
        return input1;
    }

    /**
    
     * Takes String as input for mesgae to ask user (msg).
     * Takes int as input for range of taken input (expectedInputRange);
     * 
     * Attempts to parse uset input as double,
     * if successful returns the parsed double.
     * If unseccessful askes again.
     */
    public int checkInt(String msg, int expectedInputRange){
        Scanner keyboard = new Scanner(System.in);
        boolean cont = true;
        int input1 = 0;
        while(cont){
            System.out.println(msg);
            String input = keyboard.nextLine();
            Scanner scanner = new Scanner(input);
            try {
                input1 = Integer.parseInt(input);
                if(input1 <= expectedInputRange && input1 > 0){
                    cont = false;
                }else{
                    System.out.print('\u000C');
                    System.out.println("'" + input1 + "' is not a valid answer\n");
                }
            } catch (NumberFormatException e) {
                System.out.print('\u000C');
                System.out.println("'" + input + "' is not a valid answer\n");
            }
        }
        return input1;
    }
}