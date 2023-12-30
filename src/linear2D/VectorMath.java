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

    public static float getAngleBetweenTwoVectors(Vector a, Vector b){
//        return (float) Math.acos(getDotProduct(v1, v2) / (v1.length * v2.length));
        return (float) Math.atan2( a.x*b.y - a.y*b.x, a.x*b.x + a.y*b.y );
    }

    public static void main(String[] args) {
        Vector v1 = new Vector(1,0);
        float angle = getAngleBetweenTwoVectors(v1, new Vector(0, 1));
        System.out.println("In degrees: " + Math.toDegrees(angle));
        Matrix r = getRotationMatrixFromAngle(angle);
        System.out.println("A: " + r.a + " B: " + r.b + " C: " + r.c + " D: " + r.d);

        System.out.println(v1);
        v1.applyMatrix(r);
        System.out.println(v1);
        v1.applyMatrix(r);
        System.out.println(v1);
    }
}
