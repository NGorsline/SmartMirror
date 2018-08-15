/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartmirror;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import static smartmirror.SmartMirror.USED_FONT;

/**
 *
 * @author Nick Pretty much just creates and returns(if called) a node that will
 * be displayed for the user to know what commands they can say, which will
 * enact different methods and functionality
 */
public class VoiceDialogHelp {


    public static VBox whatCanISay() {
        int textSize = 30;
        int subtextSize = 20;

        VBox WCIS = new VBox();
        WCIS.setPadding(new Insets(10, 10, 10, 10));
        //WCIS.setAlignment(Pos.CENTER);

        Text AvailableCommands = new Text("Available Commands:\n");
        AvailableCommands.setFont(Font.font(USED_FONT, textSize));
        AvailableCommands.setFill(Color.WHITE);

        Text WCIStext = new Text("\"What can I say?\"");
        WCIStext.setFont(Font.font(USED_FONT, textSize));
        WCIStext.setFill(Color.WHITE);

        Text WCIStextDescription = new Text("      Shows the list of available voice commands");
        WCIStextDescription.setFont(Font.font(USED_FONT, subtextSize));
        WCIStextDescription.setFill(Color.WHITE);

        Text wrs = new Text("\"Search Wolfram Alpha\"");
        wrs.setFont(Font.font(USED_FONT, textSize));
        wrs.setFill(Color.WHITE);

        Text wrsDescription = new Text("      Promps for a query for Wolfram Alpha");
        wrsDescription.setFont(Font.font(USED_FONT, subtextSize));
        wrsDescription.setFill(Color.WHITE);
        
        Text reminder = new Text("\"Add reminder/Remove reminder #\"");
        reminder.setFont(Font.font(USED_FONT, textSize));
        reminder.setFill(Color.WHITE);

        Text reminderDesc = new Text("      Lets you add or remove a specific reminder");
        reminderDesc.setFont(Font.font(USED_FONT, subtextSize));
        reminderDesc.setFill(Color.WHITE);

        Text home = new Text("\"Return home\"");
        home.setFont(Font.font(USED_FONT, textSize));
        home.setFill(Color.WHITE);

        Text homeDescription = new Text("      Returns you back to the main screen");
        homeDescription.setFont(Font.font(USED_FONT, subtextSize));
        homeDescription.setFill(Color.WHITE);

        Text sss = new Text("\"Show tree\"");
        sss.setFont(Font.font(USED_FONT, textSize));
        sss.setFill(Color.WHITE);

        Text sssDescription = new Text("      Starts up the tree demo");
        sssDescription.setFont(Font.font(USED_FONT, subtextSize));
        sssDescription.setFill(Color.WHITE);
        
        Text ssf = new Text("\"Show star field\"");
        ssf.setFont(Font.font(USED_FONT, textSize));
        ssf.setFill(Color.WHITE);

        Text ssfD = new Text("      Displays a randomly generated star field");
        ssfD.setFont(Font.font(USED_FONT, subtextSize));
        ssfD.setFill(Color.WHITE);

        Text awaken = new Text("\"Show Circles\"");
        awaken.setFont(Font.font(USED_FONT, textSize));
        awaken.setFill(Color.WHITE);

        Text awakenDesc = new Text("      Starts up the circles demo");
        awakenDesc.setFont(Font.font(USED_FONT, subtextSize));
        awakenDesc.setFill(Color.WHITE);

        Text pts = new Text("\"Begin/End Recognition\"");
        pts.setFont(Font.font(USED_FONT, textSize));
        pts.setFill(Color.WHITE);

        Text ptsDescription = new Text("      Enables and disables the microphone");
        ptsDescription.setFont(Font.font(USED_FONT, subtextSize));
        ptsDescription.setFill(Color.WHITE);

        WCIS.getChildren().addAll(AvailableCommands, WCIStext, WCIStextDescription, wrs, wrsDescription, reminder, reminderDesc,
                home, homeDescription, sss, sssDescription, ssf, ssfD, awaken, awakenDesc, pts, ptsDescription);

        return WCIS;
    }
}
