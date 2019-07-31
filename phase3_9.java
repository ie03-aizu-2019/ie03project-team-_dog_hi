import java.util.Scanner;

public class phase3_9 extends phase2_8{
    private static int problemNum;
    public static void main(String[] args){
        input();

        switch (problemNum){
            case 1:
                phase1_1 p11 = new phase1_1();
                Point p1 = new Point();
                p1=p11.search_intersection(0, 1);
                p11.output(p1);
                break;
            case 2:
                phase1_2 p12 = new phase1_2();
                p12.point = new Point[((M*M)-M)/2];
                p12.searchIntersection02();
                p12.sort();
                p12.output();
                break;
            case 3:
                phase1_3 p13 = new phase1_3();
                p13.searchIntersection02();
                p13.initializeMinimumSpanningTree_Matrix();
                p13.rundijkstraAlgorithm();
                break;
            case 4:
                phase1_4 p14 = new phase1_4();
                p14.searchIntersection02();
                p14.initializeMinimumSpanningTree_Matrix();
                p14.rundijkstraAlgorithm();
                break;
            case 5:
                phase2_5 p25 = new phase2_5();
                p25.searchIntersection02();
                p25.initializeMinimumSpanningTree_Matrix();
                p25.setShavedMatrix();
                p25.runyens_algorithm();
                break;
            case 6:
                phase2_6 p26 = new phase2_6();
                p26.searchIntersection02();
                p26.initializeMinimumSpanningTree_Matrix();
                p26.setShavedMatrix();
                p26.runyens_algorithm();
                break;
            case 7:
                phase2_7 p27 = new phase2_7();
                p27.calculate();
                p27.get_minimum_number();
                p27.get_new_road();
                break;
            case 8:
                phase2_8 p28 = new phase2_8();
                try(Scanner sc = new Scanner(System.in)){
                    p28.V = N;
                    int E;
                    E = M;
                    parent=new int[p28.V];
                    prenum=new int[p28.V];
                    lowest=new int[p28.V];
                    visited=new boolean[p28.V];
                    p28.G=new boolean[p28.V][p28.V];
                    for(int i=0; i<E; i++) {
                        s = b_p[i];
                        t = e_q[i];
                        p28.G[(int)s][(int)t]=G[(int)t][(int)s]=true;
                    }
                    p28.art_point();
                     
                }        
                break;
            default:
                System.out.println("存在する小課題の番号を入力してください。");
        }
    }

    public static void input(){
        Scanner scan = new Scanner(System.in);
        System.out.println("何番目の小課題を実行しますか？");
        problemNum = scan.nextInt();
        System.out.println("小課題"+problemNum+"のデータを入力してください。");

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


}