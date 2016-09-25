/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalcFX;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Jakob
 */
public class CoolCalc extends Application {
    Image icon = new Image("http://icons.iconarchive.com/icons/igh0zt/ios7-style-metro-ui/256/MetroUI-Apps-Calculator-Alt-icon.png");
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new  FXMLLoader(this.getClass().getResource("MainWindow.fxml"));
        AnchorPane pane = loader.load();
        MainWindowController controller =  loader.getController();
        Scene scene = new Scene(pane);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
