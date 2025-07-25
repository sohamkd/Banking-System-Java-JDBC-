package banking_management;

import java.sql.Connection;
import java.util.Scanner;

public class Accounts {

    private Connection connection;

    private Scanner scanner;

    public Accounts(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }
}
