package soap.cod;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface CodSoap {
    @WebMethod
    StandardResult Login(String name, String password);

    @WebMethod
    StandardResult Register(String name, String password);

    @WebMethod
    Boolean RollDice(String name, String password);

    @WebMethod
    ScoreBoardResult[] GetScoreBoard();
}
