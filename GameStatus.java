public enum GameStatus {
    X(1),O(1),InProcess(0.5),Draw(0);
    private double score;

    GameStatus(double score) {
        this.score = score;
    }

    public void setScore(double score){
        this.score = score;
    }
    public double getScore(){
        return this.score;
    }
}
