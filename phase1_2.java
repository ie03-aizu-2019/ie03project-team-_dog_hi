public class phase1_2 extends phase1_1 {
    protected static Point temp;
    protected static double differenceX;
    
    public static void main(String[] argv){
	input();
	Point[] point = new Point[(M*M-M)/2];
	search_intersection02(point);
	sort(point);
	output(point);
    }
    
    //methods
    public static void sort(Point[] point){
	for(int i=0; i<(M*M-M)/2-1; i++){
	    for(int j=(M*M-M)/2-1; j>=i; j--){
		if(point[i].x >= point[j].x){
		    differenceX = point[i].x - point[j].x;
		    // branch equal
		    if(-EPS<differenceX && differenceX<EPS){
			
			if(point[i].y > point[j].y){
			    temp = point[i];
			    point[i] = point[j];
			    point[j] = temp;
			}
			
		    } else {//point[i].x > point[j].x
			temp = point[i];
			point[i] = point[j];
			point[j] = temp;
		    }
		}
	    }
	}
    }

    public static void search_intersection02(Point[] point){
	int k = 0;
	for(int i=0; i<M-1; i++){
	    for(int j=i+1; j<M; j++){
		point[k] = search_intersection(i, j);
		k++;
	    }
	}
    }
    
    public static void output(Point[] point){
	for(int i=0; i<(M*M-M)/2; i++){
	    if(point[i].x != 0  &&  point[i].y !=0){
		System.out.print(String.format("%.5f", point[i].x));
		System.out.println(String.format(" %.5f", point[i].y));
	    }
	}
    }
}
