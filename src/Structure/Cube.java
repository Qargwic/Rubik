package Structure;

import Structure.Enum.Color;
import Structure.Enum.Line;
import View.View;

public class Cube {

    public static void main(String[] args){
        Cube cube = new Cube();
        System.out.println(cube.overallSide(Color.B,Color.R));
        cube.turn(Color.B,1);View.view(cube);
        cube.turn(Color.L,3);View.view(cube);
        cube.turn(Color.F,2);View.view(cube);
    }

    private Side [] sides = new Side[6];

    private Color[] edge = new Color[24];

    private Color[] angle = new Color[24];

    private int colorID (Color a){
        return a.ordinal()*4;
    }

    private Line overallSide(Color front, Color t){

        if (front == Color.D && t != Color.U){
            return Line.DOWN;
        }
        if (front == Color.U && t != Color.U){
            return Line.UP;
        }
        if (front == Color.L && t != Color.R){
            if(t == Color.B) return Line.RIGHT;
            return Line.LEFT;
        }
        if (front == Color.R && t != Color.L){
            if(t == Color.B) return Line.LEFT;
            return Line.RIGHT;
        }
        if (front == Color.F && t!= Color.B){
            switch(t){
                case D:
                    return Line.UP;
                case U:
                    return Line.DOWN;
                case R:
                    return Line.LEFT;
                case L:
                    return Line.RIGHT;
            }
        }
        if (front == Color.B && t!= Color.F){
            switch(t){
                case U:
                    return Line.UP;
                case D:
                    return Line.DOWN;
                case L:
                    return Line.LEFT;
                case R:
                    return Line.RIGHT;
            }
        }
        return null;
    }

    private void miniTurn(Color front, Color from, Color to){
        Line line1  = overallSide(front, from);
        Line line2  = overallSide(front, to);

        edge[colorID(to) + line2.ang] = edge[colorID(from) + line1.ang];
        angle[colorID(to) + line2.ang] = angle[colorID(from) + line1.ang];
        angle[colorID(to) + (line2.ang + 1)%4] = angle[colorID(from) + (line1.ang + 1)%4];

    }
    private void miniTurnUnbuf(Color front, Color to, Color [] s){
        Line line  = overallSide(front, to);

        edge[colorID(to) + line.ang] = s[0];
        angle[colorID(to) + line.ang] = s[1];
        angle[colorID(to) + (line.ang + 1)%4] = s[2];

    }
    private Color[] miniTurnBuf(Color front, Color from){
        Line line = overallSide(front, from);
        return new Color[] {
                edge[colorID(from) + line.ang],
                angle[colorID(from) + line.ang],
                angle[colorID(from) + (line.ang + 1)%4]
        };
    }

    public Cube(){
        Color c[] = Color.values();
        for(int i = 0; i<6; i++){
            sides[i] = new Side(c[i]);
        }
        for(int i = 0; i<6; i++){
            for(int j = 0; j<4; j++){
                edge[i*4+j] = c[i];
                angle[i*4+j] = c[i];
            }
        }
    }

    public void turn(Color id, int pi) {
        pi%=4;
        if (pi<0) pi = 4+pi;
        if (pi == 0) return;
        //int st = id.ordinal()*4;
        int st = colorID(id);

        for (int i = 0; i < pi; i++) {
            int a = 1;
            Color buf = edge[st];
            edge[st] = edge[st + a];
            edge[st + a] = edge[st + ++a];
            edge[st + a] = edge[st + ++a];
            edge[st + a] = buf;
            a = 1;
            buf = angle[st];
            angle[st] = angle[st + a];
            angle[st + a] = angle[st + ++a];
            angle[st + a] = angle[st + ++a];
            angle[st + a] = buf;

            Color[] turnSide = id.turnSide();
            Color[] buf1 = miniTurnBuf(id, turnSide[0]);
            for (int j = 0; j < 3; j++) {
                miniTurn(id, turnSide[j + 1], turnSide[j]);
            }
            miniTurnUnbuf(id, turnSide[3], buf1);
        }
    }


    public Side getSide(Color color){
        Color c[] = Color.values();
        for(int j = 0; j<6; j++){
            if (c[j] == color) return sides[j];
        }
        return null;
    }

    public String getLine(Color id, int num){
        num++;
        String foo = "";
        if (num == 1){
            foo = angle[id.ordinal()*4].toString()+' '+ edge[id.ordinal()*4].toString()+' '+angle[id.ordinal()*4+1].toString();
        }
        if (num == 2){
            foo = edge[id.ordinal()*4+3].toString()+' '+ id+' '+edge[id.ordinal()*4+1];
        }
        if (num == 3){
            foo = angle[id.ordinal()*4+3].toString()+' '+ edge[id.ordinal()*4+2].toString()+' '+angle[id.ordinal()*4+2].toString();
        }
        return foo+" ";
    }

}
