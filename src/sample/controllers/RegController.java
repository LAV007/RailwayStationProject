package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.auxiliary.dataDAO;
import sample.dataBase.DataBase;
import sample.auxiliary.User;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class RegController {
    @FXML
    private Button btn_reg;

    @FXML
    private TextField login_reg;

    @FXML
    private PasswordField password_reg;

    @FXML
    private Button btn_auth;

    @FXML
    private TextField login_auth;

    @FXML
    private PasswordField password_auth;

    private dataDAO dataBase = new DataBase();

    @FXML
    void initialize() {
        /**
         * Actions when you click on the registration button
         */
        btn_reg.setOnAction(event -> {
            if(login_reg.getCharacters().length() <= 3) {
                login_reg.setStyle("-fx-border-color: red");
                return;
            } else if(password_reg.getCharacters().length() <= 3) {
                password_reg.setStyle("-fx-border-color: red");
                return;
            }

            String pass = md5String(password_reg.getCharacters().toString());

            try {
                boolean isReg = dataBase.regUser(login_reg.getCharacters().toString(), pass);
                if(isReg) {
                    login_reg.setText("");
                    password_reg.setText("");
                    btn_reg.setText("Done");
                } else
                    btn_reg.setText("Enter another login");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        /**
         * Actions when clicking the authorization button
         */
        btn_auth.setOnAction(event -> {
            if(login_auth.getCharacters().length() <= 3) {
                login_auth.setStyle("-fx-border-color: red");
                return;
            } else if(password_auth.getCharacters().length() <= 3) {
                password_auth.setStyle("-fx-border-color: red");
                return;
            }

            String pass = md5String(password_auth.getCharacters().toString());

            try {
                boolean isAuth = dataBase.authUser(login_auth.getCharacters().toString(), pass);
                if(isAuth) {
                    FileOutputStream fos = new FileOutputStream("user.settings");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(new User(login_auth.getCharacters().toString()));
                    oos.close();

                    login_auth.setText("");
                    password_auth.setText("");
                    btn_auth.setText("Done");

                    Parent root = FXMLLoader.load(getClass().getResource("/sample/scenes/main.fxml"));
                    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    primaryStage.setTitle("Railway station");
                    primaryStage.setScene(new Scene(root, 333, 350));
                    primaryStage.show();
                } else
                    btn_auth.setText("not founded");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * This method encrypts the password
     * @param pass
     * @return
     */
    public static String md5String(String pass) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(pass.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        BigInteger bigInteger = new BigInteger(1, digest);
        String m5dHex = bigInteger.toString(16);

        while(m5dHex.length() < 32)
            m5dHex = "0" + m5dHex;

        return m5dHex;
    }
}
