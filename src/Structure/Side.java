package Structure;

import Structure.Enum.Color;

public class Side {

    final Color id;

    private Color color[][] = new Color[3][3];

    public Side(Color id){
        this.id = id;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                color[i][j] = id;
            }
        }
    }

    public Color getColor(int i, int j){
        return color[i][j];
    }
}
