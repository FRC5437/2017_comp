package org.usfirst.frc.team5437.robot.subsystems;

import org.usfirst.frc.team5437.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Stirrer extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void Stir() {
    	RobotMap.stirrer.set(1.0);
    }
    public void Stop() {
    	RobotMap.stirrer.set(0.0);
    }
}

