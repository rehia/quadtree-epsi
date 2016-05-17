package quad;

public class Point {
	
	private int abscisse;
	private int ordonne;
	
	public Point(int abscisse, int ordonne){
		this.setAbscisse(abscisse);
		this.setOrdonne(ordonne);
	}

	public int getAbscisse() {
		return abscisse;
	}

	public void setAbscisse(int abscisse) {
		this.abscisse = abscisse;
	}

	public int getOrdonne() {
		return ordonne;
	}

	public void setOrdonne(int ordonne) {
		this.ordonne = ordonne;
	}
	
	public boolean isInThisBloc(float startWidth, float endWidth, float startHeight, float endHeight){
		if(this.abscisse > startWidth && this.abscisse < endWidth && this.ordonne > startHeight && this.ordonne < endHeight){
			return true;
		}
		return false;
	}
	
	public String toString(){
		return "("+this.abscisse+" , "+this.ordonne+")";
	}
}
