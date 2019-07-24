import java.util.*;

public class phase2_5 extends phase1_4{
  private static double[][] shavedMatrix;
  protected static PriorityQueue pq = new PriorityQueue();
  protected static int xxx, yyy, zzz, sper_node, locate;
  protected static Queue<String> sper_root;
  protected static String[] array_A, array_B;
  protected static double[] cost_path;
  protected static double add=0;

  public static void main(String[] args){
    input();
    searchIntersection02();
    initializeMinimumSpanningTree_Matrix();
    setShavedMatrix();
    //matrixOutput();
    runyens_algorithm();
  }

  public static void runyens_algorithm(){
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

      array_A = new String[50];
      array_B = new String[50];
      //cost_path = new double[k[i]];
      xxx = 0;
      yyy = 0;
      yens_algorithm(start_num, end_num, k[i]);
      outputresult_cost();
    }
  }

  public static void yens_algorithm(int start, int end, int num){
    String tmp, ttmp, t;
    for(int i=0; i<num; i++){
      locate = 0;
      sper_root = new ArrayDeque<>();
      if(i==0){ //第一最短経路
        sper_node = start-1;
        //System.out.println("sper_node is "+sper_node);
        array_A[xxx] = dijkstraAlgorithm(sper_node, end-1);
        //System.out.println("1st shortest_path: "+ array_A[xxx]);
        //System.out.println(Character.getNumericValue(array_A[xxx].charAt(0))-1);
        //System.out.println(Character.getNumericValue(array_A[xxx].charAt(1))-1);
        shavedMatrix[Character.getNumericValue(array_A[xxx].charAt(0))-1][Character.getNumericValue(array_A[xxx].charAt(1))-1] = INFINITY;
        shavedMatrix[Character.getNumericValue(array_A[xxx].charAt(1))-1][Character.getNumericValue(array_A[xxx].charAt(0))-1] = INFINITY;


        while(true){
          t = dijkstraAlgorithm(start-1, end-1);
          if(t == "INFINITY"){
            setShavedMatrix();
             break;
          }
          array_B[yyy] = t;
          //System.out.println(array_B[yyy]+" をB["+yyy+"]に入れる");
          shavedMatrix[Character.getNumericValue(array_B[yyy].charAt(0))-1][Character.getNumericValue(array_B[yyy].charAt(1))-1] = INFINITY;
          shavedMatrix[Character.getNumericValue(array_B[yyy].charAt(1))-1][Character.getNumericValue(array_B[yyy].charAt(0))-1] = INFINITY;
          yyy++;
        }

      } else { //第二最短経路以降
        for(int j=0; j<array_A[xxx].length(); j++){
          //System.out.println("i = "+ i +", j = "+j);
          if(j!=0){
            sper_node = Character.getNumericValue(array_A[xxx].charAt(locate))-1;
            //System.out.println("sper_node is "+sper_node+" locate = "+locate);
            if(sper_node==end-1){
              //System.out.println("nodeがendまで到達したのでBの中の最短経路を選びます");
              break;
            }
            sper_root.add(String.valueOf(array_A[xxx].charAt(locate-1)));
            copy_to_array_B(yyy);
            //System.out.println(Character.getNumericValue(array_A[xxx].charAt(locate))-1);
            //System.out.println(Character.getNumericValue(array_A[xxx].charAt(locate+1))-1);
            shavedMatrix[Character.getNumericValue(array_A[xxx].charAt(locate))-1][Character.getNumericValue(array_A[xxx].charAt(locate+1))-1] = INFINITY;
            shavedMatrix[Character.getNumericValue(array_A[xxx].charAt(locate+1))-1][Character.getNumericValue(array_A[xxx].charAt(locate))-1] = INFINITY;
            array_B[yyy] += dijkstraAlgorithm(sper_node, end-1);
            ttmp = array_B[yyy];
            array_B[yyy] = null;
            if(ttmp.equals(INFINITY)){
              break;
            }
            if(isSame(ttmp)!=1){
              locate++;
              array_B[yyy] = ttmp;
              //System.out.println(array_B[yyy]+" をB["+yyy+"]に入れる");
              yyy++;
            }else {
              //System.out.println(ttmp + " 同じものがありました");

              array_B[yyy] = null;
            //locate++;
            shavedMatrix[Character.getNumericValue(ttmp.charAt(locate))-1][Character.getNumericValue(ttmp.charAt(locate+1))-1] = INFINITY;
            shavedMatrix[Character.getNumericValue(ttmp.charAt(locate+1))-1][Character.getNumericValue(ttmp.charAt(locate))-1] = INFINITY;
            copy_to_array_B(yyy);

            //System.out.println(dijkstraAlgorithm(sper_node, end-1));
            array_B[yyy] += dijkstraAlgorithm(sper_node, end-1);
            //ttmp = array_B[yyy];
            //System.out.println(array_B[yyy]+" をB["+yyy+"]に入れる");
            locate++;
            yyy++;
              }
          }
          else {

            sper_node = Character.getNumericValue(array_A[xxx].charAt(locate)-1);
            //System.out.println("sper_node is "+sper_node+" xxx = "+xxx);
            tmp = dijkstraAlgorithm(sper_node, end-1);
            if(isSame(tmp)!=1){ //同じルートがarray_A,Bに存在しなかったら追加する。
              locate++;
              array_B[yyy] = tmp;
              //System.out.println(array_B[yyy]+" をB["+yyy+"]に入れる");
              //System.out.println(Character.getNumericValue(array_B[yyy].charAt(yyy))-1);
              //System.out.println(Character.getNumericValue(array_B[yyy].charAt(yyy+1))-1);
              shavedMatrix[Character.getNumericValue(array_B[yyy].charAt(yyy))-1][Character.getNumericValue(array_B[yyy].charAt(yyy+1))-1] = INFINITY;
              shavedMatrix[Character.getNumericValue(array_B[yyy].charAt(yyy+1))-1][Character.getNumericValue(array_B[yyy].charAt(yyy))-1] = INFINITY;
              yyy++;
            }
            else{
              //System.out.println(tmp + " 同じものがありました");
            locate++;
            shavedMatrix[Character.getNumericValue(tmp.charAt(locate-1))-1][Character.getNumericValue(tmp.charAt(locate))-1] = INFINITY;
            shavedMatrix[Character.getNumericValue(tmp.charAt(locate))-1][Character.getNumericValue(tmp.charAt(locate-1))-1] = INFINITY;
          }

          }
        }
        search_shortest_path(); //k最短経路をarray_Bから選ぶ
        //outputresult();
        setShavedMatrix();
      }
    }
  }

  public static String dijkstraAlgorithm(int start, int end){
    double[] d = new double[N+countIntersection];
    int[] color = new int[N+countIntersection];
    double minv;
    via = new int[N+countIntersection];
    route = new int[N+countIntersection];

    //initialize
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
        if(color[v] != BLACK && shavedMatrix[u][v] != INFINITY){
          if(d[v] >= d[u]+shavedMatrix[u][v]){
            d[v] = d[u] + shavedMatrix[u][v];
            color[v] = GRAY;
            via[v] = u;
          }
        }
      }
    }// end of while loop

    //output answer

    //System.out.println(String.format("%.5f", d[end]));

    if(d[end]==INFINITY){
      return "INFINITY";
    }
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

  return outputReverseRoute2();
}// end of dijkstraAlgorithm method

