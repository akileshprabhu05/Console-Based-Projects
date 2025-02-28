package Controller;
import java.util.ArrayList;

import Model.*;
import View.*;

public class BankController {
    private BankModel model;
    private BankView view;

    public BankController(BankModel model, BankView view){
        this.model = model;
        this.view = view;
    }

    public void createCustomer(String name, String email, String address, int accType, int balance, int pin){
        int newCustomerId = 100+(model.getCustomerCount()+1);
        Customer newCustomer = new Customer(newCustomerId, name, email, address, pin);
        Account newAccount;
        if(accType==1){
            newAccount = new LoanAccount(1,balance);
        }
        else{
            newAccount = new SavingsAccount(2,balance);
        }

        newCustomer.addAccount(newAccount);
        model.addCustomer(newCustomer);
        System.out.println("Account Created Successfully! Your CustomerID is : "+newCustomer.getCustomerID());
    }

    public void displayCustomer(){
        view.displayCustomer(new ArrayList<>(model.getAllCustomers()));
    }

    public void checkBalance(int CustId){
        Customer customer = model.findCustomerById(CustId);
        if(customer!=null){
            for(Account acc : customer.getAccounts()){
                System.out.println("Account Id: "+ acc.getAccountId() + "Balance: "+ acc.getBalance());
            }
        }
        else{
            System.out.println("Invalid Customer Id");
        }
    }
    
    public void depositAmount(int CustId, int AccountId, int amount){
        Customer customer = model.findCustomerById(CustId);
        if(customer!=null){
            for(Account acc : customer.getAccounts()){
                if(acc.getAccountId()==AccountId){
                    acc.deposit(amount);
                    System.out.println("Amount Deposited!");
                    return;
                }
            }
            System.out.println("Invalid Account Id");
        }
        else{
            System.out.println("Invalid Customer Id");
        }
    }

    public void withdrawAmount(int CustId, int AccountId, int amount){
        Customer customer = model.findCustomerById(CustId);
        if(customer!=null){
            for(Account acc : customer.getAccounts()){
                if(acc.getAccountId()==AccountId){
                    acc.withdraw(amount);
                    return;
                }
            }
            System.out.println("Invalid Account Id");
        }
        else{
            System.out.println("Invalid Customer Id");
        }
    }
}

