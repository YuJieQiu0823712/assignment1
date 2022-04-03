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
	 	BallState[] myNewBalls1_1 = new BallState[] {myNewBall1_1};
	 		
	 	// new BreakoutState1_1
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
		BallState[] myNewBalls1_2 = new BallState[] {myNewBall1_2};
		 		
		// new BreakoutState1_2
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
		BallState[] myNewBalls1_3 = new BallState[] {myNewBall1_3};
			 		
		// new BreakoutState1_3
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
		
//		BallState myNewBall1_4 = new BallState(myBall1_4.getTl().plus(myBall1_4.getVelocity()), myBall1_4.getBr().plus(myBall1_4.getVelocity()),myBall1_4.getVelocity().mirrorOver(Vector.DOWN));
//			 		                                   tl(49997,30003) br(49999,30005) velocity(0,-6)
//	    BallState[] myNewBalls1_4 = new BallState[] {myNewBall1_4};
			 		                                 
		BallState[] myNewBall1_4 = new BallState[] {};
		
		// new BreakoutState1_4
	    BreakoutState myNewBreakoutState1_4 = new BreakoutState(myNewBall1_4,myBlocks,myBottomRight, myPaddle);

		assertArrayEquals(myNewBall1_4,myNewBreakoutState1_4.getBalls());
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
	  
//	    [2_2_a] After ball touch left of paddle
		
		// new Ball2_2_a
		Point myBallTl2_2_a = new Point(3,5);
		Point myBallBr2_2_a = new Point(5,7);
		Vector myBallVelocity2_2_a = new Vector(6,0);
		BallState myBall2_2_a = new BallState(myBallTl2_2_a, myBallBr2_2_a, myBallVelocity2_2_a);
			 	
		BallState myNewBall2_2_a = new BallState(myBallTl2_2_a.plus(myBallVelocity2_2_a), myBallBr2_2_a.plus(myBallVelocity2_2_a), myBallVelocity2_2_a.mirrorOver(Vector.RIGHT));
			 		                                //tl(9,5) br(11,7) velocity(-6,0)
		BallState[] myBalls2_2_a = new BallState[] {myBall2_2_a};		
		BallState[] myNewBalls2_2_a = new BallState[] {myNewBall2_2_a};		
		
		// paddle2_2
		Point myPaddleTl2_2_a = new Point(6,6);
		Point myPaddleBr2_2_a = new Point(8,7);
		int myPaddleVelocity2_2_a = 0 ;
		PaddleState myPaddle2_2_a = new PaddleState(myPaddleTl2_2_a, myPaddleBr2_2_a, myPaddleVelocity2_2_a);
		
		// new BreakoutState2_2
		BreakoutState myBreakoutState2_2_a = new BreakoutState(myBalls2_2_a,myBlocks,myBottomRight,myPaddle2_2_a);
		BreakoutState myNewBreakoutState2_2_a = new BreakoutState(myNewBalls2_2_a,myBlocks,myBottomRight,myPaddle2_2_a);
		
		myBreakoutState2_2_a.tick(myPaddleVelocity2_2_a);
		
		assertArrayEquals(myNewBalls2_2_a,myNewBreakoutState2_2_a.getBalls());
		assertArrayEquals(myBlocks, myNewBreakoutState2_2_a.getBlocks());
		assertEquals(myPaddle2_2_a.getTl(),myNewBreakoutState2_2_a.getPaddle().getTl());
		assertEquals(myBottomRight,myNewBreakoutState2_2_a.getBottomRight());
		
		
//		[2_2_b] After ball touch left of paddle (squeezed)
		// new Ball2_2_b
		Point myBallTl2_2_b = new Point(3,5);
		Point myBallBr2_2_b = new Point(5,7);
		Vector myBallVelocity2_2_b = new Vector(6,6);
		BallState myBall2_2_b = new BallState(myBallTl2_2_b, myBallBr2_2_b, myBallVelocity2_2_b);
			 	
		BallState myNewBall2_2_b = new BallState(new Point(0,myBallTl2_2_b.getY()), new Point(myBallBr2_2_b.getX()-myBallTl2_2_b.getX(),myBallBr2_2_b.getY()), new Vector(0,Math.abs(myBallVelocity2_2_b.getY())));
			 		                                //tl(0,5) br(2,7) velocity(0,6)
		
		BallState[] myNewBalls2_2_b = new BallState[] {myNewBall2_2_b};		
		
		// new paddle2_2_b
		Point myPaddleTl2_2_b = new Point(6,6);
		Point myPaddleBr2_2_b = new Point(8,7);
		int myPaddleVelocity2_2_b = -100 ;
		PaddleState myPaddle2_2_b = new PaddleState(myPaddleTl2_2_b, myPaddleBr2_2_b, myPaddleVelocity2_2_b);
		PaddleState myNewPaddle2_2_b = new PaddleState(myPaddleTl2_2_b.plus(new Vector(myPaddleVelocity2_2_b,0)), myPaddleBr2_2_b.plus(new Vector(myPaddleVelocity2_2_b,0)), myPaddleVelocity2_2_b);
		                                               //tl(-94,6) br(-92,7) velocity(-100,0)
		// new BreakoutState2_2_b
		BreakoutState myNewBreakoutState2_2_b = new BreakoutState(myNewBalls2_2_b,myBlocks,myBottomRight,myPaddle2_2_b);

		
		assertArrayEquals(myNewBalls2_2_b,myNewBreakoutState2_2_b.getBalls());
		assertArrayEquals(myBlocks, myNewBreakoutState2_2_b.getBlocks());
		assertEquals(myPaddle2_2_b.getTl(),myNewBreakoutState2_2_b.getPaddle().getTl());
		assertEquals(myBottomRight,myNewBreakoutState2_2_b.getBottomRight());
		
	    
//	    [3] After ball touch block
	    // new Ball3
	 	Point myBallTl3 = new Point(0,2);
	 	Point myBallBr3 = new Point(2,4);
	 	Vector myBallVelocity3 = new Vector(0,-6);
	 	BallState myBall3 = new BallState(myBallTl3, myBallBr3, myBallVelocity3);
	 			 	
	 	BallState myNewBall3 = new BallState(myBallTl3.plus(myBallVelocity3), myBallBr3.plus(myBallVelocity3), myBallVelocity3.mirrorOver(Vector.DOWN));
	 			 		                                //tl(0,-2) br(2,0) velocity(0,6)
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

		
		
//		assertFalse(myBreakoutState.isWon());
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
