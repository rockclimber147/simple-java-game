package linear2D;

public class VectorMath {
    private VectorMath(){}

    public static Matrix getRotationMatrixFromAngle(float theta){

        float sinTheta = (float) Math.sin(theta);
        float cosTheta = (float) Math.cos(theta);

        return new Matrix(cosTheta, sinTheta, -sinTheta, cosTheta);
    }

    public static float getDotProduct(Vector v1, Vector v2){
        return v1.x*v2.x + v1.y+v2.y;
    }
    public static Vector getVectorSum(Vector v1, Vector v2){
        return new Vector(v1.x + v2.x, v1.y + v2.y);
    }
    public static Vector getVectorDifference(Vector v1, Vector v2){
        return new Vector(v1.x - v2.x, v1.y - v2.y);
    }
    public static float getAngleBetweenTwoVectors(Vector a, Vector b){
        return (float) Math.atan2( a.x*b.y - a.y*b.x, a.x*b.x + a.y*b.y );
    }

    public static void main(String[] args) {}
}
