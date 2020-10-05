package sample.auxiliary;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface dataDAO {

    boolean regUser(String login, String password) throws SQLException, ClassNotFoundException;

    boolean authUser(String login, String password) throws SQLException, ClassNotFoundException;

    ResultSet getTickets() throws SQLException, ClassNotFoundException;

    void addTicket(String station, String date, String time, String fio) throws SQLException, ClassNotFoundException;
}
