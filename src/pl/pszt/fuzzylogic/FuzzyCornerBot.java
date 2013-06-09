package pl.pszt.fuzzylogic;

import java.awt.*;
//package vis;
import robocode.*;
import java.awt.Color;

import static robocode.util.Utils.normalRelativeAngleDegrees;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * FuzzyCornerBot - a robot by (your name here)
 */
public class FuzzyCornerBot extends Robot
{
    /**
     * run: FuzzyCornerBot's default behavior
     */
    FuzzyStatement fuzzyHealth;
    HealthLevels healthRange;
    FuzzyStatement fuzzyTracked;
    TrackedLevels trackedRange;
    final int defuzzificationDelay = 2;
    int gunIncrement = 3;

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

    double distance(double pointAX, double pointAY, double pointBX, double pointBY) {
        return Math.sqrt((pointBX-pointAX)*(pointBX-pointAX) + (pointBY-pointAY)*(pointBY-pointAY));
    }

    public void changeCorner() {
        ahead(5000);
        turnLeft(90);
    }

    /* To jest CRAP. Nie będzie używane. Zostawiłem na wszelki wypadek
	void goToClosestCorner() {
        double mapWidth = getWidth();
        double mapHeight = getHeight();

        double corner1[] = {0,0};
        double corner2[] = {0, mapHeight};
        double corner3[] = {mapWidth, mapHeight};
        double corner4[] = {mapWidth, 0};

        double myX = getX();
        double myY = getY();
        int closestCorner = 1;
        double smallestDistance = distance(myX, myY, corner1[0], corner1[1]);

        double corner2Distance = distance(myX, myY, corner2[0], corner2[1]);
        if(corner2Distance < smallestDistance) {
            smallestDistance = corner2Distance;
            closestCorner = 2;
        }

        double corner3Distance = distance(myX, myY, corner3[0], corner3[1]);
        if(corner2Distance < smallestDistance) {
            smallestDistance = corner3Distance;
            closestCorner = 3;
        }

        double corner4Distance = distance(myX, myY, corner4[0], corner4[1]);
        if(corner2Distance < smallestDistance) {
            smallestDistance = corner4Distance;
            closestCorner = 4;
        }


    }
    */

    //public void

    public void onScannedRobot(ScannedRobotEvent e) {
        // Replace the next line with any behavior you would like
        smartFire(e.getDistance());
    }

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

    /**
     * onHitByBullet: What to do when you're hit by a bullet
     */
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

    /**
     * onHitWall: What to do when you hit a wall
     */
    public void onHitWall(HitWallEvent e) {
        // Replace the next line with any behavior you would like
        //back(20);
    }
}
