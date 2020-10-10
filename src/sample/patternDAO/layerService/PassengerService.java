package sample.patternDAO.layerService;

import sample.patternDAO.layerDAO.PassengerDAO;
import sample.patternDAO.dataBase.DataBase;
import sample.patternDAO.layerEntity.Passenger;
import java.sql.*;
import java.util.*;

public class PassengerService extends DataBase implements PassengerDAO {

    private Connection connection = getDataBaseConnection();

    @Override
    public void createPassenger(Passenger passenger) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO `passengers` (`name`, `surname`) VALUES(?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getSurname());
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
    public List<Passenger> readPassenger() throws SQLException {
        List<Passenger> passengerList = new ArrayList<>();

        String sql = "SELECT `name`, `surname` FROM `passengers`";

        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Passenger passenger = new Passenger();
                passenger.setName(resultSet.getString("name"));
                passenger.setSurname(resultSet.getString("surname"));

                passengerList.add(passenger);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return passengerList;
    }

    @Override
    public void updatePassenger(Passenger passenger) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE `passengers` SET name=? WHERE `id`=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setInt(2, passenger.getId());

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
    public void deletePassenger(int id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM `passenger` WHERE `id`=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

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
    public ResultSet getPassengers() throws SQLException{
        try {
            String sql = "SELECT `name`, `surname` FROM `passengers`";
            Statement statement = getDataBaseConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            return res;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            getDataBaseConnection().close();
        }
        return null;
    }
}