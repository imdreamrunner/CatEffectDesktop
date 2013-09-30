/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cams;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dreamrunner
 */
public class PopUpFormController implements Initializable {

    @FXML
    private WebView webView;
    
    private Stage selfStage;
    private String target;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
    
    public void setStage(Stage stage) {
        selfStage = stage;
    }
    
    public void setTarget(String newTarget) {
        target = newTarget;
        final WebEngine webEngine = webView.getEngine();
        webEngine.load("http://localhost:9000" + target);
    }
    
}
