package soap.cod;

import java.util.LinkedList;
import java.util.Optional;

public class MyCodSoap implements CodSoap {
    private LinkedList<UserEntity> userEntities
            = new LinkedList<UserEntity>();

    private boolean ValidUser(String name, String password)
    {
        return userEntities.stream().anyMatch(x ->
                x.getUsername() == name &&
                x.getPassword() == password);
    }


    @Override
    public StandardResult Login(String name, String password)
    {
        if (ValidUser(name, password))
        {
            return new StandardResult(true, "");
        }
        else
        {
            return new StandardResult(false, "Can't find username or password");
        }
    }

    @Override
    public StandardResult Register(String name, String password)
    {
        boolean userExist = userEntities.stream().anyMatch(x ->
            x.getUsername() == name);
        if (userExist)
        {
            return new StandardResult(false, "Username already exists");
        }
        else
        {
            UserEntity newUser = new UserEntity(name, password);
            userEntities.add(newUser);
            return new StandardResult(true, "");
        }
    }

    @Override
    public Boolean RollDice(String name, String password) {
        Optional<UserEntity> maybeuser = userEntities.stream()
                .filter(x ->
                    x.getUsername() == name && x.getPassword() == password)
                .findFirst();
        if (maybeuser.isPresent())
        {
            UserEntity user = maybeuser.get();
            boolean random = Math.random() < 0.5;
            if (random)
            {
                user.increaseLoss();
                return false;
            }
            else
            {
                user.increaseWin();
                return true;
            }
        }
        return false;
    }

    @Override
    public ScoreBoardResult GetScoreBoard() {
        return null;
    }
}
