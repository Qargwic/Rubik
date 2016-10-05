package Logic.Math3D;

public class Math3D {

    public static double sq(double a){ return a*a;}

    public static Vector3 add(Vector3 a, Vector3 b){
        return new Vector3(a.x+b.x, a.y+b.y,a.z+b.z);
    }

    public static Vector3 sub(Vector3 a, Vector3 b){
        return new Vector3(a.x-b.x, a.y-b.y,a.z-b.z);
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

    public static double getCos(Vector3 a, Vector3 b, PVector v){
        Vector3 b1 = sub(b, a);
        Vector3 a1 = new Vector3(a.x - v.x, a.y - v.y, a.z - v.z);
        return (a1.x*b1.x + a1.y*b1.y + a1.z*b1.z)/(a1.getLen()*b1.getLen());
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
    public static Matrix proectionZ(PVector v){
        return new Matrix(new Vector3 [] {
                new Vector3(
                        v.getCos('b')*v.getCos('g'),
                        v.getCos('b')*v.getSin('g'),
                        0,
                        v.getSin('b')/v.getLen() ),
                new Vector3(
                        v.getCos('g')*v.getSin('a')*v.getSin('b') - v.getCos('a')*v.getSin('g'),
                        v.getSin('g')*v.getSin('a')*v.getSin('b') + v.getCos('a')*v.getCos('g'),
                        0,
                        -v.getCos('b')*v.getSin('a')/v.getLen()),
                new Vector3(
                        v.getCos('g')*v.getCos('a')*v.getSin('b') + v.getSin('a')*v.getSin('g'),
                        v.getCos('a')*v.getSin('b')*v.getSin('g') - v.getCos('g')*v.getSin('a'),
                        0,
                        -v.getCos('a')*v.getCos('b')/v.getLen()),
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
        double min = a[0];
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
