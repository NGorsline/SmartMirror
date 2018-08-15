/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartmirror;

import javafx.geometry.Pos;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Anh Duy
 */
//public class LoadingScreen extends Application {
public class LoadingScreen {

    /**
     * @param args the command line arguments
     * @return
     */
    public Pane start() {

        BorderPane pane = new BorderPane();

        final ProgressIndicator loadingIndicator = new ProgressIndicator();

        loadingIndicator.setVisible(false);
        loadingIndicator.setStyle(" -fx-progress-color: white;");
        loadingIndicator.setPrefSize(300, 300);

        loadItems(loadingIndicator);

        HBox root = new HBox(
                new StackPane(
                        loadingIndicator
                )
        );
        root.setAlignment(Pos.CENTER);

        ImageView pic = new ImageView(new Image(getClass().getResourceAsStream("/resources/weather_icons/walogo.png"), 200, 200, true, true));
        StackPane pane2 = new StackPane();
        pane2.getChildren().addAll(pic, root);
        pane.setCenter(pane2);

        pane.setStyle("-fx-background-color: black;");
        return pane;
    }

    private void loadItems(final ProgressIndicator loadingIndicator) {
        if (loadingIndicator.isVisible()) {
            return;
        }
        loadingIndicator.setVisible(true);

        Thread loadingThread = new Thread("list-loader");
        loadingThread.setDaemon(true);
        loadingThread.start();
    }

}
