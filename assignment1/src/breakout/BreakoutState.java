package breakout;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: implement, document
/**
 * Abstract state invariants
 * @invar | getBottomRight() != null
 * @invar | getBottomRight().getX() == GameMap.getWidth() &&
 * 		  | getBottomRight().getY() == GameMap.getHeight() 
 * 
 * 
 * @invar The paddle is located entirely within the game field
 * | getPaddle().getTl().getX() >= 0 && getPaddle().getBr().getX() <= GameMap.getWidth() &&
 * | getPaddle().getTl().getY() >= 0 && getPaddle().getBr().getY() <= GameMap.getHeight()
 * @invar | getPaddle() != null
 * 
 * 
 * @invar All blocks are located entirely within the game field
 * | Arrays.stream(getBlocks()).allMatch(e -> e.getTl().getX() >= 0 && e.getBr().getX() <= GameMap.getWidth()) &&
 * | Arrays.stream(getBlocks()).allMatch(e -> e.getTl().getY() >= 0 && e.getBr().getY() <= GameMap.getHeight())
 * @invar | getBlocks() != null && Arrays.stream(getBlocks()).allMatch(e -> e != null)
 * @invar | Arrays.stream(getBlocks()).allMatch(e -> e.getTl().getX() < e.getBr().getX())
 * @invar | Arrays.stream(getBlocks()).allMatch(e -> e.getTl().getY() < e.getBr().getY())
 * 
 * 
 * @invar All balls are located entirely within the game field
 * | Arrays.stream(getBalls()).allMatch(e -> e.getTl().getX() >= 0 && e.getBr().getX() <= GameMap.getWidth()) && 
 * | Arrays.stream(getBlocks()).allMatch(e -> e.getTl().getY() >= 0 && e.getBr().getY() <= GameMap.getHeight())
 * @invar | getBalls() != null && Arrays.stream(getBalls()).allMatch(e -> e != null)
 * @invar | Arrays.stream(getBalls()).allMatch(e -> e.getTl().getX() < e.getBr().getX())
 * @invar | Arrays.stream(getBalls()).allMatch(e -> e.getTl().getY() < e.getBr().getY())
 * 
 * 
 * 
 * @representationObject 
 */

public class BreakoutState {

	/**
	 * Representation invariants
	 * 
	 * @invar | bottomRight != null 
	 * 		  | && bottomRight.getX() == GameMap.getWidth() 
	 * 		  | && bottomRight.getY() == GameMap.getHeight()
	 * 
	 * 
	 * @invar | paddle != null
	 * @invar The paddle is located entirely within the game field
	 * | paddle.getTl().getX() >= 0 && paddle.getBr().getX() <= GameMap.getWidth() &&
	 * | paddle.getTl().getY() >= 0 && paddle.getBr().getY() <= GameMap.getHeight()
	 * 
	 * 
	 * @invar | blocks != null && Arrays.stream(blocks).allMatch(e -> e != null)
	 * @invar All blocks are located entirely within the game field
	 * | Arrays.stream(blocks).allMatch(e -> e.getTl().getX() >= 0 && e.getBr().getX() <= GameMap.getWidth()) 
	 * @invar| Arrays.stream(blocks).allMatch(e -> e.getTl().getY() >= 0 && e.getBr().getY() <= GameMap.getHeight()) 
	 * 
	 * 
	 * @invar | balls != null && Arrays.stream(balls).allMatch(e -> e != null)
	 * @invar All balls are located entirely within the game field
	 * | Arrays.stream(balls).allMatch(e -> e.getTl().getX() >= 0 && e.getBr().getX() <= GameMap.getWidth()) 
	 * @invar| Arrays.stream(balls).allMatch(e -> e.getTl().getY() >= 0 && e.getBr().getY() <= GameMap.getHeight())
	 * 
	 * 
	 */

	private BallState[] balls;
	private BlockState[] blocks;
	private PaddleState paddle;

	private Point bottomRight;
	private static final int BOUNDARY = GameMap.getWidth();
	private static final int PADDLE_VELOCITY = 10;


