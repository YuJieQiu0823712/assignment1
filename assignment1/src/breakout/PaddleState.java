package breakout;
/**
 * 
 * Abstract state invariants:
 * @invar | getTl() != null
 * @invar | getBr() != null
 * 
 *@immutable
 */

public class PaddleState {
	// TODO: implement
	// contractual programming
	/**
	 * Representation invariants:
	 * 
//	 * @invar | tl.getX() <= br.getX()
//	 * @invar | tl.getY() <= br.getY() //?
	 * @invar | tl != null
     * @invar | br != null
	 */
	private final Point tl;
	private final Point br;
	private final int velocity;

	/**
	 * 
	 * @pre | tl != null
	 * @pre | br != null
	 * 
	 * @post | getTl() == tl
	 * @post | getBr() == br
	 * @post | getVelocity() == velocity //add
	 * @throws IllegalArgumentEception //add
	 *   | tl == null || br == null 
	 */
	



	public PaddleState(Point tl, Point br,int velocity) {
		 if(tl == null || br == null ){
				throw new IllegalArgumentException("tl,br,and velocity should not be null");
			}
		this.tl = tl;
		this.br = br;
		this.velocity = velocity;
	}

	public Point getTl() {
		return tl;
	}

	public Point getBr() {
		return br;
	}
	public int getVelocity() {
		return velocity;
	}

	/**
	 * @post | result != null
	 * @create | result
	 */
	
	public Point getPosition() {
		Point center = new Point((tl.getX()+br.getX())/2,(tl.getY()+br.getY())/2);
		return center;
	}
	
	/**
	 * 
	 * @creates | result
	 */
	
	public int getSize() {
		int width = br.getY()-tl.getY();
		int length = br.getX()-tl.getX();
		return width*length;
	}


}
