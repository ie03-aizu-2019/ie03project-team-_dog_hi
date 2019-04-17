import java.util.Scanner;

public class phase1_1 {
    public static final double EPS = 1.0e-7;  //EPS=1.0*10^(-7)
    protected static int N, M, P, Q;
    protected static int[] x,y,b_p,e_q;//b_p[0]:P1, e_q[0]:Q1, b_p[1]:P2, e_q[1]:Q2
    protected static double s,t, x_intersection, y_intersection;

    //main method
    public static void main(String[] argv){
        input();
        search_intersection();
    }

    public static void search_intersection(){
        double matrix_a;    // matrix_a is |A|
        boolean exist_intersection = false;
        matrix_a = (x[e_q[0]] - x[b_p[0]]) * (y[b_p[1]] - y[e_q[1]]) + (x[e_q[1]] - x[b_p[1]]) * (y[e_q[0]] - y[b_p[0]]);
        //step1
        if(matrix_a <= EPS  && -EPS <= matrix_a){
            exist_intersection = false;
        } else {
            exist_intersection = true;
            //step2 (calculate s and t)
            s = ((y[b_p[1]] - y[e_q[1]]) * (x[b_p[1]] - x[b_p[0]]) + (x[e_q[1]] - x[b_p[1]]) * (y[b_p[1]] - y[b_p[0]])) / matrix_a;
            t = ((y[b_p[0]] - y[e_q[0]]) * (x[b_p[1]] - x[b_p[0]]) + (x[e_q[0]] - x[b_p[0]]) * (y[b_p[1]] - y[b_p[0]])) / matrix_a;
        }
        if(exist_intersection == true){
            //step3
            if(0<= s && s<=1  && 0<=t && t<=1){
                exist_intersection = true;
                //step4
                x_intersection = x[b_p[0]] + (x[e_q[0]] - x[b_p[0]]) * s;   //x = x_P1 + (x_Q1 - x_P1)*s
                y_intersection = y[b_p[0]] + (y[e_q[0]] - y[b_p[0]]) * s;
            } else {
                exist_intersection = false;
            }
        }

        //search for contact point
        for(int i=0; i<N ;i++){
            if(x[i]==x_intersection && y[i]==y_intersection){
                exist_intersection = false;
            }
        }
        //print
        if(exist_intersection == true){
            System.out.println(String.format("%.5f",x_intersection));
            System.out.println(String.format("%.5f",y_intersection));
        } else {
            System.out.println("NA");
        }
    }

    public static void input(){
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        P = scan.nextInt();
        Q = scan.nextInt();
        x = new int[N];
        y = new int[N];
        b_p = new int[M];
        e_q = new int[M];
        for(int i=0; i<N; i++){
            x[i] = scan.nextInt();
            y[i] = scan.nextInt();
        }
        for(int i=0; i<M; i++){
            b_p[i] = scan.nextInt() - 1;
            e_q[i] = scan.nextInt() - 1;
        }
    }
}