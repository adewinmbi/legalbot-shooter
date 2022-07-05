// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class TurretConstants {
        public static final int kTurretMasterTalonID = 0;
    }

    public static final class HoodConstants {
        public static final int kHoodTalonID = 0;

        public static final double kHoodGearRatio = 0;
        public static final double kHoodAngleOffset = 0;

        // PIDF
        public static final double kHoodP = 0;
        public static final double kHoodI = 0;
        public static final double kHoodD = 0;
        public static final double kHoodF = 0;

        // Feedforwards
        public static final double kHoodArbitraryFF = 0;
        public static final double kHoodGravityFF = 0;

        // Motion Magic values
        public static final double kHoodMMCruiseVelocity = 0; // MM = Motion Magic
        public static final double kHoodMMAcceleration = 0;

        // System identification coefficients
        public static final double kHoodS = 0;
        public static final double kHoodV = 0;
        public static final double kHoodA = 0;
        public static final double kHoodCos = 0;
    }

}
