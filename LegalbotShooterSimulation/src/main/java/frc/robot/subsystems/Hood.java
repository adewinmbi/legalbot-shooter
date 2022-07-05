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
        return Math.abs(talonSRX.getSelectedSensorVelocity()) >= kStaticFrictionDeadband;
    }

    public void setAngleMotionMagic(double angle) {
        if (isMoving()) {
            talonSRX.set(ControlMode.MotionMagic, degreesToTicks(angle) + kTicksOffset, 
            DemandType.ArbitraryFeedForward, HoodConstants.kGravityFF);
        } else {
            talonSRX.set(ControlMode.MotionMagic, degreesToTicks(angle) + kTicksOffset,
            DemandType.ArbitraryFeedForward, HoodConstants.kGravityFF);
        }
    }

    // Convert degrees to native units
    private double degreesToTicks(double degrees) {
        return (degrees / 360) * HoodConstants.kGearRatio * HoodConstants.kEncoderTicksPerRotation;
    }

}
