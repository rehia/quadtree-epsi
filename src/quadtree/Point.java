package quadtree;

public class Point {
	
    float x;
    float y;
    
    public Point(float x, float y){
        this.x=x;
        this.y=y;
    }
	
     public Point(int max, int min){
        this.abcisseX = (int)(Math.random() * (max-min));
        this.abcisseX = (int)(Math.random() * (max-min));
    }
}
