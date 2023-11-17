package Servlets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DatabaseConnection {
        private String jdbcURL;
        private String jdbcUsername;
        private String jdbcPassword;
        private Connection jdbcConnection;

        public DatabaseConnection(String jdbcURL, String jdbcUsername, String jdbcPassword) {
            this.jdbcURL = "jdbc:mysql://localhost:3306/todolist";
            this.jdbcUsername = "root";
            this.jdbcPassword = "pass123";
        }

        protected void connect() throws SQLException {
            if (jdbcConnection == null || jdbcConnection.isClosed()) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    throw new SQLException(e);
                }
                jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            }
        }

        protected void disconnect() throws SQLException {
            if (jdbcConnection != null && !jdbcConnection.isClosed()) {
                jdbcConnection.close();
            }
        }

        public List<String> getAllItems() throws SQLException {
            List<String> listTodo = new ArrayList<>();
            String sql = "SELECT to_Do_item FROM tasks";

            connect();

            Statement statement = jdbcConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String item = resultSet.getString("to_Do_item");
                listTodo.add(item);
            }

            resultSet.close();
            statement.close();

            disconnect();

            return listTodo;
        }

        public boolean insertItem(String item) throws SQLException {
            String sql = "INSERT INTO tasks (to_Do_item) VALUES (?)";
            connect();

            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, item);

            boolean rowInserted = statement.executeUpdate() > 0;
            statement.close();
            disconnect();
            return rowInserted;
        }

        public boolean deleteItem(String item) throws SQLException {
            String sql = "DELETE FROM tasks WHERE to_Do_item = ?";

            connect();

            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, item);

            boolean rowDeleted = statement.executeUpdate() > 0;
            statement.close();
            disconnect();
            return rowDeleted;
        }
    }


