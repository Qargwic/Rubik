package View;

import Logic.Math3D.Math3D;
import Logic.Math3D.Matrix;
import Logic.Math3D.Vector3;

import java.awt.*;

public class MiniCube {

    private int X;
    private int Y;
    private final int EDGE;
    private static int ID = 1;
    private int distanceZ = 10;
    public Matrix matrix;
    private int[][] sides = {{0, 1, 2, 3}, {4, 7, 6, 5}, {0, 4, 5, 1}, {3, 2, 6, 7}, {0, 3, 7, 4}, {1, 5, 6, 2}};

    private Color [] sideColor = {Color.YELLOW, Color.WHITE, Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE};

    public MiniCube(int glX, int glY, int edge, double x, double y, double z){
        X = glX; Y = glY; EDGE = edge;
        matrix = new Matrix(new Vector3[] {
                new Vector3(1+x, 1+y, 1+z),
                new Vector3(-1+x, 1+y, 1+z),
                new Vector3(-1+x, 1+y, -1+z),
                new Vector3(1+x, 1+y, -1+z),
                new Vector3(1+x, -1+y, 1+z),
                new Vector3(-1+x, -1+y, 1+z),
                new Vector3(-1+x, -1+y, -1+z),
                new Vector3(1+x, -1+y, -1+z),
        });
    }

    public double zDist(){
        return (-50-matrix.getVector(0).z + matrix.getVector(6).z)*(-50-matrix.getVector(0).z + matrix.getVector(6).z) +
                (matrix.getVector(0).y + matrix.getVector(6).y)*(matrix.getVector(0).y + matrix.getVector(6).y)+
                (matrix.getVector(0).x + matrix.getVector(6).x)*(matrix.getVector(0).x + matrix.getVector(6).x);
    }
    public double [] zDistToSides(){
        double [] buf = new double[6];
        double [] arr = matrix.getZ();
        for (int i = 0; i < 6; i++){
            buf[i] = (arr[sides[i][0]] + arr[sides[i][2]])/2;
        }
        return buf;
    }
    public void turnX(double alpha){
        matrix = Math3D.mult(matrix, Math3D.turnX(alpha));
    }
    public void turnY(double alpha){
        matrix = Math3D.mult(matrix, Math3D.turnY(alpha));
    }
    public void turnZ(double alpha){
        Math3D.mult(matrix, Math3D.turnZ(alpha));
    }
    public void turn(double X, double Y, double Z){
        matrix = Math3D.mult(matrix, Math3D.mult(Math3D.turnX(X), Math3D.turnY(Y), Math3D.turnZ(Z)));
    }

    public void draw(Graphics g, int [] ans){

        Matrix proect = Math3D.mult(matrix, Math3D.proectionZ(distanceZ));
        proect.dec();

        for(int j = 0; j<6; j++){
            int i = ans[j];
            g.setColor(sideColor[i]);
            g.fillPolygon(proect.getX(sides[i]).mult(EDGE).add(X).toInt(), proect.getY(sides[i]).mult(EDGE).add(Y).toInt(), 4);
        }
    }

}
