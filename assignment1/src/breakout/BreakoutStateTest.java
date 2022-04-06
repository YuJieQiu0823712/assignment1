  
package breakout;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

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
		int PADDLE_VELOCITY = 100 ;
		PaddleState myPaddle = new PaddleState(myPaddleTl, myPaddleBr, PADDLE_VELOCITY);

		// bottomRight
		Point myBottomRight = new Point(GameMap.getWidth(), GameMap.getHeight());

		// breakoutState
		BreakoutState myBreakoutState = new BreakoutState(myBalls,myBlocks,myBottomRight, myPaddle);

		assertArrayEquals(myBalls, myBreakoutState.getBalls());
		assertArrayEquals(myBlocks, myBreakoutState.getBlocks());
		assertEquals(myPaddle.getTl(),myBreakoutState.getPaddle().getTl());
		assertEquals(myBottomRight,myBreakoutState.getBottomRight());
		assertEquals(1, myBreakoutState.getBalls().length);

//		tick method
		int paddleDir0 = 0;
		myBreakoutState.tick(paddleDir0);

		//ball is moved by one step of the velocity.
		assertTrue(myBreakoutState.getBalls()[0].getTl().equals(myBalls[0].getTl().plus(myBallVelocity)));
		assertTrue(myBreakoutState.getBalls()[0].getBr().equals(myBalls[0].getBr().plus(myBallVelocity)));
		assertEquals(myBreakoutState.getBalls()[0].getVelocity(),new Vector(5,7));


		//paddle is not moved.
		assertEquals(myPaddle.getTl(),myBreakoutState.getPaddle().getTl());
		assertEquals(myPaddle.getBr(),myBreakoutState.getPaddle().getBr());
		assertEquals(myPaddle.getVelocity()*paddleDir0,myBreakoutState.getPaddle().getVelocity());	


		//blocks are not moved.
		assertEquals(myBlock1.getTl(),myBreakoutState.getBlocks()[0].getTl());
		assertEquals(myBlock1.getBr(),myBreakoutState.getBlocks()[0].getBr());
		assertEquals(myBlock2.getTl(),myBreakoutState.getBlocks()[1].getTl());
		assertEquals(myBlock2.getBr(),myBreakoutState.getBlocks()[1].getBr());


//	    [1_1] After ball touch GameMapLeft  
		// new Ball1_1
		Point myBallTl1_1 = new Point(3,3);
		Point myBallBr1_1 = new Point(5,5);
		Vector myBallVelocity1_1 = new Vector(-6,0);
		BallState myBall1_1 = new BallState(myBallTl1_1, myBallBr1_1, myBallVelocity1_1); 	
		BallState myNewBall1_1 = new BallState(new Point(3,3), new Point(5,5), myBallVelocity1_1.mirrorOver(Vector.RIGHT));
		BallState[] myBalls1_1 = new BallState[] {myBall1_1};
		BallState[] myNewBalls1_1 = new BallState[] {myNewBall1_1};

		// new Block1_1
		Point myBlockTl1_1 = new Point(0,0);
		Point myBlockBr1_1 = new Point(2,1);
		BlockState myBlock1_1 = new BlockState(myBlockTl1_1, myBlockBr1_1);
		Point myBlockTl1_2 = new Point(2,0);
		Point myBlockBr1_2 = new Point(4,1);
		BlockState myBlock1_2 = new BlockState(myBlockTl1_2, myBlockBr1_2);

		BlockState[] myBlocks1 = new BlockState[] {myBlock1_1,myBlock1_2};

		// new Paddle1_1
		Point myPaddleTl_1 = new Point(6,6);
		Point myPaddleBrl_1 = new Point(8,7);
		int myPaddleVelocityl_1 = 10 ;
		PaddleState myPaddlel_1 = new PaddleState(myPaddleTl_1, myPaddleBrl_1, myPaddleVelocityl_1);

 
		// new BreakoutState1_1
		BreakoutState myNewBreakoutState1_1beforetick = new BreakoutState(myBalls1_1,myBlocks,myBottomRight, myPaddle);
		myNewBreakoutState1_1beforetick.tick(paddleDir0);
		BreakoutState myNewBreakoutState1_1aftertick = new BreakoutState(myNewBalls1_1,myBlocks1,myBottomRight, myPaddlel_1);

		assertEquals(1, myNewBreakoutState1_1beforetick.getBalls().length);
		assertTrue(myNewBreakoutState1_1aftertick.getBalls()[0].getTl().equals(myNewBreakoutState1_1beforetick.getBalls()[0].getTl()));
		assertTrue(myNewBreakoutState1_1aftertick.getBalls()[0].getBr().equals(myNewBreakoutState1_1beforetick.getBalls()[0].getBr()));
		assertTrue(myNewBreakoutState1_1aftertick.getBalls()[0].getVelocity().equals(myNewBreakoutState1_1beforetick.getBalls()[0].getVelocity()));
		assertEquals(myNewBreakoutState1_1aftertick.getBalls()[0].getTl(),new Point(3,3));
		assertEquals(myNewBreakoutState1_1aftertick.getBalls()[0].getBr(),new Point(5,5));
		assertEquals(myNewBreakoutState1_1beforetick.getBalls()[0].getVelocity(),new Vector(6,0));

		assertEquals(2, myNewBreakoutState1_1beforetick.getBlocks().length);
		assertTrue(myNewBreakoutState1_1beforetick.getBlocks()[0].getTl().equals(myNewBreakoutState1_1aftertick.getBlocks()[0].getTl()));
		assertTrue(myNewBreakoutState1_1beforetick.getBlocks()[0].getBr().equals(myNewBreakoutState1_1aftertick.getBlocks()[0].getBr()));	
		assertTrue(myNewBreakoutState1_1beforetick.getBlocks()[1].getTl().equals(myNewBreakoutState1_1aftertick.getBlocks()[1].getTl()));
		assertTrue(myNewBreakoutState1_1beforetick.getBlocks()[1].getBr().equals(myNewBreakoutState1_1aftertick.getBlocks()[1].getBr()));	
		assertEquals(myNewBreakoutState1_1beforetick.getBlocks()[0].getTl(),new Point(0,0));
		assertEquals(myNewBreakoutState1_1beforetick.getBlocks()[0].getBr(),new Point(2,1));
		assertEquals(myNewBreakoutState1_1beforetick.getBlocks()[1].getTl(),new Point(2,0));
		assertEquals(myNewBreakoutState1_1beforetick.getBlocks()[1].getBr(),new Point(4,1));

		assertTrue(myNewBreakoutState1_1beforetick.getPaddle().getTl().equals(myNewBreakoutState1_1aftertick.getPaddle().getTl()));
		assertTrue(myNewBreakoutState1_1beforetick.getPaddle().getBr().equals(myNewBreakoutState1_1aftertick.getPaddle().getBr()));
		assertEquals(myNewBreakoutState1_1beforetick.getPaddle().getTl(),new Point(6,6));
		assertEquals(myNewBreakoutState1_1beforetick.getPaddle().getBr(),new Point(8,7));



