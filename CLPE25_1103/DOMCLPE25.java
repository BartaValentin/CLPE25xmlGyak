package domclpe25;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
 
public class DomParserDemo {
   public static void main(String[] args){
 
      try {    
         File inputFile = new File("szemelyek.xml");
         DocumentBuilderFactory dbFactory 
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :"
            + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("szemely");
         System.out.println("----------------------------");
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :"
               + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("Szemely id: "
                  + eElement.getAttribute("id"));
               System.out.println("Name : "
                  + eElement
                  .getElementsByTagName("nev")
                  .item(0)
                  .getTextContent());
               System.out.println("Age : "
               + eElement
                  .getElementsByTagName("kor")
                  .item(0)
                  .getTextContent());
               System.out.println("City : "
               + eElement
                  .getElementsByTagName("varos")
                  .item(0)
                  .getTextContent());
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}