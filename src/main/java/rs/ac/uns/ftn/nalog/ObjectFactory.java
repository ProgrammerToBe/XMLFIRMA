//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.06 at 11:32:38 AM CEST 
//


package rs.ac.uns.ftn.nalog;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.nalog package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.nalog
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NalogZaPrenos }
     * 
     */
    public NalogZaPrenos createNalogZaPrenos() {
        return new NalogZaPrenos();
    }

    /**
     * Create an instance of {@link NalogZaPrenos.PodaciOPlacanju }
     * 
     */
    public NalogZaPrenos.PodaciOPlacanju createNalogZaPrenosPodaciOPlacanju() {
        return new NalogZaPrenos.PodaciOPlacanju();
    }

    /**
     * Create an instance of {@link TFinansijskiPodaci }
     * 
     */
    public TFinansijskiPodaci createTFinansijskiPodaci() {
        return new TFinansijskiPodaci();
    }

}