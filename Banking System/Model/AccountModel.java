package Model;
import java.sql.*;

public class AccountModel {
    private static final String URL = "jdbc:mysql://localhost:3306/BankingDb";
    private static final String User = "root";
    private static final String password = "Akilesh#11";

    public void checkBalance(int CustId){
        String checkSql = "SELECT Balance FROM Account WHERE CustomerId = ?";   

        try{
            Connection con = DriverManager.getConnection(URL,User,password);
            PreparedStatement psmt = con.prepareStatement(checkSql);

            psmt.setInt(1, CustId);
            ResultSet rs = psmt.executeQuery();
            if(rs.next()){
                int balance = rs.getInt("Balance");
                System.out.println("Balance : "+balance);
            }
            else{
                System.out.println("Customer Id Not Found! Create Account");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void depositAmount(int CustId, int amount){
        String depositSql = "UPDATE Account SET Balance = Balance + ? WHERE CustomerId = ?";

        try{
            Connection con = DriverManager.getConnection(URL,User,password);
            PreparedStatement psmt = con.prepareStatement(depositSql);

            psmt.setInt(1, amount);
            psmt.setInt(2, CustId);

            int rows = psmt.executeUpdate();

            if(rows>0){
                System.out.println("Amount Deposited Successfully");
            }
            else{
                System.out.println("Customer Not Found");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
    }

    public void withdrawAmount(int CustId, int amount){
        String checkSql = "SELECT Balance FROM Account WHERE CustomerId = ?";
        String withdrawSql = "UPDATE Account SET Balance = Balance - ? WHERE CustomerId = ?";

        try{
            Connection con = DriverManager.getConnection(URL,User,password);
            PreparedStatement psmt1 = con.prepareStatement(checkSql);
            PreparedStatement psmt2 = con.prepareStatement(withdrawSql);

            psmt1.setInt(1, CustId);
            ResultSet rs = psmt1.executeQuery();

            if(rs.next()){
                int balance = rs.getInt("Balance");
                if(balance>=amount){
                    psmt2.setInt(1, amount);
                    psmt2.setInt(2, CustId);
                    psmt2.executeUpdate();
                    System.out.println("Amount Withdrawal Successful! Your Amount"+amount);
                }
                else{
                    System.out.println("Insufficient Balance");
                }
            }   
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
