/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cams;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author dreamrunner
 */
public class LoginFormController implements Initializable {
    
    @FXML
    private Label labelStatus;
    private Stage selfStage;
    
    @FXML
    private void login(ActionEvent event) throws Exception {
        selfStage.close();
        
        Parent root;
        root = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("CaMS@BTU");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void close(ActionEvent event) {
        selfStage.close();
    }
    
    public void setStage(Stage stage) {
        selfStage = stage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
}
