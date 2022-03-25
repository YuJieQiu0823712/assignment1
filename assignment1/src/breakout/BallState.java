package breakout;

/**
 * Abstract state invariants:
 * @immutable
 * @invar | getCenter() !=null
 * @invar | getVelocity() !=null
 * @invar | getTl() !=null
 * @invar | getTl() !=null
 * @invar | getBr() !=null
 * @invar | getSize() == (int) Math.round((Math.PI*(getRadius())*(getRadius())))
 * @invar | getRadius() == (getBr().getY()-getTl().getY())/2
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
	 * @throws IllegalArgumentEception
	 *   | tl == null || br == null || velocity == null
	 */
	public BallState(Point tl, Point br, Vector velocity) {
		if(tl == null || br == null || velocity == null){
			throw new IllegalArgumentException("tl,br,and velocity should not be null");
		}
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
		return this.velocity;
	}
	

	public Point getTl() {
		return this.tl;
	}

	public Point getBr() {
		return this.br;        
	}

	/**
	 * @create | result 
	 */
	
	public int getSize() {
		return (int) Math.round((Math.PI*(getRadius())*(getRadius())));
	}

	/**
	 * @create | result
	 */
	public int getRadius() {
		int radius = (br.getY()-tl.getY())/2;
		return radius;
	}



	




}
