package Model;
import java.util.*;

public class Customer {
    private int CustomerID;
    private String CustomerName;
    private String email;
    private String address;
    private List<Account> accounts;
    private int pin;

    public Customer(int CustomerID, String CustomerName, String email, String address, int pin) {
        this.CustomerID = CustomerID;
        this.CustomerName = CustomerName;
        this.email = email;
        this.address = address;
        this.accounts = new ArrayList<>();
        this.pin = pin;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return CustomerName;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public int getPin(){
        return pin;
    }

    @Override
    public String toString() {
        return "Customer ID: " + CustomerID + ", Name: " + CustomerName + ", Email: " + email + ", Address: " + address;
    }
}