public class TicTacToe {

    int BOARD_WITH = 3;
    Names[][] board = new Names[BOARD_WITH][BOARD_WITH];

    private void initialBoard(Names[][] board) {
        for(int i = 0; i < BOARD_WITH; i++){
            for (int j = 0; j < BOARD_WITH; j++) {
                board[i][j] = Names.EMPTY ;
            }
        }
    }
    public static void main(String[] args){
        TicTacToe game = new TicTacToe();
        int games = 1;
        game.initialBoard(game.board);
        Agent player1 = new Agent(Names.X);
        Agent player2 = new Agent(Names.O);

        player1.setInLearningMode(true);
        player2.setInLearningMode(true);
        player1.setRandomChance(0.3);
        player2.setRandomChance(0.3);
        player1.setVerbose(true);
        player2.setVerbose(true);
        //self-learning
        for(int i = 0; i < games; i++){
            GameStatus result = game.play(player1, player2);
            System.out.printf("Game result is: %s", result);
        }
        //play with trained model
        player2.setVerbose(true);
        player2.setRandomChance(0);
        Human human = new Human();
        //TODO: update to user can chose X or O later and play first or not.Currently play first as "X"
        human.setSign(Names.X);
        while (true){
            GameStatus gameRes = game.play(human, player2);
            if(gameRes == GameStatus.Draw){
                System.out.printf("The Game is Draw");
            }else
            System.out.printf("The game winner is: %s", gameRes);
        }
    }

    private GameStatus play(Player player1, Player player2) {
        int[] step;
        initialBoard(this.board);
        this.displayBoard(board);
        for(int i = 0; i < BOARD_WITH * BOARD_WITH; i++){
            if( i%2 == 0){
                player1.makeNextMove(this.board);
            }else {
                player2.makeNextMove(this.board);
            }
            this.displayBoard(board);
            GameStatus status = player1.getGameStatus(this.board);
            if(status != GameStatus.InProcess){
                return status;
            }
        }
        return GameStatus.Draw;
    }

    private void displayBoard(Names[][] board) {
        String temp = "";
        for(int row = 0; row < board.length; row++){
            if(row == 0) System.out.printf("\n%13s\n|", "-------------");
            else System.out.printf("\n%13s\n|", "|-----------|");
            for(int column = 0; column < board.length; column++){
                if(board[row][column] == Names.EMPTY){
                    temp = " ";
                }else temp = board[row][column].sign;
                System.out.printf("%3s|", temp);
            }
        }
        System.out.printf("\n%13s\n", "-------------");
    }


}
