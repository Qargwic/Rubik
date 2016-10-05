package View;

import Logic.Math3D.Math3D;
import Logic.Math3D.PVector;
import Logic.Math3D.Vector3;

import java.awt.*;

public class BigCube{

    private final int EDGE = 20;
    private final int X=200;
    private final int Y=200;
    private final double miniEdge = 3;
    private int distanceZ = 15;
    private PVector pVector = new PVector(distanceZ);

    private MiniCube [] cubes = new MiniCube[26];

    private  int [] coordX = {
            0, 0,
            -1, 0, 1, 1, 1, 0, -1, -1,
            -1, 0, 1, 1, 1, 0, -1, -1,
            -1, 0, 1, 1, 1, 0, -1, -1,
    };
    private  int [] coordY = {
            1, -1,
            1, 1, 1, 1, 1, 1, 1, 1,
            0, 0, 0, 0, 0, 0, 0, 0,
            -1, -1, -1, -1, -1, -1, -1, -1,
    };
    private  int [] coordZ = {
            0, 0,
            -1, -1, -1, 0, 1, 1, 1, 0,
            -1, -1, -1, 0, 1, 1, 1, 0,
            -1, -1, -1, 0, 1, 1, 1, 0,
    };

    private int [][] sides = {
            {0, 2, 3, 4, 5, 6, 7, 8, 9},
            {15, 8, 7, 6, 14, 22, 23, 24, 16},
            {1, 18, 19, 20, 21, 22, 23, 24, 25},
            {11, 2, 3, 4, 12, 20, 18, 19, 10},
            {13, 4, 5, 6, 14, 22, 21, 20, 12},
            {17, 8, 9, 2, 10, 18, 25, 24, 16}
    };

    public BigCube(){
        for (int i = 0; i < 26; i++){
            cubes[i] = new MiniCube(X,Y,EDGE, coordX[i]*miniEdge,coordY[i]*miniEdge,coordZ[i]*miniEdge);
        }
        //pVector.addAngle(40, 40);
    }

    public double [] getZ(){
        double [] ans = new double[26];
        for (int i = 0; i<26; i++) ans[i] = cubes[i].zDist(pVector);
        return ans;
    }

    public void turnP(int dx, int dy){
        pVector.addAngle(dx, dy);
    }
    public void released(){
        pVector.setNorm();
    }

    public void draw(Graphics2D g){
        int [] cubeSort = Math3D.sort(getZ());
        for (int i = 0; i<26; i++){
            cubes[cubeSort[i]].draw(g, Math3D.proectionZ(pVector), pVector);
        }
    }

}
