package Logic.Math3D;

public class Vector3 {

    public double x;
    public double y;
    public double z;
    public double w = 1;

    public Vector3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector3(double x, double y, double z, double w){
        this(x, y, z);
        this.w = w;
    }

    public void add(double a){
        x += a;
        y += a;
        z += a;
    }

    public void mult(double a){
        x *= a;
        y *= a;
        z *= a;
    }

    public void dec(){
        if (w == 1 || w == 0) return;
        x/=w;
        y/=w;
        z/=w;
        w = 1;
    }

    public double getLen(){
        return Math.sqrt(x*x + y*y + z*z);
    }

    public double getRaw(int i){
        switch (i){
            case 0: return x;
            case 1: return y;
            case 2: return z;
            case 3: return w;
        }
        return 0;
    }
    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < 4; i++){
            str = str + String.format("%10.2f ", getRaw(i));
        }
        return str;
    }
}
