package quad;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private List<Point> points;
	private List<Node> childrens;
	private float startWidth;
	private float endWidth;
	private float startHeight;
	private float endHeight;
	private int profondeur;
	
	public Node(float startWidth, float endWidth, float startHeight, float endHeight, int profondeur){
		this.points = new ArrayList<Point>();
		this.childrens = new ArrayList<Node>();
		this.startWidth = startWidth;
		this.endWidth = endWidth;
		this.startHeight = startHeight;
		this.endHeight = endHeight;
		this.setProfondeur(profondeur);
	}
	
	public void addNode(Node node){
		this.getChildrens().add(node);
	}
	
	public boolean hasChildren(){
		return this.childrens.size()>0;
	}
	
	public void addPoint(Point point){
		boolean addToAChild = false;
		if(this.hasChildren()){
			 addToAChild = this.tryToGivePointToAChildren(point);	
		}
		if(!addToAChild){
			this.points.add(point);
		}
		if(this.points.size() > 4 && !this.hasChildren()){
			this.subdivision();
			this.ventilation();
		}
	}
	
	public boolean tryToGivePointToAChildren(Point point) {
		for(Node child: this.childrens){
			if(point.isInThisBloc(child.startWidth, child.endWidth, child.startHeight, child.endHeight)){
				child.addPoint(point);
				return true;
			}
		}
		return false;
	}

	public void subdivision(){	
		this.addNode(new Node(this.startWidth, (this.endWidth+this.startWidth)/2, this.startHeight, (this.endHeight+this.startHeight)/2, this.getProfondeur()+1));
		this.addNode(new Node((this.endWidth+this.startWidth)/2, this.endWidth, this.startHeight, (this.endHeight+this.startHeight)/2, this.getProfondeur()+1));
		this.addNode(new Node(this.startWidth, (this.endWidth+this.startWidth)/2, (this.endHeight+this.startHeight)/2, this.endHeight, this.getProfondeur()+1));
		this.addNode(new Node((this.endWidth+this.startWidth)/2, this.endWidth, (this.endHeight+this.startHeight)/2, this.endHeight, this.getProfondeur()+1));
	}
	
	public void ventilation(){
		List<Point> newPointsList = new ArrayList<Point>();
		for(Point point : this.points){
			boolean addInAChild = false;
			for(Node child : this.childrens){
				if(point.isInThisBloc(child.startWidth, child.endWidth, child.startHeight, child.endHeight)){
					child.addPoint(point);
					addInAChild = true;
				}
			}
			if(!addInAChild){
				newPointsList.add(point);
			}
		}
		this.setPoints(newPointsList);
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public List<Node> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Node> childrens) {
		this.childrens = childrens;
	}
	
	public String toString(){
		StringBuilder resultat = new StringBuilder();
		resultat.append("Node : ");
		for(Point point : this.points){
			resultat.append(point.toString());
		}
		resultat.append("\n");
		for(Node child:this.childrens){
			for(int index = 0;index<this.getProfondeur();index++){
				resultat.append("-");	
			}
			resultat.append(child.toString());
		}
		return resultat.toString();
	}

	public Node findPoint(Point point) {
		Node returnNode = null;
		if(this.points.contains(point)){
			returnNode = this;
		}
		else{
			for(Node child:this.childrens){
				if(child.findPoint(point) != null){
					returnNode = child.findPoint(point);
				}
			}
		}
		return returnNode;
	}

	public int getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}
}
