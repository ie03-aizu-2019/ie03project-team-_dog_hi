public class phase2_7 extends phase1_1{
    // protected static int o_x,o_y,a_x,a_y,b_x,b_y;
    protected static double[][] array;
    protected static double[] minimum;
    protected static double minimum_number;
    protected static double a_vector,b_vector,inner_product,intersection_p,x_intersection,y_intersection,distance,sameline,cos;
    public static void main(String[] args){
	input();
	calculate();
	get_minimum_number();
	get_new_road();
	//	output();
    }

    public static void calculate(){
	
	array = new double[P][M];
	for(int i = 0;i<P;i++){
	    for(int j = 0;j<M;j++){

		
		a_vector = Math.sqrt((x[e_q[j]]-x[b_p[j]])*(x[e_q[j]]-x[b_p[j]])+(y[e_q[j]]-y[b_p[j]])*(y[e_q[j]]-y[b_p[j]]));
		b_vector = Math.sqrt(Math.abs((x[N+i]-x[b_p[j]])*(x[N+i]-x[b_p[j]])+(y[N+i]-y[b_p[j]])*(y[N+i]-y[b_p[j]])));

		//a*b_vector  'naiseki'
		inner_product = (((x[e_q[j]] - x[b_p[j]]) * (x[N+i] - x[b_p[j]])) + ((y[e_q[j]] - y[b_p[j]]) * (y[N+i]-y[b_p[j]])));

		intersection_p = (inner_product / (a_vector*a_vector));

		//op_vector = ? / ? oa_vextor
	
		x_intersection = (x[b_p[j]] + ((x[e_q[j]] - x[b_p[j]]) * intersection_p));
		y_intersection = (y[b_p[j]] + ((y[e_q[j]] - y[b_p[j]]) * intersection_p));

         

		distance = Math.sqrt(Math.abs(((Math.abs(intersection_p) * a_vector) * (Math.abs(intersection_p) * a_vector)) - (b_vector * b_vector)));

		sameline = y[b_p[j]]*(x[e_q[j]]-x[N+i]) + y[e_q[j]]*(x[N+i]-x[b_p[j]]) + y[N+i]*(x[b_p[j]] - x[e_q[j]]); 
		if(sameline == 0){
		    distance = 0;
		}

		cos = inner_product / (Math.abs(a_vector) * Math.abs(b_vector));
		if(0 > cos && cos >= -1 ){
		    distance = 200;//Big number
		}
		array[i][j] = distance;
		

	    }
	

	}
    }

    
    
    public static void get_minimum_number(){
	minimum = new double[P];
	for(int i = 0;i<P;i++){
	    minimum_number = array[i][0];
	    for(int j = 0;j<M;j++){
		if(array[i][j] < minimum_number){
		    minimum_number = array[i][j];
		}
		
	    }
	    minimum[i] = minimum_number;
	}
    }

    public static void get_new_road(){
	for(int i = 0;i<P;i++){
	    for(int j = 0;j<M;j++){
		if(minimum[i] == array[i][j]){

                  a_vector = Math.sqrt((x[e_q[j]]-x[b_p[j]])*(x[e_q[j]]-x[b_p[j]])+(y[e_q[j]]-y[b_p[j]])*(y[e_q[j]]-y[b_p[j]]));
		  b_vector = Math.sqrt(Math.abs((x[N+i]-x[b_p[j]])*(x[N+i]-x[b_p[j]])+(y[N+i]-y[b_p[j]])*(y[N+i]-y[b_p[j]])));

		  //a*b_vector  'naiseki'
		  inner_product = (((x[e_q[j]] - x[b_p[j]]) * (x[N+i] - x[b_p[j]])) + ((y[e_q[j]] - y[b_p[j]]) * (y[N+i]-y[b_p[j]])));

		  intersection_p = (inner_product / (a_vector*a_vector));

		  //op_vector = ? / ? oa_vextor
	
		  x_intersection = (x[b_p[j]] + ((x[e_q[j]] - x[b_p[j]]) * intersection_p));
		  y_intersection = (y[b_p[j]] + ((y[e_q[j]] - y[b_p[j]]) * intersection_p));

		  distance = Math.sqrt(Math.abs(((Math.abs(intersection_p) * a_vector) * (Math.abs(intersection_p) * a_vector)) - (b_vector * b_vector)));

		  sameline = y[b_p[j]]*(x[e_q[j]]-x[N+i]) + y[e_q[j]]*(x[N+i]-x[b_p[j]]) + y[N+i]*(x[b_p[j]] - x[e_q[j]]); 
		  if(sameline == 0){
		      distance = 0;
		  }

		  if(distance == 0){
		      x_intersection = x[e_q[j]];
		      y_intersection = y[b_p[j]];
		  }

		  System.out.print(x_intersection + " ");
		  System.out.println(y_intersection);
		}
	    }
	}
     
    } 
    
    //    public static void output(){
    //
    //	for(int i = 0;i<P;i++){
    //    for(int j = 0;j<M;j++){
    //	System.out.print(array[i][j]+" ");
    //    }
    //    System.out.println(" ");
    //}
    //for(int i = 0;i<P;i++){
    //    System.out.print(minimum[i]+" ");
    //}
    //System.out.println(" ");
	
    //}
}
