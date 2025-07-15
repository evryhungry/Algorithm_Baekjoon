import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int max = -1, tot=0;
        for(int i=0; i<4; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            tot += b - a;
            if(max < tot){
                max = tot;
            }
        }
        System.out.println(max);
    }
}