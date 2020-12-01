package hu.domparse.Clpe25;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.Scanner;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DOMModifyClpe25 {

	public static String ReadId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nid:");
		String id = sc.nextLine();
		return id;
	}
	
	public static void CreateXML(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("src/hu/domparse/Clpe25/updated.xml"));
        transformer.transform(source, result);
        System.out.println("output file elkészült");
	}
	
	public static void UpdatePlayer(Document doc) throws TransformerException {

		System.out.println("\nMelyik játékost szeretné módosítani?\n");

		DOMReadClpe25.ReadPlayer(doc);

		String id = ReadId();

		Scanner sc = new Scanner(System.in);
		System.out.print("Jatékos neve: ");
		String name = sc.nextLine();
		System.out.print("Jatékos beceneve: ");
		String nickname = sc.nextLine();
		System.out.print("Jatékos csapata: ");
		String team = sc.nextLine();
		System.out.print("Bajnokság: ");
		String league = sc.nextLine();
		System.out.print("Orszag: ");
		String country = sc.nextLine();
		
		UpdatePlayerById(doc, id, name, nickname, team, league, country);
	}

	public static void UpdatePlayerById(Document doc, String id, String name, String nickname, String team, String league, String country) throws TransformerException {

		NodeList nList = doc.getElementsByTagName("player");
		
		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String playerid = element.getAttribute("id");

				if (playerid.equals(id)) {

					String sponsorid = element.getAttribute("sponsorid");
					String matchid = element.getAttribute("matchid");
					String positionid = element.getAttribute("positionid");

					Node node1 = element.getElementsByTagName("name").item(0);
					node1.setTextContent(name);

					Node node2 = element.getElementsByTagName("nickname").item(0);
					node2.setTextContent(nickname);
					
					Node node3 = element.getElementsByTagName("team").item(0);
					node3.setTextContent(team);
					
					Node node4 = element.getElementsByTagName("country").item(0);
					node4.setTextContent(country);
					
					Node node5 = element.getElementsByTagName("league").item(0);
					node5.setTextContent(league);
					
					System.out.println("Jatekos Id:" + playerid + "\tSzponzor id:" + sponsorid + "\tMérközés id:"
							+ matchid + "\tPozício Id:" + positionid + "\tNev: " + node1.getTextContent() + "\tBecenev: " + node2.getTextContent()
							+ "\tCsapat: " + node3.getTextContent() + "\tOrszag:" + node4.getTextContent() + "\tLiga: " + node5.getTextContent());

					System.out.println("\nSikeres Modosítas\n");
					
				}
			}
		}
		
		CreateXML(doc);
	}
	
	public static void UpdateSponsor(Document doc) throws TransformerException {
	
		System.out.println("\nMelyik szponzort szeretné módosítani?\n");

		DOMReadClpe25.ReadSponsor(doc);

		String id = ReadId();

		Scanner sc = new Scanner(System.in);
		System.out.print("Név: ");
		String name = sc.nextLine();
		System.out.print("Ország: ");
		String country = sc.nextLine();
		System.out.print("Bevétel: ");
		String income = sc.nextLine();

		UpdateSponsorById(doc, id, name, country, income);
		
	}

	public static void UpdateSponsorById(Document doc, String id, String name, String country, String income) throws TransformerException {

		NodeList nList = doc.getElementsByTagName("sponsor");
		
		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String sponsorid = element.getAttribute("id");

				if (sponsorid.equals(id)) {

					Node node1 = element.getElementsByTagName("name").item(0);
					node1.setTextContent(name);

					Node node2 = element.getElementsByTagName("country").item(0);
					node2.setTextContent(country);
					
					Node node3 = element.getElementsByTagName("income").item(0);
					node3.setTextContent(income);
					
					System.out.println("Szponzor id:" + sponsorid + "\tNev: " + node1.getTextContent() + "\tOrszag: " + node2.getTextContent()
							+ "\tBevetel: " + node3.getTextContent());
					
					System.out.println("\nSikeres Modosítas\n");
					
				}
			}
		}
		
		CreateXML(doc);
	}
	
	public static void UpdateMatch(Document doc) throws TransformerException {

		System.out.println("\nMelyik mérközést szeretné módosítani?\n");

		DOMReadClpe25.ReadMatch(doc);
		
		String id = ReadId();

		Scanner sc = new Scanner(System.in);
		System.out.print("Helyszín: ");
		String location = sc.nextLine();
		System.out.print("Végeredmény: ");
		String fulltime = sc.nextLine();
		System.out.print("Félidõ: ");
		String halftime = sc.nextLine();
		System.out.print("Bíró: ");
		String referee = sc.nextLine();
		System.out.print("Év: ");
		String year = sc.nextLine();
		System.out.print("Hónap: ");
		String month = sc.nextLine();
		System.out.print("Nap: ");
		String day = sc.nextLine();
	
		UpdateMatchById(doc, id, location, fulltime, halftime, referee, year, month, day);
		
	}

	public static void UpdateMatchById(Document doc, String id, String location, String fulltime, String halftime, String referee, String year, String month, String day) throws TransformerException {

		NodeList nList = doc.getElementsByTagName("match");
		
		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			NodeList cList = nList.item(i).getChildNodes();
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String sponsorid = element.getAttribute("id");

				if (sponsorid.equals(id)) {

					Node node1 = element.getElementsByTagName("location").item(0);
					node1.setTextContent(location);

					Node node2 = element.getElementsByTagName("fulltime").item(0);
					node2.setTextContent(fulltime);
					
					Node node3 = element.getElementsByTagName("halftime").item(0);
					node3.setTextContent(halftime);
					
					Node node4 = element.getElementsByTagName("referee").item(0);
					node4.setTextContent(referee);
										
					for (int j = 0; j < cList.getLength(); j++) {

						Node cnode1 = element.getElementsByTagName("year").item(0);
						cnode1.setTextContent(year);

						Node cnode2 = element.getElementsByTagName("month").item(0);
						cnode2.setTextContent(month);

						Node cnode3 = element.getElementsByTagName("day").item(0);
						cnode3.setTextContent(day);

					}

					System.out.println("Merkozes id:" + id + "\tHelyszin: " + node1.getTextContent() + "\tVegeredmeny: " + node2.getTextContent()
							+ "\tFelido: " + node3.getTextContent() + "\tBiro: " + node4.getTextContent() + "\tDatum: " + year + "." + month + "."
							+ day);
										
					System.out.println("\nSikeres Modosítas\n");
					
				}
			}
		}
		
		CreateXML(doc);
	}
	
	public static void UpdatePosition(Document doc) throws TransformerException {

		System.out.println("\nMelyik pozíciót szeretné módosítani?\n");

		DOMReadClpe25.ReadPosition(doc);

		String id = ReadId();

		Scanner sc = new Scanner(System.in);
		System.out.print("Név: ");
		String name = sc.nextLine();
	
		UpdatePositionById(doc, id, name);
		
	}

	public static void UpdatePositionById(Document doc, String id, String name) throws TransformerException {

		NodeList nList = doc.getElementsByTagName("position");
		
		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			NodeList cList = nList.item(i).getChildNodes();
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String positionid = element.getAttribute("id");

				if (positionid.equals(id)) {

					Node node1 = element.getElementsByTagName("name").item(0);
					node1.setTextContent(name);
				
					System.out.println("Pozicio id:" + positionid + "\tNev: " + name);
					System.out.println("\nSikeres Modosítas\n");
					
				}
								
			}
		}
		
		CreateXML(doc);
	}
	
	public static void UpdateTeam(Document doc) throws TransformerException {

		System.out.println("\nMelyik csapatot szeretné módosítani?\n");

		DOMReadClpe25.ReadTeam(doc);
		
		String id = ReadId();

		Scanner sc = new Scanner(System.in);
		System.out.print("Név: ");
		String name = sc.nextLine();
		System.out.print("Ország: ");
		String country = sc.nextLine();
		System.out.print("Város: ");
		String city = sc.nextLine();
		
		UpdateTeamById(doc, id, name, country, city);
		
	}

	public static void UpdateTeamById(Document doc, String id, String name, String country, String city) throws TransformerException {

		NodeList nList = doc.getElementsByTagName("footballteam");
		
		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			NodeList cList = nList.item(i).getChildNodes();
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String teamid = element.getAttribute("id");

				if (teamid.equals(id)) {

					Node node1 = element.getElementsByTagName("name").item(0);
					node1.setTextContent(name);

										
					for (int j = 0; j < cList.getLength(); j++) {

						Node cnode1 = element.getElementsByTagName("country").item(0);
						cnode1.setTextContent(country);

						Node cnode2 = element.getElementsByTagName("city").item(0);
						cnode2.setTextContent(city);

					}

					System.out.println("Csapat id: " + teamid + "\tNev: " + node1.getTextContent() + "\tOrszag: " + country + "\tVaros: " + city);
					
					System.out.println("\nSikeres Modosítas\n");
					
				}
			}
		}
		
		CreateXML(doc);
	}
	
	public static void UpdateContract(Document doc) throws TransformerException {

		System.out.println("\nMelyik szerzõdést szeretné módosítani?\n");

		DOMReadClpe25.ReadContract(doc);
		
		String id = ReadId();

		Scanner sc = new Scanner(System.in);
		System.out.print("Leírás: ");
		String description = sc.nextLine();
		System.out.print("Év: ");
		String year = sc.nextLine();
		System.out.print("Hónap: ");
		String month = sc.nextLine();
		System.out.print("Nap: ");
		String day = sc.nextLine();
		
		UpdateContractById(doc, id, description, year, month, day);
	}
	
	public static void UpdateContractById(Document doc, String id, String description, String year, String month, String day) throws TransformerException {

		NodeList nList = doc.getElementsByTagName("contract");
				
		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			NodeList cList = nList.item(i).getChildNodes();
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				String contractid = element.getAttribute("id");

				if (contractid.equals(id)) {

					Node node1 = element.getElementsByTagName("description").item(0);
					node1.setTextContent(description);
				
					for (int j = 0; j < cList.getLength(); j++) {

						Node cnode1 = element.getElementsByTagName("year").item(0);
						cnode1.setTextContent(year);

						Node cnode2 = element.getElementsByTagName("month").item(0);
						cnode2.setTextContent(month);

						Node cnode3 = element.getElementsByTagName("day").item(0);
						cnode3.setTextContent(day);
						
					}
					
					System.out.println("Szerzodes id:\t" + contractid + "\tDatum:\t" + year + "." + month + "." + day + "\tLeiras:\t" + node1.getTextContent() );
					System.out.println("\nSikeres Modosítas\n");
					
				}
								
			}
		}
		
		CreateXML(doc);
	}

}