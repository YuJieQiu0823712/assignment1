package breakout;


import java.util.ArrayList;
import java.util.Arrays;

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
 * @invar | 0 <= getBalls().length
 * @invar | 0 <= getBlocks().length
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
//	 * @invar | 0 <= balls.length
//	 * @invar | 0 <= blocks.length
     * @representationObject
	 */

	private BallState[] balls;
	private BlockState[] blocks;
	private PaddleState paddle;
	private Point bottomRight;
	private static final int BOUNDARY = GameMap.getWidth();
	
	
	
	/**
	 * Initializes this object with the given balls, blocks, bottomRight and paddle.
	 * 
	 * @post | Arrays.equals(getBalls(),balls)
	 * @post | Arrays.equals(getBlocks(),blocks) 
	 * @post | getBottomRight() == bottomRight
//	 * @post | getPaddle()==paddle | does not hold
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
//	 * @post | 0 <= getBalls().length => don't need
//     * @post | getBalls().length >= old(getBalls().length) => shutdown
	 * @post | Arrays.stream(result).allMatch(e -> e != null)
	 * @creates | result
	 * @inspect | this
	 */
	public BallState[] getBalls() {
		ArrayList<BallState> newballs2 = new ArrayList<BallState>(); 
		for (BallState ball : balls) {
			BallState newball = new BallState(ball.getTl().plus(ball.getVelocity()),ball.getBr().plus(ball.getVelocity()),ball.getVelocity());		
			newballs2.add(newball);
		}
		return newballs2.toArray(balls);
	}
	
	/**
	 * @post | result != null
//	 * @post | 0 <= getBlocks().length => don't need
//     * @post | getBlocks().length <= old(getBlocks().length)=> shutdown
	 * @post | Arrays.stream(result).allMatch(e -> e != null)
	 * @creates | result
	 * @inspect | this
	 */

	public BlockState[] getBlocks() {
		
		
		//�P�_�y���L����block
		ArrayList<BallState> newballs = new ArrayList<BallState>();
		for (BallState ball : balls) {
			ArrayList<Point> blockstl = new ArrayList<Point>();
			ArrayList<BlockState> newblocks = new ArrayList<BlockState>();
			boolean ballreflectX = false;
			boolean ballreflectY = false;
			for (BlockState block: blocks) {
				boolean hit = false;
				int ballTY=ball.getTl().getY();
				int ballBY=ball.getBr().getY();
				int ballLX=ball.getTl().getX();
				int ballRX=ball.getBr().getX();

				int blockTY=block.getTl().getY();
				int blockBY=block.getBr().getY();
				int blockLX=block.getTl().getX();
				int blockRX=block.getBr().getX();
				
				if (ballLX <= blockRX && ballRX >= blockLX && blockBY >= ballTY && blockTY <= ballBY) {
					hit = true;
					
					blockstl.add(block.getTl());
					Vector balltoblockvector= new Vector((blockRX+blockLX)/2-ball.getCenter().getX(),(blockBY+blockTY)/2-ball.getCenter().getY());
					                                 
					if (ballLX <= blockRX&&ballRX >= blockRX || ballRX >= blockLX&&ballLX <= blockLX) {
						ballreflectX=true;
					}
					if (blockBY >= ballTY&&blockBY <= ballBY || blockTY <= ballBY&&blockTY >= ballTY) {
						ballreflectY=true;
					}
					if(balltoblockvector.getX()*ball.getVelocity().getX()<0&&ballreflectX&&ballreflectY) {
						ballreflectX=false;
					}
					if(balltoblockvector.getY()*ball.getVelocity().getY()<0&&ballreflectX&&ballreflectY) {
						ballreflectY=false;
					}
				}
				if (hit == false) {
					newblocks.add(block);
				}

			} 
			if(blockstl.size() == 1||blockstl.size() == 3) {
				if(ballreflectY) {
					BallState newball = new BallState (ball.getTl(),ball.getBr(),ball.getVelocity().mirrorOver(Vector.DOWN));
					ball=newball;
				}
				if(ballreflectX) {
					BallState newball = new BallState (ball.getTl(),ball.getBr(),ball.getVelocity().mirrorOver(Vector.RIGHT));
					ball=newball;
				}
			}
			if(blockstl.size() == 2) {
				if(blockstl.get(0).getY() == blockstl.get(1).getY()) {
					BallState newball = new BallState (ball.getTl(),ball.getBr(),ball.getVelocity().mirrorOver(Vector.DOWN));
					ball=newball;
				}
				if(blockstl.get(0).getX() == blockstl.get(1).getX()) {
					BallState newball = new BallState (ball.getTl(),ball.getBr(),ball.getVelocity().mirrorOver(Vector.RIGHT));
					ball=newball;
				}
			}
			newballs.add(ball);
			BlockState[] newblocksarray = new BlockState[newblocks.size()];
			newblocks.toArray(newblocksarray);
			blocks = newblocksarray;
		}
		newballs.toArray(balls);

		return blocks;
	}
	
	/**
	 * @post | result != null
	 * @creates | result
	 * @inspect | this
	 */

	public PaddleState getPaddle() {
		PaddleState newpaddle = new PaddleState(paddle.getTl().plus(new Vector(paddle.getVelocity(),0)),paddle.getBr().plus(new Vector(paddle.getVelocity(),0)),0);
		paddle = newpaddle;
		return paddle;
	}
	
	/**
	 * @post | result != null
	 */

	public Point getBottomRight() {
		return this.bottomRight;
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
	 * @inspect | this //add?
	 */
	// It is not necessary to specify the precise behavior
	public void tick(int paddleDir) {
		

		ArrayList<BallState> newballs2 = new ArrayList<BallState>();
		for (BallState ball: balls) {
			// ball touch paddle
			if (ball.getBr().getY() >= getPaddle().getTl().getY()&&ball.getTl().getY() <= getPaddle().getBr().getY() && ball.getBr().getX()>= getPaddle().getTl().getX() && ball.getTl().getX()<= getPaddle().getBr().getX()) {
				BallState newball = new BallState (ball.getTl(),ball.getBr(),new Vector (ball.getVelocity().getX()+getPaddle().getVelocity()*(1/5)*paddleDir,-ball.getVelocity().getY()));
				ball=newball;
			}
			// ball touch GameMapRight
			if (ball.getBr().getX() >= GameMap.getWidth()) {
				BallState newball = new BallState (ball.getTl(),ball.getBr(),new Vector (-ball.getVelocity().getX(),ball.getVelocity().getY()));
				ball=newball;
			}
			// ball touch GameMapLeft
			if (ball.getTl().getX() <= 0) {
				BallState newball = new BallState (ball.getTl(),ball.getBr(),new Vector (-ball.getVelocity().getX(),ball.getVelocity().getY()));
				ball=newball;
			}
			// ball touch GameMapTop
			if (ball.getTl().getY() <= 0) {
				BallState newball = new BallState (ball.getTl(),ball.getBr(),new Vector (ball.getVelocity().getX(),-ball.getVelocity().getY()));
				ball=newball;
			}
				newballs2.add(ball);

		}
		newballs2.toArray(balls);
	}
	
	/**
	 * @creates | result
	 * @inspects | this //add?
	 */
	// It is not necessary to specify the precise behavior
	public void movePaddleRight() {
		if (paddle.getBr().getX() <= BOUNDARY) {
		PaddleState newpaddle = new PaddleState(paddle.getTl(),paddle.getBr(),100);
		paddle = newpaddle;
		} 
	}
	
	/**
	 * @creates | result
	 * @inspects | this //add?
	 */
	// It is not necessary to specify the precise behavior
	public void movePaddleLeft() {
		if (paddle.getTl().getX() >=0) {
			PaddleState newpaddle = new PaddleState(paddle.getTl(),paddle.getBr(),-100);
			paddle = newpaddle;
		}
		
	}

	/**
	 * @post If getBlock().length == 0, return true,otherwise return false
	 *   | getBlocks().length == 0 ? true : false
	 */
	public boolean isWon() {
		if(getBlocks().length == 0){
			return true;
		} else {

			return false;
		}
	}

	/**
	 * @post If getBlock().length == 0, return true,otherwise return false
	 *   | getBalls().length == 0 ? true : false
	 */
	public boolean isDead() {
		if(Arrays.stream(balls,0,balls.length).allMatch(e -> e.getBr().getY()>=GameMap.getHeight())){
			return true;
		} else {

			return false;
		}

	}


}
