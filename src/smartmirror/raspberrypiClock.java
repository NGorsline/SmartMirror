/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartmirror;

/**
 *
 * @author Muhaamed
 */
/**
Computer Science II CS143 Winter Quarter raspberrypi project.
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.*;
import javafx.util.Duration;
import java.util.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/** 
    The raspberrypiClock class implements an application that 
    displays an AnalogueClock, a DigitalClock, and a Calendar 
    Dawit Abera.
*/
public class raspberrypiClock {
//  public static void main(String[] args) throws Exception { 
//      launch(args);
//  }
//  @Override
//  public void start(final Stage stage) throws Exception {
//    /** 
//     * Construct the AnalogueClock and the DigitalClock pieces
//     * Formats the Calendar
//     * Constructs the analogueClock pieces (Hour,Minute,Second)
//     * Imports a Cascaded Style Sheet for the raspberrypiClock class
//     * Add animation to rotate the AnalogueClock and a key frame to update the DigitalClock pieces 
//     * Add effects to the Scene
//     * Add mouthDraged effect.
//     */
//      
//    
//     // Draws the brand name for the analogueClock
//    final Label Group_name     = new Label("Silent Assassins");
//                Group_name.setId("brand");
//    final Date dateD = new Date(); 
//    final Calendar calendar1 = GregorianCalendar.getInstance();
//    final Calendar calendar  = GregorianCalendar.getInstance();
//     //Formats the Calendar and displays the month           
//    final Label monthFormat = new Label();
//                DateFormat month_Format = new SimpleDateFormat("MMMM");
//                monthFormat.setId("newFont");
//                monthFormat.setText(month_Format.format(dateD));
//     //Formats the Calendar and displays the date           
//    final Label dayFormat = new Label();              
//                DateFormat date_Format = new SimpleDateFormat("EEEE");
//                dayFormat.setId("newFont");
//                dayFormat.setText(date_Format.format(dateD));
//     //Formats the Calendar and displays the day           
//    final Label dateFormat = new Label();
//                DateFormat date = new SimpleDateFormat("d");
//                dateFormat.setId("DateFont");
//                dateFormat.setText(date.format(dateD));
//                
//    /** 
//     Construct the AnalogueClock pieces. 
//    */
//                
//    final Circle circile     = new Circle(100, 100, 100);
//                 circile.setFill(Color.TRANSPARENT);
//                 circile.setId("face");
//                 //Draws the brand just below the circle
//                Group_name.layoutXProperty()
//                           .bind(circile.centerXProperty()
//                           .subtract(Group_name.widthProperty()
//                           .divide(2)));
//                Group_name.layoutYProperty()
//                          .bind(circile.centerYProperty()
//                          .add(circile.radiusProperty()
//                          .divide(2)));
//    final Line hourLine   = new Line(0, 0, 0, -50);
//               hourLine.setTranslateX(100);  
//               hourLine.setTranslateY(100);
//               hourLine.setId("hourLine");
//    final Line minuteLine = new Line(0, 0, 0, -75);
//               minuteLine.setTranslateX(100); 
//               minuteLine.setTranslateY(100);
//               minuteLine.setId("minuteLine");
//    final Line secondLine = new Line(0, 15, 0, -88);
//               secondLine.setTranslateX(100); 
//               secondLine.setTranslateY(100);
//               secondLine.setId("secondLine");
//    final Circle spindle = new Circle(100, 100, 5);
//                 Text t1 = new Text(100-5,100-100+12,"12"); 
//                 Text t2 = new Text(100-100+3,100+5,"9");
//                 Text t3 = new Text(100+100-10,100+3,"3");
//                 Text t4 = new Text(100-3,100+100-3,"6");
//                 spindle.setId("spindle");
//                 Group ticks = new Group();
//                        for (int i = 0; i < 12; i++) {
//                              Line tick = new Line(0, -83, 0, -93);
//                                    tick.setTranslateX(100); tick.setTranslateY(100);
//                                    tick.getStyleClass().add("tick");
//                                    tick.getTransforms().add(new Rotate(i * (360 / 12)));
//                                    ticks.getChildren().add(tick);
//    }
//    final Group analogueClock = new Group(circile,Group_name, ticks, spindle, t1,t2,t3,t4,hourLine, minuteLine, secondLine);
//
//
//    // Set the time.
//    final double Second  = calendar.get(Calendar.SECOND) * (360 / 60);
//    final double Minute  = (calendar.get(Calendar.MINUTE) + Second / 360.0) * (360 / 60);
//    final double Hour    = (calendar.get(Calendar.HOUR)   + Minute / 360.0) * (360 / 12) ;
//
//    // Add rotation to the AnalogueClock and then to the time.
//    final Rotate hourRotate      = new Rotate(Hour);
//    final Rotate minuteRotate    = new Rotate(Minute);
//    final Rotate secondRotate    = new Rotate(Second);
//   
//          hourLine.getTransforms().add(hourRotate);
//          minuteLine.getTransforms().add(minuteRotate);
//          secondLine.getTransforms().add(secondRotate);
//
//    // the hour hand rotates twice a day.
//    final Timeline hourTime = new Timeline(
//      new KeyFrame(
//        Duration.hours(12),
//        new KeyValue(
//          hourRotate.angleProperty(),
//          360 + Hour,
//          Interpolator.LINEAR
//        )
//      )
//    );
//
//    // the minute hand rotates once an hour.
//    final Timeline minuteTime = new Timeline(
//      new KeyFrame(
//        Duration.minutes(60),
//        new KeyValue(
//          minuteRotate.angleProperty(),
//          360 + Minute,
//          Interpolator.LINEAR
//        )
//      )
//    );
//
//    // move second hand rotates once a minute.
//    final Timeline secondTime = new Timeline(
//      new KeyFrame(
//        Duration.seconds(60),
//        new KeyValue(
//          secondRotate.angleProperty(),
//          360 + Second,
//          Interpolator.LINEAR
//        )
//      )
//    );
//    
//    /**
//     * Construct the DigitalClock pieces.
//     */
//      
//    final Label digitalClock = new Label();
//                digitalClock.setId("fancytext");
//                
//    /**
//     * The Digital clock has to update the every second.
//     * Add key frame to the time line to update the Digital clock each second
//    */
//    
//    final Timeline digitalTime = new Timeline(
//      new KeyFrame(Duration.seconds(0), 
//              (ActionEvent actionEvent) -> {
//          
//          String hourString = pad(2, '0', calendar1.get(Calendar.HOUR) == 0 ? "12" : calendar1.get(Calendar.HOUR) + "");
//          String minuteString = pad(2, '0',calendar1.get(Calendar.MINUTE) + "");
//          String secondString = pad(2, '0', calendar1.get(Calendar.SECOND) + "");
//          String ampmString = calendar1.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
//          digitalClock.setText(hourString + ":" + minuteString + ":" + secondString + " " + ampmString);
//    }),
//      new KeyFrame(Duration.seconds(1))
//    );
//    
//
//    // The time has to loop infinitely .
//    hourTime.setCycleCount(Animation.INDEFINITE);
//    minuteTime.setCycleCount(Animation.INDEFINITE);
//    secondTime.setCycleCount(Animation.INDEFINITE);
//    digitalTime.setCycleCount(Animation.INDEFINITE);
//
//    // start the animation for the AnalogueClock.
//    digitalTime.play();
//    secondTime.play();
//    minuteTime.play();
//    hourTime.play();
//
//    stage.initStyle(StageStyle.TRANSPARENT);
//    /**
//       Add effects to the scene. 
//    */
//    // add a glow effect the scene.
//    final Glow glow = new Glow(0.2);
//    analogueClock.setOnMouseEntered((MouseEvent mouseEvent) -> {
//        analogueClock.setEffect(glow);
//        dayFormat.setEffect(glow);
//        dateFormat.setEffect(glow);
//        monthFormat.setEffect(glow);
//        digitalClock.setEffect(glow);
//    }); 
//    // remove the effects after mouse exited  
//    analogueClock.setOnMouseExited((MouseEvent mouseEvent) -> {
//        analogueClock.setEffect(null);
//        dayFormat.setEffect(null);
//        dateFormat.setEffect(null);
//        monthFormat.setEffect(null);
//    });
//
//    // add effect for exiting the scene
//    analogueClock.setOnMouseClicked((MouseEvent mouseEvent) -> {
//        analogueClock.setMouseTransparent(true);
//                     FadeTransition fade = new FadeTransition(Duration.seconds(3), analogueClock);
//                       fade.setOnFinished((ActionEvent actionEvent) -> {
//             stage.close();
//        });
//        fade.setFromValue(1);
//        fade.setToValue(0);
//        fade.play();
//    });
//
//    /**
//       Construct and Design the Scene.
//    */
//    // Creat a layout
//    final VBox layout = new VBox();
//    final HBox lay_out = new HBox();
//    //add elements to the layout
//    lay_out.getChildren().addAll(dayFormat,dateFormat);
//    layout.getChildren().addAll(monthFormat,lay_out,analogueClock, digitalClock);
//    layout.setAlignment(Pos.CENTER);
//    lay_out.setAlignment(Pos.CENTER);
//    // Group the layout before adding it the scene
//    Group group = new Group(layout);
//    final Scene scene = new Scene(group,Color.TRANSPARENT);
//    scene.getStylesheets().add(getResource("clock.css"));
//    stage.setScene(scene);
//    
//// records relative x and y co-ordinates.
//class xyCoordinate { 
//      double x, y; 
// } 
//    // allow the clock background to be used to drag the raspberrypiClock around.
//    final xyCoordinate xyCoordinate= new xyCoordinate();
//    layout.setOnMousePressed((MouseEvent mouseEvent) -> {
//        // record a xyCoordinate distance for the drag.
//        xyCoordinate.x = stage.getX() - mouseEvent.getScreenX();
//        xyCoordinate.y = stage.getY() - mouseEvent.getScreenY();
//        scene.setCursor(Cursor.MOVE);
//    });
//    layout.setOnMouseReleased((MouseEvent mouseEvent) -> {
//        scene.setCursor(Cursor.HAND);
//    });
//    layout.setOnMouseDragged((MouseEvent mouseEvent) -> {
//        stage.setX(mouseEvent.getScreenX() + xyCoordinate.x);
//        stage.setY(mouseEvent.getScreenY() + xyCoordinate.y);
//    });
//    layout.setOnMouseEntered((MouseEvent mouseEvent) -> {
//        if (!mouseEvent.isPrimaryButtonDown()) {
//            scene.setCursor(Cursor.HAND);
//        }
//    });
//    layout.setOnMouseExited((MouseEvent mouseEvent) -> {
//        if (!mouseEvent.isPrimaryButtonDown()) {
//            scene.setCursor(Cursor.DEFAULT);
//        }
//    });
//
//    // show the scene.
//    stage.show();
//  }


