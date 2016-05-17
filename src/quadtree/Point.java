package quadtree;

public class Point {
    
    public int abcisseX;
    public int abcisseY;
    private int profondeur = 0;
    
    public int getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}

	public Point(int x, int y){
        this.abcisseX=x;
        this.abcisseY=y;
    }
    
    public Point(int max, int min, boolean random){
        if (random == true){
            this.abcisseX = (int)(Math.random() * (max-min));
            this.abcisseX = (int)(Math.random() * (max-min));
        }
    }

	
		public int getAbcisseX() {
			return abcisseX;
		}

		public void setAbcisseX(int abcisseX) {
			this.abcisseX = abcisseX;
		}

		public int getAbcisseY() {
			return abcisseY;
		}

		public void setAbcisseY(int abcisseY) {
			this.abcisseY = abcisseY;
		}
}

