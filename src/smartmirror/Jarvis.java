/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartmirror;

import java.io.File;
import javaFlacEncoder.FLACFileWriter;

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GoogleResponse;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

/**
 * Jarvis Speech API Tutorial
 *
 * @author Aaron Gokaslan (Skylion)
 *
 */
public class Jarvis {

    public Jarvis() {
    }

    public void startListening() {

        GSpeechDuplex dup = new GSpeechDuplex("AIzaSyAKnNGVoIelt_O7FosBGUfURW8pkYbtuhY");//Instantiate the API
        dup.addResponseListener((GoogleResponse gr) -> {
            System.out.println("Google thinks you said: " + gr.getResponse());
            System.out.println("with "
                    + ((gr.getConfidence() != null) ? (Double.parseDouble(gr.getConfidence()) * 100) : null)
                    + "% confidence.");
            System.out.println("Google also thinks that you might have said:"
                    + gr.getOtherPossibleResponses());

            SmartMirror.setQuery(gr.getResponse());
            SmartMirror.setResponseFromGoogle(true);

        } // Adds the listener
        );
        Microphone mic = new Microphone(FLACFileWriter.FLAC);//Instantiate microphone and have 
        // it record FLAC file.
        File file = new File("CRAudioTest.flac");//The File to record the buffer to. 
        //You can also create your own buffer using the getTargetDataLine() method.

        try {
            mic.captureAudioToFile(file);//Begins recording
            System.out.println("you have 5 seconds from now to speak");
            //Thread.sleep(5000);//Records for 5 seconds
            TimeUnit.SECONDS.sleep(6);
            System.out.println("stopped listening...");
            mic.close();//Stops recording
            //Sends xx second voice recording to Google
            byte[] data = Files.readAllBytes(mic.getAudioFile().toPath());//Saves data into memory.
            dup.recognize(data, (int) mic.getAudioFormat().getSampleRate());
            mic.getAudioFile().delete();//Deletes Buffer file
        } catch (Exception ex) {
            ex.printStackTrace();//Prints an error if something goes wrong.
        }

    }

}
