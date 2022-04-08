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
 * @invar The given center is not null.
 * |getPosition() != null
 * @invar The center of the rectangle is in the middle of the left bound and right bound.
 * | (getTl().getX() + getBr().getX())/2 == getPosition().getX() 
 * @invar The center of the rectangle is in the middle of the top and the bottom.
 * | (getTl().getY() + getBr().getY())/2 == getPosition().getY()  
 * 
 * @invar The size of the rectangle is the product of its length and width.
 * | getSize() == (getBr().getX()-getTl().getX()) * (getBr().getY()-getTl().getY())
 * @invar The size of the rectangle is positive.
 * | getSize() >= 0
 * 
 * @immutable
 */

public abstract class Rectangle {
	
	
	/**
	 * @invar The given top left point is not null.
	 * | tl != null
	 * @invar The given bottom right point is not null.
	 * | br != null
	 * @invar The given left edge is not greater than the given right edge. 
	 * | tl.getX() <= br.getX()
	 * @invar The given top is not greater than the given bottom.
	 * | tl.getY() <= br.getY()
	 */
	
	private final Point tl;
	private final Point br;
	
	
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
	public Rectangle(Point tl, Point br) {
		this.tl = tl;
		this.br = br;
	}
	
	public Point getTl() {
		return tl;
	}

	public Point getBr() {
		return br;
	}
	
	/**
	 * 
	 * @create | result
	 * @inspect | this
	 * @post The center of the rectangle is not null.
	 * | result != null
	 * @post The center of the rectangle is in the middle of the left bound and right bound.
	 * | (getTl().getX() + getBr().getX())/2 == result.getX() 
	 * @post The center of the rectangle is in the middle of the top and the bottom.
	 * | (getTl().getY() + getBr().getY())/2 == result.getY()  
	 */
	public Point getPosition() {
		Point center = new Point((tl.getX()+br.getX())/2, (tl.getY()+br.getY())/2);
		return center;
	}
	
	/**
	 * @post The size of the rectangle is the product of its length and width.
	 * | result == (getBr().getX()-getTl().getX()) * (getBr().getY()-getTl().getY())
	 * @post The size of the rectangle is positive.
	 * | result >= 0
	 * @create | result
	 */
	public int getSize() {
		int width = br.getY()-tl.getY();
		int length = br.getX()-tl.getX();
		return width*length;
	}
	
	
	
	

}
