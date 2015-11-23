package util;

public class Pair<T1,T2> {
	private T1 x=null;
	private T2 y=null;
	
	public Pair(T1 x, T2 y) {
		this.setX(x);
		this.setY(y);
	}

	public T1 getX() {
		return x;
	}

	public void setX(T1 x) {
		this.x = x;
	}

	public T2 getY() {
		return y;
	}

	public void setY(T2 y) {
		this.y = y;
	}
	
}
