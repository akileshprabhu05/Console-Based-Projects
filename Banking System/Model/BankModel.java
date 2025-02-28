package Model;
import java.util.*;

public class BankModel {
    private Map<Integer, Customer> customerMap;  

    public BankModel() {
        customerMap = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        customerMap.put(customer.getCustomerID(), customer);
    }

    public int getCustomerCount() {
        return customerMap.size();
    }

    public Collection<Customer> getAllCustomers() {
        return customerMap.values();
    }    
    
    public Customer findCustomerById(int customerId) {
        return customerMap.get(customerId);
    }

    public boolean isCustomerExists(int customerId) {
        return customerMap.containsKey(customerId);
    }
}
