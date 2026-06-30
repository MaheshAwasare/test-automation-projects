package com.aiacademy.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    public Object[][] readExcel(String filePath,String sheetName) throws IOException{
       // FileInputStream fis=new FileInputStream
                //("src/test/resources/TestData/LoginData.xlsx");
        FileInputStream fis=new FileInputStream(filePath);
        Workbook workbook=new XSSFWorkbook(fis);
        Sheet sheet= workbook.getSheet(sheetName);
        int rowCount=sheet.getPhysicalNumberOfRows();

        Object[][]data=new Object[rowCount-1][4];

        System.out.println("Total Rows : "+rowCount);
        for(int i=1; i<rowCount; i++){

            Row row = sheet.getRow(i);

            Cell emailCell = row.getCell(0);
            Cell paaswordCell = row.getCell(1);
            Cell expectedResultCell = row.getCell(2);
            Cell expectedMessageCell = row.getCell(3);

            String email = emailCell.toString();
            String password = paaswordCell.toString();
            String expectedResult=expectedResultCell.toString();


            String expectedMessage=expectedMessageCell !=
                    null ?expectedMessageCell.toString():"";

            data[i-1][0]=email;
            data[i-1][1]=password;
            data[i-1][2]=expectedResult;
            data[i-1][3]=expectedMessage;

            System.out.println(email);
            System.out.println(password);
        }
        return data;

        }
        /*public static void main(String[] args) throws IOException {
        ExcelUtils excel=new ExcelUtils();
        excel.readExcel();
        }*/

}

