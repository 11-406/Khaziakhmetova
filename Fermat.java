import java.util.Random;
import  java.util.Scanner;
public class Fermat{

    public static int getPow(int p, int x){
        int res = 1;
        while(p>0){
            if (p%2 !=0){
                res = res * x;
                x = x * x;
                p = p/2;
            }
            else{
                x = x * x;
                p = p/2;
            }
        }
        return res;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int p = sc.nextInt();
        Random random = new Random();
        int rnd = random.nextInt(p);
        int k = 0;
        for (int i = 0; i < 5; i++){
            if ((getPow(rnd, p-1) - 1) % p == 0){
                k+=1;
            }
        }
        if (k == 5){
            System.out.println("Число простое");
        }

    }
}