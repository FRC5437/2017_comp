package org.usfirst.frc.team5437.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
	static WPI_TalonSRX drive1 = new WPI_TalonSRX(0);
	static WPI_TalonSRX drive2 = new WPI_TalonSRX(1);
	static WPI_TalonSRX drive3 = new WPI_TalonSRX(2);
	static WPI_TalonSRX drive4 = new WPI_TalonSRX(3);
	public static NetworkTable grip;
	
	public static RobotDrive chassis = new RobotDrive(drive1, drive2, drive3, drive4);
	
	public static WPI_TalonSRX shooter = new WPI_TalonSRX(4);
	public static WPI_TalonSRX shooter2 = new WPI_TalonSRX(5);
	public static WPI_TalonSRX panSweeper = new WPI_TalonSRX(6);
	public static Spark stirrer = new Spark(0);
	public static Victor climber1 = new Victor(1);
	public static Victor climber2 = new Victor(2);
	public static DigitalInput lSwitch = new DigitalInput(1);
	public static DigitalOutput lights = new DigitalOutput(0);
	public static AnalogInput ultrasonic = new AnalogInput(0);
	public static void init() {
		drive1.setInverted(false);
		drive1.setInverted(false);
		drive3.setInverted(true);
		drive4.setInverted(true);
		chassis.setSafetyEnabled(false);
		grip = NetworkTable.getTable("GRIP");
		lights.set(true);

	}
}
