import java.util.Scanner;

public class phase1_1 {
    public static final double EPS = 1.0e-7;  //EPS=1.0*10^(-7)
    protected static int N, M, P, Q;
    protected static int[] x,y,b_p,e_q,k;//b_p[0]:P1, e_q[0]:Q1, b_p[1]:P2, e_q[1]:Q2
    protected static String[] start,end;
    protected static double s,t, x_intersection, y_intersection;

    //main method
    public static void main(String[] argv){
	Point p1 = new Point();
        input();
        p1=search_intersection(0, 1);
        output(p1);
    }

    public static Point search_intersection(int i, int j){
        double matrix_a;    // matrix_a is |A|
        boolean exist_intersection = false;
        Point point1 = new Point();
        matrix_a = (x[e_q[i]] - x[b_p[i]]) * (y[b_p[j]] - y[e_q[j]]) + (x[e_q[j]] - x[b_p[j]]) * (y[e_q[i]] - y[b_p[i]]);
        //step1
        if(matrix_a <= EPS  && -EPS <= matrix_a){
            exist_intersection = false;
        } else {
            exist_intersection = true;
            //step2 (calculate s and t)
            s = ((y[b_p[j]] - y[e_q[j]]) * (x[b_p[j]] - x[b_p[i]]) + (x[e_q[j]] - x[b_p[j]]) * (y[b_p[j]] - y[b_p[i]])) / matrix_a;
            t = ((y[b_p[i]] - y[e_q[i]]) * (x[b_p[j]] - x[b_p[j]]) + (x[e_q[i]] - x[b_p[i]]) * (y[b_p[j]] - y[b_p[i]])) / matrix_a;
        }
        if(exist_intersection == true){
            //step3
            if(0<= s && s<=1  && 0<=t && t<=1){
                exist_intersection = true;
                //step4
                x_intersection = x[b_p[i]] + (x[e_q[i]] - x[b_p[i]]) * s;   //x = x_P1 + (x_Q1 - x_P1)*s
                y_intersection = y[b_p[i]] + (y[e_q[i]] - y[b_p[i]]) * s;
            } else {
                exist_intersection = false;
            }
        }

        //search for contact point
        for(int k=0; k<N ;k++){
            if(x[k]==x_intersection && y[k]==y_intersection){
                exist_intersection = false;
            }
        }
        //print
        if(exist_intersection == true){
	    point1.x = x_intersection;
	    point1.y = y_intersection;
        } else {
	    point1.x = 0;
	    point1.y = 0;
        }
	return point1;
    }

    public static void input(){
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        P = scan.nextInt();
        Q = scan.nextInt();
        x = new int[N+P];
        y = new int[N+P];
        b_p = new int[M];
        e_q = new int[M];
        start = new String[Q];
        end = new String[Q];
        k = new int[Q];
        for(int i=0; i<N; i++){
            x[i] = scan.nextInt();
            y[i] = scan.nextInt();
        }
        for(int i=0; i<M; i++){
            b_p[i] = scan.nextInt() - 1;
            e_q[i] = scan.nextInt() - 1;
        }
        for(int i=N; i<N+P; i++){
            x[i] = scan.nextInt();
            y[i] = scan.nextInt();
        }
        for(int i=0; i<Q; i++){
            start[i] = scan.next();
            end[i] = scan.next();
            k[i] = scan.nextInt();
        }

    }

    public static void output(Point point){
        if(point.x != 0  &&  point.y != 0){
            System.out.print(String.format("%.5f", point.x));
            System.out.println(String.format(" %.5f", point.y));        
        } else {
            System.out.println("NA");
        }
    }
}

class Point {
    double x;
    double y;
}
