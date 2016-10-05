package Logic.Math3D;

public class Matrix {

    private int rawCount = 0;
    private Vector3 [] matrix3;

    public Matrix(Vector3 [] vectors){
        matrix3 = new Vector3[vectors.length];
        for (Vector3 i : vectors){
            matrix3[rawCount] = i;
            rawCount++;
        }
    }

    public Vector3 getVector(int i){
        return matrix3[i];
    }

    public Vector3 getColumn(int i){
        if (rawCount != 4) return null;
        return new Vector3(
                matrix3[0].getRaw(i),
                matrix3[1].getRaw(i),
                matrix3[2].getRaw(i),
                matrix3[3].getRaw(i)
        );
    }

    public int getRaw(){
        return rawCount;
    }

    public void dec(){
        for(int i = 0; i < rawCount; i++){
            matrix3[i].dec();
        }
    }
    public Vertex getX(int [] arr){
        double [] ans = new double[arr.length];
        for (int i = 0; i < arr.length; i++){
            ans[i] = matrix3[arr[i]].x;
        }
        return new Vertex(ans);
    }

    public Vertex getY(int [] arr){
        double [] ans = new double[arr.length];
        for (int i = 0; i < arr.length; i++){
            ans[i] = matrix3[arr[i]].y;
        }
        return new Vertex(ans);
    }
    public double[] getZ(){
        double [] ans = new double[rawCount];
        for (int i = 0; i < rawCount; i++){
            ans[i] = matrix3[i].z;
        }
        return ans;
    }

    public Vertex getZ(int [] arr){
        double [] ans = new double[arr.length];
        for (int i = 0; i < arr.length; i++){
            ans[i] = matrix3[arr[i]].y;
        }
        return new Vertex(ans);
    }

    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < rawCount; i++){
            str = str + getVector(i).toString() + "\n";
        }
        return str;
    }


}
