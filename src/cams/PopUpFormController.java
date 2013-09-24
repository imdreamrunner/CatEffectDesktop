/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cams;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dreamrunner
 */
public class PopUpFormController implements Initializable {

    private Stage selfStage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void setStage(Stage stage) {
        selfStage = stage;
    }
}
