package org.usfirst.frc.team5437.robot.subsystems;

import org.usfirst.frc.team5437.robot.RobotMap;
import org.usfirst.frc.team5437.robot.commands.StartSweep;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PanSweeper extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new StartSweep());
    }
    public void Sweep(double speed) {
    	RobotMap.panSweeper.set(speed);
    }
}

