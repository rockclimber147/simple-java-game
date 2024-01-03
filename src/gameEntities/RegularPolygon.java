package gameEntities;

import linear2D.Matrix;
import linear2D.Vector;
import linear2D.VectorMath;

public class RegularPolygon {
    protected Vector position;
    protected Vector[] vertices;
    int[][] drawableVertices;
    int vertexCount;

    public RegularPolygon(Vector position, float radius, int vertexCount){
        this.position = position;
        this.vertexCount = vertexCount;
        vertices = new Vector[vertexCount];
        drawableVertices = new int[2][vertexCount];
        float interiorAngle = (float) (2 * Math.PI / vertexCount);

        for (int i = 0; i < vertexCount; i++){
            float currentAngle = interiorAngle* i;
            Vector currentVector = new Vector(0, radius);
            currentVector.applyMatrix(VectorMath.getRotationMatrixFromAngle(currentAngle));
            vertices[i] = currentVector;
        }
    }
    public Vector getDirection(){
        return vertices[0];
    }
    public int[][] getDrawableVertices(){
        for (int i = 0; i < vertexCount; i++){
            Vector currentPositionalVertex = VectorMath.getVectorSum(vertices[i], position);
            drawableVertices[0][i] = (int) currentPositionalVertex.getX();
            drawableVertices[1][i] = (int) currentPositionalVertex.getY();
        }
        return drawableVertices;
    }

    public void applyMatrix(Matrix m){
        for (Vector vertex: vertices){
            vertex.applyMatrix(m);
        }
    }
}
