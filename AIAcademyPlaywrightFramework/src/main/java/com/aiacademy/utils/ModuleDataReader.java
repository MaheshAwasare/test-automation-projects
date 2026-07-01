package com.aiacademy.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModuleDataReader {
    public static List<String> getModuleTitles() throws IOException {
        String filePath = "src/test/resources/TestData/BeforeYouCodeModules.xlsx";
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("Modules");
        List<String> moduleTitles = new ArrayList<>();

        for(int i=1; i<= sheet.getLastRowNum(); i++){
            Row row= sheet.getRow(i);
            if(row==null){
                continue;
            }
            String moduleTitle=row.getCell(1).toString().trim();
            moduleTitles.add(moduleTitle);
        }
        workbook.close();
        fis.close();

        return moduleTitles;
    }
}
