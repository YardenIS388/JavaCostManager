package il.ac.Shenkar.CostManager.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private Connection connection;

    public CategoryDAO() {
        // Initialize the connection to the DerbyDB here (e.g., in the constructor)
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:mydatabase");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions properly in your application
        }
    }

    public void addCategory(Category category) {
        /**
         * insert a category record into the categories table
         */
        String sql = "INSERT INTO categories (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions properly in your application
        }
    }

    public List<Category> getAllCategories() {
        /**
         * retrieve all categories from the categories table and return them as a list
         */
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                categories.add(new Category(name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions properly in your application
        }
        return categories;
    }

    // Implement other CRUD methods (update and delete) as needed

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
