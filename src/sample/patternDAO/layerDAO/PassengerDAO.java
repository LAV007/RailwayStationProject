package sample.patternDAO.layerDAO;

import sample.patternDAO.dataBase.DataBase;
import sample.patternDAO.layerEntity.Passenger;
import java.sql.*;

public class PassengerDAO implements DAO<Passenger> {

    private Connection connection;
    DataBase db = new DataBase();

    public PassengerDAO() {
    }
    public PassengerDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(final Passenger model) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO `passengers` (`name`, `surname`) VALUES(?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getSurname());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public ResultSet read() throws SQLException {
        try {
            String sql = "SELECT `name`, `surname` FROM `passengers`";
            Statement statement = db.getDataBaseConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            return res;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.getDataBaseConnection().close();
        }
        return null;
    }
    //выборка данных из таблицы
    /*public String userLogin;
    public int getUser(String table) throws ClassNotFoundException, SQLException {
        int user = 0;
        String sqlCmd = "SELECT * FROM " + table + " WHERE `login` = 'john'";
        Statement statement = getDbCon().createStatement();
        ResultSet res = statement.executeQuery(sqlCmd);
        while (res.next()) {
            user = res.getInt("id");
            userLogin = res.getString("login");
            System.out.print(userLogin + " - ");
        }
        return user;
    }*/

   /* public String readName(){
        try (PreparedStatement preparedStatement = db.getDataBaseConnection().prepareStatement("SELECT `name`, `surname` FROM `passengers` WHERE `name`= 'Alex'")) {

            Passenger passenger = new Passenger();
            passenger.setName();
            passenger.setSurname();



            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String();
    }*/

    @Override
    public void update(Passenger passenger, int id) {
        try (PreparedStatement preparedStatement = db.getDataBaseConnection().prepareStatement("UPDATE `passengers` SET `name`=? WHERE `id`=?")) {

            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement =
             db.getDataBaseConnection().prepareStatement("DELETE FROM `passengers` WHERE `id`=?")) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}