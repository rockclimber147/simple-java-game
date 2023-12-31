package linear2D;

public class Vector {
    protected float x;
    protected float y;
    protected float length;

    public Vector(float x, float y){
        this.x = x;
        this.y = y;
        updateLength();
    }
    public void updateLength(){
        this.length = (float) (Math.sqrt(x*x + y*y));
    }

    public void addVector(Vector v){
        this.x += v.x;
        this.y += v.y;
        updateLength();
    }
    public void subtractVector(Vector v){
        this.x -= v.x;
        this.y -= v.y;
        updateLength();
    }

    public void applyMatrix(Matrix m){
        float newX = m.a*x + m.b*y;
        float newY = m.c*x + m.d*y;
        this.x = newX;
        this.y = newY;
    }

    public void scaleBy(float magnitude){
        this.x *= magnitude;
        this.y *= magnitude;
        this.length *= magnitude;
    }
    public void scaleTo(float desiredLength){
        float factor = desiredLength / length;
        scaleBy(factor);
    }
    public void changeX(float change){
        this.x += change;
    }
    public void changeY(float change){
        this.y += change;
    }

    public float getX() {
        return x;
    }
    public float getUnitX(){return x / length;}

    public float getY() {
        return y;
    }
    public float getUnitY(){return y / length;}
    public float getLength(){return length;}

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String toString(){
        return "x: " + x + "\ny: " + y + "\nlength: " + length;
    }

    public static void main(String[] args) {
        System.out.println(new Vector(1,1));
    }
}
