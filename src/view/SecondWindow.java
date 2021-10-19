package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SecondWindow implements Initializable {

    @FXML
    private Button btnback;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void back() throws IOException {
        btnback.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/window.fxml")));
    }

}
