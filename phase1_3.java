public class phase1_3 extends phase1_2 {
    public static final double INFINITY = 10.0/0.0;
    protected static int countIntersection = 0;
    protected static double[][] Matrix;
    protected static int[] point1Intersection;
    protected static int[] point2Intersection;
    protected static int[] point3Intersection;
    protected static int[] point4Intersection;
    protected static int[] elementIntersection;
    protected static int WHITE = 0;
    protected static int GRAY = 1;
    protected static int BLACK = 2;
    protected static Point[] originalPoints;
    public static void main(String[] argv){
        input();
        searchIntersection02();
        initializeMinimumSpanningTree_Matrix();
        //matrixOutput();
        rundijkstraAlgorithm();
    }

    //methods
    public static void dijkstraAlgorithm(int start, int end, int loop){
        double[] d = new double[N+countIntersection];
        int[] color = new int[N+countIntersection];
        double minv;

        if(start>N+countIntersection || end>N+countIntersection){
            for(int i=0;i<loop;i++){
                System.out.println("NA");
            }
        } else { // the element is in normal range
            //initialize
            start -= 1;
            end -= 1;
            for(int i=0;i < N+countIntersection;i++){
                d[i] = INFINITY;
                color[i] = WHITE;
            }

            d[start] = 0;
            color[start] = GRAY;
            while(true){
                minv = INFINITY;
                int u = -1;
                for(int i=0; i < N+countIntersection; i++){
                    if(minv > d[i] && color[i] != BLACK){
                        u = i;
                        minv = d[i];
                    }
                }
                if(u == -1){
                    break;
                }
                color[u] = BLACK;
                for(int v=0; v < N+countIntersection; v++){
                    if(color[v] != BLACK && Matrix[u][v] != INFINITY){
                        if(d[v] > d[u]+Matrix[u][v]){
                            d[v] = d[u] + Matrix[u][v];
                            color[v] = GRAY;
                        }
                    }
                }
            }// end of while loop

            //output answer
            System.out.println(String.format("%.5f", d[end]));
        }
    }// end of dijkstraAlgorithm method
    public static void rundijkstraAlgorithm(){
        int start_num,end_num;
        for(int i=0; i<Q; i++){
            start_num=0;
            end_num=0;
            if(start[i].charAt(0) == 'C'){
                start_num = N + Integer.valueOf(start[i].substring(1));
            } else {
                start_num = Integer.valueOf(start[i]);
            }
            if(end[i].charAt(0) == 'C'){
                end_num = N + Integer.valueOf(end[i].substring(1));
            } else {
                end_num = Integer.valueOf(end[i]);
            }

            dijkstraAlgorithm(start_num, end_num, k[i]);
        }
    }// end of rundijkstraAlgorithm method

    public static void initializeMinimumSpanningTree_Matrix(){
        Matrix = new double[N+countIntersection][N+countIntersection];
        originalPoints = new Point[N+countIntersection];
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
        int k;
        int[] maybeSamePoint = new int[4];
        for(int i=0; i<countIntersection; i++){
            for(int j=i+1; j<countIntersection; j++){
                k=0;
                if(point1Intersection[elementIntersection[i]] == point1Intersection[elementIntersection[j]] || point1Intersection[elementIntersection[i]] == point2Intersection[elementIntersection[j]] || point1Intersection[elementIntersection[i]] == point3Intersection[elementIntersection[j]] || point1Intersection[elementIntersection[i]] == point4Intersection[elementIntersection[j]])maybeSamePoint[k++]=point1Intersection[elementIntersection[i]];
                if(point2Intersection[elementIntersection[i]] == point1Intersection[elementIntersection[j]] || point2Intersection[elementIntersection[i]] == point2Intersection[elementIntersection[j]] || point2Intersection[elementIntersection[i]] == point3Intersection[elementIntersection[j]] || point2Intersection[elementIntersection[i]] == point4Intersection[elementIntersection[j]])maybeSamePoint[k++]=point2Intersection[elementIntersection[i]];
                if(point3Intersection[elementIntersection[i]] == point1Intersection[elementIntersection[j]] || point3Intersection[elementIntersection[i]] == point2Intersection[elementIntersection[j]] || point3Intersection[elementIntersection[i]] == point3Intersection[elementIntersection[j]] || point3Intersection[elementIntersection[i]] == point4Intersection[elementIntersection[j]])maybeSamePoint[k++]=point3Intersection[elementIntersection[i]];
                if(point4Intersection[elementIntersection[i]] == point1Intersection[elementIntersection[j]] || point4Intersection[elementIntersection[i]] == point2Intersection[elementIntersection[j]] || point4Intersection[elementIntersection[i]] == point3Intersection[elementIntersection[j]] || point4Intersection[elementIntersection[i]] == point4Intersection[elementIntersection[j]])maybeSamePoint[k++]=point4Intersection[elementIntersection[i]];

                if(k >= 2){
                    if(checkSameLine(maybeSamePoint, k) == true){
                        Matrix[i+N][j+N] = calculateDistance(point[elementIntersection[i]].x, point[elementIntersection[i]].y, point[elementIntersection[j]].x, point[elementIntersection[j]].y);
                        Matrix[j+N][i+N] = Matrix[i+N][j+N];    
                    }
                }
            }
        }

        // initialize all Point data
        Point temp = new Point();
        for(int i=0; i<N+P; i++){
            originalPoints[i] = new Point();
            originalPoints[i].x = (double)x[i];
            originalPoints[i].y = (double)y[i];
        }
        int ele_poi = N;
        for(int i=0; i<point.length; i++){
            if(point[i].x!=0 && point[i].y!=0){
                originalPoints[ele_poi] = new Point();
                originalPoints[ele_poi] = point[i];
                ele_poi++;
            }
        }

        // if tilt of Point data is same others
        /*for(int i=0; i<N+countIntersection; i++){
            System.out.println(originalPoints[i].x+" "+originalPoints[i].y);
        }*/
        double tilt1, tilt2, tilt3;
        for(int i=0; i<M; i++){
            tilt1 = (double)(y[b_p[i]] - y[e_q[i]]) / (x[b_p[i]] - x[e_q[i]]);
            for(int j=0; j<N+countIntersection; j++){
                if(b_p[i]!=j && e_q[i]!=j){
                    tilt2 = (double)(originalPoints[j].y - y[b_p[i]]) / (originalPoints[j].x - x[b_p[i]]);

                    if(-EPS<=tilt1-tilt2 && tilt1-tilt2<=EPS && x[b_p[i]]<originalPoints[j].x && originalPoints[j].x<x[e_q[i]]){
                        Matrix[j][b_p[i]] = calculateDistance(originalPoints[j].x, originalPoints[j].y, x[b_p[i]], y[b_p[i]]);
                        Matrix[b_p[i]][j] = Matrix[j][b_p[i]];
                        Matrix[j][e_q[i]] = calculateDistance(originalPoints[j].x, originalPoints[j].y, x[e_q[i]], y[e_q[i]]);
                        Matrix[e_q[i]][j] = Matrix[j][e_q[i]];   
                        
                        for(int m=N; m<N+countIntersection; m++){
                            tilt3 = (double)(originalPoints[m].y - y[b_p[i]]) / (originalPoints[m].x - x[b_p[i]]);
                            if(-EPS<=tilt1-tilt3 && tilt1-tilt3<=EPS && x[b_p[i]]<originalPoints[m].x && originalPoints[m].x<x[e_q[i]]){
                                Matrix[m][j] = calculateDistance(originalPoints[m].x, originalPoints[m].y, originalPoints[j].x, originalPoints[j].y);
                                Matrix[j][m] = Matrix[m][j];
                            }
                        }
                    }
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
    public static boolean checkSameLine(int[] maybeSamePoint, int k){
        boolean isLine = false;
        for(int i=0; i<k; i++){
            for(int j=0; j<M; j++){
                if(b_p[j] == maybeSamePoint[i]){
                    for(int ii=0; ii<k; ii++){
                        if(e_q[j] == maybeSamePoint[ii])isLine = true;
                    }
                }
            }
        }
        return isLine;
    }

    public static void matrixOutput(){
        for(int i=0;i<N+countIntersection;i++){
            for(int j=0;j<N+countIntersection;j++){
                System.out.print(String.format("%.6f", Matrix[i][j])+"  ");
            }
            System.out.println("");
        }

        for(int i=0;i<((M*M)-M)/2;i++){
            System.out.println(point1Intersection[i]+"  "+point2Intersection[i]+"  "+point3Intersection[i]+"  "+point4Intersection[i]+"  ");
        }
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

    public static Point search_intersection(int i, int j){
        double matrix_a;    // matrix_a is |A|
        boolean exist_intersection = false;
        Point point1 = new Point();
        matrix_a = (double)(x[e_q[i]] - x[b_p[i]]) * (y[b_p[j]] - y[e_q[j]]) + (x[e_q[j]] - x[b_p[j]]) * (y[e_q[i]] - y[b_p[i]]);
        //step1
        if(matrix_a <= EPS  && -EPS <= matrix_a){
            exist_intersection = false;
        } else {
            exist_intersection = true;
            //step2 (calculate s and t)
            s = ((double)(y[b_p[j]] - y[e_q[j]]) * (x[b_p[j]] - x[b_p[i]]) + (x[e_q[j]] - x[b_p[j]]) * (y[b_p[j]] - y[b_p[i]])) / matrix_a;
            t = ((double)(y[b_p[i]] - y[e_q[i]]) * (x[b_p[j]] - x[b_p[j]]) + (x[e_q[i]] - x[b_p[i]]) * (y[b_p[j]] - y[b_p[i]])) / matrix_a;
        }
        if(exist_intersection == true){
            //step3
            if(0<=s && s<=1  && 0<=t && t<=1){
                exist_intersection = true;
                //step4
                x_intersection = (double)x[b_p[i]] + (double)(x[e_q[i]] - x[b_p[i]]) * s;   //x = x_P1 + (x_Q1 - x_P1)*s
                y_intersection = (double)y[b_p[i]] + (double)(y[e_q[i]] - y[b_p[i]]) * s;
                //System.out.println(x_intersection+" "+y_intersection);
            } else {
                exist_intersection = false;
            }
        }

        //search for contact point
        for(int k=0; k<N ;k++){
            if(x[k]==x_intersection && y[k]==y_intersection){
                exist_intersection = false;
                //System.out.println("交点と元の座標が被りました。"+x_intersection+" "+y_intersection);
            }
        }
        
        if(exist_intersection == true){
            point1.x = x_intersection;
            point1.y = y_intersection;
        } else {
            point1.x = 0;
            point1.y = 0;
        }
        return point1;
    }

}