package createservicedocument;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;
import javafx.scene.control.Cell;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;


public class ShareMethods {

	public static void main(String[] args)
	{
		System.out.println(ShareMethods.chechType("adil abdullah ansari"));
	}
	
	
	 public static String convertDocumentToString(Document doc) {
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer;
	        try {
	            transformer = tf.newTransformer();
	            // below code to remove XML declaration
	            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	            StringWriter writer = new StringWriter();
	            transformer.transform(new DOMSource(doc), new StreamResult(writer));
	            String output = writer.getBuffer().toString();
	            return output;
	        } catch (TransformerException e) {
	            e.printStackTrace();
	        }
	        
	        return null;
	    }

	 public static Document convertStringToDocument(String xmlStr) {
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	        DocumentBuilder builder;  
	        try  
	        {  
	            builder = factory.newDocumentBuilder();  
	            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
	            return doc;
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
	        return null;
	    }
	
	 
	 public static String chechType(String input)
	 {
		 if(Pattern.matches("[0-9]*",input))
		 {
			 return "Integer";
		 }
		 else if(Pattern.matches("[+-]?([0-9]*[.])?[0-9]+",input))
		 {
			 return "Float";
		 }
		 else if(Pattern.matches("[A-Za-z ]*",input))
		 {
			 return "String";
		 }
		 else
		 {
			 return "Alphanumeric";
		 }
	 }
	 
	 
	 
	 
	 public static void ExcelWrite(String path,String worksheet,List<ServiceDesc> ls) throws FileNotFoundException, IOException
	 {
		 // workbook object
	        XSSFWorkbook workbook = new XSSFWorkbook();
	  
	        // spreadsheet object
	        XSSFSheet spreadsheet
	            = workbook.createSheet(" Student Data ");
	  
	        // creating a row object
	        XSSFRow row;
	  
	        // This data needs to be written (Object[])
	        Map<String, Object[]> serviceData
	            = new TreeMap<String, Object[]>();
	 //           serviceData.put( "1", new Object[] {
	  //                  "FIELD NAME", "SAMLPE VALUE", "LENGTH","TYPE","MANDATORY","DESCRIPTION" });
	    //        (String fieldName, String sampleValue, String length,
	   //     			String type, String desc, String isMand)
	           for(int j=0;j<ls.size();j++){
                    serviceData.put(String.valueOf(j), new Object[] {
	                    ls.get(j).getFieldName(),ls.get(j).getSampleValue(),ls.get(j).getLength(),
	                    ls.get(j).getType(),ls.get(j).getDesc(),ls.get(j).getIsMand()});
                   }
	            
	            Set<String> keyid = serviceData.keySet();
	            
	            int rowid = 0;
	      
	            // writing the data into the sheets...
	      
	            for (String key : keyid) {
	      
	                row = spreadsheet.createRow(rowid++);
	                Object[] objectArr = serviceData.get(key);
	                int cellid = 0;
	      
	                for (Object obj : objectArr) {
	                    XSSFCell cell = row.createCell(cellid++);
	                    cell.setCellValue((String)obj);
	                }
	            }
	      
	            // .xlsx is the format for Excel Sheets...
	            // writing the workbook into the file...
	            FileOutputStream out = new FileOutputStream(
	                new File(path));
	      
	            workbook.write(out);
	            out.close();
	 }
	 
         
          public static void WordDocument(String path,List<ServiceDesc> ls) throws FileNotFoundException, IOException
    {//Blank Document
      XWPFDocument document = new XWPFDocument();
        
      //Write the Document in file system
      FileOutputStream out = new FileOutputStream(new File(path));
        
      //create table
      XWPFTable table = document.createTable();
    //  List<XWPFTableRow> tableRowTwo=new ArrayList<XWPFTableRow>();
      XWPFTableRow[] tableRowTwo=new XWPFTableRow[100];
      
      //create first row
      XWPFTableRow tableRowOne = table.getRow(0);
      tableRowOne.getCell(0).setText("S.NO");
      tableRowOne.addNewTableCell().setText("FIELD NAME");
      tableRowOne.addNewTableCell().setText("SAMLPE VALUE");
      tableRowOne.addNewTableCell().setText("LENGTH");
      tableRowOne.addNewTableCell().setText("TYPE");
      tableRowOne.addNewTableCell().setText("DESCRIPTION");
      tableRowOne.addNewTableCell().setText("MANDATORY");
		
      //create second row
       for(int j=1;j<ls.size();j++){
   //   XWPFTableRow tableRowTwo = table.createRow();
      tableRowTwo[j] = table.createRow();     
      tableRowTwo[j].getCell(0).setText(String.valueOf(j+1));
      tableRowTwo[j].getCell(1).setText(ls.get(j).getFieldName());
      tableRowTwo[j].getCell(2).setText(ls.get(j).getSampleValue());
      tableRowTwo[j].getCell(3).setText(ls.get(j).getLength());
      tableRowTwo[j].getCell(4).setText(ls.get(j).getType());
      tableRowTwo[j].getCell(5).setText(ls.get(j).getDesc());
      tableRowTwo[j].getCell(6).setText(ls.get(j).getIsMand());
       }
     
	
      document.write(out);
      out.close();
      System.out.println("docx written successully");
   }
          
          
          
  public static void CreateJsonFile(String filePath,List<FormatDesc> lis) {
    try {
      FileWriter myWriter = new FileWriter(filePath);
     myWriter.write("{");
      for(FormatDesc fd:lis)
       {
        if(chechType(fd.getValue())=="Integer"||
           chechType(fd.getValue())=="Float"||
           chechType(fd.getValue())=="Double")
          {
           myWriter.write("\""+fd.getKey()+"\":"+fd.getValue()+",");
          }
        else
          {
           myWriter.write("\""+fd.getKey()+"\":\""+fd.getValue()+"\",");
          }
       }
       myWriter.write("}");
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
    
    /**
     *
     * @param filePath
     * @param text
     */
    public static void CreateXmlFile(String filePath,List<FormatDesc> lis) {
    try {
      FileWriter myWriter = new FileWriter(filePath);
       myWriter.write("<Parent>"); 
      for(FormatDesc fd:lis)
       { 
        myWriter.write("<"+fd.getKey()+">"+fd.getValue()+"</"+fd.getKey()+">");
       }
      myWriter.write("</Parent>");
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
  }
         
}
