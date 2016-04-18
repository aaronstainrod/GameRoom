import fang2.attributes._
import fang2.core._
import fang2.media._
import fang2.network._
import fang2.sprites._
import fang2.transformers._
import fang2.ui._
import fang2.util._

import java.awt.Color
import scala.util.Random

//Programmer's Notes:
class Kindergarten extends Game {
/*Data Values******************************************************************************************/
	var ship: ImageSprite = new ImageSprite("ship.png")
	var shipLoc = (0.50,0.95)
	var shipSpd = 0.05

	var missile: ImageSprite = new ImageSprite("missile.png")
	var missile2: ImageSprite = new ImageSprite("missile.png")
	var missile3: ImageSprite = new ImageSprite("missile.png")
	var missile4: ImageSprite = new ImageSprite("missile.png")
	var missile5: ImageSprite = new ImageSprite("missile.png")

	var missilet1: ImageSprite = new ImageSprite("missile.png")
	var missilet2: ImageSprite = new ImageSprite("missile.png")

	var ProblemString: StringSprite = new StringSprite
	var TutorialString: StringSprite = new StringSprite
	var SkipString: StringSprite = new StringSprite("Skip")

	var ContinueString: StringSprite = new StringSprite("Continue?")
	var YesString: StringSprite = new StringSprite("Yes")
	var NoString: StringSprite = new StringSprite("No")

	var pointer: PolygonSprite = new PolygonSprite(3)
	var testProb: StringSprite = new StringSprite("1 + 1 = ?")
	var right: Boolean = false
	var wrong: Boolean = false

	var tutorial = true
	var level1 = false
	var level2 = false
	var level3 = false
	var level4 = false
	var level5 = false
	var complete = false
	var gameover = false

	//Background
	var space: ImageSprite = new ImageSprite("space.jpg")

	var AnswerString1: StringSprite = new StringSprite
	var AnswerString2: StringSprite = new StringSprite
	var AnswerString3: StringSprite = new StringSprite
	var AnswerString4: StringSprite = new StringSprite
	var AnswerString5: StringSprite = new StringSprite

	var t1: StringSprite = new StringSprite("1")
	var t2: StringSprite = new StringSprite("2")
	var t3: StringSprite = new StringSprite("3")
	var t4: StringSprite = new StringSprite("4")
	var t5: StringSprite = new StringSprite("5")

	//Text Sprites
	var LifeString: StringSprite = new StringSprite
	var lives:Int = 10

	var time: Int = 31
	var timeKeeper: ImageSprite = new ImageSprite("missile.png")
	var ClockString: StringSprite = new StringSprite("Ready?")
	var LevelString: StringSprite = new StringSprite

	var number1: Random = new Random
	var number2: Random = new Random

	var incorrect1: Random = new Random
	var incorrect2: Random = new Random
	var incorrect3: Random = new Random
	var incorrect4: Random = new Random 

	var wrong1 = incorrect1.nextInt(13)
	var wrong2 = incorrect2.nextInt(13)
	var wrong3 = incorrect3.nextInt(13)
	var wrong4 = incorrect4.nextInt(13)

	var add11 = number1.nextInt(10)
	var add12 = number2.nextInt(10)
	var sum1 = add11 + add12

	var add21 = number1.nextInt(10)
	var add22 = number2.nextInt(10)
	var sum2 = add21 + add22

	var add31 = number1.nextInt(10)
	var add32 = number2.nextInt(10)
	var sum3 = add31 + add32

	var add41 = number1.nextInt(10)
	var add42 = number2.nextInt(10)
	var sum4 = add41 + add42

	var add51 = number1.nextInt(10)
	var add52 = number2.nextInt(10)
	var sum5 = add51 + add52
/*Main Functions***************************************************************************************/
	override def setup {
		stage
		startGameImmediately
	}

