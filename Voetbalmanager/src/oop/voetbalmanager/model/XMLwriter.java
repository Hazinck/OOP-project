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
	private Element divisieElement;
	private Document document;
	
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
			this.document = document;
			//maak element van <divisie>
			Element divisieEl = document.getRootElement();
			divisieElement = divisieEl;
			
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
				 //als naam van <tag> element overeenkomt dan:
				 if(element.getChildText("naam").equals(naam)){

						//als element bestaat dan updaten
					 	if(element.getChild(type) != null){
							element.getChild(type).setText(waarde);
						}
						//anders maak nieuwe element
						else{
							 Element elNieuw = new Element(type).setText(waarde);
							 element.addContent(elNieuw);
							 break;
						}
				 }				
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
			this.document = document;
			//maak element van <divisie>
			Element divisieEl = document.getRootElement();
			divisieElement = divisieEl;
			
			boolean bestaat = false;
			Element parent = null;
			boolean spelerBestaat = false;
			ElementFilter filterSp=new org.jdom2.filter.ElementFilter("speler");
			for(Element element: document.getDescendants(filterSp)){
				if(element.getChildText("naam").equals(childNaam)){
					 spelerBestaat = true;
					 break;
				 }
			}
			ElementFilter filter=new org.jdom2.filter.ElementFilter("team");
			for(Element element: document.getDescendants(filter)){
				 if(parentTag.equals("divisie")){
				 		plaats = "0";
				 		parent = divisieEl;
				 		
				 }else if(element.getChildText("naam").equals(parentNaam)){// 
				 		parent = element;
				 		plaats = parent.getAttributeValue("id");
				 }
				 
				 if(element.getChildText("naam").equals(childNaam) || spelerBestaat){
					 bestaat = true;
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
	/**
	 * @return the divisieElement
	 */
	public Element getDivisieElement() {
		return divisieElement;
	}
	/**
	 * @return the document
	 */
	public Document getDocument() {
		return document;
	}
	
	
}
