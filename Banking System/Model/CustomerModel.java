package Model;

import java.sql.*;
import java.util.Random;

public class CustomerModel {
    private static final String URL = "jdbc:mysql://localhost:3306/BankingDb";
    private static final String User = "root";
    private static final String password = "Akilesh#11";

    public boolean loginCustomer(int CustId, int pin){
        String loginSql = "SELECT CustomerID FROM Customer WHERE CustomerId = ? AND Pin = ?";
        try{
            Connection con = DriverManager.getConnection(URL,User,password);
            PreparedStatement psmt = con.prepareStatement(loginSql);

            psmt.setInt(1, CustId);
            psmt.setInt(2, pin);

            ResultSet rs = psmt.executeQuery();
            return rs.next();
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public void viewCustomer(int CustId){
        String viewSql = "SELECT CustomerName, Email, Address FROM Customer WHERE CustomerId = ?";

        try{
            Connection con = DriverManager.getConnection(URL,User,password);

            PreparedStatement psmt = con.prepareStatement(viewSql);
            psmt.setInt(1, CustId);
            ResultSet rs = psmt.executeQuery();

            if(rs.next()){
                System.out.println("Name: " + rs.getString("CustomerName"));
                System.out.println("Email: " + rs.getString("Email"));
                System.out.println("Address: "+ rs.getString("Address"));
            }
            else{
                System.out.println("Customer Id Not Found!");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    public void createCustomer(String name, String email, String address, int accType, int balance, int pin){
        String customerSql = "INSERT INTO Customer (CustomerName, Email, Address, Pin) VALUES (?,?,?,?)";
        String accountSql = "INSERT INTO ACCOUNT (AccountId, CustomerId, Balance, AccountType) VALUES (?,?,?,?)";

        try {
            Connection conn = DriverManager.getConnection(URL, User, password);

            PreparedStatement psmt1 = conn.prepareStatement(customerSql,Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psmt2 = conn.prepareStatement(accountSql);

            psmt1.setString(1, name);
            psmt1.setString(2, email);
            psmt1.setString(3, address);
            psmt1.setInt(4, pin);
            psmt1.executeUpdate();

            ResultSet rs = psmt1.getGeneratedKeys();
            int CustId = -1;
            if(rs.next()){
                CustId = rs.getInt(1);
            }
            int AccountId = CustId * 10 + new Random().nextInt(10);
            psmt2.setInt(1, AccountId);
            psmt2.setInt(2, CustId);
            psmt2.setInt(3, balance);
            psmt2.setString(4,(accType==1) ? "Loan" : "Savings");
            psmt2.executeUpdate();

            System.out.println("Account Created Successfully! Your Customer Id is :" + CustId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
