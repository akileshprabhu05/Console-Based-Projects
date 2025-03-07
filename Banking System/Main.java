import java.util.*;
import Controller.*;

public class Main {
    public static void main(String[] args){
        BankController Controller = new BankController();
        Scanner scanner =new Scanner(System.in);

        while(true){
            System.out.println("<------Welcome to SBI Bank------>");
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

                        if(Controller.loginCustomer(CustId, Pin)){
                            System.out.println("Login Success, Happy Banking!!");

                            boolean flag = true;
                            while(flag){
                                System.out.println("1. View Account Details");
                                System.out.println("2. Check Balance");
                                System.out.println("3. Deposit Ammount");
                                System.out.println("4. Withdraw Ammount");
                                System.out.println("5. LogOut");
                                int SubChoice = scanner.nextInt();
                                switch (SubChoice) {
                                    case 1:
                                        Controller.viewCustomer(CustId);
                                        break;
                                    
                                    case 2:
                                        Controller.checkBalance(CustId);
                                        break;
                                    
                                    case 3:
                                        System.out.println("Enter Amount to Deposit");
                                        int amount = scanner.nextInt();
                                        Controller.depositAmount(CustId, amount);
                                        break;
                                    
                                    case 4:
                                        System.out.println("Enter Amount");
                                        int Amount = scanner.nextInt();
                                        Controller.withdrawAmount(CustId, Amount);
                                        break;
                                    
                                    case 5:
                                        System.out.println("Exiting...");
                                        flag=false;
                                        break;
                                    
                                    default:
                                        System.out.println("Enter a valid choice");
                                        break;
                                }
                            }
                        }
                        else{
                            System.out.println("Invalid Customer Id or Pin");
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

    }
}
