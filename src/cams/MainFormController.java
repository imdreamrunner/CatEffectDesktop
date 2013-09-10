/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cams;

import javafx.concurrent.Worker.State;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

// import com.google.zxing.BarcodeFormat;

/**
 * FXML Controller class
 *
 * @author dreamrunner
 */
public class MainFormController implements Initializable {

    private Stage selfStage;
    
    @FXML
    private WebView webView;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        final WebEngine webEngine = webView.getEngine();
        
        webEngine.getLoadWorker().stateProperty().addListener(
            new ChangeListener<State>() {
                @Override
                public void changed(ObservableValue<? extends State> ov,
                    State oldState, State newState) {
                    JSObject jsobj = (JSObject)webEngine.executeScript("window");
        jsobj.setMember("java", new Bridge());
                }
            }
        );
        
        webEngine.load("http://localhost:9000/system/managers?auth_username=admin&auth_password=admin");
        
    }    
    
    public void setStage(Stage stage) {
        selfStage = stage;
    }
    
    public class Bridge {
        public void exit() {
            System.out.println("Form is going to close...");
            selfStage.close();
        }
    }
}
