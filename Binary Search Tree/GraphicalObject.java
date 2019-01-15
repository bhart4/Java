
public class GraphicalObject implements GraphicalObjectADT {
	
	private BinarySearchTree tree;
	private int id, width, height;
	private String type;
	private Location pos;
	
	public GraphicalObject (int id, int width, int height, String type, Location pos){
		tree = new BinarySearchTree();
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;		
	}
	
	public void setType (String type) {
		this.type = type;
	}
	
	public int getWidth () {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public String getType () {
		return this.type;
	}
	
	 public int getId() {
		 return this.id;
	 }
	 
	 public Location getOffset() {
		 return this.pos;
	 }
	 
	 public void setOffset(Location value) {
		 this.pos = value;
	 }
	 
	 public void addPixel(Pixel pix) throws DuplicatedKeyException { 
		 tree.put(tree.getRoot(), pix);
	 }
	 
	 public boolean intersects (GraphicalObject gobj) {
		 int offsetX = gobj.getOffset().xCoord();
		 int offsetY = gobj.getOffset().xCoord();
		 
		 for (int x = offsetX; x < offsetX + gobj.getWidth(); x++) {
				if (findPixel(new Location(x, offsetY)) || findPixel(new Location(x, offsetY + gobj.getHeight()))) {
					return true;
				}
			}

			for (int y = offsetY; y < offsetY + gobj.getHeight(); y++) {
				if (findPixel(new Location(offsetX, y)) || findPixel(new Location(offsetX + gobj.getWidth(), y))) {
					return true;
				}
			}
			return false;
		}
		 
	 private boolean findPixel(Location p) {
		 return (p.xCoord() >= pos.xCoord() && p.xCoord() <= (pos.xCoord() + width) && p.yCoord() >= pos.yCoord() && p.yCoord() <= (pos.yCoord() + height));
		 
	 }
	 
	 private BinarySearchTree getTree() {
		return tree;
	}

}
