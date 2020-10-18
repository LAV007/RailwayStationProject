package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sample.patternDAO.layerDAO.DAO;
import sample.patternDAO.layerDAO.PassengerDAO;
import sample.patternDAO.layerEntity.Passenger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerDAOTest {

    private DAO<Passenger> dao;
    private Connection connection;

    @Before
    public void before() {
        try {
            String user = "root";
            String password = "root";
            String url = "jdbc:mysql://localhost:3306/RailwayStation" + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            connection = DriverManager.getConnection(url, user, password);
            dao = new PassengerDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPassengersName() throws SQLException {
        PassengerDAO passengerDAO = new PassengerDAO();
        ResultSet resultSet = passengerDAO.read();
        Passenger passenger = new Passenger();
        while(resultSet.next()){
            passenger.setName(resultSet.getString("name"));
            passenger.setSurname(resultSet.getString("surname"));
        }

        Passenger expected = new Passenger("Petr","Petrovich");
        Assert.assertEquals(expected, passenger);
    }
}