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

            String email;
            long account_number;

            while (true)
            {
                System.out.println("*** Welcome To Banking System ***");
                System.out.println();
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println("Enter your choice:");
                int choice1=scanner.nextInt();
                switch (choice1)
                {
                    case 1:
                        user.register();
                        break;

                    case 2:
                        email= user.login();
                        if(email!=null)
                        {
                            System.out.println();
                            System.out.println("User Logged In!");
                            if (!accounts.account_exist(email))
                            {
                                System.out.println();
                                System.out.println("1. Open a new bank account.");
                                System.out.println("2. Exit");

                                if (scanner.nextInt()==1)
                                {
                                    account_number=accounts.open_account(email);
                                    System.out.println("Account created successfully.");
                                    System.out.println("Your account number is:"+account_number);
                                }
                                else {
                                    break;
                                }
                            }
                            account_number=accounts.getAccount_number(email);
                            int choice2=0;
                            while (choice2!=5)
                            {
                                System.out.println();
                                System.out.println("1. Debit Money");
                                System.out.println("2. Credit Money");
                                System.out.println("3. Transfer Money");
                                System.out.println("4. Check Balance");
                                System.out.println("5. Logout");
                                System.out.println("Enter Your Choice:");
                                choice2=scanner.nextInt();
                                switch (choice2)
                                {
                                    case 1:
                                        accountManager.debit_money(account_number);
                                        break;

                                    case 2:
                                        accountManager.credit_money(account_number);
                                        break;

                                    case 3:
                                        accountManager.transfer_money(account_number);
                                        break;

                                    case 4:
                                        accountManager.getBalance(account_number);
                                        break;

                                    case 5:
                                        break;

                                    default:
                                        System.out.println("Enter valid choice.");
                                        break;
                                }
                            }
                        }

                        else {
                            System.out.println("Incorrect email or password.");
                        }

                    case 3:
                        System.out.println("Thank You For Using Bank System.");
                        System.out.println("Exiting System.");
                        return;

                    default:
                        System.out.println("Enter Valid Choice.");
                        break;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
