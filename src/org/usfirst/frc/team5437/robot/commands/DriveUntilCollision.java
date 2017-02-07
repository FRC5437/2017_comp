package org.usfirst.frc.team5437.robot.commands;

import org.usfirst.frc.team5437.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveUntilCollision extends Command {
	private boolean hasCollided = false;
	private double forward;
    public DriveUntilCollision(double fwd) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	forward = fwd;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.Drive(forward, 0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return hasCollided;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.Drive(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
