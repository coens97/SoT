
package soap.cod;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap.cod package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Register_QNAME = new QName("http://cod.soap/", "Register");
    private final static QName _RollDice_QNAME = new QName("http://cod.soap/", "RollDice");
    private final static QName _GetScoreBoard_QNAME = new QName("http://cod.soap/", "GetScoreBoard");
    private final static QName _RegisterResponse_QNAME = new QName("http://cod.soap/", "RegisterResponse");
    private final static QName _LoginResponse_QNAME = new QName("http://cod.soap/", "LoginResponse");
    private final static QName _RollDiceResponse_QNAME = new QName("http://cod.soap/", "RollDiceResponse");
    private final static QName _GetScoreBoardResponse_QNAME = new QName("http://cod.soap/", "GetScoreBoardResponse");
    private final static QName _Login_QNAME = new QName("http://cod.soap/", "Login");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap.cod
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegisterResponse }
     * 
     */
    public RegisterResponse createRegisterResponse() {
        return new RegisterResponse();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link Register }
     * 
     */
    public Register createRegister() {
        return new Register();
    }

    /**
     * Create an instance of {@link RollDice }
     * 
     */
    public RollDice createRollDice() {
        return new RollDice();
    }

    /**
     * Create an instance of {@link GetScoreBoard }
     * 
     */
    public GetScoreBoard createGetScoreBoard() {
        return new GetScoreBoard();
    }

    /**
     * Create an instance of {@link GetScoreBoardResponse }
     * 
     */
    public GetScoreBoardResponse createGetScoreBoardResponse() {
        return new GetScoreBoardResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link RollDiceResponse }
     * 
     */
    public RollDiceResponse createRollDiceResponse() {
        return new RollDiceResponse();
    }

    /**
     * Create an instance of {@link StandardResult }
     * 
     */
    public StandardResult createStandardResult() {
        return new StandardResult();
    }

    /**
     * Create an instance of {@link ScoreBoardResult }
     * 
     */
    public ScoreBoardResult createScoreBoardResult() {
        return new ScoreBoardResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Register }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cod.soap/", name = "Register")
    public JAXBElement<Register> createRegister(Register value) {
        return new JAXBElement<Register>(_Register_QNAME, Register.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RollDice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cod.soap/", name = "RollDice")
    public JAXBElement<RollDice> createRollDice(RollDice value) {
        return new JAXBElement<RollDice>(_RollDice_QNAME, RollDice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetScoreBoard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cod.soap/", name = "GetScoreBoard")
    public JAXBElement<GetScoreBoard> createGetScoreBoard(GetScoreBoard value) {
        return new JAXBElement<GetScoreBoard>(_GetScoreBoard_QNAME, GetScoreBoard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cod.soap/", name = "RegisterResponse")
    public JAXBElement<RegisterResponse> createRegisterResponse(RegisterResponse value) {
        return new JAXBElement<RegisterResponse>(_RegisterResponse_QNAME, RegisterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cod.soap/", name = "LoginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RollDiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cod.soap/", name = "RollDiceResponse")
    public JAXBElement<RollDiceResponse> createRollDiceResponse(RollDiceResponse value) {
        return new JAXBElement<RollDiceResponse>(_RollDiceResponse_QNAME, RollDiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetScoreBoardResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cod.soap/", name = "GetScoreBoardResponse")
    public JAXBElement<GetScoreBoardResponse> createGetScoreBoardResponse(GetScoreBoardResponse value) {
        return new JAXBElement<GetScoreBoardResponse>(_GetScoreBoardResponse_QNAME, GetScoreBoardResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cod.soap/", name = "Login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

}
