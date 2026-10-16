package PastaJuizoFinal;
import robocode.AdvancedRobot;
import robocode.*;
import java.awt.Color;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * JuizoFinal - a robot by (your name here)
 */
public class JuizoFinal extends AdvancedRobot
{
	/**
	 * run: JuizoFinal's default behavior
	 */
	public void run() {
	// aqui to só definindo as cores do robô
	setGunColor(new Color(240, 208, 221));
	setBodyColor(new Color(240, 208, 221));
	setRadarColor(new Color(240, 208, 221));
	
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(100);
			turnGunRight(360);
			back(100);
			turnGunRight(360);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(1);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}	
}
