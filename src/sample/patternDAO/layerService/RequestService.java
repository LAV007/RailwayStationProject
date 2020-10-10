package sample.patternDAO.layerService;

import sample.patternDAO.dataBase.DataBase;
import sample.patternDAO.layerDAO.RequestDAO;
import sample.patternDAO.layerEntity.Request;
import java.sql.*;
import java.util.*;

public class RequestService extends DataBase implements RequestDAO {

    private Connection connection = getDataBaseConnection();

    @Override
    public void createRequest(Request request) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO `request` (`passengerId`, `ticketId`) VALUES(?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, request.getPassengerId());
            preparedStatement.setLong(2, request.getTicketId());

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
    public List<Request> readRequest() throws SQLException {
        List<Request> requestList = new ArrayList<>();

        String sql = "SELECT `passengerId`, `ticketId` FROM `request`";

        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Request request = new Request();
                request.setPassengerId(resultSet.getInt("passengerId"));
                request.setTicketId(resultSet.getInt("ticketId"));

                requestList.add(request);
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
        return requestList;
    }

    @Override
    public void updateRequest(Request request) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE `request` SET `passengerId`=?, `ticketId`=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, request.getPassengerId());
            preparedStatement.setInt(2, request.getTicketId());

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
    public void deleteRequest(Request request) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM `request` WHERE `passengerId`=? AND `ticketId`=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, request.getPassengerId());
            preparedStatement.setInt(2, request.getTicketId());

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
}