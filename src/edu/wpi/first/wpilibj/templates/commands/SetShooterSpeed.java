/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.subsystems.Shooter;

/**
 *
 * @author bigcompy
 */
public class SetShooterSpeed extends CommandBase {
    Shooter m_shooter;
    
    public SetShooterSpeed() {
        m_shooter = Shooter.getInstance();
        requires(m_shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//        m_shooter.buttons(OI.getInstance().getJoystickButton23().get());
//        m_shooter.normalize();
//        m_shooter.setShooterSpeed();
//        m_shooter.shoot(OI.getInstance().getJoystickButton24().get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
