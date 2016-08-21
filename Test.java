import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ibm on 2016/8/19.
 */
public class Test {
    public static void main(String[] args){
        /*int[][] trial = new int[2][2];
        for(int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++){
                trial[i][j] = i * 3 + j;
            }
        }

        System.out.print(trial);*/
        /*for (Names n : Names.values()){
            System.out.printf("%s |", n.sign);
        }*/
        String text = "-1";
        System.out.print(text.matches("[-+]?\\d*\\.?\\d+"));

        ArrayList<Integer> testList = new ArrayList<Integer>();
        System.out.print("\n");
        int index = new Random().nextInt(2);
        System.out.print(testList.size());

        /*System.out.print(GameStatus.X.toString());
        GameStatus status = GameStatus.X;
        if(status.toString() == Names.X.sign){
            System.out.print("to string is working");
        }else
        {
            System.out.print("\nto string is not working");
        }*/
        /*for(int i = 0; i < 10; i++){
            System.out.printf("\n%d", new Random().nextInt(10));
        }*/
    }
}
