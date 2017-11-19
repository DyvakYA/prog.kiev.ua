package lesson2.xml1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by User on 11/19/2017.
 */
public class XmlParser {

    public static final String ID = "id";
    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String PATH = "src/lesson2/xml1/1.xml";
    public static final String DATE = "date";
    public static final String DEPARTURE = "departure";
    public static final String HH_MM = "HH:mm";
    public static final String FINISH_TIME = "19:00";
    public static final String START_TIME = "15:00";

    void parse() {

        try {
            Collection trainList = new ArrayList();
            Element root = documentBuilder();
            System.out.println(root.getNodeName());
            System.out.println("---------------------------");
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (isCurrentTimeOfDeparture(element)) {
                        trainXmlBuilder(trainList, element);
                        System.out.println(trainList);
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | ParseException | SAXException e) {
            e.printStackTrace();
        }
    }

    private Element documentBuilder() throws ParserConfigurationException, SAXException, IOException {
        File xmlFile = new File(PATH);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        return document.getDocumentElement();
    }

    private boolean isCurrentTimeOfDeparture(Element element) throws ParseException {
        return longTime(element.getElementsByTagName(DEPARTURE).item(0)
                .getChildNodes().item(0).getNodeValue()) < longTime(FINISH_TIME)
                && longTime(element.getElementsByTagName(DEPARTURE).item(0)
                .getChildNodes().item(0).getNodeValue()) > longTime(START_TIME);
    }

    private void trainXmlBuilder(Collection trainList, Element element) throws ParseException {
        trainList.add(new Train.Builder()
                .setId(Integer.parseInt(element.getAttribute(ID)))
                .setFrom(element.getElementsByTagName(FROM).item(0)
                        .getChildNodes().item(0).getNodeValue())
                .setTo(element.getElementsByTagName(TO).item(0)
                        .getChildNodes().item(0).getNodeValue())
                .setDate(element.getElementsByTagName(DATE).item(0)
                        .getChildNodes().item(0).getNodeValue())
                .setDeparture(element.getElementsByTagName(DEPARTURE).item(0)
                        .getChildNodes().item(0).getNodeValue())
                .build());
    }


    public long longTime(String time) throws ParseException {
        DateFormat timeFormat = new SimpleDateFormat(HH_MM);
        Date tDuration = timeFormat.parse(time);
        long result = tDuration.getTime();
        return result;
    }
}
