/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cams;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

/**
 *
 * @author dreamrunner
 */
public class LoginFormController implements Initializable {
    
    private Stage selfStage;
    
    @FXML
    TextField username;
    
    @FXML
    TextField password;
    
    @FXML
    ChoiceBox choiceType;
    
    @FXML
    private void login(ActionEvent event) throws Exception {
        int type = 0;
        if (choiceType.getValue().toString().equals("OFS Staff")) {
            type = 1;
        }
        boolean success = ServerInterface.login(type, username.getText(), password.getText());
        if (success) {
            openMainForm();
        } else {
            MessageBox.show(selfStage,
                ServerInterface.getErrorMessage(),
                "Login Fails",
                MessageBox.ICON_INFORMATION);
        }
    }
    
    private void openMainForm() throws IOException {
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
    private void exit(ActionEvent event) {
        selfStage.close();
    }
    
    private double dragInitialX, dragInitialY;
    
    @FXML
    private void mousePressedHandler(MouseEvent me) {
        if (me.getButton() != MouseButton.MIDDLE) {
            dragInitialX = me.getSceneX();
            dragInitialY = me.getSceneY();
        }
    }
    
    @FXML
    private void mouseDraggedHandler(MouseEvent me) {
        if (me.getButton() != MouseButton.MIDDLE) {
            selfStage.setX(me.getScreenX() - dragInitialX);
            selfStage.setY(me.getScreenY() - dragInitialY);
        }
    }
    
    public void setStage(Stage stage) {
        selfStage = stage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
}
