package com.aiacadamy.testngtests;

import com.aiacademy.utils.ModuleDataReader;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class TemporaryClass {
    @Test
    public void verifyModuleDataReader() throws IOException{
        List<String> titles= ModuleDataReader.getModuleTitles();
        for(String title:titles){
            System.out.println(title);
        }
    }
}
