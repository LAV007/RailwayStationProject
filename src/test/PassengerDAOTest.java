package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sample.patternDAO.layerDAO.PassengerDAO;
import sample.patternDAO.layerEntity.Passenger;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerDAOTest extends PassengersTestSettings {

    @Before
    public void before() {
        try {
            String user = "root";
            String password = "root";
            String url = "jdbc:mysql://localhost:3306/RailwayStation" + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            connection = DriverManager.getConnection(url, user, password);
            passengerDAO = new PassengerDAO(connection);
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


    @Test //creating a new passenger and writing them into the database
    public void testCase_1() throws SQLException {
        Passenger passenger = new Passenger("Petr", "Petrov");
        passengerDAO.create(passenger);
    }
    @Test //check out that passenger was created, read from database
    public void testCase_2() throws SQLException {
        PassengerDAO passengerDAO = new PassengerDAO();
        ResultSet resultSet = passengerDAO.read();
        Passenger passenger = new Passenger();
        while(resultSet.next()){
            passenger.setName(resultSet.getString("name"));
            passenger.setSurname(resultSet.getString("surname"));
        }

        Passenger expected = new Passenger("Petr","Petrov");
        Assert.assertEquals(expected, passenger);
    }

    @Test //update of passenger that was created, this method update only the name of the passenger
    public void testCase_3() {
        passengerDAO.update(new Passenger("Sidr", "Petrov"), 32);
    }
    @Test //check out that passenger was updated, read from database
    public void testCase_4() throws SQLException {
        PassengerDAO passengerDAO = new PassengerDAO();
        ResultSet resultSet = passengerDAO.read();
        Passenger passenger = new Passenger();
        while(resultSet.next()){
            passenger.setName(resultSet.getString("name"));
            passenger.setSurname(resultSet.getString("surname"));
        }

        Passenger expected = new Passenger("Sidr","Petrov");
        Assert.assertEquals(expected, passenger);
    }

    @Test //delete of passenger
    public void testCase_5() {
        passengerDAO.delete(31);
    }

}