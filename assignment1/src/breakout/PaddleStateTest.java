package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaddleStateTest {

	@Test
	void test() {
		
		Point myTl = new Point(0,0);
		Point myBr = new Point(2,2);
		int myVelocity = 10;
		PaddleState myPaddleState = new PaddleState(myTl, myBr, myVelocity);

		assertEquals(10, myPaddleState.getVelocity());
		assertEquals(new Point(0,0),myPaddleState.getTl());
		assertEquals(new Point(2,2),myPaddleState.getBr());
		assertEquals(new Point(1,1),myPaddleState.getPosition());
		assertEquals(4,myPaddleState.getSize());
	}

}
