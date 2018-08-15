/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartmirror;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import static smartmirror.SmartMirror.USED_FONT;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 *
 * @author Nicholas
 */
public class StockTracker {

    //If anything changes here, need to change in both.
    //Should use a hashmap in the future, but this works for the moment
    private static String[] stocks = {
        "MSFT",
        "LLY",
        "COST",
        "BA", 
        "XOM",
        "VLKPF",
        "CMG",
        "TSLA", 
        "F",
        "AAPL"};
    private static String[] stockNames = {
        "Microsoft",
        "Eli Lilly",
        "Costco",
        "Boeing",
        "Exxon",
        "Volk. PRF",
        "Chipotle",
        "Tesla",
        "Ford",
        "Apple"};

    public static GridPane getStockGrid() {
        GridPane gp = new GridPane();
        gp.setHgap(10);
        Stock currentStock = null;
        for (int row = 0; row < stocks.length; row++) {
            try {
                currentStock = YahooFinance.get("MSFT");
            } catch (IOException ex) {
                Logger.getLogger(StockTracker.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Check internet...\nReturning error grid");
                
                Text errorText = new Text("Check connection...");
                errorText.setFont(Font.font(USED_FONT, 20));
                errorText.setFill(Color.color(.95, .25, .21));
                gp.add(errorText, 0, 0);
                return gp;
            } catch (Exception e) {
                System.out.println("class: StockTracker, with row = " + row);
                continue;
            }
            //String name = currentStock.getName();
            String name = stockNames[row];
            double price = currentStock.getQuote().getPrice().doubleValue();
            double changeInPercent = currentStock.getQuote().getChangeInPercent().doubleValue();
            double changeInValue = currentStock.getQuote().getChange().doubleValue();
            
            // Crappy way to fix alighment with negative values
            String negAlignment = "";
            
            Color colorToUse;
            if(changeInValue < 0){
                // red
                colorToUse = Color.color(.95, .25, .21);
                
            }
            else{
                // green
                colorToUse = Color.color(.30, .70, .31);
                negAlignment = " ";
            }
            
            Text nameT = new Text(name);
            nameT.setFont(Font.font(USED_FONT, 20));
            nameT.setFill(Color.WHITE);
            
            Text priceT = new Text(Double.toString(price));
            priceT.setFont(Font.font(USED_FONT, 20));
            priceT.setFill(Color.WHITE);
            
            Text value = new Text(negAlignment + changeInValue);
            value.setFont(Font.font(USED_FONT, 20));
            value.setFill(colorToUse);

            Text percent = new Text(negAlignment + changeInPercent + "%");
            percent.setFont(Font.font(USED_FONT, 20));
            percent.setFill(colorToUse);
            
            gp.add(nameT, 0, row);
            gp.add(priceT, 1, row);
            gp.add(value, 2, row);
            gp.add(percent, 3, row);
            
            System.out.println(name + " " + price + " " + changeInPercent + "% " + changeInValue);
        }
        return gp;
    }

//    /**
//     * This is just a patch until I get have a better solution to get the DJI, because
//     * for some reason YahooFinance API does not support some indexes...
//     * @return An array of values relevant {Price, percent, value}
//     * @throws IOException JIC
//     */
//    private static String[] getDow() throws IOException{
//        String[] DJI = new String[4];
//        DJI[0] = "DOW J";
//        URL url = new URL("http://finance.yahoo.com/webservice/v1/symbols/%5EDJI/quotes");
//        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//        for (String line = in.readLine(); line != null; line = in.readLine()) {
//            System.out.println(line);
//            if(line.startsWith("<field name=\"price\">")){
//                int start = line.indexOf(">") +1;
//                String value = line.substring(start, start + 8);
//                System.out.println(value);
//                DJI[1] = value;
//            }
//            
//        }   
//        return DJI;
//    }
}
