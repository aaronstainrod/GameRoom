import fang2.attributes._
import fang2.core._
import fang2.media._
import fang2.network._
import fang2.sprites._
import fang2.transformers._
import fang2.ui._
import fang2.util._

import java.awt.Color
import java.net.URL
import javax.sound.sampled._

//Programmer's Notes:
class Cadet extends Game {
/*Main*************************************************************************************************/
	override def setup {
		//Background
		generateBackground
		generateStage
		generateShip
		generateUI
		generateLining
		generateHUD
		generateShip

		startGameImmediately
	}

	override def advance {
		moveShip
		timer
		collision
		progression
	}
/*Data Values******************************************************************************************/
	//Ship Values
	var ship: ImageSprite = new ImageSprite("ship.png")
	var shipLoc = (0.50,0.50)
	var shipSpd = 0.05
	//Screens
	var tutorial: Boolean = true
	var training: Boolean = false
	var dead: Boolean = false
	var complete = false
	//Player HUD
	var ClockString: StringSprite = new StringSprite
	var time: Int = 0
	var TutorialString: StringSprite = new StringSprite
	//Pointers
	var up: PolygonSprite = new PolygonSprite(3)
	var right: PolygonSprite = new PolygonSprite(3)
	var down: PolygonSprite = new PolygonSprite(3)
	var left: PolygonSprite = new PolygonSprite(3)
	//Player UI
	var ContinueString: StringSprite = new StringSprite("Continue?")
	var YesString: StringSprite = new StringSprite("Yes")
	var NoString: StringSprite = new StringSprite("No")
	//Background
	var space: ImageSprite = new ImageSprite("space.jpg")
	//HUD Background
	var lining: RectangleSprite = new RectangleSprite(1,0.10)
	//Obstacles
	var missile: ImageSprite = new ImageSprite("missile.png")
	var missile2: ImageSprite = new ImageSprite("missile.png")
	var missile3: ImageSprite = new ImageSprite("missile.png")
	var missile4: ImageSprite = new ImageSprite("missile.png")
	var missile5: ImageSprite = new ImageSprite("missile.png")
	var missile6: ImageSprite = new ImageSprite("missile.png")
	var missile7: ImageSprite = new ImageSprite("missile.png")
	var missile8: ImageSprite = new ImageSprite("missile.png")
	var missile9: ImageSprite = new ImageSprite("missile.png")
	var missile10: ImageSprite = new ImageSprite("missile.png")
	var missile11: ImageSprite = new ImageSprite("missile.png")
	var missile12: ImageSprite = new ImageSprite("missile.png")
	var missile13: ImageSprite = new ImageSprite("missile.png")
	var missile14: ImageSprite = new ImageSprite("missile.png")
	var missile15: ImageSprite = new ImageSprite("missile.png")
	var missile16: ImageSprite = new ImageSprite("missile.png")
	var missile17: ImageSprite = new ImageSprite("missile.png")
	var missile18: ImageSprite = new ImageSprite("missile.png")
	var missile19: ImageSprite = new ImageSprite("missile.png")
	var missile20: ImageSprite = new ImageSprite("missile.png")
	var missile21: ImageSprite = new ImageSprite("missile.png")
	var missile22: ImageSprite = new ImageSprite("missile.png")
	var missile23: ImageSprite = new ImageSprite("missile.png")
	var missile24: ImageSprite = new ImageSprite("missile.png")
	var missile25: ImageSprite = new ImageSprite("missile.png")
	var missile26: ImageSprite = new ImageSprite("missile.png")
	var missile27: ImageSprite = new ImageSprite("missile.png")
	var missile28: ImageSprite = new ImageSprite("missile.png")
	var missile29: ImageSprite = new ImageSprite("missile.png")
	var missile30: ImageSprite = new ImageSprite("missile.png")
	var missile31: ImageSprite = new ImageSprite("missile.png")
	var missile32: ImageSprite = new ImageSprite("missile.png")
	var missile33: ImageSprite = new ImageSprite("missile.png")
	var missile34: ImageSprite = new ImageSprite("missile.png")
	var missile35: ImageSprite = new ImageSprite("missile.png")
	var missile36: ImageSprite = new ImageSprite("missile.png")
	var missile37: ImageSprite = new ImageSprite("missile.png")
	var missile38: ImageSprite = new ImageSprite("missile.png")
	var missile39: ImageSprite = new ImageSprite("missile.png")
	var missile40: ImageSprite = new ImageSprite("missile.png")
	//TimeKeeper
	var timeKeeper: ImageSprite = new ImageSprite("missile.png")
/*Generation*******************************************************************************************/
	def generateShip {
		ship.setScale(0.05)
		ship.setLocation(shipLoc._1, shipLoc._2)
		addSprite(ship)
	}

