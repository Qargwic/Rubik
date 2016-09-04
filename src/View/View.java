package View;

import Structure.Cube;
import Structure.Enum.Color;


public class View {

    private static void space(int num){
        for(int j = 0; j<3*num; j++){
            System.out.print("  ");
        }
    }

    public static void view(Cube cube){
        int j;
        for(j = 0; j<3; j++){
            space(1);
            System.out.println(cube.getLine(Color.U, j));
        }
        for(j = 0; j<3; j++){
            System.out.print(cube.getLine(Color.L, j)+ "|");
            System.out.print(cube.getLine(Color.F, j)+ "|");
            System.out.print(cube.getLine(Color.R, j)+ "|");
            System.out.print(cube.getLine(Color.B, j)+ "|");
            System.out.println(' ');
        }
        for(j = 0; j<3; j++){
            space(1);
            System.out.println(cube.getLine(Color.D, j));
        }

    }

    private static void outSide(Cube cube, Color color, int num){
        for(int j = 0; j<3; j++){
            System.out.print(cube.getSide(color).getColor(num,j));
            System.out.print(' ');
        }
    }
}
