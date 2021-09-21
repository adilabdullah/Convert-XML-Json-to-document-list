package createservicedocument;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


//import org.json.JSONObject;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class ServiceDocs {
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
	    String resp="";
              String data="";
String filePath="C:\\AdulRauf\\info1.txt";
String destPath="C:\\AdulRauf\\";
            List<ServiceDesc> lis=new ArrayList<ServiceDesc>();
		 try {
      File myObj = new File(filePath);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
         data =data+myReader.nextLine(); 
      }
      myReader.close();
      if(data.charAt(0)=='<')
      {
	      File inputFile = new File(filePath);
	      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	
                 lis.add(new ServiceDesc("FIELD NAME", "SAMLPE VALUE", "LENGTH","TYPE","DESCRIPTION","MANDATORY"));
                 NodeList nList = doc.getElementsByTagName("*");
	         for (int i=1; i<nList.getLength(); i++) 
	         {
	             // Get element
	             Element element = (Element)nList.item(i);
	     //    System.out.println(element.getNodeName()+"   "+element.getTextContent()+"  "+element.getTextContent().length());
        //         System.out.println(element.getNodeName());             

                     lis.add(new ServiceDesc(element.getNodeName(),element.getTextContent(),String.valueOf(element.getTextContent().length()),
                     ShareMethods.chechType(element.getTextContent()),"For input "+element.getNodeName(),"Y")); 

                 }
	         ShareMethods.ExcelWrite(destPath+"info1.xlsx","XML Documet",lis);
	         ShareMethods.WordDocument(destPath+"info1.docx", lis);
                 System.out.println("Success");
      }
      else if(data.charAt(0)=='{')
      {
       try{
        JSONObject jso = new JSONObject(data);
          Iterator<String> ks=jso.keys();
       lis.add(new ServiceDesc("FIELD NAME", "SAMLPE VALUE", "LENGTH","TYPE","DESCRIPTION","MANDATORY"));
  
        while(ks.hasNext())
        {
         String field=ks.next(); 
     //    System.out.println(field+"  "+String.valueOf(jso.get(field)));
       lis.add(new ServiceDesc(field,String.valueOf(jso.get(field)),String.valueOf(jso.get(field).toString().length()),
       ShareMethods.chechType(jso.get(field).toString()),"For input "+field,"Y")); 

        }
       ShareMethods.ExcelWrite(destPath+"info1.xlsx","XML Documet",lis);
       ShareMethods.WordDocument(destPath+"info1.docx", lis);
       System.out.println("Success");
       }
       catch(Exception ee)
       {
        System.out.println(ee.getMessage());
       }
      }
      else
      {
       System.out.println("Invalid format file");
      }
    
          } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	  }
	
	
	
}
