public class phase1_4 extends phase1_3{
    protected static int[] via, route;
    protected static int element_route;
    public static void main(String[] args){
        input();
        searchIntersection02();
        initializeMinimumSpanningTree_Matrix();
        rundijkstraAlgorithm();
    }

    public static void dijkstraAlgorithm(int start, int end, int loop){
        double[] d = new double[N+countIntersection];
        int[] color = new int[N+countIntersection];
        double minv;
        via = new int[N+countIntersection];
        route = new int[N+countIntersection];

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
                via[i] = -1;
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
                        if(d[v] >= d[u]+Matrix[u][v]){
                            d[v] = d[u] + Matrix[u][v];
                            color[v] = GRAY;
                            via[v] = u;
                        }
                    }
                }
            }// end of while loop

            //output answer
            System.out.println(String.format("%.5f", d[end]));
/*            for(int i=0; i<N+countIntersection; i++){
                System.out.print(via[i]+" ");
            }
            System.out.println();*/

            element_route=0;
            for(int i=end; i!=start; i=via[i]){
                route[element_route] = (i+1);
                element_route++;
                //System.out.print(i+1+"  ");
            }//System.out.println();
            route[element_route] = (start+1);
            element_route++;
            outputReverseRoute();
        }
    }// end of dijkstraAlgorithm method
    public static void outputReverseRoute(){
        for(int i=element_route-1; i>=0 ; i--){
            if(route[i] - M > 1){
                System.out.print("C"+(route[i]-M-1)+" ");
            } else {
                System.out.print(route[i]+" ");
            }
        }
        System.out.println();
    }

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



}