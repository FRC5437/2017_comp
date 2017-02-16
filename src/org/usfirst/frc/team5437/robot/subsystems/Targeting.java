package org.usfirst.frc.team5437.robot.subsystems;

import org.usfirst.frc.team5437.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Targeting extends Subsystem {
	double targetX = 180.0;
	double[] defaults = {0.0};
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	@SuppressWarnings("unused")
	double[] GetTarget() {
    	double[] results = new double[2];
    	double[] areas = RobotMap.grip.getNumberArray("myContoursReport/area", defaults),
    			centerX = RobotMap.grip.getNumberArray("myContoursReport/centerX", defaults),
    			centerY = RobotMap.grip.getNumberArray("myContoursReport/centerY", defaults),
    			width = RobotMap.grip.getNumberArray("myContoursReport/width", defaults),
    			height = RobotMap.grip.getNumberArray("myContoursReport/height", defaults);
    	double targetDistance = Double.MAX_VALUE;
    	int indexI = -1;
    	int indexJ = -1;
    	for (int i = 0; i < areas.length; i++) {
    		for(int j = 0; j < areas.length; j++) {
    			if (i != j) {
    				if ((Math.sqrt(Math.pow(centerX[i], 2) + Math.pow(centerY[i], 2)) - Math.sqrt(Math.pow(centerX[j], 2) + Math.pow(centerY[j], 2))) < targetDistance) {
    					targetDistance = Math.sqrt(Math.pow(centerX[i], 2) + Math.pow(centerY[i], 2)) - Math.sqrt(Math.pow(centerX[j], 2) + Math.pow(centerY[j], 2));
    					indexI = i;
    					indexJ = j;
    				}
    			}
    		}
    	}

    	if (targetDistance != Double.MAX_VALUE) {
    		results[0] = centerX[indexI];
    		results[1] = centerX[indexJ];
    	}
    	return results;
    }
	public double calcDeltaSetpoint(double distance) {
		double[] targets = GetTarget();
		double centerTarget = 0.0;
		if(targets[1] < targets[0]) {
			centerTarget = targets[0] - targets[1];
		} else centerTarget = targets[1] - targets[0];
		double inchesScale = centerTarget / 8.5; //8.5 inches between the targets, so find scaling for distance
		double targetDistance = centerTarget - 180;
		double targetDistanceInches = targetDistance * inchesScale;
		double radiansToRotate = Math.asin(targetDistance / distance);
		double degreesToRotate = radiansToRotate * 180 / Math.PI;
		return degreesToRotate;
	}
}

