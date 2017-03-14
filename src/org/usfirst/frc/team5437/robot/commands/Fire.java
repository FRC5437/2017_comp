 package org.usfirst.frc.team5437.robot.commands;

import org.usfirst.frc.team5437.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Fire extends Command {

    public Fire() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    	requires(Robot.stirrer);
    	requires(Robot.pansweeper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.pansweeper.Sweep(0.65);
    	Robot.shooter.Fire();

    	if(timeSinceInitialized() > 0.5 ) {
    	Robot.stirrer.Stir();
    	}
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
    	Robot.shooter.Stop();
    	Robot.stirrer.Stop(); 
    	Robot.pansweeper.Sweep(0.0);
    }
}
