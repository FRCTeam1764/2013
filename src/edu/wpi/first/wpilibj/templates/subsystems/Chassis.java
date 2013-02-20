/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.commands.DriveWithJoystick;

/**
 *
 * @author bigcompy
 */
public class Chassis extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public static final int FRONT_LEFT_MOTOR_PORT = 1;
    public static final int REAR_LEFT_MOTOR_PORT = 2;
    public static final int FRONT_RIGHT_MOTOR_PORT = 3;
    public static final int REAR_RIGHT_MOTOR_PORT = 4;
    public static final int ANGLOMETRON_PORT = 10;
    private Victor fl;
    private Victor rl;
    private Victor fr;
    private Victor rr;
    public Victor anglometron;
    
    private static Chassis instance = null;

    public static Chassis getInstance() {
        if (instance == null) {
            instance = new Chassis();
            instance.setDefaultCommand(new DriveWithJoystick());
        }
        return instance;
    }

    private Chassis() {
        fl = new Victor(FRONT_LEFT_MOTOR_PORT);
        rl = new Victor(REAR_LEFT_MOTOR_PORT);
        fr = new Victor(FRONT_RIGHT_MOTOR_PORT);
        rr = new Victor(REAR_RIGHT_MOTOR_PORT);
        anglometron = new Victor(ANGLOMETRON_PORT);
    }

    public void driveWithJoystick() {
        if (OI.getInstance().getJoystickButton21().get() == false) {
            double y = OI.getInstance().getJoystick().getY();
            double x = OI.getInstance().getJoystick().getZ() * 0.5;
            
            fl.set(y-x);
            rl.set(y-x);
            fr.set(-y-x);
            rr.set(-y-x);
            
            if(OI.getInstance().getJoystickButton211().get() && OI.getInstance().getPot() < 550) {
                anglometron.set(-1.0);
            }else if(OI.getInstance().getJoystickButton210().get() && OI.getInstance().getPot() > 375){
                anglometron.set(1.0);
            }else{
                anglometron.set(0.0);
            }
            System.out.println(OI.getInstance().getLeftQuad() + " + " + OI.getInstance().getRightQuad());
            System.out.println(OI.getInstance().ultra.getAverageValue()/1.9);
            System.out.println("Angle" + OI.getInstance().getPot());
        } else {
            driveWithCamera();
        }
    }

    public void driveWithCamera() {
        double x = Camera.getInstance().x;
        
        if (x > 200) {
            fl.set(-0.6);
            fr.set(-0.6);
            rl.set(-0.6);
            rr.set(-0.6);
        }else if (x < -200) {
            fl.set(0.6);
            fr.set(0.6);
            rl.set(0.6);
            rr.set(0.6);
        }else if (x > 100) {
            fl.set(-0.5);
            fr.set(-0.5);
            rl.set(-0.5);
            rr.set(-0.5);
        }else if (x < -100) {
            fl.set(0.5);
            fr.set(0.5);
            rl.set(0.5);
            rr.set(0.);
        }else if (x > 50) {
            fl.set(-0.46);
            fr.set(-0.46);
            rl.set(-0.46);
            rr.set(-0.46);
        }else if (x < -50) {
            fl.set(0.46);
            fr.set(0.46);
            rl.set(0.46);
            rr.set(0.46);
        }else if(x < 25 && x > -25) {
            fl.set(0.0);
            fr.set(0.0);
            rl.set(0.0);
            rr.set(0.0);
            anglometron();
        }
    }
    
    public void anglometron() {
        int y = Camera.getInstance().y - 240;
        
        if (y > 40) {
            anglometron.set(-1.0);
        }else if (y < -40) {
            anglometron.set(1.0);
        }else if (y < -20) {
            anglometron.set(-0.5);
        }else if (y < -20) {
            anglometron.set(0.5);
        }else{
            anglometron.set(0.0);
        }
    }
    
    public void driveWithAuto(double mY, double mX) {
        fl.set(mY-mX);
        rl.set(mY-mX);
        fr.set(-mY-mX);
        rr.set(-mY-mX);
    }

    protected void initDefaultCommand() {
    }
}