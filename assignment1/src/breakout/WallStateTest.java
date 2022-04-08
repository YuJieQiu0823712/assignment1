package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WallStateTest {

	@Test
	void test() {
		Point myTl = new Point(0,0);
		Point myBr = new Point(2,2);
		WallState myWallState = new WallState(myTl, myBr);

		assertEquals(new Point(0,0),myWallState.getTl());
		assertEquals(new Point(2,2),myWallState.getBr());
		assertEquals(new Point(1,1),myWallState.getPosition());
		assertEquals(4,myWallState.getSize());
		
		
	}

}
