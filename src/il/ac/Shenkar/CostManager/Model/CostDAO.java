package il.ac.Shenkar.CostManager.Model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CostDAO {

    private Connection connection;

    public CostDAO() {
        // Initialize the connection to the DerbyDB here
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:mydatabase");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }

    public void addCost(Cost cost) {
        /**
         * insert a cost record into the costs table
         */
        String sql = "INSERT INTO costs (id, category, sum, currency, description, date) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cost.getId());
            statement.setString(2, cost.getCategory());
            statement.setBigDecimal(3, cost.getSum());
            statement.setString(4, cost.getCurrency());
            statement.setString(5, cost.getDescription());
            statement.setDate(6, new java.sql.Date(cost.getDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

/*
    public List<Cost> getCostsByDate(Date date) {
        */
/**
         * retrieve all costs on a specific date from the costs table and return them as a list
         *//*

        List<Cost> costs = new ArrayList<>();
        String sql = "SELECT * FROM costs WHERE date = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(date.getTime()));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String category = resultSet.getString("category");
                BigDecimal sum = resultSet.getBigDecimal("sum");
                String currency = resultSet.getString("currency");
                String description = resultSet.getString("description");
                Date costDate = resultSet.getDate("date");

                costs.add(new Cost(id, category, sum, currency, description, costDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions properly in your application
        }

        return costs;
    }
*/

    public List<Cost> getCostsByMonthYear(Date date) {
        /**
         * retrieve all costs on a specific month+year from the costs table and return them as a list
         */
        List<Cost> costs = new ArrayList<>();
        String sql = "SELECT * FROM costs WHERE YEAR(date) = ? AND MONTH(date) = ?";

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, calendar.get(Calendar.YEAR));
            statement.setInt(2, calendar.get(Calendar.MONTH) + 1); // Months are zero-based in JDBC

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String category = resultSet.getString("category");
                BigDecimal sum = resultSet.getBigDecimal("sum");
                String currency = resultSet.getString("currency");
                String description = resultSet.getString("description");
                Date costDate = resultSet.getDate("date");

                costs.add(new Cost(id, category, sum, currency, description, costDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions properly in your application
        }

        return costs;
    }

    public void close() {
        // Close the database connection when done
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions properly in your application
        }
    }
}
