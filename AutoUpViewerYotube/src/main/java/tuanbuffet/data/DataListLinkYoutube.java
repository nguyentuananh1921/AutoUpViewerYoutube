package tuanbuffet.data;

import org.apache.poi.ss.extractor.ExcelExtractor;
import tuanbuffet.controlExcelFile.ExcelHelper;

import java.util.ArrayList;
import java.util.List;

public class DataListLinkYoutube {
    DataLinksYoutube data;
    List<DataLinksYoutube> listData;
    ExcelHelper excel;
    public List<DataLinksYoutube> getDataListLinkExcel(){
        listData = new ArrayList<>();
        excel = new ExcelHelper();
        for ( int i = 1; ; i++ ) {
            excel.setExcelFile("C:\\dataAutoBos\\deleteHV.xlsx", "Sheet1");
            if (excel.getCell("Link", i) == null) {
                data = new DataLinksYoutube("");
            }
            else {
                if (excel.getCell("Link", i).isEmpty()) break;

                String link = excel.getCell("teacher", i);
                data = new DataLinksYoutube(link);
            }
            listData.add(data);
        }
        return listData;
    }

}
