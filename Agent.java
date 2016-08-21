import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Agent extends Player {
    private boolean learning;
    private double randomChance;
    private double alpha = 0.99;
    private static HashMap<String, Double> boardValueMap = new HashMap<String, Double>();
    private boolean verbose = false;

    public Agent(Names sign) {
        super.setSign(sign);
    }
    public void makeNextMove(Names[][] board) {
        double isRandom = Math.random();
        if( isRandom < this.randomChance)
            this.makeRandomMove(board);
        else this.madeGreedyMove(board);
    }

    private void madeGreedyMove(Names[][] board) {
        double maxValue = -100;
        double stepScore = 0;
        int[] step = new int[2];
        String boardStr = "";
        String[][] searchRes = new String[board.length][board.length];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j] == Names.EMPTY){
                    //get value for player X
                    board[i][j] = Names.X;
                    stepScore = this.getStepValue(board);

                    //get value for player O
                    board[i][j] = Names.O;
                    stepScore += this.getStepValue(board);
                    stepScore /= 2;

                    //add state after new move if no value in the state value map
                    board[i][j] = this.getSign();
                    appendBoardValMap(board, stepScore);

                    board[i][j] = Names.EMPTY;
                    searchRes[i][j] = Double.toString(stepScore);
                    if (stepScore > maxValue){
                        maxValue = stepScore;
                        step[0] = i;
                        step[1] = j;
                    }
                    if(this.learning){
                        this.updateValueMap(board, stepScore);
                    }
                }else searchRes[i][j] = board[i][j].sign;
            }
        }
        //make the move with maximum score
        int[] expStep = expStrategy(searchRes, maxValue);
        if(expStep == null){
            board[step[0]][step[1]] = this.getSign();
        }else board[expStep[0]][expStep[1]] = this.getSign();
        if(this.verbose) this.displaySearchResult(searchRes);
    }

    private int[] expStrategy(String[][] searchRes, double maxValue) {
        //Taking conner spot have more chance to win
        ArrayList<int[]> candidateCorners = new ArrayList<int[]>();
        String tempValue;
        for(int row : new int[]{0,searchRes.length - 1}){
            for(int column : new int[]{0, searchRes.length - 1}){
                tempValue = searchRes[row][column];
                if(tempValue.matches("[-+]?\\d*\\.?\\d+") && Double.parseDouble(tempValue) == maxValue){
                    candidateCorners.add(new int[]{row, column});
                }
            }
        }
        if(candidateCorners.size() > 0){
            return candidateCorners.get(new Random().nextInt(candidateCorners.size()));
        }
        return null;
    }

    private void appendBoardValMap(Names[][] board, double score) {
        String boardStr;
        boardStr = this.boardToString(board);
        if(!this.boardValueMap.containsKey(boardStr)){
            this.boardValueMap.put(boardStr, score);
        }
    }

    private void displaySearchResult(String[][] searchRes) {
        for(int row = 0; row < searchRes.length; row++){
            if( row == 0 )System.out.printf("\n%13s\n|", "-------------");
            else System.out.printf("\n%13s\n|","|-----------|");
            for(int column = 0; column < searchRes.length; column++){
                System.out.printf("%3s|", searchRes[row][column]);
            }
        }
        System.out.printf("\n%13s\n","-------------");
    }
    private void updateValueMap(Names[][] board, double stepScore) {
        String boardStr = this.boardToString(board);
        if(boardValueMap.containsKey(boardStr)){
            Double value = boardValueMap.get(boardStr);
            double newValue = value + this.alpha * (stepScore - value);
            boardValueMap.put(boardStr, newValue);
        }
    }

    private String boardToString(Names[][] board){
        String bStr = "";
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j] == Names.EMPTY){
                    bStr += "E";
                }else bStr += board[i][j];
            }
        }
        return bStr;
    }

    private double getStepValue(Names[][] potentialBoard) {
        //Taking both sides(losing game) into account
        GameStatus status = getGameStatus(potentialBoard);
        if(status == GameStatus.InProcess){
            return GameStatus.InProcess.getScore();
        }
        if (status == GameStatus.Draw){
            return GameStatus.Draw.getScore();
        }
        return status.getScore();
    }

    private void makeRandomMove(Names[][] board) {
        double score;
        ArrayList<int[]> candidateSteps = new ArrayList<int[]>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j] == Names.EMPTY){
                    candidateSteps.add(new int[]{i,j});
                }
            }
        }
        int[] randomStep = candidateSteps.get(new Random().nextInt(candidateSteps.size()));
        score = this.getStepValue(board);
        updateValueMap(board,score);
    }
    public void setInLearningMode(boolean learningMode) {
        this.learning = learningMode;
    }
    
    public void setRandomChance(double randomChance){
        this.randomChance = randomChance;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
}
