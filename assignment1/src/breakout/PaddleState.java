package breakout;
/**
 * 
 * Abstract state invariants:
 * 
 * @invar The velocity of the paddle is a multiple of 10.
 * | getVelocity() % 10 == 0
 * 
 * @immutable
 */

public class PaddleState extends Rectangle{
	// TODO: implement
	
	/**
	 * @invar The velocity of the paddle is a multiple of 10.
	 * | velocity % 10 == 0
	 */
	
	private final int velocity;

	/**
	 * 
	 * @pre The given top left point is not null.
	 * | tl != null
	 * @pre The given bottom right point is not null.
	 * | br != null
	 * @pre The given left edge is not greater than the given right edge.
	 * | tl.getX() <= br.getX()
	 * @pre The given top is not greater than the given bottom. 
	 * | tl.getY() <= br.getY()
	 * @pre The velocity of the paddle is a multiple of 10.
	 * | velocity % 10 == 0
	 * 	
	 * 
	 * @post The given top left point is not null.
	 * | getTl() != null
	 * @post The given bottom right point is not null.
	 * | getBr() != null
	 * @post The top left point of the rectangle equals the given top left point.
	 * | getTl().equals(tl)
	 * @post The bottom right point of the rectangle equals the given bottom right point.
	 * | getBr().equals(br)
	 * @post The given left edge is not greater than the given right edge. 
	 * | getTl().getX() <= getBr().getX()
	 * @post The given top is not greater than the given bottom. 
	 * | getTl().getY() <= getBr().getY()
	 * @post The velocity of the paddle is a multiple of 10.
	 * | getVelocity() % 10 == 0
	 * 
	 */


	public PaddleState(Point tl, Point br,int velocity) {
		super(tl,br);
		this.velocity = velocity;
	}


	public int getVelocity() {
		return velocity;
	}
	
	

}
