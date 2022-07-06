package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Constants.HoodConstants;

public class Hood {
    
    public TalonSRX talonSRX;

    private final double kTicksOffset = degreesToTicks(HoodConstants.kAngleOffset);
    private final int kStaticFrictionDeadband = 5; // Value in native units pulled from NerdyLib. The velocity required to overcome static friction.

    private double storedAngle = 0; // Angle used when setting angle through a command

    public Hood() {
        talonSRX = new TalonSRX(HoodConstants.kTalonID);
        talonSRX.configFactoryDefault();

        talonSRX.config_kP(0, HoodConstants.kP);
        talonSRX.config_kI(0, HoodConstants.kI);
        talonSRX.config_kD(0, HoodConstants.kD);
        talonSRX.config_kF(0, HoodConstants.kF);
        talonSRX.configMotionCruiseVelocity(HoodConstants.kMMCruiseVelocity);
        talonSRX.configMotionAcceleration(HoodConstants.kMMAcceleration);
        talonSRX.setNeutralMode(NeutralMode.Coast);
    }

    // If the velocity is above the minimum required for movement, the mechanism is moving.
    private boolean isMoving() {
        return Math.abs(talonSRX.getSelectedSensorVelocity()) > kStaticFrictionDeadband;
    }

    public void setStoredAngleDegrees(double angle) {
        storedAngle = angle;
    }

    public double getStoredAngleDegrees() {
        return storedAngle;
    }

    public void setAngleMotionMagic(double angle) {
        if (isMoving()) {
            talonSRX.set(ControlMode.MotionMagic, degreesToTicks(angle) + kTicksOffset, 
            DemandType.ArbitraryFeedForward, getFFIfMoving());
        } else {
            talonSRX.set(ControlMode.MotionMagic, degreesToTicks(angle) + kTicksOffset,
            DemandType.ArbitraryFeedForward, getFFIfNotMoving(angle - getAngle()));
        }
    }

    public double getAngle() {
        return ticksToDegrees(talonSRX.getSelectedSensorPosition());
    }

    /* NOTE: The following utility functions could be moved to a 
    separate file for organization, but they are placed here for 
    the purpose of isolating the shooter code from the rest of the 
    robot in this repository. */

    private double getFFIfMoving() {
        return HoodConstants.kGravityFF * Math.cos(degreesToRadians(getAngle()));
    }

    // Use arbitrary FF multiplied by sign of error to hold position in right direction
    private double getFFIfNotMoving(double error) {
        double sign = Math.signum(error);
        return HoodConstants.kGravityFF * Math.cos(degreesToRadians(getAngle()))
        + sign * HoodConstants.kArbitraryFF;
    }

    // Convert degrees of arm to native units
    private double degreesToTicks(double degrees) {
        return (degrees / 360) * HoodConstants.kGearRatio * HoodConstants.kEncoderTicksPerRotation;
    }

    private double ticksToDegrees(double ticks) {
        return (ticks / HoodConstants.kEncoderTicksPerRotation) * (1 / HoodConstants.kGearRatio) * 360;
    }

    private double degreesToRadians(double degrees) {
        return degrees * (Math.PI / 180);
    }

}
