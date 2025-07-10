import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());
        int index = Integer.parseInt(br.readLine());

        if (n >= (long) 6){
            System.out.println("Love is open door\n");
            return;
        }

        for (int i = 1; i < n; i++) {
            if( index == 0){
                System.out.println(++index);
            }
            else{
                System.out.println(--index);
            }
        }
    }
}