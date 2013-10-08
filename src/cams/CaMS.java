/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author dreamrunner
 */
public class CaMS extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        readSettings();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
        Parent root = (Parent)loader.load();
        LoginFormController controller = (LoginFormController)loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root);
        scene.setFill(null);
        stage.setScene(scene);
        stage.setTitle("CaMS@BTU - Welcome");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    
    private static void readSettings() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("config.ini"));
        String line;
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            String[] lineArr = line.split("=");
            if (lineArr.length == 2) {
                if ("server".equals(lineArr[0])) {
                    ServerInterface.setServerAddress(lineArr[1]);
                }
            }
        }
        scanner.close();   
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}