package org.usfirst.frc.team5437.robot.subsystems;

import org.usfirst.frc.team5437.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void Climb() {
    	RobotMap.climber1.set(-1.0);
    	RobotMap.climber2.set(-1.0);
    }
    public void Drop() { //This exists for testing only. It is not intended to be used in competition.
    	RobotMap.climber1.set(1.0);
    	RobotMap.climber2.set(1.0);
    }
    public void Stop() {
    	RobotMap.climber1.set(0.0);
    	RobotMap.climber2.set(0.0);
    }
}