  private static String pad(int fieldWidth, char padChar, String s) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = s.length(); i < fieldWidth; i++) {
      stringBuilder.append(padChar);
    }
    stringBuilder.append(s);

    return 
            stringBuilder.toString();
  }
  static String getResource(String path) {
    return raspberrypiClock.class.getResource(path).toExternalForm();
  }
  
  public static Group clock(){
       /** 
     * Construct the AnalogueClock and the DigitalClock pieces
     * Formats the Calendar
     * Constructs the analogueClock pieces (Hour,Minute,Second)
     * Imports a Cascaded Style Sheet for the raspberrypiClock class
     * Add animation to rotate the AnalogueClock and a key frame to update the DigitalClock pieces 
     * Add effects to the Scene
     * Add mouthDraged effect.
     */
      
    
     
    
    final Date dateD = new Date(); 
    final Calendar calendar1 = GregorianCalendar.getInstance();
    final Calendar calendar  = GregorianCalendar.getInstance();
     //Formats the Calendar and displays the month           
//    final Label monthFormat = new Label();
//                DateFormat month_Format = new SimpleDateFormat("MMMM");
//                monthFormat.setId("newFont");
//                monthFormat.setText(month_Format.format(dateD));
//     //Formats the Calendar and displays the date           
//    final Label dayFormat = new Label();              
//                DateFormat date_Format = new SimpleDateFormat("EEEE");
//                dayFormat.setId("newFont");
//                dayFormat.setText(date_Format.format(dateD));
//     //Formats the Calendar and displays the day           
//    final Label dateFormat = new Label();
//                DateFormat date = new SimpleDateFormat("d");
//                dateFormat.setId("DateFont");
//                dateFormat.setText(date.format(dateD));
                
    /** 
     Construct the AnalogueClock pieces. 
    */
                
    final Circle circile     = new Circle(200, 200, 300);
                 circile.setFill(Color.TRANSPARENT);
                 circile.setId("face");
                 
                
    final Line hourLine   = new Line(0, 0, 0, -50);
               hourLine.setTranslateX(100);  
               hourLine.setTranslateY(100);
              
               hourLine.setId("hourLine");
    final Line minuteLine = new Line(0, 0, 0, -75);
               minuteLine.setTranslateX(100); 
               minuteLine.setTranslateY(100);
               minuteLine.setId("minuteLine");
    final Line secondLine = new Line(0, 15, 0, -88);
               secondLine.setTranslateX(100); 
               secondLine.setTranslateY(100);
               secondLine.setId("secondLine");
    final Circle spindle = new Circle(100, 100, 5);
                 Text t1 = new Text(100-5,100-100+12,"12"); 
                 Text t2 = new Text(100-100+3,100+5,"9");
                 Text t3 = new Text(100+100-10,100+3,"3");
                 Text t4 = new Text(100-3,100+100-3,"6");
                 spindle.setId("spindle");
                 Group ticks = new Group();
                        for (int i = 0; i < 12; i++) {
                              Line tick = new Line(0, -83, 0, -93);
                                    tick.setTranslateX(100); tick.setTranslateY(100);
                                    tick.getStyleClass().add("tick");
                                    tick.getTransforms().add(new Rotate(i * (360 / 12)));
                                    ticks.getChildren().add(tick);
    }
    final Group analogueClock = new Group(circile, ticks, spindle,hourLine, minuteLine, secondLine);


    // Set the time.
    final double Second  = calendar.get(Calendar.SECOND) * (360 / 60);
    final double Minute  = (calendar.get(Calendar.MINUTE) + Second / 360.0) * (360 / 60);
    final double Hour    = (calendar.get(Calendar.HOUR)   + Minute / 360.0) * (360 / 12) ;

    // Add rotation to the AnalogueClock and then to the time.
    final Rotate hourRotate      = new Rotate(Hour);
    final Rotate minuteRotate    = new Rotate(Minute);
    final Rotate secondRotate    = new Rotate(Second);
   
          hourLine.getTransforms().add(hourRotate);
          minuteLine.getTransforms().add(minuteRotate);
          secondLine.getTransforms().add(secondRotate);

    // the hour hand rotates twice a day.
    final Timeline hourTime = new Timeline(
      new KeyFrame(
        Duration.hours(12),
        new KeyValue(
          hourRotate.angleProperty(),
          360 + Hour,
          Interpolator.LINEAR
        )
      )
    );

    // the minute hand rotates once an hour.
    final Timeline minuteTime = new Timeline(
      new KeyFrame(
        Duration.minutes(60),
        new KeyValue(
          minuteRotate.angleProperty(),
          360 + Minute,
          Interpolator.LINEAR
        )
      )
    );

    // move second hand rotates once a minute.
    final Timeline secondTime = new Timeline(
      new KeyFrame(
        Duration.seconds(60),
        new KeyValue(
          secondRotate.angleProperty(),
          360 + Second,
          Interpolator.LINEAR
        )
      )
    );
    
    /**
     * Construct the DigitalClock pieces.
     */
      
    final Label digitalClock = new Label();
                digitalClock.setId("fancytext");
                
    /**
     * The Digital clock has to update the every second.
     * Add key frame to the time line to update the Digital clock each second
    */
    
//    final Timeline digitalTime = new Timeline(
//      new KeyFrame(Duration.seconds(0), 
//              (ActionEvent actionEvent) -> {
//          
//          String hourString = pad(2, '0', calendar1.get(Calendar.HOUR) == 0 ? "12" : calendar1.get(Calendar.HOUR) + "");
//          String minuteString = pad(2, '0',calendar1.get(Calendar.MINUTE) + "");
//          String secondString = pad(2, '0', calendar1.get(Calendar.SECOND) + "");
//          String ampmString = calendar1.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
//          digitalClock.setText(hourString + ":" + minuteString + ":" + secondString + " " + ampmString);
//    }),
//      new KeyFrame(Duration.seconds(1))
//    );
    

    // The time has to loop infinitely .
    hourTime.setCycleCount(Animation.INDEFINITE);
    minuteTime.setCycleCount(Animation.INDEFINITE);
    secondTime.setCycleCount(Animation.INDEFINITE);
    //digitalTime.setCycleCount(Animation.INDEFINITE);

    // start the animation for the AnalogueClock.
    //digitalTime.play();
    secondTime.play();
    minuteTime.play();
    hourTime.play();

    //stage.initStyle(StageStyle.TRANSPARENT);
    /**
       

    

    /**
       Construct and Design the Scene.
    */
    // Creat a layout
    final VBox layout = new VBox();
    final HBox lay_out = new HBox();
    //add elements to the layout
    //lay_out.getChildren().addAll(dayFormat,dateFormat);
    layout.getChildren().addAll(lay_out,analogueClock, digitalClock);
    layout.setAlignment(Pos.CENTER);
    //lay_out.setAlignment(Pos.CENTER);
    // Group the layout before adding it the scene
    Group group = new Group(layout);
    //final Scene scene = new Scene(group,Color.TRANSPARENT);
    group.getStylesheets().add(getResource("clock.css"));
    //stage.setScene(scene);
    
// records relative x and y co-ordinates.
class xyCoordinate { 
      double x, y; 
 } 
    // allow the clock background to be used to drag the raspberrypiClock around.
//    final xyCoordinate xyCoordinate= new xyCoordinate();
//    layout.setOnMousePressed((MouseEvent mouseEvent) -> {
//        // record a xyCoordinate distance for the drag.
//        xyCoordinate.x = stage.getX() - mouseEvent.getScreenX();
//        xyCoordinate.y = stage.getY() - mouseEvent.getScreenY();
//        scene.setCursor(Cursor.MOVE);
//    });
//    layout.setOnMouseReleased((MouseEvent mouseEvent) -> {
//        scene.setCursor(Cursor.HAND);
//    });
//    layout.setOnMouseDragged((MouseEvent mouseEvent) -> {
//        stage.setX(mouseEvent.getScreenX() + xyCoordinate.x);
//        stage.setY(mouseEvent.getScreenY() + xyCoordinate.y);
//    });
//    layout.setOnMouseEntered((MouseEvent mouseEvent) -> {
//        if (!mouseEvent.isPrimaryButtonDown()) {
//            scene.setCursor(Cursor.HAND);
//        }
//    });
//    layout.setOnMouseExited((MouseEvent mouseEvent) -> {
//        if (!mouseEvent.isPrimaryButtonDown()) {
//            scene.setCursor(Cursor.DEFAULT);
//        }
//    });
//
//    // show the scene.
//    stage.show();
//      
      
      return group;
  
      
  }
  
   
}

