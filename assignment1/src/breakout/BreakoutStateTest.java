package breakout;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
		int paddleDir1 = 0;
		myBreakoutState.tick(paddleDir1);
		assertEquals(myPaddle.getTl(),myBreakoutState.getPaddle().getTl());
		assertEquals(myPaddle.getBr(),myBreakoutState.getPaddle().getBr());
		assertEquals(myPaddle.getVelocity()*paddleDir1,myBreakoutState.getPaddle().getVelocity());
		
//	    [1_1] After ball touch GameMapLeft 
	    // new Ball1_1
	 	Point myBallTl1_1 = new Point(3,3);
	 	Point myBallBr1_1 = new Point(5,5);
	 	Vector myBallVelocity1_1 = new Vector(-6,0);
	 	BallState myBall1_1 = new BallState(myBallTl1_1, myBallBr1_1, myBallVelocity1_1);
	 	
	 	BallState myNewBall1_1 = new BallState(myBall1_1.getTl().plus(myBall1_1.getVelocity()), myBall1_1.getBr().plus(myBall1_1.getVelocity()), myBallVelocity1_1.mirrorOver(Vector.RIGHT));
	 		                                   //tl(-3,3) br(-1,5) velocity(6,0)
	 	BallState[] myBalls1_1 = new BallState[] {myBall1_1};
	 	BallState[] myNewBalls1_1 = new BallState[] {myNewBall1_1};
	 		
	 	// new BreakoutState1_1
	 	BreakoutState myBreakoutState1_1 = new BreakoutState(myBalls1_1,myBlocks,myBottomRight, myPaddle);
	 	myBreakoutState1_1.tick(paddleDir1);
		BreakoutState myNewBreakoutState1_1 = new BreakoutState(myNewBalls1_1,myBlocks,myBottomRight, myPaddle);

	    assertArrayEquals(myNewBalls1_1,myNewBreakoutState1_1.getBalls());
	    assertEquals(Arrays.asList(myNewBalls1_1), Arrays.asList(myNewBreakoutState1_1.getBalls()));
	  
		assertArrayEquals(myBlocks, myNewBreakoutState1_1.getBlocks());
		assertEquals(myPaddle.getTl(),myNewBreakoutState1_1.getPaddle().getTl());
		assertTrue(myPaddle.getTl().equals(myNewBreakoutState1_1.getPaddle().getTl()));
		assertEquals(myBottomRight,myNewBreakoutState1_1.getBottomRight());
		assertEquals(1, myNewBreakoutState1_1.getBalls().length);
		
//		[1_2] After ball touch GameMapRight 
		// new Ball1_2
		Point myBallTl1_2 = new Point(GameMap.getWidth()-3,GameMap.getHeight()-3);
		Point myBallBr1_2 = new Point(GameMap.getWidth()-1,GameMap.getHeight()-1);
		Vector myBallVelocity1_2 = new Vector(6,0);
		BallState myBall1_2 = new BallState(myBallTl1_2, myBallBr1_2, myBallVelocity1_2);	
		BallState myNewBall1_2 = new BallState(myBall1_2.getTl().plus(myBall1_2.getVelocity()), myBall1_2.getBr().plus(myBall1_2.getVelocity()),myBall1_2.getVelocity().mirrorOver(Vector.RIGHT));
		 		                                   //tl(50005,29999) br(50003,29997) velocity(-6,0)
		BallState[] myBalls1_2 = new BallState[] {myBall1_2};
		BallState[] myNewBalls1_2 = new BallState[] {myNewBall1_2};
		 		
		// new BreakoutState1_2
		BreakoutState myBreakoutState1_2 = new BreakoutState(myBalls1_2,myBlocks,myBottomRight, myPaddle);
		myBreakoutState1_2.tick(paddleDir1);
		BreakoutState myNewBreakoutState1_2 = new BreakoutState(myNewBalls1_2,myBlocks,myBottomRight, myPaddle);

		assertArrayEquals(myNewBalls1_2,myNewBreakoutState1_2.getBalls());
		assertArrayEquals(myBlocks, myNewBreakoutState1_2.getBlocks());
		assertEquals(myPaddle.getTl(),myNewBreakoutState1_2.getPaddle().getTl());
		assertEquals(myBottomRight,myNewBreakoutState1_2.getBottomRight());
		
