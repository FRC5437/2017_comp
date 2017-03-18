package org.usfirst.frc.team5437.robot;

import org.usfirst.frc.team5437.robot.commands.Climb;
import org.usfirst.frc.team5437.robot.commands.Fire;
import org.usfirst.frc.team5437.robot.commands.LeftGear;
import org.usfirst.frc.team5437.robot.commands.LeftGearLessSpeed;
import org.usfirst.frc.team5437.robot.commands.LeftGearMoreTurn;
import org.usfirst.frc.team5437.robot.commands.LeftGearMoreTurnLessSpeed;
import org.usfirst.frc.team5437.robot.commands.RelayToggle;
import org.usfirst.frc.team5437.robot.commands.ResetYaw;
import org.usfirst.frc.team5437.robot.commands.RightGear;
import org.usfirst.frc.team5437.robot.commands.RightGearLessSpeed;
import org.usfirst.frc.team5437.robot.commands.RightGearMoreTurn;
import org.usfirst.frc.team5437.robot.commands.RightGearMoreTurnLessSpeed;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	Joystick joy1 = new Joystick(0);
	Button b1_1 = new JoystickButton(joy1, 1);
	Button b1_2 = new JoystickButton(joy1, 2);
	Button b1_3 = new JoystickButton(joy1, 3);
	Joystick joy2 = new Joystick(1);
	Button b2_1 = new JoystickButton(joy2, 1);
	Button b2_2 = new JoystickButton(joy2, 2);
	Button b2_3 = new JoystickButton(joy2, 3);
	Button b2_4 = new JoystickButton(joy2, 4);
	Button b2_5 = new JoystickButton(joy2, 5);
	Button b2_6 = new JoystickButton(joy2, 6);
	Button b2_7 = new JoystickButton(joy2, 7);
	Button b2_8 = new JoystickButton(joy2, 8);
	Button b2_9 = new JoystickButton(joy2, 9);
	Button b2_11 = new JoystickButton(joy2, 11);
	Button b2_12 = new JoystickButton(joy2, 12);
	public void init() {
		b1_2.whileHeld(new Climb());
		//b1_2.whileHeld(new Drop()); NO. BAD.
		//b1_2.whenPressed(new BBATest1());
		//b1_3.whenPressed(new CameraDrive());
		b1_1.whileHeld(new Fire());
		b2_1.whenPressed(new RightGear());
		b2_2.whenPressed(new LeftGear());
		b2_3.whenPressed(new RightGearMoreTurn());
		b2_4.whenPressed(new RightGearLessSpeed());
		b2_5.whenPressed(new RightGearMoreTurnLessSpeed());
		b2_6.whenPressed(new LeftGearMoreTurn());
		b2_7.whenPressed(new LeftGearLessSpeed());
		b2_8.whenPressed(new LeftGearMoreTurnLessSpeed());
		b2_9.whenPressed(new RelayToggle());
		//b2_3.whenPressed(new LeftGearAndShoot());
		//b2_11.whenPressed(new RotateFromPeg(45));
		b2_12.whenPressed(new ResetYaw());
	}
	public Joystick getJoy1(){return joy1;}
	public Joystick getJoy2(){return joy2;} 
}
