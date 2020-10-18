package sample.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.patternDAO.dataBase.DataBase;
import sample.auxiliary.User;
import sample.patternDAO.layerDAO.PassengerDAO;
import sample.patternDAO.layerDAO.TicketDAO;
import sample.patternDAO.layerEntity.Passenger;
import sample.patternDAO.layerEntity.Ticket;

public class MainController {

    @FXML
    private Button btn_exit, btn_add;

    @FXML
    private VBox paneVBox;

    @FXML
    private VBox paneVBox1;

    private DataBase dataBase = new DataBase();

    @FXML
    void initialize() throws SQLException {

        PassengerDAO passengerDAO = new PassengerDAO();
        /**
         * Обновление пассажира по идентификационному номеру
         */
        passengerDAO.update(new Passenger("Alex", "Lys"), 22);

        /**
         *Удаление пассажира по идентификационному номеру
         */
        passengerDAO.delete(22);

        TicketDAO ticketDAO = new TicketDAO();
        /**
         * Обновление билета по идентификационному номеру
         */
        ticketDAO.update(new Ticket("10/18/2020", "1.14", "Vyborg"), 16);

        /**
         *Удаление билета по идентификационному номеру
         */
        ticketDAO.delete(17);

        //Вывод пассажиров в UI
        ResultSet resultSet = new PassengerDAO().read();
        while(resultSet.next()) {
            Node node = null;
            try {
                node = FXMLLoader.load(getClass().getResource("/sample/scenes/passengers.fxml"));

                Label passengersName = (Label) node.lookup("#passengersName");
                passengersName.setText(resultSet.getString("name"));

                Label passengersSurname = (Label) node.lookup("#passengersSurname");
                passengersSurname.setText(resultSet.getString("surname"));

                final Node nodeSet = node;

                node.setOnMouseEntered(event -> {
                    nodeSet.setStyle("-fx-background-color:  #ffc000");
                });

                node.setOnMouseExited(event -> {
                    nodeSet.setStyle("-fx-background-color: #d9e8ea");
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            HBox hBox = new HBox();
            hBox.getChildren().add(node);
            hBox.setAlignment(Pos.BASELINE_CENTER);
            paneVBox.getChildren().add(hBox);
            paneVBox.setSpacing(10);
        }

        //Вывод билетов в UI
        ResultSet res = new TicketDAO().read();
        while(res.next()) {
            Node node = null;
            try {
                node = FXMLLoader.load(getClass().getResource("/sample/scenes/tickets.fxml"));

                Label station = (Label) node.lookup("#station");
                station.setText(res.getString("station"));

                Label date = (Label) node.lookup("#date");
                date.setText(res.getString("date"));

                Label time = (Label) node.lookup("#time");
                time.setText(res.getString("time"));

                final Node nodeSet = node;

                node.setOnMouseEntered(event -> {
                    nodeSet.setStyle("-fx-background-color:  #ffc000");
                });

                node.setOnMouseExited(event -> {
                    nodeSet.setStyle("-fx-background-color: #d9e8ea");
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            HBox hBox = new HBox();
            hBox.getChildren().add(node);
            hBox.setAlignment(Pos.BASELINE_CENTER);
            paneVBox1.getChildren().add(hBox);
            paneVBox1.setSpacing(10);
        }

        btn_exit.setOnAction(event -> {
            try {
                FileOutputStream fos = new FileOutputStream("user.settings");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(new User(""));
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/scenes/sample.fxml"));
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setTitle("Railway station");
                primaryStage.setScene(new Scene(root, 333, 350));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btn_add.setOnAction(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/scenes/addTicket.fxml"));
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setTitle("Railway station");
                primaryStage.setScene(new Scene(root, 333, 350));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}