	def generateMissileY(missile: ImageSprite, x: Double, y: Double) {
		missile.setScale(0.05)
		missile.setRotationDegrees(315.5)
		missile.setLocation(x, y)
		addSprite(missile)
	}

	def generateMissileYReverse(missile: ImageSprite, x: Double, y: Double) {
		missile.setScale(0.05)
		missile.setRotationDegrees(135.5)
		missile.setLocation(x, y)
		addSprite(missile)
	}

	def generateMissileX(missile: ImageSprite, x: Double, y: Double) {
		missile.setScale(0.05)
		missile.setRotationDegrees(225.5)
		missile.setLocation(x, y)
		addSprite(missile)
	}

	def generateMissileXReverse(missile: ImageSprite, x: Double, y: Double) {
		missile.setScale(0.05)
		missile.setRotationDegrees(45.5)
		missile.setLocation(x, y)
		addSprite(missile)
	}

	def generateMissile(missile: ImageSprite, rotation: Double, x: Double, y: Double) {
		missile.setScale(0.05)
		missile.setRotationDegrees(rotation)
		missile.setLocation(x, y)
		addSprite(missile)
	}

	def generateUI {
		//Continue?
		ContinueString.setHeight(0.05)
		ContinueString.setColor(Color.white)
		ContinueString.setLocation(0.50,1.10)
		addSprite(ContinueString)
		//Yes
		YesString.setHeight(0.05)
		YesString.setColor(Color.green)
		YesString.setLocation(0.25,1.10)
		addSprite(YesString)
		//No
		NoString.setHeight(0.05)
		NoString.setColor(Color.red)
		NoString.setLocation(0.75,1.10)
		addSprite(NoString)
	}

	def generateHUD {
		//Clock
		ClockString.setHeight(0.05)
		ClockString.setColor(Color.black)
		ClockString.setLocation(0.50,0.05)
		addSprite(ClockString)
		//Tutorial
		TutorialString.setHeight(0.05)
		TutorialString.setColor(Color.white)
		TutorialString.setLocation(0.50,0.20)
		addSprite(TutorialString)
	}

	def generatePointers {
		//Directions
		up.setScale(0.025)
		up.setColor(Color.red)
		up.setLocation(0.50,0.30)
		addSprite(up)

		right.setScale(0.025)
		right.setColor(Color.red)
		right.setRotationDegrees(90)
		right.setLocation(0.525,0.325)
		addSprite(right)

		down.setScale(0.025)
		down.setColor(Color.red)
		down.setRotationDegrees(180)
		down.setLocation(0.50,0.35)
		addSprite(down)

		left.setScale(0.025)
		left.setColor(Color.red)
		left.setRotationDegrees(-90)
		left.setLocation(0.475,0.325)
		addSprite(left)
	}

	def generateBackground {
		//Background
		space.setLocation(0.50,0.50)
		space.setScale(2)
		addSprite(space)
	}

	def generateLining {
		//White Bar
		lining.setLocation(0.50,0.04)
		lining.setColor(Color.white)
		addSprite(lining)
	}
/*Movement*********************************************************************************************/
	def moveShip {
		if (upPressed) {
			ship.translateY(-shipSpd)
			if (ship.getY < 0.05) {
				ship.setY(0.05)
			}
		}
		if (downPressed) {
			ship.translateY(shipSpd)
			if (ship.getY > 0.95) {
				ship.setY(0.95)
			}
		}
		if (leftPressed) {
			ship.translateX(-shipSpd)
			if (ship.getX < 0.05) {
				ship.setX(0.05)
			}
		}
		if (rightPressed) {
			ship.translateX(shipSpd)
			if (ship.getX > 0.95) {
				ship.setX(0.95)
			}
		}
	}

