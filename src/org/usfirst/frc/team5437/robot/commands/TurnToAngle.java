package org.usfirst.frc.team5437.robot.commands;

import org.usfirst.frc.team5437.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToAngle extends Command {
	private double setpoint;
    public TurnToAngle(double rotation) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	setpoint = rotation;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putNumber("P", Robot.chassis.getPIDController().getP());
    	Robot.chassis.setSetpoint(setpoint);
    	Robot.chassis.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.chassis.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
