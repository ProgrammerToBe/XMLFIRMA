//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.14 at 12:57:44 AM CEST 
//


package korenski.soap.fakture_model;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the korenski.soap.fakture_model package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: korenski.soap.fakture_model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Faktura }
     * 
     */
    public Faktura createFaktura() {
        return new Faktura();
    }

    /**
     * Create an instance of {@link StavkaFakture }
     * 
     */
    public StavkaFakture createStavkaFakture() {
        return new StavkaFakture();
    }

    /**
     * Create an instance of {@link TFirma }
     * 
     */
    public TFirma createTFirma() {
        return new TFirma();
    }

    /**
     * Create an instance of {@link Faktura.Stavke }
     * 
     */
    public Faktura.Stavke createFakturaStavke() {
        return new Faktura.Stavke();
    }

}