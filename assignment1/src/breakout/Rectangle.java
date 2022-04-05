package breakout;

/**
 * 
// * @invar | 0<=getTl().getX() && getTl().getX()<=GameMap.getWidth()-(getBr().getX()-getTl().getX())
// * @invar | getTl().getX()<=getBr().getX() && getBr().getX()<=GameMap.getWidth()
 */


public abstract class Rectangle {
	
	
	private final Point tl;
	private final Point br;
	/**
//	* @post | 0 <= tl.getX() 
//	* @post | tl.getX() <= br.getX() && br.getX() <= GameMap.getWidth()
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

	public Point getPosition() {
		Point center = new Point((tl.getX()+br.getX())/2, (tl.getY()+br.getY())/2);
		return center;
	}
	
	public int getSize() {
		int width = br.getY()-tl.getY();
		int length = br.getX()-tl.getX();
		return width*length;
	}
	
	
	
	

}