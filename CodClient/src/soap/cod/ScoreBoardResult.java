
package soap.cod;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for scoreBoardResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="scoreBoardResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="loss" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wins" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "scoreBoardResult", propOrder = {
    "loss",
    "name",
    "wins"
})
public class ScoreBoardResult {

    protected int loss;
    protected String name;
    protected int wins;

    /**
     * Gets the value of the loss property.
     * 
     */
    public int getLoss() {
        return loss;
    }

    /**
     * Sets the value of the loss property.
     * 
     */
    public void setLoss(int value) {
        this.loss = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the wins property.
     * 
     */
    public int getWins() {
        return wins;
    }

    /**
     * Sets the value of the wins property.
     * 
     */
    public void setWins(int value) {
        this.wins = value;
    }

}
