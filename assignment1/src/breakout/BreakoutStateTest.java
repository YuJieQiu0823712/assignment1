package breakout;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import stringClass.String;

class BreakoutStateTest {

	@Test
	void test() {
		// ball
		Point myBallTl = new Point(3,3);
		Point myBallBr = new Point(5,5);
		Vector myBallVelocity = new Vector(5,7);
		BallState myBall = new BallState(myBallTl, myBallBr, myBallVelocity);
		
//		Point myBallTl11 = new Point(33,33);
//		Point myBallBr11 = new Point(55,55);
//		Vector myBallVelocity11 = new Vector(5,7);
//		BallState myBall11 = new BallState(myBallTl11, myBallBr11, myBallVelocity11);
		
		BallState[] myBalls = new BallState[] {myBall};
//		BallState[] myBalls11 = new BallState[] {myBall,myBall11};
		
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
	 	
	 	BallState myNewBall1 = new BallState(myBall1.getTl().plus(myBall1.getVelocity()), myBall1.getBr().plus(myBall1.getVelocity()), new Vector(-myBall1.getVelocity().getX(),myBall1.getVelocity().getY()));
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
		
		// new BreakoutState2
		BreakoutState myNewBreakoutState2 = new BreakoutState(myNewBalls2,myBlocks,myBottomRight, myPaddle);

		Assert.assertArrayEquals(myNewBalls2,myNewBreakoutState2.getBalls());
	    Assert.assertEquals(Arrays.asList(myNewBalls2), Arrays.asList(myNewBreakoutState2.getBalls()));
	  
	    
//	    [3] After ball touch block
	    // new Ball3
	 	Point myBallTl3 = new Point(0,2);
	 	Point myBallBr3 = new Point(2,4);
	 	Vector myBallVelocity3 = new Vector(0,-4);
	 	BallState myBall3 = new BallState(myBallTl3, myBallBr3, myBallVelocity3);
	 			 	
	 	BallState myNewBall3 = new BallState(myBallTl3.plus(myBallVelocity3), myBallBr3.plus(myBallVelocity3), myBall3.getVelocity().mirrorOver(new Vector(0,1)));
	 			 		                                //tl(0,-2) br(2,0) velocity(0,4)
	 	BallState[] myNewBalls3 = new BallState[] {myNewBall3};		
	 	
	 	// new Blocks3
	 	BlockState[] myNewBlocks3 = new BlockState[] {myBlock2};
	 	
	 	// new BreakoutState3
	 	BreakoutState myNewBreakoutState3 = new BreakoutState(myNewBalls3,myNewBlocks3,myBottomRight, myPaddle);

	 	Assert.assertArrayEquals(myNewBalls3,myNewBreakoutState3.getBalls());
	 	Assert.assertArrayEquals(myNewBlocks3,myNewBreakoutState3.getBlocks());
	 	Assert.assertEquals(Arrays.asList(myNewBalls3), Arrays.asList(myNewBreakoutState3.getBalls()));
	 	  
	    
//	    [4] movePaddleRight
	    myBreakoutState.movePaddleRight();
	    
	    // new paddle4
		PaddleState myNewPaddle4 = new PaddleState(myPaddleTl.plus(new Vector(myPaddleVelocity,0)), myPaddleBr.plus(new Vector(myPaddleVelocity,0)), myPaddleVelocity);
		// new BreakoutState4
		BreakoutState myNewBreakoutState4 = new BreakoutState(myNewBalls3,myNewBlocks3,myBottomRight, myNewPaddle4);
		
		Assert.assertEquals(myNewPaddle4.getTl(),myNewBreakoutState4.getPaddle().getTl());
		

//		[5] movePaddleLeft
		myBreakoutState.movePaddleLeft();
		
		// new paddle5
		PaddleState myNewPaddle5 = new PaddleState(myPaddleTl.plus(new Vector(-myPaddleVelocity,0)), myPaddleBr.plus(new Vector(-myPaddleVelocity,0)), myPaddleVelocity);
	   
		// new BreakoutState5
		BreakoutState myNewBreakoutState5 = new BreakoutState(myNewBalls3,myNewBlocks3,myBottomRight, myNewPaddle5);
		
		Assert.assertEquals(myNewPaddle5.getTl(),myNewBreakoutState5.getPaddle().getTl());
		
		
//		[6] isWon
		assertEquals(1,myBreakoutState.getBalls().length);
		assertEquals(2,myBreakoutState.getBlocks().length);
		
		assertFalse(myBreakoutState.isWon());
//		assertSame(myBreakoutState.isWon(),false);
//		assertEquals(false,myBreakoutState.isWon());
//		System.out.println(myBreakoutState.isWon());
		
//		[7] isDead
		
        // new ball7
		Point myBallTl7 = new Point(GameMap.getWidth()/2-2,GameMap.getHeight()-1-2);
		Point myBallBr7 = new Point(GameMap.getWidth()/2,GameMap.getHeight()-1);
		Vector myBallVelocity7 = new Vector(0,4);
		BallState myBall7 = new BallState(myBallTl7, myBallBr7, myBallVelocity7);
		                                     //tl(24998,29997) br(25000,29999) velocity(0,4)
		BallState myNewBall7 = new BallState(myBallTl7.plus(myBallVelocity7), myBallBr7.plus(myBallVelocity7), myBall7.getVelocity().mirrorOver(new Vector(0,-1)));
		                                    //tl(24998,30001) br(25000,30003) velocity(0,-4)
		BallState[] myNewBalls7 = new BallState[] {myNewBall7} ;
		
//		BallState[] EMPTY = new BallState[] {};

//		BreakoutState myBreakoutState7 = new BreakoutState(EMPTY,myBlocks,myBottomRight, myPaddle);
//		assertEquals(0,myBreakoutState7.getBalls().length);

		
//		ArrayList<BallState> newballs = new ArrayList<BallState>();
//		if(myNewBall7.getBr().getY()<GameMap.getHeight()) {
//			myNewBalls7.
//		}
//		BallState[] myNewBalls7 = new BallState[] {myNewBall7};
		
//		assertFalse(myBreakoutState.isDead());
		
		
		
		
	    
	    
	    
	    
		
		

	
		
		
		
		
	
	}

}
