package org.usfirst.frc.team5437.robot.subsystems;

import org.usfirst.frc.team5437.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Targeting extends Subsystem {
	double targetX = 0.0;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    @SuppressWarnings("deprecation")
	double[] GetTarget() {
    	double[] results = new double[4];
    	double[] areas = RobotMap.grip.getNumberArray("myContoursReport/area"),
    			centerX = RobotMap.grip.getNumberArray("myContoursReport/centerX"),
    			centerY = RobotMap.grip.getNumberArray("myContoursReport/centerY"),
    			width = RobotMap.grip.getNumberArray("myContoursReport/width"),
    			height = RobotMap.grip.getNumberArray("myContoursReport/height");    			    					
    			    			
    	double targetWidth = -1.0;
    	int index = -1;
    	for (int i = 0; i < areas.length; i++) {
    		if (areas[i] > targetWidth) {
    			targetWidth = areas[i];
    			index = i;
    		}
    	}

    	if (targetWidth >= 0.0) {
    		results[0] = centerX[index];
    		results[1] = centerY[index];
    		results[2] = width[index];
    		results[3] = height[index];
    	}
    	return results;
    }
}

