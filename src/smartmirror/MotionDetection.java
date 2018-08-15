/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartmirror;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 *
 * @author Nicholas
 * 
 * This class uses the pi4j library to listen to the GPIO pin(s) and look for
 * motion from the PIR sensor. Sets a boolean based on motion condition, in which
 * the SmartMirror is listening for. 
 */
public class MotionDetection {
    
    public void startDetection(){
        System.out.println("motion detection started...");
        final GpioController gpioSensor = GpioFactory.getInstance();
        final GpioPinDigitalInput sensor = gpioSensor.provisionDigitalInputPin(RaspiPin.GPIO_07, PinPullResistance.PULL_DOWN);
        GpioPinListenerDigital listener;
        listener = (GpioPinDigitalStateChangeEvent event) -> {
            System.out.println("--> GPIO pin state change: " + event.getPin() + " = " + event.getState());
            if(event.getState().isHigh()){
                SmartMirror.setMotionDetected(true);
                System.out.println("setting motionDetected true");
            }
            else if (event.getState().isLow()){
                SmartMirror.setMotionDetected(false);
                System.out.println("setting mortionDetected false");
            }

        };

        sensor.addListener(listener);
    }
    
}
