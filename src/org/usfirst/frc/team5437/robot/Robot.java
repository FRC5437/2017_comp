
package org.usfirst.frc.team5437.robot;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team5437.robot.commands.CenterGear;
import org.usfirst.frc.team5437.robot.commands.CenterGearNoTarget;
import org.usfirst.frc.team5437.robot.commands.Fire;
import org.usfirst.frc.team5437.robot.commands.LeftGear;
import org.usfirst.frc.team5437.robot.commands.LeftGearAndShoot;
import org.usfirst.frc.team5437.robot.commands.LeftGearLessSpeed;
import org.usfirst.frc.team5437.robot.commands.LeftGearMoreTurn;
import org.usfirst.frc.team5437.robot.commands.LeftGearMoreTurnLessSpeed;
import org.usfirst.frc.team5437.robot.commands.RightGear;
import org.usfirst.frc.team5437.robot.commands.RightGearAndShoot;
import org.usfirst.frc.team5437.robot.commands.RightGearLessSpeed;
import org.usfirst.frc.team5437.robot.commands.RightGearMoreTurn;
import org.usfirst.frc.team5437.robot.commands.RightGearMoreTurnLessSpeed;
import org.usfirst.frc.team5437.robot.subsystems.Chassis;
import org.usfirst.frc.team5437.robot.subsystems.Climber;
import org.usfirst.frc.team5437.robot.subsystems.NavX;
import org.usfirst.frc.team5437.robot.subsystems.PanSweeper;
import org.usfirst.frc.team5437.robot.subsystems.Relay;
import org.usfirst.frc.team5437.robot.subsystems.Shooter;
import org.usfirst.frc.team5437.robot.subsystems.Stirrer;
import org.usfirst.frc.team5437.robot.subsystems.Targeting;
import org.usfirst.frc.team5437.robot.subsystems.Ultrasonic;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static final Chassis chassis = new Chassis();
	public static final Shooter shooter = new Shooter();
	public static final NavX navx = new NavX();
	public static final Climber climber = new Climber();
	public static final Stirrer stirrer = new Stirrer();
	public static final Targeting targeting = new Targeting();
	public static final Relay relay = new Relay();
	public static final Ultrasonic ultrasonic = new Ultrasonic();
	public static final PanSweeper pansweeper = new PanSweeper();
	public static final Object imgLock = new Object();
	
	public static double centerX1 = 0.0;
	public static double centerX2 = 0.0;
	public static int contours = 0;
	private static CvSource cvsource;

	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		NetworkTable.globalDeleteAll();
		RobotMap.init();
		oi = new OI();
		oi.init();
		chooser.addObject("Left Side", new LeftGear());
		chooser.addObject("Center", new CenterGear());
		chooser.addObject("Right Side", new RightGear());
		chooser.addObject("Left Gear And Shoot", new LeftGearAndShoot());
		chooser.addObject("Right Gear And Shoot", new RightGearAndShoot());
		chooser.addDefault("Center No Cam", new CenterGearNoTarget());
		chooser.addObject("Left Side More Turn", new LeftGearMoreTurn());
		chooser.addObject("Left Side Less Speed", new LeftGearLessSpeed());
		chooser.addObject("Left Side More Turn", new LeftGearMoreTurnLessSpeed());
		chooser.addObject("Right Side More Turn", new RightGearMoreTurn());
		chooser.addObject("Right Side Less Speed", new RightGearLessSpeed());
		chooser.addObject("Right Side More Turn Less Speed", new RightGearMoreTurnLessSpeed());
		chooser.addObject("Shoot", new Fire());
		//TODO: Add center and right side autos
		SmartDashboard.putData("Auto mode", chooser);
		AxisCamera cam = CameraServer.getInstance().addAxisCamera("10.54.37.11");
		CvSink cvsink = CameraServer.getInstance().getVideo();
		cvsource = CameraServer.getInstance().putVideo("cam", 320, 240);
		Mat source = new Mat();
		Scalar color = new Scalar(0, 0, 255);
		
		VisionThread visionThread = new VisionThread(cam, new GripPipeline(), pipeline-> {
			if (cvsink.grabFrame(source) == 0) {
				System.out.println(cvsink.getError());
			} else for(int i = 0; i<pipeline.filterContoursOutput().size(); i++) {
				Imgproc.drawContours(source, pipeline.filterContoursOutput(), i, color);
			}
			contours = pipeline.filterContoursOutput().size();
			if (pipeline.filterContoursOutput().size() > 1) {
				
				Rect r1 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
				Rect r2 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(1));
				
				synchronized(imgLock) {
					centerX1 = r1.x + (r1.width / 2);
					centerX2 = r2.x + (r2.width / 2);
				}
			}
			cvsource.putFrame(source);
		});
		visionThread.start();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("centerX", centerX1);

	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("hasGear", RobotMap.lSwitch.get());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
