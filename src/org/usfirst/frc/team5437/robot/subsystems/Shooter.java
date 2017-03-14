package org.usfirst.frc.team5437.robot.subsystems;

import org.usfirst.frc.team5437.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void Fire() {
    	RobotMap.shooter.set(3900);
    	RobotMap.shooter.enable();
    }
    public void Stop() {
    	RobotMap.shooter.set(0.0);
    	RobotMap.shooter.disable();
    }
}