//		[1_3] After ball touch GameMapTop 
		// new Ball1_3
		Point myBallTl1_3 = new Point(GameMap.getWidth()-3,1);
		Point myBallBr1_3 = new Point(GameMap.getWidth()-1,3);
		Vector myBallVelocity1_3 = new Vector(0,-6);
		BallState myBall1_3 = new BallState(myBallTl1_3, myBallBr1_3, myBallVelocity1_3);	
		BallState myNewBall1_3 = new BallState(myBall1_3.getTl().plus(myBall1_3.getVelocity()), myBall1_3.getBr().plus(myBall1_3.getVelocity()),myBall1_3.getVelocity().mirrorOver(Vector.DOWN));
			 		                                   //tl(49997,-5) br(49999,-3) velocity(0,6)
		BallState[] myBalls1_3 = new BallState[] {myBall1_3};
		BallState[] myNewBalls1_3 = new BallState[] {myNewBall1_3};
			 		
		// new BreakoutState1_3
		BreakoutState myBreakoutState1_3 = new BreakoutState(myBalls1_3,myBlocks,myBottomRight, myPaddle);
		myBreakoutState1_3.tick(paddleDir1);
	    BreakoutState myNewBreakoutState1_3 = new BreakoutState(myNewBalls1_3,myBlocks,myBottomRight, myPaddle);

		assertArrayEquals(myNewBalls1_3,myNewBreakoutState1_3.getBalls());
		assertArrayEquals(myBlocks, myNewBreakoutState1_3.getBlocks());
		assertEquals(myPaddle.getTl(),myNewBreakoutState1_3.getPaddle().getTl());
		assertEquals(myBottomRight,myNewBreakoutState1_3.getBottomRight());
			
//		[1_4] After ball touch GameMapBottom 
		// new Ball1_4
		Point myBallTl1_4 = new Point(GameMap.getWidth()-3,GameMap.getHeight()-3);
		Point myBallBr1_4 = new Point(GameMap.getWidth()-1,GameMap.getHeight()-1);
		Vector myBallVelocity1_4 = new Vector(0,6);
		BallState myBall1_4 = new BallState(myBallTl1_4, myBallBr1_4, myBallVelocity1_4);	
		
		BallState myNewBall1_4 = new BallState(myBall1_4.getTl().plus(myBall1_4.getVelocity()), myBall1_4.getBr().plus(myBall1_4.getVelocity()),myBall1_4.getVelocity().mirrorOver(Vector.DOWN));
			 		                                   //tl(49997,30003) br(49999,30005) velocity(0,-6)
		BallState[] myBalls1_4 = new BallState[] {myBall1_4};
		BallState[] myNewBalls1_4 = new BallState[] {myNewBall1_4};
			 		                                 
		BallState[] myEmptyBall1_4 = new BallState[] {};
		
		// new BreakoutState1_4
		BreakoutState myBreakoutState1_4 = new BreakoutState(myBalls1_4,myBlocks,myBottomRight, myPaddle);
		myBreakoutState1_4.tick(paddleDir1);
	    BreakoutState myNewBreakoutState1_4 = new BreakoutState(myEmptyBall1_4,myBlocks,myBottomRight, myPaddle);

		assertArrayEquals(myEmptyBall1_4,myNewBreakoutState1_4.getBalls());
		assertArrayEquals(myBlocks, myNewBreakoutState1_4.getBlocks());
		assertEquals(myPaddle.getTl(),myNewBreakoutState1_4.getPaddle().getTl());
		assertEquals(myBottomRight,myNewBreakoutState1_4.getBottomRight());
		
			 
//		[2_1] After ball touch top of paddle
		// new Ball2_1
		Point myBallTl2_1 = new Point(6,3);
		Point myBallBr2_1 = new Point(8,5);
		Vector myBallVelocity2_1 = new Vector(0,6);
		BallState myBall2_1 = new BallState(myBallTl2_1, myBallBr2_1, myBallVelocity2_1);
			 	
		BallState myNewBall2_1 = new BallState(myBallTl2_1.plus(myBallVelocity2_1), myBallBr2_1.plus(myBallVelocity2_1), new Vector(myBallVelocity2_1.getX()+myPaddle.getVelocity()/5,-myBallVelocity2_1.getY()));
			 		                                //tl(6,9) br(8,11) velocity(0,-6)
		BallState[] myNewBalls2_1 = new BallState[] {myNewBall2_1};		
		
		// new BreakoutState2_1
		BreakoutState myNewBreakoutState2_1 = new BreakoutState(myNewBalls2_1,myBlocks,myBottomRight,myPaddle);

		assertArrayEquals(myNewBalls2_1,myNewBreakoutState2_1.getBalls());
		assertArrayEquals(myBlocks, myNewBreakoutState2_1.getBlocks());
		assertEquals(myPaddle.getTl(),myNewBreakoutState2_1.getPaddle().getTl());
		assertEquals(myBottomRight,myNewBreakoutState2_1.getBottomRight());
	  
