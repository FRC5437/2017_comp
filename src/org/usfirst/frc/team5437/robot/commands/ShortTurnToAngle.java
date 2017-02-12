package org.usfirst.frc.team5437.robot.commands;

import org.usfirst.frc.team5437.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShortTurnToAngle extends PIDCommand {
	private double setpoint;
	private static final double kP = 0.06;
	private static final double kI = 0.0;
	private static final double kD = 0.0;
	private static boolean onTarget = false;
	private static int onTargetCounter = 0;
	private static int counterTarget = 5;
    public ShortTurnToAngle(double rotation) {
    	super(kP, kI, kD);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	requires(Robot.navx);
    	setpoint = rotation;
    	setInputRange(-180.0, 180.0);
    	getPIDController().setContinuous();
    }

    // Called just before this Command runs the first time
    protected double returnPIDInput() {
    	return Robot.navx.getYaw();
    }
    protected void initialize() {
    	SmartDashboard.putData(this.getPIDController().getSmartDashboardType(), this.getPIDController());
    	setSetpoint(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Yaw", Robot.navx.getYaw());
    	SmartDashboard.putNumber("avgError", getPIDController().getAvgError());
    	SmartDashboard.putNumber("error", getPIDController().getError());
    	if (getPosition() <= setpoint + 4 && getPosition() >= setpoint - 4) {
    		onTargetCounter++;
    	} else onTargetCounter = 0;
    	if (onTargetCounter >= counterTarget) {
    		onTarget = true;
    	}
    }
    protected void usePIDOutput(double output) {
    	Robot.chassis.Drive(0.0, -output);
    	SmartDashboard.putNumber("output", output);
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return onTarget;
    }

    // Called once after isFinished returns true
    protected void end() {
    	onTarget = false;
    	onTargetCounter = 0;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
