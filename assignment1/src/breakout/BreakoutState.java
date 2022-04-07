package breakout;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: implement, document
//these class properly encapsulated
//insert complete public and internal formal documentation for these classes.
//defensive programming: throw error
//test suite for these classes 

//@pre,@post,@throws,@invar,@immutable,@representationObject,@inspects and @mutates

//`BreakoutState` must have an invariant that specifies that
//the paddle is located entirely within the game field and this invariant must be enforced correctly.
//`BreakoutState` must have invariants that specify that 
//all blocks and balls are located entirely within the game field and these invariants must be enforced 
/**
 * Abstract state invariants
 * @invar | getBalls() != null
 * @invar | getBlocks() != null
 * @invar | getPaddle() != null
 * @invar | getBottomRight() != null
 *
 */

public class BreakoutState {
	
	/**
	 * Representation invariants
	 * 
	 * @invar | balls != null
     * @invar | blocks != null
     * @invar | paddle != null
     * @invar | bottomRight != null
     * @representationObject
	 */

	private BallState[] balls;
	private BlockState[] blocks;
	private PaddleState paddle;
	private Point bottomRight;
	private static final int BOUNDARY = GameMap.getWidth();
	private static final int PADDLE_VELOCITY = 100;
	
	
	/**
	 * Initializes this object with the given balls, blocks, bottomRight and paddle.
	 * 
	 * @post | Arrays.equals(getBalls(),balls)
	 * @post | Arrays.equals(getBlocks(),blocks) 
	 * @post | getBottomRight().equals(bottomRight)
	 * @post | !(getPaddle().equals(paddle))
     * @post all elements in the balls not equals null.
	 *   | Arrays.stream(balls,0,balls.length).allMatch(e -> e != null) // change to "balls.length"
 	 * @post all elements in the blocks not equals null.
     *   | Arrays.stream(blocks,0,blocks.length).allMatch(e -> e != null) // change to "blocks.length"
	 * @throws IllegalArgumentException
	 *   | balls == null | blocks == null | paddle == null | bottomRight == null
	 * @throw IllegalArgumentException
	 *   | Arrays.stream(balls).anyMatch(e -> e == null)
	 *   | Arrays.stream(blocks).anyMatch(e -> e == null)
	 */

	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight,PaddleState paddle ) {
		
		if ( balls == null | blocks == null | paddle == null | bottomRight == null ) {
			throw new IllegalArgumentException("The input parameters should not be null.");
		}
		
		if (Arrays.stream(balls).anyMatch(e -> e == null)) {
			throw new IllegalArgumentException("BallState array should not be null.");
		}
		
		if (Arrays.stream(blocks).anyMatch(e -> e == null)) {
			throw new IllegalArgumentException("BlockState array should not be null.");
		}
		
		this.balls = balls;
		this.blocks = blocks;
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
		ArrayList<BallState> newballs2 = new ArrayList<BallState>(); 
		for (BallState ball : balls) {
			BallState newball = new BallState(ball.getTl(),ball.getBr(),ball.getVelocity());		
			newballs2.add(newball);
		}
		newballs2.toArray(balls);


		return newballs2.toArray(balls);
	}
	
	/**
	 * @post | result != null
	 * @post | 0 <= result.length
	 * @post | Arrays.stream(result).allMatch(e -> e != null)
	 * @creates | result
	 * @inspect | this
	 */

	public BlockState[] getBlocks() {

		return blocks;
	}
	
	/**
	 * @post | result != null
	 * @creates | result
	 * @inspect | this
	 */

	public PaddleState getPaddle() {
		PaddleState newpaddle = new PaddleState(paddle.getTl(),paddle.getBr(),paddle.getVelocity());
		paddle = newpaddle;
		return paddle;
	}
	
	/**
	 * @post | result != null
	 */

	public Point getBottomRight() {
		return bottomRight;
	}
	
//	This method must do the following (in this order):
//	- Move all balls one step forward according to their current velocity.
//	- Check whether any balls hit the walls on the left, right and top side of the game area, in which case they must bounce back.
//	- Check whether any balls hit the bottom of the field, in which case they must be removed from the game.
//	- Check whether any ball hit any block, in which case the block must be removed from the game and the ball must bounce back.
//	- Check whether any ball hit the paddle, in which case it must bounce back.