//	    [2_2_a] After ball touch right of paddle //not yet just change name
		
		// new Ball2_2_a
		Point myBallTl2_2_a = new Point(3,5);
		Point myBallBr2_2_a = new Point(5,7);
		Vector myBallVelocity2_2_a = new Vector(6,0);
		BallState myBall2_2_a = new BallState(myBallTl2_2_a, myBallBr2_2_a, myBallVelocity2_2_a);
			 	
		BallState myNewBall2_2_a = new BallState(myBallTl2_2_a.plus(myBallVelocity2_2_a), myBallBr2_2_a.plus(myBallVelocity2_2_a), myBallVelocity2_2_a.mirrorOver(Vector.RIGHT));
			 		                                //tl(9,5) br(11,7) velocity(-6,0)
		BallState[] myBalls2_2_a = new BallState[] {myBall2_2_a};		
		BallState[] myNewBalls2_2_a = new BallState[] {myNewBall2_2_a};		
		
		// paddle2_2_a
		Point myPaddleTl2_2_a = new Point(6,6);
		Point myPaddleBr2_2_a = new Point(8,7);
		int myPaddleVelocity2_2_a = 0 ;
		PaddleState myPaddle2_2_a = new PaddleState(myPaddleTl2_2_a, myPaddleBr2_2_a, myPaddleVelocity2_2_a);
		
		// new BreakoutState2_2_a
		BreakoutState myBreakoutState2_2_a = new BreakoutState(myBalls2_2_a,myBlocks,myBottomRight,myPaddle2_2_a);
		BreakoutState myNewBreakoutState2_2_a = new BreakoutState(myNewBalls2_2_a,myBlocks,myBottomRight,myPaddle2_2_a);
		
		myBreakoutState2_2_a.tick(myPaddleVelocity2_2_a);
		
		assertArrayEquals(myNewBalls2_2_a,myNewBreakoutState2_2_a.getBalls());
		assertArrayEquals(myBlocks, myNewBreakoutState2_2_a.getBlocks());
		assertEquals(myPaddle2_2_a.getTl(),myNewBreakoutState2_2_a.getPaddle().getTl());
		assertEquals(myBottomRight,myNewBreakoutState2_2_a.getBottomRight());
		
		
//		[2_2_b] After ball touch right of paddle (squeezed) // red ?// not yet
		// new Ball2_2_b
		Point myBallTl2_2_b = new Point(3,5);
		Point myBallBr2_2_b = new Point(5,7);
		Vector myBallVelocity2_2_b = new Vector(6,0);
		BallState myBall2_2_b = new BallState(myBallTl2_2_b, myBallBr2_2_b, myBallVelocity2_2_b);
			 	
		BallState myNewBall2_2_b = new BallState(new Point(0,myBallTl2_2_b.getY()), new Point(myBallBr2_2_b.getX()-myBallTl2_2_b.getX(),myBallBr2_2_b.getY()), new Vector(0,Math.abs(myBallVelocity2_2_b.getY())));
			 		                                //tl(0,5) br(2,7) velocity(0,0)
		BallState[] myBalls2_2_b = new BallState[] {myBall2_2_b};	
		BallState[] myNewBalls2_2_b = new BallState[] {myNewBall2_2_b};		
		
		// new paddle2_2_b
		Point myPaddleTl2_2_b = new Point(6,6);
		Point myPaddleBr2_2_b = new Point(8,7);
		int myPaddleVelocity2_2_b = -100 ;
		PaddleState myPaddle2_2_b = new PaddleState(myPaddleTl2_2_b, myPaddleBr2_2_b, myPaddleVelocity2_2_b);
		PaddleState myNewPaddle2_2_b = new PaddleState(myPaddleTl2_2_b.plus(new Vector(myPaddleVelocity2_2_b,0)), myPaddleBr2_2_b.plus(new Vector(myPaddleVelocity2_2_b,0)), myPaddleVelocity2_2_b);
		                                               //tl(-94,6) br(-92,7) velocity(-100,0)
		// new BreakoutState2_2_b
		BreakoutState myBreakoutState2_2_b = new BreakoutState(myBalls2_2_b,myBlocks,myBottomRight,myPaddle2_2_b);
		myBreakoutState2_2_b.movePaddleLeft();
		BreakoutState myNewBreakoutState2_2_b = new BreakoutState(myNewBalls2_2_b,myBlocks,myBottomRight,myNewPaddle2_2_b);

		
		assertArrayEquals(myNewBalls2_2_b,myNewBreakoutState2_2_b.getBalls());
		assertArrayEquals(myBlocks, myNewBreakoutState2_2_b.getBlocks());
		assertEquals(myNewPaddle2_2_b.getTl(),myNewBreakoutState2_2_b.getPaddle().getTl());
		assertEquals(myBottomRight,myNewBreakoutState2_2_b.getBottomRight());
		
