public abstract class Player {

    private Names sign;
    private double randomChance = 0.5;

    abstract void makeNextMove(Names[][] board);
    public Names getSign() {
        return sign;
    }

    public void setSign(Names sign) {
        this.sign = sign;
    }

    public GameStatus getGameStatus(Names[][] board) {
        for(int i = 0; i < board.length; i++){
            // row i has the same value
            if(board[i][0] != Names.EMPTY && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return GameStatus.valueOf(board[i][0].name());
            }
            // column has the same value
            if(board[0][i] != Names.EMPTY && board[0][i] == board[1][i] && board[0][i] == board[2][i]){
                return GameStatus.valueOf(board[0][i].name());
            }
        }
        // forward diagonal
        if(board[0][0] != Names.EMPTY && board[0][0] == board[1][1] && board[0][0] == board[2][2]){
            return GameStatus.valueOf(board[0][0].name());
        }
        // backward diagonal
        if(board[0][2] != Names.EMPTY && board[0][2] == board[1][1] && board[0][2] == board[2][0]){
            return GameStatus.valueOf(board[0][2].name());
        }

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j] == Names.EMPTY){
                    return GameStatus.InProcess;
                }
            }
        }
        return GameStatus.Draw;
    }
}
