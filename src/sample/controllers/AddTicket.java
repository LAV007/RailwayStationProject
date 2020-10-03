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
        import sample.dataBase.DataBase;

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

    private DataBase dataBase = new DataBase();

    @FXML
    void initialize() {
        btn_add.setOnAction(event -> {
            personAdd.setStyle("-fx-border-color: #fafafa");
            dateAdd.setStyle("-fx-border-color: #fafafa");
            timeAdd.setStyle("-fx-border-color: #fafafa");

            if(personAdd.getCharacters().length() <= 5) {
                personAdd.setStyle("-fx-border-color: red");
                return;
            } else if(dateAdd.getText().length() <= 5) {
                dateAdd.setStyle("-fx-border-color: red");
                return;
            } else if(timeAdd.getText().length() <= 5) {
                timeAdd.setStyle("-fx-border-color: red");
                return;
            } else if(stationAdd.getText().length() <= 5) {
                stationAdd.setStyle("-fx-border-color: red");
                return;
            }

            try {
                dataBase.addTicket(stationAdd.getText(), dateAdd.getText(), timeAdd.getText(), personAdd.getCharacters().toString());

                Parent root = FXMLLoader.load(getClass().getResource("/sample/scenes/main.fxml"));
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setTitle("Railway station");
                primaryStage.setScene(new Scene(root, 333, 350));
                primaryStage.show();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}