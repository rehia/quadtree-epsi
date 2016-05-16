package pro.vhsl.dev;

public class QuadTree {

	private Node root;

	public QuadTree(double width, double height) {
		this.root = new Node(0, 0, width, height);
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

}
