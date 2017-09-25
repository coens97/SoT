package com.coen.Data;

import com.coen.Dto.ScoreBoardResult;
import com.coen.Dto.ScoreBoardResults;
import com.coen.Dto.UserEntity;

import java.util.LinkedList;
import java.util.Optional;

public class DataStore {
    private static DataStore ourInstance = new DataStore();

    public static DataStore getInstance() {
        return ourInstance;
    }

    private DataStore() {
    }

    private LinkedList<UserEntity> userEntities
            = new LinkedList<UserEntity>();

    public LinkedList<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(LinkedList<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public Optional<UserEntity> MaybeUser(String name, String password)
    {
        return userEntities.stream()
                .filter(x ->
                        x.getUsername().equals(name) && x.getPassword().equals(password))
                .findFirst();
    }

    public boolean ValidUser(String name, String password)
    {
        return userEntities.stream().anyMatch(x ->
                x.getUsername().equals(name) &&
                        x.getPassword().equals(password));
    }

    public boolean Register(String name, String password)
    {
        boolean userExist = userEntities.stream().anyMatch(x ->
                x.getUsername().equals(name));
        if (userExist)
        {
            return false;
        }
        else
        {
            UserEntity newUser = new UserEntity(name, password);
            userEntities.add(newUser);
            return true;
        }
    }

    public ScoreBoardResults GetScoreboard()
    {
        ScoreBoardResult[] results = DataStore.getInstance()
                .getUserEntities().stream()
                .map(x ->
                    new ScoreBoardResult(x.getUsername(), x.getWins(), x.getLoss()))
                .toArray(ScoreBoardResult[]::new);
        return new ScoreBoardResults(results);
    }
}
