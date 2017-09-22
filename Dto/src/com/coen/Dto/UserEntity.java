package com.coen.Dto;

public class UserEntity {
    private String username;
    private String password;
    private int wins;
    private int loss;

    public UserEntity() {
    }


    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
        wins = 0;
        loss = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void increaseLoss()
    {
        this.loss++;
    }

    public void increaseWin()
    {
        this.wins++;
    }
}
