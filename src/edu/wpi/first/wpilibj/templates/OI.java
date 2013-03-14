
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Dice was here!
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public static final int JOYSTICK_PORT = 1;
    public static final int JOYSTICK_PORT_2 = 2;
    
    private static OI instance = null;
    public Encoder quad;
    public Encoder quadL;
    public Encoder quadR;
    public AnalogChannel ultra;
    public AnalogChannel pot;
    public DigitalInput limit1;
    public DigitalInput limit2;
    
    private Joystick stick;
    private Joystick stick2;
    
    private JoystickButton j2b1;
    private JoystickButton j2b2;
    private JoystickButton j2b3;
    private JoystickButton j2b4;
    private JoystickButton j2b5;
    private JoystickButton j2b8;
    private JoystickButton j2b9;
    private JoystickButton j2b11;
    private JoystickButton j2b10;
    
    public OI() {
        ultra = new AnalogChannel(1);
        pot = new AnalogChannel(2);
        limit1 = new DigitalInput(9);
        limit2 = new DigitalInput(10);
        stick = new Joystick(JOYSTICK_PORT);
        stick2 = new Joystick(JOYSTICK_PORT_2);
        quad = new Encoder(13, 11);
        quadL = new Encoder(3, 1);
        quadR = new Encoder(7, 5);
        
        j2b1 = new JoystickButton(stick2, 1);
        j2b2 = new JoystickButton(stick2, 2);
        j2b3 = new JoystickButton(stick2, 3);
        j2b4 = new JoystickButton(stick2, 4);
        j2b5 = new JoystickButton(stick2, 5);
        j2b8 = new JoystickButton(stick2, 8);
        j2b9 = new JoystickButton(stick2, 9);
        j2b11 = new JoystickButton(stick2, 11);
        j2b10 = new JoystickButton(stick2, 10);
        
        startQuads();
    }
    
    public static OI getInstance() {
        if(instance == null) {
            instance = new OI();
        }
        return instance;
    }
    
    private void startQuads() {
        quad.start();
        quadL.start();
        quadR.start();
    }

    public int getPlan() {
        if (SmartDashboard.getDouble("Slider 1") > 25 && SmartDashboard.getDouble("Slider 1") < 75){
            return 3;
        }else if (SmartDashboard.getDouble("Slider 1") < 25) {
            return 1;
        }else if (SmartDashboard.getDouble("Slider 1") > 75){
            return 2;
        }else{
            return 3;
        }
    }

    public double getEncoder() {
        return quad.getRate();
    }

    public double getLeftQuad() {
        return quadL.getDistance();
    }

    public double getRightQuad() {
        return quadR.getDistance();
    }

    public double getPot() {
        return pot.getValue();
    }
    
    public boolean getLimit1() {
        return limit1.get();
    }
    
    public boolean getLimit2() {
        return limit2.get();
    }

    public Joystick getJoystick() {
        return stick;
    }

    public Joystick getJoystick2() {
        return stick2;
    }
    
    public JoystickButton getJoystickButton21() {
        return j2b1; 
    }

    public JoystickButton getJoystickButton22() {
        return j2b2; 
    }

    public JoystickButton getJoystickButton23() {
        return j2b3; 
    }

    public JoystickButton getJoystickButton24() {
        return j2b4; 
    }

    public JoystickButton getJoystickButton25() {
        return j2b5; 
    }
    
    public JoystickButton getJoystickButton28() {
        return j2b8; 
    }
    
    public JoystickButton getJoystickButton29() {
        return j2b9; 
    }
    
    public JoystickButton getJoystickButton211() {
        return j2b11; 
    }
    
    public JoystickButton getJoystickButton210() {
        return j2b10; 
    }
}