//	Additionally, you must not duplicate code for detecting collisions between 
//	(1) balls and blocks, (2) balls and paddles and (3) balls and walls,
//	by introducing and using a new well-documented abstraction for representing rectangles.
    
	/**
	 * @post | 0 <= getBalls().length
	 * @creates | result
	 * @inspect | this
	 */
	// It is not necessary to specify the precise behavior


	public void tick(int paddleDir) {

		PaddleState newpaddle = new PaddleState(paddle.getTl(),paddle.getBr(),PADDLE_VELOCITY*paddleDir);
			if(paddle.getTl().getX()<=0&&paddleDir==-1||paddle.getBr().getX()>=BOUNDARY&&paddleDir==1) {
			newpaddle = new PaddleState(paddle.getTl(),paddle.getBr(),0);
		}
		paddle = newpaddle;
		ArrayList<BallState> newballs = new ArrayList<BallState>();

		for (BallState ball: balls) {
			//Move all balls one step forward according to their current velocity.
			BallState newball = new BallState(ball.getTl().plus(ball.getVelocity()),ball.getBr().plus(ball.getVelocity()),ball.getVelocity());
			ball=newball;
		
	
		
			List <Rectangle> rectangleList = new ArrayList<Rectangle>();
			//Add blocks first
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

			Vector newvelocity = ball.getVelocity();
			Point newballTl = ball.getTl();
			Point newballBr = ball.getBr();

			ArrayList<Point> blockstl = new ArrayList<Point>();
			ArrayList<BlockState> newblocks = new ArrayList<BlockState>();

			// Check whether any balls hit the walls on the left, right and top side of the game area, in which case they must bounce back.
			// ball touch GameMapRight 
			int x = 0;
			if (ball.getBr().getX() >= GameMap.getWidth()) {
				x = newballBr.getX()-BOUNDARY;
				newballTl = newballTl.plus(new Vector(-2*x,0));
				newballBr = newballBr.plus(new Vector(-2*x,0));
				newvelocity = newvelocity.mirrorOver(Vector.RIGHT);
			}
			//ball touch GameMapLeft
			if (ball.getTl().getX() <= 0) {
				x = newballTl.getX();
				newballTl = newballTl.plus(new Vector(-2*x,0));
				newballBr = newballBr.plus(new Vector(-2*x,0));
				newvelocity = newvelocity.mirrorOver(Vector.RIGHT);
			}

			// ball touch GameMapTop
			if (ball.getTl().getY() <= 0) {			
				
				int y = newballTl.getY();
				newballTl = newballTl.plus(new Vector(0,-2*y));
				newballBr = newballBr.plus(new Vector(0,-2*y));
				newvelocity = newvelocity.mirrorOver(Vector.DOWN);
				
				
			}

			//Check whether any balls hit the bottom of the field, in which case they must be removed from the game.
			if (ball.getBr().getY()>=GameMap.getHeight()) {
				removeball =true;
			}


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
					
					Vector balltorectanglevector= new Vector((rectangleRX+rectangleLX)/2-ball.getCenter().getX(),(rectangleBY+rectangleTY)/2-ball.getCenter().getY());
					//If the ball hit two sides of the rectangle
					if (ballLX <= rectangleRX && ballRX >= rectangleRX 
							|| ballRX >= rectangleLX && ballLX <= rectangleLX) {
						ballreflectX=true;
					}
					//If the ball hit the top of the paddle or the the top and bottom of the blocks
					if (rectangleTY <= ballBY && rectangleTY >= ball.getCenter().getY() 
							|| rectangleBY <= ballBY && rectangleBY >= ballTY && rectangle instanceof BlockState) {
						ballreflectY=true;
					}
					//Deal with the reflection on edges
					if(balltorectanglevector.getX()*ball.getVelocity().getX()<0 
							&& ballreflectX && ballreflectY) {
						ballreflectX=false;
					}
					if(balltorectanglevector.getY()*ball.getVelocity().getY()<0 
							&& ballreflectX && ballreflectY&&rectangle instanceof BlockState) {
						ballreflectY=false;
					}
					
					//Renew blocks
					if (rectangle instanceof BlockState) {
						blockstl.add(rectangle.getTl());
					}

					//Renew paddle
					if (rectangle instanceof PaddleState) {
						if(ballreflectY&&!ballreflectX) {
							newvelocity = newvelocity.plus(new Vector(paddle.getVelocity()/5,-2*newvelocity.getY()));			
						}
						if(ballreflectY&&ballreflectX) {
							newvelocity = newvelocity.plus(new Vector(paddle.getVelocity()/5-2*newvelocity.getX(),-2*newvelocity.getY()));
						}
						if(ballreflectX&&!ballreflectY) {
							//Deal with the vibration when the ball is squeezed by the paddle and the wall
							if (ballRX>= BOUNDARY||ballLX<=0) {

								if(ball.getCenter().getX()>=rectangle.getPosition().getX()) {

									//no limit paddle location?
									newpaddle = new PaddleState(paddle.getTl().minus(new Vector(paddle.getVelocity(),0)),paddle.getBr().minus(new Vector(paddle.getVelocity(),0)),0);
									newballTl = new Point(BOUNDARY-(ballRX-ballLX),ballTY);
									newballBr = new Point(BOUNDARY,ballBY);
									newvelocity = new Vector(0,ball.getVelocity().getY());
								}  
								else {
									newpaddle = new PaddleState(paddle.getTl().minus(new Vector(paddle.getVelocity(),0)),paddle.getBr().minus(new Vector(paddle.getVelocity(),0)),0);
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
									
//									if(newballTl.getX()<rectangle.getBr().getX() ) {//add
//										newvelocity = newvelocity.plus(new Vector(paddle.getVelocity()/5-2*newvelocity.getX(),0));
//										
//										
//										Point newpaddleTl = paddle.getTl();
//										Point newpaddleBr = paddle.getBr();
//										int newpaddlevelocity = paddle.getVelocity();
//										
//										newpaddleTl = new Point((newballBr.getX()-(newpaddleBr.getX()-newpaddleTl.getX())),paddle.getTl().getY());
//										newpaddleBr = new Point(newballBr.getX(),paddle.getBr().getY());
//										newpaddlevelocity = 0;
//										newpaddle = new PaddleState(newpaddleTl,newpaddleBr,newpaddlevelocity);
//									}
//									if(newballBr.getX()>rectangle.getTl().getX()) { //add
//										newvelocity = newvelocity.plus(new Vector(paddle.getVelocity()/5-2*newvelocity.getX(),0));
//										
//										
//										Point newpaddleTl = paddle.getTl();
//										Point newpaddleBr = paddle.getBr();
//										int newpaddlevelocity = paddle.getVelocity();
//										
//										newpaddleTl = new Point(newballBr.getX(),paddle.getTl().getY());
//										newpaddleBr = new Point(newballBr.getX()+(newpaddleBr.getX()-newpaddleTl.getX()),paddle.getBr().getY());
//										newpaddlevelocity = 0;
//										newpaddle = new PaddleState(newpaddleTl,newpaddleBr,newpaddlevelocity);
//
//										
//									}
									
									

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


			if(blockstl.size() == 1||blockstl.size() == 3) {
				if(ballreflectY) {
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
	 * @inspect | this
	 */
	// It is not necessary to specify the precise behavior
	public void movePaddleRight() {
		PaddleState newpaddle = new PaddleState(paddle.getTl().plus(new Vector(paddle.getVelocity(),0)),paddle.getBr().plus(new Vector(paddle.getVelocity(),0)),paddle.getVelocity());
		paddle = newpaddle;

	}
	
	
	
	/**
	 * @creates | result
	 * @inspect | this
	 */
	// It is not necessary to specify the precise behavior
	public void movePaddleLeft() {

		PaddleState newpaddle = new PaddleState(paddle.getTl().plus(new Vector(paddle.getVelocity(),0)),paddle.getBr().plus(new Vector(paddle.getVelocity(),0)),paddle.getVelocity());
		paddle = newpaddle;

	}
	
// https://stackoverflow.com/questions/1834621/what-should-i-write-in-my-javadoc-class-and-method-comments
	/**
	 * @return | getBlocks().length == 0 && getBalls().length>0 ? true : false
//	 * @post If getBlock().length == 0, return true,otherwise return false
//	 *   | getBlocks().length == 0 && getBalls().length>0 ? true : false
//     *   | result == true ? getBlocks().length == 0 && getBalls().length>0 : false
	 */
	public boolean isWon() {
		if(getBlocks().length == 0 && balls.length>0){
			return true;
		} else {
			return false;
		}
		 
	}

	/**
	 * * @return | getBlocks().length == 0 && getBalls().length>0 ? true : false
//	 * @post If getBalls().length == 0, return true,otherwise return false
//	 *   | getBalls().length == 0 ?  true : false
	 */
	public boolean isDead() {
		if(balls.length == 0){
			return true;
		} else {
			return false;
		}

	}


}
