package utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ExcelReader {

    private ExcelReader() {
    }

    public static List<String[]> getSheetData(String filePath, String sheetName) {

        List<String[]> data = new ArrayList<>();

        try (FileInputStream inputStream = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                        "Excel sheet not found: " + sheetName
                );
            }

            DataFormatter formatter = new DataFormatter();

            // Skip header row
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {

                Row row = sheet.getRow(rowIndex);

                if (row == null) {
                    continue;
                }

                String testCaseId =
                        formatter.formatCellValue(row.getCell(0)).trim();

                String content =
                        formatter.formatCellValue(row.getCell(1)).trim();

                String expected =
                        formatter.formatCellValue(row.getCell(2)).trim();

                data.add(new String[]{
                        testCaseId,
                        content,
                        expected
                });
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to read Excel file: " + filePath,
                    e
            );
        }

        return data;
    }
}