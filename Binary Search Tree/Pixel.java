
public class Pixel {
	
	private Location locationKey;
	private int pColor;

	public Pixel(Location p, int color){
		locationKey = p;
		pColor = color;
	}
	
	public Location getLocation(){
		return locationKey;
	}
	
	public int getColor(){
		return pColor;
	}
	
}
