package breakout;
/**
 * 
 * Abstract state invariants:
 * @invar | getTl() != null
 * @invar | getBr() != null
 //* @invar | getTl().getX()>=0 && getBr().getX()<= 右邊界
 //* @invar | getTl().getY()>=最後一個block.getBr().getY && getBr().getY()<= 下邊界 
 * 
 *@immutable
 */

public class PaddleState {
	// TODO: implement
	// contractual programming
	private final Point tl;
	private final Point br;
	private final int velocity;


	public int getVelocity() {
		return velocity;
	}


	public PaddleState(Point tl, Point br,int velocity) {
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

	public Point getPosition() {
		Point center = new Point((this.tl.getX()+this.br.getX())/2,(this.tl.getY()+this.br.getY())/2);
		return center;
	}
	
	public int getSize() {
		int width = br.getY()-tl.getY();
		int length = br.getX()-tl.getX();
		return width*length;
	}


}
