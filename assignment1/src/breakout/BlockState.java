package breakout;

/**
 * 
 * Abstract state invariants:
 * @invar | getTl() != null
 * @invar | getBr() != null
 * 
 * @immutable
 */


public class BlockState {
	// TODO: implement
	// contractual programming
	
	/**
	 * Representation invariants:
	 * 
	 * @invar | tl != null
     * @invar | br != null
	 */
	private final Point tl;
	private final Point br;
	
	
	/**
	 * 
	 * @pre | tl != null
	 * @pre | br != null
	 * @post | getTl() == tl
	 * @post | getBr() == br
	 */
 
	public BlockState(Point tl, Point br) {
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
	 * @post | result !=null
	 * @creates | result
	 */
	

	public Point getPosition() {
		Point center = new Point((tl.getX()+br.getX())/2, (tl.getY()+br.getY())/2);
		return center;
	}
	
	/**
	 * @post | result >= 0
	 * @creates | result
	 */
	
	public int getSize() {
		int width = br.getY()-tl.getY();
		int length = br.getX()-tl.getX();
		return width*length;
	}
	

}


