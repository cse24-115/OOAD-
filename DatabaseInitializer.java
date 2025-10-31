import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    // Method to connect to SQLite database
    public static Connection connect() {
        Connection conn = null;
        try {
            // SQLite database file (will auto-create if missing)
            String url = "jdbc:sqlite:bank.db";
            conn = DriverManager.getConnection(url);
            System.out.println("✅ Database connected successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed: " + e.getMessage());
        }
        return conn;
    }

    // Method to create tables
    public static void initialize() {
        String createCustomerTable = """
            CREATE TABLE IF NOT EXISTS Customer (
                id TEXT PRIMARY KEY,
                phone TEXT NOT NULL,
                email TEXT NOT NULL,
                password TEXT NOT NULL
            );
        """;

        String createAccountTable = """
            CREATE TABLE IF NOT EXISTS Account (
                account_number TEXT PRIMARY KEY,
                customer_id TEXT NOT NULL,
                balance REAL DEFAULT 0.0,
                FOREIGN KEY (customer_id) REFERENCES Customer(id)
            );
        """;

        String createTransactionTable = """
            CREATE TABLE IF NOT EXISTS Transaction (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                account_number TEXT NOT NULL,
                type TEXT NOT NULL,
                amount REAL NOT NULL,
                date TEXT NOT NULL,
                FOREIGN KEY (account_number) REFERENCES Account(account_number)
            );
        """;

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(createCustomerTable);
            stmt.execute(createAccountTable);
            stmt.execute(createTransactionTable);
            System.out.println("✅ All tables created successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error initializing database: " + e.getMessage());
        }
    }
}
