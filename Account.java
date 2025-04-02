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
    private String name;
    private String adress;
    private String accNumber;
    private String accType;
    private double balance;
    private boolean active;
    private double minBalance;
    private double maxWithdraw;
    
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
                this.minBalance = -1000;
                this.maxWithdraw = 5000;
                break;
            case "Savings":
                this.minBalance = 0;
                this.maxWithdraw = 5000;
                break;
            case "Everyday":
                this.minBalance = 0;
                this.maxWithdraw = 5000;
                break;
        }
        
    }
    public Account(String name, String adress, String accType)
    {
        this.name = name;
        this.adress = adress;
        this.accNumber = generateAccNumber();
        this.accType = accType;
        this.balance = 0;
        this.active = true;
        
        switch(this.accType){
            case "Current":
                this.minBalance = -1000;
                this.maxWithdraw = 5000;
                break;
            case "Savings":
                this.minBalance = 0;
                this.maxWithdraw = 5000;
                break;
            case "Everyday":
                this.minBalance = 0;
                this.maxWithdraw = 5000;
                break;
        }
        
    }
    public Account(String accType)
    {
        this.accType = accType;
        this.active = false;
        
        switch(this.accType){
            case "Current":
                this.minBalance = -1000;
                this.maxWithdraw = 5000;
                break;
            case "Savings":
                this.minBalance = 0;
                this.maxWithdraw = 5000;
                break;
            case "Everyday":
                this.minBalance = 0;
                this.maxWithdraw = 5000;
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
    public void changeState(){
        if(this.active = false){
            this.active = true;
        }else if(this.active = true){
            this.active = false;
        }
    }
    public String generateAccNumber(){
        Random random = new Random();
        String accNumber = random.nextInt(100) + "-" + random.nextInt(10000) + "-" + random.nextInt(10000000) + "-" + random.nextInt(100);
        return accNumber;
    }
    public void deposit(double depositAmmount){
        this.balance = this.balance + depositAmmount;
    }
    public void withdraw(double withdrawAmmount){
        if((this.balance - withdrawAmmount) > this.minBalance){
            this.balance = this.balance - withdrawAmmount;
        }else{
            System.out.println("your withdrawl is invalid");
        }
    }
}