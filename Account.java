/**
 * Class for accounts
 *
 * Toby S
 * 31/03/2025
 */
import java.util.Random;
public class Account
{
    // instance variables
    final String branchNumber = "0101";
    final double maxWithdrawSavings = 5000;
    final double maxWithdrawEveryday = 5000;
    final double maxWithdrawCurrent = 5000;
    final double minBalanceCurrent = -1000;
    final double minBalanceEveryday = 0;
    final double minBalanceSavings = 0;
    private String name;
    private String adress;
    private String accNumber;
    private String accType;
    private double balance;
    private boolean active;
    private double minBalance;
    private double maxWithdraw;
    private double totalTransactions = 0.0;

    /**
     * Constructor for objects of class Account
     */
    public Account(String name, String adress, String accNumber, String accType, double balance)
    {
        this.name = name;
        this.adress = adress;
        this.accNumber = accNumber;
        this.accType = accType;
        this.balance = balance;
        this.active = true;

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

    public Account(String name, String adress, String accType)
    {
        this.name = name;
        this.adress = adress;
        this.accNumber = generateAccountNumber();
        this.accType = accType;
        this.balance = 0;
        this.active = true;

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

    public Account(String accType)
    {
        this.accType = accType;
        this.active = false;

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

    public double getMaxWithdraw(){
        return(this.maxWithdraw);
    }

    public double getMinBalance(){
        return(this.minBalance);
    }

    public boolean getState(){
        return(this.active);
    }

    public String getName(){
        return(this.name);
    }

    public String getAdress(){
        return(this.adress);
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

    public void setState(boolean State){
        this.active = State;
    }

    public String generateAccountNumber(){
        Random random = new Random();
        String accNumber = "08-" + this.branchNumber + "-" + random.nextInt(10000000) + "-00";
        return accNumber;
    }

    public void deposit(double depositAmmount){
        if(depositAmmount < this.maxWithdraw && depositAmmount > 0){
            this.totalTransactions = this.totalTransactions + depositAmmount;
            this.balance = this.balance + depositAmmount;
        }else{
            System.out.println("your deposit is invalid");
        }
    }

    public void withdraw(double withdrawAmmount){
        if((this.balance - withdrawAmmount) > this.minBalance){
            this.totalTransactions = this.totalTransactions - withdrawAmmount;
            this.balance = this.balance - withdrawAmmount;
        }else{
            System.out.println("your withdrawl is invalid");
        }
    }
}