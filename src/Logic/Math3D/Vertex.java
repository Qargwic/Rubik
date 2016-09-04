package Logic.Math3D;

/**
 * Created by Дмитрий on 31.08.2016.
 */
public class Vertex {

    private double [] vertex;
    private int length;

    public Vertex(double [] arr){
        length = arr.length;
        vertex = arr.clone();

    }

    public Vertex mult(double a){
        for(int i =  0; i < length; i++) vertex[i] *= a;
        return this;
    }

    public Vertex add(double a){
        for(int i =  0; i < length; i++) vertex[i] += a;
        return this;
    }

    public int [] toInt(){
        int [] ans = new int[length];
        for(int i = 0; i < length; i++){
            ans[i] = (int)Math.round(vertex[i]);
        }
        return ans;
    }

    @Override
    public String toString(){
        String str = "";
        for(double i : vertex){
            str = str + String.format("%10.2f ", i);
        }
        return str;
    }
}
