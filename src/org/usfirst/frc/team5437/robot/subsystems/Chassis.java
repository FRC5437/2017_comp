package org.usfirst.frc.team5437.robot.subsystems;

import org.usfirst.frc.team5437.robot.Robot;
import org.usfirst.frc.team5437.robot.RobotMap;
import org.usfirst.frc.team5437.robot.commands.DriveRobot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Chassis extends PIDSubsystem {
	final static double kP = 1.8;
	final static double kI = 0.0;//275;
	final static double kD = 0.0;//3.5;//8;

    // Initialize your subsystem here
    public Chassis() {
    	super("Chassis", kP, kI, kD);
    	setInputRange(-180.0, 180.0);
    	setOutputRange(-0.6, 0.6);
    	setAbsoluteTolerance(2.0);
    	getPIDController().setContinuous(true);
    	getPIDController().setToleranceBuffer(1);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveRobot());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return Robot.navx.navx.pidGet();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	Drive(0.0, -output);
    }
   /* public void PIDDrive(double left, double right) {
    	RobotMap.chassis.tankDrive(left, right, false);
    }*/
    public void Drive(double drive, double rotate){
    	RobotMap.chassis.arcadeDrive(drive, rotate, false);
    }
    
}
