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
    public static final int CAM_PORT = 6;
    public static final int LIGHT_PORT = 1;
    
    private Victor shooter;
    private Relay cam;
    private Solenoid light;
    
    public double speed;
    public double power;
    public double lastPower;
    public double want = 200;
    
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
    }
    
    public void buttons(boolean set) {
        light.set(true);
        if (OI.getInstance().getJoystickButton22().get()) {
            speed = 0.0;
        }else if (set) {
            speed = (0.0785*(OI.getInstance().getUltrasonic()*OI.getInstance().getUltrasonic())) + 1914.2;
            //y = 0.0785x^2 + 1914.2
        }
    }
    
    public void normalize() {
        if(Math.abs(speed - OI.getInstance().getEncoder()) > 100){
            power += ((speed - OI.getInstance().getEncoder())/(speed*50));
        }else{
            power = power;
        }
    }
    
    public void setShooterSpeed() {
        power = (power + lastPower)/2;
        shooter.set(power);
        lastPower = power;
    }
    
    public void shoot(boolean go) {
        if(go) {
            want = 700;
        }
        
        if(OI.getInstance().getPot() > want + 40) {
            cam.set(Relay.Value.kReverse);
        }else if(OI.getInstance().getPot() < want - 40) {
            cam.set(Relay.Value.kForward);
        }else{
            cam.set(Relay.Value.kOff);
        }
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}