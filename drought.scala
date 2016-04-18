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
import scala.util.Random;


//Programmer's Notes:
class drought extends Game {
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
	var ship: ImageSprite = new ImageSprite("ship.gif")
	var shipLoc = (0.50,0.95)
	var shipSpd = 0.05
	//Screens
	var tutorial: Boolean = true
	var training: Boolean = false
	var dead: Boolean = false
	var complete = false
	//Random
	var generator: Random = new Random
	var generator2: Random = new Random
	var generator3: Random = new Random
	var generator4: Random = new Random
	var generator5: Random = new Random
	var generator6: Random = new Random
	var generator7: Random = new Random
	var generator8: Random = new Random
	var generator9: Random = new Random
	var generator10: Random = new Random
	var newPlace: Double = math.round(generator.nextInt(20)) * 0.05
	var newPlace2: Double = math.round(generator2.nextInt(20)) * 0.05
	var newPlace3: Double = math.round(generator3.nextInt(20)) * 0.05
	var newPlace4: Double = math.round(generator4.nextInt(20)) * 0.05
	var newPlace5: Double = math.round(generator5.nextInt(20)) * 0.05
	var newPlace6: Double = math.round(generator6.nextInt(20)) * 0.05
	var newPlace7: Double = math.round(generator7.nextInt(20)) * 0.05
	var newPlace8: Double = math.round(generator8.nextInt(20)) * 0.05
	var newPlace9: Double = math.round(generator9.nextInt(20)) * 0.05
	var newPlace10: Double = math.round(generator10.nextInt(20)) * 0.05
	var ClockString: StringSprite = new StringSprite
	var time: Int = 0
	var score: Int = 0
	var ScoreString: StringSprite = new StringSprite("Score: ")
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
	//TimeKeeper
	var timeKeeper: ImageSprite = new ImageSprite("missile.png")
/*Generation*******************************************************************************************/
	def generateShip {
		ship.setScale(0.05)
		ship.setLocation(shipLoc._1, shipLoc._2)
		addSprite(ship)
	}

	def generateMissileX(missile: ImageSprite, x: Double, y: Double) {
		missile.setScale(0.05)
		missile.setLocation(x, y)
		addSprite(missile)
	}

	def generateMissileY(missile: ImageSprite, x: Double, y: Double) {
		missile.setScale(0.05)
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
		//Score
		ScoreString.setHeight(0.05)
		ScoreString.setColor(Color.black)
		ScoreString.setLocation(0.125, 0.05)
		addSprite(ScoreString)
	}

	def generatePointers {
		//Directions
		right.setScale(0.025)
		right.setColor(Color.red)
		right.setRotationDegrees(90)
		right.setLocation(0.525,0.325)
		addSprite(right)

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

	def moveMissileX (missile: ImageSprite, speed: Double) {
		missile.translateX(speed)
		if (missile.getX > 1.10) {
			missile.setX(-0.1)
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
				moveMissileY(missile2,0.05)
				moveMissileY(missile9,0.05)
			if (time > 2) {
			}
			if (time > 4) {
				moveMissileY(missile3,0.05)
				moveMissileY(missile8,0.05)
			}
			if (time > 6) {
				moveMissileY(missile4,0.05)
				moveMissileY(missile7,0.05)
			}
			if (time > 8) {
				moveMissileY(missile5,0.05)
			}
			if (time > 30 && score > 30) {
				training = false
				complete = true
				shipReset
			}	
		}
		if (complete) {
			TutorialString.setText("You're prepared for the drought!")
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
			ClockString.setText("Score: " + String.valueOf(time))
			ContinueString.setLocation(0.50,0.50)
			moveOptionsY(YesString,0.01)
			moveOptionsY(NoString,0.01)
		}
		if (dead && ship.intersects(YesString)) {
			dead = false
			training = true
			time = 0
			score = 0
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
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				missile.setX(newPlace)
				System.out.println("Missile 1: " + missile.getX + ", " + missile.getY)
		}
		if (ship.intersects(missile2)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				missile2.setX(newPlace2)
				System.out.println("Missile 2: " + missile2.getX + ", " + missile2.getY)
		}
		if (ship.intersects(missile3)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				missile3.setX(newPlace3)
				System.out.println("Missile 3: " + missile3.getX + ", " + missile3.getY)
		}
		if (ship.intersects(missile4)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				missile4.setX(newPlace4)
				System.out.println("Missile 4: " + missile4.getX + ", " + missile4.getY)
		}
		if (ship.intersects(missile5)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				missile5.setX(newPlace5)
				System.out.println("Missile 5: " + missile5.getX + ", " + missile5.getY)
		}
		if (ship.intersects(missile6)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				missile6.setX(newPlace6)
				System.out.println("Missile 6: " + missile6.getX + ", " + missile6.getY)
		}
		if (ship.intersects(missile7)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				missile7.setX(newPlace7)
				System.out.println("Missile 7: " + missile7.getX + ", " + missile7.getY)
		}
		if (ship.intersects(missile8)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				missile8.setX(newPlace8)
				System.out.println("Missile 8: " + missile8.getX + ", " + missile8.getY)
		}
		if (ship.intersects(missile9)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				missile9.setX(newPlace9)
				System.out.println("Missile 9: " + missile9.getX + ", " + missile9.getY)
		}
		if (ship.intersects(missile10)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				missile10.setX(newPlace10)
				System.out.println("Missile 10: " + missile5.getX + ", " + missile5.getY)
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
				TutorialString.setText("Chief, it is about to rain!")
			}
			if (time > 2) {
				TutorialString.setText("We must collect the rain water!")
			}
			if (time > 4) {
				generatePointers
				TutorialString.setText("Chief, use the arrow keys to collect rain!")			
			}
			if (time > 6) {
				TutorialString.setText("We must hydrate the livestock")			
			}
			if (time > 8) {
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
		}
	}
/*Stage************************************************************************************************/
	def generateStage {
		generateMissileX(timeKeeper,0.0,-2.0)

		generateMissileY(missile,0.10,-0.75)
		generateMissileY(missile2,0.20,-0.45)
		generateMissileY(missile3,0.30,-0.95)
		generateMissileY(missile4,0.40,-0.25)
		generateMissileY(missile5,0.50,-0.05)
		generateMissileY(missile6,0.60,-0.35)
		generateMissileY(missile7,0.70,-0.85)
		generateMissileY(missile8,0.80,-0.05)
		generateMissileY(missile9,0.90,-0.65)
		generateMissileY(missile10,0.95,-0.15)

	}
/*Score Keeper******************************************************************************************/
/*Data Values******************************************************************************************/
/*Data Values******************************************************************************************/
/*Data Values******************************************************************************************/
/*Data Values******************************************************************************************/