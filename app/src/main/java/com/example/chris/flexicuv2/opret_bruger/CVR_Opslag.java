package com.example.chris.flexicuv2.opret_bruger;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class CVR_Opslag {
    private URL getString;
    private File xmlFile;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder db;

    private final String CVR_STRING = "cvr";
    private final String VSH_NAVN_STRING = "navn";
    private final String ADRESSE_STRING = "adresse";
    private final String POSTNR_STRING = "postnr";
    private final String BY_STRING = "city";


    /**
     * Klassen anvendes til at lave opslag gennem: https://cvrapi.dk/examples
     *  - Koden er også lånt fra hjemmesiden
     */
    public CVR_Opslag() {
        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            db = dbFactory.newDocumentBuilder();
            xmlFile = new File(System.getProperty("java.io.tmpdir"), "cvrapitemp.xml");

        } catch(ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Metoden henter søgeresultatet fra https://cvrapi.dk/
     * @param text : Søgekriteriet der søges på. Det kan være CVR, Firmanavn eller telefonnummer
     * @return Map med diverse nøgler og tilhørende værdier for søgningen
     */
    public Map<String, String> getResult(String text) {
        try {
            System.setProperty("Flexicu test1", "Flexicu V2");
            getString = new URL("http://cvrapi.dk/api?search=" +  text +  "&country=dk&format=xml");
            System.out.println(getString);
        } catch (MalformedURLException e) {
            System.out.println(111);
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        ReadableByteChannel rbc;
        try {
            fos = new FileOutputStream(xmlFile);
            System.out.println(222);
            rbc = Channels.newChannel(getString.openStream());
            System.out.println(rbc.isOpen()); //TODO OK
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            System.out.println(2222);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return parseXML();
    }

    private Map<String,String> parseXML() {
        Map<String, String> map = new HashMap<>();
        Document d = null;
        try {
            d = db.parse(xmlFile);
            System.out.println(333);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        d.getDocumentElement().normalize();

        Node n = d.getFirstChild(); //TODO print: INVALID_UA
        System.out.println(n/*.getFirstChild()*/.getTextContent() + " KFKFKFKFKFKF");

        if(n.getNodeType() == Node.ELEMENT_NODE) {

            Element eElement = (Element) n;
            try {
                System.out.println("Jeg finder elementerne");
                System.out.println(eElement.getElementsByTagName("vat").item(0)+ "Dette indeholder jeg");
                map.put("cvr", eElement.getElementsByTagName("vat").item(0).getTextContent());
                System.out.println(444);
                map.put(VSH_NAVN_STRING, eElement.getElementsByTagName("name").item(0).getTextContent());
                map.put(ADRESSE_STRING, eElement.getElementsByTagName("address").item(0).getTextContent());
                map.put(POSTNR_STRING, eElement.getElementsByTagName("zipcode").item(0).getTextContent());
                map.put(BY_STRING, eElement.getElementsByTagName("city").item(0).getTextContent());
                map.put("beskytet", eElement.getElementsByTagName("protected").item(0).getTextContent());
                map.put("startdato", eElement.getElementsByTagName("startdate").item(0).getTextContent());
                map.put("bkode", eElement.getElementsByTagName("industrycode").item(0).getTextContent());
                map.put("vkode", eElement.getElementsByTagName("companycode").item(0).getTextContent());
                map.put("vtekst", eElement.getElementsByTagName("companydesc").item(0).getTextContent());
                map.put("apiversion", eElement.getElementsByTagName("version").item(0).getTextContent());
                System.out.println(map.get(VSH_NAVN_STRING));
            } catch(NullPointerException e) {
                System.out.println("Jeg kunne ikke finde dem alligevel");
                map.put("error", "not found");
            }
        }

        return map;
    }

    public String getAdresseString() {
        return ADRESSE_STRING;
    }

    public String getVirksomhedsNavnString() {
        return VSH_NAVN_STRING;
    }

    public String getPostNrString() {
        return POSTNR_STRING;
    }

    public String getByString() {
        return BY_STRING;
    }
    public String getCVRString(){
        return CVR_STRING;
    }
}
