package Structure.Enum;

//public enum Color {WHITE, GREEN, RED, BLUE, ORANGE, YELLOW}
public enum Color {
    D, F, R, B, L, U;
    public  Color [] turnSide(){
        switch (this){
            case D:
                return new Color[] {F,R,B,L};
            case F:
                return new Color[] {U,R,D,L};
            case R:
                return new Color[] {F,U,B,D};
            case B:
                return new Color[] {U,L,D,R};
            case L:
                return new Color[] {F,D,B,U};
            case U:
                return new Color[] {F,L,B,R};
        }
        return null;
    }
}