//	    [2_3_a] After ball touch left of paddle
		
		// new Ball2_3_a
		Point myBallTl2_3_a = new Point(3,5);
		Point myBallBr2_3_a = new Point(5,7);
		Vector myBallVelocity2_3_a = new Vector(6,0);
		BallState myBall2_3_a = new BallState(myBallTl2_3_a, myBallBr2_3_a, myBallVelocity2_3_a);
			 	
		BallState myNewBall2_3_a = new BallState(myBallTl2_3_a.plus(myBallVelocity2_3_a), myBallBr2_3_a.plus(myBallVelocity2_3_a), myBallVelocity2_3_a.mirrorOver(Vector.RIGHT));
			 		                                //tl(9,5) br(11,7) velocity(-6,0)
		BallState[] myBalls2_3_a = new BallState[] {myBall2_3_a};		
		BallState[] myNewBalls2_3_a = new BallState[] {myNewBall2_3_a};		
		
		// paddle2_3_a
		Point myPaddleTl2_3_a = new Point(6,6);
		Point myPaddleBr2_3_a = new Point(8,7);
		int myPaddleVelocity2_3_a = 0 ;
		PaddleState myPaddle2_3_a = new PaddleState(myPaddleTl2_3_a, myPaddleBr2_3_a, myPaddleVelocity2_3_a);
		
		// new BreakoutState2_3_a
		BreakoutState myBreakoutState2_3_a = new BreakoutState(myBalls2_3_a,myBlocks,myBottomRight,myPaddle2_3_a);
		BreakoutState myNewBreakoutState2_3_a = new BreakoutState(myNewBalls2_3_a,myBlocks,myBottomRight,myPaddle2_3_a);
		
		myBreakoutState2_3_a.tick(myPaddleVelocity2_3_a);
		
		assertArrayEquals(myNewBalls2_3_a,myNewBreakoutState2_3_a.getBalls());
		assertArrayEquals(myBlocks, myNewBreakoutState2_3_a.getBlocks());
		assertEquals(myPaddle2_3_a.getTl(),myNewBreakoutState2_3_a.getPaddle().getTl());
		assertEquals(myBottomRight,myNewBreakoutState2_3_a.getBottomRight());
		
		
