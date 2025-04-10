/**
 * Class for accounts
 *
 * Toby S
 * 31/03/2025
 */
public class Account
{
    // constant variables
    final String branchNumber = "0101";
    final double maxWithdrawSavings = 5000;
    final double maxWithdrawEveryday = 5000;
    final double maxWithdrawCurrent = 5000;
    final double minBalanceCurrent = -1000;
    final double minBalanceEveryday = 0;
    final double minBalanceSavings = 0;
    // instance variables
    private String name;
    private String Address;
    private String accNumber;
    private String accType;
    private double balance;
    private double minBalance;
    private double maxWithdraw;
    private double totalTransactions = 0.0;

    /**

     * Takes inputs 4 * String, double.
     * Constructor for objects of class Account when reading from bankData - bankData.csv.
     */
    public Account(String name, String Address, String accNumber, String accType, double balance)
    {
        this.name = name;
        this.Address = Address;
        this.accNumber = accNumber;
        this.accType = accType;
        this.balance = balance;

        switch(this.accType){
            case "Current":
                this.minBalance = minBalanceCurrent;
                this.maxWithdraw = maxWithdrawCurrent;
                break;
            case "Savings":
                this.minBalance = minBalanceSavings;
                this.maxWithdraw = maxWithdrawSavings;
                break;
            case "Everyday":
                this.minBalance = minBalanceEveryday;
                this.maxWithdraw = maxWithdrawEveryday;
                break;
        }
    }

    /**

     * Takes inputs 3 * String.
     * Constructor for objects of class Account when creating new.
     */
    public Account(String name, String Address, String accType)
    {
        this.name = name;
        this.Address = Address;
        this.accNumber = generateAccountNumber();
        this.accType = accType;
        this.balance = 0;

        switch(this.accType){
            case "Current":
                this.minBalance = minBalanceCurrent;
                this.maxWithdraw = maxWithdrawCurrent;
                break;
            case "Savings":
                this.minBalance = minBalanceSavings;
                this.maxWithdraw = maxWithdrawSavings;
                break;
            case "Everyday":
                this.minBalance = minBalanceEveryday;
                this.maxWithdraw = maxWithdrawEveryday;
                break;
        }
    }

    /**

     * Takes input String.
     * Constructor for objects of class Account when creating example Account.
     */
    public Account(String accType)
    {
        this.accType = accType;

        switch(this.accType){
            case "Current":
                this.minBalance = minBalanceCurrent;
                this.maxWithdraw = maxWithdrawCurrent;
                break;
            case "Savings":
                this.minBalance = minBalanceSavings;
                this.maxWithdraw = maxWithdrawSavings;
                break;
            case "Everyday":
                this.minBalance = minBalanceEveryday;
                this.maxWithdraw = maxWithdrawEveryday;
                break;
        }
    }

    /**

     * Takes no input.
     * 
     * Generates new account number according to NZ account number conventions.
     * Returns account number.
     */
    public String generateAccountNumber(){
        double num = Math.floor(Math.random() * (10000000 - 1000000 + 1) + 1000000);
        int random7Digit = (int) Math.round(num);
        String accNumber = "08-" + this.branchNumber + "-" + random7Digit + "-00";
        return accNumber;
    }
    
    /**

     * Takes a double as input (depositAmmount)
     * 
     * Checkes the deposit ammount is valid,
     * if deposit ammount is valid changes balance with regard to deposit ammount.
     * 
     * This function doesnt return anything.
     */
    public void deposit(double depositAmmount){
        depositAmmount = round2dp(depositAmmount);
        if(depositAmmount >= 0){
            this.totalTransactions = round2dp(this.totalTransactions + depositAmmount);
            this.balance = round2dp(this.balance + depositAmmount);
        }else{
            System.out.println("your deposit is invalid");
        }
    }

    /**

     * Takes a double as input (withdrawAmmount)
     * 
     * Checkes the withdraw ammount is valid,
     * if deposit ammount is valid changes balance with regard to withdraw ammount.
     * 
     * This function doesnt return anything.
     */
    public void withdraw(double withdrawAmmount){
        withdrawAmmount = round2dp(withdrawAmmount);
        double i = this.balance - withdrawAmmount;
        if(i > this.minBalance){
            this.totalTransactions = round2dp(this.totalTransactions - withdrawAmmount);
            this.balance = round2dp(this.balance - withdrawAmmount);
        }else{
            System.out.println("your withdrawal is invalid");
        }
    }

    /**

     * Takes an input as double (num).
     * 
     * Returns double (num) rounded to 2 decimal places.
     */
    public double round2dp(double num){
        num = num * 100;
        num = Math.floor(num);
        num = num / 100;
        return num;
    }

    /**

     * Each of the following functions take no input and return a specified variable.
     */
    public double getMaxWithdraw(){
        return(this.maxWithdraw);
    }

    public double getMinBalance(){
        return(this.minBalance);
    }

    public String getName(){
        return(this.name);
    }

    public String getAddress(){
        return(this.Address);
    }

    public String getAccNumber(){
        return(this.accNumber);
    }

    public String getAccType(){
        return(this.accType);
    }

    public double getBalance(){
        return(this.balance);
    }

    public double getTotalTransactions(){
        return(this.totalTransactions);
    }

}