	def moveMissileY (missile: ImageSprite, speed: Double) {
		missile.translateY(speed)
		if (missile.getY > 1.10) {
			missile.setY(-0.1)
		}
	}

	def moveMissileYReverse (missile: ImageSprite, speed: Double) {
		missile.translateY(-speed)
		if (missile.getY < -0.10) {
			missile.setY(1.1)
		}
	}

	def moveMissileX (missile: ImageSprite, speed: Double) {
		missile.translateX(speed)
		if (missile.getX > 1.10) {
			missile.setX(-0.1)
		}
	}

	def moveMissileXReverse (missile: ImageSprite, speed: Double) {
		missile.translateX(-speed)
		if (missile.getX < -0.10) {
			missile.setX(1.1)
		}
	}

	def moveMissileReverseX (missile: ImageSprite, x: Double, y: Double) {
		missile.translateX(-x)
		missile.translateY(y)
		if (missile.getX < -0.10) {
			missile.setX(1.1)
		}
		if (missile.getY > 1.10) {
			missile.setY(-0.1)
		}
	}

	def moveMissileXY (missile: ImageSprite, x: Double, y: Double) {
		missile.translateX(x)
		missile.translateY(y)
		if (missile.getX > 1.10) {
			missile.setX(-0.1)
		}
		if (missile.getY > 1.10) {
			missile.setY(-0.1)
		}
	}

