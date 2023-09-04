package il.ac.Shenkar.CostManager.Model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
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
    public List<Cost> getCostsByDate(Date date) {
        /**
         * retrieve all costs on a specific date from the costs table and return them as a list
         */
        List<Cost> costs = new ArrayList<>();
        Connection connection = null;
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



    @Override
    public List<Cost> getCosts() {
        /**
         * retrieve all costs on a specific date from the costs table and return them as a list
         */
        List<Cost> costs = new ArrayList<>();
        Connection connection = null;
        String sql = "SELECT * FROM costs";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

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
