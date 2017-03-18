package org.usfirst.frc.team5437.robot.commands;

import org.usfirst.frc.team5437.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CameraDrive extends Command {
	double pegPos = 0.0;
	double tarPos = 120.0;
	boolean hasCollided = false;
	double spd = 0.0;
    public CameraDrive(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	spd = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.contours == 0) {
    		Robot.chassis.Drive(0.0, 0.2);
    	} else {
    		SmartDashboard.putString("CameraDriveCenters", "x1: " + Robot.centerX1 + "  x2: " + Robot.centerX2);
    		pegPos = (Robot.centerX1 + Robot.centerX2) / 2;
    		pegPos -= tarPos;
    		if (pegPos < -10 || pegPos > 10){
    			System.out.println("Large Delta Detected  **** pegPos: " + pegPos);
    			pegPos /= 2;
    		}
    		Robot.chassis.Drive(spd, pegPos * -0.008);
    		if(timeSinceInitialized() > 1.0) {
    			hasCollided = Robot.navx.detectCollision();
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return hasCollided;
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
