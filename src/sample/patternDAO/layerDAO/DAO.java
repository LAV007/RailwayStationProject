package sample.patternDAO.layerDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DAO<Entity> {
    void create(Entity model) throws SQLException;
    ResultSet read() throws SQLException;
    void update(Entity model, int id);
    void delete(int id);
}