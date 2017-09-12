package soap.cod;

public class MyCodSoap implements CodSoap {
    @Override
    public StandardResult Login(String name, String password) {
        return null;
    }

    @Override
    public StandardResult Register(String name, String password) {
        return null;
    }

    @Override
    public Boolean RollDice(String hash) {
        return null;
    }

    @Override
    public ScoreBoardResult GetScoreBoard(String hash) {
        return null;
    }
}
