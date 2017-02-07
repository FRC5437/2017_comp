package org.usfirst.frc.team5437.robot.subsystems;

import org.usfirst.frc.team5437.robot.commands.NavXPrintYaw;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class NavX extends Subsystem {
	
	private static final double COLLISION_DETECTION_FACTOR = 0.5;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	AHRS navx = new AHRS(SPI.Port.kMXP);
	double last_x = 0.0;
	double last_y = 0.0;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new NavXPrintYaw());
    }
    public double getYaw() {
    	return navx.getYaw();
    }
    public boolean detectCollision() {
    	double accel_x = navx.getWorldLinearAccelX();
    	double accel_y = navx.getWorldLinearAccelY();
    	double jerk_x = accel_x - last_x;
    	double jerk_y = accel_y - last_y;
    	last_x = accel_x;
    	last_y = accel_y;
    	if(jerk_x > COLLISION_DETECTION_FACTOR || jerk_y > COLLISION_DETECTION_FACTOR) return true; 
    	else return false;
    }
}

