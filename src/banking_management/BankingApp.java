package banking_management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {

    private static final String url="jdbc:mysql://localhost:3306/banking_system";

    private static final String username="root";

    private static final String password="root";

    public static void main(String[] args) throws ClassNotFoundException {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Scanner scanner=new Scanner(System.in);

            User user=new User(con,scanner);
            Accounts accounts=new Accounts(con,scanner);
            AccountManager accountManager=new AccountManager(con,scanner);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
