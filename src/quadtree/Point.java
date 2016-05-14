package quadtree;

public class Point {
    
    public int abcisseX;
    public int abcisseY;
    
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
    
}
