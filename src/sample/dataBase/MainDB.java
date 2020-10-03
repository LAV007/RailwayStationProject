package sample.dataBase;

import java.sql.SQLException;

public class MainDB {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        try {
            dataBase.isConnected();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}