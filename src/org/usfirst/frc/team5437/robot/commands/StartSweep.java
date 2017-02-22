package org.usfirst.frc.team5437.robot.commands;

import org.usfirst.frc.team5437.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartSweep extends Command {

    public StartSweep() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.pansweeper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*double time = timeSinceInitialized();
    	if ((time + 6.0) % 12 < 12.0 && (time + 6.0) % 12 > 6.0) {
    		Robot.pansweeper.Sweep(1.0);
    	} else {
    		Robot.pansweeper.Sweep(-1.0);
    	}*/
    	//Robot.pansweeper.Sweep(1.0);
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
