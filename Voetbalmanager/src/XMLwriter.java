import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class XMLwriter {
	
	public XMLwriter(){
		
	}
	/**Voegt een waarde toe in de vorm van "<type>waarde</type>"
	 * 
	 * @param tag		Tag van element die gewijzigd moet worden(speler of team)
	 * @param naam		Naam van team of speler die gewijzigd moet worden
	 * @param type		eigenschap die gewijzigd/toegevoegd moet worden
	 * @param waarde	waarde van die gewijzigd/toegevoegd moet worden 
	 */
	public void updaten(String tag, String naam, String type, String waarde){
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(Driver.path);
		try {
			//open xml
			Document document = (Document) builder.build(xmlFile);
			//maak element van <divisie>
			Element divisieEl = document.getRootElement();
			
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
						}
				 }
			//	 System.out.println(element.getTextNormalize());
			  }
			 
			//wegschrivern naar xml file
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(document, new FileWriter(Driver.path));
			
			System.out.println("Toegevoegd/gewijzigd in "+"<"+tag+">"+naam+"<"+tag+">"+
			" :\n" + "<"+type+">" + waarde +"<"+type+">\n");
			
		 } catch (IOException io) {
			 System.out.println(io.getMessage());
		 } catch (JDOMException jdomex) {
			 System.out.println(jdomex.getMessage());
		 }
	}
}
