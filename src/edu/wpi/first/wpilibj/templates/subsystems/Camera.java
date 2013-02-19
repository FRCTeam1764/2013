/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.commands.Track;

/**
 *
 * @author bigcompy
 */
public class Camera extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public double x = 0;
    public int y = 0;
    
    private static Camera instance = null;

    public static Camera getInstance() {
        if (instance == null) {
            instance = new Camera();
            instance.setDefaultCommand(new Track());
        }
        return instance;
    }

    private Camera() {
    }

    public void track() {
        x = (SmartDashboard.getDouble("Left") + SmartDashboard.getDouble("Right")) / 2;
        y = SmartDashboard.getInt("Top");
        x = x-320;
    }

    

    protected void initDefaultCommand() {
    }
}