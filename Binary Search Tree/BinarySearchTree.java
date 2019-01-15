
public class BinarySearchTree implements BinarySearchTreeADT {
	
	private BinaryNode root;
	private int size;
	private boolean found = false;
	
	public BinarySearchTree() { //constructs an empty binary tree 
		this.root = null;
		size = 0;
	}
	
	
	// Returns the Pixel storing the given key, if the key is stored in the tree; returns null otherwise.
	public Pixel get(BinaryNode r, Location key) {
		if (find(r, key) == null) { //if the key is not found in the given location, nothing is returned
			return null;
		} 
		else {
			return find(r, key).getData(); //else, the node in this location is returned
		}
	}
	
	

	// Inserts the given data in the tree if no data item with the same key is already there. If a node already stores the same key, the algorithm throws a DuplicatedKeyException
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {
		if (size == 0) { //if the tree is empty, then the new key is inserted as the root
			root = new BinaryNode(data, null, null, r);
			size++; //the size of the binary tree is increased by one 
			return;
		}
		
		else { 

			if (r.getData().getLocation().compareTo(data.getLocation()) == 0) {  
			throw new DuplicatedKeyException("The key already exists");
			} 
		
			else if (r.getData().getLocation().compareTo(data.getLocation()) == 1) {
				if (r.getLeft() == null) { 
					r.setLeft(new BinaryNode(data, null, null, r));
					size++;
					return;
				}
				else 
					put(r.getLeft(), data);
			}
		
			else if (r.getData().getLocation().compareTo(data.getLocation()) == -1) {
				if (r.getRight() == null) { 
					r.setRight(new BinaryNode(data, null, null, r));
					size++;
					return;
				}
				else 
					put(r.getRight(), data);
			}

		}
	}
	


	// Removes the data item with the given key, if the key is stored in the tree; throws an InexistentKeyException otherwise.
	public void remove(BinaryNode r, Location key) throws InexistentKeyException { 
		BinaryNode temp = find(r, key);

		// the key does not exist
		if(temp == null || temp.isLeaf() == true) { 
			throw new InexistentKeyException("The key does not exist");
		}

		// the key is in the tree and has a left child, and does not have a right child
		else if(temp.getLeft() == null && temp.getRight() != null) {
			temp.getRight().setParent(temp.getParent());
        	}

		// the key is in the tree and has a right child, and does not have a left child
		else  if(temp.getLeft() != null && temp.getRight() == null) {
			temp.getLeft().setParent(temp.getParent());

        	}
        
		// the key is in the tree and has both children
		else {
			if(successor(temp.getRight(), key) != null) {
				Pixel thePixel = successor(temp.getRight(), key);
				BinaryNode theNode = find(r, thePixel.getLocation());
				theNode.setLeft(temp.getLeft());
				theNode.setRight(temp.getRight());
				theNode.setParent(temp.getParent());
				temp = null;

			}                
		}
	}
	

	// Returns the Pixel with the smallest key larger than the given one (note that the tree does not need to store a node with the given key). Returns null if the given key has no successor
	public Pixel successor(BinaryNode r, Location key) {
		if (r == null) { //if the tree is empty, then return null
			return null;
		} 
		else if (r.getData().getLocation().compareTo(key) <= 0) { //if the current node is equal to or less than the position of the desired key
			return successor(r.getRight(), key); //then invoke the method recursively starting with the right child node 
		} 
		else {
			Pixel nextNode = successor(r.getLeft(), key); //otherwise, traverse the left child node, 
			if (nextNode == null) { //until there are none left
				return r.getData(); //then return that node
			} 
			else {
				return nextNode;
			}
		}
	}
	


	// Returns the Pixel with the largest key smaller than the given one (note that the tree does not need to store a node with the given key). Returns null if the given key has no predecessor.
	public Pixel predecessor(BinaryNode r, Location key) {
		if (r == null) { //if the tree is empty, then return null
			return null;
		} 
		else if (r.getData().getLocation().compareTo(key) >= 0) { //if the current node is equal to or more than the position of the desired key,
			return predecessor(r.getLeft(), key); //the invoke the method recursively starting with the left child node
		} 
		else {
			Pixel nextNode = predecessor(r.getRight(), key); //otherwise, invoke recursively on the right child node
			if (nextNode == null) { //until you reach the end of them
				return r.getData(); //then return that node
			} 
			else {
				return nextNode;
			}
		}
	}
	 

	// Returns the Pixel with the smallest key. Throws an EmptyTreeException if the tree does not contain any data
	public Pixel smallest(BinaryNode r) throws EmptyTreeException {
		 if (r.equals(null))
			 throw new EmptyTreeException("The tree is empty"); //if the tree is empty, then throw an empty tree exception
		 else {
			while (r.getLeft() != null) 
				r = r.getLeft(); //then traverse to the left child node
				return r.getData();
		}
	 }
	 
	
	// Returns the Pixel with the largest key. Throws an EmptyTreeException if the tree does not contain any data.
	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		if (r.equals(null))
			 throw new EmptyTreeException("The tree is empty"); //if the tree is empty, then throw an empty tree exception
		 else {
			while (r.getRight() != null) 
				r = r.getRight(); //then traverse to the right child node
				return r.getData();
		}
	 }

	 
	
	// Returns the root of the binary search tree.
	public BinaryNode getRoot() {
		return this.root;
	 }
	 
	 private BinaryNode find(BinaryNode r, Location key) {
		while (r != null) {
			if (key.compareTo(r.getData().getLocation()) == 0) {
				found = true;
				return r;
			}
			else if (key.compareTo(r.getData().getLocation()) == 1) {
				r = r.getRight();
			}
			else {
				r = r.getLeft();
			}
		}
		if (!found) {
			return null;
		}
		else
			return r;
		}
	 
}
