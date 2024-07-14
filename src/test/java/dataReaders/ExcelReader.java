package dataReaders;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {

    static FileInputStream fis = null;

    public FileInputStream getFileInputStream() {
        String filepath = System.getProperty("user.dir")+PropertiesReader.Data.getProperty("ExcelFilePath");
        System.out.println(filepath);
        File srcFile = new File(filepath);
        try {
            fis = new FileInputStream(srcFile);
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Check file path of test data");
            System.exit(0);
        }

        return fis;
    }


    public Object[][] getCredentialsFromExcel() throws IOException {
        fis = getFileInputStream();
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("credentials"); //get sheet by name

        int totalNumberOfRows = (sheet.getLastRowNum() + 1);
        int totalNumberOfCols = 2;

        String[][] arrayOfExcelData = new String[totalNumberOfRows][totalNumberOfCols];

        for (int i = 0; i < totalNumberOfRows; i++) {
            for (int j = 0; j < totalNumberOfCols; j++) {
                XSSFRow row = sheet.getRow(i);
                arrayOfExcelData[i][j] = row.getCell(j).toString();
            }
        }
        wb.close();
        return arrayOfExcelData;
    }

    public Object[][] getLogDataFromExcel() throws IOException {
        fis = getFileInputStream();
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("log"); //get sheet by name

        int totalNumberOfRows = (sheet.getLastRowNum() + 1);
        int totalNumberOfCols = 4;

        String[][] arrayOfExcelData = new String[totalNumberOfRows][totalNumberOfCols];

        for (int i = 0; i < totalNumberOfRows; i++) {
            for (int j = 0; j < totalNumberOfCols; j++) {
                XSSFRow row = sheet.getRow(i);
                arrayOfExcelData[i][j] = row.getCell(j).toString();
            }
        }
        wb.close();
        return arrayOfExcelData;
    }


    @DataProvider(name = "Credentials")
    public Object[][] Credentials() throws IOException {
        ExcelReader er = new ExcelReader();
        return er.getCredentialsFromExcel();
    }

    @DataProvider(name = "Logging")
    public Object[][] LogData() throws IOException {
        ExcelReader er = new ExcelReader();
        return er.getLogDataFromExcel();
    }
}
