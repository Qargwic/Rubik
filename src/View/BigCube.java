package View;

import Logic.Math3D.Math3D;

import java.awt.*;

public class BigCube {

    private final int EDGE = 20;
    private final int X=200;
    private final int Y=200;
    private final double miniEdge = 3;

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
    }

    public double [] getZ(){
        double [] ans = new double[26];
        for (int i = 0; i<26; i++) ans[i] = cubes[i].zDist();
        return ans;
    }

    public void turn(double x, double y, double z){

    }

    public void draw(Graphics g){
        int [] sideSort = Math3D.sort(cubes[0].zDistToSides());
        int [] cubeSort = Math3D.sort(getZ());
        for (int i = 0; i<26; i++){
            cubes[cubeSort[i]].draw(g,sideSort);
        }
    }

}
