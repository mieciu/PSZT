package pl.pszt.fuzzylogic;

import java.awt.*;
//package vis;
import robocode.*;
import java.awt.Color;
import static robocode.util.Utils.normalRelativeAngleDegrees;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

public class FuzzyCornerBot extends Robot
{
    FuzzyStatement fuzzyHealth;
    HealthLevels healthRange;
    FuzzyStatement fuzzyTracked;
    TrackedLevels trackedRange;
    final int defuzzificationDelay = 2;
    int gunIncrement = 3;
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public void run() {
        // Initialization of the robot should be put here
        setColors(Color.black, Color.white, Color.black); // body,gun,radar
        setBulletColor(Color.white);

        fuzzyHealth = new FuzzyStatement("Healthy", 1.0);
        fuzzyTracked = new FuzzyStatement("I am tracked", 0.0);

        int defuzzificationCounter = 0;

        turnRight(normalRelativeAngleDegrees(0 - getHeading()));
        ahead(5000);
        turnLeft(90);
        ahead(5000);
        turnLeft(90);


        // Robot main loop
        while(true) {
            for (int i = 0; i < 30; i++) {
                turnGunLeft(gunIncrement);
                fuzzyTracked.decrementValue(0.01);
            }
            gunIncrement *= -1;
            defuzzificationCounter += 1;

            if(defuzzificationCounter >= defuzzificationDelay) {
                fuzzyHealth.setValue(this.getEnergy()/100.0);

                trackedRange = fuzzyTracked.defuzzificationOfTrackedLevels();
                healthRange = fuzzyHealth.defuzzificationOfHealthLevels();
                System.out.print("\nHP: " + healthRange + "\nTracked Level: " + trackedRange);
                System.out.print("\nHelth Value: " + fuzzyHealth.getValue() + "\nTracked Value: " + fuzzyTracked.getValue());
                defuzzificationCounter = 0;
            }
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public void changeCorner() {
        ahead(5000);
        turnLeft(90);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public void onScannedRobot(ScannedRobotEvent e) {
        // Replace the next line with any behavior you would like
        smartFire(e.getDistance());
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public void smartFire(double robotDistance) {
        if (robotDistance > 200 || getEnergy() < 15) {
            fire(1);
            //fuzzyHealth.decrementValue(0.01);
        } else if (robotDistance > 50) {
            fire(2);
            //fuzzyHealth.decrementValue(0.02);
        } else {
            fire(3);
            //fuzzyHealth.decrementValue(0.03);
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public void onHitByBullet(HitByBulletEvent e) {
        // Replace the next line with any behavior you would like
        healthRange = fuzzyHealth.defuzzificationOfHealthLevels();

        if(trackedRange == TrackedLevels.NOTTRACKED) {
            fuzzyTracked.incrementValue(0.4);
            trackedRange = fuzzyTracked.defuzzificationOfTrackedLevels();

            if(healthRange == HealthLevels.CRITICAL) {
                changeCorner();
            }
        }

        else if(trackedRange == TrackedLevels.RATHERNOTTRACKED) {
            fuzzyTracked.incrementValue(0.4);
            trackedRange = fuzzyTracked.defuzzificationOfTrackedLevels();

            if(healthRange != HealthLevels.HEALTHY) {
                changeCorner();
            }
        }

        else if(trackedRange == TrackedLevels.RATHERTRACKED) {
            fuzzyTracked.incrementValue(0.4);
            trackedRange = fuzzyTracked.defuzzificationOfTrackedLevels();

            changeCorner();
        }

        else {
            fuzzyTracked.incrementValue(0.4);
            trackedRange = fuzzyTracked.defuzzificationOfTrackedLevels();

            changeCorner();
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public void onHitWall(HitWallEvent e) {
        // Replace the next line with any behavior you would like
        //back(20);
    }
}
