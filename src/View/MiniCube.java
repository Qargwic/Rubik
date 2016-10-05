package View;

import Logic.Math3D.Math3D;
import Logic.Math3D.Matrix;
import Logic.Math3D.PVector;
import Logic.Math3D.Vector3;

import java.awt.*;

public class MiniCube {

    private int X;
    private int Y;
    private final int EDGE;
    private static int ID = 1;
    private Matrix matrix;
    private int[][] sides = {{0, 1, 2, 3}, {4, 7, 6, 5}, {0, 4, 5, 1}, {3, 2, 6, 7}, {0, 3, 7, 4}, {1, 5, 6, 2}};
    private int[][] sideNorm = {{0, 4}, {4, 0}, {0, 3}, {3, 0}, {0, 1}, {1, 0}};

    private Color [] sideColor = {Color.YELLOW, Color.WHITE, Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE};

    private double[] getNorm(PVector pVector){
        double [] ret = new double[6];
        for (int i = 0; i < 6; i += 1){
            ret[i] = Math3D.getCos(matrix.getVector(sideNorm[i][0]), matrix.getVector(sideNorm[i][1]), pVector);
        }
        return ret;
    }

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

    public double zDist(PVector pVector){
        return Math3D.sq(pVector.z + matrix.getVector(0).z + matrix.getVector(6).z) +
                Math3D.sq(pVector.y + matrix.getVector(0).y + matrix.getVector(6).y) +
                Math3D.sq(pVector.x + matrix.getVector(0).x + matrix.getVector(6).x);
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

    public void draw(Graphics2D g, Matrix pMatrix, PVector pVector){
        double [] n = getNorm(pVector);

        Matrix proect = Math3D.mult(matrix, pMatrix);
        proect.dec();

        for(int i = 0; i<6; i++){
            if (n[i]<0) continue;
            g.setColor(sideColor[i]);
            g.fillPolygon(proect.getX(sides[i]).mult(EDGE).add(X).toInt(), proect.getY(sides[i]).mult(EDGE).add(Y).toInt(), 4);
        }
    }

}
