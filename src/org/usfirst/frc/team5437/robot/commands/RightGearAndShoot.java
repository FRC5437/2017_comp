package org.usfirst.frc.team5437.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RightGearAndShoot extends CommandGroup {

    public RightGearAndShoot(double drivetime, int rotation, double camspeed, double waittime) {
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
    	addSequential(new RightGear(drivetime, rotation, camspeed));
    	addSequential(new WaitCommand(waittime));
    	addSequential(new RotateFromPeg(false, -42));
    	addSequential(new DriveUntilCollision(-0.5));
    	addSequential(new DriveForTime(-0.2, 0.0, 0.2));
    	addSequential(new Fire());
    }
}