	override def advance {
		moveShip
		collision
		timer

		level
		levelProgression
		norepeat
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

	def moveOptionsY (option: StringSprite, y: Double) {
		option.translateY(y)
		if (option.getY > 1.10) {
			option.setY(-0.1)
		}
	}
/*Generation*******************************************************************************************/
	def generateShip {
		ship.setScale(0.05)
		ship.setLocation(shipLoc._1, shipLoc._2)
		addSprite(ship)
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

	def generatePointer {
		testProb.setHeight(0.05)
		testProb.setLocation(0.50,0.10)
		testProb.setColor(Color.white)
		addSprite(testProb)

		pointer.setScale(0.025)
		pointer.setLocation(0.50,0.15)
		pointer.setColor(Color.red)
		addSprite(pointer)
	}

	def generateTutorial {
		t1.setHeight(0.05)
		t1.setLocation(0.30,0.50)
		t1.setColor(Color.white)
		addSprite(t1)

		t2.setHeight(0.05)
		t2.setLocation(0.40,0.50)
		t2.setColor(Color.white)
		addSprite(t2)

		t3.setHeight(0.05)
		t3.setLocation(0.50,0.50)
		t3.setColor(Color.white)
		addSprite(t3)

		t4.setHeight(0.05)
		t4.setLocation(0.60,0.50)
		t4.setColor(Color.white)
		addSprite(t4)

		t5.setHeight(0.05)
		t5.setLocation(0.70,0.50)
		t5.setColor(Color.white)
		addSprite(t5)
	}

	def generateAnswers {
		AnswerString1.setHeight(0.05)
		AnswerString1.setLocation(0.40,0.30)
		AnswerString1.setColor(Color.white)
		addSprite(AnswerString1)

		AnswerString2.setHeight(0.05)
		AnswerString2.setLocation(0.80,0.40)
		AnswerString2.setColor(Color.white)
		addSprite(AnswerString2)

		AnswerString3.setHeight(0.05)
		AnswerString3.setLocation(0.10,0.50)
		AnswerString3.setColor(Color.white)
		addSprite(AnswerString3)

		AnswerString4.setHeight(0.05)
		AnswerString4.setLocation(0.30,0.60)
		AnswerString4.setColor(Color.white)
		addSprite(AnswerString4)

		AnswerString5.setHeight(0.05)
		AnswerString5.setLocation(0.90,0.70)
		AnswerString5.setColor(Color.white)
		addSprite(AnswerString5)
	}

	def generateHUD {
		LifeString.setHeight(0.05)
		LifeString.setColor(Color.white)
		LifeString.setLocation(0.975,0.025)
		LifeString.topJustify()
		LifeString.rightJustify()
		LifeString.setText("Tries: " + lives)
		addSprite(LifeString)

		ProblemString.setHeight(0.075)
		ProblemString.setColor(Color.blue)
		ProblemString.topJustify()
		ProblemString.centerJustify()
		ProblemString.setLocation(0.50,0.125)
		addSprite(ProblemString)	

		LevelString.setHeight(0.05)
		LevelString.setColor(Color.blue)
		LevelString.topJustify()
		LevelString.centerJustify()
		LevelString.setLocation(0.50,0.05)
		addSprite(LevelString)

		ClockString.setHeight(0.05)
		ClockString.setColor(Color.magenta)
		ClockString.leftJustify()
		ClockString.topJustify()
		ClockString.setLocation(0.025,0.025)
		addSprite(ClockString)

		TutorialString.setHeight(0.05)
		TutorialString.setColor(Color.white)
		TutorialString.setLocation(0.50,0.20)
		addSprite(TutorialString)

		ContinueString.setHeight(0.05)
		ContinueString.setColor(Color.blue)
		ContinueString.setLocation(0.50,1.10)
		addSprite(ContinueString)

		YesString.setHeight(0.05)
		YesString.setColor(Color.green)
		YesString.setLocation(0.25,1.10)
		addSprite(YesString)

		NoString.setHeight(0.05)
		NoString.setColor(Color.red)
		NoString.setLocation(0.75,1.10)
		addSprite(NoString)

		SkipString.setHeight(0.04)
		SkipString.setColor(Color.white)
		SkipString.setLocation(0.925,0.95)
		addSprite(SkipString)
	}

	def generateBackground {
		space.setLocation(0.5,0.5)
		space.setScale(2)
		addSprite(space)
	}
/*Level Functions**************************************************************************************/
	def stage {
		generateMissileX(missile,-0.3,0.7)
		generateMissileXReverse(missile2,-0.3,0.6)
		generateMissileX(missile3,-0.3,0.5)
		generateMissileXReverse(missile4,-0.3,0.4)
		generateMissileX(missile5,-0.3,0.3)
		generateMissileX(missilet1,-0.3,0.6)
		generateMissileX(missilet2,-0.3,0.4)
		generateMissileX(timeKeeper,-0.0,-2.0)

		generateBackground
		generateShip
		generateHUD
		generateAnswers
	}

	def levelProgression {
		//Tutorial
		tutorialScreen
		//Level 1
		if (level1) {
			norepeat
			ProblemString.setText(add11 + " + " + add12 + " = ?")
			LevelString.setText("Level 1")
			AnswerString1.setText(String.valueOf(wrong1))
			AnswerString2.setText(String.valueOf(sum1))
			AnswerString3.setText(String.valueOf(wrong3))
			AnswerString4.setText(String.valueOf(wrong4))
			AnswerString5.setText(String.valueOf(wrong2))
			if (ship.intersects(AnswerString1) || ship.intersects(AnswerString3) || ship.intersects(AnswerString4) || ship.intersects(AnswerString5)) {
				shipHit
			}
			if (ship.intersects(AnswerString2)) {
				level1 = false
				level2 = true
				time = 31
				ship.setLocation(shipLoc._1, shipLoc._2)
			}
		}
		//Level 2
		if (level2) {
			norepeat
			ProblemString.setText(add21 + " + " + add22 + " = ?")
			LevelString.setText("Level 2")
			AnswerString1.setText(String.valueOf(wrong1))
			AnswerString2.setText(String.valueOf(wrong2))
			AnswerString3.setText(String.valueOf(wrong3))
			AnswerString4.setText(String.valueOf(sum2))
			AnswerString5.setText(String.valueOf(wrong4))
			if (ship.intersects(AnswerString1) || ship.intersects(AnswerString2) || ship.intersects(AnswerString3) || ship.intersects(AnswerString5)) {
				shipHit
			}
			if (ship.intersects(AnswerString4)) {
				level2 = false
				level3 = true
				time = 31
				ship.setLocation(shipLoc._1, shipLoc._2)
			}
		}
		//Level 3
		if (level3) {
			norepeat
			ProblemString.setText(add31 + " + " + add32 + " = ?")
			LevelString.setText("Level 3")
			AnswerString1.setText(String.valueOf(sum3))
			AnswerString2.setText(String.valueOf(wrong1))
			AnswerString3.setText(String.valueOf(wrong3))
			AnswerString4.setText(String.valueOf(wrong4))
			AnswerString5.setText(String.valueOf(wrong2))
			if (ship.intersects(AnswerString2) || ship.intersects(AnswerString3) || ship.intersects(AnswerString4) || ship.intersects(AnswerString5)) {
				shipHit
			}
			if (ship.intersects(AnswerString1)) {
				level3 = false
				level4 = true
				time = 31
				ship.setLocation(shipLoc._1, shipLoc._2)
			}
		}
		//Level 4
		if (level4) {
			norepeat
			ProblemString.setText(add41 + " + " + add42 + " = ?")
			LevelString.setText("Level 4")
			AnswerString1.setText(String.valueOf(wrong1))
			AnswerString2.setText(String.valueOf(wrong2))
			AnswerString3.setText(String.valueOf(sum4))
			AnswerString4.setText(String.valueOf(wrong3))
			AnswerString5.setText(String.valueOf(wrong4))
			if (ship.intersects(AnswerString1) || ship.intersects(AnswerString2) || ship.intersects(AnswerString4) || ship.intersects(AnswerString5)) {
				shipHit
			}
			if (ship.intersects(AnswerString3)) {
				level4 = false
				level5 = true
				time = 31
				ship.setLocation(shipLoc._1, shipLoc._2)
			}
		}
		//Level 5
		if (level5) {
			norepeat
			ProblemString.setText(add51 + " + " + add52 + " = ?")
			LevelString.setText("Level 5")
			AnswerString1.setText(String.valueOf(wrong1))
			AnswerString2.setText(String.valueOf(wrong2))
			AnswerString3.setText(String.valueOf(wrong3))
			AnswerString4.setText(String.valueOf(wrong4))
			AnswerString5.setText(String.valueOf(sum5))
			if (ship.intersects(AnswerString1) || ship.intersects(AnswerString2) || ship.intersects(AnswerString3) || ship.intersects(AnswerString4)) {
				shipHit
			}
			if (ship.intersects(AnswerString5)) {
				level5 = false
				complete = true
				ship.setLocation(shipLoc._1, shipLoc._2)
			}
		}
		if (gameover) {
			level1 = false
			level2 = false
			level3 = false
			level4 = false
			level5 = false
			ProblemString.setText("Limbo")
		}
		if (complete) {
			removeSprite(missile)
			removeSprite(missile2)
			removeSprite(missile3)
			removeSprite(missile4)
			removeSprite(missile5)

			removeSprite(AnswerString1)
			removeSprite(AnswerString2)
			removeSprite(AnswerString3)
			removeSprite(AnswerString4)
			removeSprite(AnswerString5)

			ProblemString.setText("Congratulations! You Won!")
			if ((time % 2) == 0) {
				ProblemString.setColor(Color.red)
			}
			if ((time % 2) == 1) {
				ProblemString.setColor(Color.yellow)
			}
			ContinueString.setText("Play again?")
			LevelString.setText("Winner")
			if (time == 0) {
				sys.exit(0)
			}
			//fanfare.play()
		}
	}

	def level {
		moveMissileX(timeKeeper,0.0375)
		if (level1) {
			removeSprite(TutorialString)
			SkipString.setLocation(2,2)
		}
		if (level2) {
			addSprite(missile)
			addSprite(missilet1)
			addSprite(missile3)
			addSprite(missilet2)
			addSprite(missile5)

			moveMissileX(missile,0.01)
			moveMissileX(missilet1,0.01)
			moveMissileX(missile3,0.01)
			moveMissileX(missilet2,0.01)
			moveMissileX(missile5,0.01)
		}
		if (level3) {
			removeSprite(missilet1)
			removeSprite(missilet2)

			addSprite(missile2)
			addSprite(missile4)

			moveMissileX(missile,0.04)
			moveMissileXReverse(missile2,0.06)
			moveMissileX(missile3,0.03)
			moveMissileXReverse(missile4,0.02)
			moveMissileX(missile5,0.05)
		}
		if (level4) {
			moveMissileX(missile,0.07)
			moveMissileXReverse(missile2,0.03)
			moveMissileX(missile3,0.09)
			moveMissileXReverse(missile4,0.05)
			moveMissileX(missile5,0.06)
		}
		if (level5) {
			moveMissileX(missile,0.07)
			moveMissileXReverse(missile2,0.09)
			moveMissileX(missile3,0.06)
			moveMissileXReverse(missile4,0.02)
			moveMissileX(missile5,0.04)	
		}
		if (complete) {
			removeSprite(missile)
			removeSprite(missile2)
			removeSprite(missile3)
			removeSprite(missile4)
			removeSprite(missile5)

			removeSprite(AnswerString1)
			removeSprite(AnswerString2)
			removeSprite(AnswerString3)
			removeSprite(AnswerString4)
			removeSprite(AnswerString5)
		}
		if (gameover) {
			missile.setLocation(2,2)
			missile2.setLocation(2,2)
			missile3.setLocation(2,2)
			missile4.setLocation(2,2)
			missile5.setLocation(2,2)

			missilet1.setLocation(2,2)
			missilet2.setLocation(2,2)

			ContinueString.setLocation(0.50,0.50)

			moveOptionsY(YesString,0.01)
			moveOptionsY(NoString,0.01)
		}
	}
/*Collision********************************************************************************************/
	def collision {
		if (ship.intersects(missile)) {
			shipHit
		}
		if (ship.intersects(missile2)) {
			shipHit
		}
		if (ship.intersects(missile3)) {
			shipHit
		}
		if (ship.intersects(missile4)) {
			shipHit
		}
		if (ship.intersects(missile5)) {
			shipHit
		}
		if (ship.intersects(missilet1)) {
			shipHit
		}
		if (ship.intersects(missilet2)) {
			shipHit
		}
		if (gameover && ship.intersects(YesString)) {
			gameover = false
			complete = false
			level1 = true
			lives = 10
			time = 31
			removeSprite(TutorialString)
			setup
			advance
		}
		if (gameover && ship.intersects(NoString)) {
			sys.exit(0)
		}
		if (ship.intersects(SkipString)) {
			tutorial = false
			level1 = true
			removeSprite(TutorialString)
			SkipString.setLocation(2,2)
			removeSprite(t1)
			removeSprite(t2)
			removeSprite(t3)
			removeSprite(t4)
			removeSprite(t5)
			removeSprite(pointer)
			removeSprite(testProb)
			time = 31
			ship.setLocation(shipLoc._1,shipLoc._2)
		}
	}
	
	def shipHit {
		println("Try Again!")
		ship.setLocation(shipLoc._1, shipLoc._2)
		lives -= 1
		LifeString.setText("Lives: " + lives)
		if (lives < 1) {
			gameover = true
			level1 = false
			level2 = false
			level3 = false
			level4 = false
			level5 = false
		}
	}
/*time**********************************************************************************************/
	def timer {
		if (timeKeeper.getX > 1.09) {
			time = time - 1
			ClockString.setText(String.valueOf(time))
		}
		if (time > 11) {
			ClockString.setColor(Color.magenta)
		}
		if (time < 11) {
			ClockString.setColor(Color.yellow)
		}
		if (time < 6) {
			ClockString.setColor(Color.red)
		}
		if (time < 0) {
			gameover = true
			ClockString.setText("Game Over")
		}
	}
/*Logic check******************************************************************************************/
	def norepeat {
		//For fake answers being equal to other fake answers
		if (wrong1 == wrong2) {
			wrong1 += 1
			wrong2 -= 2
		}
		if (wrong1 == wrong3) {
			wrong1 += 1
		}
		if (wrong1 == wrong4) {
			wrong1 += 1
		}
		if (wrong2 == wrong3) {
			wrong2 -= 2
			wrong3 -= 1
		}
		if (wrong2 == wrong4) {
			wrong2 -= 2
			wrong4 += 2
		}
		if (wrong3 == wrong4) {
			wrong3 -= 1
			wrong4 += 2
		}
		//For fake answers being equal to real answer
		//Level 1
		if (level1) {
			if (wrong1 == sum1) {
				wrong1 += 1
				AnswerString1.setText(String.valueOf(wrong1))
			}
			if (wrong2 == sum1) {
				wrong2 += 1
				AnswerString2.setText(String.valueOf(wrong2))
			}
			if (wrong3 == sum1) {
				wrong3 += 1
				AnswerString3.setText(String.valueOf(wrong3))
			}
			if (wrong4 == sum1) {
				wrong4 += 1
				AnswerString4.setText(String.valueOf(wrong4))
			}
		}
		//Level 2
		if (level2) {
			if (wrong1 == sum2) {
				wrong1 += 1
				AnswerString1.setText(String.valueOf(wrong1))
			}
			if (wrong2 == sum2) {
				wrong2 += 1
				AnswerString2.setText(String.valueOf(wrong2))
			}
			if (wrong3 == sum2) {
				wrong3 += 1
				AnswerString3.setText(String.valueOf(wrong3))
			}
			if (wrong4 == sum2) {
				wrong4 += 1
				AnswerString4.setText(String.valueOf(wrong4))
			}
		}
		//Level 3
		if (level3) {
			if (wrong1 == sum3) {
				wrong1 += 1
				AnswerString1.setText(String.valueOf(wrong1))
			}
			if (wrong2 == sum3) {
				wrong2 += 1
				AnswerString2.setText(String.valueOf(wrong2))
			}
			if (wrong3 == sum3) {
				wrong3 += 1
				AnswerString3.setText(String.valueOf(wrong3))
			}
			if (wrong4 == sum3) {
				wrong4 += 1
				AnswerString4.setText(String.valueOf(wrong4))
			}
		}
		//Level 4
		if (level4) {
			if (wrong1 == sum4) {
				wrong1 += 1
				AnswerString1.setText(String.valueOf(wrong1))
			}
			if (wrong2 == sum4) {
				wrong2 += 1
				AnswerString2.setText(String.valueOf(wrong2))
			}
			if (wrong3 == sum4) {
				wrong3 += 1
				AnswerString3.setText(String.valueOf(wrong3))
			}
			if (wrong4 == sum4) {
				wrong4 += 1
				AnswerString4.setText(String.valueOf(wrong4))
			}
		}
		//Level 5
		if (level5) {
			if (wrong1 == sum5) {
				wrong1 += 1
				AnswerString1.setText(String.valueOf(wrong1))
			}
			if (wrong2 == sum5) {
				wrong2 += 1
				AnswerString2.setText(String.valueOf(wrong2))
			}
			if (wrong3 == sum5) {
				wrong3 += 1
				AnswerString3.setText(String.valueOf(wrong3))
			}
			if (wrong4 == sum5) {
				wrong4 += 1
				AnswerString4.setText(String.valueOf(wrong4))
			}
		}
	}
/*Tutorial Sequence*************************************************************************************************/
	def tutorialScreen {
		if (time < 20) {
			SkipString.setLocation(2,2)
		}
		if (tutorial) {
			if (time < 31) {
				TutorialString.setText("Hi")
			}
			if (time < 29) {
				TutorialString.setText("Let's go over how to play")
			}
			if (time < 27) {
				TutorialString.setText("Use the arrow keys to move around")
			}
			if (time < 24){
				TutorialString.setText("See this problem?")
				generatePointer
			}
			if (time < 22) {
				TutorialString.setText("Move your ship to the answer")
				generateTutorial
				if (ship.intersects(t2)) {
					right = true
				}
				if (right) {
					removeSprite(t1)
					removeSprite(t2)
					removeSprite(t3)
					removeSprite(t4)
					removeSprite(t5)
					TutorialString.setText("Great Job!")
					ship.setLocation(shipLoc._1,shipLoc._2)
					if (time < 15) {
						TutorialString.setText("Keep answering questions to move on")
					}
					if (time < 12) {
						TutorialString.setText("If you answer wrong...")
					}
					if (time < 10) {
						TutorialString.setText("You lose a try")
					}
				}
				if (ship.intersects(t1) || ship.intersects(t3) || ship.intersects(t4) || ship.intersects(t5)) {
					wrong = true
				}
				if (wrong) {
					pointer.setLocation(0.40,0.55)
					TutorialString.setText("It's actually 2")
					ship.setLocation(shipLoc._1,shipLoc._2)
					if (time < 15) {
						TutorialString.setText("But that's ok. This is practice")
					}
					if (time < 12) {
						TutorialString.setText("Answer correctly to move on")
					}
					if (time < 10) {
						TutorialString.setText("If you answer wrong, you lose a try")
						addSprite(pointer)
						pointer.setLocation(0.95,0.10)
					}
				}
			}
			if (time < 7) {
				TutorialString.setText("Mess up 10 times and its game over")
				if (time < 5) {
					TutorialString.setText("You have 30 seconds per question")
					pointer.setLocation(0.035,0.10)
				}
				if (time < 3) {
					TutorialString.setText("Or game over. Ready?")
				}
			}
			if (time == 0) {
				tutorial = false
				level1 = true
				time = 31
				lives = 10

				removeSprite(TutorialString)
				SkipString.setLocation(2,2)
				removeSprite(pointer)
				removeSprite(testProb)
				removeSprite(t1)
				removeSprite(t2)
				removeSprite(t3)
				removeSprite(t4)
				removeSprite(t5)
			}
		}
	}
}