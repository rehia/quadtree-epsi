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
	private float QuadtreeTailleX = 100;
	private float QuadtreeTailleY = 100;
	private int profondeur = 0;
	
	public Quadtree()
	{
		this.ListeDePoint = new ArrayList<Point>();
	}
	
	
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
	public float getQuadtreeTailleX() {
		return QuadtreeTailleX;
	}
	public void setQuadtreeTailleX(float f) {
		QuadtreeTailleX = f;
	}
	
	public float getQuadtreeTailleY() {
		return QuadtreeTailleY;
	}
	public void setQuadtreeTailleY(float f) {
		QuadtreeTailleY = f;
	}
	public int getNbPointMax() {
		return nbPointMax;
	}
	
	public int getProfondeur() {
		return profondeur;
	}
	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}
	
	public void subdivision()
	{
		this.setNordeEst(new Quadtree());
		this.getNordeEst().setQuadtreeTailleX(QuadtreeTailleX/2);
		this.getNordeEst().setQuadtreeTailleY(QuadtreeTailleY/2);
		this.getNordeEst().setProfondeur(this.getProfondeur()+1);
		
		this.setNordOuest(new Quadtree());

		this.getNordOuest().setQuadtreeTailleX(QuadtreeTailleX/2);
		this.getNordOuest().setQuadtreeTailleY(QuadtreeTailleY/2);
		this.getNordOuest().setProfondeur(this.getProfondeur()+1);
		

		this.setNbPointMaxAtteint(nbPointMaxAtteint);


		this.setSudEst(new Quadtree());
		this.getSudEst().setQuadtreeTailleX(QuadtreeTailleX/2);
		this.getSudEst().setQuadtreeTailleY(QuadtreeTailleY/2);
		this.getSudEst().setProfondeur(this.getProfondeur()+1);
		
		this.setSudOuest(new Quadtree());
		this.getSudOuest().setQuadtreeTailleX(QuadtreeTailleX/2);
		this.getSudOuest().setQuadtreeTailleY(QuadtreeTailleY/2);
		this.getSudOuest().setProfondeur(this.getProfondeur()+1);
	}
	

	public void insertionEnListe (Point p)
	{
		
		if(this.getListeDePoint().size() < 4)
		{
			this.getListeDePoint().add(p);
		}
		else 
		{
			this.subdivision();	
		}
	}
	
	public boolean estSurLaLigne(Point p)
	{
		boolean estSurlaLigne = false;
		
		if (p.getAbcisseX() == this.getQuadtreeTailleX() || p.getAbcisseX() == 0 || p.getAbcisseY() == this.getQuadtreeTailleY() || p.getAbcisseY() == 0  ) 
		{
			estSurlaLigne =  true;
		}
		return estSurlaLigne;
	}
	
	

}
