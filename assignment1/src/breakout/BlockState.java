package breakout;

/**
 * @immutable
 * * Abstract state invariants:
 * @invar | getTl() != null
 * @invar | getBr() != null
 //* @invar | getTl().getX()>=0 && getBr().getX()<= 右邊界
 //* @invar | getTl().getY()>=0 && getBr().getX()<= 下邊界 or paddle.getTl().getY
 * 
 **/


public class BlockState {
	// TODO: implement
	// contractual programming
	private final Point tl;
	private final Point br;
 
	public BlockState(Point tl, Point br) {
		this.tl = tl;
		this.br = br;
	}

	public Point getTl() {
		return this.tl;
	}


	public Point getBr() {
		return this.br;
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
