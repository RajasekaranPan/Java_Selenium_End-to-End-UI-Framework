package dataproviders;

import org.testng.annotations.DataProvider;
import testdata.FeedTestData;
import utilities.ExcelReader;

import java.util.List;

public class BuzzFeedDataProvider {

    private static final String EXCEL_PATH =
            "src/test/resources/testdata/orangeHRM.xlsx";

    @DataProvider(name = "feedData")
    public static Object[][] feedData() {

        List<String[]> rows =
                ExcelReader.getSheetData(EXCEL_PATH, "Feed");

        Object[][] data = new Object[rows.size()][1];

        for (int i = 0; i < rows.size(); i++) {

            String[] row = rows.get(i);

            data[i][0] = new FeedTestData(
                    row[0],
                    row[1],
                    row[2]
            );
        }

        return data;
    }
}