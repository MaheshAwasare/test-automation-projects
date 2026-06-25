package com.aiacadamy.dataproviders;

import com.aiacademy.utils.ExcelUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class LoginDataProvider {
    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        ExcelUtils exel=new ExcelUtils();
        return exel.readExcel("src/test/resources/TestData/LoginData.xlsx",
                "LoginData");
    }
}
