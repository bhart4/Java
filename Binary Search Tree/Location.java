
public class Location {
	
	private int x;
	private int y;
	
	public Location(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int xCoord(){
		return this.x;
	}
	
	public int yCoord(){
		return this.y;
	}
	
	public int compareTo(Location p){
		int pY = p.yCoord();
		int pX = p.xCoord();
		
		if ((pX == p.x) && (pY == p.y)) {
			return 0;
		}
		else if (p.y < pY || (p.y == pY && p.x < pX)) {
			return 1;
		}
		else {
			return -1;
		}
	}

}
