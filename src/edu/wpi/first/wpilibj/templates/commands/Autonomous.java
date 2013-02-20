/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Relay;
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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Timer time = new Timer(); 
        if(OI.getInstance().getPlan() == 1) {
            time.reset();
            time.start();
            Timer.delay(0.5);
            while((OI.getInstance().ultra.getAverageValue()/1.9) > 65) {
                m_chassis.driveWithAuto(-0.5, 0.0);
            }
            m_chassis.driveWithAuto(-0.4, 0);
            Timer.delay(0.1);
            m_chassis.driveWithAuto(0, 0);
            OI.getInstance().quadL.reset();
            while(OI.getInstance().getLeftQuad() < 520) {
                m_chassis.driveWithAuto(0.0, 0.5);
            }
            m_shooter.shooter.set(-0.85);
            m_chassis.driveWithAuto(0, 0);
            Timer.delay(1);
            m_shooter.light.set(true);
            m_camera.track();
            while((m_camera.x-320>50 || m_camera.x-320<-50) && time.get() < 11){
                m_camera.track();
                m_chassis.driveWithCamera();
                m_chassis.anglometron();
                System.out.println("Camera");
            }
            m_chassis.driveWithAuto(0, 0);
            while(OI.getInstance().getPot() < 550 && time.get() < 12.75){
                System.out.println("Angle");
                m_chassis.anglometron.set(-1.0);
            }
            Timer.delay(0.25);
            System.out.println(time.get());
            while(time.get() < 14.9){
                System.out.println("Shoot");
                m_shooter.shoot = true;
                m_shooter.shoot();
            }
            System.out.println("Shoot");
            m_shooter.shooter.set(0.0);
            m_shooter.shoot = false;
            time.stop();
        }else if(OI.getInstance().getPlan() == 2) {
            time.reset();
            time.start();
            Timer.delay(0.5);
            while((OI.getInstance().ultra.getAverageValue()/1.9) > 65) {
                m_chassis.driveWithAuto(-0.5, 0.0);
            }
            m_chassis.driveWithAuto(-0.4, 0);
            Timer.delay(0.1);
            m_chassis.driveWithAuto(0, 0);
            OI.getInstance().quadL.reset();
            while(OI.getInstance().getRightQuad() < 520) {
                m_chassis.driveWithAuto(0.0, -0.5);
            }
            m_shooter.shooter.set(-0.85);
            m_chassis.driveWithAuto(0, 0);
            Timer.delay(1);
            m_shooter.light.set(true);
            m_camera.track();
            while((m_camera.x-320>50 || m_camera.x-320<-50) && time.get() < 11){
                m_camera.track();
                m_chassis.driveWithCamera();
                m_chassis.anglometron();
                System.out.println("Camera");
            }
            m_chassis.driveWithAuto(0, 0);
            while(OI.getInstance().getPot() < 550 && time.get() < 12.75){
                System.out.println("Angle");
                m_chassis.anglometron.set(-1.0);
            }
            Timer.delay(0.25);
            System.out.println(time.get());
            while(time.get() < 14.9){
                System.out.println("Shoot");
                m_shooter.shoot = true;
                m_shooter.shoot();
            }
            System.out.println("Shoot");
            m_shooter.shooter.set(0.0);
            m_shooter.shoot = false;
            time.stop();
        }else if(OI.getInstance().getPlan() == 3) {
            time.reset();
            time.start();
            Timer.delay(0.5);
            while(OI.getInstance().getLeftQuad() > -600) {
                m_chassis.driveWithAuto(0.5, 0.0);
            }
            m_chassis.driveWithAuto(-0.4, 0);
            Timer.delay(0.1);
            m_chassis.driveWithAuto(0, 0);
            m_shooter.shooter.set(-0.75);
            while(OI.getInstance().getPot() < 435 && time.get() < 10){
                System.out.println("Angle");
                m_chassis.anglometron.set(-1.0);
            }
            Timer.delay(2);
            while(time.get() < 14.9){
                System.out.println("Shoot");
                m_shooter.shoot = true;
                m_shooter.shoot();
            }
            System.out.println("Shoot");
            m_shooter.shooter.set(0.0);
            m_shooter.shoot = false;
            time.stop();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
        m_shooter.shooter.set(0.0);
        m_shooter.shoot = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
