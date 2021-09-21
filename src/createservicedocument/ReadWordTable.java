/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createservicedocument;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author pc
 */
public class ReadWordTable {
  public static void main(String args[]) throws IOException 
    {
                List<FormatDesc> lis=new ArrayList<FormatDesc>();

          String fileName = "C:\\AdulRauf\\info1.docx";
        try{ 
            XWPFDocument doc = new XWPFDocument(
                Files.newInputStream(Paths.get(fileName)));

            /*XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
            String docText = xwpfWordExtractor.getText();
            System.out.println(docText);*/

            Iterator<IBodyElement> docElementsIterator = doc.getBodyElementsIterator();

            //Iterate through the list and check for table element type
            while (docElementsIterator.hasNext()) {
                IBodyElement docElement = docElementsIterator.next();
                if ("TABLE".equalsIgnoreCase(docElement.getElementType().name())) {
                    //Get List of table and iterate it
                    List<XWPFTable> xwpfTableList = docElement.getBody().getTables();
                    for (XWPFTable xwpfTable : xwpfTableList) {
                  //      System.out.println("Total Rows : " + xwpfTable.getNumberOfRows());
                      /*  for (int i = 1; i < xwpfTable.getRows().size(); i++) {
                            for (int j = 0; j < xwpfTable.getRow(i).getTableCells().size(); j++) {
                                System.out.println(xwpfTable.getRow(i).getCell(j).getText());
                            }
                        }  */
                 /*       System.out.println("<Parent>");
                        for (int i = 1; i < xwpfTable.getRows().size(); i++) {
                          System.out.println("<"+xwpfTable.getRow(i).getCell(0).getText()+">"+xwpfTable.getRow(i).getCell(1).getText()+"</"+xwpfTable.getRow(i).getCell(0).getText()+">");
                        }
                        System.out.println("</Parent>");  */
                        ////////////////////////////////////
                //         System.out.println("{");
                      //   System.out.println(xwpfTable.getRows().size());
                         for (int i = 1; i < xwpfTable.getRows().size(); i++) {
                           lis.add(new FormatDesc(xwpfTable.getRow(i).getCell(0).getText(),xwpfTable.getRow(i).getCell(1).getText()));
                /*            if(ShareMethods.chechType(xwpfTable.getRow(i).getCell(1).getText())=="Integer"||
                                  ShareMethods.chechType(xwpfTable.getRow(i).getCell(1).getText())=="Float"||
                                  ShareMethods.chechType(xwpfTable.getRow(i).getCell(1).getText())=="Double")
                          {
                           
                             System.out.println("\""+xwpfTable.getRow(i).getCell(0).getText()+"\":"+xwpfTable.getRow(i).getCell(1).getText()+",");  
                           }
                          else
                          {
                          
                             System.out.println("\""+xwpfTable.getRow(i).getCell(0).getText()+"\":\""+xwpfTable.getRow(i).getCell(1).getText()+"\",");
                           }   */
                       
                         }
                        
                    //    System.out.println("}");
                       }
                    }
                }
            ShareMethods.CreateJsonFile("C:\\AdulRauf\\info_json.txt",lis);
                        ShareMethods.CreateXmlFile("C:\\AdulRauf\\info_xml.txt",lis);
            }

        
catch(Exception ex)
{
ex.printStackTrace();
System.out.println(ex.getMessage());
}

    }
}

