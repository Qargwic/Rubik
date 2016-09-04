package Structure.Enum;

public enum Line {
    UP(0), RIGHT(1), DOWN(2), LEFT(3);

    public final int ang;

    Line(int ang){
        this.ang = ang;
    }
}
