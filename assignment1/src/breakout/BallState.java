package breakout;

/**
 * Abstract state invariants:
 * @immutable
 * @invar | getCenter() !=null
 * @invar | getVelocity() !=null
 * @invar | getTl() !=null
 * @invar | getBr() !=null
 * @invar | getSize() >= 0
 * @invar | getRadius() >= 0
 */

public class BallState {
	// TODO: implement
	// contractual programming
	// you do not write any code in the method body to check whether the arguments are legal; instead, you implement the method under the assumption that the arguments are legal. You include one or more @pre clauses in the Javadoc
	// https://btj.github.io/ogp-notes/single_object_doc_instr.html

	
	/**
	 * Representation invariants
	 * 
	 * @invar | tl !=null
	 * @invar | br !=null
	 * @invar | velocity !=null
	 */
	
	private final Point tl;
	private final Point br;
	private final Vector velocity;
	

 
	/**
	 * Initializes this object with the given tl, br, velocity.
	 * @pre | tl != null
	 * @pre | br != null
	 * @pre | velocity != null
	 * @post | getTl() == tl
	 * @post | getBr() == br
	 * @post | getVelocity() == velocity
	 */
	public BallState(Point tl, Point br, Vector velocity) {

		this.tl = tl;
		this.br = br;
		this.velocity = velocity;
	}
	

	/**
	 * @post | result != null
	 * @create | result
	 */
	public Point getCenter() {
		Point center = new Point((tl.getX()+br.getX())/2,(tl.getY()+br.getY())/2);
		return center; 
	}
	
	
	public Vector getVelocity() {
		return velocity;
	}
	

	public Point getTl() {
		return tl;
	}

	public Point getBr() {
		return br;        
	}

	/**
	 * @post | result >= 0
	 * @create | result 
	 */
	
	public int getSize() {
		return (int) Math.round((Math.PI*(getRadius())*(getRadius())));
	}

	/**
	 * @post | result >= 0
	 * @create | result
	 */
	public int getRadius() {
		int radius = (br.getY()-tl.getY())/2;
		return radius;
	}
	
	



	




}
