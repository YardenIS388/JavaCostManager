package il.ac.Shenkar.CostManager.Model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DerbyDatabaseDAOModel implements IModel {

    private static DerbyDatabaseDAOModel dao;

    private DerbyDatabaseDAOModel() throws DAOException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            throw new DAOException("Cannot find DerbyDB driver", e);
        }
    }

    public static DerbyDatabaseDAOModel getInstance() throws DAOException {
        if (dao == null) {
            dao = new DerbyDatabaseDAOModel();
        }
        return dao;
    }

    @Override
    public void createCostsTableIfNotExists() throws DAOException {
        Connection connection = null;
        Statement statement = null;

        try {
            // Initialize DerbyDB connection (you may need to specify the correct database URL)
            connection = DriverManager.getConnection("jdbc:derby:costs_mng;create=true");
            statement = connection.createStatement();

            // Check if the "costs" table exists
            ResultSet resultSet = connection.getMetaData().getTables(null, null, "costs", null);

            if (!resultSet.next()) {
                // The "costs" table doesn't exist, so create it
                statement.executeUpdate("CREATE TABLE costs ("
                        + "id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,"
                        + "name VARCHAR(255),"
                        + "category VARCHAR(255),"
                        + "sum INT,"
                        + "currency VARCHAR(50),"
                        + "description VARCHAR(255),"
                        + "date DATE"
                        + ")");
                System.out.println("Created 'costs' table");
            }
        } catch (SQLException e) {
            throw new DAOException("Problem checking or creating 'costs' table", e);
        } finally {
            // Close resources
            closeResources(connection, (PreparedStatement) statement, null);
        }
    }

    @Override
    public void addCost(Cost cost) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Initialize DerbyDB connection (you may need to specify the correct database URL)
            connection = DriverManager.getConnection("jdbc:derby:cost_mng;create=true");
            statement = connection.prepareStatement("INSERT INTO costs (id, category, sum, currency, description, date) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setInt(1, cost.getId());
            statement.setString(2, cost.getCategory());
            statement.setBigDecimal(3, cost.getSum());
            statement.setString(4, cost.getCurrency());
            statement.setString(5, cost.getDescription());
            statement.setDate(6, new java.sql.Date(cost.getDate().getTime()));
            int result = statement.executeUpdate();
            if (result != 1) {
                throw new DAOException("Problem with adding cost");
            }
        } catch (SQLException e) {
            throw new DAOException("Problem with adding cost", e);
        } finally {
            closeResources(connection, statement, null);
        }
    }

    @Override
    public List<Cost> getCostsByDate(Date date) throws DAOException {
        /**
         * retrieve all costs on a specific date from the costs table and return them as a list
         */
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Cost> costs = new ArrayList<>();

        try {
            // Initialize DerbyDB connection (replace with your database URL)
            connection = DriverManager.getConnection("jdbc:derby:costs_mng;create=true");

            // Define the SQL statement to retrieve costs by date
            statement = connection.prepareStatement("SELECT * FROM costs WHERE date = ?");
            statement.setDate(1, new java.sql.Date(date.getTime()));

            // Execute the SQL query
            resultSet = statement.executeQuery();

            // Process the query results
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
            throw new DAOException("Problem retrieving costs by date", e);
        } finally {
            closeResources(connection, statement, resultSet);
        }

        return costs;
    }

    @Override
    public List<Cost> getCosts() throws DAOException{
        /**
         * retrieve all costs on a specific date from the costs table and return them as a list
         */
        List<Cost> costs = new ArrayList<>();
        Connection connection = null;
        String sqlQuery = "SELECT * FROM costs";

        try {
            // Establish a database connection (you may need to specify the correct database URL)
            connection = DriverManager.getConnection("jdbc:derby:cost_mng;create=true");

            // Prepare and execute the SQL query
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery);
                 ResultSet resultSet = statement.executeQuery()) {

                // Process the query results
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String category = resultSet.getString("category");
                    BigDecimal sum = resultSet.getBigDecimal("sum");
                    String currency = resultSet.getString("currency");
                    String description = resultSet.getString("description");
                    Date costDate = resultSet.getDate("date");

                    // Create a Cost object and add it to the list
                    costs.add(new Cost(id, category, sum, currency, description, costDate));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Problem retrieving costs", e);
        } finally {
            // Close the database connection (resource management)
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Problem closing database connection", e);
            }
        }
        return costs;
    }


/*
    @Override
    public Product getProduct(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            // Initialize DerbyDB connection (you may need to specify the correct database URL)
            connection = DriverManager.getConnection("jdbc:derby:mydatabase");
            statement = connection.prepareStatement("SELECT * FROM products WHERE id = ?");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (!rs.next()) {
                throw new DAOException("Product does not exist id=" + id);
            }
            Product result = new Product(rs.getInt("id"), rs.getDouble("price"), rs.getString("name"));
            if (rs.next()) {
                throw new DAOException("More than one product with the id=" + id);
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException("Problem with getting product", e);
        } finally {
            closeResources(connection, statement, rs);
        }
    }
*/

    // Helper method to close database resources
    private void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
