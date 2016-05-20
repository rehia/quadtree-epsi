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
	private ArrayList<Quadtree>ListeDeQuadtree = new ArrayList<Quadtree>();
	ArrayList<Point>ListeAffichage =  new ArrayList<Point>();
	private float QuadtreeTailleXmin = 0;
	private float QuadtreeTailleXmax = 100;
	private float QuadtreeTailleYmin = 0;
	private float QuadtreeTailleYmax = 100;
	private int profondeur = 0;
	private Quadtree quadtreeParent = null;
	
	public Quadtree()
	{
		this.ListeDePoint = new ArrayList<Point>();
	}
	
	public Quadtree(float Xmin, float Xmax, float Ymin, float Ymax, Quadtree parent)
	{
		this.ListeDePoint = new ArrayList<Point>();
		this.setQuadtreeTailleXmin(Xmin);
		this.setQuadtreeTailleXmax(Xmax);
		this.setQuadtreeTailleYmin(Ymin);
		this.setQuadtreeTailleYmax(Ymax);
		this.setQuadtreeParent(parent);
		
		System.out.println("Quadtree X min : "+this.getQuadtreeTailleXmin());
		System.out.println("Quadtree X max : "+this.getQuadtreeTailleXmax());
		System.out.println("Quadtree Y min : "+this.getQuadtreeTailleYmin());
		System.out.println("Quadtree Y max : "+this.getQuadtreeTailleYmax());
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
	
	public float getQuadtreeTailleXmin() {
		return QuadtreeTailleXmin;
	}

	public void setQuadtreeTailleXmin(float quadtreeTailleXmin) {
		QuadtreeTailleXmin = quadtreeTailleXmin;
	}

	public float getQuadtreeTailleXmax() {
		return QuadtreeTailleXmax;
	}

	public void setQuadtreeTailleXmax(float quadtreeTailleXmax) {
		QuadtreeTailleXmax = quadtreeTailleXmax;
	}

	public float getQuadtreeTailleYmin() {
		return QuadtreeTailleYmin;
	}

	public void setQuadtreeTailleYmin(float quadtreeTailleYmin) {
		QuadtreeTailleYmin = quadtreeTailleYmin;
	}

	public float getQuadtreeTailleYmax() {
		return QuadtreeTailleYmax;
	}

	public void setQuadtreeTailleYmax(float quadtreeTailleYmax) {
		QuadtreeTailleYmax = quadtreeTailleYmax;
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
	public Quadtree getQuadtreeParent() {
		return quadtreeParent;
	}

	public void setQuadtreeParent(Quadtree quadtreeParent) {
		this.quadtreeParent = quadtreeParent;
	}
	
	public void subdivision()
	{		
		this.setSudOuest(new Quadtree());
		this.getSudOuest().setQuadtreeParent(this);
		this.setSudOuest(new Quadtree(this.sudOuest.getQuadtreeParent().getQuadtreeTailleXmin(),this.sudOuest.getQuadtreeParent().getQuadtreeTailleXmax()/2,this.sudOuest.getQuadtreeParent().getQuadtreeTailleYmin(),this.sudOuest.getQuadtreeParent().getQuadtreeTailleYmax()/2, this));
		this.sudOuest.setProfondeur(sudOuest.getQuadtreeParent().getProfondeur()+1);
		ListeDeQuadtree.add(sudOuest);
		
		this.setSudEst(new Quadtree()); 
		this.getSudEst().setQuadtreeParent(this);
		this.setSudEst(new Quadtree(this.sudEst.getQuadtreeParent().getQuadtreeTailleXmax()/2,this.sudEst.getQuadtreeParent().getQuadtreeTailleXmax(),this.sudEst.getQuadtreeParent().getQuadtreeTailleYmin(),this.sudEst.getQuadtreeParent().getQuadtreeTailleYmax()/2, this));
		this.sudEst.setProfondeur(sudEst.getQuadtreeParent().getProfondeur()+1);
		ListeDeQuadtree.add(sudEst);
		
		this.setNordOuest(new Quadtree()); 
		this.getNordOuest().setQuadtreeParent(this);
		this.setNordOuest(new Quadtree(this.nordOuest.getQuadtreeParent().getQuadtreeTailleXmin(),this.nordOuest.getQuadtreeParent().getQuadtreeTailleXmax()/2,this.nordOuest.getQuadtreeParent().getQuadtreeTailleYmax()/2,this.nordOuest.getQuadtreeParent().getQuadtreeTailleYmax(), this));
		this.nordOuest.setProfondeur(nordOuest.getQuadtreeParent().getProfondeur()+1);
		ListeDeQuadtree.add(nordOuest);
		
		this.setNordeEst(new Quadtree()); 
		this.getNordeEst().setQuadtreeParent(this);
		this.setNordeEst(new Quadtree(this.nordeEst.getQuadtreeParent().getQuadtreeTailleXmax()/2,this.nordeEst.getQuadtreeParent().getQuadtreeTailleXmax(),this.nordeEst.getQuadtreeParent().getQuadtreeTailleYmax()/2,this.nordeEst.getQuadtreeParent().getQuadtreeTailleYmax(), this));
		this.getNordeEst().setProfondeur(nordeEst.getQuadtreeParent().getProfondeur()+1);	
		ListeDeQuadtree.add(nordeEst);
	}
	
	public void ventiler()
	{
		for(Point p :  this.getListeDePoint())
		{
			if(p.getAbcisseX()< this.sudOuest.QuadtreeTailleXmax || p.getAbcisseX() > this.sudOuest.QuadtreeTailleXmin && p.getAbcisseY() < this.sudOuest.getQuadtreeTailleYmin() || p.getAbcisseY() > this.sudOuest.getQuadtreeTailleYmax())
			{
				this.sudOuest.getListeDePoint().add(p);
				p.setProfondeur(sudOuest.getProfondeur());
			}
			if(p.getAbcisseX()< this.sudEst.QuadtreeTailleXmax || p.getAbcisseX() > this.sudEst.QuadtreeTailleXmin && p.getAbcisseY() < this.sudEst.getQuadtreeTailleYmin() || p.getAbcisseY() > this.sudEst.getQuadtreeTailleYmax())
			{
				this.sudEst.getListeDePoint().add(p);
				p.setProfondeur(sudEst.getProfondeur());
			}
			if(p.getAbcisseX()< this.nordOuest.QuadtreeTailleXmax || p.getAbcisseX() > this.nordOuest.QuadtreeTailleXmin && p.getAbcisseY() < this.nordOuest.getQuadtreeTailleYmin() || p.getAbcisseY() > this.nordOuest.getQuadtreeTailleYmax())
			{
				this.nordOuest.getListeDePoint().add(p);
				p.setProfondeur(nordOuest.getProfondeur());
			}
			if(p.getAbcisseX()< this.nordeEst.QuadtreeTailleXmax || p.getAbcisseX() > this.nordeEst.QuadtreeTailleXmin && p.getAbcisseY() < this.nordeEst.getQuadtreeTailleYmin() || p.getAbcisseY() > this.nordeEst.getQuadtreeTailleYmax())
			{
				this.nordeEst.getListeDePoint().add(p);
				p.setProfondeur(nordeEst.getProfondeur());
			}
		}
	}
	
	public void insertionEnListe (Point p)
	{
		
		
		if(this.getListeDePoint().size() < 4)
		{
			p.setProfondeur(this.getProfondeur());
			this.ListeAffichage.add(p);
			this.getListeDePoint().add(p);
			
		}
		else 
		{
			this.subdivision();	
			ventiler();
			this.getListeDePoint().clear();
			for(Quadtree quad : this.ListeDeQuadtree)
			{
				if (quad.getListeDePoint().size()>4)
				{
					System.out.println("Profondeur du quad : "+quad.getProfondeur());
					quad.subdivision();
					quad.ventiler();
					quad.getListeDePoint().clear();
				}
				else
				{
					quad.getListeDePoint().add(p);
					p.setProfondeur(quad.getProfondeur());
				}
					
			}
		}
	}
	

	
	
	public boolean estSurLaLigne(Point p)
	{
		boolean estSurlaLigne = false;
		
		if (p.getAbcisseX() == this.getQuadtreeTailleXmax() || p.getAbcisseX() == 0 || p.getAbcisseY() == this.getQuadtreeTailleYmax() || p.getAbcisseY() == 0  ) 
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
				insertionEnListe(new Point((int)this.QuadtreeTailleXmax, (int)(this.QuadtreeTailleXmax-this.getQuadtreeParent().getQuadtreeTailleXmax()), true));
			}
		}
		else 
		{
			insertionEnListe(p);
		}
			
	}
	
	public void afficheListEtProfondeur()
	{
		for(int i= 0; i<this.ListeAffichage.size(); i++)
		{
			System.out.println("Point numéro : "+i);
			System.out.println("Coordonné X : "+ListeAffichage.get(i).abcisseX);
			System.out.println("Coordonné Y : "+ListeAffichage.get(i).abcisseY);
			System.out.println("Profondeur : "+ListeAffichage.get(i).getProfondeur());
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
		}
		if(!isContain)
		{
			System.out.println("Ce point m'est inconnu il n'est probablement pas dans la liste");
		}
	}
	
	

}
