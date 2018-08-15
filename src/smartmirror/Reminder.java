/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartmirror;

import java.util.ArrayList;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import static smartmirror.SmartMirror.USED_FONT;

/**
 *
 * @author Nicholas
 */
public class Reminder {

    public VBox reminderBox;
    private ArrayList<String> reminderList = new ArrayList<>();
    private final int MAX_LIST_SIZE = 9;

    public Reminder() {
        reminderBox = new VBox();
        Text remindersTitle = new Text("Reminders:");
        remindersTitle.setFont(Font.font(USED_FONT, 40));
        remindersTitle.setFill(Color.WHITE);
        remindersTitle.setUnderline(true);
        reminderBox.getChildren().add(remindersTitle);

        //Initial item in list
        addReminder("Fix this shitty program");
        addReminder("Voice detection off");
        addReminder("Motion detection off");
        addReminder("Stocks broken");


    }

    public void addReminder(String reminder) {
        try {
            String reminderCapitalized = reminder.substring(0, 1).toUpperCase() + reminder.substring(1);
            if (reminderList.size() < MAX_LIST_SIZE) {
                reminderList.add(reminderCapitalized);
            } else {
                System.out.println("Already have 9 reminders, remove one if you would like to add more...");
            }
            updateList();
            System.out.println("Reminder added. Updated list");

        } catch (Exception e) {
            System.out.println("Nothing said");
        }

    }

    private void updateList() {
        reminderBox.getChildren().clear();
        Text remindersTitle = new Text("Reminders:");
        remindersTitle.setFont(Font.font(USED_FONT, 40));
        remindersTitle.setFill(Color.WHITE);
        remindersTitle.setUnderline(true);
        reminderBox.getChildren().add(remindersTitle);
        for (int i = 0; i < reminderList.size(); i++) {
            Text currentItem = new Text(i + 1 + ". " + reminderList.get(i));
            currentItem.setFont(Font.font(USED_FONT, 25));
            currentItem.setFill(Color.WHITE);
            reminderBox.getChildren().add(currentItem);
        }
    }

    public void removeReminder(String index) {
        // Change index so its 0 based instead of 1 based
        String indexNumber = index.substring(index.lastIndexOf(" ") + 1);
        int indexUncorrected = 0;

        switch (indexNumber) {
            case "one":
                indexUncorrected = 1;
                break;
            case "two":
                indexUncorrected = 2;
                break;
            case "three":
                indexUncorrected = 3;
                break;
            case "four":
                indexUncorrected = 4;
                break;
            case "five":
                indexUncorrected = 5;
                break;
            case "six":
                indexUncorrected = 6;
                break;
            case "seven":
                indexUncorrected = 7;
                break;
            case "eight":
                indexUncorrected = 8;
                break;
            case "nine":
                indexUncorrected = 9;
                break;
        }

        int indexCorrected = indexUncorrected - 1;
        if (indexUncorrected <= reminderList.size()) {
            reminderList.remove(indexCorrected);
        } else {
            System.out.println(indexUncorrected + " doesnt exist.");
        }
        updateList();
    }

    public VBox getReminderBox() {
        return reminderBox;
    }

}
