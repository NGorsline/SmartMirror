/*
 * Copyright 2013 Carnegie Mellon University.
 * Portions Copyright 2004 Sun Microsystems, Inc.
 * Portions Copyright 2004 Mitsubishi Electric Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL
 * WARRANTIES.
 */
package edu.cmu.sphinx.demo.dialog;

import edu.cmu.sphinx.api.lsr.Configuration;
import edu.cmu.sphinx.api.lsr.LiveSpeechRecognizer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import smartmirror.SmartMirror;

public class DialogDemo implements Runnable {

    private static final String ACOUSTIC_MODEL
            = "resource:/edu/cmu/sphinx/models/en-us/en-us";
    private static final String DICTIONARY_PATH
            = "resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict";
    private static final String GRAMMAR_PATH
            = "resource:/edu/cmu/sphinx/demo/dialog/";
    private static final String LANGUAGE_MODEL
            = "resource:/edu/cmu/sphinx/demo/dialog/weather.lm";
    public static String utterance = " ";
    public static String lastUtterance = "";
    
    // Commands that need Jarvis
    private final String[] jarvisCommandList = {"search wolfram alpha", "add reminder"};

    @Override
    public void run() {
        try {
            Configuration configuration = new Configuration();
            configuration.setAcousticModelPath(ACOUSTIC_MODEL);
            configuration.setDictionaryPath(DICTIONARY_PATH);
            configuration.setGrammarPath(GRAMMAR_PATH);
            configuration.setUseGrammar(true);

            configuration.setGrammarName("dialog");
            LiveSpeechRecognizer jsgfRecognizer
                    = new LiveSpeechRecognizer(configuration);

            configuration.setUseGrammar(false);
            configuration.setLanguageModelPath(LANGUAGE_MODEL);

            while (true) {
                System.out.println("\n\n\n--------Speak now----------\nlast =    " + utterance);
                jsgfRecognizer.startRecognition(true);
                utterance = jsgfRecognizer.getResult().getHypothesis();

                System.out.println(utterance);
                jsgfRecognizer.stopRecognition();

                if (inCommandList(utterance) && SmartMirror.isListening) {
                    jsgfRecognizer.closeRecognitionLine();
                    SmartMirror.setCommand(utterance);
                    SmartMirror.setNewCommand(true);
                    break;
                }
                SmartMirror.setCommand(utterance);
                SmartMirror.setNewCommand(true);
            }
            System.out.println("Broken out of loop in DialogDemo");
        } catch (IOException ex) {
            Logger.getLogger(DialogDemo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Helper method to check if the command is in the list of commands that requires
     * the use of the Jarvis API
     * @param command Command to be checked
     * @return said result
     */
    private boolean inCommandList(String command){
        for(int i = 0; i < jarvisCommandList.length; i++){
            if(command.equals(jarvisCommandList[i])){
                return true;
            }
        }
        return false;
    }
}
