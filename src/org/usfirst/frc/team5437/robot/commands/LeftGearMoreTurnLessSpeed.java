package org.usfirst.frc.team5437.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LeftGearMoreTurnLessSpeed extends CommandGroup {

    public LeftGearMoreTurnLessSpeed() {
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
    	addParallel(new ResetYaw());
    	System.out.println("Starting Test");
    	addSequential(new DriveForTime(1.0, 0.0, 0.7));
    	addSequential(new WaitCommand(0.4));
    	addSequential(new TurnToAngle(70));
    	System.out.println("Waiting");
    	addSequential(new WaitCommand(0.6));
    	addSequential(new CameraDrive(0.25, 1.0));
    	addSequential(new DriveForTime(0.3, 0.0, 1.5));
    }
}
