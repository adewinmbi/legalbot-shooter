package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.Constants;

public class Turret {

    public TalonFX turretMaster;

    public Turret() {
        turretMaster = new TalonFX(Constants.TurretConstants.kTurretMasterTalonID);
    }

}
