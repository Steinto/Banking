
/**
 * Class for accounts
 *
 * Toby S
 * 31/03/2025
 */
public class Account
{
    // instance variables
    private String name;
    private String adress;
    private String accNumber;
    private String accType;
    private double balance;
    private boolean active;
    double minBalanceSavings = 0;
    double minBalanceEveryday = 0;
    double minBalanceCurrent = -1000;

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
        
    }

    public boolean getState(){
        return(this.active);
    }
    public void changeState(){
        if(this.active = false){
            this.active = true;
        }else if(this.active = true){
            this.active = false;
        }
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
    public void deposit(double depositAmmount){
        this.balance = this.balance + depositAmmount;
    }
    public void withdraw(double withdrawAmmount){
        switch(this.accType){
            case "Current":
                if((this.balance - withdrawAmmount) > this.minBalanceCurrent){
                    this.balance = this.balance - withdrawAmmount;
                }else{
                    System.out.println("your withdrawl is invalid");
                }
                break;
            case "Savings":
                if((this.balance - withdrawAmmount) > this.minBalanceSavings){
                    this.balance = this.balance - withdrawAmmount;
                }else{
                    System.out.println("your withdrawl is invalid");
                }
                break;
            case "Everyday":
                if((this.balance - withdrawAmmount) > this.minBalanceEveryday){
                    this.balance = this.balance - withdrawAmmount;
                }else{
                    System.out.println("your withdrawl is invalid");
                }
                break;
        }
    }
}
