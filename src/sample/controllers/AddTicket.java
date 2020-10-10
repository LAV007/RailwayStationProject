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
        import sample.patternDAO.dataBase.DataBase;
        import sample.patternDAO.layerEntity.Passenger;
        import sample.patternDAO.layerEntity.Request;
        import sample.patternDAO.layerEntity.Ticket;
        import sample.patternDAO.layerService.PassengerService;
        import sample.patternDAO.layerService.RequestService;
        import sample.patternDAO.layerService.TicketService;

        import java.io.IOException;
        import java.sql.SQLException;
        import java.util.List;

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
                //dataBase.addTicket(stationAdd.getText(), dateAdd.getText(), timeAdd.getText(), personAdd.getCharacters().toString());

                PassengerService passengerService = new PassengerService();
                Passenger passengerWithTicket = new Passenger(personAdd.getCharacters().toString(), surname.getCharacters().toString());
                passengerService.createPassenger(passengerWithTicket);//записывает в БД нового пассажира

                TicketService ticketService = new TicketService();
                Ticket ticket = new Ticket(dateAdd.getText(), timeAdd.getText(), stationAdd.getText());
                ticketService.createTicket(ticket);//записывает в БД новый билет

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