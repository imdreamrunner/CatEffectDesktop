/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cams;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

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
    
    private MainFormController mainFormController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
    
    public void setStage(Stage stage) {
        selfStage = stage;
    }
    
    public void setMainFormController(MainFormController mfc) {
        mainFormController = mfc;
    }
    
    public void setTarget(String newTarget) {
        target = newTarget;
        final WebEngine webEngine = webView.getEngine();
        webView.setVisible(false);
        
        ChangeListener webViewListener = new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> ov,
                Worker.State oldState, Worker.State newState) {
                System.out.println(" Popup WebView Stage: " + newState.toString());
                selfStage.setTitle(webEngine.getTitle() + " - CaMS@BTU");
                JSObject jsobj = (JSObject)webEngine.executeScript("window");
                jsobj.setMember("java", new Bridge());
                if (newState == Worker.State.SUCCEEDED) {
                    webView.setVisible(true);
                    webEngine.executeScript("javaPageLoad();");
                }
            }
        };
        
        webEngine.getLoadWorker().stateProperty().addListener(webViewListener);
        webEngine.load("http://localhost:9000" + target);
    }
    
    
    public class Bridge {
        public String getUsername() {
            return ServerInterface.getUsername();
        }
        public String getPassword() {
            return ServerInterface.getPassword();
        }
        public void setFullScreen(boolean value) {
            selfStage.setFullScreen(value);
        }
        public void close(boolean refresh) {
            System.out.println("trying to close.");
            if (refresh) {
                mainFormController.refreshWebView();
            }
            selfStage.close();
        }
    }
    
}
