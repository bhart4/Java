
public class BinaryNode {
	
	private BinaryNode parent;
	private BinaryNode left;
	private BinaryNode right;
	private Pixel value;
	
	public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) {
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.value = value;
	}

	public BinaryNode() {
		this.parent = null;
		this.left = null;
		this.right = null;
		this.value = null;	
	}
	
	public BinaryNode getParent() {
		return this.parent;	
	}
	
	public void setParent(BinaryNode parent) {
		this.parent = parent;	
	}
	
	public void setLeft(BinaryNode left) {
		this.left = left;	
	}
	
	public void setRight(BinaryNode right) {
		this.right = right;	
	}
	
	public void setData(Pixel value) {
		this.value = value;		
	}
	
	public boolean isLeaf() {
		if (this.getRight() == null && this.getLeft() == null)
			return true;
		else
			return false;
	}
	
	public Pixel getData() {
		return value;
	}
	
	public BinaryNode getLeft() {
		return left;	
	}
	
	public BinaryNode getRight() {
		return right;	
	}
	
}
