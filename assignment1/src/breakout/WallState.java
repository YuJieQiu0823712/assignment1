package breakout;

/**
 * 
 * Abstract state invariants:
 * @invar The given top left point is not null.
 * | getTl() != null
 * @invar The given bottom right point is not null.
 * | getBr() != null
 * @invar The given left edge is not greater than the given right edge. 
 * | getTl().getX() <= getBr().getX()
 * @invar The given top is not greater than the given bottom.
 * | getTl().getY() <= getBr().getY()
 * 
 * @immutable
 */

public class WallState extends Rectangle{
	
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
	 * 
	 */
	
	public WallState(Point tl, Point br) {
		super(tl, br);
	}

}
