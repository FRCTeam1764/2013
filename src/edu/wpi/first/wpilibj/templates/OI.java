
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Encoder;

//Dice was here!
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public static final int JOYSTICK_PORT = 1;
    public static final int JOYSTICK_PORT_2 = 2;
    
    public static final int US_PORT_1 = 1;
    public static final int US_PORT_2 = 2;
    public static final int US_PORT_3 = 3;
    public static final int QUAD_PORT_1 = 1;
    public static final int QUAD_PORT_2 = 2;
    public static final int QUAD_PORT_3 = 3;
    public static final int QUAD_PORT_4 = 4;
    public static final int QUAD_PORT_5 = 5;
    public static final int QUAD_PORT_6 = 6;
    public static final int POT_PORT = 4;
    
    private static OI instance = null;
    
    private AnalogChannel us;
    private AnalogChannel us2;
    private AnalogChannel us3;
    public Encoder quad;
    public Encoder quadL;
    public Encoder quadR;
    public AnalogChannel pot;
    
    private Joystick stick;
    private Joystick stick2;
    
    private JoystickButton j2b1;
    private JoystickButton j2b2;
    private JoystickButton j2b3;
    private JoystickButton j2b4;
    private JoystickButton j2b5;
    
    private OI() {
        us = new AnalogChannel(US_PORT_1);
        us2 = new AnalogChannel(US_PORT_2);
        us3 = new AnalogChannel(US_PORT_3);
        quad = new Encoder(QUAD_PORT_1, QUAD_PORT_2);
        quadL = new Encoder(QUAD_PORT_3, QUAD_PORT_4);
        quadR = new Encoder(QUAD_PORT_5, QUAD_PORT_6);
        quad.start();
        quadL.start();
        quadR.start();
        pot = new AnalogChannel(POT_PORT);
        
        stick = new Joystick(JOYSTICK_PORT);
        stick2 = new Joystick(JOYSTICK_PORT_2);
        
        j2b1 = new JoystickButton(stick2, 1);
        j2b2 = new JoystickButton(stick2, 2);
        j2b3 = new JoystickButton(stick2, 3);
        j2b4 = new JoystickButton(stick2, 4);
    }
    
  public static OI getInstance() {
      if(instance == null) {
          instance = new OI();
      }
      return instance;
  }
  
  public int getPlan() {
      return 1;
  }
    
  public double getUltrasonic() {
      return us.getValue() / 1.9;
  }
  
  public double getEncoder() {
      quad.start();
      return quad.getRate();
  }
  
  public double getLeftQuad() {
      quadL.start();
      return quadL.getDistance();
  }
  
  public double getRightQuad() {
      quadR.start();
      return quadR.getDistance();
  }
  
  public double getPot() {
      return pot.getValue();
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
}

