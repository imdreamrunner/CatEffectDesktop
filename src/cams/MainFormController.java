/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cams;

import java.io.IOException;
import javafx.concurrent.Worker.State;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
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
        Font.loadFont(
            getClass().getResource("fonts/glyphicons-halflings-regular.ttf").toExternalForm(), 
            10
        );
        Font.loadFont(
            getClass().getResource("fonts/Telex-Regular.ttf").toExternalForm(), 
            10
        );
        
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
        public void open() throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUpForm.fxml"));
            Parent root = (Parent)loader.load();
            PopUpFormController controller = (PopUpFormController)loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            controller.setStage(stage);
            stage.setScene(scene);
            stage.setTitle("Pop Up Window");
            stage.setScene(scene);
            stage.show();
        }
        public void exit() {
            selfStage.close();
        }
        public void setFullScreen(boolean value) {
            selfStage.setFullScreen(value);
        }
    }
}
