package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class SetAngleHood extends CommandBase{
    
    @Override
    public void execute() {
        RobotContainer.hood.setAngleMotionMagic(RobotContainer.hood.getStoredAngleDegrees());
    }

}
