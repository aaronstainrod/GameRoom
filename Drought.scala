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
import scala.util.Random;


//Programmer's Notes:
class Drought extends Game {
/*Main*************************************************************************************************/
	override def setup {
		//Background
		generateBackground
		generateStage
		generateCharacter
		generateUI
		generateLining
		generateHUD
		generateCharacter

		startGameImmediately
	}

	override def advance {
		moveCharacter
		timer
		collision
		progression
	}
/*Data Values******************************************************************************************/
	//character Values
	var character: ImageSprite = new ImageSprite("character.gif")
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
	var drop: ImageSprite = new ImageSprite("drop.png")
	var drop2: ImageSprite = new ImageSprite("drop.png")
	var drop3: ImageSprite = new ImageSprite("drop.png")
	var drop4: ImageSprite = new ImageSprite("drop.png")
	var drop5: ImageSprite = new ImageSprite("drop.png")
	var drop6: ImageSprite = new ImageSprite("drop.png")
	var drop7: ImageSprite = new ImageSprite("drop.png")
	var drop8: ImageSprite = new ImageSprite("drop.png")
	var drop9: ImageSprite = new ImageSprite("drop.png")
	var drop10: ImageSprite = new ImageSprite("drop.png")
	//TimeKeeper
	var timeKeeper: ImageSprite = new ImageSprite("drop.png")
/*Generation*******************************************************************************************/
	def generateCharacter {
		character.setScale(0.05)
		character.setLocation(shipLoc._1, shipLoc._2)
		addSprite(character)
	}

	def generateDropX(drop: ImageSprite, x: Double, y: Double) {
		drop.setScale(0.05)
		drop.setLocation(x, y)
		addSprite(drop)
	}

	def generateDropY(drop: ImageSprite, x: Double, y: Double) {
		drop.setScale(0.05)
		drop.setLocation(x, y)
		addSprite(drop)
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
	def moveCharacter {
		if (leftPressed) {
			character.translateX(-shipSpd)
			if (character.getX < 0.05) {
				character.setX(0.05)
			}
		}
		if (rightPressed) {
			character.translateX(shipSpd)
			if (character.getX > 0.95) {
				character.setX(0.95)
			}
		}
	}

	def moveDropY (drop: ImageSprite, speed: Double) {
		drop.translateY(speed)
		if (drop.getY > 1.10) {
			drop.setY(-0.1)
		}
	}

	def moveDropX (drop: ImageSprite, speed: Double) {
		drop.translateX(speed)
		if (drop.getX > 1.10) {
			drop.setX(-0.1)
		}
	}

	def moveDropXY (drop: ImageSprite, x: Double, y: Double) {
		drop.translateX(x)
		drop.translateY(y)
		if (drop.getX > 1.10) {
			drop.setX(-0.1)
		}
		if (drop.getY > 1.10) {
			drop.setY(-0.1)
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
		moveDropX(timeKeeper,0.04)
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
				moveDropY(drop,0.05)
				moveDropY(drop10,0.05)
			}
				moveDropY(drop2,0.05)
				moveDropY(drop9,0.05)
			if (time > 2) {
			}
			if (time > 4) {
				moveDropY(drop3,0.05)
				moveDropY(drop8,0.05)
			}
			if (time > 6) {
				moveDropY(drop4,0.05)
				moveDropY(drop7,0.05)
			}
			if (time > 8) {
				moveDropY(drop5,0.05)
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
			drop.setLocation(2,2)
			drop2.setLocation(2,2)
			drop3.setLocation(2,2)
			drop4.setLocation(2,2)
			drop5.setLocation(2,2)
			drop6.setLocation(2,2)
			drop7.setLocation(2,2)
			drop8.setLocation(2,2)
			drop9.setLocation(2,2)
			drop10.setLocation(2,2)
			ClockString.setText("Score: " + String.valueOf(time))
			ContinueString.setLocation(0.50,0.50)
			moveOptionsY(YesString,0.01)
			moveOptionsY(NoString,0.01)
		}
		if (dead && character.intersects(YesString)) {
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
		if (dead && character.intersects(NoString)) {
			sys.exit(0)
		}
		if (complete){
			timeKeeper.setLocation(2,2)
			drop.setLocation(2,2)
			drop2.setLocation(2,2)
			drop3.setLocation(2,2)
			drop4.setLocation(2,2)
			drop5.setLocation(2,2)
			drop6.setLocation(2,2)
			drop7.setLocation(2,2)
			drop8.setLocation(2,2)
			drop9.setLocation(2,2)
			drop10.setLocation(2,2)

			ClockString.setText("Excellent")
			ContinueString.setLocation(0.50,0.50)
			moveOptionsY(YesString,0.01)
			moveOptionsY(NoString,0.01)
		}
		if (complete && character.intersects(YesString)) {
			complete = false
			training = true
			time = 0
			YesString.setLocation(2,2)
			NoString.setLocation(2,2)
			TutorialString.setLocation(2,2)
			setup
			advance
		}
		if (complete && character.intersects(NoString)) {
			sys.exit(0)
		}
		if (character.intersects(drop)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				drop.setX(newPlace)
				System.out.println("drop 1: " + drop.getX + ", " + drop.getY)
		}
		if (character.intersects(drop2)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				drop2.setX(newPlace2)
				System.out.println("drop 2: " + drop2.getX + ", " + drop2.getY)
		}
		if (character.intersects(drop3)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				drop3.setX(newPlace3)
				System.out.println("drop 3: " + drop3.getX + ", " + drop3.getY)
		}
		if (character.intersects(drop4)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				drop4.setX(newPlace4)
				System.out.println("drop 4: " + drop4.getX + ", " + drop4.getY)
		}
		if (character.intersects(drop5)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				drop5.setX(newPlace5)
				System.out.println("drop 5: " + drop5.getX + ", " + drop5.getY)
		}
		if (character.intersects(drop6)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				drop6.setX(newPlace6)
				System.out.println("drop 6: " + drop6.getX + ", " + drop6.getY)
		}
		if (character.intersects(drop7)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				drop7.setX(newPlace7)
				System.out.println("drop 7: " + drop7.getX + ", " + drop7.getY)
		}
		if (character.intersects(drop8)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				drop8.setX(newPlace8)
				System.out.println("drop 8: " + drop8.getX + ", " + drop8.getY)
		}
		if (character.intersects(drop9)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				drop9.setX(newPlace9)
				System.out.println("drop 9: " + drop9.getX + ", " + drop9.getY)
		}
		if (character.intersects(drop10)) {
				score += 1
				ScoreString.setText(String.valueOf("Score: " + score))
				drop10.setX(newPlace10)
				System.out.println("drop 10: " + drop5.getX + ", " + drop5.getY)
		}
	}
/*character Reset*******************************************************************************************/
	def shipReset {
		character.setLocation(shipLoc._1,0.95)
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
		generateDropX(timeKeeper,0.0,-2.0)

		generateDropY(drop,0.10,-0.75)
		generateDropY(drop2,0.20,-0.45)
		generateDropY(drop3,0.30,-0.95)
		generateDropY(drop4,0.40,-0.25)
		generateDropY(drop5,0.50,-0.05)
		generateDropY(drop6,0.60,-0.35)
		generateDropY(drop7,0.70,-0.85)
		generateDropY(drop8,0.80,-0.05)
		generateDropY(drop9,0.90,-0.65)
		generateDropY(drop10,0.95,-0.15)

	}
}