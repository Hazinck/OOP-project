package oop.voetbalmanager.model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class XMLwriter {
	private	SAXBuilder builder = new SAXBuilder();
	private File xmlFile;
	
	public XMLwriter(String infile){
		xmlFile=new File(infile);
	}
	/**Voegt een waarde toe in de vorm van "<type>waarde</type>"
	 * 
	 * @param tag		Tag van element die gewijzigd moet worden(speler of team)
	 * @param naam		Naam van team of speler die gewijzigd moet worden
	 * @param type		eigenschap die gewijzigd/toegevoegd moet worden
	 * @param waarde	waarde van die gewijzigd/toegevoegd moet worden 
	 */
	public void updaten(String tag, String naam, String type, String waarde){

		try {
			//open xml
			Document document = (Document) builder.build(xmlFile);
			//maak element van <divisie>
			Element divisieEl = document.getRootElement();
			
			if(tag.equals("divisie")){
				//als element bestaat dan updaten
			 	if(divisieEl.getChild(type) != null){
					divisieEl.getChild(type).setText(waarde);
				}
				//anders maak nieuwe element
				else{
				Element elNieuw = new Element(type).setText(waarde);
				divisieEl.addContent(elNieuw);
				}
			}else{
			
			ElementFilter filter=new org.jdom2.filter.ElementFilter(tag);
			
			//zoeken naar <tag> elementen
			 for(Element element: divisieEl.getDescendants(filter)){
			//	 System.out.println(element);
				 //als naam van <tag> element overeenkomt dan:
			//	 System.out.println(element.toString() + " " + tag);
// System.out.println(element.getChildText("naam"));//element.getParentElement().getChildren(tag).size());
				// System.out.println(element.getParentElement().getChildren().toString() + " == " + naam + " filter: " + filter.toString() );
				 if(element.getChildText("naam").equals(naam)){
					/* 	//als er geen waarde is ingevoer maak nieuwe element
					 	if(waarde.equals("")){
					 		
					 	}
						//als element bestaat dan updaten
					 	else*/if(element.getChild(type) != null){
							element.getChild(type).setText(waarde);
						}
						//anders maak nieuwe element
						else{
							 Element elNieuw = new Element(type).setText(waarde);
				//			 System.out.println(elNieuw);
							 element.addContent(elNieuw);
							 break;
						}
				 }				
			//	 System.out.println(element.getTextNormalize());
			  }
			}
			//wegschrivern naar xml file
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(document, new FileWriter(xmlFile));
			
			System.out.println("Toegevoegd/gewijzigd in "+"<"+tag+">"+naam+"</"+tag+">"+
			" :\n" + "<"+type+">" + waarde +"</"+type+">\n");
			
		 } catch (IOException io) {
			 System.out.println(io.getMessage());
		 } catch (JDOMException jdomex) {
			 System.out.println(jdomex.getMessage());
		 }
	}
	/**Nieuwe element toevoegen
	 * 
	 * @param parentTag Tag van element waarin je nieuwe element wil creeren
	 * @param parentNaam Naam van element waarin je nieuwe element wil creeren
	 * @param childTag	tag van nieuwe element
	 * @param childNaam	naam van nieuwe element
	 */
	public void add(String parentTag, String parentNaam, String childTag, String childNaam){
		String plaats = "";
		try {
			//open xml
			Document document = (Document) builder.build(xmlFile);
			//maak element van <divisie>
			Element divisieEl = document.getRootElement();

			boolean bestaat = false;
			Element parent = null;
			boolean spelerBestaat = false;//element.getChild("speler")!=null && element.getChild("speler").getChildText("naam").equals(childNaam);
			ElementFilter filterSp=new org.jdom2.filter.ElementFilter("speler");
			for(Element element: document.getDescendants(filterSp)){
				if(element.getChildText("naam").equals(childNaam)){
					 spelerBestaat = true;
				//	 System.out.println(element.getChildText("naam") + " == "+childNaam+": "+bestaat);
					 break;
				 }
			}
			ElementFilter filter=new org.jdom2.filter.ElementFilter("team");
			for(Element element: document.getDescendants(filter)){
			//	 System.out.println(element.getChildText("naam") + " " + childNaam + " " + childTag);
				 if(parentTag.equals("divisie")){
				 		plaats = "0";
				 		parent = divisieEl;
				 		
				 }else if(element.getChildText("naam").equals(parentNaam)){// 
				 		parent = element;
				 		plaats = parent.getAttributeValue("id");
				 }
			//	 System.out.println(element.getChild("speler")+ " " + element.getChild("speler").getChildText("naam")+ " == " + childNaam);
				 
				 if(element.getChildText("naam").equals(childNaam) || spelerBestaat){
					 bestaat = true;
				//	 System.out.println(element.getChildText("naam") + " == "+childNaam+": "+bestaat);
					 break;
				 }
			 }
			if(bestaat == false){
				if(parent == null && parentNaam.equals("opstellingen")){
					parent = divisieEl.getChild("opstellingen");
				}
				String id = plaats + Integer.toString(parent.getChildren(childTag).size() + 1);

				 Element childNieuw = new Element(childTag);
				 childNieuw.setAttribute("id", id);

				 Element naam = new Element("naam").setText(childNaam);
				 childNieuw.addContent(naam);
				 
				 parent.addContent(childNieuw);
				 
				//wegschrivern naar xml file
				XMLOutputter xmlOutput = new XMLOutputter();
				xmlOutput.setFormat(Format.getPrettyFormat());
				xmlOutput.output(document, new FileWriter(xmlFile));
					
				 System.out.println("Toegevoegd " + "<"+childTag+">" + childNaam +"</"+childTag+">" + 
						 				" in " + parentNaam);
			}else{
				System.out.println("<"+childTag+">" + childNaam +"</"+childTag+">" + 
		 				" in " + parentNaam + " BESTAAT AL");
			}
		}catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
			
		
		 
	}
	
	
}
