import java.util.Scanner;
public class dijkstra {
    public static void main(String[] args) {
	
	Scanner sc = new Scanner(System.in);
	int nTown = sc.nextInt();
	int nRoute = sc.nextInt(); 
	int[][] map = new int[nTown][nTown]; 
	for (int i=0; i<nTown; i++)
	    for (int j=0; j<nTown; j++)
		map[i][j] = (i==j) ? 0 : -1;
	for (int i=0; i<nRoute; i++) {
	    int from = sc.nextInt();
	    int to = sc.nextInt();
	    int len = sc.nextInt();
	    map[from][to] = map[to][from] = len;
	}
	int src = sc.nextInt();
	int dst = sc.nextInt();
	int[] distance = new int[nTown]; 
	dijkstra(map,src,distance);
	if (distance[dst]==Integer.MAX_VALUE) { 
	    System.out.println("no route");
	} else {
	    System.out.println("distance="+distance[dst]);
	}
    }
    public static void dijkstra(int[][] map,int src,int[] distance) {
	int nTown = distance.length;
	boolean[] fixed = new boolean[nTown];
	for (int i=0; i<nTown; i++) { 
	    distance[i] = Integer.MAX_VALUE; 
	    fixed[i] = false;
	}
	distance[src] = 0;      
	while (true) {
	   
	    int marked = minIndex(distance,fixed);
	    if (marked < 0) return; 
	    if (distance[marked]==Integer.MAX_VALUE) return; 
	    fixed[marked] = true; 
	    for (int j=0; j<nTown; j++) {
		if (map[marked][j]>0 && !fixed[j]) { 
		    
		    int newDistance = distance[marked]+map[marked][j];
		   
		    if (newDistance < distance[j]) distance[j] = newDistance;
		}
	    }
	}
    }
    
    static int minIndex(int[] distance,boolean[] fixed) {
	int idx=0;
	for (; idx<fixed.length; idx++) 
	    if (!fixed[idx]) break;
	if (idx == fixed.length) return -1; 
	for (int i=idx+1; i<fixed.length; i++) 
	    if (!fixed[i] && distance[i]<distance[idx]) idx=i;
	return idx;
    }
}

/* 使えるかわからんけどダイクストラ法のアルゴリズムです。*/
