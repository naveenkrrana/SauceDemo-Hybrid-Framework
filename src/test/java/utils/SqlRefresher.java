package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlRefresher {

    public static void main(String[] args) {
        // 1. Open the TCP connection to the H2 RAM Database
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {

            // 2. DDL (Data Definition Language) - CREATE
            // We create a table named 'Employees' with 3 columns: an integer ID, a string Name, and a string Role.
            stmt.execute("CREATE TABLE Employees (id INT, name VARCHAR(50), role VARCHAR(50))");
            System.out.println("Table 'Employees' created successfully.");

            // 3. DML (Data Manipulation Language) - INSERT
            // We insert two rows of data into the exact columns we just defined.
            stmt.execute("INSERT INTO Employees VALUES (1, 'Naveen', 'Senior QA')");
            stmt.execute("INSERT INTO Employees VALUES (2, 'Rahul', 'Developer')");
            System.out.println("Data inserted successfully.\n");

            // 4. DQL (Data Query Language) - SELECT
            // We ask the database to return ALL (*) columns for ANY row where the role is exactly 'Senior QA'
            String sqlQuery = "SELECT * FROM Employees WHERE role = 'Senior QA'";

            // We execute the query and store the returned data in a Java ResultSet object
            ResultSet rs = stmt.executeQuery(sqlQuery);

            System.out.println("--- SQL QUERY RESULTS ---");
            // The ResultSet acts like an iterator. We loop through the returned rows.
            while (rs.next()) {
                // We extract the specific Java String from the 'name' column of the database row
                String dbName = rs.getString("name");
                String dbRole = rs.getString("role");

                System.out.println("Found Record: Name = " + dbName + ", Role = " + dbRole);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}