/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartmirror;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Nick
 */
public class Starfield {

    private double screenHeight;
    private double screenWidth;
    public boolean isActive = false;
    public Pane pane = new Pane();

    public void start(Stage stage) {

    }

    public Pane getStarfieldPane() {
        return pane;
    }

    public void startStarfield() {
        pane.setStyle("-fx-background-color: #000000;");
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        screenHeight = screenBounds.getHeight();
        screenWidth = screenBounds.getWidth();
        isActive = true;
        for (int i = 0; i < 1500; i++) {
            Circle c = generateStar();
            pane.getChildren().add(c);
            translateCircle(c, pane);
        }
    }

    public void stopStarfield() {
        isActive = false;
        pane.getChildren().clear();
    }

    private void translateCircle(Circle circle, Pane pane) {
        double height = screenHeight * Math.random();
        double speed = (10 - circle.getRadius()) * 2000 + Math.random() * 1000;
        
        speed = 2*speed;
        
        Timeline testTimeLine = new Timeline();
        testTimeLine.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, // set start position at 0
                        new KeyValue(circle.translateXProperty(), screenWidth + 10),
                        new KeyValue(circle.translateYProperty(), height)),
                new KeyFrame(new Duration(speed), // 
                        new KeyValue(circle.translateXProperty(), 0 - 10),
                        new KeyValue(circle.translateYProperty(), height)));
        testTimeLine.setOnFinished((ActionEvent event) -> {
            if (isActive) {
                pane.getChildren().remove(circle);
                Circle c = generateStar();
                pane.getChildren().add(c);
                translateCircle(c, pane);
            }

        });

        testTimeLine.play();

    }

    private static Circle generateStar() {
        Random random = new Random();
        int max = 8;
        int min = 1;

        int size = random.nextInt(max - min + 1) + min;

        Circle circle = new Circle(-10, -10, size, Color.WHITE);
        circle.setEffect(new BoxBlur(size * 2, 1, 1));
        InnerShadow outerglow = new InnerShadow();
        outerglow.setOffsetX(0);
        outerglow.setOffsetY(0);

        double colorChoice = Math.random();
        // chance for blue
        if (colorChoice > 0.995) {
            outerglow.setColor(Color.rgb(4, 163, 255));
            circle.setEffect(outerglow);

        } //chance for red
        else if (colorChoice < 0.005) {
            outerglow.setColor(Color.rgb(229, 73, 12));
            circle.setEffect(outerglow);
        }

        return circle;
    }

}
