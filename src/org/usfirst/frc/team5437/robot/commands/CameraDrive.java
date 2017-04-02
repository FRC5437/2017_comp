package org.usfirst.frc.team5437.robot.commands;

import org.usfirst.frc.team5437.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CameraDrive extends Command {
	double pegPos = 0.0;
	double tarPos = 120.0; //TODO: Change back to 120 for competition
	boolean hasCollided = false;
	double spd = 0.0;
	boolean isTimed = false;
    public CameraDrive(double speed, double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	spd = speed;
    	if(time > 0) {
    	isTimed = true;
    	setTimeout(time);
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.contours == 0) {
    		Robot.chassis.Drive(0.0, 0.2);
    	} else {
    		pegPos = (Robot.centerX1 + Robot.centerX2) / 2;
    		pegPos -= tarPos;
    		if (pegPos < -10 || pegPos > 10){
    			pegPos /= 2;
    		}
    		Robot.chassis.Drive(spd, pegPos * -0.008);
    		if(timeSinceInitialized() > 1.5) {
    			hasCollided = Robot.navx.detectCollision();
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (isTimed) {
    		return isTimedOut();
    	} else {
    		return hasCollided;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	hasCollided = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
