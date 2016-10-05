package Logic.Math3D;

public class PVector extends Vector3 {

    private double length;
    private double alpha = 0;
    private double betta = 0;
    private double cosA = 1;
    private double cosB = 1;
    private double sinA = 0;
    private double sinB = 0;
    private PVector normV;

    public PVector(double len){
        super(0,0,len);
        length = len;
        z = length;
        x = 0;
        y = 0;
        normV = new PVector(length, false);
    }
    public PVector(double len, boolean f){
        super(0,0,len);
        length = len;
        z = length;
        x = 0;
        y = 0;
        if (f) normV = new PVector(length);
    }
    public void setNorm(){
        System.out.println('q');
        normV.setAngle(alpha, betta);
        normV.x = x;
        normV.y = y;
        normV.y = y;
    }
    public void addAngle(int dx, int dy){
        double a = Math.atan(dy/length);
        double b = Math.atan(dx/length);
        if(a != 0){
            a = a * Math.PI / 180;
            alpha += a;
            //if (alpha > Math.PI/2) alpha = Math.PI/2;
            //if (alpha < -Math.PI/2) alpha = -Math.PI/2;
            cosA = Math.cos(alpha);
            sinA = Math.sin(alpha);
        }
        if(b != 0){
            b = b * Math.PI / 180;
            betta += b;
            if (betta > 2*Math.PI) betta -= 2*Math.PI;
            if (-betta < 2*Math.PI) betta  += 2*Math.PI;
            cosB = Math.cos(betta);
            sinB = Math.sin(betta);
        }
        setCoord();
        //System.out.format("X: %5.2f  Y: %5.2f  Z: %5.2f  a: %5.2f\n",x ,y ,z, alpha);
    }

    public double getCos(char a){
        if (a == 'a'){
            return cosA;
        }
        if (a=='b'){
            return cosB;
        }
        return 1;
    }

    public double getSin(char a){
        if (a == 'a'){
            return sinA;
        }
        if (a == 'b'){
            return sinB;
        }
        return 0;
    }

    @Override
    public double getLen(){
        return length;
    }
    protected void setAngle(double a, double b){
        cosB = Math.cos(b);
        cosA = Math.cos(a);
        sinB = Math.sin(b);
        sinA = Math.sin(a);
    }
    protected void setCoord(){
        x = - length*sinB;
        y = length*cosB*sinA;
        z = length*cosA*cosB;/*
        x = length*(cosG*sinB + cosB*sinA*sinG);
        y = length*(sinB*sinG - cosB*cosG*sinA);
        z = length*cosA*cosB;*/
        /*x = length*(sinG*sinA + cosB*cosA*cosG);
        y = length*(-sinA*cosG + cosA*cosB*sinG);
        z = length*cosA*sinB;*/
    }

}