	def moveOptionsY (option: StringSprite, y: Double) {
		option.translateY(y)
		if (option.getY > 1.10) {
			option.setY(-0.1)
		}
	}
/*Timer************************************************************************************************/
	def timer {
		moveMissileX(timeKeeper,0.04)
		if (timeKeeper.getX > 1.05) {
			time += 1
			ClockString.setText(String.valueOf(time))
		}
	}
/*Progression******************************************************************************************/
	def progression {
		if (tutorial) {
			introduction
		}
		if (training) {
			removeSprite(TutorialString)
			//Vertical Array
			if (time > 0) {
				moveMissileY(missile,0.05)
				moveMissileY(missile10,0.05)
			}
			if (time > 2) {
				moveMissileY(missile2,0.04)
				moveMissileY(missile9,0.04)
			}
			if (time > 4) {
				moveMissileY(missile3,0.03)
				moveMissileY(missile8,0.03)
			}
			if (time > 6) {
				moveMissileY(missile4,0.02)
				moveMissileY(missile7,0.02)
			}
			if (time > 8) {
				moveMissileY(missile5,0.01)
			}
			if (time > 10) {
				moveMissileX(missile11,0.01)
				moveMissileX(missile20,0.01)
			}
			if (time > 12) {
				moveMissileX(missile12,0.02)
				moveMissileX(missile19,0.02)
			}
			if (time > 14) {
				moveMissileX(missile13,0.03)
				moveMissileX(missile18,0.03)
			}
			if (time > 16) {
				moveMissileX(missile14,0.04)
				moveMissileX(missile17,0.04)
			}
			if (time > 18) {
				moveMissileX(missile15,0.01)
			}
			if (time > 20) {
				moveMissileYReverse(missile21,0.05)
				moveMissileYReverse(missile30,0.05)
			}
			if (time > 22) {
				moveMissileYReverse(missile22,0.04)
				moveMissileYReverse(missile29,0.04)
			}
			if (time > 24) {
				moveMissileYReverse(missile23,0.03)
				moveMissileYReverse(missile28,0.03)
			}
			if (time > 26) {
				moveMissileYReverse(missile24,0.02)
				moveMissileYReverse(missile27,0.02)
			}
			if (time > 28) {
				moveMissileYReverse(missile25,0.01)
			}
			if (time > 30) {
				training = false
				complete = true
				shipReset
			}	
		}
		if (complete) {
			TutorialString.setText("Congratulations!")
			moveOptionsY(YesString,0.01)
			moveOptionsY(NoString,0.01)
		}
	}
/*Collision********************************************************************************************/
	def collision {
		if (dead){
			timeKeeper.setLocation(2,2)
			missile.setLocation(2,2)
			missile2.setLocation(2,2)
			missile3.setLocation(2,2)
			missile4.setLocation(2,2)
			missile5.setLocation(2,2)
			missile6.setLocation(2,2)
			missile7.setLocation(2,2)
			missile8.setLocation(2,2)
			missile9.setLocation(2,2)
			missile10.setLocation(2,2)
			missile11.setLocation(2,2)
			missile12.setLocation(2,2)
			missile13.setLocation(2,2)
			missile14.setLocation(2,2)
			missile15.setLocation(2,2)
			missile16.setLocation(2,2)
			missile17.setLocation(2,2)
			missile18.setLocation(2,2)
			missile19.setLocation(2,2)
			missile20.setLocation(2,2)
			missile20.setLocation(2,2)
			missile21.setLocation(2,2)
			missile22.setLocation(2,2)
			missile23.setLocation(2,2)
			missile24.setLocation(2,2)
			missile25.setLocation(2,2)
			missile26.setLocation(2,2)
			missile27.setLocation(2,2)
			missile28.setLocation(2,2)
			missile29.setLocation(2,2)
			missile30.setLocation(2,2)
			missile31.setLocation(2,2)
			missile32.setLocation(2,2)
			missile33.setLocation(2,2)
			missile34.setLocation(2,2)
			missile35.setLocation(2,2)
			missile36.setLocation(2,2)
			missile37.setLocation(2,2)
			missile38.setLocation(2,2)
			missile39.setLocation(2,2)
			missile40.setLocation(2,2)
			ClockString.setText("Score: " + String.valueOf(time))
			ContinueString.setLocation(0.50,0.50)
			moveOptionsY(YesString,0.01)
			moveOptionsY(NoString,0.01)
		}
		if (dead && ship.intersects(YesString)) {
			dead = false
			training = true
			time = 0
			YesString.setLocation(2,2)
			NoString.setLocation(2,2)
			TutorialString.setLocation(2,2)
			setup
			advance
		}
		if (dead && ship.intersects(NoString)) {
			sys.exit(0)
		}
		if (complete){
			timeKeeper.setLocation(2,2)
			missile.setLocation(2,2)
			missile2.setLocation(2,2)
			missile3.setLocation(2,2)
			missile4.setLocation(2,2)
			missile5.setLocation(2,2)
			missile6.setLocation(2,2)
			missile7.setLocation(2,2)
			missile8.setLocation(2,2)
			missile9.setLocation(2,2)
			missile10.setLocation(2,2)
			missile11.setLocation(2,2)
			missile12.setLocation(2,2)
			missile13.setLocation(2,2)
			missile14.setLocation(2,2)
			missile15.setLocation(2,2)
			missile16.setLocation(2,2)
			missile17.setLocation(2,2)
			missile18.setLocation(2,2)
			missile19.setLocation(2,2)
			missile20.setLocation(2,2)
			missile20.setLocation(2,2)
			missile21.setLocation(2,2)
			missile22.setLocation(2,2)
			missile23.setLocation(2,2)
			missile24.setLocation(2,2)
			missile25.setLocation(2,2)
			missile26.setLocation(2,2)
			missile27.setLocation(2,2)
			missile28.setLocation(2,2)
			missile29.setLocation(2,2)
			missile30.setLocation(2,2)
			missile31.setLocation(2,2)
			missile32.setLocation(2,2)
			missile33.setLocation(2,2)
			missile34.setLocation(2,2)
			missile35.setLocation(2,2)
			missile36.setLocation(2,2)
			missile37.setLocation(2,2)
			missile38.setLocation(2,2)
			missile39.setLocation(2,2)
			missile40.setLocation(2,2)
			ClockString.setText("Excellent")
			ContinueString.setLocation(0.50,0.50)
			moveOptionsY(YesString,0.01)
			moveOptionsY(NoString,0.01)
		}
		if (complete && ship.intersects(YesString)) {
			complete = false
			training = true
			time = 0
			YesString.setLocation(2,2)
			NoString.setLocation(2,2)
			TutorialString.setLocation(2,2)
			setup
			advance
		}
		if (complete && ship.intersects(NoString)) {
			sys.exit(0)
		}
		if (ship.intersects(missile)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile2)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile3)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile4)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile5)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile6)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile7)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile8)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile9)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile10)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile11)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile12)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile13)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile14)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile15)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile16)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile17)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile18)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile19)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile20)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile21)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile22)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile23)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile24)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile25)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile26)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile27)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile28)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile29)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile30)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile31)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile32)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile33)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile34)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile35)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile36)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile37)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile38)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile39)) {
				training = false
				dead = true
				shipReset
		}
		if (ship.intersects(missile40)) {
				training = false
				dead = true
				shipReset
		}
	}
