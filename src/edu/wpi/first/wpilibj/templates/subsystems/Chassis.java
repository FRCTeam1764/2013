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
    public static final int BACK_LEFT_MOTOR_PORT = 2;
    public static final int FRONT_RIGHT_MOTOR_PORT = 3;
    public static final int BACK_RIGHT_MOTOR_PORT = 4;
    public static final int ANGLOMETRON_PORT = 5;
    public RobotDrive drive;
    public Victor left;
    public Victor right;
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
        drive = new RobotDrive(FRONT_LEFT_MOTOR_PORT, BACK_LEFT_MOTOR_PORT, FRONT_RIGHT_MOTOR_PORT, BACK_RIGHT_MOTOR_PORT);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        left = new Victor(FRONT_LEFT_MOTOR_PORT);
        right = new Victor(FRONT_RIGHT_MOTOR_PORT);
        anglometron = new Victor(ANGLOMETRON_PORT);
    }

    public void driveWithJoystick() {
        if (OI.getInstance().getJoystickButton21().get() == false) {
            drive.arcadeDrive(OI.getInstance().getJoystick());
            anglometron.set(OI.getInstance().getJoystick2().getAxis(Joystick.AxisType.kY));
        } else {
            driveWithCamera();
        }
    }

    public void driveWithCamera() {
        int x = Camera.getInstance().x - 160;
        
        if (x > 50) {
            left.set(0.5);
            right.set(-0.5);
        }else if (x < 50) {
            left.set(-0.5);
            right.set(0.5);
        }else if (x > 30) {
            left.set(0.4);
            right.set(-0.4);
        }else if (x < 30) {
            left.set(-0.4);
            right.set(0.4);
        }else if (x > 20) {
            left.set(0.2);
            right.set(-0.2);
        }else if (x < 20) {
            left.set(-0.2);
            right.set(0.2);
        }
    }
    
    public void anglometron() {
        int y = Camera.getInstance().y - 120;
        
        if (y > 0) {
            anglometron.set(-0.5);
        }else if (y < 0) {
            anglometron.set(0.5);
        }else{
            anglometron.set(0.0);
        }
    }
    
    public void driveWithAuto(double fwd, double turn) {
        drive.arcadeDrive(fwd, turn);
    }

    protected void initDefaultCommand() {
    }
}