public static String outputReverseRoute2(){
  String s=String.valueOf(route[element_route-1]);

  for(int i=element_route-2; i>=0 ; i--){
    if(route[i] - M > 1){
      //System.out.print("C"+(route[i]-M-1)+" ");
      s += String.valueOf(route[i]);
    } else {
      //System.out.print(route[i]+" ");
      s += String.valueOf(route[i]);
    }
  }
  return s;
}

public static void search_shortest_path(){
  double min = INFINITY;
  double sum;
  int flag;
  xxx++;
  //System.out.println(array_B[5]);
  //System.out.println(array_B[0].length()-1);
  for(int i=0; array_B[i]!=null; i++){
    sum = 0;
    flag = 0;
    for(int k=0; array_A[k]!=null; k++){
      if(array_A[k].equals(array_B[i])){
        flag = 1;
      }
    }
    int s = array_B[i].length()-1;
    String ss = String.valueOf(array_B[i].charAt(s));
    if(ss.equals("Y")){
      continue;
    }

    if(array_B[i].charAt(0)==array_B[i].charAt(2)){
      continue;
    }

    if(flag != 1){
      //System.out.println("i is "+i);
      for(int j=0; j<array_B[i].length()-1; j++){
        //System.out.println(Character.getNumericValue(array_B[i].charAt(j)-1));
        //System.out.println(Character.getNumericValue(array_B[i].charAt(j+1)-1));

        sum += Matrix[Character.getNumericValue(array_B[i].charAt(j)-1)][Character.getNumericValue(array_B[i].charAt(j+1)-1)];
      }
      //System.out.println("sum is "+ sum);
      if(min > sum && sum != 0){
        min = sum;
        array_A[xxx] = array_B[i];
      }
    }
  }


  //cost_path[zzz] = min;
  //zzz++;
  //System.out.println("Aに "+array_A[xxx]+ "を入れます。");
  //System.out.println(cost_path[zzz-1]);

}

