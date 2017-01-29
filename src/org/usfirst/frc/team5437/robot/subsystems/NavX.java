package org.usfirst.frc.team5437.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class NavX extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	AHRS navx = new AHRS(SPI.Port.kMXP);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public double getYaw() {
    	return navx.getYaw();
    }
}