//		[2_3_b] After ball touch left of paddle (squeezed) // red ? 
		// new Ball2_3_b
		Point myBallTl2_3_b = new Point(3,5);
		Point myBallBr2_3_b = new Point(5,7);
		Vector myBallVelocity2_3_b = new Vector(6,0);
		BallState myBall2_3_b = new BallState(myBallTl2_3_b, myBallBr2_3_b, myBallVelocity2_3_b);
			 	
		BallState myNewBall2_3_b = new BallState(new Point(0,myBallTl2_3_b.getY()), new Point(myBallBr2_3_b.getX()-myBallTl2_3_b.getX(),myBallBr2_3_b.getY()), new Vector(0,Math.abs(myBallVelocity2_3_b.getY())));
			 		                                //tl(0,5) br(2,7) velocity(0,0)
		BallState[] myBalls2_3_b = new BallState[] {myBall2_3_b};	
		BallState[] myNewBalls2_3_b = new BallState[] {myNewBall2_3_b};		
		
		// new paddle2_3_b
		Point myPaddleTl2_3_b = new Point(6,6);
		Point myPaddleBr2_3_b = new Point(8,7);
		int myPaddleVelocity2_3_b = -100 ;
		PaddleState myPaddle2_3_b = new PaddleState(myPaddleTl2_3_b, myPaddleBr2_3_b, myPaddleVelocity2_3_b);
		PaddleState myNewPaddle2_3_b = new PaddleState(myPaddleTl2_3_b.plus(new Vector(myPaddleVelocity2_3_b,0)), myPaddleBr2_3_b.plus(new Vector(myPaddleVelocity2_3_b,0)), myPaddleVelocity2_3_b);
		  											//tl(-94,6) br(-92,7) velocity(-100,0)
		// new BreakoutState2_3_b
		BreakoutState myBreakoutState2_3_b = new BreakoutState(myBalls2_3_b,myBlocks,myBottomRight,myPaddle2_3_b);
		myBreakoutState2_3_b.movePaddleLeft();
		BreakoutState myNewBreakoutState2_3_b = new BreakoutState(myNewBalls2_3_b,myBlocks,myBottomRight,myNewPaddle2_3_b);

		
		assertArrayEquals(myNewBalls2_3_b,myNewBreakoutState2_3_b.getBalls());
		assertArrayEquals(myBlocks, myNewBreakoutState2_3_b.getBlocks());
		assertEquals(myNewPaddle2_3_b.getTl(),myNewBreakoutState2_3_b.getPaddle().getTl());
		assertEquals(myBottomRight,myNewBreakoutState2_3_b.getBottomRight());
		
		
		
//	    [3_1_a] After ball touch one block bottom
	    // new Ball3_1_a
	 	Point myBallTl3_1_a = new Point(0,2);
	 	Point myBallBr3_1_a = new Point(2,4);
	 	Vector myBallVelocity3_1_a = new Vector(0,-6);
	 	BallState myBall3_1_a = new BallState(myBallTl3_1_a, myBallBr3_1_a, myBallVelocity3_1_a);
	 			 	
	 	BallState myNewBall3_1_a = new BallState(myBallTl3_1_a.plus(myBallVelocity3_1_a), myBallBr3_1_a.plus(myBallVelocity3_1_a), myBallVelocity3_1_a.mirrorOver(Vector.DOWN));
	 			 		                                //tl(0,-4) br(2,-2) velocity(0,6)
	 	BallState[] myBalls3_1_a = new BallState[] {myBall3_1_a};	
	 	BallState[] myNewBalls3_1_a = new BallState[] {myNewBall3_1_a};		
	 	
	 	// new Blocks3_1_a
	 	BlockState[] myBlocks3_1_a = new BlockState[] {myBlock1,myBlock2};
	 	BlockState[] myNewBlocks3_1_a = new BlockState[] {myBlock2};
	 	
	 	// new BreakoutState3_1_a
	 	BreakoutState myBreakoutState3_1_a = new BreakoutState(myBalls3_1_a,myBlocks3_1_a,myBottomRight, myPaddle);
	 	myBreakoutState3_1_a.tick(paddleDir1);
	 	BreakoutState myNewBreakoutState3_1_a = new BreakoutState(myNewBalls3_1_a,myNewBlocks3_1_a,myBottomRight, myPaddle);

	 	assertArrayEquals(myNewBalls3_1_a,myNewBreakoutState3_1_a.getBalls());
	    assertArrayEquals(myNewBlocks3_1_a,myNewBreakoutState3_1_a.getBlocks());
	 	assertEquals(Arrays.asList(myNewBalls3_1_a), Arrays.asList(myNewBreakoutState3_1_a.getBalls()));
	 	
	 	
