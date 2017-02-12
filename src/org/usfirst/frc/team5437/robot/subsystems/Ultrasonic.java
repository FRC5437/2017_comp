package org.usfirst.frc.team5437.robot.subsystems;

import org.usfirst.frc.team5437.robot.RobotMap;
import org.usfirst.frc.team5437.robot.commands.GetDistance;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Ultrasonic extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GetDistance());
    }
    public double getDistance() {
    	return RobotMap.ultrasonic.getVoltage() * 977 / 25.4; //returns distance in inches
    }
}

