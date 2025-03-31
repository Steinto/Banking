
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
        
    }
    public void withdraw(int withdrawAmmount){
        
    }
}
