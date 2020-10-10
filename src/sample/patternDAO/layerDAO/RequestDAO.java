package sample.patternDAO.layerDAO;

import sample.patternDAO.layerEntity.Request;

import java.sql.SQLException;
import java.util.List;

public interface RequestDAO {

    //create
    void createRequest(Request request) throws SQLException;

    //read
    List<Request> readRequest() throws SQLException;

    //update
    void updateRequest(Request request) throws SQLException;

    //delete
    void deleteRequest(Request request) throws SQLException;
}
