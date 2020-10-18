package sample.patternDAO.layerDAO;

import sample.patternDAO.dataBase.DataBase;
import sample.patternDAO.layerEntity.Ticket;
import java.sql.*;

public class TicketDAO implements DAO<Ticket>  {

    private Connection connection;
    DataBase db = new DataBase();

    public TicketDAO() {
    }
    public TicketDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Ticket model) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO `tickets` (`date`, `time`, `station`) VALUES(?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getDate());
            preparedStatement.setString(2, model.getTime());
            preparedStatement.setString(3, model.getStation());
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
            String sql = "SELECT `station`, `date`, `time` FROM `tickets`";
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

    @Override
    public void update(Ticket model, int id) {
        try (PreparedStatement preparedStatement = db.getDataBaseConnection().prepareStatement("UPDATE `tickets` SET `station`=? WHERE `id`=?")) {

            preparedStatement.setString(1, model.getStation());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id){
        try (PreparedStatement preparedStatement =
                     db.getDataBaseConnection().prepareStatement("DELETE FROM `tickets` WHERE `id`=?")) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}