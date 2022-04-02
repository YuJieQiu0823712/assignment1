package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BallStateTest {
 
	@Test
	void test() {
		
		Point myTl = new Point(0,0);
		Point myBr = new Point(2,2);
		Vector myVelocity = new Vector(5,7);
		BallState myBallState = new BallState(myTl, myBr, myVelocity);
		
		assertEquals(new Point(1,1), myBallState.getCenter());
		assertEquals(new Vector(5,7), myBallState.getVelocity());
		assertEquals(new Point(0,0),myBallState.getTl());
		assertEquals(new Point(2,2),myBallState.getBr());
		assertEquals(3,myBallState.getSize());
		assertEquals(1,myBallState.getRadius());
		
		
		
	}

}