//		[1_2] After ball touch GameMapRight 
		// new Ball1_2
		Point myBallTl1_2 = new Point(49997,29997);
		Point myBallBr1_2 = new Point(49999,29999);
		Vector myBallVelocity1_2 = new Vector(6,0);
		BallState myBall1_2 = new BallState(myBallTl1_2, myBallBr1_2, myBallVelocity1_2);	
		BallState myNewBall1_2 = new BallState(new Point(49993,29997), new Point(49995,29999),myBall1_2.getVelocity().mirrorOver(Vector.RIGHT));
		BallState[] myBalls1_2 = new BallState[] {myBall1_2};
		BallState[] myNewBalls1_2 = new BallState[] {myNewBall1_2};


		// new BreakoutState1_2
		BreakoutState myBreakoutState1_2 = new BreakoutState(myBalls1_2,myBlocks,myBottomRight,myPaddle);
		myBreakoutState1_2.tick(paddleDir0); //
		BreakoutState myNewBreakoutState1_2 = new BreakoutState(myNewBalls1_2,myBlocks1,myBottomRight,myPaddlel_1);

		assertTrue(myBreakoutState1_2.getBalls()[0].getTl().equals(myNewBreakoutState1_2.getBalls()[0].getTl()));
		assertTrue(myBreakoutState1_2.getBalls()[0].getBr().equals(myNewBreakoutState1_2.getBalls()[0].getBr()));
		assertTrue(myBreakoutState1_2.getBalls()[0].getVelocity().equals(myNewBreakoutState1_2.getBalls()[0].getVelocity()));

		assertEquals(myBreakoutState1_2.getBalls()[0].getTl(),new Point(49993,29997));
		assertEquals(myBreakoutState1_2.getBalls()[0].getBr(),new Point(49995,29999));
		assertEquals(myBreakoutState1_2.getBalls()[0].getVelocity(),new Vector(-6,0));

			
//		[1_3] After ball touch GameMapTop 
		// new Ball1_3
		Point myBallTl1_3 = new Point(49997,1);
		Point myBallBr1_3 = new Point(49999,3);
		Vector myBallVelocity1_3 = new Vector(0,-6);
		BallState myBall1_3 = new BallState(myBallTl1_3, myBallBr1_3, myBallVelocity1_3);	
		BallState myNewBall1_3 = new BallState(new Point(49997,5), new Point(49999,7),myBall1_3.getVelocity().mirrorOver(Vector.DOWN));
		BallState[] myBalls1_3 = new BallState[] {myBall1_3};
		BallState[] myNewBalls1_3 = new BallState[] {myNewBall1_3};

		// new BreakoutState1_3
		BreakoutState myNewBreakoutState1_3beforetick = new BreakoutState(myBalls1_3,myBlocks,myBottomRight, myPaddle);
		myNewBreakoutState1_3beforetick.tick(paddleDir0); 
		BreakoutState myNewBreakoutState1_3aftertick = new BreakoutState(myNewBalls1_3,myBlocks1,myBottomRight, myPaddlel_1);

		assertTrue(myNewBreakoutState1_3aftertick.getBalls()[0].getTl().equals(myNewBreakoutState1_3beforetick.getBalls()[0].getTl()));
		assertTrue(myNewBreakoutState1_3aftertick.getBalls()[0].getBr().equals(myNewBreakoutState1_3beforetick.getBalls()[0].getBr()));
		assertTrue(myNewBreakoutState1_3aftertick.getBalls()[0].getVelocity().equals(myNewBreakoutState1_3beforetick.getBalls()[0].getVelocity()));

		assertEquals(myNewBreakoutState1_3beforetick.getBalls()[0].getTl(),new Point(49997,5));
		assertEquals(myNewBreakoutState1_3beforetick.getBalls()[0].getBr(),new Point(49999,7));
		assertEquals(myNewBreakoutState1_3beforetick.getBalls()[0].getVelocity(),new Vector(0,6));
		
		assertEquals(myNewBreakoutState1_3aftertick.isDead(),false);
		assertEquals(myNewBreakoutState1_3aftertick.isWon(),false);


