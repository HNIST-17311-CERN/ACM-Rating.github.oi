package liu.com;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class shuai {
    static String PATH= "src/main/resources/rating.xlsx";
    static int LHCD=0;
    public static void LHCOO() throws IOException {
        //获取文件流
        FileInputStream inputStream = new FileInputStream(PATH);
        //1，创建一个工作簿
        Workbook workbook = new XSSFWorkbook(inputStream);
        LHCD=workbook.getNumberOfSheets();
        System.out.println(LHCD);
    }
    
}