/*Ship Reset*******************************************************************************************/
	def shipReset {
		ship.setLocation(shipLoc._1,0.95)
	}
/*Introduction*****************************************************************************************/
	def introduction {
		if (tutorial) {
			ClockString.setText("Tutorial")
			if (time > 0) {
				TutorialString.setText("Welcome to training, cadet")
			}
			if (time > 2) {
				TutorialString.setText("Let's train your mind and reflexes")
			}
			if (time > 4) {
				generatePointers
				TutorialString.setText("Use your arrow keys to move around")			
			}
			if (time > 6) {
				TutorialString.setText("We'll train your reflexes first")			
			}
			if (time > 8) {
				TutorialString.setText("Dodge the missiles for 30 seconds")
			}
			if (time > 10) {
				TutorialString.setText("Ready?")
			}
			if (time > 12) {
				tutorial = false
				training = true
				time = 0
				ClockString.setText("0")
				removeSprite(TutorialString)
				removeSprite(up)
				removeSprite(right)
				removeSprite(down)
				removeSprite(left)
			}
			if (time > 31) {
				TutorialString.setText("Good job, cadet")
			}
		}
	}
/*Stage************************************************************************************************/
	def generateStage {
		generateMissileX(timeKeeper,0.0,-2.0)

		generateMissileY(missile,0.10,-0.05)
		generateMissileY(missile2,0.20,-0.05)
		generateMissileY(missile3,0.30,-0.05)
		generateMissileY(missile4,0.40,-0.05)
		generateMissileY(missile5,0.50,-0.05)
		generateMissileY(missile6,0.60,-0.05)
		generateMissileY(missile7,0.70,-0.05)
		generateMissileY(missile8,0.80,-0.05)
		generateMissileY(missile9,0.90,-0.05)
		generateMissileY(missile10,0.95,-0.05)

		generateMissileX(missile11,-0.05,0.10)
		generateMissileX(missile12,-0.05,0.20)
		generateMissileX(missile13,-0.05,0.30)
		generateMissileX(missile14,-0.05,0.40)
		generateMissileX(missile15,-0.05,0.50)
		generateMissileX(missile16,-0.05,0.60)
		generateMissileX(missile17,-0.05,0.70)
		generateMissileX(missile18,-0.05,0.80)
		generateMissileX(missile19,-0.05,0.90)
		generateMissileX(missile20,-0.05,0.95)

		generateMissileYReverse(missile21,0.15,1.05)
		generateMissileYReverse(missile22,0.25,1.05)
		generateMissileYReverse(missile23,0.35,1.05)
		generateMissileYReverse(missile24,0.45,1.05)
		generateMissileYReverse(missile25,0.55,1.05)
		generateMissileYReverse(missile26,0.65,1.05)
		generateMissileYReverse(missile27,0.75,1.05)
		generateMissileYReverse(missile28,0.85,1.05)
		generateMissileYReverse(missile29,0.95,1.05)
		generateMissileYReverse(missile30,1.00,1.05)

		generateMissileXReverse(missile31,1.05,0.15)
		generateMissileXReverse(missile32,1.05,0.25)
		generateMissileXReverse(missile33,1.05,0.35)
		generateMissileXReverse(missile34,1.05,0.45)
		generateMissileXReverse(missile35,1.05,0.55)
		generateMissileXReverse(missile36,1.05,0.65)
		generateMissileXReverse(missile37,1.05,0.75)
		generateMissileXReverse(missile38,1.05,0.85)
		generateMissileXReverse(missile39,1.05,0.95)
		generateMissileXReverse(missile40,1.05,1.00)
	}
/*Data Values******************************************************************************************/
/*Data Values******************************************************************************************/
/*Data Values******************************************************************************************/
/*Data Values******************************************************************************************/
/*Data Values******************************************************************************************/
}