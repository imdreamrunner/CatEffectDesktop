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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
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
        
        EventHandler<WebEvent<String>> alertHandler = new EventHandler<WebEvent<String>> () {

            @Override
            public void handle(WebEvent<String> t) {
                System.out.println(t.getData());
            }
            
        };
        
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
        
        webEngine.setOnAlert(alertHandler);
        webEngine.getLoadWorker().stateProperty().addListener(webViewListener);
        webEngine.load(ServerInterface.getUrl(target));
    }
    
    
    public class Bridge {
        private boolean fullScreen = false;
        public String getUsername() {
            return ServerInterface.getUsername();
        }
        public String getPassword() {
            return ServerInterface.getPassword();
        }
        public void setFullScreen(boolean value) {
            fullScreen = true;
            selfStage.setFullScreen(value);
        }
        public void refreshParent() {
            System.out.println("trying to refresh.");
            mainFormController.refreshWebView();
        }
        public void close() {
            selfStage.close();
        }
        public String getAccountString() {
            String str = null;
            if (fullScreen) {
                selfStage.setFullScreen(false);
                str = QRScanner.getText();
                selfStage.setFullScreen(true);
            } else {
                str = QRScanner.getText();
            }
            System.out.println(str);
            return str;
        }
    }
}
