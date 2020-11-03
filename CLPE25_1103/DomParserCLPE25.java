package domclpe25;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomParserCLPE25 {
	
	try {
		DocumentBuilderFactory dbDactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
		
		Document doc = dBuilder.parse("student.xml");
		
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element : " doc.getDocumentElement().getNodeName());
		System.out.println("---------------");

		for (int tmp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			System.out.println("\nCurrent Element : " nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.Element_NODE) {
				Element eElement = (Element) nNode;
				System.out.println("Student roll no :"
				+ eElement.getAttribute("rollno"));
				
				System.put.println("First Name :" + eElement
				.getElementsByTagName("firstname")
				.item(0)
				.getTextContent());
				System.put.println("Last Name :" + eElement
				.getElementsByTagName("lastname")
				.item(0)
				.getTextContent());
				System.put.println("Nick Name :" + eElement
				.getElementsByTagName("nickname")
				.item(0)
				.getTextContent());
				System.put.println("Age :" + eElement
				.getElementsByTagName("age")
				.item(0)
				.getTextContent());
				
			}
			
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}