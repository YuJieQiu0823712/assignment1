package breakout;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BreakoutStateTest {

	@Test
	void test() {
		// ball
		Point myBallTl = new Point(3,3);
		Point myBallBr = new Point(5,5);
		Vector myBallVelocity = new Vector(5,7);
		BallState myBallState = new BallState(myBallTl, myBallBr, myBallVelocity);
		
		Point myBallTl2 = new Point(4,4);
		Point myBallBr2 = new Point(6,6);
		Vector myBallVelocity2 = new Vector(5,7);
		BallState myBallState2 = new BallState(myBallTl2, myBallBr2, myBallVelocity2);
		
		BallState[] myBallsState = new BallState[] {myBallState,myBallState2};
		
		// block
		Point myBlockTl = new Point(0,0);
		Point myBlockBr = new Point(2,1);
		BlockState myBlockState = new BlockState(myBlockTl, myBlockBr);
		
		Point myBlockTl2 = new Point(2,2);
		Point myBlockBr2 = new Point(4,3);
		BlockState myBlockState2 = new BlockState(myBlockTl2, myBlockBr2);
		
		BlockState[] myBlocksState = new BlockState[] {myBlockState,myBlockState2};
		
//		//paddle
		Point myPaddleTl = new Point(6,6);
		Point myPaddleBr = new Point(8,7);
		int myPaddleVelocity = 1;
		PaddleState myPaddleState = new PaddleState(myPaddleTl, myPaddleBr, myPaddleVelocity);
		
//		// bottomRight
		Point myBottomRight = new Point(GameMap.getWidth(), GameMap.getHeight());
		
	
		
		// new Balls
		BallState myNewBall = new BallState(myBallTl.plus(myBallVelocity), myBallBr.plus(myBallVelocity),myBallVelocity);
		BallState myNewBall2 = new BallState(myBallTl2.plus(myBallVelocity2), myBallBr2.plus(myBallVelocity2),myBallVelocity2);
		BallState[] myNewBallsState = new BallState[] {myNewBall,myNewBall2};
		
		
		BreakoutState myBreakoutState = new BreakoutState(myBallsState,myBlocksState,myBottomRight, myPaddleState);
		
//		assertArrayEquals(myBallsState, myBreakoutState.getBalls());
//		assertEquals(2, myBreakoutState.getBalls().length);
		

		
	
		
//		assertEquals(myBlocksState, myBreakoutState.getBlocks());
//		assertEquals(myPaddleState,myBreakoutState.getPaddle());
//		assertEquals(myBottomRight,myBreakoutState.getBottomRight());
//		assertEquals(myBottomRight,myBreakoutState.tick(1));
	
	}

}
