import java.util.*;
import Model.*;
import View.*;
import Controller.*;
import Model.Customer;

public class Main {
    public static void main(String[] args){
        BankModel model = new BankModel();
        BankView view = new BankView();
        BankController Controller = new BankController(model, view);
        Scanner scanner =new Scanner(System.in);
        boolean flag = false;
        Customer check = null;

        while(true){
            System.out.println("<------Welcome to SBI Bank------>");

            if(!flag){
                System.out.println("1. Create Customer & Account");
                System.out.println("2. Login in to Account");
                System.out.println("3. Exit");
                System.out.println("Enter Choice");
                int Choice = scanner.nextInt();

                switch (Choice) {
                    case 1:
                        System.out.println("Enter your Name: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        System.out.println("Enter your email");
                        String email = scanner.nextLine();
                        System.out.println("Enter your Address");
                        String address = scanner.nextLine();
                        System.out.println("Enter Account Type : 1.Loan Account 2.Savings Account");
                        int accType = scanner.nextInt();
                        System.out.println("Enter Initial Amount");
                        int balance = scanner.nextInt();
                        System.out.println("Enter 4 digit pin");
                        int pin = 0;
                        while(String.valueOf(pin).length()!=4){
                            pin = scanner.nextInt();
                            if(String.valueOf(pin).length()!=4){
                                System.out.println("Enter a valid pin");
                            }
                        }
                        Controller.createCustomer(name, email, address, accType, balance, pin);
                        break;

                    case 2:
                        System.out.println("Enter your Customer Id");
                        int CustId = scanner.nextInt();
                        System.out.println("Enter Your Pin");
                        int Pin = scanner.nextInt();

                        check = model.findCustomerById(CustId);
                        if(check==null){
                            System.out.println("Invalid Customer Id.Not Found");
                        }
                        else if(check.getPin()!=Pin){
                            System.out.println("Incorrect Pin. Please try again");
                        }
                        else{
                            System.out.println("Login Successful! Welcome, "+check.getName());
                            flag=true;
                        }
                        break;
                    
                    case 3:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                        
                    default:
                        break;
                }
            }
            else{
                System.out.println("1. View Account Details");
                System.out.println("2. Check Balance");
                System.out.println("3. Deposit Ammount");
                System.out.println("4. Withdraw Ammount");
                System.out.println("5. Exit");
                int Choice = scanner.nextInt();
                int CustId = check.getCustomerID();

                switch (Choice) {
                    case 1:
                        System.out.println(model.findCustomerById(CustId));
                        break;
                    
                    case 2:
                        Controller.checkBalance(CustId);
                        break;

                    case 3:
                        System.out.println("Enter Account Id (1.Loan Account 2.Savings Account)");
                        int AccountId = scanner.nextInt();
                        System.out.println("Enter Amount to Deposit");
                        int amount = scanner.nextInt();
                        Controller.depositAmount(CustId, AccountId, amount);
                        break;

                    case 4:
                        System.out.println("Enter Account Id (1.Loan Account 2.Savings Account)");
                        int accountId = scanner.nextInt();
                        System.out.println("Enter Amount");
                        int Amount = scanner.nextInt();
                        Controller.withdrawAmount(CustId, accountId, Amount);
                        break;
                    
                    case 5:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Enter a valid choice");
                        break;
                }
            }
        }

    }
}
