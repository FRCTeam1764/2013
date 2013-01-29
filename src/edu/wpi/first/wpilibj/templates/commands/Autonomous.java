/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.subsystems.*;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author bigcompy
 */
public class Autonomous extends CommandBase {
    Camera m_camera;
    Chassis m_chassis;
    Shooter m_shooter;
    
    boolean done = false;
    
    public Autonomous() {
        m_camera = Camera.getInstance();
        m_chassis = Chassis.getInstance();
        m_shooter = Shooter.getInstance();
        requires(m_camera);
        requires(m_chassis);
        requires(m_shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        m_chassis.drive.setSafetyEnabled(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(OI.getInstance().getPlan() == 1) {
            while(OI.getInstance().getLeftQuad() < 1000) {
                m_chassis.driveWithAuto(0.65, 0.0);
            }
            OI.getInstance().quadL.reset();
            while(OI.getInstance().getLeftQuad() < 500) {
                m_chassis.driveWithAuto(0.0, 0.5);
            }
            m_chassis.driveWithAuto(0, 0);
            while(m_camera.x-160>20 || m_camera.x-160<-20 || m_camera.y-120>20 || m_camera.y-120<-20){
                m_camera.track(true);
                m_chassis.driveWithCamera();
                m_chassis.anglometron();
            }
            m_chassis.driveWithAuto(0, 0);
            m_shooter.buttons(true);
            Timer.delay(1);
            for(int i = 0; i < 3; i++){
                m_shooter.shoot(true);
                Timer.delay(1);
            }
            done = true;
        }else if(OI.getInstance().getPlan() == 2) {
            while(OI.getInstance().getRightQuad() < 1000) {
                m_chassis.driveWithAuto(0.65, 0.0);
            }
            OI.getInstance().quadR.reset();
            while(OI.getInstance().getRightQuad() < 500) {
                m_chassis.driveWithAuto(0.0, -0.5);
            }
            m_chassis.driveWithAuto(0, 0);
            while(m_camera.x-160>20 || m_camera.x-160<-20 || m_camera.y-120>20 || m_camera.y-120<-20){
                m_camera.track(true);
                m_chassis.driveWithCamera();
                m_chassis.anglometron();
            }
            m_chassis.driveWithAuto(0, 0);
            m_shooter.buttons(true);
            Timer.delay(1);
            for(int i = 0; i < 3; i++){
                m_shooter.shoot(true);
                Timer.delay(1);
            }
            done = true;
        }else if(OI.getInstance().getPlan() == 3) {
            while(m_camera.x-160>20 || m_camera.x-160<-20 || m_camera.y-120>20 || m_camera.y-120<-20){
                m_camera.track(true);
                m_chassis.driveWithCamera();
                m_chassis.anglometron();
            }
            m_chassis.driveWithAuto(0, 0);
            m_shooter.buttons(true);
            Timer.delay(1);
            for(int i = 0; i < 3; i++){
                m_shooter.shoot(true);
                Timer.delay(1);
            }
            done = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
