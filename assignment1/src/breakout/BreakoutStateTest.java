package breakout;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class BreakoutStateTest {

	@Test
	void test() {
		// ball
		Point myBallTl = new Point(3,3);
		Point myBallBr = new Point(5,5);
		Vector myBallVelocity = new Vector(5,7);
		BallState myBall = new BallState(myBallTl, myBallBr, myBallVelocity);
		
		BallState[] myBalls = new BallState[] {myBall};
				
		// block
		Point myBlockTl1 = new Point(0,0);
		Point myBlockBr1 = new Point(2,1);
		BlockState myBlock1 = new BlockState(myBlockTl1, myBlockBr1);
		Point myBlockTl2 = new Point(2,0);
		Point myBlockBr2 = new Point(4,1);
		BlockState myBlock2 = new BlockState(myBlockTl2, myBlockBr2);
		
		BlockState[] myBlocks = new BlockState[] {myBlock1,myBlock2};
		
		// paddle
		Point myPaddleTl = new Point(6,6);
		Point myPaddleBr = new Point(8,7);
		int myPaddleVelocity = 100 ;
		PaddleState myPaddle = new PaddleState(myPaddleTl, myPaddleBr, myPaddleVelocity);
		
		// bottomRight
		Point myBottomRight = new Point(GameMap.getWidth(), GameMap.getHeight());
		
		// breakoutState
		BreakoutState myBreakoutState = new BreakoutState(myBalls,myBlocks,myBottomRight, myPaddle);
		
		assertArrayEquals(myBalls, myBreakoutState.getBalls());
		assertArrayEquals(myBlocks, myBreakoutState.getBlocks());
		assertEquals(myPaddle.getTl(),myBreakoutState.getPaddle().getTl());
		assertTrue(myPaddle.getTl().equals(myBreakoutState.getPaddle().getTl()));
		assertEquals(myBottomRight,myBreakoutState.getBottomRight());
		assertEquals(1, myBreakoutState.getBalls().length);
		
//		tick method
		int paddleDir = 0;
		myBreakoutState.tick(paddleDir);
		assertEquals(myPaddle.getTl(),myBreakoutState.getPaddle().getTl());
		assertEquals(myPaddle.getBr(),myBreakoutState.getPaddle().getBr());
		assertEquals(myPaddle.getVelocity()*paddleDir,myBreakoutState.getPaddle().getVelocity());
		
//	    [1] After ball touch GameMapLeft 
	    
	    // new Ball1
	 	Point myBallTl1 = new Point(3,3);
	 	Point myBallBr1 = new Point(5,5);
	 	Vector myBallVelocity1 = new Vector(-6,0);
	 	BallState myBall1 = new BallState(myBallTl1, myBallBr1, myBallVelocity1);
	 	
	 	BallState myNewBall1 = new BallState(myBallTl1.plus(myBallVelocity1), myBallBr1.plus(myBallVelocity1), new Vector(-myBallVelocity1.getX(),myBallVelocity1.getY()));
	 		                                   //tl(-3,3) br(-1,5) velocity(6,0)
	 	BallState[] myNewBalls1 = new BallState[] {myNewBall1};
	 		
	 	// new BreakoutState
		BreakoutState myNewBreakoutState1 = new BreakoutState(myNewBalls1,myBlocks,myBottomRight, myPaddle);

	    Assert.assertArrayEquals(myNewBalls1,myNewBreakoutState1.getBalls());
	    Assert.assertEquals(Arrays.asList(myNewBalls1), Arrays.asList(myNewBreakoutState1.getBalls()));
	  
		assertArrayEquals(myBlocks, myBreakoutState.getBlocks());
		assertEquals(myPaddle.getTl(),myBreakoutState.getPaddle().getTl());
		assertTrue(myPaddle.getTl().equals(myBreakoutState.getPaddle().getTl()));
		assertEquals(myBottomRight,myBreakoutState.getBottomRight());
		assertEquals(1, myBreakoutState.getBalls().length);
		
//		[2] After ball touch top of paddle
		
		// new Ball2
		Point myBallTl2 = new Point(6,3);
		Point myBallBr2 = new Point(8,5);
		Vector myBallVelocity2 = new Vector(0,6);
		BallState myBall2 = new BallState(myBallTl2, myBallBr2, myBallVelocity2);
			 	
		BallState myNewBall2 = new BallState(myBallTl2.plus(myBallVelocity2), myBallBr2.plus(myBallVelocity2), new Vector(myBallVelocity2.getX()+myPaddle.getVelocity()/5,-myBallVelocity2.getY()));
			 		                                //tl(6,9) br(8,11) velocity(0,-6)
		BallState[] myNewBalls2 = new BallState[] {myNewBall2};		
		
		// newBreakoutState2
		BreakoutState myNewBreakoutState2 = new BreakoutState(myNewBalls2,myBlocks,myBottomRight, myPaddle);

		Assert.assertArrayEquals(myNewBalls2,myNewBreakoutState2.getBalls());
	    Assert.assertEquals(Arrays.asList(myNewBalls2), Arrays.asList(myNewBreakoutState2.getBalls()));
	  
	    
//	    [3] After ball touch block
	    // new Ball3
	 	Point myBallTl3 = new Point(0,1);
	 	Point myBallBr3 = new Point(2,3);
	 	Vector myBallVelocity3 = new Vector(0,-6);
	 	BallState myBall3 = new BallState(myBallTl3, myBallBr3, myBallVelocity3);
	 			 	
	 	BallState myNewBall3 = new BallState(myBallTl3.plus(myBallVelocity3), myBallBr3.plus(myBallVelocity3), myBall3.getVelocity().mirrorOver(new Vector(0,1)));
	 			 		                                //tl(0,-5) br(2,-3) velocity(0,6)
//	 	System.out.println(myNewBall3.getVelocity().getX());
	 	BallState[] myNewBalls3 = new BallState[] {myNewBall3};		
	 	
	 	// new blocks
	 	BlockState[] myNewBlocks3 = new BlockState[] {myBlock2};
//	 	System.out.println(myBlock2.getBr().getX());
//	 	System.out.println(myNewBlocks3.length);
	 	
	 	// newBreakoutState3
	 	boolean ballreflectX = false;
	 	
	 	BreakoutState myNewBreakoutState3 = new BreakoutState(myNewBalls3,myBlocks,myBottomRight, myPaddle);

//	 	Assert.assertArrayEquals(myNewBalls3,myNewBreakoutState3.getBalls());
	 	
//	 	Assert.assertArrayEquals(myNewBlocks3,myNewBreakoutState3.getBlocks());
	 	
//	 	Assert.assertEquals(Arrays.asList(myNewBalls3), Arrays.asList(myNewBreakoutState3.getBalls()));
	 	  
	    
//	    [4] move paddleRight
//	    myBreakoutState.movePaddleRight();
//	    GameMap.getWidth();
	    
	    
	    
	    
		
		

	
		
		
		
		
	
	}

}