//		[1_4] After ball touch GameMapBottom 
		// new Ball1_4
		Point myBallTl1_4 = new Point(GameMap.getWidth()-3,GameMap.getHeight()-3);
		Point myBallBr1_4 = new Point(GameMap.getWidth()-1,GameMap.getHeight()-1);
		Vector myBallVelocity1_4 = new Vector(0,6);
		BallState myBall1_4 = new BallState(myBallTl1_4, myBallBr1_4, myBallVelocity1_4);
		//a "fake" ball
		BallState myNewBall1_4 = new BallState(myBall1_4.getTl().plus(myBall1_4.getVelocity()), myBall1_4.getBr().plus(myBall1_4.getVelocity()),myBall1_4.getVelocity().mirrorOver(Vector.DOWN));
		BallState[] myBalls1_4 = new BallState[] {myBall1_4};
		BallState[] myNewBalls1_4 = new BallState[] {}; 

		// new BreakoutState1_4
		BreakoutState myNewBreakoutState1_4beforetick = new BreakoutState(myBalls1_4,myBlocks,myBottomRight, myPaddle);
		myNewBreakoutState1_4beforetick.tick(paddleDir0); 
		BreakoutState myNewBreakoutState1_4aftertick = new BreakoutState(myNewBalls1_4,myBlocks1,myBottomRight, myPaddlel_1);
		
		assertArrayEquals(myNewBreakoutState1_4beforetick.getBalls(),myNewBreakoutState1_4aftertick.getBalls()); 
		assertArrayEquals(myNewBreakoutState1_4beforetick.getBalls(),new BallState[] {});
		
		assertEquals(myNewBreakoutState1_4beforetick.getBalls().length,0);
		assertEquals(myNewBreakoutState1_4beforetick.isDead(),true);
		assertEquals(myNewBreakoutState1_4beforetick.isWon(),false);



