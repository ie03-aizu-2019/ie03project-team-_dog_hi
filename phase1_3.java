public class phase1_3 extends phase1_2 {
    public static final double INFINITY = 10.0/0.0;
    protected static int countIntersection = 0;
    protected static double[][] Matrix;
    protected static int[] point1Intersection;
    protected static int[] point2Intersection;
    protected static int[] point3Intersection;
    protected static int[] point4Intersection;
    protected static int[] elementIntersection;
    public static void main(String[] argv){
        input();
        searchIntersection02();
        initializeMinimumSpanningTree_Matrix();
        matrixOutput();
    }

    //methods
    public static void initializeMinimumSpanningTree_Matrix(){
        Matrix = new double[N+countIntersection][N+countIntersection];
        boolean isExist;
        int nowElement=-1;
        elementIntersection = new int[countIntersection];
        int kk=0;//element of elementIntersection[kk]

        for(int i=0; i < N+countIntersection; i++){
            for(int j=i; j < N+countIntersection; j++){
                if(i==j){
                    Matrix[i][j] = INFINITY;
                    Matrix[j][i] = INFINITY;
                } else if(i<N){
                    if(j<N){
                        isExist = false;
                        for(int k=0; k<M; k++){
                            if(b_p[k]==i && e_q[k]==j){
                                isExist = true;
                                Matrix[i][j] = calculateDistance(x[i], y[i], x[j], y[j]);
                                Matrix[j][i] = Matrix[i][j];
                            }
                            if(isExist == false){
                                Matrix[i][j] = INFINITY;
                                Matrix[j][i] = INFINITY;
                            }
                        }
                    }
                } else {/* i>=N and i<N+countIntersection */}
            }
        }

        //calculate distance include intersections
        for(int i=N; i<N+countIntersection; i++){
            for(int ii=nowElement+1; ii<((M*M)-M)/2; ii++){
                if(point[ii].x != 0 && point[ii].y != 0 && nowElement != ii){
                    Matrix[point1Intersection[ii]][i] = calculateDistance(x[point1Intersection[ii]], y[point1Intersection[ii]], point[ii].x, point[ii].y);
                    Matrix[point2Intersection[ii]][i] = calculateDistance(x[point2Intersection[ii]], y[point2Intersection[ii]], point[ii].x, point[ii].y);
                    Matrix[point3Intersection[ii]][i] = calculateDistance(x[point3Intersection[ii]], y[point3Intersection[ii]], point[ii].x, point[ii].y);
                    Matrix[point4Intersection[ii]][i] = calculateDistance(x[point4Intersection[ii]], y[point4Intersection[ii]], point[ii].x, point[ii].y);
                    Matrix[i][point1Intersection[ii]] = Matrix[point1Intersection[ii]][i];
                    Matrix[i][point2Intersection[ii]] = Matrix[point2Intersection[ii]][i];
                    Matrix[i][point3Intersection[ii]] = Matrix[point3Intersection[ii]][i];
                    Matrix[i][point4Intersection[ii]] = Matrix[point4Intersection[ii]][i];
                    nowElement = ii;
                    elementIntersection[kk++] = ii;
                    //System.out.println(elementIntersection[kk-1]);
                    break;
                }
            }
        }

        //calculate distance intersection to intersection
        int coincidence;
        for(int i=0; i<countIntersection; i++){
            for(int j=i+1; j<countIntersection; j++){
                coincidence = 0;
                if(point1Intersection[elementIntersection[i]] == point1Intersection[elementIntersection[j]] || point1Intersection[elementIntersection[i]] == point2Intersection[elementIntersection[j]] || point1Intersection[elementIntersection[i]] == point3Intersection[elementIntersection[j]] || point1Intersection[elementIntersection[i]] == point4Intersection[elementIntersection[j]])coincidence++;
                if(point2Intersection[elementIntersection[i]] == point1Intersection[elementIntersection[j]] || point2Intersection[elementIntersection[i]] == point2Intersection[elementIntersection[j]] || point2Intersection[elementIntersection[i]] == point3Intersection[elementIntersection[j]] || point2Intersection[elementIntersection[i]] == point4Intersection[elementIntersection[j]])coincidence++;
                if(point3Intersection[elementIntersection[i]] == point1Intersection[elementIntersection[j]] || point3Intersection[elementIntersection[i]] == point2Intersection[elementIntersection[j]] || point3Intersection[elementIntersection[i]] == point3Intersection[elementIntersection[j]] || point3Intersection[elementIntersection[i]] == point4Intersection[elementIntersection[j]])coincidence++;
                if(point4Intersection[elementIntersection[i]] == point1Intersection[elementIntersection[j]] || point4Intersection[elementIntersection[i]] == point2Intersection[elementIntersection[j]] || point4Intersection[elementIntersection[i]] == point3Intersection[elementIntersection[j]] || point4Intersection[elementIntersection[i]] == point4Intersection[elementIntersection[j]])coincidence++;
                //System.out.println(coincidence);
                if(coincidence >= 3){
                    Matrix[i+N][j+N] = calculateDistance(point[elementIntersection[i]].x, point[elementIntersection[i]].y, point[elementIntersection[j]].x, point[elementIntersection[j]].y);
                    Matrix[j+N][i+N] = Matrix[i+N][j+N];
                }
            }
        }

        //set infinite
        for(int i=0;i<N+countIntersection;i++){
            for(int j=i;j<N+countIntersection;j++){
                if(Matrix[i][j]==0){
                    Matrix[i][j]=INFINITY;
                    Matrix[j][i]=INFINITY;
                }
            }
        }
    }// end of initializeMinimumSpanningTree_Matrix
    public static void matrixOutput(){
        for(int i=0;i<N+countIntersection;i++){
            for(int j=0;j<N+countIntersection;j++){
                System.out.print(String.format("%.6f", Matrix[i][j])+"  ");
            }
            System.out.println("");
        }

        /*for(int i=0;i<((M*M)-M)/2;i++){
            System.out.println(point1Intersection[i]+"  "+point2Intersection[i]+"  "+point3Intersection[i]+"  "+point4Intersection[i]+"  ");
        }*/
    }

    public static void searchIntersection02(){//override
        point = new Point[((M*M)-M)/2];
        point1Intersection = new int[((M*M)-M)/2];
        point2Intersection = new int[((M*M)-M)/2];
        point3Intersection = new int[((M*M)-M)/2];
        point4Intersection = new int[((M*M)-M)/2];
		int k = 0;
		for(int i=0; i<M-1; i++){
			for(int j=i+1; j<M; j++){
                point[k] = search_intersection(i, j);
                if(point[k].x != 0 && point[k].y != 0){
                    point1Intersection[k] = b_p[i];
                    point2Intersection[k] = e_q[i];
                    point3Intersection[k] = b_p[j];
                    point4Intersection[k] = e_q[j];
                    countIntersection++;
                }
				k++;
			}
		}
    }

    public static double calculateDistance(double x1, double y1, double x2, double y2){
        double distance;
        distance = (x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2);
        distance = Math.sqrt(distance);
        return distance;
    }
}