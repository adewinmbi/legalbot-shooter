package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.simulation.PS4ControllerSim;

public class OI {
    public PS4Controller ps4Controller = new PS4Controller(0);
    public PS4ControllerSim ps4ControllerSim = new PS4ControllerSim(ps4Controller);
}