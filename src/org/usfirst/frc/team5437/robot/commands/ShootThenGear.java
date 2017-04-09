package org.usfirst.frc.team5437.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootThenGear extends CommandGroup {

    public ShootThenGear(Alliance alliance) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	if (alliance == DriverStation.Alliance.Blue) {
    		addSequential(new DriveForTime(0.5, -0.35, 0.4));
    		addSequential(new DriveForTime(0.5, 0.35, 0.4));
    	} else {
    		addSequential(new DriveForTime(0.5, 0.35, 0.4));
    		addSequential(new DriveForTime(0.5, -0.35, 0.4));
    	}
    	addSequential(new CameraDrive(0.35, 0));
    	addSequential(new DriveForTime(0.35, 0.0, 0.5));
    }
}
