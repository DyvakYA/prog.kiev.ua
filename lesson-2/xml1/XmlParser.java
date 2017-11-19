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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<Train> trainList = new ArrayList<>();

    void parse() {
        try {
            long l1 = longTime(START_TIME);
            long l2 = longTime(FINISH_TIME);
            Element root = documentBuilder();
            System.out.println(root.getNodeName());
            System.out.println("---------------------------");
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    trainList = trainXmlBuilder(trainList, element).stream()
                            .filter(e -> e.getDeparture() > l1)
                            .filter(e -> e.getDeparture() < l2)
                            .collect(Collectors.toList());
                }
            }
        } catch (ParserConfigurationException | IOException | ParseException | SAXException e) {
            e.printStackTrace();
        }
        System.out.println(trainList);
    }

    private Element documentBuilder() throws ParserConfigurationException, SAXException, IOException {
        File xmlFile = new File(PATH);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        return document.getDocumentElement();
    }

    private List<Train> trainXmlBuilder(List<Train> trainList, Element element) throws ParseException {
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
        return trainList;
    }


    public long longTime(String time) throws ParseException {
        DateFormat timeFormat = new SimpleDateFormat(HH_MM);
        Date tDuration = timeFormat.parse(time);
        long result = tDuration.getTime();
        return result;
    }
}
