package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {

    @FXML
    private TextField tflink;

    @FXML
    private Button btnconfirm;

    @FXML
    private Button btnbrowse;

    private Alert alert = new Alert(Alert.AlertType.NONE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getlink() throws IOException {
        String link = tflink.getText();
        URL url = new URL(link);
        if (doesURLExist(url)) {
            btnconfirm.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/window2.fxml")));
        } else {
            System.out.println("erreur");
            errorAlert();
        }
    }

    public boolean doesURLExist(URL url) throws IOException {
        // we want to check the current URL
        HttpURLConnection.setFollowRedirects(false);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod("HEAD");

        // some websites don't like programmatic access so pretend to be a browser
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
        int responseCode = httpURLConnection.getResponseCode();

        // we only accept response code 200
        return responseCode == HttpURLConnection.HTTP_OK;
    }

    public void browse(ActionEvent event) {
        Window window = ((Node) (event.getSource())).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);
        event.consume();
        System.out.println("selected file : " + file);
    }

    public void errorAlert() {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setHeaderText("Input not valid");
        alert.setContentText("Please, enter a valid URL");
        alert.show();
    }

}
