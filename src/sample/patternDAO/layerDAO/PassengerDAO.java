package sample.patternDAO.layerDAO;

import sample.patternDAO.layerEntity.Passenger;

import java.sql.SQLException;
import java.util.List;

public interface PassengerDAO {

    //create
    void createPassenger(Passenger passenger) throws SQLException;

    //read
    List<Passenger> readPassenger() throws SQLException;

    //update
    void updatePassenger(Passenger passenger) throws SQLException;

    //delete
    void deletePassenger(int id) throws SQLException;
}
