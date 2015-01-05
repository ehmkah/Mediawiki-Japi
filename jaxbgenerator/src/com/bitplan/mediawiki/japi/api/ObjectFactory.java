//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2015.01.05 um 04:52:15 PM CET 
//


package com.bitplan.mediawiki.japi.api;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bitplan.mediawiki.japi.api package. 
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

    private final static QName _ParamType_QNAME = new QName("", "type");
    private final static QName _ParametersParam_QNAME = new QName("", "param");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bitplan.mediawiki.japi.api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Api }
     * 
     */
    public Api createApi() {
        return new Api();
    }

    /**
     * Create an instance of {@link Paraminfo }
     * 
     */
    public Paraminfo createParaminfo() {
        return new Paraminfo();
    }

    /**
     * Create an instance of {@link Modules }
     * 
     */
    public Modules createModules() {
        return new Modules();
    }

    /**
     * Create an instance of {@link Module }
     * 
     */
    public Module createModule() {
        return new Module();
    }

    /**
     * Create an instance of {@link Helpurls }
     * 
     */
    public Helpurls createHelpurls() {
        return new Helpurls();
    }

    /**
     * Create an instance of {@link Parameters }
     * 
     */
    public Parameters createParameters() {
        return new Parameters();
    }

    /**
     * Create an instance of {@link Param }
     * 
     */
    public Param createParam() {
        return new Param();
    }

    /**
     * Create an instance of {@link Type }
     * 
     */
    public Type createType() {
        return new Type();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "type", scope = Param.class)
    public JAXBElement<Type> createParamType(Type value) {
        return new JAXBElement<Type>(_ParamType_QNAME, Type.class, Param.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Param }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "param", scope = Parameters.class)
    public JAXBElement<Param> createParametersParam(Param value) {
        return new JAXBElement<Param>(_ParametersParam_QNAME, Param.class, Parameters.class, value);
    }

}