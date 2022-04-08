package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RectangleTest {

	@Test
	void test() {
		Point myTl = new Point(0,0);
		Point myBr = new Point(2,2);
		Rectangle myRectangleBlock = new BlockState(myTl, myBr);
		
		assertEquals(new Point(0,0),myRectangleBlock.getTl());
		assertEquals(new Point(2,2),myRectangleBlock.getBr());
		assertEquals(new Point(1,1),myRectangleBlock.getPosition());
		assertEquals(4,myRectangleBlock.getSize());
		
		
		
		
		int velocity = 10;
		Rectangle myRectanglePaddle = new PaddleState(myTl,myBr,velocity);
		assertEquals(new Point(0,0),myRectanglePaddle.getTl());
		assertEquals(new Point(2,2),myRectanglePaddle.getBr());
		assertEquals(new Point(1,1),myRectanglePaddle.getPosition());
		assertEquals(4,myRectanglePaddle.getSize());
		
		
		assertEquals(true, myRectangleBlock instanceof BlockState);
		assertEquals(true, myRectanglePaddle instanceof PaddleState);
		
		assertEquals(false, myRectanglePaddle instanceof BlockState);
		assertEquals(false, myRectangleBlock instanceof PaddleState);
		
		
		
	}

}
