import java.sql.*;

public class MySql{
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/MyDb";
        String user = "root";
        String password = "Akilesh#11";

        String sql = "INSERT INTO User(id,name,mobile) VALUES (?,?,?)";
        
        try{
            Connection con = DriverManager.getConnection(url,user,password);
            PreparedStatement psmt = con.prepareStatement(sql);

            psmt.setInt(1, 101);
            psmt.setString(2, "Akilesh");
            psmt.setInt(3, 108);

            int rows = psmt.executeUpdate();

            if(rows > 0){
                System.out.println("Data Inserted Successfully");
            }
        }
        catch(SQLException e){
            System.out.print(e);
        }
    }
}