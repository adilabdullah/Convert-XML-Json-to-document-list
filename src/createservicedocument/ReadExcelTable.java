/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createservicedocument;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author pc
 */
public class ReadExcelTable {
  public static void main(String[] args) 
    {
        List<FormatDesc> lis=new ArrayList<FormatDesc>();
        try
        {
            FileInputStream file = new FileInputStream(new File("C:\\AdulRauf\\info1.xlsx"));
 
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
             Iterator<Row> rowIterator = sheet.iterator();
             System.out.println(sheet.getPhysicalNumberOfRows());
        for(int j=1;j<sheet.getPhysicalNumberOfRows();j++)
        {
             Row row = sheet.getRow(j);
            Cell fields=row.getCell(0);
                Cell values=row.getCell(1);
 //       System.out.println(fields.getStringCellValue()+"  "+values.getStringCellValue());
        lis.add(new FormatDesc(fields.getStringCellValue(),values.getStringCellValue()));
        }
               
            
            file.close();
              ShareMethods.CreateJsonFile("C:\\AdulRauf\\info_json.txt",lis);
                        ShareMethods.CreateXmlFile("C:\\AdulRauf\\info_xml.txt",lis);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }   
}
