package org.usfirst.frc.team5437.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BlueBoilerAuto extends CommandGroup {

    public BlueBoilerAuto() {
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
    	addParallel(new DriveForTime(1.0,0.0,1.0));
    	addParallel(new TurnToAngle(60.0));
    	addParallel(new target());
    	addParallel(new DriveUntilCollision(0.5));
    	addParallel(new WaitUntilNoGear());
    	addParallel(new DriveForTime(-1.0, 0.0, 0.2));
    	addParallel(new TurnToAngle(45.0));
    	addParallel(new ShortTurnToAngle(45.0));
    	addParallel(new DriveUntilCollision(-0.8));
    	addParallel(new Fire());
    }
}
