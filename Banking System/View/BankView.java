package View;
import Model.*;
import java.util.*;

public class BankView {
    public void displayCustomer(List<Customer> customers) {
        for(Customer c : customers) {
            System.out.print("Customer ID: "+c.getCustomerID()+" Customer Name: "+c.getName());
            for(Account acc : c.getAccounts()){
                System.out.println(" "+ acc);
            }
            System.out.println("<----------------->");
        }
    }

}
