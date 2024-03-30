package DataUtils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class ReadExcelFile {
    HSSFWorkbook workbook;
    HSSFSheet worksheet;

    public ReadExcelFile(String excel_path){
        try {
            ClassLoader classLoarder = getClass().getClassLoader();
            File src = new File(Objects.requireNonNull(classLoarder.getResource(excel_path)).getFile());
            FileInputStream fis = new FileInputStream(src);
            workbook = new HSSFWorkbook(fis);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String getData(int sheetnumber, int row, int column){
        worksheet = workbook.getSheetAt(sheetnumber);
        String data = worksheet.getRow(row).getCell(column).getStringCellValue();
        return data;
    }

    public int getRowCount(int sheetIndex){
        int row = workbook.getSheetAt(sheetIndex).getLastRowNum();
        row = row + 1;
        return row;
    }
}
