
package breakout;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;



class BreakoutStateTest {

	@Test
	void test() {
		// ball
		Point myBallTl = new Point(22000,18000);
		Point myBallBr = new Point(22700,18700);
		Vector myBallVelocity = new Vector(5,7);
		BallState myBall = new BallState(myBallTl, myBallBr, myBallVelocity);

		BallState[] myBalls = new BallState[] {myBall};

		// block
		Point myBlockTl1 = new Point(0,0);
		Point myBlockBr1 = new Point(5000,3000);
		BlockState myBlock1 = new BlockState(myBlockTl1, myBlockBr1);
		Point myBlockTl2 = new Point(5000,0);
		Point myBlockBr2 = new Point(10000,3000);
		BlockState myBlock2 = new BlockState(myBlockTl2, myBlockBr2);

		BlockState[] myBlocks = new BlockState[] {myBlock1,myBlock2};

		// paddle
		Point myPaddleTl = new Point(25000,23000);
		Point myPaddleBr = new Point(28000,24000);
		int PADDLE_VELOCITY = 10 ;
		int paddleDir0 = 0;
		int paddleDir1 = 1;
		int paddleDir_1 = -1;
		PaddleState myPaddle0 = new PaddleState(myPaddleTl, myPaddleBr, paddleDir0*PADDLE_VELOCITY);
		PaddleState myPaddle1 = new PaddleState(myPaddleTl, myPaddleBr, paddleDir1*PADDLE_VELOCITY);
		PaddleState myPaddle_1 = new PaddleState(myPaddleTl, myPaddleBr, paddleDir_1*PADDLE_VELOCITY);
		
		// bottomRight
		Point myBottomRight = new Point(GameMap.getWidth(), GameMap.getHeight());

		// breakoutState
		BreakoutState myBreakoutState = new BreakoutState(myBalls,myBlocks,myBottomRight, myPaddle0);

		assertArrayEquals(myBalls, myBreakoutState.getBalls());
		assertArrayEquals(myBlocks, myBreakoutState.getBlocks());
		assertEquals(myPaddle0.getTl(),myBreakoutState.getPaddle().getTl());
		assertEquals(myBottomRight,myBreakoutState.getBottomRight());
		assertEquals(1, myBreakoutState.getBalls().length);
		assertEquals(2, myBreakoutState.getBlocks().length);

//		tick method
		myBreakoutState.tick(paddleDir0);

		//ball is moved by one step of the velocity.
		assertTrue(myBreakoutState.getBalls()[0].getTl().equals(myBalls[0].getTl().plus(myBallVelocity)));
		assertTrue(myBreakoutState.getBalls()[0].getBr().equals(myBalls[0].getBr().plus(myBallVelocity)));
		assertEquals(myBreakoutState.getBalls()[0].getVelocity(),new Vector(5,7));

		//paddle is not moved.
		assertEquals(myPaddle0.getTl(),myBreakoutState.getPaddle().getTl());
		assertEquals(myPaddle0.getBr(),myBreakoutState.getPaddle().getBr());
		assertEquals(myPaddle0.getVelocity(),myBreakoutState.getPaddle().getVelocity());	

		//blocks are not moved.
		assertEquals(myBlock1.getTl(),myBreakoutState.getBlocks()[0].getTl());
		assertEquals(myBlock1.getBr(),myBreakoutState.getBlocks()[0].getBr());
		assertEquals(myBlock2.getTl(),myBreakoutState.getBlocks()[1].getTl());
		assertEquals(myBlock2.getBr(),myBreakoutState.getBlocks()[1].getBr());


//	    [1_1] After ball touch GameMapLeft 
		// new Ball1_1
		Point myBallTl1_1 = new Point(0+5,18000-7);
		Point myBallBr1_1 = new Point(700+5,18700-7);
		Vector myBallVelocity1_1 = new Vector(-5,7);
		BallState myBall1_1 = new BallState(myBallTl1_1, myBallBr1_1, myBallVelocity1_1); 	
		BallState myNewBall1_1 = new BallState(new Point(0,18000), new Point(700,18700), new Vector(5,7));
		
		BallState[] myBalls1_1 = new BallState[] {myBall1_1};
		BallState[] myNewBalls1_1 = new BallState[] {myNewBall1_1};
 
		// new BreakoutState1_1
		BreakoutState myBreakoutState1_1 = new BreakoutState(myBalls1_1,myBlocks,myBottomRight, myPaddle0);
		myBreakoutState1_1.tick(paddleDir0);
		BreakoutState myNewBreakoutState1_1 = new BreakoutState(myNewBalls1_1,myBlocks,myBottomRight, myPaddle0);

		assertTrue(myBreakoutState1_1.getBalls()[0].getTl().equals(myNewBreakoutState1_1.getBalls()[0].getTl()));
		assertTrue(myBreakoutState1_1.getBalls()[0].getBr().equals(myNewBreakoutState1_1.getBalls()[0].getBr()));
		assertTrue(myBreakoutState1_1.getBalls()[0].getVelocity().equals(myNewBreakoutState1_1.getBalls()[0].getVelocity()));
		assertEquals(myBreakoutState1_1.getBalls()[0].getTl(),new Point(0,18000));
		assertEquals(myBreakoutState1_1.getBalls()[0].getBr(),new Point(700,18700));
		assertEquals(myBreakoutState1_1.getBalls()[0].getVelocity(),new Vector(5,7));
		assertEquals(1, myBreakoutState1_1.getBalls().length);

		assertTrue(myBreakoutState1_1.getBlocks()[0].getTl().equals(myNewBreakoutState1_1.getBlocks()[0].getTl()));
		assertTrue(myBreakoutState1_1.getBlocks()[0].getBr().equals(myNewBreakoutState1_1.getBlocks()[0].getBr()));	
		assertTrue(myBreakoutState1_1.getBlocks()[1].getTl().equals(myNewBreakoutState1_1.getBlocks()[1].getTl()));
		assertTrue(myBreakoutState1_1.getBlocks()[1].getBr().equals(myNewBreakoutState1_1.getBlocks()[1].getBr()));	
		assertEquals(myBreakoutState1_1.getBlocks()[0].getTl(),new Point(0,0));
		assertEquals(myBreakoutState1_1.getBlocks()[0].getBr(),new Point(5000,3000));
		assertEquals(myBreakoutState1_1.getBlocks()[1].getTl(),new Point(5000,0));
		assertEquals(myBreakoutState1_1.getBlocks()[1].getBr(),new Point(10000,3000));
		assertEquals(2, myBreakoutState1_1.getBlocks().length);
		
		assertTrue(myBreakoutState1_1.getPaddle().getTl().equals(myNewBreakoutState1_1.getPaddle().getTl()));
		assertTrue(myBreakoutState1_1.getPaddle().getBr().equals(myNewBreakoutState1_1.getPaddle().getBr()));
		assertEquals(myBreakoutState1_1.getPaddle().getVelocity(),myNewBreakoutState1_1.getPaddle().getVelocity());
		assertEquals(myBreakoutState1_1.getPaddle().getTl(),new Point(25000,23000));
		assertEquals(myBreakoutState1_1.getPaddle().getBr(),new Point(28000,24000));
		assertEquals(myBreakoutState1_1.getPaddle().getVelocity(),0);
		
//	    [1_1] After ball touch GameMapLeft and out of the boundary 
		// new Ball1_1
		Point myBallTl1_1_out = new Point(0+5-1,18000-7);
		Point myBallBr1_1_out = new Point(700+5-1,18700-7);
		Vector myBallVelocity1_1_out = new Vector(-5,7);
		BallState myBall1_1_out = new BallState(myBallTl1_1_out, myBallBr1_1_out, myBallVelocity1_1_out); 	
		BallState myNewBall1_1_out = new BallState(new Point(0+1,18000), new Point(700+1,18700), new Vector(5,7));
		
		BallState[] myBalls1_1_out = new BallState[] {myBall1_1_out};
		BallState[] myNewBalls1_1_out = new BallState[] {myNewBall1_1_out};
 
		// new BreakoutState1_1
		BreakoutState myBreakoutState1_1_out = new BreakoutState(myBalls1_1_out,myBlocks,myBottomRight, myPaddle0);
		myBreakoutState1_1_out.tick(paddleDir0);
		BreakoutState myNewBreakoutState1_1_out = new BreakoutState(myNewBalls1_1_out,myBlocks,myBottomRight, myPaddle0);

		assertTrue(myBreakoutState1_1_out.getBalls()[0].getTl().equals(myNewBreakoutState1_1_out.getBalls()[0].getTl()));
		assertTrue(myBreakoutState1_1_out.getBalls()[0].getBr().equals(myNewBreakoutState1_1_out.getBalls()[0].getBr()));
		assertTrue(myBreakoutState1_1_out.getBalls()[0].getVelocity().equals(myNewBreakoutState1_1_out.getBalls()[0].getVelocity()));
		assertEquals(myBreakoutState1_1_out.getBalls()[0].getTl(),new Point(0+1,18000));
		assertEquals(myBreakoutState1_1_out.getBalls()[0].getBr(),new Point(700+1,18700));
		assertEquals(myBreakoutState1_1_out.getBalls()[0].getVelocity(),new Vector(5,7));
		

//		[1_2] After ball touch GameMapRight 
		// new Ball1_2
		Point myBallTl1_2 = new Point(49300-5,18000-7);
		Point myBallBr1_2 = new Point(50000-5,18700-7);
		Vector myBallVelocity1_2 = new Vector(5,7);
		BallState myBall1_2 = new BallState(myBallTl1_2, myBallBr1_2, myBallVelocity1_2);	
		BallState myNewBall1_2 = new BallState(new Point(49300,18000), new Point(50000,18700),new Vector(-5,7));
		
		BallState[] myBalls1_2 = new BallState[] {myBall1_2};
		BallState[] myNewBalls1_2 = new BallState[] {myNewBall1_2};

		// new BreakoutState1_2
		BreakoutState myBreakoutState1_2 = new BreakoutState(myBalls1_2,myBlocks,myBottomRight,myPaddle0);
		myBreakoutState1_2.tick(paddleDir0); 
		BreakoutState myNewBreakoutState1_2 = new BreakoutState(myNewBalls1_2,myBlocks,myBottomRight,myPaddle0);
		

		assertTrue(myBreakoutState1_2.getBalls()[0].getTl().equals(myNewBreakoutState1_2.getBalls()[0].getTl()));
		assertTrue(myBreakoutState1_2.getBalls()[0].getBr().equals(myNewBreakoutState1_2.getBalls()[0].getBr()));
		assertTrue(myBreakoutState1_2.getBalls()[0].getVelocity().equals(myNewBreakoutState1_2.getBalls()[0].getVelocity()));
		assertEquals(myBreakoutState1_2.getBalls()[0].getTl(),new Point(49300,18000));
		assertEquals(myBreakoutState1_2.getBalls()[0].getBr(),new Point(50000,18700));
		assertEquals(myBreakoutState1_2.getBalls()[0].getVelocity(),new Vector(-5,7));
		
		
//		[1_2] After ball touch GameMapRight and out of the boundary
		// new Ball1_2
		Point myBallTl1_2_out = new Point(49300-5+1,18000-7);
		Point myBallBr1_2_out = new Point(50000-5+1,18700-7);
		Vector myBallVelocity1_2_out = new Vector(5,7);
		BallState myBall1_2_out = new BallState(myBallTl1_2_out, myBallBr1_2_out, myBallVelocity1_2_out);	
		BallState myNewBall1_2_out = new BallState(new Point(49300-1,18000), new Point(50000-1,18700),new Vector(-5,7));
		
		BallState[] myBalls1_2_out = new BallState[] {myBall1_2_out};
		BallState[] myNewBalls1_2_out = new BallState[] {myNewBall1_2_out};

		// new BreakoutState1_2
		BreakoutState myBreakoutState1_2_out = new BreakoutState(myBalls1_2_out,myBlocks,myBottomRight,myPaddle0);
		myBreakoutState1_2_out.tick(paddleDir0); 
		BreakoutState myNewBreakoutState1_2_out = new BreakoutState(myNewBalls1_2_out,myBlocks,myBottomRight,myPaddle0);
		
		assertTrue(myBreakoutState1_2_out.getBalls()[0].getTl().equals(myNewBreakoutState1_2_out.getBalls()[0].getTl()));
		assertTrue(myBreakoutState1_2_out.getBalls()[0].getBr().equals(myNewBreakoutState1_2_out.getBalls()[0].getBr()));
		assertTrue(myBreakoutState1_2_out.getBalls()[0].getVelocity().equals(myNewBreakoutState1_2_out.getBalls()[0].getVelocity()));
		assertEquals(myBreakoutState1_2_out.getBalls()[0].getTl(),new Point(49300-1,18000));
		assertEquals(myBreakoutState1_2_out.getBalls()[0].getBr(),new Point(50000-1,18700));
		assertEquals(myBreakoutState1_2_out.getBalls()[0].getVelocity(),new Vector(-5,7));
			
		
//		[1_3] After ball touch GameMapTop 
		// new Ball1_3
		Point myBallTl1_3 = new Point(22000-5,0+7);
		Point myBallBr1_3 = new Point(22700-5,700+7);
		Vector myBallVelocity1_3 = new Vector(5,-7);
		BallState myBall1_3 = new BallState(myBallTl1_3, myBallBr1_3, myBallVelocity1_3);	
		BallState myNewBall1_3 = new BallState(new Point(22000,0), new Point(22700,700),new Vector(5,7));
		
		BallState[] myBalls1_3 = new BallState[] {myBall1_3};
		BallState[] myNewBalls1_3 = new BallState[] {myNewBall1_3};

		// new BreakoutState1_3 
		BreakoutState myBreakoutState1_3 = new BreakoutState(myBalls1_3,myBlocks,myBottomRight, myPaddle0);
		myBreakoutState1_3.tick(paddleDir0); 
		BreakoutState myNewBreakoutState1_3 = new BreakoutState(myNewBalls1_3,myBlocks,myBottomRight, myPaddle0);

		assertTrue(myBreakoutState1_3.getBalls()[0].getTl().equals(myNewBreakoutState1_3.getBalls()[0].getTl()));
		assertTrue(myBreakoutState1_3.getBalls()[0].getBr().equals(myNewBreakoutState1_3.getBalls()[0].getBr()));
		assertTrue(myBreakoutState1_3.getBalls()[0].getVelocity().equals(myNewBreakoutState1_3.getBalls()[0].getVelocity()));
		assertEquals(myBreakoutState1_3.getBalls()[0].getTl(),new Point(22000,0));
		assertEquals(myBreakoutState1_3.getBalls()[0].getBr(),new Point(22700,700));
		assertEquals(myBreakoutState1_3.getBalls()[0].getVelocity(),new Vector(5,7));
		
		assertEquals(1, myBreakoutState1_1.getBalls().length);
		assertEquals(2, myBreakoutState1_1.getBlocks().length);
		assertEquals(myBreakoutState1_3.isWon(),false);
		assertEquals(myBreakoutState1_3.isDead(),false);
		
//		[1_3] After ball touch GameMapTop and out of the boundary 
		// new Ball1_3
		Point myBallTl1_3_out = new Point(22000-5,0+7-1);
		Point myBallBr1_3_out = new Point(22700-5,700+7-1);
		Vector myBallVelocity1_3_out = new Vector(5,-7);
		BallState myBall1_3_out = new BallState(myBallTl1_3_out, myBallBr1_3_out, myBallVelocity1_3_out);	
		BallState myNewBall1_3_out = new BallState(new Point(22000,0+1), new Point(22700,700+1),new Vector(5,7));
		
		BallState[] myBalls1_3_out = new BallState[] {myBall1_3_out};
		BallState[] myNewBalls1_3_out = new BallState[] {myNewBall1_3_out};

		// new BreakoutState1_3 
		BreakoutState myBreakoutState1_3_out = new BreakoutState(myBalls1_3_out,myBlocks,myBottomRight, myPaddle0);
		myBreakoutState1_3_out.tick(paddleDir0); 
		BreakoutState myNewBreakoutState1_3_out = new BreakoutState(myNewBalls1_3_out,myBlocks,myBottomRight, myPaddle0);

		assertTrue(myBreakoutState1_3_out.getBalls()[0].getTl().equals(myNewBreakoutState1_3_out.getBalls()[0].getTl()));
		assertTrue(myBreakoutState1_3_out.getBalls()[0].getBr().equals(myNewBreakoutState1_3_out.getBalls()[0].getBr()));
		assertTrue(myBreakoutState1_3_out.getBalls()[0].getVelocity().equals(myNewBreakoutState1_3_out.getBalls()[0].getVelocity()));
		assertEquals(myBreakoutState1_3_out.getBalls()[0].getTl(),new Point(22000,0+1));
		assertEquals(myBreakoutState1_3_out.getBalls()[0].getBr(),new Point(22700,700+1));
		assertEquals(myBreakoutState1_3_out.getBalls()[0].getVelocity(),new Vector(5,7));
		

//		[1_4] After ball touch GameMapBottom 
		// new Ball1_4
		Point myBallTl1_4 = new Point(49300-5,29300-7);
		Point myBallBr1_4 = new Point(50000-5,30000-7);
		Vector myBallVelocity1_4 = new Vector(5,7);
		BallState myBall1_4 = new BallState(myBallTl1_4, myBallBr1_4, myBallVelocity1_4);
		BallState myNewBall1_4 = new BallState(new Point(49300,29300), new Point(50000,30000),new Vector(5,7));
		//which is removed
		
		BallState[] myBalls1_4 = new BallState[] {myBall1_4};
		BallState[] myEmptyBalls1_4 = new BallState[] {}; 

		// new BreakoutState1_4
		BreakoutState myBreakoutState1_4 = new BreakoutState(myBalls1_4,myBlocks,myBottomRight, myPaddle0);
		myBreakoutState1_4.tick(paddleDir0); 
		BreakoutState myNewBreakoutState1_4 = new BreakoutState(myEmptyBalls1_4,myBlocks,myBottomRight, myPaddle0);
		
		assertArrayEquals(myBreakoutState1_4.getBalls(),myNewBreakoutState1_4.getBalls()); 
		assertArrayEquals(myBreakoutState1_4.getBalls(),new BallState[] {});
		
		assertEquals(0, myBreakoutState1_4.getBalls().length);
		assertEquals(2, myBreakoutState1_4.getBlocks().length);
		assertEquals(myBreakoutState1_4.isWon(),false);
		assertEquals(myBreakoutState1_4.isDead(),true);
		

	
	

//		[2_1_a] When the ball falls down and touches the top of the paddle (the paddle does't move) 
		// new Ball2_1
		Point myBallTl2_1_a = new Point(26300-5,22300-7);
		Point myBallBr2_1_a = new Point(27000-5,23000-7);
		Vector myBallVelocity2_1_a = new Vector(5,7);
		BallState myBall2_1_a = new BallState(myBallTl2_1_a, myBallBr2_1_a, myBallVelocity2_1_a);
		BallState myNewBall2_1_a = new BallState(new Point(26300,22300),new Point(27000,23000),new Vector(5,-7));	

		BallState[] myBalls2_1_a = new BallState[] {myBall2_1_a};
		BallState[] myNewBalls2_1_a = new BallState[] {myNewBall2_1_a};	

		// new BreakoutState2_1_a
		BreakoutState myBreakoutState2_1_a = new BreakoutState(myBalls2_1_a,myBlocks,myBottomRight, myPaddle0);
		myBreakoutState2_1_a.tick(paddleDir0);
		BreakoutState myNewBreakoutState2_1_a = new BreakoutState(myNewBalls2_1_a,myBlocks,myBottomRight,myPaddle0);

		assertTrue(myBreakoutState2_1_a.getBalls()[0].getTl().equals(myNewBreakoutState2_1_a.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_1_a.getBalls()[0].getBr().equals(myNewBreakoutState2_1_a.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_1_a.getBalls()[0].getVelocity().equals(myNewBreakoutState2_1_a.getBalls()[0].getVelocity()));  
		assertEquals(myBreakoutState2_1_a.getBalls()[0].getTl(),new Point(26300,22300));
		assertEquals(myBreakoutState2_1_a.getBalls()[0].getBr(),new Point(27000,23000));
		assertEquals(myBreakoutState2_1_a.getBalls()[0].getVelocity(),new Vector(5,-7)); 

		
//		[2_1_b] When the ball falls down and touches the top of the paddle (same direction: both right) 
		// new Ball2_1_b
		Point myBallTl2_1_b = new Point(26300-5,22300-7);
		Point myBallBr2_1_b = new Point(27000-5,23000-7);
		Vector myBallVelocity2_1_b = new Vector(5,7);
		BallState myBall2_1_b = new BallState(myBallTl2_1_b, myBallBr2_1_b, myBallVelocity2_1_b);
		BallState myNewBall2_1_b = new BallState(new Point(26300,22300),new Point(27000,23000), new Vector(7,-7));
		
		BallState[] myBalls2_1_b = new BallState[] {myBall2_1_b};
		BallState[] myNewBalls2_1_b = new BallState[] {myNewBall2_1_b};	
		
		// new Paddle2_1_b
		Point myNewPaddleTl2_1_b = new Point(25010,23000);
		Point myNewPaddleBr2_1_b = new Point(28010,24000);
		PaddleState myNewPaddle2_1_b = new PaddleState(myNewPaddleTl2_1_b, myNewPaddleBr2_1_b, paddleDir1*PADDLE_VELOCITY);
		
		// new BreakoutState2_1_b
		BreakoutState myBreakoutState2_1_b = new BreakoutState(myBalls2_1_b,myBlocks,myBottomRight, myPaddle1);
		myBreakoutState2_1_b.movePaddleRight();
		myBreakoutState2_1_b.tick(paddleDir1);
	    BreakoutState myNewBreakoutState2_1_b = new BreakoutState(myNewBalls2_1_b,myBlocks,myBottomRight,myNewPaddle2_1_b);
		
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getTl().equals(myNewBreakoutState2_1_b.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getBr().equals(myNewBreakoutState2_1_b.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getVelocity().equals(myNewBreakoutState2_1_b.getBalls()[0].getVelocity()));
		assertEquals(myBreakoutState2_1_b.getBalls()[0].getTl(),new Point(26300,22300));
		assertEquals(myBreakoutState2_1_b.getBalls()[0].getBr(),new Point(27000,23000));
		assertEquals(myBreakoutState2_1_b.getBalls()[0].getVelocity(),new Vector(7,-7)); 
		
		assertTrue(myBreakoutState2_1_b.getPaddle().getTl().equals(myNewBreakoutState2_1_b.getPaddle().getTl()));
		assertTrue(myBreakoutState2_1_b.getPaddle().getBr().equals(myNewBreakoutState2_1_b.getPaddle().getBr()));
		assertEquals(myBreakoutState2_1_b.getPaddle().getVelocity(),myNewBreakoutState2_1_b.getPaddle().getVelocity());
		assertEquals(myBreakoutState2_1_b.getPaddle().getTl(),new Point(25010,23000));
		assertEquals(myBreakoutState2_1_b.getPaddle().getBr(),new Point(28010,24000));
		assertEquals(myBreakoutState2_1_b.getPaddle().getVelocity(),10); 
		
	
//		[2_1_c] When the ball falls down and touches the top of the paddle (opposite direction: ball right, paddle left) 
		// new Ball2_1_c
		Point myBallTl2_1_c = new Point(26300-5,22300-7);
		Point myBallBr2_1_c = new Point(27000-5,23000-7);
		Vector myBallVelocity2_1_c = new Vector(5,7);
		BallState myBall2_1_c = new BallState(myBallTl2_1_c, myBallBr2_1_c, myBallVelocity2_1_c);
		BallState myNewBall2_1_c = new BallState(new Point(26300,22300),new Point (27000,23000), new Vector(3,-7));	 	

		BallState[] myBalls2_1_c = new BallState[] {myBall2_1_c};
		BallState[] myNewBalls2_1_c = new BallState[] {myNewBall2_1_c};		
		
		// new paddle2_1_c
		Point myPaddleTl2_1_c = new Point(25000+10,23000);
		Point myPaddleBr2_1_c = new Point(28000+10,24000);
		PaddleState myPaddle2_1_c = new PaddleState(myPaddleTl2_1_c,myPaddleBr2_1_c, paddleDir_1*PADDLE_VELOCITY);
		PaddleState myNewPaddle2_1_c = new PaddleState(new Point(25000,23000),new Point (28000,24000), paddleDir_1*PADDLE_VELOCITY);
		
		// new BreakoutState2_1_c
		BreakoutState myBreakoutState2_1_c = new BreakoutState(myBalls2_1_c,myBlocks,myBottomRight, myPaddle2_1_c);
		myBreakoutState2_1_c.movePaddleLeft();
		myBreakoutState2_1_c.tick(paddleDir_1);
		BreakoutState myNewBreakoutState2_1_c = new BreakoutState(myNewBalls2_1_c,myBlocks,myBottomRight,myNewPaddle2_1_c);

		assertTrue(myBreakoutState2_1_c.getBalls()[0].getTl().equals(myNewBreakoutState2_1_c.getBalls()[0].getTl())); 
		assertTrue(myBreakoutState2_1_c.getBalls()[0].getBr().equals(myNewBreakoutState2_1_c.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_1_c.getBalls()[0].getVelocity().equals(myNewBreakoutState2_1_c.getBalls()[0].getVelocity())); 
		assertEquals(myBreakoutState2_1_c.getBalls()[0].getTl(),new Point(26300,22300));
		assertEquals(myBreakoutState2_1_c.getBalls()[0].getBr(),new Point(27000,23000));
		assertEquals(myBreakoutState2_1_c.getBalls()[0].getVelocity(),new Vector(3,-7)); 
		
		assertTrue(myBreakoutState2_1_c.getPaddle().getTl().equals(myNewBreakoutState2_1_c.getPaddle().getTl())); 
		assertTrue(myBreakoutState2_1_c.getPaddle().getBr().equals(myNewBreakoutState2_1_c.getPaddle().getBr())); 
		assertEquals(myBreakoutState2_1_c.getPaddle().getVelocity(),myNewBreakoutState2_1_c.getPaddle().getVelocity());
		assertEquals(myBreakoutState2_1_c.getPaddle().getTl(),new Point(25000,23000)); 
		assertEquals(myBreakoutState2_1_c.getPaddle().getBr(),new Point(28000,24000)); 
		assertEquals(myBreakoutState2_1_c.getPaddle().getVelocity(),-10); 
		

//	    [2_2_a] When the ball falls down and touches the left of the paddle (ball right, paddle doesn't move)
		// new Ball2_2_a
		Point myBallTl2_2_a = new Point(24300-5,23100-7);
		Point myBallBr2_2_a = new Point(25000-5,23800-7);
		Vector myBallVelocity2_2_a = new Vector(5,7);
		BallState myBall2_2_a = new BallState(myBallTl2_2_a, myBallBr2_2_a, myBallVelocity2_2_a);
		BallState myNewBall2_2_a = new BallState(new Point(24300,23100),new Point (25000,23800), new Vector(-5,7));	 	

		BallState[] myBalls2_2_a = new BallState[] {myBall2_2_a};		
		BallState[] myNewBalls2_2_a = new BallState[] {myNewBall2_2_a};		

		// new BreakoutState2_2
		BreakoutState myBreakoutState2_2_a = new BreakoutState(myBalls2_2_a,myBlocks,myBottomRight,myPaddle0);
		myBreakoutState2_2_a.tick(paddleDir0);
		BreakoutState myNewBreakoutState2_2_a = new BreakoutState(myNewBalls2_2_a,myBlocks,myBottomRight,myPaddle0);
		
		assertTrue(myBreakoutState2_2_a.getBalls()[0].getTl().equals(myNewBreakoutState2_2_a.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_2_a.getBalls()[0].getBr().equals(myNewBreakoutState2_2_a.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_2_a.getBalls()[0].getVelocity().equals(myNewBreakoutState2_2_a.getBalls()[0].getVelocity())); 
		assertEquals(myBreakoutState2_2_a.getBalls()[0].getTl(),new Point(24300,23100)); 
		assertEquals(myBreakoutState2_2_a.getBalls()[0].getBr(),new Point(25000,23800));
		assertEquals(myBreakoutState2_2_a.getBalls()[0].getVelocity(),new Vector(-5,7));

//      [2_2_b] When the ball falls down and touches the left of the paddle (ball move right, paddle move left) (no squeezed) 
		// new Ball2_2_b
		Point myBallTl2_2_b = new Point(24300-5,23100-7);
		Point myBallBr2_2_b = new Point(25000-5,23800-7);
		Vector myBallVelocity2_2_b = new Vector(5,7);
		BallState myBall2_2_b = new BallState(myBallTl2_2_b, myBallBr2_2_b, myBallVelocity2_2_b);
		BallState myNewBall2_2_b = new BallState(new Point(24300,23100),new Point(25000,23800), new Vector(-7,7));		 		                              
		BallState[] myBalls2_2_b = new BallState[] {myBall2_2_b};	
		BallState[] myNewBalls2_2_b = new BallState[] {myNewBall2_2_b};		
		
		// new paddle2_2_b
		Point myNewPaddleTl2_2_b = new Point(25000+10,23000);
		Point myNewPaddleBr2_2_b = new Point(28000+10,24000);
		PaddleState myPaddle2_2_b = new PaddleState(myNewPaddleTl2_2_b,myNewPaddleBr2_2_b, paddleDir_1*PADDLE_VELOCITY);

		PaddleState myNewPaddle2_2_b = new PaddleState(new Point(25000,23000),new Point (28000,24000), paddleDir_1*PADDLE_VELOCITY);
		
		// new BreakoutState2_2_b 
		BreakoutState myBreakoutState2_2_b = new BreakoutState(myBalls2_2_b,myBlocks,myBottomRight,myPaddle2_2_b);
		myBreakoutState2_2_b.movePaddleLeft();
		myBreakoutState2_2_b.tick(paddleDir_1);
		BreakoutState myNewBreakoutState2_2_b = new BreakoutState(myNewBalls2_2_b,myBlocks,myBottomRight,myNewPaddle2_2_b);

		assertTrue(myBreakoutState2_2_b.getBalls()[0].getTl().equals(myNewBreakoutState2_2_b.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_2_b.getBalls()[0].getBr().equals(myNewBreakoutState2_2_b.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_2_b.getBalls()[0].getVelocity().equals(myNewBreakoutState2_2_b.getBalls()[0].getVelocity())); 
		assertEquals(myBreakoutState2_2_b.getBalls()[0].getTl(),new Point(24300,23100));
		assertEquals(myBreakoutState2_2_b.getBalls()[0].getBr(),new Point(25000,23800));
		assertEquals(myBreakoutState2_2_b.getBalls()[0].getVelocity(),new Vector(-7,7)); 
		
		assertTrue(myBreakoutState2_2_b.getPaddle().getTl().equals(myNewBreakoutState2_2_b.getPaddle().getTl()));
		assertTrue(myBreakoutState2_2_b.getPaddle().getBr().equals(myNewBreakoutState2_2_b.getPaddle().getBr())); 
		assertEquals(myBreakoutState2_2_b.getPaddle().getVelocity(),myNewBreakoutState2_2_b.getPaddle().getVelocity()); 
		assertEquals(myBreakoutState2_2_b.getPaddle().getTl(),new Point(25000,23000));
		assertEquals(myBreakoutState2_2_b.getPaddle().getBr(),new Point(28000,24000));
		assertEquals(myBreakoutState2_2_b.getPaddle().getVelocity(),-10); 

		
//     [2_2_c] When the ball falls down and touches the left of the paddle (ball move left, paddle move left) (squeezed) 
		// new Ball2_3_c
		Point myBallTl2_2_c = new Point(0+5,23100-7);
		Point myBallBr2_2_c = new Point(700+5,23800-7);
		Vector myBallVelocity2_2_c = new Vector(-5,7);
		BallState myBall2_2_c = new BallState(myBallTl2_2_c, myBallBr2_2_c, myBallVelocity2_2_c);
		BallState myNewBall2_2_c = new BallState(new Point(0,23100),new Point(700,23800), new Vector(0,7));

		BallState[] myBalls2_2_c = new BallState[] {myBall2_2_c};	
		BallState[] myNewBalls2_2_c = new BallState[] {myNewBall2_2_c};

		// new paddle2_2_c
		Point myPaddleTl2_2_c = new Point(700+10,23000);
		Point myPaddleBr2_2_c = new Point(3700+10,24000);
		PaddleState myPaddle2_2_c = new PaddleState(myPaddleTl2_2_c, myPaddleBr2_2_c, paddleDir_1*PADDLE_VELOCITY);
		PaddleState myNewPaddle2_2_c = new PaddleState(new Point(700,23000),new Point(3700,24000), paddleDir0*PADDLE_VELOCITY);

		// new BreakoutState2_2_c
		BreakoutState myBreakoutState2_2_c = new BreakoutState(myBalls2_2_c,myBlocks,myBottomRight,myPaddle2_2_c);
		myBreakoutState2_2_c.movePaddleLeft();
		myBreakoutState2_2_c.tick(paddleDir_1);

		BreakoutState myNewBreakoutState2_2_c = new BreakoutState(myNewBalls2_2_c,myBlocks,myBottomRight,myNewPaddle2_2_c);

		assertTrue(myBreakoutState2_2_c.getBalls()[0].getTl().equals(myNewBreakoutState2_2_c.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_2_c.getBalls()[0].getBr().equals(myNewBreakoutState2_2_c.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_2_c.getBalls()[0].getVelocity().equals(myNewBreakoutState2_2_c.getBalls()[0].getVelocity())); 
		assertEquals(myBreakoutState2_2_c.getBalls()[0].getTl(),new Point(0,23100));
		assertEquals(myBreakoutState2_2_c.getBalls()[0].getBr(),new Point(700,23800));
		assertEquals(myBreakoutState2_2_c.getBalls()[0].getVelocity(),new Vector(0,7)); 
		
		assertTrue(myBreakoutState2_2_c.getPaddle().getTl().equals(myNewBreakoutState2_2_c.getPaddle().getTl())); 
		assertTrue(myBreakoutState2_2_c.getPaddle().getBr().equals(myNewBreakoutState2_2_c.getPaddle().getBr())); 
		assertEquals(myBreakoutState2_2_c.getPaddle().getVelocity(),myNewBreakoutState2_2_c.getPaddle().getVelocity()); 
		assertEquals(myBreakoutState2_2_c.getPaddle().getTl(),new Point(700,23000));
		assertEquals(myBreakoutState2_2_c.getPaddle().getBr(),new Point(3700,24000));
		assertEquals(myBreakoutState2_2_c.getPaddle().getVelocity(),0);
		


		
//	    [2_3_a] When the ball falls down and touches the right of the paddle (ball left, paddle doesn't move) 
		// new Ball2_3_a
		Point myBallTl2_3_a = new Point(28000+5,23100-7);
		Point myBallBr2_3_a = new Point(28700+5,23800-7);
		Vector myBallVelocity2_3_a = new Vector(-5,7);
		BallState myBall2_3_a = new BallState(myBallTl2_3_a, myBallBr2_3_a, myBallVelocity2_3_a);
		BallState myNewBall2_3_a = new BallState(new Point(28000,23100),new Point (28700,23800), new Vector(5,7));	 	

		BallState[] myBalls2_3_a = new BallState[] {myBall2_3_a};		
		BallState[] myNewBalls2_3_a = new BallState[] {myNewBall2_3_a};		

		// new BreakoutState2_3_a
		BreakoutState myBreakoutState2_3_a = new BreakoutState(myBalls2_3_a,myBlocks,myBottomRight,myPaddle0);
		myBreakoutState2_3_a.tick(paddleDir0);
		BreakoutState myNewBreakoutState2_3_a = new BreakoutState(myNewBalls2_3_a,myBlocks,myBottomRight,myPaddle0);

		assertTrue(myBreakoutState2_3_a.getBalls()[0].getTl().equals(myNewBreakoutState2_3_a.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_3_a.getBalls()[0].getBr().equals(myNewBreakoutState2_3_a.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_3_a.getBalls()[0].getVelocity().equals(myNewBreakoutState2_3_a.getBalls()[0].getVelocity())); 
		assertEquals(myBreakoutState2_3_a.getBalls()[0].getTl(),new Point(28000,23100)); 
		assertEquals(myBreakoutState2_3_a.getBalls()[0].getBr(),new Point(28700,23800));
		assertEquals(myBreakoutState2_3_a.getBalls()[0].getVelocity(),new Vector(5,7));

		
//      [2_3_b] When the ball falls down and touches the right of the paddle (ball move left, paddle move right) (no squeezed) 
		// new Ball2_3_b
		Point myBallTl2_3_b = new Point(24300+5,23100-7);
		Point myBallBr2_3_b = new Point(25000+5,23800-7);
		Vector myBallVelocity2_3_b = new Vector(-5,7);
		BallState myBall2_3_b = new BallState(myBallTl2_3_b, myBallBr2_3_b, myBallVelocity2_3_b);
		BallState myNewBall2_3_b = new BallState(new Point(24300,23100),new Point(25000,23800), new Vector(-5,7));
			 		                               
		BallState[] myBalls2_3_b = new BallState[] {myBall2_3_b};	
		BallState[] myNewBalls2_3_b = new BallState[] {myNewBall2_3_b};		
		
		// new paddle2_3_b
		Point myPaddleTl2_3_b = new Point(21300-10,24300);
		Point myPaddleBr2_3_b = new Point(24300-10,27300);
		PaddleState myPaddle2_3_b = new PaddleState(myPaddleTl2_3_b, myPaddleBr2_3_b, paddleDir_1*PADDLE_VELOCITY);
		PaddleState myNewPaddle2_3_b = new PaddleState(new Point(21300,24300),new Point (24300,27300), paddleDir1*PADDLE_VELOCITY);
				
		// new BreakoutState2_2_b
		BreakoutState myBreakoutState2_3_b = new BreakoutState(myBalls2_3_b,myBlocks,myBottomRight,myPaddle2_3_b);
		myBreakoutState2_3_b.movePaddleRight();
		myBreakoutState2_3_b.tick(paddleDir1);
		BreakoutState myNewBreakoutState2_3_b = new BreakoutState(myNewBalls2_3_b,myBlocks,myBottomRight,myNewPaddle2_3_b);

		assertTrue(myBreakoutState2_3_b.getBalls()[0].getTl().equals(myNewBreakoutState2_3_b.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_3_b.getBalls()[0].getBr().equals(myNewBreakoutState2_3_b.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_3_b.getBalls()[0].getVelocity().equals(myNewBreakoutState2_3_b.getBalls()[0].getVelocity()));
		assertEquals(myBreakoutState2_3_b.getBalls()[0].getTl(),new Point(24300,23100));
		assertEquals(myBreakoutState2_3_b.getBalls()[0].getBr(),new Point(25000,23800));
		assertEquals(myBreakoutState2_3_b.getBalls()[0].getVelocity(),new Vector(-5,7)); 
		
		assertTrue(myBreakoutState2_3_b.getPaddle().getTl().equals(myNewBreakoutState2_3_b.getPaddle().getTl()));
		assertTrue(myBreakoutState2_3_b.getPaddle().getBr().equals(myNewBreakoutState2_3_b.getPaddle().getBr())); 
		assertEquals(myBreakoutState2_3_b.getPaddle().getVelocity(),myNewBreakoutState2_3_b.getPaddle().getVelocity()); 
		assertEquals(myBreakoutState2_3_b.getPaddle().getTl(),new Point(21300,24300));
		assertEquals(myBreakoutState2_3_b.getPaddle().getBr(),new Point(24300,27300));
		assertEquals(myBreakoutState2_3_b.getPaddle().getVelocity(),10); 

		
//     [2_3_c] When the ball falls down and touches the right of the paddle (ball move right, paddle move right) (squeezed)
		// new Ball2_3_c
		Point myBallTl2_3_c = new Point(49300-5,23100-7);
		Point myBallBr2_3_c = new Point(50000-5,23800-7);
		Vector myBallVelocity2_3_c = new Vector(5,7);
		BallState myBall2_3_c = new BallState(myBallTl2_3_c, myBallBr2_3_c, myBallVelocity2_3_c);
		BallState myNewBall2_3_c = new BallState(new Point(49300,23100),new Point(50000,23800), new Vector(0,7));
		
		BallState[] myBalls2_3_c = new BallState[] {myBall2_3_c};	
		BallState[] myNewBalls2_3_c = new BallState[] {myNewBall2_3_c};			

		// new paddle2_3_c
		Point myPaddleTl2_3_c = new Point(46300-10,23000);
		Point myPaddleBr2_3_c = new Point(49300-10,24000);
		PaddleState myPaddle2_3_c = new PaddleState(myPaddleTl2_3_c, myPaddleBr2_3_c, paddleDir1*PADDLE_VELOCITY);
		PaddleState myNewPaddle2_3_c = new PaddleState(new Point(46300,23000),new Point (49300,24000), paddleDir0*PADDLE_VELOCITY);
				
		// new BreakoutState2_2_c
		BreakoutState myBreakoutState2_3_c = new BreakoutState(myBalls2_3_c,myBlocks,myBottomRight,myPaddle2_3_c);
		myBreakoutState2_3_c.movePaddleRight();
		myBreakoutState2_3_c.tick(paddleDir1);
		BreakoutState myNewBreakoutState2_3_c = new BreakoutState(myNewBalls2_3_c,myBlocks,myBottomRight,myNewPaddle2_3_c);

		assertTrue(myBreakoutState2_3_c.getBalls()[0].getTl().equals(myNewBreakoutState2_3_c.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_3_c.getBalls()[0].getBr().equals(myNewBreakoutState2_3_c.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_3_c.getBalls()[0].getVelocity().equals(myNewBreakoutState2_3_c.getBalls()[0].getVelocity())); 
		assertEquals(myBreakoutState2_3_c.getBalls()[0].getTl(),new Point(49300,23100));
		assertEquals(myBreakoutState2_3_c.getBalls()[0].getBr(),new Point(50000,23800));
		assertEquals(myBreakoutState2_3_c.getBalls()[0].getVelocity(),new Vector(0,7)); 
		
		assertTrue(myBreakoutState2_3_c.getPaddle().getTl().equals(myNewBreakoutState2_3_c.getPaddle().getTl())); 
		assertTrue(myBreakoutState2_3_c.getPaddle().getBr().equals(myNewBreakoutState2_3_c.getPaddle().getBr())); 
		assertEquals(myBreakoutState2_3_c.getPaddle().getVelocity(),myNewBreakoutState2_3_c.getPaddle().getVelocity()); 
		assertEquals(myBreakoutState2_3_c.getPaddle().getTl(),new Point(46300,23000));
		assertEquals(myBreakoutState2_3_c.getPaddle().getBr(),new Point(49300,24000));
		assertEquals(myBreakoutState2_3_c.getPaddle().getVelocity(),0); 


//	    [3_1_a] After ball touch one block bottom
		// new Ball3_1_a
		Point myBallTl3_1_a = new Point(6000-5,3000+7);
		Point myBallBr3_1_a = new Point(6700-5,3700+7);
		Vector myBallVelocity3_1_a = new Vector(5,-7);
		BallState myBall3_1_a = new BallState(myBallTl3_1_a, myBallBr3_1_a, myBallVelocity3_1_a);
		BallState myNewBall3_1_a = new BallState(new Point(6000,3000),new Point(6700,3700), new Vector(5,7));
		
		BallState[] myBalls3_1_a = new BallState[] {myBall3_1_a};	
		BallState[] myNewBalls3_1_a = new BallState[] {myNewBall3_1_a};		

		// new Blocks3_1_a
		BlockState[] myNewBlocks3_1_a = new BlockState[] {myBlock1};
			
		// new BreakoutState3_1_a
		BreakoutState myBreakoutState3_1_a = new BreakoutState(myBalls3_1_a,myBlocks,myBottomRight, myPaddle0);
		myBreakoutState3_1_a.tick(paddleDir0);
		BreakoutState myNewBreakoutState3_1_a = new BreakoutState(myNewBalls3_1_a,myNewBlocks3_1_a,myBottomRight, myPaddle0);
		
		assertTrue(myBreakoutState3_1_a.getBalls()[0].getTl().equals(myNewBreakoutState3_1_a.getBalls()[0].getTl()));
		assertTrue(myBreakoutState3_1_a.getBalls()[0].getBr().equals(myNewBreakoutState3_1_a.getBalls()[0].getBr())); 
		assertTrue(myBreakoutState3_1_a.getBalls()[0].getVelocity().equals(myNewBreakoutState3_1_a.getBalls()[0].getVelocity())); 
		assertEquals(myBreakoutState3_1_a.getBalls()[0].getTl(),new Point(6000,3000));
		assertEquals(myBreakoutState3_1_a.getBalls()[0].getBr(),new Point(6700,3700));
		assertEquals(myBreakoutState3_1_a.getBalls()[0].getVelocity(),new Vector(5,7)); 
		
		assertTrue(myBreakoutState3_1_a.getBlocks()[0].getTl().equals(myNewBreakoutState3_1_a.getBlocks()[0].getTl()));
		assertTrue(myBreakoutState3_1_a.getBlocks()[0].getBr().equals(myNewBreakoutState3_1_a.getBlocks()[0].getBr()));  
		assertEquals(myBreakoutState3_1_a.getBlocks()[0].getTl(),new Point(0,0));
		assertEquals(myBreakoutState3_1_a.getBlocks()[0].getBr(),new Point(5000,3000));
		assertEquals(myBreakoutState3_1_a.getBlocks().length,1); 

		
//		[3_1_b] After ball touch one block right 
	    // new Ball3_1_b
		Point myBallTl3_1_b = new Point(10000+5,1500+7);
		Point myBallBr3_1_b = new Point(10700+5,2200+7);
		Vector myBallVelocity3_1_b = new Vector(-5,-7);
		BallState myBall3_1_b = new BallState(myBallTl3_1_b, myBallBr3_1_b, myBallVelocity3_1_b);
		BallState myNewBall3_1_b = new BallState(new Point(10000,1500),new Point(10700,2200), new Vector(5,-7));
	 			 	
	 	BallState[] myBalls3_1_b = new BallState[] {myBall3_1_b};	
	 	BallState[] myNewBalls3_1_b = new BallState[] {myNewBall3_1_b};		
	 	
	 	// new Blocks3_1_b
	 	BlockState[] myNewBlocks3_1_b = new BlockState[] {myBlock1};
	 	
	 	// new BreakoutState3_1_b
	 	BreakoutState myBreakoutState3_1_b = new BreakoutState(myBalls3_1_b,myBlocks,myBottomRight, myPaddle0);
	 	myBreakoutState3_1_b.tick(paddleDir0);
	 	BreakoutState myNewBreakoutState3_1_b = new BreakoutState(myNewBalls3_1_b,myNewBlocks3_1_b,myBottomRight, myPaddle0);

	 	assertTrue(myBreakoutState3_1_b.getBalls()[0].getTl().equals(myNewBreakoutState3_1_b.getBalls()[0].getTl()));
		assertTrue(myBreakoutState3_1_b.getBalls()[0].getBr().equals(myNewBreakoutState3_1_b.getBalls()[0].getBr())); 
		assertTrue(myBreakoutState3_1_b.getBalls()[0].getVelocity().equals(myNewBreakoutState3_1_b.getBalls()[0].getVelocity())); 
		assertEquals(myBreakoutState3_1_b.getBalls()[0].getTl(),new Point(10000,1500));
		assertEquals(myBreakoutState3_1_b.getBalls()[0].getBr(),new Point(10700,2200));
		assertEquals(myBreakoutState3_1_b.getBalls()[0].getVelocity(),new Vector(5,-7)); 
		
		assertTrue(myBreakoutState3_1_b.getBlocks()[0].getTl().equals(myNewBreakoutState3_1_b.getBlocks()[0].getTl()));
		assertTrue(myBreakoutState3_1_b.getBlocks()[0].getBr().equals(myNewBreakoutState3_1_b.getBlocks()[0].getBr()));  
		assertEquals(myBreakoutState3_1_b.getBlocks()[0].getTl(),new Point(0,0));
		assertEquals(myBreakoutState3_1_b.getBlocks()[0].getBr(),new Point(5000,3000));
		assertEquals(myBreakoutState3_1_b.getBlocks().length,1); 
	 	
		
//		[3_1_c] After ball touch one block corner (ball left up) 
	    // new Ball3_1_c
		Point myBallTl3_1_c = new Point(10000+5,3000+7);
		Point myBallBr3_1_c = new Point(10700+5,3700+7);
		Vector myBallVelocity3_1_c = new Vector(-5,-7);
		BallState myBall3_1_c = new BallState(myBallTl3_1_c, myBallBr3_1_c, myBallVelocity3_1_c);
		BallState myNewBall3_1_c = new BallState(new Point(10000,3000),new Point(10700,3700), new Vector(5,7));
	 			 	
	 	BallState[] myBalls3_1_c = new BallState[] {myBall3_1_c};	
	 	BallState[] myNewBalls3_1_c = new BallState[] {myNewBall3_1_c};		
	 	
	 	// new Blocks3_1_c
	 	BlockState[] myNewBlocks3_1_c = new BlockState[] {myBlock1};
	 	
	 	// new BreakoutState3_1_c
	 	BreakoutState myBreakoutState3_1_c = new BreakoutState(myBalls3_1_c,myBlocks,myBottomRight, myPaddle0);
	 	myBreakoutState3_1_c.tick(paddleDir0);
	 	BreakoutState myNewBreakoutState3_1_c = new BreakoutState(myNewBalls3_1_c,myNewBlocks3_1_c,myBottomRight, myPaddle0);

	 	assertTrue(myBreakoutState3_1_c.getBalls()[0].getTl().equals(myNewBreakoutState3_1_c.getBalls()[0].getTl()));
		assertTrue(myBreakoutState3_1_c.getBalls()[0].getBr().equals(myNewBreakoutState3_1_c.getBalls()[0].getBr())); 
		assertTrue(myBreakoutState3_1_c.getBalls()[0].getVelocity().equals(myNewBreakoutState3_1_c.getBalls()[0].getVelocity())); 
		assertEquals(myBreakoutState3_1_c.getBalls()[0].getTl(),new Point(10000,3000));
		assertEquals(myBreakoutState3_1_c.getBalls()[0].getBr(),new Point(10700,3700));
		assertEquals(myBreakoutState3_1_c.getBalls()[0].getVelocity(),new Vector(5,7)); 
		
		assertTrue(myBreakoutState3_1_c.getBlocks()[0].getTl().equals(myNewBreakoutState3_1_c.getBlocks()[0].getTl()));
		assertTrue(myBreakoutState3_1_c.getBlocks()[0].getBr().equals(myNewBreakoutState3_1_c.getBlocks()[0].getBr()));  
		assertEquals(myBreakoutState3_1_c.getBlocks()[0].getTl(),new Point(0,0));
		assertEquals(myBreakoutState3_1_c.getBlocks()[0].getBr(),new Point(5000,3000));
		assertEquals(myBreakoutState3_1_c.getBlocks().length,1); 

//	 	[3_2_a] After ball touch two block (touch two horizontal block bottom at same time)
	    // new Ball3_2_a
		Point myBallTl3_2_a = new Point(4900-5,3000+7);
		Point myBallBr3_2_a = new Point(5600-5,3700+7);
		Vector myBallVelocity3_2_a = new Vector(5,-7);
		BallState myBall3_2_a = new BallState(myBallTl3_2_a, myBallBr3_2_a, myBallVelocity3_2_a);
		BallState myNewBall3_2_a = new BallState(new Point(4900,3000),new Point(5600,3700), new Vector(5,7));
	 			 	
	 	BallState[] myBalls3_2_a= new BallState[] {myBall3_2_a};	
	 	BallState[] myNewBalls3_2_a = new BallState[] {myNewBall3_2_a};	
		
	 	// new Blocks3_2_a
	 	BlockState[] myEmptyBlocks3_2_a = new BlockState[] {};
	 	
	 	// new BreakoutState3_2_a
	 	BreakoutState myBreakoutState3_2_a = new BreakoutState(myBalls3_2_a,myBlocks,myBottomRight, myPaddle0);
	 	myBreakoutState3_2_a.tick(paddleDir0);
	 	BreakoutState myNewBreakoutState3_2_a = new BreakoutState(myNewBalls3_2_a,myEmptyBlocks3_2_a,myBottomRight, myPaddle0);

	    assertTrue(myBreakoutState3_2_a.getBalls()[0].getTl().equals(myNewBreakoutState3_2_a.getBalls()[0].getTl()));
		assertTrue(myBreakoutState3_2_a.getBalls()[0].getBr().equals(myNewBreakoutState3_2_a.getBalls()[0].getBr()));
		assertTrue(myBreakoutState3_2_a.getBalls()[0].getVelocity().equals(myNewBreakoutState3_2_a.getBalls()[0].getVelocity())); 
		assertEquals(myBreakoutState3_2_a.getBalls()[0].getTl(),new Point(4900,3000));
		assertEquals(myBreakoutState3_2_a.getBalls()[0].getBr(),new Point(5600,3700));
		assertEquals(myBreakoutState3_2_a.getBalls()[0].getVelocity(),new Vector(5,7)); 
		
		assertEquals(myBreakoutState3_2_a.getBalls().length,1);
		assertEquals(myBreakoutState3_2_a.getBlocks().length,0);
		assertEquals(myBreakoutState3_2_a.isWon(),true);
		assertEquals(myBreakoutState3_2_a.isDead(),false);
		
		
//	 	[3_2_b] After ball touch two block (touch two vertical block bottom at same time)
	    // new Ball3_2_b
		Point myBallTl3_2_b = new Point(5000+5,2900+7);
		Point myBallBr3_2_b = new Point(5700+5,3600+7);
		Vector myBallVelocity3_2_b = new Vector(-5,-7);
		BallState myBall3_2_b = new BallState(myBallTl3_2_b, myBallBr3_2_b, myBallVelocity3_2_b);
		BallState myNewBall3_2_b = new BallState(new Point(5000,2900),new Point(5700,3600), new Vector(5,-7));
	 			 	
	 	BallState[] myBalls3_2_b= new BallState[] {myBall3_2_b};	
	 	BallState[] myNewBalls3_2_b = new BallState[] {myNewBall3_2_b};	
		
	 	// new Blocks3_2_b
	 	Point myBlockTl3 = new Point(0,3000);
		Point myBlockBr3 = new Point(5000,6000);
		BlockState myBlock3 = new BlockState(myBlockTl3, myBlockBr3);
	 	
		BlockState[] myBlocks3_2_b = new BlockState[] {myBlock1,myBlock3};
	 	BlockState[] myEmptyBlocks3_2_b = new BlockState[] {};
	 	
	 	// new BreakoutState3_2_b
	 	BreakoutState myBreakoutState3_2_b = new BreakoutState(myBalls3_2_b,myBlocks3_2_b,myBottomRight, myPaddle0);
	 	myBreakoutState3_2_b.tick(paddleDir0);
	 	BreakoutState myNewBreakoutState3_2_b = new BreakoutState(myNewBalls3_2_b,myEmptyBlocks3_2_b,myBottomRight, myPaddle0);

	    assertTrue(myBreakoutState3_2_b.getBalls()[0].getTl().equals(myNewBreakoutState3_2_b.getBalls()[0].getTl()));
		assertTrue(myBreakoutState3_2_b.getBalls()[0].getBr().equals(myNewBreakoutState3_2_b.getBalls()[0].getBr()));
		assertTrue(myBreakoutState3_2_b.getBalls()[0].getVelocity().equals(myNewBreakoutState3_2_b.getBalls()[0].getVelocity())); 
		assertEquals(myBreakoutState3_2_b.getBalls()[0].getTl(),new Point(5000,2900));
		assertEquals(myBreakoutState3_2_b.getBalls()[0].getBr(),new Point(5700,3600));
		assertEquals(myBreakoutState3_2_b.getBalls()[0].getVelocity(),new Vector(5,-7)); 
		
		assertEquals(myBreakoutState3_2_b.getBlocks().length,0);
		
		
//	 	[3_3] After ball touch three block at same time 
	    // new Ball3_3
	 	Point myBallTl3_3 = new Point(5000+5,3000+7);
	 	Point myBallBr3_3 = new Point(5700+5,3700+7);
	 	Vector myBallVelocity3_3 = new Vector(-5,-7);
	 	BallState myBall3_3 = new BallState(myBallTl3_3, myBallBr3_3, myBallVelocity3_3);
		BallState myNewBall3_3 = new BallState(new Point(5000,3000),new Point(5700,3700), new Vector(5,7));
	 
	 	BallState[] myBalls3_3 = new BallState[] {myBall3_3};	
	 	BallState[] myNewBalls3_3 = new BallState[] {myNewBall3_3};		
	 	
	 	// new Blocks3_3
		BlockState[] myBlocks3_3 = new BlockState[] {myBlock1,myBlock2,myBlock3};
	 	BlockState[] myNewBlocks3_3 = new BlockState[] {};
	 	
	 	// new BreakoutState3_3
	 	BreakoutState myBreakoutState3_3 = new BreakoutState(myBalls3_3,myBlocks3_3,myBottomRight, myPaddle0);
	 	myBreakoutState3_3.tick(paddleDir0);

	 	BreakoutState myNewBreakoutState3_3 = new BreakoutState(myNewBalls3_3,myNewBlocks3_3,myBottomRight, myPaddle0);

	 	assertTrue(myBreakoutState3_3.getBalls()[0].getTl().equals(myNewBreakoutState3_3.getBalls()[0].getTl()));
		assertTrue(myBreakoutState3_3.getBalls()[0].getBr().equals(myNewBreakoutState3_3.getBalls()[0].getBr()));
		assertTrue(myBreakoutState3_3.getBalls()[0].getVelocity().equals(myNewBreakoutState3_3.getBalls()[0].getVelocity())); //error

		assertEquals(myBreakoutState3_3.getBalls()[0].getTl(),new Point(5000,3000));
		assertEquals(myBreakoutState3_3.getBalls()[0].getBr(),new Point(5700,3700));
		assertEquals(myBreakoutState3_3.getBalls()[0].getVelocity(),new Vector(5,7)); 
	 	
	
//	    [4] move paddle right to WallRight
		// new paddle4
		Point myPaddleTl4 = new Point(47000-10,23000);
		Point myPaddleBr4 = new Point(50000-10,24000);
		PaddleState myPaddle4 = new PaddleState(myPaddleTl4, myPaddleBr4, paddleDir1*PADDLE_VELOCITY);
		PaddleState myNewPaddle4 = new PaddleState(new Point(47000,23000),new Point (50000,24000), 0);
		
		// new BreakoutState4
		BreakoutState myBreakoutState4 = new BreakoutState(myBalls,myBlocks,myBottomRight, myPaddle4);
		myBreakoutState4.movePaddleRight();
		myBreakoutState4.tick(paddleDir1);
		BreakoutState myNewBreakoutState4 = new BreakoutState(myBalls,myBlocks,myBottomRight, myNewPaddle4);
				
		assertTrue(myBreakoutState4.getPaddle().getTl().equals(myNewBreakoutState4.getPaddle().getTl())); 
		assertTrue(myBreakoutState4.getPaddle().getBr().equals(myNewBreakoutState4.getPaddle().getBr())); 
		assertEquals(myBreakoutState4.getPaddle().getVelocity(),myNewBreakoutState4.getPaddle().getVelocity()); 
		assertEquals(myBreakoutState4.getPaddle().getTl(),new Point(47000,23000));
		assertEquals(myBreakoutState4.getPaddle().getBr(),new Point(50000,24000));
		assertEquals(myBreakoutState4.getPaddle().getVelocity(),0); 

		
//		[5] move paddle left to WallLeft
		// new paddle5
		Point myPaddleTl5 = new Point(0+10,23000);
		Point myPaddleBr5 = new Point(3000+10,24000);
		PaddleState myPaddle5 = new PaddleState(myPaddleTl5, myPaddleBr5, paddleDir_1*PADDLE_VELOCITY);
		PaddleState myNewPaddle5 = new PaddleState(new Point(0,23000),new Point (3000,24000), 0);
				
		// new BreakoutState5
		BreakoutState myBreakoutState5= new BreakoutState(myBalls,myBlocks,myBottomRight, myPaddle5);
		myBreakoutState5.movePaddleLeft();
		myBreakoutState5.tick(paddleDir_1);
		BreakoutState myNewBreakoutState5 = new BreakoutState(myBalls,myBlocks,myBottomRight, myNewPaddle5);

						
		assertTrue(myBreakoutState5.getPaddle().getTl().equals(myNewBreakoutState5.getPaddle().getTl())); 
		assertTrue(myBreakoutState5.getPaddle().getBr().equals(myNewBreakoutState5.getPaddle().getBr())); 
		assertEquals(myBreakoutState5.getPaddle().getVelocity(),myNewBreakoutState5.getPaddle().getVelocity()); 
		assertEquals(myBreakoutState5.getPaddle().getTl(),new Point(0,23000));
		assertEquals(myBreakoutState5.getPaddle().getBr(),new Point(3000,24000));
		assertEquals(myBreakoutState5.getPaddle().getVelocity(),0);
		
		
		
//		[6] After ball touch paddle corner 
	    // new Ball6
		Point myBallTl6 = new Point(24300-5,23000-7);
		Point myBallBr6 = new Point(25000-5,23700-7);
		Vector myBallVelocity6 = new Vector(5,7);
		
		BallState myBall6 = new BallState(myBallTl6, myBallBr6, myBallVelocity6);
		BallState myNewBall6 = new BallState(new Point(24300,23000),new Point(25000,23700), new Vector(-5,-7));
	 			 	
	 	BallState[] myBalls6 = new BallState[] {myBall6};	
	 	BallState[] myNewBalls6 = new BallState[] {myNewBall6};		
	 	
	 	// new paddle6
		Point myPaddleTl6 = new Point(25000,23700);
		Point myPaddleBr6 = new Point(28000,24700);
		PaddleState myPaddle6 = new PaddleState(myPaddleTl6, myPaddleBr6, paddleDir0*PADDLE_VELOCITY);
		
	 	
	 	// new BreakoutState6
	 	BreakoutState myBreakoutState6 = new BreakoutState(myBalls6,myBlocks,myBottomRight, myPaddle6);
	 	myBreakoutState6.tick(paddleDir0);

	 	BreakoutState myNewBreakoutState6 = new BreakoutState(myNewBalls6,myBlocks,myBottomRight, myPaddle6);

	 	assertTrue(myBreakoutState6.getBalls()[0].getTl().equals(myNewBreakoutState6.getBalls()[0].getTl()));
		assertTrue(myBreakoutState6.getBalls()[0].getBr().equals(myNewBreakoutState6.getBalls()[0].getBr())); 
		assertTrue(myBreakoutState6.getBalls()[0].getVelocity().equals(myNewBreakoutState6.getBalls()[0].getVelocity())); 
		assertEquals(myBreakoutState6.getBalls()[0].getTl(),new Point(24300,23000));
		assertEquals(myBreakoutState6.getBalls()[0].getBr(),new Point(25000,23700));
		assertEquals(myBreakoutState6.getBalls()[0].getVelocity(),new Vector(-5,-7)); 
		
		assertTrue(myBreakoutState6.getPaddle().getTl().equals(myNewBreakoutState6.getPaddle().getTl())); 
		assertTrue(myBreakoutState6.getPaddle().getBr().equals(myNewBreakoutState6.getPaddle().getBr())); 
		assertEquals(myBreakoutState6.getPaddle().getVelocity(),myNewBreakoutState6.getPaddle().getVelocity()); 
		assertEquals(myBreakoutState6.getPaddle().getTl(),new Point(25000,23700));
		assertEquals(myBreakoutState6.getPaddle().getBr(),new Point(28000,24700));
		assertEquals(myBreakoutState6.getPaddle().getVelocity(),0);
		
		
		
//		[2_1_b] When the ball falls down and touches the right of the paddle (same direction: both right) 
		// new Ball2_1_b
		Point myBallTl2_1_b_right = new Point(26300-5,22300-7);
		Point myBallBr2_1_b_right = new Point(27000-5,23000-7);
		Vector myBallVelocity2_1_b_right = new Vector(5,7);
		BallState myBall2_1_b_right = new BallState(myBallTl2_1_b_right, myBallBr2_1_b_right, myBallVelocity2_1_b_right);
		BallState myNewBall2_1_b_right = new BallState(new Point(26300,22300),new Point(27000,23000), new Vector(7,7));
		
		BallState[] myBalls2_1_b_right = new BallState[] {myBall2_1_b_right};
		BallState[] myNewBalls2_1_b_right = new BallState[] {myNewBall2_1_b_right};	
		
		// new Paddle2_1_b
		Point myNewPaddleTl2_1_b_right = new Point(23300-10,22500);
		Point myNewPaddleBr2_1_b_right = new Point(26300-10,23500);
		PaddleState myNewPaddle2_1_b_right = new PaddleState(myNewPaddleTl2_1_b_right, myNewPaddleBr2_1_b_right, paddleDir1*PADDLE_VELOCITY);
		PaddleState myNewNewPaddle2_1_b_right = new PaddleState(new Point(23300,22500), new Point(26300,23500), paddleDir1*PADDLE_VELOCITY);

		
		// new BreakoutState2_1_b
		BreakoutState myBreakoutState2_1_b_right = new BreakoutState(myBalls2_1_b_right,myBlocks,myBottomRight, myNewPaddle2_1_b_right);
		myBreakoutState2_1_b_right.movePaddleRight();
		myBreakoutState2_1_b_right.tick(paddleDir1);
	    BreakoutState myNewBreakoutState2_1_b_right = new BreakoutState(myNewBalls2_1_b_right,myBlocks,myBottomRight,myNewNewPaddle2_1_b_right);
	

		
		assertTrue(myBreakoutState2_1_b_right.getBalls()[0].getTl().equals(myNewBreakoutState2_1_b_right.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_1_b_right.getBalls()[0].getBr().equals(myNewBreakoutState2_1_b_right.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_1_b_right.getBalls()[0].getVelocity().equals(myNewBreakoutState2_1_b_right.getBalls()[0].getVelocity()));
		assertEquals(myBreakoutState2_1_b_right.getBalls()[0].getTl(),new Point(26300,22300));
		assertEquals(myBreakoutState2_1_b_right.getBalls()[0].getBr(),new Point(27000,23000));
		assertEquals(myBreakoutState2_1_b_right.getBalls()[0].getVelocity(),new Vector(7,7)); 
		
		assertTrue(myBreakoutState2_1_b_right.getPaddle().getTl().equals(myNewBreakoutState2_1_b_right.getPaddle().getTl()));
		assertTrue(myBreakoutState2_1_b_right.getPaddle().getBr().equals(myNewBreakoutState2_1_b_right.getPaddle().getBr()));
		assertEquals(myBreakoutState2_1_b_right.getPaddle().getVelocity(),myNewBreakoutState2_1_b_right.getPaddle().getVelocity());
		assertEquals(myBreakoutState2_1_b_right.getPaddle().getTl(),new Point(23300,22500));
		assertEquals(myBreakoutState2_1_b_right.getPaddle().getBr(),new Point(26300,23500)); 
		assertEquals(myBreakoutState2_1_b_right.getPaddle().getVelocity(),10); 
		
		//dot product test
		BreakoutState myBreakoutStateBefore = new BreakoutState(myBalls1_1,myBlocks,myBottomRight, myPaddle0);

		//before
		Vector ballDir = myBreakoutStateBefore.getBalls()[0].getVelocity();
		//after
		Vector ballVel = myBreakoutStateBefore.getBalls()[0].getVelocity().mirrorOver(Vector.RIGHT);
				
		int dotProduct = ballDir.product(ballVel);

		// sharp angle: cos = 0~1
		double value = Math.acos( ( (ballDir.getX() * ballVel.getX()) + (ballDir.getY() * ballVel.getY()) ) / ( Math.sqrt( (ballDir.getSquareLength() ) * Math.sqrt( (ballVel.getSquareLength()))))) ;
				
	
		//after tick
		myBreakoutStateBefore.tick(paddleDir0);		
		assertEquals(dotProduct,ballDir.product(myBreakoutStateBefore.getBalls()[0].getVelocity()));

		
		
		
	}
	
	
	
}


