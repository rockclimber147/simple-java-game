package linear2D;

public class Vector {
    protected float x;
    protected float y;
    protected float length;

    public Vector(float x, float y){
        this.x = x;
        this.y = y;
        this.length = (float) (Math.sqrt(x*x + y*y));
    }

    public void addWithVector(Vector v){
        this.x += v.x;
        this.y += v.y;
    }

    public void applyMatrix(Matrix m){
        this.x = m.a*x + m.b*y;
        this.y = m.c*x + m.d*y;
    }

    public void scale(float magnitude){
        this.x *= magnitude;
        this.y *= magnitude;
        this.length *= magnitude;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
