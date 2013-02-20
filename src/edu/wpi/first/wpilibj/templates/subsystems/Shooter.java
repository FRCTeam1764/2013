/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.commands.SetShooterSpeed;

/**
 *
 * @author bigcompy
 */
public class Shooter extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public static final int SHOOTER_PORT = 5;
    public static final int CAM_PORT = 1;
    public static final int LIGHT_PORT = 1;
    
    public Victor shooter;
    public Relay cam;
    public Solenoid light;
    public Solenoid groundLights;
    
    public boolean go = false;
    public boolean shoot = false;
    public double speed;
    public double power;
    public double lastPower;
    
    private static Shooter instance = null;
    
    /**
     *
     */    
    
    public static Shooter getInstance() {
        if(instance == null) {
            instance = new Shooter();
            instance.setDefaultCommand(new SetShooterSpeed());
        }
        return instance;
    }
    
    private Shooter() {
        shooter = new Victor(SHOOTER_PORT);
        cam = new Relay(CAM_PORT);
        light = new Solenoid(LIGHT_PORT);
        groundLights = new Solenoid(2);
    }
    
    public void buttons(boolean set) {
        if(OI.getInstance().getJoystickButton21().get()){
            light.set(true);
        }
        if (OI.getInstance().getJoystickButton22().get()) {
            shooter.set(0.0);
        }else if (OI.getInstance().getJoystickButton23().get()) {
            shooter.set(-0.75);
        }else if (OI.getInstance().j2b8.get()) {
            shooter.set(-0.5);
        }else if (OI.getInstance().j2b9.get()) {
            shooter.set(-0.85);
        }
    }
    
    public void normalize() {
//        if(Math.abs(speed - OI.getInstance().getEncoder()) > 100 && speed != 0.0){
//            power += ((speed - OI.getInstance().getEncoder())/(speed*500));
//        }else if(speed == 0.0){
//            power = 0;
//            lastPower = 0;
//        }else{
//            power = power;
//        }
    }
    
    public void setShooterSpeed() {
//        power = (power + lastPower)/2;
//        shooter.set(-power);
//        lastPower = power;
    }
    
    public void shoot() {
        if(OI.getInstance().getJoystickButton24().get()) {
            go = true;
        }else{
            go = shoot;
        }
        
        groundLights.set(true);
        
        if(go) {
            while((OI.getInstance().getLimit2() || OI.getInstance().getLimit1() == false) && go) {
                cam.set(Relay.Value.kForward);
                groundLights.set(false);
            }
            groundLights.set(true);
            while((OI.getInstance().getLimit1() || OI.getInstance().getLimit2() == false) && go) {
                cam.set(Relay.Value.kReverse);
                groundLights.set(false);
            }
        }else{
            cam.set(Relay.Value.kOff);
        }
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}