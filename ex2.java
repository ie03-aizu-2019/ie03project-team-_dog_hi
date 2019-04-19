import java.util.Scanner;

public class ex2{
    
    public static final double EPS = 1.0e-7;
    protected static int N,M,P,Q;
    protected static int[] x;
    protected static int[] y;
    protected static int[] b;
    protected static int[] e;
       
    protected static double s,t,xintersection,yintersection;


    public static void main(String[] args){
	input();

	calculate();
    }
    public static void calculate(){
	int hanteikaisu = 0;
	for(int i = M-1;i>0;i--){
	    hanteikaisu += i;
	    /* System.out.println(hanteikaisu)*/; //今回はhanteikaisu = 10;
	}
       
	int[] kousahantei;
	kousahantei = new int[hanteikaisu];
        double[] a; //is |A|
        a = new double[hanteikaisu];
	double[] koutenx;
	koutenx = new double[hanteikaisu];
	double[] kouteny;
	kouteny = new double[hanteikaisu];
	    boolean exist = false;
        int h;
	int j;
	int k = 1;
	int count = 0;
	for(int i=0;i<hanteikaisu;i++){//i = 0 ~ i = 9 ; 10 loop
           
	    for(j = k-1;j>=0;j--){
        
		    
		a[i] = (x[e[j]] - x[b[j]]) * (y[b[k]]-y[e[k]]) + (x[e[k]]-x[e[k]])*(y[e[j]]-y[b[j]]);// conpare 0 and 1;

		
		if(a[i]<= EPS && -EPS <= a[i]){
		    exist = false;
             
		}else{
		    exist = true;
		    s = ((y[b[k]] - y[e[k]]) * (x[b[k]] - x[b[j]]) + (x[e[k]] - x[b[k]]) * (y[b[k]] - y[b[j]])) / a[i];
		    t = ((y[b[j]] - y[e[j]]) * (x[b[k]] - x[b[j]]) + (x[e[j]] - x[b[j]]) * (y[b[k]] - y[b[j]])) / a[i];
		}
		if(exist == true){
		    //step3
		    if(0<= s && s<=1  && 0<=t && t<=1){
			exist = true;
			//step4
			xintersection = x[b[j]] + (x[e[j]] - x[b[j]]) * s;   //x = x_P1 + (x_Q1 - x_P1)*s
			yintersection = y[b[j]] + (y[e[j]] - y[b[j]]) * s;
	        
			koutenx[count] = xintersection;
			kouteny[count] = yintersection;
			count++;
		        
		    } else {
			exist = false;
		    }
		   
		}
	        
		k++;
		i++;
        
	    }
	}
        for(h = 0;h<count;h++){
	    System.out.println(koutenx[h]+" "+kouteny[h]);
	}

    }
    public static void input(){

	Scanner scan = new Scanner(System.in);

	int N = scan.nextInt();
	int M = scan.nextInt();
	int P = scan.nextInt();
	int Q = scan.nextInt();
	int[] x;
	x = new int[N];
	int[] y;
	y = new int[N];
	int[] s;
	b = new int[M];
	int[] t;
	e = new int[M];
	/* System.out.println("N="+N+" M= "+M+" P= "+P+" Q= "+Q);*/

	for(int i = 0;i < N;i++){
	   
	    x[i] = scan.nextInt();
	    y[i] = scan.nextInt();//x,y座標をあたえる
	   
	}
	for(int j = 0;j < M;j++){

	    b[j] = scan.nextInt();
	    e[j] = scan.nextInt();//道をつくる
	   
	}
	/* for(i=0;i < N;i++){
	   System.out.println(x[i]+" "+y[i]);
	   }*/

    }
       
       
}

    

