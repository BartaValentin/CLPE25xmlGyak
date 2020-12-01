package hu.domparse.Clpe25;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DOMReadClpe25 {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {

			/*XML beolvasás*/
	       try {
	   		File xmlFile = new File("src/hu/domparse/Clpe25/XMLCLPE25.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();		
			System.out.println("Barta Valentin CLPE25 XML beadando");
			Action(doc);	   
	       }catch (ParserConfigurationException pce) {
	           pce.printStackTrace();
	       } catch (TransformerException tfe) {
	        tfe.printStackTrace();
	       } catch (IOException ioe) {
	        ioe.printStackTrace();
	       } catch (SAXException sae) {
	        sae.printStackTrace();
	       }
	}

	public static void Action(Document doc) throws TransformerException {
		System.out.println("\nOlvasni vagy modosítani szeretne?");
		System.out.println("1 olvasas");
		System.out.println("2 modositas");
		int action = ReadCategory();

		/*1 kategória olvasás 2 kategória módosítás*/
		switch (action) {
		case 1:
			Read(doc);
			break;
		case 2:
			Update(doc);
			break;
		default:
			Action(doc);
			break;
		}

	}

	public static int ReadCategory() {
		Scanner scan = new Scanner(System.in);
		/*Sorszám alapján eldöntöm, hogy hogy melyiket szeretném módosítani*/
		System.out.print("\nAdja meg a sorszamot:");
		int readCategory = scan.nextInt();
		return readCategory;
	}
	
	public static void Update(Document doc) throws TransformerException {
		System.out.println("\nXML Módosítas\n");
		System.out.println("Kerem adja meg mit szeretne modositani");
		System.out.println("1 jatekos\n2 szponzor\n3 merkozes\n4 pozicio\n5 csapat\n6 szerzodes");
		int category = 0;
		category = ReadCategory();
		ShowElementUpdates(category, doc);
	}
	
	public static void Read(Document doc) {
		System.out.println("\nXML Olvasas\n");
		System.out.println("Kerem adja meg mit szeretne olvasni");
		System.out.println("1 jatekos\n2 szponzor\n3 merkozés\n4 pozicio\n5 csapat\n6 szerzodes");
		int category = 0;
		category = ReadCategory();
		ShowCategoryElements(category, doc);
	}

	public static void ShowCategoryElements(int category, Document doc) {
		switch (category) {
		case 1:
			ReadPlayer(doc);
			break;
		case 2:
			ReadSponsor(doc);
			break;
		case 3:
			ReadMatch(doc);
			break;
		case 4:
			ReadPosition(doc);
			break;
		case 5:
			ReadTeam(doc);
			break;
		case 6:
			ReadContract(doc);
			break;
		default:
			int newCategory = ReadCategory();
			ShowCategoryElements(newCategory, doc);
			break;
		}
	}

	public static void ShowElementUpdates(int category, Document doc) throws TransformerException {
		switch (category) {
		case 1:
			DOMModifyClpe25.UpdatePlayer(doc);
			break;
		case 2:
			DOMModifyClpe25.UpdateSponsor(doc);
			break;
		case 3:
			DOMModifyClpe25.UpdateMatch(doc);
			break;
		case 4:
			DOMModifyClpe25.UpdatePosition(doc);
			break;
		case 5:
			DOMModifyClpe25.UpdateTeam(doc);
			break;
		case 6:
			DOMModifyClpe25.UpdateContract(doc);
			break;
		default:
			int newCategory = ReadCategory();
			ShowElementUpdates(newCategory, doc);
			break;
		}
	}
	
	public static void ReadPosition(Document doc) {

		NodeList nList = doc.getElementsByTagName("position");

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String positionid = element.getAttribute("id");

				Node node1 = element.getElementsByTagName("name").item(0);
				String name = node1.getTextContent();

				System.out.println("Pozicio id:" + positionid + "\tNev: " + name);
			}
		}

	}

	public static void ReadTeam(Document doc) {

		NodeList nList = doc.getElementsByTagName("footballteam");

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			NodeList cList = nList.item(i).getChildNodes();

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String teamid = element.getAttribute("id");

				Node node1 = element.getElementsByTagName("name").item(0);
				String name = node1.getTextContent();
				
				String country = "";
				String city = "";

				for (int j = 0; j < cList.getLength(); j++) {

					Node cnode1 = element.getElementsByTagName("country").item(0);
					country = cnode1.getTextContent();
					
					Node cnode2 = element.getElementsByTagName("city").item(0);
					city = cnode2.getTextContent();

				}
				
				System.out.println("Csapat id: " + teamid + "\tNev: " + name + "\tOrszag: " + country + "\tVaros: " + city);
			}
			
		}
	}

	public static void ReadMatch(Document doc) {

		NodeList nList = doc.getElementsByTagName("match");

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			NodeList cList = nList.item(i).getChildNodes();

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String matchid = element.getAttribute("id");

				Node node1 = element.getElementsByTagName("location").item(0);
				String location = node1.getTextContent();

				Node node2 = element.getElementsByTagName("fulltime").item(0);
				String fulltime = node2.getTextContent();

				Node node3 = element.getElementsByTagName("halftime").item(0);
				String halftime = node3.getTextContent();

				Node node4 = element.getElementsByTagName("referee").item(0);
				String referee = node4.getTextContent();

				String year = "";
				String month = "";
				String day = "";

				for (int j = 0; j < cList.getLength(); j++) {

					Node cnode1 = element.getElementsByTagName("year").item(0);
					year = cnode1.getTextContent();

					Node cnode2 = element.getElementsByTagName("month").item(0);
					month = cnode2.getTextContent();

					Node cnode3 = element.getElementsByTagName("day").item(0);
					day = cnode3.getTextContent();

				}

				System.out.println("Merkozes id:" + matchid + "\tHelyszin: " + location + "\tVegeredmeny: " + fulltime
						+ "\tFelido: " + halftime + "\tBiro: " + referee + "\tDatum: " + year + "." + month + "."
						+ day);
			}
		}
	}

	public static void ReadSponsor(Document doc) {

		NodeList nList = doc.getElementsByTagName("sponsor");

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String sponsorid = element.getAttribute("id");

				Node node1 = element.getElementsByTagName("name").item(0);
				String name = node1.getTextContent();

				Node node2 = element.getElementsByTagName("country").item(0);
				String country = node2.getTextContent();

				Node node3 = element.getElementsByTagName("income").item(0);
				String income = node3.getTextContent();

				System.out.println("Szponzor id:" + sponsorid + "\tNev: " + name + "\tOrszag: " + country
						+ "\tBevetel: " + income);
			}
		}
	}

	public static void ReadPlayer(Document doc) {

		NodeList nList = doc.getElementsByTagName("player");

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String playerid = element.getAttribute("id");
				String sponsorid = element.getAttribute("sponsorid");
				String matchid = element.getAttribute("matchid");
				String positionid = element.getAttribute("positionid");

				Node node1 = element.getElementsByTagName("name").item(0);
				String name = node1.getTextContent();

				Node node2 = element.getElementsByTagName("nickname").item(0);
				String nickname = node2.getTextContent();

				Node node3 = element.getElementsByTagName("team").item(0);
				String team = node3.getTextContent();

				Node node4 = element.getElementsByTagName("country").item(0);
				String country = node4.getTextContent();

				Node node5 = element.getElementsByTagName("league").item(0);
				String league = node5.getTextContent();

				System.out.println("Jatekos Id:" + playerid + "\tSzponzor id:" + sponsorid + "\tMerkozes id:" + matchid
						+ "\tPozicio Id:" + positionid + "\tNev: " + name + "\tBecenev: " + nickname + "\tCsapat: "
						+ team + "\tOrszag:" + country + "\tLiga: " + league);

			}
		}

	}

	public static void ReadContract(Document doc) {
		
		NodeList nList = doc.getElementsByTagName("contract");

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			NodeList cList = nList.item(i).getChildNodes();

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String contractid = element.getAttribute("id");

				Node node1 = element.getElementsByTagName("description").item(0);
				String description = node1.getTextContent();

				String year = "";
				String month = "";
				String day = "";

				for (int j = 0; j < cList.getLength(); j++) {

					Node cnode1 = element.getElementsByTagName("year").item(0);
					year = cnode1.getTextContent();

					Node cnode2 = element.getElementsByTagName("month").item(0);
					month = cnode2.getTextContent();

					Node cnode3 = element.getElementsByTagName("day").item(0);
					day = cnode3.getTextContent();

				}

				System.out.println("Szerzodes id:\t" + contractid + "\tDatum:\t" + year + "." + month + "." + day + "\tLeiras:\t" + description );
			}
		}
		
	}
	
}
