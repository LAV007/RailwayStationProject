package sample.patternDAO.layerDAO;

import sample.patternDAO.layerEntity.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface TicketDAO {

    //create
    void createTicket(Ticket ticket) throws SQLException;

    //read
    List<Ticket> readTicket() throws SQLException;

    //update
    void updateTicket(Ticket ticket) throws SQLException;

    //delete
    void deleteTicket(Ticket ticket) throws SQLException;
}
