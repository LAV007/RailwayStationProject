package sample.patternDAO.dataBase;

import java.sql.*;

public class DataBase{

    private final String HOST = "localhost";
    private final String PORT = "3306";
    private final String DataBaseName = "RailwayStation";
    private final String LOGIN = "root";
    private final String PASSWORD = "root";

    /**
     * Метод для подключения к базе данных, возвращеет объект с подключением к базе данных
     */
    public Connection getDataBaseConnection()  {
        Connection dbConnection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connection = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DataBaseName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            dbConnection = DriverManager.getConnection(connection, LOGIN, PASSWORD);//помещаем подключение в переменную
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

    public boolean regUser (String login, String password) throws SQLException {
        String sql = "INSERT INTO `users` (`login`, `password`) VALUES(?, ?)";
        try(Connection connection = getDataBaseConnection();
            PreparedStatement prSt = connection.prepareStatement(sql))
        {
            Statement statement = getDataBaseConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `users` WHERE `login` = '" + login + "' LIMIT 1");
            if(resultSet.next()) return false;

            prSt.setString(1, login);
            prSt.setString(2, password);
            prSt.executeUpdate();
            return true;
        }
    }

    public boolean authUser(String login, String password) throws SQLException {
        String sql = "SELECT * FROM `users` WHERE `login` = '" + login + "' AND `password` = '" + password + "' LIMIT 1";
        try(Connection connection = getDataBaseConnection())
        {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            return res.next();
        }
    }
}