public static void outputresult(){
  System.out.println("result: ");
  //System.out.println("Array_A");
  for(int i=0; array_A[i]!=null; i++){
    //System.out.println(String.format("%.5f",cost_path[i]));
    System.out.println(array_A[i]);
  }
  /*System.out.println("Array_B");
  for(int i=0; array_B[i]!=null; i++){
    //System.out.println(String.format("%.5f",cost_path[i]));
    System.out.println(array_B[i]);
  }*/
}

  public static int isSame(String tmp){
    for(int i=0; array_A[i]!=null; i++){
      if(tmp.equals(array_A[i])){
        //System.out.println("同じものが存在しましたA");
        return 1; //同じルートが存在
      }
    }
    for(int i=0; array_B[i]!=null; i++){
      if(tmp.equals(array_B[i])){
        //System.out.println("同じものが存在しましたB");
        return 1; //同じルートが存在
      }
    }
    //System.out.println("同じものは存在しませんでした");
    return -1;
  }

public static void copy_to_array_B(int x){
  //System.out.println(sper_root+" "+x);
  //System.out.println(sper_root.size());
  array_B[x] = sper_root.poll();
  //System.out.println(sper_root.size());
  for(int i=0; i<sper_root.size(); i++){
    array_B[x] += sper_root.poll();
  }
  for(int i=0; i<array_B[x].length(); i++){
    sper_root.add(String.valueOf(array_B[x].charAt(i)));
  }


}

public static void outputresult_cost(){
  double sum;

  for(int i=0; array_A[i]!=null; i++ ){
    sum = 0;
    for(int j=0; j<array_A[i].length()-1; j++){
      sum += Matrix[Character.getNumericValue(array_A[i].charAt(j)-1)][Character.getNumericValue(array_A[i].charAt(j+1)-1)];
  }
  System.out.println(String.format("%.5f", sum));
//phase2_6
  /*for(int k=0; k<array_A[i].length(); k++){
    if(Character.getNumericValue(array_A[i].charAt(k)) > N){
      System.out.print("C"+(Character.getNumericValue(array_A[i].charAt(k))-N)+" ");
    }
    else {
      System.out.print(Character.getNumericValue(array_A[i].charAt(k))+" ");
    }
  }
  System.out.println();*/
}

}











public static void setShavedMatrix(){
  shavedMatrix = new double[N+countIntersection][N+countIntersection];
  for(int i=0; i<N+countIntersection; i++){
    for(int j=0; j<N+countIntersection; j++){
      shavedMatrix[i][j] = Matrix[i][j];
    }
  }

  // shave inefficient path
  for(int i=0; i<N+countIntersection; i++){
    for(int j=0; j<N+countIntersection; j++){
      for(int k=0; k<N+countIntersection; k++){
        double difference = Matrix[i][j] - Matrix[i][k] - Matrix[k][j];
        if(difference<EPS && -EPS<difference){//Matrix[i][j] == Matrix[i][k]+Matrix[k][j]
          shavedMatrix[i][j] = INFINITY;
          shavedMatrix[j][i] = INFINITY;
        }
      }
    }
  }
} // end of setShaveMatrix method

//Override
public static void matrixOutput(){
  // before shaving
  System.out.println("Before shaving");
  for(int i=0;i<N+countIntersection;i++){
    for(int j=0;j<N+countIntersection;j++){
      System.out.print(String.format("%.6f", Matrix[i][j])+"  ");
    }
    System.out.println("");
  }

  // after shaving
  System.out.println("\nAfter shaving");
  for(int i=0;i<N+countIntersection;i++){
    for(int j=0;j<N+countIntersection;j++){
      System.out.print(String.format("%.6f", shavedMatrix[i][j])+"  ");
    }
    System.out.println("");
  }
}

}