	private static final int WIDTH = GameMap.getWidth();
	private static final int HEIGTH = GameMap.getHeight();
	private static final WallState[] gameField = {new WallState(new Point(-WIDTH,0),new Point(0,HEIGTH)),
			new WallState(new Point(0,-HEIGTH),new Point(WIDTH,0)),
			new WallState(new Point(WIDTH,0),new Point(WIDTH*2,HEIGTH))};


	/**
	 * Initializes this object with the given balls, blocks, bottomRight and paddle.
	 * @throws IllegalArgumentException
	 *   | balls == null || blocks == null || paddle == null || bottomRight == null
	 * @throw IllegalArgumentException
	 *   | Arrays.stream(balls).anyMatch(e -> e == null) ||
	 *   | Arrays.stream(blocks).anyMatch(e -> e == null)
	 * @throw IllegalArgumentException
	 *   | bottomRight.getX() != GameMap.getWidth() || bottomRight.getY() != GameMap.getHeight()
	 * @inspect | balls 
	 * @inspect | blocks
	 * @post | Arrays.equals(getBalls(),balls)
	 * @post | Arrays.equals(getBlocks(),blocks) 
	 * @post | getBottomRight().equals(bottomRight)
	 * @post | getPaddle().equals(paddle)
	 * @post all elements in the balls do not equal null.
	 *   | Arrays.stream(balls).allMatch(e -> e != null) 
	 * @post all elements in the blocks do not equal null.
	 *   | Arrays.stream(blocks).allMatch(e -> e != null)
	 */

	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight,PaddleState paddle ) {

		if ( balls == null || blocks == null || paddle == null || bottomRight == null ) {
			throw new IllegalArgumentException("The input parameters should not be null.");
		}

		if (Arrays.stream(balls).anyMatch(e -> e == null)) {
			throw new IllegalArgumentException("BallState array should not be null.");
		}

		if (Arrays.stream(blocks).anyMatch(e -> e == null)) {
			throw new IllegalArgumentException("BlockState array should not be null.");
		}

		this.balls = balls.clone();
		this.blocks = blocks.clone();
		this.paddle = paddle;
		this.bottomRight = bottomRight;
	}


	/**
	 * @post | result != null
	 * @post | 0 <= result.length
	 * @post | Arrays.stream(result).allMatch(e -> e != null)
	 * @creates | result
	 * @inspect | this
	 */

	public BallState[] getBalls() {
		return balls.clone();

	}

	/**
	 * @post | result != null
	 * @post | 0 <= result.length
	 * @post | Arrays.stream(result).allMatch(e -> e != null)
	 * @creates | result
	 * @inspect | this
	 */

	public BlockState[] getBlocks() {
		return blocks.clone();
	}

	/**
	 * @post | result != null
	 */
	public PaddleState getPaddle() {
		return paddle;
	}

	/**
	 * @post | result != null
	 * @post | result.getX() == GameMap.getWidth()
	 * @post | result.getY() == GameMap.getHeight()
	 */

	public Point getBottomRight() {
		return bottomRight;
	}



	/**
	 * @pre | paddleDir == 0 || paddleDir == 1 || paddleDir == -1
	 * 
	 * @post | Arrays.stream(getBalls()).allMatch(e -> e.getTl().getX() < e.getBr().getX())
	 * @post | Arrays.stream(getBalls()).allMatch(e -> e.getTl().getY() < e.getBr().getY())
	 * @post | 0 <= getBalls().length
	 * @post | 0 <= getBlocks().length
	 * 
	 * @creates | result
	 * @mutates | this
	 */

	public void tick(int paddleDir) {


		PaddleState newpaddle = new PaddleState(paddle.getTl(),paddle.getBr(),paddle.getVelocity());
		paddle = newpaddle;

		ArrayList<BallState> newballs = new ArrayList<BallState>();

		for (BallState ball: balls) {
			//Move all balls one step forward according to their current velocity.
			BallState newball = new BallState(ball.getTl().plus(ball.getVelocity()),ball.getBr().plus(ball.getVelocity()),ball.getVelocity());
			if(ball.getTl().getX()<=0&&ball.getTl().getY()>=paddle.getTl().getY()&&ball.getBr().getY()<=paddle.getBr().getY()) {
				newball = new BallState(new Point(0,ball.getTl().getY()+ball.getVelocity().getY()),new Point(ball.getBr().getX()-ball.getTl().getX(),ball.getBr().getY()+ball.getVelocity().getY()),ball.getVelocity());
			}
			if(ball.getBr().getX()>=BOUNDARY&&ball.getTl().getY()>=paddle.getTl().getY()&&ball.getBr().getY()<=paddle.getBr().getY()) {
				newball = new BallState(new Point(BOUNDARY-ball.getBr().getX()+ball.getTl().getX(),ball.getTl().getY()+ball.getVelocity().getY()),new Point(BOUNDARY,ball.getBr().getY()+ball.getVelocity().getY()),ball.getVelocity());
			} 
			
			ball=newball;


			List <Rectangle> rectangleList = new ArrayList<Rectangle>();
			//Add walls first
			for (WallState wall: gameField) {
				rectangleList.add(wall);
			}
			//Add blocks 
			for (BlockState block: blocks) {
				rectangleList.add(block);
			}
			//then add paddle
			rectangleList.add(paddle);

			boolean removeball = false;
			boolean ballreflectX = false;
			boolean ballreflectY = false;

			int ballTY=ball.getTl().getY();
			int ballBY=ball.getBr().getY();
			int ballLX=ball.getTl().getX();
			int ballRX=ball.getBr().getX();
			boolean hitwall = false;

			Vector newvelocity = ball.getVelocity();
			Point newballTl = ball.getTl();
			Point newballBr = ball.getBr();

			ArrayList<Point> blockstl = new ArrayList<Point>();
			ArrayList<BlockState> newblocks = new ArrayList<BlockState>();


			//Check whether any balls hit the bottom of the field, in which case they must be removed from the game.
			if (ball.getBr().getY()>=GameMap.getHeight()) {
				removeball =true;
			}


			//Check whether any balls hit the walls on the left, right and top side of the game area, in which case they must bounce back.
			//Check whether any ball hit any block, in which case the block must be removed from the game and the ball must bounce back.
			//Check whether any ball hit the paddle, in which case it must bounce back.
			//Since the elements in `rectangleList` are in the order, the checking is also in the order.
			for (Rectangle rectangle : rectangleList) {				
				int rectangleTY=rectangle.getTl().getY();
				int rectangleBY=rectangle.getBr().getY();
				int rectangleLX=rectangle.getTl().getX();
				int rectangleRX=rectangle.getBr().getX();


				//If the ball hits the rectangles
				if (ballLX <= rectangleRX && ballRX >= rectangleLX &&rectangleTY <= ballBY && rectangleBY >= ballTY) {
					if (rectangle instanceof WallState) {
						hitwall = true;
					}
					int x = 0;
					int y = 0;
					//ball touches rectangle left 
					if (ballRX>=rectangleLX && ballLX<=rectangleLX && rectangleTY<=ballTY && rectangleBY>=ballBY) {
						//Deal with the exception when the ball is squeezed by wall and paddle
						if(rectangle instanceof WallState && paddle.getBr().getX()>=BOUNDARY-(ballRX-ballLX)-paddle.getVelocity() && ballBY<=paddle.getBr().getY() && ballTY>=paddle.getTl().getY()) {
							newballTl = new Point(BOUNDARY-(ballRX-ballLX),ball.getTl().getY());
							newballBr = new Point(BOUNDARY,ball.getBr().getY());
							newvelocity = new Vector(0,newvelocity.getY());	
							
						}
						else {
							x = ballRX-rectangleLX;
							newballTl = newballTl.plus(new Vector(-2*x,0));
							newballBr = newballBr.plus(new Vector(-2*x,0));
						}
					}
					//ball touches rectangle right
					if (ballLX<=rectangleRX && ballRX>=rectangleRX && rectangleTY<=ballTY && rectangleBY>=ballBY) {
						//Deal with the exception when the ball is squeezed by wall and paddle
						if(rectangle instanceof WallState &&paddle.getTl().getX()<=(ballRX-ballLX)-paddle.getVelocity() && ballBY<=paddle.getBr().getY() && ballTY>=paddle.getTl().getY()) {
							newballTl = new Point(0,ballTY);
							newballBr = new Point(ballRX-ballLX,ballBY);
							newvelocity = new Vector(0,newvelocity.getY());
						}
						else {
							x = ballLX-rectangleRX;
							newballTl = newballTl.plus(new Vector(-2*x,0));
							newballBr = newballBr.plus(new Vector(-2*x,0));
						}
					}

					// ball touches rectangle bottom
					if (ballTY <= rectangleBY&&ballBY >= rectangleBY&&ballLX >= rectangleLX && ballRX <= rectangleRX) {			
						y = ballTY-rectangleBY;
						newballTl = newballTl.plus(new Vector(0,-2*y));
						newballBr = newballBr.plus(new Vector(0,-2*y));
					}
					//ball touches rectangle top
					if (ballBY >= rectangleTY&&ballTY <= rectangleTY&&ballLX >= rectangleLX && ballRX <= rectangleRX) {	
						y = ballBY-rectangleTY;
						newballTl = newballTl.plus(new Vector(0,-2*y));
						newballBr = newballBr.plus(new Vector(0,-2*y));
					}


					Vector balltorectanglevector= new Vector((rectangleRX+rectangleLX)/2-ball.getCenter().getX(),(rectangleBY+rectangleTY)/2-ball.getCenter().getY());
					
					//If the ball hit two sides of the rectangle
					if (ballLX <= rectangleRX && ballRX >= rectangleRX 
							|| ballRX >= rectangleLX && ballLX <= rectangleLX) {
						ballreflectX=true;
					}
					//If the ball hit the top of the paddle or the the top and bottom of the blocks or the bottom of the wall
					if (rectangleTY <= ballBY && rectangleTY >= ball.getCenter().getY() 
							|| rectangleBY <= ballBY && rectangleBY >= ballTY && rectangle instanceof BlockState
							|| rectangleBY <= ballBY && rectangleBY >= ballTY && rectangle instanceof WallState) {
						ballreflectY=true;
					}
					//Deal with the reflection on the corner 
					if(balltorectanglevector.getX()*ball.getVelocity().getX()<0 
							&& ballreflectX && ballreflectY) {
						ballreflectX=false;
					}
					if(balltorectanglevector.getY()*ball.getVelocity().getY()<0 
							&& ballreflectX && ballreflectY&&rectangle instanceof BlockState) {
						ballreflectY=false;
					}

					//record how many blocks are hit a the same time
					if (rectangle instanceof BlockState) {
						blockstl.add(rectangle.getTl());
					}

					//when the ball hits the paddle, the ball will speed up/slow down by one fifth of the current velocity of the paddle.
					if (rectangle instanceof PaddleState) {
						if(ballreflectY&&!ballreflectX) {
							newvelocity = newvelocity.plus(new Vector(paddle.getVelocity()/5,-2*newvelocity.getY()));
						}
						if(ballreflectY&&ballreflectX) {
							newvelocity = newvelocity.plus(new Vector(paddle.getVelocity()/5-2*newvelocity.getX(),-2*newvelocity.getY()));
						}
						if(ballreflectX&&!ballreflectY) {
							//Deal with the situation when the ball is squeezed by the paddle and the wall 
							//to keep the ball in the game field.
							if (ballRX>= BOUNDARY||ballLX<=0) {
								//right 
								if(ball.getCenter().getX()>=rectangle.getPosition().getX()) {
									//newpaddle = new PaddleState(paddle.getTl().minus(new Vector(paddle.getVelocity(),0)),paddle.getBr().minus(new Vector(paddle.getVelocity(),0)),0);
								 	newpaddle = new PaddleState(new Point(ballLX-(paddle.getBr().getX()-paddle.getTl().getX()),paddle.getTl().getY()),new Point(ballLX,paddle.getBr().getY()),0);
									newballTl = new Point(BOUNDARY-(ballRX-ballLX),ballTY);
									newballBr = new Point(BOUNDARY,ballBY);
									newvelocity = new Vector(0,ball.getVelocity().getY());
								}  
								//left
								else { 
									newpaddle = new PaddleState(new Point(ballRX,paddle.getTl().getY()),new Point(ballRX+(paddle.getBr().getX()-paddle.getTl().getX()),paddle.getBr().getY()),0);

									//newpaddle = new PaddleState(paddle.getTl().minus(new Vector(paddle.getVelocity(),0)),paddle.getBr().minus(new Vector(paddle.getVelocity(),0)),0);
									newballTl = new Point(0,ballTY);
									newballBr = new Point(ballRX-ballLX,ballBY);
									newvelocity = new Vector(0,ball.getVelocity().getY());
								}
								paddle = newpaddle;
							}
							else{			
								if (newvelocity.getX()*paddle.getVelocity()>0) {
									newvelocity = newvelocity.plus(new Vector(paddle.getVelocity()/5,0));	 
								}
								else {
									newvelocity = newvelocity.plus(new Vector(paddle.getVelocity()/5-2*newvelocity.getX(),0));
								}
							}					
						}
					}
				}
				else {
					if (rectangle instanceof BlockState) {
						newblocks.add((BlockState) rectangle);
					}
				}
			}


			if (hitwall&&blockstl.size() == 0) {
				if(ballreflectY) {
					newvelocity = newvelocity.mirrorOver(Vector.DOWN);
				}
				if(ballreflectX) {	
					newvelocity = newvelocity.mirrorOver(Vector.RIGHT);
				}
			}

			if(blockstl.size() == 1||blockstl.size() == 3) {

				if (hitwall|| blockstl.size() == 3) {
					ballreflectY = true;
					ballreflectX = true;	
				}
				if(ballreflectY ) {
					newvelocity = newvelocity.mirrorOver(Vector.DOWN);
				}
				if(ballreflectX) {	
					newvelocity = newvelocity.mirrorOver(Vector.RIGHT);
				}
			}

			if(blockstl.size() == 2) {
				if(blockstl.get(0).getY() == blockstl.get(1).getY()) {
					newvelocity = newvelocity.mirrorOver(Vector.DOWN);
				}
				if(blockstl.get(0).getX() == blockstl.get(1).getX()) {
					newvelocity = newvelocity.mirrorOver(Vector.RIGHT);
				}
			}


			newball = new BallState(newballTl,newballBr,newvelocity);
			ball=newball;

			if (!removeball) {
				newballs.add(ball);
			}

			BlockState[] newblocksarray = new BlockState[newblocks.size()];
			newblocks.toArray(newblocksarray);
			blocks = newblocksarray;


		}

		BallState[] newballsarray = new BallState[newballs.size()];
		newballs.toArray(newballsarray);
		balls = newballsarray;

	}


	/**
	 * @creates | result
	 * @inspects | this 
	 * 
	 */
	public void movePaddleRight() {

		if (paddle.getBr().getX()+paddle.getVelocity() >= BOUNDARY) {
			PaddleState newpaddle = new PaddleState(new Point(BOUNDARY-paddle.getBr().getX()+paddle.getTl().getX(),paddle.getTl().getY()),new Point(BOUNDARY,paddle.getBr().getY()),0);
			paddle = newpaddle;


		}
		else {
			PaddleState newpaddle = new PaddleState(paddle.getTl().plus(new Vector(PADDLE_VELOCITY,0)),paddle.getBr().plus(new Vector(PADDLE_VELOCITY,0)),PADDLE_VELOCITY);
			paddle = newpaddle;
		} 
	}

	/**
	 * @creates | result
	 * @inspects | this 
	 */
	public void movePaddleLeft() {

		if (paddle.getTl().getX()+paddle.getVelocity() <= 0) {

			PaddleState newpaddle = new PaddleState(new Point(0,paddle.getTl().getY()),new Point(paddle.getBr().getX()-paddle.getTl().getX(),paddle.getBr().getY()),0);
			paddle = newpaddle;

		}

		else {

			PaddleState newpaddle = new PaddleState(paddle.getTl().plus(new Vector(-PADDLE_VELOCITY,0)),paddle.getBr().plus(new Vector(-PADDLE_VELOCITY,0)),-PADDLE_VELOCITY);
			paddle = newpaddle;
		}
	}

	/**
	 * @post | result == (getBlocks().length == 0 && getBalls().length > 0) 
	 */
	public boolean isWon() {
		if(blocks.length == 0 && balls.length>0){
			return true;
		} else {
			return false;
		}
	}


	/**
	 * @post | result == (getBalls().length == 0)
	 */
	public boolean isDead() {
		if(balls.length == 0){
			return true;
		} else {
			return false;
		}

	}







}


