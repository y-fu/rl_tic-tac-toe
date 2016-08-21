import java.util.Scanner;

public class Human extends Player{
    @Override
    void makeNextMove(Names[][] board) {
        System.out.print("\nPlease enter your step choice:");
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        String[] step = s.split(",");
        board[Integer.parseInt(step[0])][Integer.parseInt(step[1])] = this.getSign();
    }
}
