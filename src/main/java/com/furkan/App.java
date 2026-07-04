package com.furkan;

import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.sql.ResultSet;
import org.apache.ignite.sql.SqlRow;

import java.util.Random;

public class App {

    public static void main(String[] args) {

        try (IgniteClient client = IgniteClient.builder()
                .addresses("127.0.0.1:10800")
                .build()) {

            System.out.println("Connected to Apache Ignite!\n");

            // 1. Create table
            client.sql().execute(null,
                    """
                    CREATE TABLE IF NOT EXISTS Subscriber (
                        customerId INT PRIMARY KEY,
                        dataUsage DOUBLE,
                        smsUsage INT,
                        callUsage INT
                    )
                    """);

            // 2. Clear table
            client.sql().execute(null, "DELETE FROM Subscriber");

            System.out.println("Table cleaned.");

            // 3. Insert 5 subscribers
            for (int i = 1; i <= 5; i++) {

                client.sql().execute(
                        null,
                        "INSERT INTO Subscriber VALUES (?, ?, ?, ?)",
                        i, 0.0, 0, 0
                );
            }

            System.out.println("5 subscribers inserted.\n");

            Random random = new Random();

            // 4. Update subscribers with random usage
            for (int i = 1; i <= 5; i++) {

                double dataUsage = random.nextDouble() * 1000;
                int smsUsage = random.nextInt(500);
                int callUsage = random.nextInt(300);

                client.sql().execute(
                        null,
                        """
                        UPDATE Subscriber
                        SET dataUsage = ?, smsUsage = ?, callUsage = ?
                        WHERE customerId = ?
                        """,
                        dataUsage,
                        smsUsage,
                        callUsage,
                        i
                );
            }

            System.out.println("Random usage simulation completed.\n");

            // 5. Print final state
            ResultSet<SqlRow> result = client.sql().execute(
                    null,
                    "SELECT * FROM Subscriber ORDER BY customerId"
            );

            System.out.println("FINAL STATE:");
            System.out.println("-------------------------------");

            while (result.hasNext()) {

                SqlRow row = result.next();

                System.out.println(
                        "Customer ID: " + row.intValue("CUSTOMERID")
                                + " | Data Usage: " + row.doubleValue("DATAUSAGE")
                                + " MB | SMS Usage: " + row.intValue("SMSUSAGE")
                                + " | Call Usage: " + row.intValue("CALLUSAGE") + " min"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}