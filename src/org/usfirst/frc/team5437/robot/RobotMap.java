package org.usfirst.frc.team5437.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	static CANTalon drive1 = new CANTalon(0);
	static CANTalon drive2 = new CANTalon(1);
	static CANTalon drive3 = new CANTalon(2);
	static CANTalon drive4 = new CANTalon(3);
	public static NetworkTable grip;
	
	public static RobotDrive chassis = new RobotDrive(drive1, drive2, drive3, drive4);
	
	public static CANTalon shooter = new CANTalon(4);
	public static CANTalon shooter2 = new CANTalon(5);
	public static CANTalon panSweeper = new CANTalon(6);
	public static Spark stirrer = new Spark(0);
	public static Victor climber1 = new Victor(1);
	public static Victor climber2 = new Victor(2);
	public static DigitalInput lSwitch = new DigitalInput(1);
	public static DigitalOutput lights = new DigitalOutput(0);
	public static AnalogInput ultrasonic = new AnalogInput(0);
	public static void init() {
		drive1.reverseOutput(false);
		drive1.reverseOutput(false);
		drive3.reverseOutput(true);
		drive4.reverseOutput(true);
		chassis.setSafetyEnabled(false);
		shooter.changeControlMode(CANTalon.TalonControlMode.Speed);
		shooter.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		shooter.setPID(0.1, 0.0, 0.0, 0.026, 0, 0.0, 0);
		shooter.disable();
		shooter.setInverted(true);
		shooter.reverseSensor(true);
		shooter2.changeControlMode(CANTalon.TalonControlMode.Follower);
		shooter2.set(4);
		grip = NetworkTable.getTable("GRIP");
		lights.set(true);

	}
}
