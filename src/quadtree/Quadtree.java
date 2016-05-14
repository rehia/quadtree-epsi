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
	private int QuadtreeTailleX;
	private int QuadtreeTailleY;
	
	
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
	public int getQuadtreeTailleX() {
		return QuadtreeTailleX;
	}
	public void setQuadtreeTailleX(int quadtreeTailleX) {
		QuadtreeTailleX = quadtreeTailleX;
	}
	
	public int getQuadtreeTailleY() {
		return QuadtreeTailleY;
	}
	public void setQuadtreeTailleY(int quadtreeTailleY) {
		QuadtreeTailleY = quadtreeTailleY;
	}
	public int getNbPointMax() {
		return nbPointMax;
	}
	
	public void subdividion()
	{
		this.setNordeEst(new Quadtree());
		this.getNordeEst().setQuadtreeTailleX(QuadtreeTailleX/4);
		this.getNordeEst().setQuadtreeTailleY(QuadtreeTailleY/4);
		
		this.setNordOuest(new Quadtree());
		this.getNordOuest().setQuadtreeTailleX(QuadtreeTailleX/4);
		this.getNordOuest().setQuadtreeTailleY(QuadtreeTailleY/4);
		
		this.setSudEst(new Quadtree());
		this.getSudEst().setQuadtreeTailleX(QuadtreeTailleX/4);
		this.getSudEst().setQuadtreeTailleY(QuadtreeTailleY/4);
		
		this.setSudOuest(new Quadtree());
		this.getSudOuest().setQuadtreeTailleX(QuadtreeTailleX/4);
		this.getSudOuest().setQuadtreeTailleY(QuadtreeTailleY/4);
	}
	
	public void insertionEnListe (Point p)
	{
		this.getListeDePoint().add(p);
	}
	
	public boolean estSurLaLigne(Point p)
	{
		boolean estSurlaLigne = false;
		
		/*
		 * TO_DO : La méthode doit verrifier la position du point par rapport à la taille du quadtree censé le contenir
		*/
		return estSurlaLigne;
	}
	
	

}
