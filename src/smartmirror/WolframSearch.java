/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartmirror;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAImage;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Nicholas
 */
public class WolframSearch {
    
    private static final String APP_ID = "HT7UUX-X5JHJ9RV5E";
    private static final double MAGNIFICATION = 1.5; 
    private static final int MAX_WIDTH = 1000;// 1400

    /**
     * 
     * @param input The string to send to WFA
     * @return The Vertical box that will get added to the center panel of the magic mirror
     */
    public static VBox getResultsFor(String input){
        //need to mess with spacing and formatting the output
        VBox vb = new VBox(10);
        
        // The WAEngine is a factory for creating WAQuery objects,
        // and it also used to perform those queries.
        WAEngine engine = new WAEngine();
        
        // These properties will be set in all the WAQuery objects created from this WAEngine.
        engine.setAppID(APP_ID);
        engine.setMaxWidth(MAX_WIDTH);
        engine.setWidth(MAX_WIDTH);
        // magnification
        engine.setMagnification(MAGNIFICATION);
       
        // Create the query.
        WAQuery query = engine.createQuery();
        
        // Set properties of the query.
        query.setInput(input);
        
        try {
            // For educational purposes, print out the URL we are about to send:
            System.out.println("Query URL:");
            System.out.println(engine.toURL(query));
            System.out.println("");
            
            // This sends the URL to the Wolfram|Alpha server, gets the XML result
            // and parses it into an object hierarchy held by the WAQueryResult object.
            WAQueryResult queryResult = engine.performQuery(query);
            
            if (queryResult.isError()) {
                System.out.println("Query error");
                System.out.println("  error code: " + queryResult.getErrorCode());
                System.out.println("  error message: " + queryResult.getErrorMessage());
                
                vb.getChildren().add(new Text(queryResult.getErrorMessage()));
                
            } else if (!queryResult.isSuccess()) {
                System.out.println("Query was not understood; no results available.");
                Text errorText = new Text("Query was not understood; no results available. Try again...");
                errorText.setFill(javafx.scene.paint.Color.RED);
                vb.getChildren().add(errorText);
            } else {
                // Got a result.
//                System.out.println("Successful query. Pods follow:\n");
                System.out.println("Successful query. Results on the way...");

                for (WAPod pod : queryResult.getPods()) {
                    if (!pod.isError()) {
                        //more debug
//                        System.out.println(pod.getTitle());
//                        System.out.println("------------");
                        for (WASubpod subpod : pod.getSubpods()) {
                            for (Object element : subpod.getContents()) {
                                //prints out plain text to console, debug only
//                                if (element instanceof WAPlainText) {
//                                    System.out.println(((WAPlainText) element).getText());
//                                    System.out.println("");
//                                    
//                                }
                                if (element instanceof WAImage){
                                    String imageUrl = ((WAImage) element).getURL();
                                    Image testImage = new Image(imageUrl);
                                    ImageView imv = new ImageView(invertImage(testImage));
                                    vb.getChildren().add(imv);
                                }
                            }
                        }
//                        System.out.println("");
                    }
                }
                // We ignored many other types of Wolfram|Alpha output, such as warnings, assumptions, etc.
                // These can be obtained by methods of WAQueryResult or objects deeper in the hierarchy.
            }
        } catch (WAException e) {
            e.printStackTrace();
            vb = displayError();
        }
        
        // the final return statement
        return vb;
    }
    
    public static VBox displayError(){
        VBox errorVB = new VBox();
        Text error = new Text("Error with wolfram");
        error.setFill(javafx.scene.paint.Color.WHITE);
        errorVB.getChildren().add(error);
        
        return errorVB;
    }
    
    public static Image invertImage(Image image) {
        BufferedImage bImg = SwingFXUtils.fromFXImage(image, null);

        for (int x = 0; x < bImg.getWidth(); x++) {
            for (int y = 0; y < bImg.getHeight(); y++) {
                int rgba = bImg.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color(255 - col.getRed(),
                                255 - col.getGreen(),
                                255 - col.getBlue());
                bImg.setRGB(x, y, col.getRGB());
            }
        }
        
        Image convertedImage = SwingFXUtils.toFXImage(bImg, null);
        
        return convertedImage;
    }
}