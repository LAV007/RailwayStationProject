package test;

import sample.patternDAO.layerDAO.DAO;
import sample.patternDAO.layerEntity.Passenger;

import java.sql.Connection;

public class PassengersTestSettings {
    protected DAO<Passenger> passengerDAO;
    protected Connection connection;
}
