package sample.patternDAO.layerService;

import sample.patternDAO.dataBase.DataBase;
import sample.patternDAO.layerDAO.TicketDAO;
import sample.patternDAO.layerEntity.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketService extends DataBase implements TicketDAO {

    private Connection connection = getDataBaseConnection();

    @Override
    public void createTicket(Ticket ticket) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO `ticket` (`date`, `time`, `station`) VALUES(?, ?, ?)";


        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ticket.getDate());
            preparedStatement.setString(2, ticket.getTime());
            preparedStatement.setString(3, ticket.getStation());
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
    public List<Ticket> readTicket() throws SQLException {
        List<Ticket> ticketList = new ArrayList<>();

        String sql = "SELECT `station`, `date`, `time` FROM `ticket`";

        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setStation(resultSet.getString("station"));
                ticket.setDate(resultSet.getString("date"));
                ticket.setTime(resultSet.getString("time"));

                ticketList.add(ticket);
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
        return ticketList;
    }

    public Ticket readTicketById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT `station`, `date`, `time` FROM `ticket` WHERE `id`=?";

        Ticket ticket = new Ticket();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            ticket.setStation(resultSet.getString("station"));
            ticket.setDate(resultSet.getString("date"));
            ticket.setTime(resultSet.getString("time"));

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
        return ticket;
    }

    @Override
    public void updateTicket(Ticket ticket) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE `ticket` SET `station`=?, `date`=?, `time`=? WHERE `id`=0";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, ticket.getStation());
            preparedStatement.setString(2, ticket.getDate());
            preparedStatement.setString(3, ticket.getTime());

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
    public void deleteTicket(Ticket ticket) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM `ticket` WHERE `id`=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, ticket.getId());

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