//	    [3_1_b] After ball touch one block right
	    // new Ball3_1_b
	 	Point myBallTl3_1_b = new Point(3,0);
	 	Point myBallBr3_1_b = new Point(5,2);
	 	Vector myBallVelocity3_1_b = new Vector(-6,0);
	 	BallState myBall3_1_b = new BallState(myBallTl3_1_b, myBallBr3_1_b, myBallVelocity3_1_b);
	 			 	
	 	BallState myNewBall3_1_b = new BallState(myBallTl3_1_b.plus(myBallVelocity3_1_b), myBallBr3_1_b.plus(myBallVelocity3_1_b), myBallVelocity3_1_b.mirrorOver(Vector.RIGHT));
	 			 		                                //tl(-3,0) br(-1,2) velocity(6,0)
	 	BallState[] myBalls3_1_b = new BallState[] {myBall3_1_b};	
	 	BallState[] myNewBalls3_1_b = new BallState[] {myNewBall3_1_b};		
	 	
	 	// new Blocks3
	 	BlockState[] myBlocks3_1_b = new BlockState[] {myBlock1};
	 	BlockState[] myEmptyBlocks3_1_b = new BlockState[] {};
	 	
	 	// new BreakoutState3
	 	BreakoutState myBreakoutState3_1_b = new BreakoutState(myBalls3_1_b,myBlocks3_1_b,myBottomRight, myPaddle);
	 	myBreakoutState3_1_b.tick(paddleDir1);
	 	BreakoutState myNewBreakoutState3_1_b = new BreakoutState(myNewBalls3_1_b,myEmptyBlocks3_1_b,myBottomRight, myPaddle);

	 	assertArrayEquals(myNewBalls3_1_b,myNewBreakoutState3_1_b.getBalls());
	    assertArrayEquals(myEmptyBlocks3_1_b,myNewBreakoutState3_1_b.getBlocks());
	 	
	 	
	 	
//	 	[3_2] After ball touch two block
	    // new Ball3_2
	 	Point myBallTl3_2 = new Point(1,2);
	 	Point myBallBr3_2 = new Point(3,4);
	 	Vector myBallVelocity3_2 = new Vector(0,-6);
	 	BallState myBall3_2 = new BallState(myBallTl3_2, myBallBr3_2, myBallVelocity3_2);
	 			 	
	 	BallState myNewBall3_2 = new BallState(myBallTl3_2.plus(myBallVelocity3_2), myBallBr3_2.plus(myBallVelocity3_2), myBallVelocity3_2.mirrorOver(Vector.DOWN));
	 			 		                                //tl(1,-4) br(3,-2) velocity(0,6)
	 	BallState[] myBalls3_2 = new BallState[] {myBall3_2};	
	 	BallState[] myNewBalls3_2 = new BallState[] {myNewBall3_2};		
	 	
	 	// new Blocks3_2
		BlockState[] myBlocks3_2 = new BlockState[] {myBlock1,myBlock2};
	 	BlockState[] myNewBlocks3_2 = new BlockState[] {};
	 	
	 	// new BreakoutState3_2
	 	BreakoutState myBreakoutState3_2 = new BreakoutState(myBalls3_2,myBlocks3_2,myBottomRight, myPaddle);
	 	myBreakoutState3_2.tick(paddleDir1);
//	 	BlockState myBlock11 = (BlockState)Array.get(myBlocks3_2, 0);
	 	BreakoutState myNewBreakoutState3_2 = new BreakoutState(myNewBalls3_2,myNewBlocks3_2,myBottomRight, myPaddle);

	 	assertArrayEquals(myNewBalls3_2,myNewBreakoutState3_2.getBalls());
	 	assertArrayEquals(myNewBlocks3_2,myNewBreakoutState3_2.getBlocks());
	 	assertEquals(Arrays.asList(myNewBalls3_2), Arrays.asList(myNewBreakoutState3_2.getBalls()));
	 	assertEquals(myBlock1.getTl().getY(),myBlock2.getTl().getY());//??red 
	 	
	 	
