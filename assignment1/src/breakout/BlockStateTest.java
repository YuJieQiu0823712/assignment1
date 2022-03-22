package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BlockStateTest {
 
	@Test
	void test() {
		
		Point myTl = new Point(0,0);
		Point myBr = new Point(2,2);
		BlockState myBlockState = new BlockState(myTl, myBr);

		assertEquals(new Point(0,0),myBlockState.getTl());
		assertEquals(new Point(2,2),myBlockState.getBr());
		assertEquals(new Point(1,1),myBlockState.getPosition());
		assertEquals(4,myBlockState.getSize());
		
	}

}
