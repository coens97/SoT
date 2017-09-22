package com.coen.Dto;

public class ScoreBoardResult {
    private String name;
    private int wins;
    private int loss;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public ScoreBoardResult(){

    }

    public ScoreBoardResult(String name, int wins, int loss) {
        this.name = name;
        this.wins = wins;
        this.loss = loss;
    }
}
