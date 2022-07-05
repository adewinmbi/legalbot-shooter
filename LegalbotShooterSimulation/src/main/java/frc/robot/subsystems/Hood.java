package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Constants.HoodConstants;

public class Hood {
    
    public TalonSRX talonSRX;

    public Hood() {
        talonSRX = new TalonSRX(HoodConstants.kTalonID);
        talonSRX.config_kP(0, HoodConstants.kP);
        talonSRX.config_kI(0, HoodConstants.kI);
        talonSRX.config_kD(0, HoodConstants.kD);
        talonSRX.config_kF(0, HoodConstants.kF);
        talonSRX.configMotionCruiseVelocity(HoodConstants.kMMCruiseVelocity);
        talonSRX.configMotionAcceleration(HoodConstants.kMMAcceleration);
        talonSRX.setNeutralMode(NeutralMode.Coast);
    }

}
