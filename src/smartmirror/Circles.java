package smartmirror;

import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.util.Duration;

public class Circles {

    public int count = 0;
    public static Group[] group = new Group[6];
    public static ArrayList<Group> lineGroup = new ArrayList<>();
    public static double screenHeight;
    public static double screenWidth;
    public static Pane pane = new Pane();
    public static FadeTransition[] fade = new FadeTransition[6];

   
    public static Pane cirlce() {
        // create an object from Forrest class to use recursion method
        Forrest forrest = new Forrest();

        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds(); 
        screenHeight=screenBounds.getHeight();
        screenWidth = screenBounds.getWidth();
        
        // create a Group array to put 6 groups together
        for(int i=0;i<6;i++){
            group[i]= new Group();
        }
        
        // give lines to each group
        for(int i=0;i<6;i++){
            forrest.setOrder(i, group[i]);
        }
        createEffects(0);
        return pane;
        



        
    }
    public static void stopCircle(){
        pane.getChildren().clear();
        
        for(int i =0; i<group.length;i++){
        group[i] = null;
        }
       
    }
    public static void createEffects(int order){
        try{
        pane.setStyle("-fx-background-color: #000000;");
        if(order<6){
            System.out.println(order);
            group[order].setOpacity(0);
            fade[order] = new FadeTransition(Duration.seconds(1),group[order]); 
            fade[order].setFromValue(0);
            fade[order].setToValue(1);
            pane.getChildren().add(group[order]);
            fade[order].setOnFinished(e ->{
            
            createEffects(order+1);
            });
            fade[order].play();
        }
        if(order==5){
            //pane.getChildren().removeAll(group);
            createBackwardEffects(5);
        }
        
        }catch(Exception e){
            System.out.println("Caught circles... fix later...");
        }
        
        
    }
    public static void createBackwardEffects(int order){
        try{
            pane.setStyle("-fx-background-color: #000000;");
            if(order>=0){
                System.out.println(order);
                group[order].setOpacity(1);
                fade[order] = new FadeTransition(Duration.seconds(1),group[order]); 
                fade[order].setFromValue(1);
                fade[order].setToValue(0);
                //pane.getChildren().add(group[order]);
                fade[order].setOnFinished(e ->{

                createBackwardEffects(order-1);
                });
                fade[order].play();
            }
            if(order==0){
                pane.getChildren().removeAll(group);
                createEffects(0);
            }
        } catch(Exception e){
            System.out.println("Caught exception... ended circles");
        }
    }

    

    public static class Forrest extends Pane {

        public void setOrder(int order, Group group) {
            // add lines to a specific group, using order
                drawTrees(screenWidth/2, screenHeight/2,200, order, group);

        }
        


        public void drawTrees(double x,double y, int r, int counter, Group group) {
            if (counter>=0){
                Circle circle = new Circle();
                circle.setFill(null);
                circle.setStroke(Color.WHITE);
                circle.setStrokeWidth(7);
                circle.setCenterX(x);
                circle.setCenterY(y);
                circle.setRadius(r);
                group.getChildren().add(circle);


                drawTrees(x-r,y,r/2,counter-1,group);

                drawTrees(x+r,y,r/2,counter-1,group);   
            
        }   
        
                

        }
        
        
        
    }
    
}
