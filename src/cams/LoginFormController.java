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
    
    private Stage selfStage;
    
    @FXML
    private Label labelStatus;
    
    @FXML
    private void login(ActionEvent event) throws Exception {
        selfStage.close();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainForm.fxml"));
        Parent root = (Parent)loader.load();
        MainFormController controller = (MainFormController)loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        controller.setStage(stage);
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
