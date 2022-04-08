package breakout;

/** 
 * Abstract state invariants:
 * @immutable
 * @invar The given top left point is not null.
 * | getTl() != null
 * @invar The given bottom right point is not null.
 * | getBr() != null
 * @invar The given left edge is not greater than the given right edge. 
 * | getTl().getX() <= getBr().getX()
 * @invar The given top is not greater than the given bottom.
 * | getTl().getY() <= getBr().getY()
 * 
 * @invar The given center is not null.
 * | getCenter() !=null
 * @invar The center of the ball is in the middle of the left bound and right bound.
 * | (getTl().getX() + getBr().getX())/2 == getCenter().getX() 
 * @invar The center of the ball is in the middle of the top and the bottom.
 * | (getTl().getY() + getBr().getY())/2 == getCenter().getY()  
 * 
 * @invar The given velocity is not null.
 * | getVelocity() !=null
 * 
 * @invar The size of the ball is nonnegative.
 * | getSize() >= 0
 * @invar The size of the ball
 * | getSize() == (int) Math.round((Math.PI*(getRadius())*(getRadius())))
 * @invar The radius of the ball is nonnegative.
 * 
 * | getRadius() >= 0
 * @invar The radius of the ball is half of the difference between the top and the bottom of the ball.
 * | getRadius() == (getBr().getY()-getTl().getY())/2
 * 
 * 
 */

public class BallState {
	// TODO: implement
	
	
	/**
	 * Representation invariants
	 * 
	 * @invar The given top left point is not null.
	 * | tl !=null
	 * @invar The given bottom right point is not null.
	 * | br !=null
	 *  @invar The given left edge is not greater than the given right edge. 
	 * | tl.getX() <= br.getX()
	 * @invar The given top is not greater than the given bottom.
	 * | tl.getY() <= br.getY()
	 * @invar The given velocity is not null.
	 * | velocity !=null
	 */
	private final Point tl;
	private final Point br;
	private final Vector velocity;
	

	/**
	 * Initializes this object with the given tl, br, velocity.
	 * @pre The given top left point is not null.
	 * | tl != null
	 * @pre The given bottom right point is not null.
	 * | br != null
	 * @pre The given velocity is not null.
	 * | velocity != null
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
	 * | tl.getX() <= br.getX()
	 * @post The given top is not greater than the given bottom. 
	 * | tl.getY() <= br.getY()
	 * @post | getVelocity().equals(velocity)
	 * 
	 */
	public BallState(Point tl, Point br, Vector velocity) {
		this.tl = tl;
		this.br = br;
		this.velocity = velocity;
	}
	
	
	/**
	 * @post The center of the ball is not null.
	 * | result != null
	 * @post The center of the ball is in the middle of the left bound and right bound.
	 * | (getTl().getX() + getBr().getX())/2 == result.getX() 
	 * @post The center of the ball is in the middle of the top and the bottom.
	 * | (getTl().getY() + getBr().getY())/2 == result.getY()  
	 * 
	 * @create | result
	 * @inspect | this
	 * 
	 */
	public Point getCenter() {
		Point center = new Point((getTl().getX()+getBr().getX())/2,(getTl().getY()+getBr().getY())/2);
		return center;
	}
	

	public Vector getVelocity() {
		return this.velocity;
	}
	

	public Point getTl() {
		return tl;
	}

	public Point getBr() {
		return br;
	}

	/**
	 * @post The size of the ball is nonnegative.
	 * | result >= 0
	 * @post The size of the ball
	 * | result == (int) Math.round((Math.PI*(getRadius())*(getRadius())))
	 * @create | result 
	 */
	public int getSize() {
		return (int) Math.round((Math.PI*(getRadius())*(getRadius())));
	}
	
	/**
	 * @post The radius of the ball is nonnegative.
	 * | result >= 0
	 * @post The radius of the ball is half of the difference between the top and the bottom of the ball.
	 * | result == (getBr().getY()-getTl().getY())/2
	 * @create | result
	 */
	public int getRadius() {
		int radius = (br.getY()-tl.getY())/2;
		return radius;
	}
	
	
	

	



	




}
