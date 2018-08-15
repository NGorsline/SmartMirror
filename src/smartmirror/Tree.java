/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartmirror;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anh Duy
 */
import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Screen;
import javafx.util.Duration;

public class Tree {

    static int orderOfTree = 12;

    public static Starfield starfield;
    
    public int count = 0;
    public static Group[] group = new Group[orderOfTree];
    public static ArrayList<Group> lineGroup = new ArrayList<>();
    public static double screenHeight;
    public static double screenWidth;
    public static Pane pane = new Pane();
    public static FadeTransition[] fade = new FadeTransition[orderOfTree];
    
    public static boolean StarfieldRunning = false;

    public static void createEffects(int order) {
//        if(StarfieldRunning == false){
//            Starfield.startStarfield();
//            StarfieldRunning = true;
//            pane.getChildren().add(Starfield.getStarfieldPane());
//            System.out.println("First starfield run = true");
//        }
        try {
            pane.setStyle("-fx-background-color: #000000;");
            if (order < orderOfTree) {
                System.out.println(order);
                group[order].setOpacity(0);
                fade[order] = new FadeTransition(Duration.millis(1000), group[order]);
                fade[order].setFromValue(0);
                fade[order].setToValue(1);
                pane.getChildren().add(group[order]);
                fade[order].setOnFinished(e -> {

                    createEffects(order + 1);
                });
                fade[order].play();
            }
            if (order == 11) {
                createBackwardEffects(11);
            }
        } catch(Exception e){
            System.out.println("Ended tree. Caught exception...");
        }

    }

    public static void createBackwardEffects(int order) {
        try{
            pane.setStyle("-fx-background-color: #000000;");
        if (order > 0) {
            System.out.println(order);
            group[order].setOpacity(1);
            fade[order] = new FadeTransition(Duration.millis(1000), group[order]);
            fade[order].setFromValue(1);
            fade[order].setToValue(0);
            //pane.getChildren().add(group[order]);
            fade[order].setOnFinished(e -> {

                createBackwardEffects(order - 1);
            });
            fade[order].play();
        }
        if (order == 0) {
            pane.getChildren().removeAll(group);
            createEffects(0);
        }
        } catch(Exception e){
            System.out.println("Caught exception in tree...");
        }
        
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
    public static class Forrest extends Pane {

        final private double angleFactor = Math.PI / 5;
        final private double sizeFactor = 0.8;
        final private double lineSize = 10;

        public void setOrder(int order, Group group) {
            // add lines to a specific group, using order
            drawTrees(order, screenWidth / 2, screenHeight, 170, Math.PI / 2, group, lineSize);

        }

        public void drawTrees(int order, double x1, double y1, double length, double angle, Group group, double lineSize) {

            if (order >= 0) {
                // add line to a group using recursion

                double x2 = x1 + Math.cos(angle) * length;
                double y2 = y1 - Math.sin(angle) * length;
                Line line = new Line(x1, y1, x2, y2);

                line.setStroke(Color.web("white"));

                line.setStrokeWidth(lineSize);
                line.setStrokeLineCap(StrokeLineCap.SQUARE);
                group.getChildren().add(line);
                if (lineSize >= 2) {
                    lineSize--;
                }
                drawTrees(order - 1, x2, y2, length * sizeFactor, angle + angleFactor, group, lineSize);
                drawTrees(order - 1, x2, y2, length * sizeFactor, angle - angleFactor, group, lineSize);
            }

        }

    }

    public static Pane tree() {
        Forrest forrest = new Forrest();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        screenHeight = screenBounds.getHeight();
        screenWidth = screenBounds.getWidth();

        // create a Group array to put 14 groups together
        for (int i = 0; i < orderOfTree; i++) {
            group[i] = new Group();

        }

        // give lines to each group
        for (int i = 0; i < orderOfTree; i++) {
            forrest.setOrder(i, group[i]);
        }
        createEffects(0);

        return pane;

    }

    public static void stopTree() {
        pane.getChildren().clear();
        
//        Starfield.stopStarfield();
//        StarfieldRunning = false;

        for (int i = 0; i < orderOfTree; i++) {
            group[i] = null;
        }

    }
}