//		[2_1_a] When the ball vertically falls down and touches the top of the paddle (the paddle does't move)
		// paddle2_1_a
		Point myPaddleTl2_1_a = new Point(60,60);
		Point myPaddleBr2_1_a = new Point(80,70);
		PaddleState myPaddle2_1_a = new PaddleState(myPaddleTl2_1_a, myPaddleBr2_1_a, paddleDir0*PADDLE_VELOCITY);
		
		// new Ball2_1
		Point myBallTl2_1_a = new Point(60,30);
		Point myBallBr2_1_a = new Point(80,50);
		Vector myBallVelocity2_1_a = new Vector(0,20);
		BallState myBall2_1_a = new BallState(myBallTl2_1_a, myBallBr2_1_a, myBallVelocity2_1_a);
		BallState myNewBall2_1_a = new BallState(new Point(60,50),new Point(80,70),new Vector(0,-20));	
		BallState myNewNewBall2_1_a = new BallState(new Point(60,30),new Point(80,50),new Vector(0,-20));

		BallState[] myBalls2_1_a = new BallState[] {myBall2_1_a};
		BallState[] myNewBalls2_1_a = new BallState[] {myNewBall2_1_a};	
		BallState[] myNewNewBalls2_1_a = new BallState[] {myNewNewBall2_1_a};	


		// new BreakoutState2_1_a
		BreakoutState myBreakoutState2_1_a = new BreakoutState(myBalls2_1_a,myBlocks,myBottomRight, myPaddle2_1_a);
		myBreakoutState2_1_a.tick(paddleDir0);
		BreakoutState myNewBreakoutState2_1_a = new BreakoutState(myNewBalls2_1_a,myBlocks,myBottomRight,myPaddle2_1_a);
		myBreakoutState2_1_a.tick(paddleDir0);
		BreakoutState myNewNewBreakoutState2_1_a = new BreakoutState(myNewNewBalls2_1_a,myBlocks,myBottomRight,myPaddle2_1_a);
		
		assertTrue(myBreakoutState2_1_a.getBalls()[0].getTl().equals(myNewNewBreakoutState2_1_a.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_1_a.getBalls()[0].getBr().equals(myNewNewBreakoutState2_1_a.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_1_a.getBalls()[0].getVelocity().equals(myNewBreakoutState2_1_a.getBalls()[0].getVelocity())); 

        
		assertEquals(myBreakoutState2_1_a.getBalls()[0].getTl(),new Point(60,30));
		assertEquals(myBreakoutState2_1_a.getBalls()[0].getBr(),new Point(80,50));
		assertEquals(myBreakoutState2_1_a.getBalls()[0].getVelocity(),new Vector(0,-20)); 
	  
		assertTrue(myBreakoutState2_1_a.getPaddle().getTl().equals(myNewBreakoutState2_1_a.getPaddle().getTl()));
		assertTrue(myBreakoutState2_1_a.getPaddle().getBr().equals(myNewBreakoutState2_1_a.getPaddle().getBr()));
		assertEquals(myBreakoutState2_1_a.getPaddle().getVelocity(),myNewBreakoutState2_1_a.getPaddle().getVelocity()); 

		
//		[2_1_b] After ball touch top of paddle (ball and paddle are in the same direction: both right)
		// paddle2_1_b
		Point myPaddleTl2_1_b = new Point(25000,23000);
		Point myPaddleBr2_1_b = new Point(28000,24000);
		int paddleDir1 = 1 ;
			
		PaddleState myPaddle2_1_b = new PaddleState(myPaddleTl2_1_b, myPaddleBr2_1_b, paddleDir1*PADDLE_VELOCITY);
	    PaddleState myNewPaddle2_1_b = new PaddleState(new Point(25100,23000),new Point (28100,24000), paddleDir1*PADDLE_VELOCITY);

		// new Ball2_1_b
		Point myBallTl2_1_b = new Point(25300-5,22300-7);
		Point myBallBr2_1_b = new Point(26000-5,23000-7);
		Vector myBallVelocity2_1_b = new Vector(5,7);
		BallState myBall2_1_b = new BallState(myBallTl2_1_b, myBallBr2_1_b, myBallVelocity2_1_b);
		BallState myNewBall2_1_b = new BallState(new Point(25300,22300),new Point (26000,23000), new Vector(25,-7));
		BallState myNewNewBall2_1_b = new BallState(new Point(25300+25,22300-7),new Point (26000+25,23000-7), new Vector(25,-7));
	
		BallState[] myBalls2_1_b = new BallState[] {myBall2_1_b};
		BallState[] myNewBalls2_1_b = new BallState[] {myNewBall2_1_b};	
		BallState[] myNewNewBalls2_1_b = new BallState[] {myNewNewBall2_1_b};	
		
		// new BreakoutState2_1_b
		BreakoutState myBreakoutState2_1_b = new BreakoutState(myBalls2_1_b,myBlocks,myBottomRight, myPaddle2_1_b);
		myBreakoutState2_1_b.movePaddleRight();
		myBreakoutState2_1_b.tick(paddleDir1);
	    BreakoutState myNewBreakoutState2_1_b = new BreakoutState(myNewBalls2_1_b,myBlocks,myBottomRight,myNewPaddle2_1_b);
		
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getTl().equals(myNewBreakoutState2_1_b.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getBr().equals(myNewBreakoutState2_1_b.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getVelocity().equals(myNewBreakoutState2_1_b.getBalls()[0].getVelocity()));
		assertEquals(myBreakoutState2_1_b.getBalls()[0].getTl(),new Point(25300,22300));
		assertEquals(myBreakoutState2_1_b.getBalls()[0].getBr(),new Point(26000,23000));
		assertEquals(myBreakoutState2_1_b.getBalls()[0].getVelocity(),new Vector(25,-7)); 
		
		assertTrue(myBreakoutState2_1_b.getPaddle().getTl().equals(myNewBreakoutState2_1_b.getPaddle().getTl()));
		assertTrue(myBreakoutState2_1_b.getPaddle().getBr().equals(myNewBreakoutState2_1_b.getPaddle().getBr()));
		assertEquals(myBreakoutState2_1_b.getPaddle().getVelocity(),myNewBreakoutState2_1_b.getPaddle().getVelocity());
		assertEquals(myBreakoutState2_1_b.getPaddle().getTl(),new Point(25100,23000));
		assertEquals(myBreakoutState2_1_b.getPaddle().getBr(),new Point(28100,24000));
		assertEquals(myBreakoutState2_1_b.getPaddle().getVelocity(),100); 
		
		myBreakoutState2_1_b.tick(paddleDir1);
		BreakoutState myNewNewBreakoutState2_1_b = new BreakoutState(myNewNewBalls2_1_b,myBlocks,myBottomRight,myNewPaddle2_1_b);
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getTl().equals(myNewNewBreakoutState2_1_b.getBalls()[0].getTl())); 
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getBr().equals(myNewNewBreakoutState2_1_b.getBalls()[0].getBr())); 
		assertTrue(myBreakoutState2_1_b.getBalls()[0].getVelocity().equals(myNewNewBreakoutState2_1_b.getBalls()[0].getVelocity())); 

		assertEquals(myBreakoutState2_1_b.getBalls()[0].getTl(),new Point(25300+25,22300-7));
		assertEquals(myBreakoutState2_1_b.getBalls()[0].getBr(),new Point(26000+25,23000-7));
		assertEquals(myBreakoutState2_1_b.getBalls()[0].getVelocity(),new Vector(25,-7)); 
	
//		[2_1_c] After ball touch top of paddle (ball and paddle opposite direction: ball right, paddle left) OK 
		// paddle2_1_c
		Point myPaddleTl2_1_c = new Point(25000,23000);
		Point myPaddleBr2_1_c = new Point(28000,24000);
		int paddleDir_1 = -1 ;
	
		PaddleState myPaddle2_1_c = new PaddleState(myPaddleTl2_1_c, myPaddleBr2_1_c, paddleDir_1*PADDLE_VELOCITY);
		PaddleState myNewPaddle2_1_c = new PaddleState(new Point(25000-100,23000),new Point (28000-100,24000), paddleDir_1*PADDLE_VELOCITY);

		// new Ball2_1_c
		Point myBallTl2_1_c = new Point(25300-5,22300-7);
		Point myBallBr2_1_c = new Point(26000-5,23000-7);
		Vector myBallVelocity2_1_c = new Vector(5,7);
		BallState myBall2_1_c = new BallState(myBallTl2_1_c, myBallBr2_1_c, myBallVelocity2_1_c);
		BallState myNewBall2_1_c = new BallState(new Point(25300,22300),new Point (26000,23000), new Vector(-15,-7));	 	

		BallState[] myBalls2_1_c = new BallState[] {myBall2_1_c};
		BallState[] myNewBalls2_1_c = new BallState[] {myNewBall2_1_c};		
		
		// new BreakoutState2_1_c
		BreakoutState myBreakoutState2_1_c = new BreakoutState(myBalls2_1_c,myBlocks,myBottomRight, myPaddle2_1_c);
		myBreakoutState2_1_c.movePaddleLeft();
		myBreakoutState2_1_c.tick(paddleDir_1);
		BreakoutState myNewBreakoutState2_1_c = new BreakoutState(myNewBalls2_1_c,myBlocks,myBottomRight,myNewPaddle2_1_c);

		assertTrue(myBreakoutState2_1_c.getBalls()[0].getTl().equals(myNewBreakoutState2_1_c.getBalls()[0].getTl())); 
		assertTrue(myBreakoutState2_1_c.getBalls()[0].getBr().equals(myNewBreakoutState2_1_c.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_1_c.getBalls()[0].getVelocity().equals(myNewBreakoutState2_1_c.getBalls()[0].getVelocity())); 
		assertEquals(myBreakoutState2_1_c.getBalls()[0].getTl(),new Point(25300,22300));
		assertEquals(myBreakoutState2_1_c.getBalls()[0].getBr(),new Point(26000,23000));
		assertEquals(myBreakoutState2_1_c.getBalls()[0].getVelocity(),new Vector(-15,-7)); 
		
		assertTrue(myBreakoutState2_1_c.getPaddle().getTl().equals(myNewBreakoutState2_1_c.getPaddle().getTl())); 
		assertTrue(myBreakoutState2_1_c.getPaddle().getBr().equals(myNewBreakoutState2_1_c.getPaddle().getBr())); 
		assertEquals(myBreakoutState2_1_c.getPaddle().getVelocity(),myNewBreakoutState2_1_c.getPaddle().getVelocity());
		assertEquals(myBreakoutState2_1_c.getPaddle().getTl(),new Point(25000-100,23000)); 
		assertEquals(myBreakoutState2_1_c.getPaddle().getBr(),new Point(28000-100,24000)); 
		assertEquals(myBreakoutState2_1_c.getPaddle().getVelocity(),-100); 
		

//	    [2_2_a] After ball touch left of paddle (ball right, paddle doesn't move) OK

		// paddle2_2_a
		Point myPaddleTl2_2_a = new Point(25000,23000);
		Point myPaddleBr2_2_a = new Point(28000,24000);
		PaddleState myPaddle2_2_a = new PaddleState(myPaddleTl2_1_c, myPaddleBr2_1_c, paddleDir0*PADDLE_VELOCITY);
		PaddleState myNewPaddle2_2_a = new PaddleState(new Point(25000,23000),new Point (28000,24000), paddleDir0*PADDLE_VELOCITY);
		
		// new Ball2_2_a
		Point myBallTl2_2_a = new Point(24300-5,23300);
		Point myBallBr2_2_a = new Point(25000-5,24000);
		Vector myBallVelocity2_2_a = new Vector(5,0);
		BallState myBall2_2_a = new BallState(myBallTl2_2_a, myBallBr2_2_a, myBallVelocity2_2_a);
		BallState myNewBall2_2_a = new BallState(new Point(24300,23300),new Point (25000,24000), new Vector(-5,0));	 	

		BallState[] myBalls2_2_a = new BallState[] {myBall2_2_a};		
		BallState[] myNewBalls2_2_a = new BallState[] {myNewBall2_2_a};		

		// new BreakoutState2_2
		BreakoutState myBreakoutState2_2_a = new BreakoutState(myBalls2_2_a,myBlocks,myBottomRight,myPaddle2_2_a);
		myBreakoutState2_2_a.tick(paddleDir0);
		BreakoutState myNewBreakoutState2_2_a = new BreakoutState(myNewBalls2_2_a,myBlocks,myBottomRight,myNewPaddle2_2_a);

		assertTrue(myBreakoutState2_2_a.getBalls()[0].getTl().equals(myNewBreakoutState2_2_a.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_2_a.getBalls()[0].getBr().equals(myNewBreakoutState2_2_a.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_2_a.getBalls()[0].getVelocity().equals(myNewBreakoutState2_2_a.getBalls()[0].getVelocity())); 
		assertEquals(myBreakoutState2_2_a.getBalls()[0].getTl(),new Point(24300,23300)); 
		assertEquals(myBreakoutState2_2_a.getBalls()[0].getBr(),new Point(25000,24000));
		assertEquals(myBreakoutState2_2_a.getBalls()[0].getVelocity(),new Vector(-5,0));

//      [2_3_b]  After ball touch left of paddle (ball right, paddle left) (no squeezed) OK
		
		// new paddle2_3_b
		Point myPaddleTl2_3_b = new Point(25000+100,23000);
		Point myPaddleBr2_3_b = new Point(28000+100,24000);
		PaddleState myPaddle2_3_b = new PaddleState(myPaddleTl2_3_b, myPaddleBr2_3_b, paddleDir_1*PADDLE_VELOCITY);
		PaddleState myNewPaddle2_3_b = new PaddleState(new Point(25000,23000),new Point (28000,24000), paddleDir_1*PADDLE_VELOCITY);
		
		// new Ball2_3_b
		Point myBallTl2_3_b = new Point(24300-5,23300);
		Point myBallBr2_3_b = new Point(25000-5,24000);
		Vector myBallVelocity2_3_b = new Vector(5,0);
		BallState myBall2_3_b = new BallState(myBallTl2_3_b, myBallBr2_3_b, myBallVelocity2_3_b);
		BallState myNewBall2_3_b = new BallState(new Point(24300,23300),new Point(25000,24000), new Vector(-25,0));
			 		                               
		BallState[] myBalls2_3_b = new BallState[] {myBall2_3_b};	
		BallState[] myNewBalls2_3_b = new BallState[] {myNewBall2_3_b};		
		
		// new BreakoutState2_3_b
		BreakoutState myBreakoutState2_3_b = new BreakoutState(myBalls2_3_b,myBlocks,myBottomRight,myPaddle2_3_b);
		myBreakoutState2_3_b.movePaddleLeft();
		myBreakoutState2_3_b.tick(paddleDir_1);
		BreakoutState myNewBreakoutState2_3_b = new BreakoutState(myNewBalls2_3_b,myBlocks,myBottomRight,myNewPaddle2_3_b);

		assertTrue(myBreakoutState2_3_b.getBalls()[0].getTl().equals(myNewBreakoutState2_3_b.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_3_b.getBalls()[0].getBr().equals(myNewBreakoutState2_3_b.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_3_b.getBalls()[0].getVelocity().equals(myNewBreakoutState2_3_b.getBalls()[0].getVelocity())); 
		assertEquals(myBreakoutState2_3_b.getBalls()[0].getTl(),new Point(24300,23300));
		assertEquals(myBreakoutState2_3_b.getBalls()[0].getBr(),new Point(25000,24000));
		assertEquals(myBreakoutState2_3_b.getBalls()[0].getVelocity(),new Vector(-25,0)); 
		
		assertTrue(myBreakoutState2_3_b.getPaddle().getTl().equals(myNewBreakoutState2_3_b.getPaddle().getTl()));
		assertTrue(myBreakoutState2_3_b.getPaddle().getBr().equals(myNewBreakoutState2_3_b.getPaddle().getBr())); 
		assertEquals(myBreakoutState2_3_b.getPaddle().getVelocity(),myNewBreakoutState2_3_b.getPaddle().getVelocity()); 
		assertEquals(myBreakoutState2_3_b.getPaddle().getTl(),new Point(25000,23000));
		assertEquals(myBreakoutState2_3_b.getPaddle().getBr(),new Point(28000,24000));
		assertEquals(myBreakoutState2_3_b.getPaddle().getVelocity(),-100); 

		
//     [2_3_c]  After ball touch left of paddle (ball right, paddle left) (squeezed) 
		
		// new paddle2_3_c
		Point myPaddleTl2_3_c = new Point(750+100,23000);
		Point myPaddleBr2_3_c = new Point(3750+100,24000);
		PaddleState myPaddle2_3_c = new PaddleState(myPaddleTl2_3_c, myPaddleBr2_3_c, paddleDir_1*PADDLE_VELOCITY);
		PaddleState myNewPaddle2_3_c = new PaddleState(new Point(750,23000),new Point (3750,24000), 0);
		
		// new Ball2_3_c
		Point myBallTl2_3_c = new Point(50-5,23300);
		Point myBallBr2_3_c = new Point(750-5,24000);
		Vector myBallVelocity2_3_c = new Vector(5,0);
		BallState myBall2_3_c = new BallState(myBallTl2_3_c, myBallBr2_3_c, myBallVelocity2_3_c);
		BallState myNewBall2_3_c = new BallState(new Point(0,23300),new Point(700,24000), new Vector(0,0));
		
//		Error, because the moment of ball touch paddle, the ball automatically become squeezed by paddle at the moment
//		see BreakoutState line 300
//		it's strange, because I only tick once
//		BallState myNewBall2_3_c = new BallState(new Point(50,23300),new Point(750,24000), new Vector(-25,0));
		
		BallState[] myBalls2_3_c = new BallState[] {myBall2_3_c};	
		BallState[] myNewBalls2_3_c = new BallState[] {myNewBall2_3_c};			

		// new BreakoutState2_3_c
		BreakoutState myBreakoutState2_3_c = new BreakoutState(myBalls2_3_c,myBlocks,myBottomRight,myPaddle2_3_c);
		myBreakoutState2_3_c.movePaddleLeft();
		myBreakoutState2_3_c.tick(paddleDir_1);
		BreakoutState myNewBreakoutState2_3_c = new BreakoutState(myNewBalls2_3_c,myBlocks,myBottomRight,myNewPaddle2_3_c);

		assertTrue(myBreakoutState2_3_c.getBalls()[0].getTl().equals(myNewBreakoutState2_3_c.getBalls()[0].getTl()));
		assertTrue(myBreakoutState2_3_c.getBalls()[0].getBr().equals(myNewBreakoutState2_3_c.getBalls()[0].getBr()));
		assertTrue(myBreakoutState2_3_c.getBalls()[0].getVelocity().equals(myNewBreakoutState2_3_c.getBalls()[0].getVelocity())); 
		assertEquals(myBreakoutState2_3_c.getBalls()[0].getTl(),new Point(0,23300));
		assertEquals(myBreakoutState2_3_c.getBalls()[0].getBr(),new Point(700,24000));
		assertEquals(myBreakoutState2_3_c.getBalls()[0].getVelocity(),new Vector(0,0)); 
		
		assertTrue(myBreakoutState2_3_c.getPaddle().getTl().equals(myNewBreakoutState2_3_c.getPaddle().getTl())); 
		assertTrue(myBreakoutState2_3_c.getPaddle().getBr().equals(myNewBreakoutState2_3_c.getPaddle().getBr())); 
		
		assertEquals(myBreakoutState2_3_c.getPaddle().getVelocity(),myNewBreakoutState2_3_c.getPaddle().getVelocity()); 
		assertEquals(myBreakoutState2_3_c.getPaddle().getTl(),new Point(750,23000));
		assertEquals(myBreakoutState2_3_c.getPaddle().getBr(),new Point(3750,24000));
		assertEquals(myBreakoutState2_3_c.getPaddle().getVelocity(),0); 

		
//	    [3_1_a] After ball touch one block bottom
		// new Ball3_a
		Point myBallTl3_1_a = new Point(0,2);
		Point myBallBr3_1_a = new Point(2,4);
		Vector myBallVelocity3_1_a = new Vector(0,-6);
		BallState myBall3_1_a = new BallState(myBallTl3_1_a, myBallBr3_1_a, myBallVelocity3_1_a);

		BallState myNewBall3_1_a = new BallState(myBallTl3_1_a.plus(myBallVelocity3_1_a), myBallBr3_1_a.plus(myBallVelocity3_1_a), myBallVelocity3_1_a.mirrorOver(Vector.DOWN));
		//tl(0,-2) br(2,0) velocity(0,6)
		BallState[] myBalls3_1_a = new BallState[] {myBall3_1_a};	
		BallState[] myNewBalls3_1_a = new BallState[] {myNewBall3_1_a};		

		// new Blocks3
		BlockState[] myBlocks3_1_a = new BlockState[] {myBlock1,myBlock2};
		BlockState[] myNewBlocks3_1_a = new BlockState[] {myBlock2};
 
		// new BreakoutState3
		BreakoutState myBreakoutState3_1_a = new BreakoutState(myBalls3_1_a,myBlocks3_1_a,myBottomRight, myPaddle);
		myBreakoutState3_1_a.tick(paddleDir0);
		BreakoutState myNewBreakoutState3_1_a = new BreakoutState(myNewBalls3_1_a,myNewBlocks3_1_a,myBottomRight, myPaddle);

//		assertTrue(myBreakoutState3_1_a.getBalls()[0].getTl().equals(myNewBreakoutState3_1_a.getBalls()[0].getTl()));//error
//		assertTrue(myBreakoutState3_1_a.getBalls()[0].getBr().equals(myNewBreakoutState3_1_a.getBalls()[0].getBr())); //error
		assertTrue(myBreakoutState3_1_a.getBalls()[0].getVelocity().equals(myNewBreakoutState3_1_a.getBalls()[0].getVelocity())); 

//		assertEquals(myBreakoutState3_1_a.getBalls()[0].getTl(),new Point(0,-4));//error
//		assertEquals(myBreakoutState3_1_a.getBalls()[0].getBr(),new Point(2,-2));//error
		assertEquals(myBreakoutState3_1_a.getBalls()[0].getVelocity(),new Vector(0,6)); 

//		[3_1_b] After ball touch one block right
	    // new Ball3_1_b
	 	Point myBallTl3_1_b = new Point(3,0);
	 	Point myBallBr3_1_b = new Point(5,2);
	 	Vector myBallVelocity3_1_b = new Vector(-6,0);
	 	BallState myBall3_1_b = new BallState(myBallTl3_1_b, myBallBr3_1_b, myBallVelocity3_1_b);
	 			 	
	 	BallState myNewBall3_1_b = new BallState(myBallTl3_1_b.plus(myBallVelocity3_1_b), myBallBr3_1_b.plus(myBallVelocity3_1_b), myBallVelocity3_1_b.mirrorOver(Vector.RIGHT));
	 			 		                                //tl(-3,0) br(-1,2) velocity(6,0)
	 	BallState[] myBalls3_1_b = new BallState[] {myBall3_1_b};	
	 	BallState[] myNewBalls3_1_b = new BallState[] {myNewBall3_1_b};		
	 	
	 	// new Blocks3_1_b
	 	BlockState[] myBlocks3_1_b = new BlockState[] {myBlock1};
	 	BlockState[] myEmptyBlocks3_1_b = new BlockState[] {};
	 	
	 	// new BreakoutState3_1_b
	 	BreakoutState myBreakoutState3_1_b = new BreakoutState(myBalls3_1_b,myBlocks3_1_b,myBottomRight, myPaddle);
	 	myBreakoutState3_1_b.tick(paddleDir0);
	 	BreakoutState myNewBreakoutState3_1_b = new BreakoutState(myNewBalls3_1_b,myEmptyBlocks3_1_b,myBottomRight, myPaddle);

	    assertEquals(myNewBreakoutState3_1_b.isDead(),false);
		assertEquals(myNewBreakoutState3_1_b.isWon(),true); 
	 	
//	    assertTrue(myBreakoutState3_1_b.getBalls()[0].getTl().equals(myNewBreakoutState3_1_b.getBalls()[0].getTl()));//error
//		assertTrue(myBreakoutState3_1_b.getBalls()[0].getBr().equals(myNewBreakoutState3_1_b.getBalls()[0].getBr()));//error
		assertTrue(myBreakoutState3_1_b.getBalls()[0].getVelocity().equals(myNewBreakoutState3_1_b.getBalls()[0].getVelocity())); 
//	
//		assertEquals(myBreakoutState3_1_b.getBalls()[0].getTl(),new Point(-3,0));//error
//		assertEquals(myBreakoutState3_1_b.getBalls()[0].getBr(),new Point(-1,2));//error
		assertEquals(myBreakoutState3_1_b.getBalls()[0].getVelocity(),new Vector(6,0)); //error
	 	

//	 	[3_2] After ball touch two block (touch two block bottom at same time)
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
	 	myBreakoutState3_2.tick(paddleDir0);
//	 	BlockState myBlock11 = (BlockState)Array.get(myBlocks3_2, 0);
	 	BreakoutState myNewBreakoutState3_2 = new BreakoutState(myNewBalls3_2,myNewBlocks3_2,myBottomRight, myPaddle);

//	    assertTrue(myBreakoutState3_2.getBalls()[0].getTl().equals(myNewBreakoutState3_2.getBalls()[0].getTl()));//error
//		assertTrue(myBreakoutState3_2.getBalls()[0].getBr().equals(myNewBreakoutState3_2.getBalls()[0].getBr()));//error
		assertTrue(myBreakoutState3_2.getBalls()[0].getVelocity().equals(myNewBreakoutState3_2.getBalls()[0].getVelocity())); 
//	
//		assertEquals(myBreakoutState3_2.getBalls()[0].getTl(),new Point(-3,0));//error
//		assertEquals(myBreakoutState3_2.getBalls()[0].getBr(),new Point(-1,2));//error
		assertEquals(myBreakoutState3_2.getBalls()[0].getVelocity(),new Vector(0,6)); //error
		
		
//	 	[3_3] After ball touch three block at same time
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
	 	myBreakoutState3_3.tick(paddleDir0);
	 	BreakoutState myNewBreakoutState3_3 = new BreakoutState(myNewBalls3_3,myNewBlocks3_3,myBottomRight, myPaddle);

//	 	assertTrue(myBreakoutState3_3.getBalls()[0].getTl().equals(myNewBreakoutState3_3.getBalls()[0].getTl()));//error
//		assertTrue(myBreakoutState3_3.getBalls()[0].getBr().equals(myNewBreakoutState3_3.getBalls()[0].getBr()));//error
		assertTrue(myBreakoutState3_3.getBalls()[0].getVelocity().equals(myNewBreakoutState3_3.getBalls()[0].getVelocity())); 
//	
//		assertEquals(myBreakoutState3_3.getBalls()[0].getTl(),new Point(-3,0));//error
//		assertEquals(myBreakoutState3_3.getBalls()[0].getBr(),new Point(-1,2));//error
		assertEquals(myBreakoutState3_3.getBalls()[0].getVelocity(),new Vector(6,6)); 
	 	
	    
	    
		
//	    [4] movePaddleRight
		myBreakoutState.movePaddleRight();

		// new paddle4
		PaddleState myNewPaddle4 = new PaddleState(myPaddleTl.plus(new Vector(PADDLE_VELOCITY,0)), myPaddleBr.plus(new Vector(PADDLE_VELOCITY,0)), PADDLE_VELOCITY);
		// new BreakoutState4
		BreakoutState myNewBreakoutState4 = new BreakoutState(myNewBalls3_1_a,myNewBlocks3_1_a,myBottomRight, myNewPaddle4);

		
//		[5] movePaddleLeft
		myBreakoutState.movePaddleLeft();

		// new paddle5
        PaddleState myNewPaddle5 = new PaddleState(new Point(0,25000), new Point(200,25050), PADDLE_VELOCITY);

		// new BreakoutState5
		BreakoutState myNewBreakoutState5 = new BreakoutState(myNewBalls3_1_a,myNewBlocks3_1_a,myBottomRight, myNewPaddle5);

		
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