//	 	[3_3] After ball touch three block
	    // new Ball3_3
	 	Point myBallTl3_3 = new Point(4,3);
	 	Point myBallBr3_3 = new Point(6,5);
	 	Vector myBallVelocity3_3 = new Vector(-6,-6);
	 	Vector myNewBallVelocity3_3 = new Vector (-myBallVelocity3_3.getX(),-myBallVelocity3_3.getY());
	 	BallState myBall3_3 = new BallState(myBallTl3_3, myBallBr3_3, myBallVelocity3_3);
	 			 	
	 	BallState myNewBall3_3 = new BallState(myBallTl3_3.plus(myBallVelocity3_3), myBallBr3_3.plus(myBallVelocity3_3),myNewBallVelocity3_3);
	 			 		                                //tl(-2,-3) br(0,-1) velocity(6,6)
	 	
	 	BallState[] myBalls3_3 = new BallState[] {myBall3_3};	
	 	BallState[] myNewBalls3_3 = new BallState[] {myNewBall3_3};		
	 	
	 	// new Blocks3_3
	 	Point myBlockTl3 = new Point(0,1);
		Point myBlockBr3= new Point(2,2);
		BlockState myBlock3 = new BlockState(myBlockTl1, myBlockBr1);
		
		BlockState[] myBlocks3_3 = new BlockState[] {myBlock1,myBlock2,myBlock3};
	 	BlockState[] myNewBlocks3_3 = new BlockState[] {};
	 	
	 	// new BreakoutState3_3
	 	BreakoutState myBreakoutState3_3 = new BreakoutState(myBalls3_3,myBlocks3_3,myBottomRight, myPaddle);
	 	myBreakoutState3_3.tick(paddleDir1);
	 	BreakoutState myNewBreakoutState3_3 = new BreakoutState(myNewBalls3_3,myNewBlocks3_3,myBottomRight, myPaddle);

	 	assertArrayEquals(myNewBalls3_3,myNewBreakoutState3_3.getBalls());
	 	assertArrayEquals(myNewBlocks3_3,myNewBreakoutState3_3.getBlocks());
	 	
	   
	 	
	 	
//	    [4] movePaddleRight
	    myBreakoutState.movePaddleRight();
	    
	    // new paddle4
		PaddleState myNewPaddle4 = new PaddleState(myPaddleTl.plus(new Vector(myPaddleVelocity,0)), myPaddleBr.plus(new Vector(myPaddleVelocity,0)), myPaddleVelocity);
		// new BreakoutState4
		BreakoutState myNewBreakoutState4 = new BreakoutState(myNewBalls3_1_a,myNewBlocks3_1_a,myBottomRight, myNewPaddle4);
		
		Assert.assertEquals(myNewPaddle4.getTl(),myNewBreakoutState4.getPaddle().getTl());
		

//		[5] movePaddleLeft
		myBreakoutState.movePaddleLeft();
		
		// new paddle5
		PaddleState myNewPaddle5 = new PaddleState(myPaddleTl.plus(new Vector(-myPaddleVelocity,0)), myPaddleBr.plus(new Vector(-myPaddleVelocity,0)), myPaddleVelocity);
	   
		// new BreakoutState5
		BreakoutState myNewBreakoutState5 = new BreakoutState(myNewBalls3_1_a,myNewBlocks3_1_a,myBottomRight, myNewPaddle5);
		
		Assert.assertEquals(myNewPaddle5.getTl(),myNewBreakoutState5.getPaddle().getTl());
		
		
//		[6] isWon
		
		BlockState[] emptyBlocks = new BlockState[] {};
		
		// new BreakoutState6_1
		BreakoutState myBreakoutState6_1 = new BreakoutState(myBalls,emptyBlocks,myBottomRight, myPaddle);
		assertEquals(1,myBreakoutState6_1.getBalls().length);
		assertEquals(0,myBreakoutState6_1.getBlocks().length);
	
		assertTrue(myBreakoutState6_1.isWon());
		
		// new BreakoutState6_2
		BreakoutState myBreakoutState6_2 = new BreakoutState(myBalls,myBlocks,myBottomRight, myPaddle);
								
		assertEquals(1,myBreakoutState6_2.getBalls().length);
		assertEquals(2,myBreakoutState6_2.getBlocks().length);
		assertFalse(myBreakoutState6_2.isWon()); 
		
		
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
		BallState[] emptyBalls = new BallState[] {};
		
		// new BreakoutState7_1
		BreakoutState myBreakoutState7_1 = new BreakoutState(emptyBalls,myBlocks,myBottomRight, myPaddle);

		assertEquals(0,myBreakoutState7_1.getBalls().length);
		assertTrue(myBreakoutState7_1.isDead());
		
		// new BreakoutState7_2
		BreakoutState myBreakoutState7_2 = new BreakoutState(myNewBalls7,myBlocks,myBottomRight, myPaddle);
						
		assertEquals(1,myBreakoutState7_2.getBalls().length);
		assertFalse(myBreakoutState7_2.isDead()); 
				
 
		
		

		
		
		
		
		
		
	    
	    
	    
	    
		
		

	
		
		
		
		
	
	}

}
