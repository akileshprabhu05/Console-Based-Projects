package Controller;
import Model.CustomerModel;
import Model.AccountModel;

public class BankController {
    CustomerModel customer = new CustomerModel();
    AccountModel account = new AccountModel();

    public void checkBalance(int CustId){
        account.checkBalance(CustId);
    }
    
    public void depositAmount(int CustId, int amount){
        account.depositAmount(CustId, amount);
    }
    
    public void withdrawAmount(int CustId, int amount){
        account.withdrawAmount(CustId, amount);
    }
    
    public void createCustomer(String name, String email, String address, int accType, int balance, int pin){
        customer.createCustomer(name, email, address, accType, balance, pin);
    }

    public void viewCustomer(int CustId){
        customer.viewCustomer(CustId);
    }
    
    public boolean loginCustomer(int CustId, int pin){
        return customer.loginCustomer(CustId, pin);
    }
}

