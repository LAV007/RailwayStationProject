package sample.controllers;

        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextArea;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;
        import sample.patternDAO.layerDAO.DAO;
        import sample.patternDAO.dataBase.DataBase;
        import sample.patternDAO.layerDAO.PassengerDAO;
        import sample.patternDAO.layerDAO.TicketDAO;
        import sample.patternDAO.layerEntity.Passenger;
        import sample.patternDAO.layerEntity.Ticket;
        import java.io.IOException;
        import java.sql.SQLException;

public class AddTicket {

    @FXML
    private TextField personAdd;

    @FXML
    private Button btn_add;

    @FXML
    private TextArea dateAdd;

    @FXML
    private TextArea timeAdd;

    @FXML
    private TextArea stationAdd;

    @FXML
    private TextField surname;

    private DataBase dataBase = new DataBase();

    DAO<Passenger> passengerDAO = new PassengerDAO(dataBase.getDataBaseConnection());
    DAO<Ticket> ticketDAO = new TicketDAO(dataBase.getDataBaseConnection());

    @FXML
    void initialize() {
        btn_add.setOnAction(event -> {
            personAdd.setStyle("-fx-border-color: #fafafa");
            dateAdd.setStyle("-fx-border-color: #fafafa");
            timeAdd.setStyle("-fx-border-color: #fafafa");

            if(personAdd.getCharacters().length() <= 2) {
                personAdd.setStyle("-fx-border-color: red");
                return;
            } else if(dateAdd.getText().length() <= 2) {
                dateAdd.setStyle("-fx-border-color: red");
                return;
            } else if(timeAdd.getText().length() <= 2) {
                timeAdd.setStyle("-fx-border-color: red");
                return;
            } else if(stationAdd.getText().length() <= 2) {
                stationAdd.setStyle("-fx-border-color: red");
                return;
            }

            try {
                Passenger passengerWithTicket = new Passenger(personAdd.getCharacters().toString(), surname.getCharacters().toString());
                passengerDAO.create(passengerWithTicket);//записывает в БД нового пассажира

                Ticket ticket = new Ticket(dateAdd.getText(), timeAdd.getText(), stationAdd.getText());
                ticketDAO.create(ticket);//записывает в БД новый билет

                Parent root = FXMLLoader.load(getClass().getResource("/sample/scenes/main.fxml"));
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setTitle("Railway station");
                primaryStage.setScene(new Scene(root, 333, 350));
                primaryStage.show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}