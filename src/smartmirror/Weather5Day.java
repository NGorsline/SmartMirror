/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartmirror;

import java.net.URL;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Nicholas
 * 
 * TODO:
 * -A way to change the city on the fly, and have a default city(probably Seattle, 
 *  or use  if at all possible to get. (FROM ip?)
 */
public class Weather5Day{
///////////////////////////////////////////////////////////
    private final String API_KEY = "a31eb0ca7eb3d0d77f4fe6eb86424e6f";
    private final String DEFAULT_CITY = "Seattle";
    private  Document document;
    
    private String cityName = "City";
    private String currentTemp = "-";
    private String cloudStatus = "-";
    private String maxT = "-";
    private String minT = "-";
    private String rainStatus = "-";
    private String icon = "na";
    
    /**
     * Default constructor, calls update to populate fields
     */
    public Weather5Day(){
            //updateXML();

    }
    
    /**
     * Gets a new XML file from OpenWeatherMap.org using, and builds a DOM parser
     * to parse the file and get the requested data.
     */
    public void updateXML(){
        String currentCity = DEFAULT_CITY;
        
        try{
            URL currentWeatherXML = new URL("http://api.openweathermap.org/data/2.5/weather?q=" 
                    + currentCity + "&mode=xml&appid=" + API_KEY);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(currentWeatherXML.openStream());
            // Debug
            System.out.println("Updating XML... DONE");
            // Calls the update
            updateWeather();
            
        }catch(Exception e){
            System.out.println("Error: Couldn't load XML\nCheck internet connection...");
            // change current weather to "null"? or leave it as error, or last known weather. idk yet
        }
    }
    
    /**
     * This method constructs and returns a VBox with all the weather information 
     * in it
     * @return The VBox to display 
     */
    public VBox getWeatherVB(){
        //Make sure everything is updated
        updateXML();
        //mess with insets, font, size, dividers, allignment, type of pane
        VBox weatherVb = new VBox();
        weatherVb.setPadding(new Insets(10));
        //weatherVb.setSpacing(20);
        
        Text city = new Text(getCityName());
        city.setFont(Font.font("Adam", 60));
        city.setFill(Color.WHITE);
        city.setFontSmoothingType(FontSmoothingType.LCD);
        
        
        Text cloudState = new Text(getCloudStatus());
        cloudState.setFont(Font.font("Adam", 30));
        cloudState.setFill(Color.WHITE);
        
        Text temperature = new Text(getCurrentTemp() ); // + "°"
        temperature.setFont(Font.font("Adam", 80));
        temperature.setFill(Color.WHITE);
        
        ImageView weatherIcon = new ImageView(new Image(getClass().getResourceAsStream(
                "/resources/weather_icons/" + getIcon() + ".png"), 100, 100, true, true));
        Text rainState = new Text("rainStatus(test) = " + getRain());
        
        weatherVb.getChildren().addAll(city, cloudState, temperature, weatherIcon, rainState);
        
        return weatherVb;
    }
    
    /**
     * Updates the fields in the class.
     * This method is a helper method for updateXML
     */
    private void updateWeather(){
        cityName = getElement(document, "city", "name");
        currentTemp = kelvinToFahrenheit(getElement(document, "temperature", "value"));
        cloudStatus = getElement(document, "weather", "value");
        icon = getElement(document, "weather", "icon");
        System.out.println(icon);
        
       
        maxT = kelvinToFahrenheit(getElement(document, "temperature", "max"));
        minT = kelvinToFahrenheit(getElement(document, "temperature", "min"));
        rainStatus = getRain(document);
    }
    
        /**
     * This method return the element that is in a specific node, that has this specific 
     * attribute
     * @param doc The XML document to scan through
     * @param node The node who's children to look through
     * @param attribute The attribute to look for
     * @return The resulting data from the requested data field
     */
    public String getElement(Document doc, String node, String attribute){
        Node currentNode = doc.getElementsByTagName(node).item(0);
        Element currentElement = (Element) currentNode;
        return currentElement.getAttribute(attribute);
    }
    
    public String getRain(Document doc){
        NodeList rainNodeList = doc.getElementsByTagName("precipitation");
        String rainState = null;
        for(int i = 0; i < rainNodeList.getLength(); i++){
            Node node = rainNodeList.item(i);
            Element element = (Element) node;
            rainState = element.getAttribute("mode");
            //System.out.println(rainState);
        }
        return rainState;
    }
    
    /**
     * This method converts Kelvin to Fahrenheit
     * @param kelvin the value to convert
     * @return the Fahrenheit value
     */
    public String kelvinToFahrenheit(String kelvin){
        double k = Double.parseDouble(kelvin);
        double f = k*(9.0/5.0)-459.67;
        f = Math.round(f);
        return Double.toString(f);
    }
    
    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }
    
    /**
     * @return the rain
     */
    public String getRain() {
        return rainStatus;
    }
    
    /**
     * @return the minT
     */
    public String getMinT() {
        return minT.replaceAll("\\.0*$", "");
    }
    
    /**
     * @return the maxT
     */
    public String getMaxT() {
        return maxT.replaceAll("\\.0*$", "");
    }
    
    /**
     * @return the cloudStatus
     */
    public String getCloudStatus() {
        return cloudStatus;
    }
    
    /**
     * @return the cityName
     */
    public String getCityName() {
        
        return cityName;
        
    }

    /**
     * @return the currentTemp
     */
    public String getCurrentTemp() {
        return currentTemp.replaceAll("\\.0*$", "");
    }
}
