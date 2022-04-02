package breakout;


import java.util.ArrayList;

public class GameMap {
	
	private static final int INIT_BALL_DIAMETER = 700;
	private static final int HEIGHT = 30000;
	private static final int WIDTH = 50000;
	private static int BLOCK_LINES = 9;
	private static int BLOCK_COLUMNS = 10;
	private static final Vector INIT_BALL_VELOCITY = new Vector(25,35);

	private static BlockState createBlock(Point topleft) {
		Vector marginBL = new Vector(20,20);
		Vector size = new Vector(WIDTH/BLOCK_COLUMNS-70,HEIGHT/BLOCK_LINES-70);
		Point blockTL = topleft.plus(marginBL);
		Point blockBR = blockTL.plus(size);
		// TODO: return a block with given top left (`blockTL`) and bottom right (`blockBR`) Point
		BlockState block = new BlockState(blockTL,blockBR);
		return block;
	}
	
	private static PaddleState createPaddle(Point topleft) {
		Vector size = new Vector(WIDTH/BLOCK_COLUMNS/2,HEIGHT/BLOCK_LINES/2);
		Point center = topleft.plus(size);
		// TODO: return a paddle with given center
		return new PaddleState(topleft,new Point(center.getX(),center.getY()-(center.getY()-topleft.getY())/2),0);
		
	}
	
	private static BallState createBall(Point topleft) {
		Vector centerD = new Vector(WIDTH/BLOCK_COLUMNS/2,HEIGHT/BLOCK_LINES/2);
		Point center = topleft.plus(centerD);
		int diameter = INIT_BALL_DIAMETER;
		// TODO: return a ball with given `center`, `diameter` and initial speed `initvelocity`
		return new BallState(new Point(center.getX()-(diameter/2),center.getY()-(diameter/2)),new Point(center.getX()+(diameter/2),center.getY()+(diameter/2)),INIT_BALL_VELOCITY);
	}
		
	/**
	 * Return the initial breakout state represented by string `description`.
	 * @pre | description != null
	 * @post | result != null
	 */
	public static BreakoutState createStateFromDescription(String description) {
		String[] lines = description.split("\n", BLOCK_LINES);
		
		Vector unitVecRight = new Vector(WIDTH/BLOCK_COLUMNS,0);
		Vector unitVecDown = new Vector(0,HEIGHT/BLOCK_LINES);
		ArrayList<BlockState> blocks = new ArrayList<BlockState>();
		ArrayList<BallState> balls = new ArrayList<BallState>();
		PaddleState paddle = null;
		
		Point topLeft = new Point(0,0);
		assert lines.length <= BLOCK_LINES;
		for(String line : lines) {
			assert line.length() <= BLOCK_COLUMNS;
			Point cursor = topLeft;
			for(char c : line.toCharArray()) {
				switch(c) {
				case '#': blocks.add(createBlock(cursor)); break;
				case 'o': balls.add(createBall(cursor)); break;
				case '=': paddle = createPaddle(cursor); break;
				}
				cursor = cursor.plus(unitVecRight);
			}
			topLeft = topLeft.plus(unitVecDown);
		}
		Point bottomRight = new Point(WIDTH, HEIGHT);
		
		return new BreakoutState(balls.toArray(new BallState[] {}), blocks.toArray(new BlockState[] {}), bottomRight, paddle);
	}
	public static int getHeight() {
		return HEIGHT;
	}
	public static int getWidth() {
		return WIDTH;
	}
	
	
}
