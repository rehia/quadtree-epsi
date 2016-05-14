package quadtree;

import java.util.ArrayList;

public class Quadtree {
	
	private Quadtree nordOuest;
	private Quadtree nordeEst;
	private Quadtree sudEst;
	private Quadtree sudOuest;

	private final int nbPointMax = 4;
	private boolean nbPointMaxAtteint;
	private ArrayList<Point>ListeDePoint;
	private int QuadtreeTaille;
	
	public Quadtree getNordOuest() {
		return nordOuest;
	}
	public void setNordOuest(Quadtree nordOuest) {
		this.nordOuest = nordOuest;
	}
	public Quadtree getNordeEst() {
		return nordeEst;
	}
	public void setNordeEst(Quadtree nordeEst) {
		this.nordeEst = nordeEst;
	}
	public Quadtree getSudEst() {
		return sudEst;
	}
	public void setSudEst(Quadtree sudEst) {
		this.sudEst = sudEst;
	}
	public Quadtree getSudOuest() {
		return sudOuest;
	}
	public void setSudOuest(Quadtree sudOuest) {
		this.sudOuest = sudOuest;
	}
	public boolean isNbPointMaxAtteint() {
		return nbPointMaxAtteint;
	}
	public void setNbPointMaxAtteint(boolean nbPointMaxAtteint) {
		this.nbPointMaxAtteint = nbPointMaxAtteint;
	}
	public ArrayList<Point> getListeDePoint() {
		return ListeDePoint;
	}
	public void setListeDePoint(ArrayList<Point> listeDePoint) {
		ListeDePoint = listeDePoint;
	}
	public int getQuadtreeTaille() {
		return QuadtreeTaille;
	}
	public void setQuadtreeTaille(int quadtreeTaille) {
		QuadtreeTaille = quadtreeTaille;
	}
	public int getNbPointMax() {
		return nbPointMax;
	}
	
	public void subdividion()
	{
		this.setNordeEst(new Quadtree());
		this.getNordeEst().setQuadtreeTaille(QuadtreeTaille/4);
		
		this.setNordOuest(new Quadtree());
		this.getNordOuest().setQuadtreeTaille(QuadtreeTaille/4);
		
		this.setSudEst(new Quadtree());
		this.getSudEst().setQuadtreeTaille(QuadtreeTaille/4);
		
		this.setSudOuest(new Quadtree());
		this.getSudOuest().setQuadtreeTaille(QuadtreeTaille/4);
	}
	
	

}
