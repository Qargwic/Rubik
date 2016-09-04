package Logic.Math3D;

public class Math3D {

    private static int[][] sides = {{0, 1, 2, 3}, {4, 7, 6, 5}, {0, 4, 5, 1}, {3, 2, 6, 7}, {0, 3, 7, 4}, {1, 5, 6, 2}};

    public static Vector3 add(Vector3 a, Vector3 b){
        return new Vector3(a.x+b.x, a.y+b.y,a.z+b.z);
    }

    public static double mult(Vector3 a, Vector3 b){
        return a.x*b.x + a.y*b.y + a.z*b.z + a.w*b.w;
    }

    public static Matrix mult(Matrix A, Matrix B){
        if (A == null && B == null) return null;
        if (B == null) return A;
        if (A == null) return B;
        if (B.getRaw() != 4) return null;
        Vector3 [] vec = new Vector3[A.getRaw()];
        for(int i = 0; i<A.getRaw();i++){
            vec[i] = new Vector3(
                    mult(A.getVector(i), B.getColumn(0)),
                    mult(A.getVector(i), B.getColumn(1)),
                    mult(A.getVector(i), B.getColumn(2)),
                    mult(A.getVector(i), B.getColumn(3))
            );
        }
        return new Matrix(vec);
    }


    public static Matrix mult(Matrix ... mat){
        Matrix ans = mat[0];
        for(int i = 0; i < mat.length - 1; i++){
            ans = mult(ans,mat[i+1]);
        }
        return ans;
    }

    public static Matrix proectionZ(double z){
        return new Matrix(new Vector3 [] {
                new Vector3(1, 0, 0, 0),
                new Vector3(0, 1, 0, 0),
                new Vector3(0, 0, 0, -1/z),
                new Vector3(0, 0, 0, 1),
        });
    }

    public static Matrix turnX(double alpha){
        if (alpha == 0) return null;
        alpha = alpha / Math.PI * 180;
        return new Matrix(new Vector3 [] {
                new Vector3(1, 0, 0, 0),
                new Vector3(0, Math.cos(alpha), Math.sin(alpha), 0),
                new Vector3(0, -Math.sin(alpha), Math.cos(alpha), 0),
                new Vector3(0, 0, 0, 1),
        });
    }
    public static Matrix turnY(double alpha){
        if (alpha == 0) return null;
        alpha = alpha / Math.PI * 180;
        return new Matrix(new Vector3 [] {
                new Vector3(Math.cos(alpha), 0, -Math.sin(alpha), 0),
                new Vector3(0, 1, 0, 0),
                new Vector3(Math.sin(alpha), 0, Math.cos(alpha), 0),
                new Vector3(0, 0, 0, 1),
        });
    }
    public static Matrix turnZ(double alpha){
        if (alpha == 0) return null;
        alpha = alpha / Math.PI * 180;
        return new Matrix(new Vector3 [] {
                new Vector3( Math.cos(alpha), Math.sin(alpha), 0, 0),
                new Vector3(-Math.sin(alpha), Math.cos(alpha), 0, 0),
                new Vector3(0, 0, 1, 0),
                new Vector3(0, 0, 0, 1),
        });
    }
    //Сотировка от меньшего
    public static int [] sort(double [] a){
        double min = 100;
        int length = a.length;
        int [] ans = new int[length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (a[j] < min){
                    ans[i] = j;
                    min = a[j];
                }
            }
            a[ans[i]] = 1_000_000;
            min = 100_000;
        }
        return ans;
    }

}
