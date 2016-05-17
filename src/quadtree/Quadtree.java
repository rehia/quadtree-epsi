package quadtree;

import java.util.ArrayList;

public class Quadtree  {
	
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
	private Quadtree quadtreeParent = null;
	
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
		createChildQuadtree(nordeEst);
		
		this.setNordOuest(new Quadtree());
		createChildQuadtree(nordOuest);


		this.setNbPointMaxAtteint(nbPointMaxAtteint);


		this.setSudEst(new Quadtree());
		createChildQuadtree(sudEst);
		
		this.setSudOuest(new Quadtree());
		createChildQuadtree(sudOuest);
	}


	public void createChildQuadtree(Quadtree ChildQuatree) {
		ChildQuatree.setQuadtreeTailleX(this.QuadtreeTailleX/2);
		ChildQuatree.setQuadtreeTailleY(this.QuadtreeTailleY/2);
		ChildQuatree.setProfondeur(this.getProfondeur()+1);
		ChildQuatree.setQuadtreeParent(this);
	}
	

	public Quadtree getQuadtreeParent() {
		return quadtreeParent;
	}


	public void setQuadtreeParent(Quadtree quadtreeParent) {
		this.quadtreeParent = quadtreeParent;
	}


	public void insertionEnListe (Point p)
	{
		
		if(this.getListeDePoint().size() < 4)
		{
			p.setProfondeur(this.getProfondeur());
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
	
	public void insertUniquePoint (Point p)
	{
		boolean isUnique = true;
		int counter = 0;
		if(!this.getListeDePoint().isEmpty())
		{
			for (Point pt : this.getListeDePoint())
			{
				if (pt.getAbcisseX() == p.getAbcisseX() && pt.abcisseY == p.getAbcisseY())
				{
					counter ++;
					if (counter >1)
					{
						isUnique = false;
					}
				}
			}
			if(isUnique)
			{
				insertionEnListe(p);
			}
			else 
			{
				insertionEnListe(new Point((int)this.QuadtreeTailleX, (int)(this.QuadtreeTailleX-this.getQuadtreeParent().getQuadtreeTailleX()), true));
			}
		}
		else 
		{
			insertionEnListe(p);
		}
			
	}
	
	public void afficheListEtProfondeur()
	{
		for(int i= 0; i<this.getListeDePoint().size(); i++)
		{
			System.out.println("Point numéro : "+i);
			System.out.println("Coordonné X : "+this.getListeDePoint().get(i).abcisseX);
			System.out.println("Coordonné Y : "+this.getListeDePoint().get(i).abcisseY);
			System.out.println("Profondeur : "+this.getListeDePoint().get(i).getProfondeur());
		}
	}
	
	public void getProfondeurByPoint(int X ,int Y)
	{
		boolean isContain = false;
		for(Point p : this.getListeDePoint())
		{
			if(p.getAbcisseX() == X && p.getAbcisseY()==  Y)
			{
				isContain = true;
				System.out.println("Profondeur du point demandé : "+p.getProfondeur());
			}
			if(!isContain)
			{
				System.out.println("Ce point m'est inconnu il n'est probablement pas dans la liste");
			}
		}
	}
	
	

}
